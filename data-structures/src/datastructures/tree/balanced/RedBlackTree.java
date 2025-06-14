package datastructures.tree.balanced;

import datastructures.tree.binary.BinarySearchTree;

/**
 * 레드-블랙 트리 ADT 인터페이스
 * @param <T> 데이터 타입 (Comparable 구현 필요)
 */
public interface RedBlackTree<T extends Comparable<T>> extends BinarySearchTree<T> {
    /**
     * 삽입 후 레드-블랙 트리 속성을 유지하기 위한 수정
     * @param node 삽입된 노드
     */
    void fixAfterInsertion(RedBlackTreeNode<T> node);
    
    /**
     * 삭제 후 레드-블랙 트리 속성을 유지하기 위한 수정
     * @param node 삭제와 관련된 노드
     */
    void fixAfterDeletion(RedBlackTreeNode<T> node);
    
    /**
     * 좌회전 연산
     * @param node 회전할 노드
     */
    void rotateLeft(RedBlackTreeNode<T> node);
    
    /**
     * 우회전 연산
     * @param node 회전할 노드
     */
    void rotateRight(RedBlackTreeNode<T> node);
    
    /**
     * 레드-블랙 트리의 속성이 유지되는지 확인
     * @return 속성이 유지되면 true
     */
    boolean isValidRedBlackTree();
    
    /**
     * 트리의 검은 높이(black height)를 반환
     * @return 검은 높이
     */
    int getBlackHeight();
    
    /**
     * 특정 노드의 색상을 반환
     * @param node 대상 노드
     * @return 노드의 색상
     */
    RedBlackTreeNode.Color getColor(RedBlackTreeNode<T> node);
    
    /**
     * 특정 노드의 색상을 설정
     * @param node 대상 노드
     * @param color 설정할 색상
     */
    void setColor(RedBlackTreeNode<T> node, RedBlackTreeNode.Color color);
    
    /**
     * 루트 노드를 RedBlackTreeNode로 반환
     * @return 루트 노드
     */
    RedBlackTreeNode<T> getRedBlackRoot();
    
    /**
     * 특정 경로의 검은 노드 개수를 반환
     * @param node 시작 노드
     * @return 검은 노드 개수
     */
    int countBlackNodes(RedBlackTreeNode<T> node);
} 