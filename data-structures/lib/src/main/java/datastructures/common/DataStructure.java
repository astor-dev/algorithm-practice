package datastructures.common;

/**
 * 모든 자료구조의 기본 인터페이스
 * @param <T> 데이터 타입
 */
public interface DataStructure<T> {
    /**
     * 자료구조의 크기를 반환
     * @return 요소의 개수
     */
    int size();
    
    /**
     * 자료구조가 비어있는지 확인
     * @return 비어있으면 true, 아니면 false
     */
    boolean isEmpty();
    
    /**
     * 자료구조를 비움
     */
    void clear();
    
    /**
     * 특정 요소가 포함되어 있는지 확인
     * @param element 찾을 요소
     * @return 포함되어 있으면 true, 아니면 false
     */
    boolean contains(T element);
    
    /**
     * 자료구조를 배열로 변환
     * @return 요소들의 배열
     */
    Object[] toArray();
} 