import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        int number = Integer.parseInt(bufferedReader.readLine());
        for (int i = 2; i*i <= number; i++) {
            while(number % i == 0) {
                stringBuilder.append(i).append("\n");
                number = number / i;
            }
        }
        //  맨 마지막 소인수 추가 (ex: 10 / 2 하면 5 남는데 얘는 루프 안걸리는 소인수)
        if(number > 1) stringBuilder.append(number).append("\n");
        System.out.println(stringBuilder);
    }
}