package datastructures.tree;

import java.util.List;

public interface TreeNode<T> {
    T getData();
    void setData(T data);
    TreeNode<T> getParent();
    void setParent(TreeNode<T> parent);
    List<TreeNode<T>> getChildren();
    boolean addChild(TreeNode<T> child);
    boolean removeChild(TreeNode<T> child);
    boolean isLeaf();
    boolean hasLeftChild();
    boolean hasRightChild();
}
