package datastructures.linear;

/**
 * 배열 기반 큐 인터페이스
 * 원형 배열을 이용한 큐의 추상적 데이터 타입
 * @param <T> 데이터 타입
 */
public interface ArrayQueue<T> extends Queue<T> {
    /**
     * 큐의 용량을 반환
     * @return 내부 배열의 크기
     */
    int capacity();
    
    /**
     * 배열을 재구성하여 용량을 확장
     */
    void resize();
    
    /**
     * 큐의 현재 사용률을 반환
     * @return size / capacity 비율
     */
    double getUsageRatio();
}
