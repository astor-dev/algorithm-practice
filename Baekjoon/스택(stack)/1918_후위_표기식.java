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
            
            Stack<Character> operationStack = new Stack<>();
            HashMap<Character, Integer> priorityMapper = new HashMap<>();
            priorityMapper.put('(', 0);
            priorityMapper.put('+', 1);
            priorityMapper.put('-', 1);
            priorityMapper.put('*', 2);
            priorityMapper.put('/', 2);
                            
            char[] expression = br.readLine().toCharArray();                        
            
            for(int i = 0; i<expression.length; i++){                
                char now = expression[i];
                if(!Character.toString(now).matches("[\\(\\*\\-\\+\\/)]")){
                    bw.write(now);
                    continue;
                }
                if(now == '('){
                    operationStack.push(now);
                    continue;                        
                }
                if(now == ')'){
                    while(!(operationStack.peek()=='(')){
                        bw.write(operationStack.pop());
                    }
                    operationStack.pop();
                    continue;
                }
                while(!operationStack.isEmpty() &&                    
                      priorityMapper.get(operationStack.peek()) >= priorityMapper.get(now)){                    
                    bw.write(operationStack.pop());
                }                
                operationStack.push(now);
            }
//             남은거 다 털기
            while(!operationStack.isEmpty()){
                bw.write(operationStack.pop());
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
