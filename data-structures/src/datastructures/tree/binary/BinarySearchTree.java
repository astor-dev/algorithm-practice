package datastructures.tree.binary;

import java.util.List;

/**
 * 이진 탐색 트리 ADT 인터페이스
 * @param <T> 데이터 타입 (Comparable 구현 필요)
 */
public interface BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
    /**
     * 값을 트리에 삽입
     * @param value 삽입할 값
     * @return 삽입 성공 시 true
     */
    boolean insert(T value);
    
    /**
     * 값을 트리에서 삭제
     * @param value 삭제할 값
     * @return 삭제 성공 시 true
     */
    boolean delete(T value);
    
    /**
     * 값을 트리에서 검색
     * @param value 검색할 값
     * @return 해당 값을 가진 노드, 없으면 null
     */
    TreeNode<T> search(T value);
    
    /**
     * 최솟값을 가진 노드를 반환
     * @return 최솟값 노드
     */
    TreeNode<T> findMin();
    
    /**
     * 최댓값을 가진 노드를 반환
     * @return 최댓값 노드
     */
    TreeNode<T> findMax();
    
    /**
     * 특정 노드의 후계자(successor)를 반환
     * @param node 대상 노드
     * @return 후계자 노드
     */
    TreeNode<T> getSuccessor(TreeNode<T> node);
    
    /**
     * 특정 노드의 전임자(predecessor)를 반환
     * @param node 대상 노드
     * @return 전임자 노드
     */
    TreeNode<T> getPredecessor(TreeNode<T> node);
    
    /**
     * 특정 범위 내의 값들을 반환
     * @param min 최솟값
     * @param max 최댓값
     * @return 범위 내 값들의 리스트
     */
    List<T> rangeQuery(T min, T max);
    
    /**
     * 트리가 유효한 이진 탐색 트리인지 확인
     * @return 유효하면 true
     */
    boolean isValidBST();
    
    /**
     * 특정 값보다 작은 값들의 개수를 반환
     * @param value 기준 값
     * @return 작은 값들의 개수
     */
    int countSmallerThan(T value);
    
    /**
     * 특정 값보다 큰 값들의 개수를 반환
     * @param value 기준 값
     * @return 큰 값들의 개수
     */
    int countGreaterThan(T value);
} 