import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main{

//     static variables
    
//     ================
    public static void main(String[] args) throws Exception {
        try{                    
            // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            // StringTokenizer st;
        
            // st = new StringTokenizer(br.readLine());
            // int n = Integer.parseInt(st.nextToken());
            // int m = Integer.parseInt(st.nextToken());            
            FriendNetwork friendNetwork = new FriendNetwork();
            friendNetwork.getTestCase();
            friendNetwork.loop();
            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();            
        }

    }
//     static methods        
//     ==============
}    

class FriendNetwork{
    private HashMap<String, Integer> IntegerMapper;
    private ArrayList<Integer> network;
    private ArrayList<Integer> connection;
    private int counter = 0;
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    int nOfT;
    
    public void getTestCase(){
        try{
        nOfT = Integer.parseInt(br.readLine());
        } catch(IOException e){}
    }
    public void loop(){
        try{
        for(int i = 0; i<nOfT; i++){
            counter = 0;
            int nOfF= Integer.parseInt(br.readLine());
            network = new ArrayList<>();
            connection =  new ArrayList<>();
            IntegerMapper = new HashMap<>();
            
            for(int j = 0; j<nOfF; j++){
                st = new StringTokenizer(br.readLine());
                String first = st.nextToken();
                String second = st.nextToken();

                if(IntegerMapper.putIfAbsent(first, counter) == null){
                    connection.add(1);                    
                    network.add(counter++);                    
                }                
                if(IntegerMapper.putIfAbsent(second, counter) == null){
                    connection.add(1);
                    network.add(counter++);
                }
                Integer firstInt = IntegerMapper.get(first);
                Integer secondInt = IntegerMapper.get(second); 
                union(firstInt, secondInt);
            }            
        }
        } catch(IOException e){}

    }
    public void union(Integer x, Integer y){
        x = find(x);
        y = find(y);
        
        
        if(x == y){
            System.out.println(connection.get(x));
            return;
        }
        int connections = connection.get(x) + connection.get(y);
        connection.set(x, connections);
        connection.set(y, connections);
        System.out.println(connections);        
        if(x <= y){
            network.set(y,x);

            return;
        }
        network.set(x,y);
    }
    
    public Integer find(Integer x){
        if(x == network.get(x)){
            return x;
        }
        
        return find(network.get(x));
    }    
}
