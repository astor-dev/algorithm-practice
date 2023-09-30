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
            
            Stack<Double> operandStack = new Stack<>();
            HashMap<Character, Double> operandMapper = new HashMap<>();
            
            int n = Integer.parseInt(br.readLine());            
            char[] expression = br.readLine().toCharArray();                        
            
            for(int i = 0; i<n; i++){
                operandMapper.put((char)(65+i),Double.valueOf(br.readLine()));
            }
            
            for(int i = 0; i<expression.length; i++){
                char now = expression[i];
                if(Character.toString(now).matches("[A-Za-z]")){
                    operandStack.push(operandMapper.get(now));
                    continue;
                }
                double first = operandStack.pop();
                double second = operandStack.pop();
                double result = 0;
                switch(now){
                    case '+':                        
                        result = (second + first);
                        break;
                    case '-':
                        result = (second - first);
                        break;
                    case '*':
                        result = (second * first);
                        break;
                    case '/':
                        result = (second / first);
                        break;                        
                }                
                operandStack.push(result);
            }
            System.out.printf("%.2f", operandStack.pop());
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }
//     static methods        
//     ==============
}    
