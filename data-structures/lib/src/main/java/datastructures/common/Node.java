package datastructures.common;


public class Node<T> {
    protected T data;
    protected Node<T> next;

    public T getData() {
        return data;
    };

    void setData(T data) {
        this.data = data;
    };
    Node<T> getNext() {
        return next;
    };
    void setNext(Node<T> next) {
        this.next = next;
    };
}
