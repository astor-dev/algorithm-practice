import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class Main{
    static int t;
    static int n;
    static int m;
    static int[] initial_rank;
    static int[] changed_rank;
    
    public static void main(String[] args) throws Exception {
        try{                    
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));                                
            StringTokenizer st;
            t = Integer.parseInt(br.readLine());
            for(int i = 0; i < t; i ++){
                n = Integer.parseInt(br.readLine());
                initial_rank = new int[n+1];
                changed_rank = new int[n+1];
                st = new StringTokenizer(br.readLine());
                for(int j = 1; j <= n; j++){                    
                    int team = Integer.parseInt(st.nextToken());
                    initial_rank[team] = j;
                    changed_rank[team] = j;
                }
                m = Integer.parseInt(br.readLine());
                for(int j = 0; j < m; j++){
                    st = new StringTokenizer(br.readLine());                    
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    if(initial_rank[a] > initial_rank[b]){
                        changed_rank[a]--;
                        changed_rank[b]++;
                    } else{
                        changed_rank[a]++;
                        changed_rank[b]--;
                    }                    
                }
                Supplier<String> topology_sort = () -> {
                    int searching_rank = 0;
                    String answer = "";
                    PriorityQueue<Node> pq = new PriorityQueue<>();
                    for(int z = 1; z <= n; z++){
                        pq.add(new Node(z, changed_rank[z]));
                    }
                    while(!pq.isEmpty()){
                        Node n = pq.poll();
                        if(searching_rank == n.value){
                            return "IMPOSSIBLE\n";
                        }
                        searching_rank = n.value;
                        answer = answer + n.index + " ";
                    }
                    return answer + "\n";
                };
                bw.write(topology_sort.get());
            }
            bw.flush();
            bw.close();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }
    }
}
class Node implements Comparable<Node>{
    int index;
    int value;
    Node(int index,  int value){
        this.index = index;
        this.value = value;
    }
    @Override
    public int compareTo(Node target){
        return this.value - target.value;
    }
}
