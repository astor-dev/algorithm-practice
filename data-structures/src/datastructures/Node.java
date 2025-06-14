package datastructures.common;


public interface Node<T> {
    T getData();
    void setData(T data);
    Node<T> getNext();
    void setNext(Node<T> next);
}


/**
 * 모든 자료구조에서 사용할 기본 Node 클래스
 * @param <T> 데이터 타입
 */
public class Node<T> implements Node<T> {
    protected T data;
    protected Node<T> next;
    
    public Node(T data) {
        this.data = data;
        this.next = null;
    }
    
    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
    
    public Node<T> getNext() {
        return next;
    }
    
    public void setNext(Node<T> next) {
        this.next = next;
    }
    
    @Override
    public String toString() {
        return data != null ? data.toString() : "null";
    }
} 