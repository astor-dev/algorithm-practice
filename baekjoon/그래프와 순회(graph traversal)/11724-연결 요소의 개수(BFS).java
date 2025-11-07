    import java.io.*;
    import java.util.*;
    import java.util.stream.*;

    public class Main{ 
        public static void main(String[] args) throws Exception {
            try{   
    // ========================================================Write Here========================================================                       
                /*
                11724 연결 요소의 개수
                전체 노드 받아서 그래프 만들기
                예제
                Type: ArrayList<ArrayList<Integer>>
                size: n+1
                idx, <ArrayList<Integer>>
                0: 안씀
                1, [2,3,4]           
                2, []
                3, [2]
                4, [5]
                
                이런식으로 만들기
                돌면서 BFS
                
                */
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                StringTokenizer st = new StringTokenizer(br.readLine());
                int N = Integer.parseInt(st.nextToken()); //노드 개수
                int M = Integer.parseInt(st.nextToken()); //엣지 개수
                Graph graph = new Graph(N, M);
                
                for(int i = 0; i<M; i++){
                    st = new StringTokenizer(br.readLine());
                    int index = Integer.parseInt(st.nextToken());
                    graph.addEdge(index, Integer.parseInt(st.nextToken()));
                }
                int counter = 0;
                for(int i = 1; i<N+1; i++){
                    if(graph.check(i)){
                        continue;
                    }
                    graph.BFS(i);
                    counter += 1;
                }
                System.out.println(counter);
    // ========================================================Write Here========================================================            
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();            
            }

        }
}
class Graph{
    private ArrayList<ArrayList<Integer>> mygraph;
    private int maxEdge;
    private ArrayList<Boolean> visited;
    
    Graph(int N, int M){        
        mygraph = new ArrayList<ArrayList<Integer>>(N+1);
        visited = new ArrayList<Boolean>(N+1);
        for(int i = 0; i<N+1; i++){
            visited.add(false);
            mygraph.add(new ArrayList<Integer>());
        }        
    }
    
    void addEdge(int index, int value){
        mygraph.get(index).add(value);
        mygraph.get(value).add(index);        
    }
    
    void printGraph(){
        Iterator it = mygraph.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }        
    }
    boolean check(int index){
        return visited.get(index);
    }
    void BFS(int start){
        LinkedList<Integer> queue = new LinkedList<>();
        Iterator<Integer> it = mygraph.get(start).iterator();
        while(it.hasNext()){
            queue.add(it.next());
        }
        while(!queue.isEmpty()){
            int out = queue.poll();
            if(visited.get(out)){
                continue;
            }
            visited.set(out,true);
            it = mygraph.get(out).iterator();
            while(it.hasNext()){
                queue.add(it.next());
            }
        }
    }
}
