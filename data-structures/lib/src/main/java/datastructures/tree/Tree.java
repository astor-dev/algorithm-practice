package datastructures.tree;

import datastructures.common.DataStructure;

import java.util.List;

/**
 * 트리 ADT 인터페이스
 * @param <T> 데이터 타입
 */
public interface Tree<T> extends DataStructure<T> {
    /**
     * 루트 노드를 반환
     * @return 루트 노드
     */
    TreeNode<T> getRoot();
    
    /**
     * 루트 노드를 설정
     * @param root 새로운 루트 노드
     */
    void setRoot(TreeNode<T> root);
    
    /**
     * 특정 노드에 자식 노드를 추가
     * @param parent 부모 노드
     * @param child 자식 노드
     * @return 추가 성공 시 true
     */
    boolean addChild(TreeNode<T> parent, TreeNode<T> child);
    
    /**
     * 특정 노드를 제거
     * @param node 제거할 노드
     * @return 제거 성공 시 true
     */
    boolean removeNode(TreeNode<T> node);
    
    /**
     * 특정 값을 가진 노드를 찾음
     * @param value 찾을 값
     * @return 해당 값을 가진 노드, 없으면 null
     */
    TreeNode<T> findNode(T value);
    
    /**
     * 트리의 높이를 반환
     * @return 트리의 높이
     */
    int getHeight();
    
    /**
     * 트리의 깊이를 반환 (특정 노드까지의)
     * @param node 대상 노드
     * @return 노드의 깊이
     */
    int getDepth(TreeNode<T> node);
    
    /**
     * 전위 순회 결과를 반환
     * @return 전위 순회 리스트
     */
    List<T> preOrderTraversal();
    
    /**
     * 중위 순회 결과를 반환
     * @return 중위 순회 리스트
     */
    List<T> inOrderTraversal();
    
    /**
     * 후위 순회 결과를 반환
     * @return 후위 순회 리스트
     */
    List<T> postOrderTraversal();
    
    /**
     * 레벨 순회 결과를 반환
     * @return 레벨 순회 리스트
     */
    List<T> levelOrderTraversal();
    
    /**
     * 특정 노드의 자식 노드들을 반환
     * @param node 부모 노드
     * @return 자식 노드들의 리스트
     */
    List<TreeNode<T>> getChildren(TreeNode<T> node);
    
    /**
     * 특정 노드가 리프 노드인지 확인
     * @param node 확인할 노드
     * @return 리프 노드이면 true
     */
    boolean isLeaf(TreeNode<T> node);
    
    /**
     * 특정 노드가 내부 노드인지 확인
     * @param node 확인할 노드
     * @return 내부 노드이면 true
     */
    boolean isInternal(TreeNode<T> node);
} 