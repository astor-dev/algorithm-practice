import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main{ 

//     static variables

//     ================
    public static void main(String[] args) throws Exception {
        try{                    
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st;
            
            st = new StringTokenizer(br.readLine());
            
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            //입력 값이 저장 될 배열
            long[] arr = new long[n+1];
            for (int i = 1; i < n+1; i++){
                arr[i] = Long.parseLong(br.readLine());
            }
            SegmentTree segTree = new SegmentTree(n);
            
            segTree.init(arr, 1, 1, n);
            
            for(int i = 0; i < m+k; i++){
                st = new StringTokenizer(br.readLine());
                
                int cmd = Integer.parseInt(st.nextToken());
                
                int a = Integer.parseInt(st.nextToken());
                long b = Long.parseLong(st.nextToken());
                
                if(cmd==1){
                    //b - arr[a] = diff
                    segTree.update(1,1,n,a,b-arr[a]);
                    arr[a] = b;
                } else{
                    bw.write(segTree.sum(1,1,n,a,(int)b) + "\n");
                }
            }
            bw.flush();
            bw.close();  
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }
//     static methods        
    
//     ==============
}    

class SegmentTree{
    long tree[];
    int treeSize;
    
    public SegmentTree(int arrSize){
        //h: 트리 높이. 입력 배열 크기를 기반으로 트리 사이즈를 계산한다.
        int h = (int)Math.ceil(Math.log(arrSize) / Math.log(2));
        
        this.treeSize = (int)Math.pow(2, h+1);
        
        tree = new long[treeSize];
    }
    // arr: 원소가 담긴 배열, node: 현재 노드, start: 배열시작, end: 배열끝
    public long init(long[] arr, int node, int start, int end){
        //start == end 이면 leafNode이므로 그대로 값 담기
        if(start == end){
            //대입연산 하면서 동시에 리턴
            return tree[node] = arr[start];
        }
        
        return tree[node] =
            init(arr, node*2, start, (start+end)/2)
            + init(arr, node*2+1, (start + end)/2 +1, end);
    }
    public void update(int node, int start, int end, int idx, long diff){
        if(idx < start || end < idx) return;
        
        tree[node] += diff;
        
        if(start != end){
            update(node*2, start, (start+end)/2, idx, diff);
            update(node*2+1, (start+end)/2+1, end, idx, diff);
        }
    }
    
//     node: 현재 노드, start: 배열시작, end: 배열끝, left:원하는 누적합 시작, right: 원하는 누적합 끝
    public long sum(int node, int start, int end, int left, int right){
        if(left > end || right < start){
            return 0;
        }
        
        if( left <= start && end <= right){
            return tree[node];
        }
        
        return sum(node*2, start, (start+end)/2, left, right)+
            sum(node*2+1, (start + end)/2+1, end, left, right);
    }
}
