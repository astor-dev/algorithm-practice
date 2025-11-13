import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class Main {
    static AtomicInteger aCount = new AtomicInteger(0);
    static AtomicInteger bCount = new AtomicInteger(0);
    // permit이 여러 개일 때 데드락 재현: 각 세마포어에 3개의 permit
    // 각 작업은 1개씩만 permit을 획득하지만, 여러 작업이 동시에 접근 가능
    static Semaphore semaphoreA = new Semaphore(3);
    static Semaphore semaphoreB = new Semaphore(3);
    static AtomicInteger jobIdCounter = new AtomicInteger(0);

    static void log(String message) {
        long timestamp = System.currentTimeMillis();
        String threadName = Thread.currentThread().getName();
        System.out.printf("[%d] [%s] %s%n", timestamp, threadName, message);
    }

    static class JobAB implements Runnable {
        private final int jobId;

        JobAB(int jobId) {
            this.jobId = jobId;
        }

        @Override
        public void run() {
            log(String.format("JobAB-%d: 시작", jobId));
            try {
                // semaphoreA 1개 획득
                log(String.format("JobAB-%d: semaphoreA 1개 획득 시도... (현재 available: %d)", 
                    jobId, semaphoreA.availablePermits()));
                semaphoreA.acquire();
                log(String.format("JobAB-%d: semaphoreA 1개 획득 성공! aCount 증가", jobId));
                aCount.incrementAndGet();
                
                Thread.sleep(1000);
                
                // semaphoreB 1개 획득 시도 (데드락 가능성)
                log(String.format("JobAB-%d: semaphoreB 1개 획득 시도... (현재 available: %d)", 
                    jobId, semaphoreB.availablePermits()));
                semaphoreB.acquire();
                log(String.format("JobAB-%d: semaphoreB 1개 획득 성공! bCount 증가", jobId));
                bCount.incrementAndGet();
                
                Thread.sleep(1000);
                
                log(String.format("JobAB-%d: semaphoreB 1개 해제", jobId));
                semaphoreB.release();
                log(String.format("JobAB-%d: semaphoreA 1개 해제", jobId));
                semaphoreA.release();
                
                log(String.format("JobAB-%d: 완료! (aCount: %d, bCount: %d)", 
                    jobId, aCount.get(), bCount.get()));
            } catch (InterruptedException e) {
                log(String.format("JobAB-%d: 인터럽트 발생!", jobId));
                e.printStackTrace();
            }
        }
    }

    static class JobBA implements Runnable {
        private final int jobId;

        JobBA(int jobId) {
            this.jobId = jobId;
        }

        @Override
        public void run() {
            log(String.format("JobBA-%d: 시작", jobId));
            try {
                // semaphoreB 1개 획득
                log(String.format("JobBA-%d: semaphoreB 1개 획득 시도... (현재 available: %d)", 
                    jobId, semaphoreB.availablePermits()));
                semaphoreB.acquire();
                log(String.format("JobBA-%d: semaphoreB 1개 획득 성공! bCount 증가", jobId));
                bCount.incrementAndGet();
                
                Thread.sleep(1000);
                
                // semaphoreA 1개 획득 시도 (데드락 가능성)
                log(String.format("JobBA-%d: semaphoreA 1개 획득 시도... (현재 available: %d)", 
                    jobId, semaphoreA.availablePermits()));
                semaphoreA.acquire();
                log(String.format("JobBA-%d: semaphoreA 1개 획득 성공! aCount 증가", jobId));
                aCount.incrementAndGet();
                
                Thread.sleep(1000);
                
                log(String.format("JobBA-%d: semaphoreA 1개 해제", jobId));
                semaphoreA.release();
                log(String.format("JobBA-%d: semaphoreB 1개 해제", jobId));
                semaphoreB.release();
                
                log(String.format("JobBA-%d: 완료! (aCount: %d, bCount: %d)", 
                    jobId, aCount.get(), bCount.get()));
            } catch (InterruptedException e) {
                log(String.format("JobBA-%d: 인터럽트 발생!", jobId));
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        log("=== permit이 여러 개일 때 데드락 실험 시작 ===");
        log(String.format("설정 - semaphoreA permit: %d, semaphoreB permit: %d", 3, 3));
        log("각 작업은 1개씩만 permit을 획득하지만, 여러 작업이 동시에 접근 가능");
        log(String.format("초기 상태 - semaphoreA available: %d, semaphoreB available: %d", 
            semaphoreA.availablePermits(), semaphoreB.availablePermits()));
        log("데드락 시나리오:");
        log("  - JobAB 3개가 각각 semaphoreA 1개씩 획득 (총 3개 모두 점유)");
        log("  - JobBA 3개가 각각 semaphoreB 1개씩 획득 (총 3개 모두 점유)");
        log("  - JobAB들은 semaphoreB를 기다리고, JobBA들은 semaphoreA를 기다림 → 데드락!");
        
        // 데드락을 확실히 재현하기 위해 스레드 풀 크기를 늘림
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        
        log("JobAB 작업 10개 제출...");
        for (int i = 0; i < 10; i++) {
            int jobId = jobIdCounter.incrementAndGet();
            executorService.submit(new JobAB(jobId));
        }
        
        log("JobBA 작업 10개 제출...");
        for (int i = 0; i < 10; i++) {
            int jobId = jobIdCounter.incrementAndGet();
            executorService.submit(new JobBA(jobId));
        }
        
        executorService.shutdown();
        log("ExecutorService shutdown 호출됨");
        
        try {
            boolean terminated = executorService.awaitTermination(30, TimeUnit.SECONDS);
            if (terminated) {
                log("=== 모든 작업 완료 ===");
            } else {
                log("=== 타임아웃 발생! 데드락으로 인해 일부 작업이 완료되지 않았습니다 ===");
                log(String.format("최종 상태 - aCount: %d, bCount: %d", aCount.get(), bCount.get()));
                log(String.format("현재 상태 - semaphoreA available: %d, semaphoreB available: %d", 
                    semaphoreA.availablePermits(), semaphoreB.availablePermits()));
                log("데드락 발생: JobAB들이 semaphoreA를 모두 보유하고 semaphoreB 대기 중,");
                log("            JobBA들이 semaphoreB를 모두 보유하고 semaphoreA 대기 중");
            }
        } catch (InterruptedException e) {
            log("메인 스레드 인터럽트 발생!");
            e.printStackTrace();
        }
    }
}