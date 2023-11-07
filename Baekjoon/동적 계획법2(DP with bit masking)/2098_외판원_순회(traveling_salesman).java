import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main{
    static int N;
    static final int INF = 160_000_000;
    static int[][] W, dp;
    
    public static void main(String[] args) throws Exception {
        try{                    
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;                              
                
            N = Integer.parseInt(br.readLine());
            W = new int[N][N];
            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++)
                    W[i][j] = Integer.parseInt(st.nextToken());
            }
            //(10000) - (00001) = (01111)
            dp = new int[N][(1<<N)-1];
            for(int i = 0; i < N; i++)
                Arrays.fill(dp[i], -1);            
            System.out.println(dfs(0,1));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }
    }
    static int dfs(int now, int visit){        
        //모든 도시를 지난 경우(1111....1)
        if(visit == (1<<N)-1){
//             0(출발도시)로 가는 경로 있을 경우 해당 값을 리턴. 없으면 INF.
            if(W[now][0] == 0) return INF;
            return W[now][0];
        }
//         값이 dp에 들어있으면 그 값을 리턴
        if(dp[now][visit] != -1) return dp[now][visit];
        
//         값 없으면 값 찾기
        dp[now][visit] = INF;
        for(int i = 0; i<N; i++){
//             (visit&(1<<i)) 했을 때 visit의 i번째 값이 0이면 (0&1)==0, 1이면 (1&1)==1
//             즉, 첫번째 조건문의 의미는 '만약 i번째 노드를 방문하지 않았을 경우', 그리고 경로가 있는 경우
            if( (visit & (1<<i)) == 0 && W[now][i] != 0 ){
                //안 방문한 i로 넘어가서(이동비용 소요) 남은 노드들을 방문하는 비용 < 현재 노드에서 최솟값 일경우 갱신
                dp[now][visit] = Math.min(dfs(i, visit | (1<<i)) + W[now][i], dp[now][visit]);
            }
        }
        return dp[now][visit];
        
    }
}
