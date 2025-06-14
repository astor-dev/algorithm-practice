package datastructures.tree.balanced;


import datastructures.tree.TreeNode;

/**
 * 레드-블랙 트리 노드 인터페이스
 * 색상 정보를 포함한 트리 노드의 추상적 데이터 타입
 * @param <T> 데이터 타입
 */
public interface RedBlackTreeNode<T> extends TreeNode<T> {
    /**
     * 노드 색상 열거형
     */
    enum Color {
        RED, BLACK
    }
    
    /**
     * 노드의 색상을 반환
     * @return 노드의 색상
     */
    Color getColor();
    
    /**
     * 노드의 색상을 설정
     * @param color 설정할 색상
     */
    void setColor(Color color);
    
    /**
     * 노드가 빨간색인지 확인
     * @return 빨간색이면 true
     */
    boolean isRed();
    
    /**
     * 노드가 검은색인지 확인
     * @return 검은색이면 true
     */
    boolean isBlack();
    
    /**
     * 조부모 노드를 반환
     * @return 조부모 노드
     */
    RedBlackTreeNode<T> getGrandparent();
    
    /**
     * 삼촌 노드를 반환
     * @return 삼촌 노드
     */
    RedBlackTreeNode<T> getUncle();
    
    /**
     * 형제 노드를 반환
     * @return 형제 노드
     */
    RedBlackTreeNode<T> getSibling();
    
    /**
     * 왼쪽 자식 노드를 RedBlackTreeNode로 반환
     * @return 왼쪽 자식 노드
     */
    @Override
    RedBlackTreeNode<T> getLeft();
    
    /**
     * 오른쪽 자식 노드를 RedBlackTreeNode로 반환
     * @return 오른쪽 자식 노드
     */
    @Override
    RedBlackTreeNode<T> getRight();
    
    /**
     * 부모 노드를 RedBlackTreeNode로 반환
     * @return 부모 노드
     */
    @Override
    RedBlackTreeNode<T> getParent();
} 