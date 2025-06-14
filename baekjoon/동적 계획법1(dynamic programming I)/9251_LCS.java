import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        try{
            boolean test = false;

            InputReader io = (test) ? new testInputReader() : new submitInputReader();
            String s1 = io.readLine();
            String s2 = io.readLine();
            int[][] dpT = new int[s1.length()+1][s2.length()+1];



            for(int i = 0; i < s1.length(); i++){
                for(int j = 0; j < s2.length(); j++){
                    if(s1.charAt(i) == s2.charAt(j)){
                        dpT[i+1][j+1] = dpT[i][j] + 1;                    
                    } else{
                        dpT[i+1][j+1] = Math.max(dpT[i][j+1], dpT[i+1][j]);
                    }
                }
            }
            System.out.println(dpT[s1.length()][s2.length()]);

        } catch (Exception e) {}

    }
}

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
