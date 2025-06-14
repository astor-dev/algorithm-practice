package datastructures.linear;

/**
 * 연결 리스트 기반 스택 인터페이스
 * 연결 노드를 이용한 스택의 추상적 데이터 타입
 * @param <T> 데이터 타입
 */
public interface LinkedStack<T> extends Stack<T> {
    /**
     * 스택의 맨 위 노드에 대한 참조를 제공하는 메서드
     * (디버깅이나 내부 구조 확인용)
     * @return 맨 위 노드의 정보
     */
    String getTopNodeInfo();
} 