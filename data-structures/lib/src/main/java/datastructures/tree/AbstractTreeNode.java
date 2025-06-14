package datastructures.tree;

public abstract class AbstractTreeNode<T> implements TreeNode<T> {
    protected T data;
    protected TreeNode<T> parent;

    @Override
    public T getData() {
        return this.data;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public TreeNode<T> getParent() {
        return this.parent;
    }

    @Override
    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    @Override
    public boolean isLeaf() {
        return getChildren() == null || getChildren().isEmpty();
    }
}
