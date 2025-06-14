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
                StringTokenizer st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());
                int m = Integer.parseInt(st.nextToken());
//                 그래프
                ArrayList<int[]> graph = new ArrayList<>();
//                 방문확인
                ArrayList<boolean[]> visited = new ArrayList<>();                
//                 값저장
                ArrayList<int[]> values = new ArrayList<>();
//                 시작 값
                int[] start = new int[2];
//                 입력받기
                for(int i = 0; i<n; i++){
                    int[] tempar = new int[m];
//                     visited 기초값
                    boolean[] falses = new boolean[m];
                    Arrays.fill(falses, false);
//                     zeroes 기초값
                    int[] zeroes = new int[m];
                    Arrays.fill(zeroes, -1);
                    
                    st = new StringTokenizer(br.readLine());
                    for(int j = 0; j<m; j++){
                        int inputData = Integer.parseInt(st.nextToken());
                        if(inputData == 0){
                            zeroes[j] = 0;
                        } else if(inputData == 2){
                            start = new int[]{i,j};                            
                            zeroes[j] = 0;
                        }
                        tempar[j] = inputData;
                    }                    
                    graph.add(tempar);
                    visited.add(falses);
                    values.add(zeroes);
                }
                
//                 상하좌우 [세로, 가로]
                ArrayList<int[]> direction = new ArrayList<>();
                direction.add(new int[]{1,0});
                direction.add(new int[]{-1,0});                
                direction.add(new int[]{0,1});
                direction.add(new int[]{0,-1});
                
                LinkedList<int[]> queue = new LinkedList<>();
                queue.add(start);
//                 시작점 방문처리
                boolean[] temp = visited.get(start[0]);
                temp[start[1]] = true;
                visited.set(start[0], temp); 
                
                        
                
                while (!queue.isEmpty()){
                    int[] coordinate = queue.poll();
                    int y = coordinate[0];
                    int x = coordinate[1];
                    
                    for(int[] dxy : direction){
                        int dy =  dxy[0] + coordinate[0];
                        int dx =  dxy[1] + coordinate[1];                        
                        if(dy >= 0 && dy < n && dx >= 0 && dx < m){
                            if(visited.get(dy)[dx]){
                                continue;
                            }
                            if(graph.get(dy)[dx] == 0){
                                boolean[] tv = visited.get(dy);
                                tv[dx] = true;
                                visited.set(dy, tv);
                                continue;
                            }                            
//                             방문처리
                            boolean[] tempVisited = visited.get(dy);
                            tempVisited[dx] = true;
                            visited.set(dy, tempVisited);
                            
//                             벨류 값 증가
                            int[] tempValues = values.get(dy);
                            tempValues[dx] = values.get(y)[x] +1;
                            values.set(dy, tempValues);
                            
//                             큐에 넣기
                            queue.add(new int[]{dy, dx});                            
                        }
                    }
                }
                
//                 출력
                Iterator<int[]> it = values.iterator();
                while(it.hasNext()){
                    int[] arr = it.next();
                    String str = Arrays.toString(arr).replaceAll("[\\[\\],]", "");                    
                    System.out.println(str);
                }
    // ========================================================Write Here========================================================            
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();            
            }

        }
    }
