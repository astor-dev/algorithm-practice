package datastructures.tree.binary;


import datastructures.tree.Tree;
import datastructures.tree.TreeNode;

/**
 * 이진 트리 ADT 인터페이스
 * @param <T> 데이터 타입
 */
public interface BinaryTree<T> extends Tree<T> {
    /**
     * 특정 노드에 왼쪽 자식을 추가
     * @param parent 부모 노드
     * @param leftChild 왼쪽 자식 노드
     * @return 추가 성공 시 true
     */
    boolean addLeftChild(TreeNode<T> parent, TreeNode<T> leftChild);
    
    /**
     * 특정 노드에 오른쪽 자식을 추가
     * @param parent 부모 노드
     * @param rightChild 오른쪽 자식 노드
     * @return 추가 성공 시 true
     */
    boolean addRightChild(TreeNode<T> parent, TreeNode<T> rightChild);
    
    /**
     * 특정 노드의 왼쪽 자식을 반환
     * @param node 부모 노드
     * @return 왼쪽 자식 노드
     */
    TreeNode<T> getLeftChild(TreeNode<T> node);
    
    /**
     * 특정 노드의 오른쪽 자식을 반환
     * @param node 부모 노드
     * @return 오른쪽 자식 노드
     */
    TreeNode<T> getRightChild(TreeNode<T> node);
    
    /**
     * 특정 노드의 형제 노드를 반환
     * @param node 대상 노드
     * @return 형제 노드
     */
    TreeNode<T> getSibling(TreeNode<T> node);
    
    /**
     * 이진 트리가 완전 이진 트리인지 확인
     * @return 완전 이진 트리이면 true
     */
    boolean isComplete();
    
    /**
     * 이진 트리가 포화 이진 트리인지 확인
     * @return 포화 이진 트리이면 true
     */
    boolean isFull();
    
    /**
     * 이진 트리가 균형 이진 트리인지 확인
     * @return 균형 이진 트리이면 true
     */
    boolean isBalanced();
    
    /**
     * 트리를 좌우 대칭으로 뒤집기
     */
    void mirror();
    
    /**
     * 특정 노드의 서브트리 크기를 반환
     * @param node 대상 노드
     * @return 서브트리의 노드 개수
     */
    int getSubtreeSize(TreeNode<T> node);
} 