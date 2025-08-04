    import java.io.*;
    import java.util.*;
    import java.util.stream.*;

    public class Main{ 
        public static void main(String[] args) throws Exception {
            try{   
    // ========================================================Write Here========================================================                       
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                
                /*
                dpTable을 만드는데 Rtable Gtable Btable 3개를 만듦
                Rtable은 마지막 색칠한 값이 R
                0: 38 79 21
                
                1: 56 12 97
                Rtable= G랑B중 제일 작은거
                Gtable= R이랑B중 제일 작은거
                Btable= R이랑G중 제일 작은거
                2: 75 21 56
                */
                int N = Integer.parseInt(br.readLine());
                int[] rDPT = new int[N+1];
                int[] gDPT = new int[N+1];
                int[] bDPT = new int[N+1];
                
                rDPT[0] = 0;
                gDPT[0] = 0;
                bDPT[0] = 0;
                for(int i = 1; i<N+1; i++){
                    StringTokenizer st = new StringTokenizer(br.readLine());
                    int R = Integer.parseInt(st.nextToken());                    
                    int G = Integer.parseInt(st.nextToken());
                    int B = Integer.parseInt(st.nextToken());
                    
                    rDPT[i] = Math.min(gDPT[i-1], bDPT[i-1]) + R;
                    gDPT[i] = Math.min(rDPT[i-1], bDPT[i-1]) + G;
                    bDPT[i] = Math.min(rDPT[i-1], gDPT[i-1]) + B;

                    
                }
                int result = Math.min(gDPT[N], bDPT[N]);
                result = Math.min(rDPT[N], result);
                System.out.println(result);
    // ========================================================Write Here========================================================            
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();            
            }

        }
}
