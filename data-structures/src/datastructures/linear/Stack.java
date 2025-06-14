package datastructures.linear;


/**
 * 스택 ADT 인터페이스
 * @param <T> 데이터 타입
 */
public interface Stack<T> extends DataStructure<T> {
    /**
     * 스택의 맨 위에 요소를 추가
     * @param element 추가할 요소
     */
    void push(T element);
    
    /**
     * 스택의 맨 위 요소를 제거하고 반환
     * @return 제거된 요소
     * @throws RuntimeException 스택이 비어있는 경우
     */
    T pop();
    
    /**
     * 스택의 맨 위 요소를 반환 (제거하지 않음)
     * @return 맨 위 요소
     * @throws RuntimeException 스택이 비어있는 경우
     */
    T peek();
    
    /**
     * 특정 요소가 스택에서 맨 위부터 몇 번째 위치에 있는지 반환
     * @param element 찾을 요소
     * @return 위치 (1부터 시작), 없으면 -1
     */
    int search(T element);
} 