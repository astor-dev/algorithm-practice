import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main{ 

//     static variables
            static TreeMap<String, Node> map = new TreeMap<>();

//     ================
    public static void main(String[] args) throws Exception {
        try{                    
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st;
            int N = Integer.parseInt(br.readLine());
            Tree tree = new Tree();
            /*
            TreeMap 만드는데 key: A, 안에 들은 정보: node key, l, r.
            treemap을 순회하는데 A를 시작으로 재귀함수
            start(key){
                
                node left = start(left)
                node right = start(rigth)
                return new node(left, right)
            }
            */
            for(int i = 0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                String root = st.nextToken();
                if(!map.containsKey(root)){
                    map.put(root,tree.addNode(root));
                }
                String left = st.nextToken();
                if(!map.containsKey(left)){
                    map.put(left, tree.addNode(left));
                }
                if(!left.equals(".")){
                    map.get(root).addLeft(map.get(left));                    
                }
                String right = st.nextToken();
                if(!map.containsKey(right)){
                    map.put(right, tree.addNode(right));
                }
                if(!right.equals(".")){
                    map.get(root).addRight(map.get(right));                    
                }
            }
            tree.preOrder(map.get("A"));
            System.out.println();
            tree.inOrder(map.get("A"));
            System.out.println();
            tree.postOrder(map.get("A"));
            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }
//     static methods        

//     ==============
}    
class Node{
    Object data;
    Node left;
    Node right;

    public Node(Object data){
        this.data = data;
        left = null;
        right = null;
    }

    public void addLeft(Node left){
        this.left = left;
    }

    public void addRight(Node right){
        this.right = right;
    }

    public void deleteLeft(){
        left = null;
    }

    public void deleteRight(){
        right = null;
    }
}
class Tree{
    
    public Node addNode(Object data){
        return new Node(data);        
    }
    
    public void preOrder(Node node){
        if(node==null){
            return;
        }
        System.out.print(node.data);
        preOrder(node.left);
        preOrder(node.right);
    }
    
    public void inOrder(Node node){
        if(node==null){
            return;
        }
        inOrder(node.left);
        System.out.print(node.data);
        inOrder(node.right);
    }
    
    public void postOrder(Node node){
        if(node==null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data);
    }

}
