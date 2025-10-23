class Main {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    static class BinarySearchTree {
        Node head;

        void add(int value) {
            Node node = new Node(value);
            if(head == null) {
                head = node;
                return;
            }
            Node parent = head;
            while(true) {
                if(node.value > parent.value) {
                    if(parent.right != null) {
                        parent = parent.right;
                    } else {
                         parent.right = node;
                         break;
                    }
                } else {
                    if(parent.left != null) {
                        parent = parent.left;
                    } else {
                        parent.left = node;
                        break;
                    }
                }
            }
        }

        void remove(int value) {
            if(head == null) {
                return;
            }
            Node parent;
            Node node = head;
            while(node.value != value ) {
                if(value > node.value) {
                    parent = node;
                    node = node.right;
                } else {
                    parent = node;
                    node = node.left;
                }
                if(node == null) return;
            }

        }

        public void inOrder() {
            if(head == null) {
                return;
            }
            inOrder(head);
        }

        private void inOrder(Node node) {
            if(node == null) return;
            inOrder(node.left);
            System.out.println(node.value);
            inOrder(node.right);
        }
    }




    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.add(10);
        bst.add(20);
        bst.add(20);
        bst.add(20);
        bst.add(5);
        bst.add(1);
        bst.add(100);
        bst.add(-10);
        bst.inOrder();
    }
}