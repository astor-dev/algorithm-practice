import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main{ 
        
    public static void main(String[] args) throws Exception {
        try{   
// ========================================================Write Here========================================================            
            StringBuilder sb = new StringBuilder();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));            
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            minHeap heap = new minHeap();
            
            int N = Integer.valueOf(br.readLine());
            for(int i=0; i<N; i++){
                int val = Integer.valueOf(br.readLine());
                if(val == 0){
                    System.out.println(heap.delete());
                    continue;
                }
                heap.add(val);
                //heap.print();
            }

// ========================================================Write Here========================================================            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }
}

class minHeap{
    ArrayList<Integer> heap;
    minHeap(){
        heap = new ArrayList<Integer>();
        heap.add(0);
    }
    /*
    index 0은 사용안함
    index 1 = 루트노드
    2, 3: 1의 자식노드
    ...
    parent n < 2n(left child node), 2n+1(right child node) 을 만족하며 저장
    */
    public void add(int n){
        heap.add(n);
        int index = heap.size()-1;
        
        while(index>1 &&heap.get(index/2) > heap.get(index)){
            int parentVal = heap.get(index/2);        
            heap.set(index/2, n); //부모노드에 n을 넣고
            heap.set(index, parentVal); //원래 자리에 옛날 부모를 넣는다.
            
            index = index/2;
        }
        
    }
    public int delete(){
        if (heap.size() < 2){
            return 0;
        }
        int deletedItem = heap.get(1); //리턴용 루트노드(최소값) 저장
        heap.set(1,heap.get(heap.size()-1));//마지막노드(큰 값! 최대값은 아닐 수 있음)을 루트노드에 넣는다
                                            //이 과정에서 루트노드는 덮어씌어지며 삭제됨
        heap.remove(heap.size()-1);         //루트노드로 복제했으니 마지막노드를 지운다.
        
        int index = 1; //삽입할 인덱스 값
        while( (index*2)<heap.size() ){ //삽입할 곳의 자식노드의 인덱스 값이 힙보다 커진다? outofrange에러
            int min = heap.get(index*2); //왼쪽노드의 값
            int minIndex = index*2;
            if(((index*2+1) < heap.size()) && min > heap.get(index*2+1)){
                min = heap.get(index*2+1);
                minIndex = index*2+1;
            }
            
            if(min>heap.get(index)){
                break;
            }
            
            int tmp = heap.get(index);
            heap.set(index, min);
            heap.set(minIndex, tmp);
            index = minIndex;
        }
        return deletedItem;                                
    }
    public void print(){
        System.out.println(heap.toString());
    }
}
