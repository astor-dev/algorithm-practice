import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main{ 
        
    public static void main(String[] args) throws Exception {

        try{   
/*
int T 입력
int k 입력
for (k){
stringtokenizer
tempOp = nextToken
tempInt = nextToken
DPQ.operate(Op, Int);
}


class DPQ{

상태: TreeSet

역할: 오퍼레이션에 따라 입력을 받거나 값을 제거함
메서드1: operate{
OP의 값에 따라 메서드 실행시킴
}

private 메서드 addSth(int)
자료형에 더하기

private 메서드 remove(int){
if 길이 == 0
return;

if int =1 
최대값 제거
if int = -1
최소값 제거
}

역할2: 자신이 가진 최댓값 최솟값 출력
메서드2 String printSelf
if 길이 == 0:
return "EMPTY"
return 최댓값 + " " + 최솟값
p
*/
// ========================================================Write Here========================================================            
            //입력
         
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int T = Integer.valueOf(br.readLine());
            for(int i = 0; i<T; i++){
                int k = Integer.valueOf(br.readLine());
                DPQ dpq = new DPQ();
                
                for(int j = 0; j<k; j++){
                    StringTokenizer st = new StringTokenizer(br.readLine());
                    String tempOp = st.nextToken();
                    int tempInt = Integer.valueOf(st.nextToken());
                    dpq.operate(tempOp, tempInt);                                                                            
                    
                }
                dpq.printSelf();
            }
            

            


            
            
// ========================================================Write Here========================================================            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }
}

class DPQ{
    TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
    
    public void operate(String op, int n){

        
        if(op.equals("I")){
            addSth(n);
            return;
        }
        if(op.equals("D")){
            remove(n);
        }
    }
    public void printSelf(){
        if(map.isEmpty()){
            System.out.println("EMPTY");
            return;
        }
        System.out.println(map.lastKey() + " " + map.firstKey());
    }
    private void addSth(int n){        
        if (!map.containsKey(n)){
            map.put(n,1);
            return;
        }
        map.put(n,map.get(n) + 1);
    }
    
    private void remove(int n){
        if(map.isEmpty()){
            return;
        }
        if(n == 1){
            Integer val = map.get(map.lastKey());
            if(val == 1){
                map.remove(map.lastKey());
                return;
            }
            map.put(map.lastKey(), val -1);
            return;
        }
        if(n == -1){
            Integer val = map.get(map.firstKey());
            if(val == 1){
                map.remove(map.firstKey());
                return;
            }
            map.put(map.firstKey(), val -1);
            return;
        }
    }
}
