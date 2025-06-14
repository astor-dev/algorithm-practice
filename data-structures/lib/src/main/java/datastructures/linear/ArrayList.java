package datastructures.linear;

/**
 * 배열 기반 리스트 인터페이스
 * 동적 배열을 이용한 리스트의 추상적 데이터 타입
 * @param <T> 데이터 타입
 */
public interface ArrayList<T> extends List<T> {
    /**
     * 내부 배열의 용량을 반환
     * @return 현재 내부 배열의 크기
     */
    int capacity();
    
    /**
     * 용량을 확장하여 성능 최적화
     */
    void ensureCapacity();
    
    /**
     * 사용하지 않는 공간을 제거하여 메모리 최적화
     */
    void trimToSize();
} 