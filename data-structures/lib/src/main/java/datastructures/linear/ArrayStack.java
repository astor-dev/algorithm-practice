package datastructures.linear;

/**
 * 배열 기반 스택 인터페이스
 * 동적 배열을 이용한 스택의 추상적 데이터 타입
 * @param <T> 데이터 타입
 */
public interface ArrayStack<T> extends Stack<T> {
    /**
     * 스택의 용량을 반환
     * @return 내부 배열의 크기
     */
    int capacity();
    
    /**
     * 용량을 확장하여 성능 최적화
     */
    void ensureCapacity();
} 