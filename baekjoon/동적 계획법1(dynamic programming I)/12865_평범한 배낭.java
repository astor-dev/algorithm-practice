import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        try{
            
// ========================================================Write Here========================================================            
            boolean test = false;

            InputReader io = (test) ? new testInputReader() : new submitInputReader();
            String[] tempio = io.readLine().split(" ");
            int N = Integer.valueOf(tempio[0]);
            int K = Integer.valueOf(tempio[1]);
            ArrayList<Integer>[] arr = new ArrayList[2];
            arr[0] = new ArrayList<Integer>();
            arr[1] = new ArrayList<Integer>();
            
            for(int i = 0; i<N; i++){
                tempio = io.readLine().split(" ");
                int W = Integer.valueOf(tempio[0]);
                int V = Integer.valueOf(tempio[1]);
                arr[0].add(W);
                arr[1].add(V);
            }
            int[][] dpT = new int[N][K+1];
            
            for(int i = 0; i<N; i++){
                int tempW = arr[0].get(i);
                int tempV = arr[1].get(i); 
                int[] tempdpT = dpT[i].clone();         
                for(int j = 0; j<K+1; j++){
                    if(j+tempW < K+1){                        
                        tempdpT[j + tempW] = Math.max(dpT[i][j] + tempV, dpT[i][j+tempW]);
                    }
                }
                dpT[i] = tempdpT;
                //System.out.println(Arrays.toString(dpT[i]));
                
                if (!(i+1==N)){
                    dpT[i+1] = dpT[i];                        
                }
            }
            /*for (int i = 0; i<dpT[N-1].length; i++){
                System.out.println(dpT[N-1][i]);
            }*/
            int answer = 0;
            for(int i = 0; i< K+1; i++){
                answer = Math.max(dpT[N-1][i], answer);
            }
            System.out.println(answer);
            
                //while문으로 temparr이 빌때까지 순회하면서 dpT mathmax로 갱신
            
            //정렬후 최대값 출력
            
            
// ========================================================Write Here========================================================            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }
}

// ========================================================InputReader========================================================
abstract class InputReader{

    abstract protected String readLine();
}

class testInputReader extends InputReader{
    private Scanner sc = new Scanner(System.in);
    protected String readLine(){
        return sc.nextLine();
    }
}

class submitInputReader extends InputReader {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    /*private StringTokenizer st;
    {
        try{
            st = new StringTokenizer(br.readLine()); 
        } catch(Exception e) {}
    }*/
    protected String readLine(){        
        try{return br.readLine();} catch(Exception e) {} ;
        return "";
        //return st.nextToken();
    }
}
// ========================================================InputReader========================================================
