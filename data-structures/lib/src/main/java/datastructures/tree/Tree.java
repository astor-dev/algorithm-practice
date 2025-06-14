package datastructures.tree;

import datastructures.common.DataStructure;

import java.util.List;


public interface Tree<T> extends DataStructure<T> {
    TreeNode<T> getRoot();
    void setRoot(TreeNode<T> root);
    boolean addChild(TreeNode<T> parent, TreeNode<T> child);
    boolean removeNode(TreeNode<T> node);
    TreeNode<T> findNode(T value);
    int getHeight();
    int getDepth(TreeNode<T> node);
    List<T> preOrderTraversal();
    List<T> inOrderTraversal();
    List<T> postOrderTraversal();
    List<T> levelOrderTraversal();
    List<TreeNode<T>> getChildren(TreeNode<T> node);
    boolean isLeaf(TreeNode<T> node);
    boolean isInternal(TreeNode<T> node);
} 