package datastructures.tree;

import java.util.List;

/**
 * 트리 노드 인터페이스
 * 트리 자료구조에서 사용할 노드의 추상적 데이터 타입
 * @param <T> 데이터 타입
 */
public interface TreeNode<T> {
    /**
     * 노드의 데이터를 반환
     * @return 노드의 데이터
     */
    T getData();
    
    /**
     * 노드의 데이터를 설정
     * @param data 설정할 데이터
     */
    void setData(T data);
    
    /**
     * 왼쪽 자식 노드를 반환
     * @return 왼쪽 자식 노드
     */
    TreeNode<T> getLeft();
    
    /**
     * 왼쪽 자식 노드를 설정
     * @param left 왼쪽 자식 노드
     */
    void setLeft(TreeNode<T> left);
    
    /**
     * 오른쪽 자식 노드를 반환
     * @return 오른쪽 자식 노드
     */
    TreeNode<T> getRight();
    
    /**
     * 오른쪽 자식 노드를 설정
     * @param right 오른쪽 자식 노드
     */
    void setRight(TreeNode<T> right);
    
    /**
     * 부모 노드를 반환
     * @return 부모 노드
     */
    TreeNode<T> getParent();
    
    /**
     * 부모 노드를 설정
     * @param parent 부모 노드
     */
    void setParent(TreeNode<T> parent);
    
    /**
     * 노드가 리프 노드인지 확인
     * @return 리프 노드이면 true
     */
    boolean isLeaf();
    
    /**
     * 왼쪽 자식이 있는지 확인
     * @return 왼쪽 자식이 있으면 true
     */
    boolean hasLeftChild();
    
    /**
     * 오른쪽 자식이 있는지 확인
     * @return 오른쪽 자식이 있으면 true
     */
    boolean hasRightChild();
    
    /**
     * 모든 자식 노드들을 반환
     * @return 자식 노드들의 리스트
     */
    List<TreeNode<T>> getChildren();
    
    /**
     * 자식 노드를 추가
     * @param child 추가할 자식 노드
     * @return 추가 성공 시 true
     */
    boolean addChild(TreeNode<T> child);
    
    /**
     * 자식 노드를 제거
     * @param child 제거할 자식 노드
     * @return 제거 성공 시 true
     */
    boolean removeChild(TreeNode<T> child);
} 