import java.io.*;
import java.util.*;


public class Main {

    static int n ;
    static int m;
    static int k;
    static int l;
    static long[][] pascal;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        k = Integer.parseInt(stringTokenizer.nextToken());
        l = n+m;
       pascal = new long[l+1][l+1];
        for (int i = 0; i<=l; i++) {
            pascal[i][0] = 1;
            pascal[i][i] = 1;
        }
        for (int i = 1; i <= l; i++) {
            for (int j = 1; j <= l; j++) {
                pascal[i][j] = pascal[i-1][j-1] + pascal[i-1][j];
                if(pascal[i][j] > k) pascal[i][j] = k+1;
            }
        }
        System.out.println(find(l,m, 0));



    }

    static String find (int l, int m, int index) {
        // 알파벳 순서대로 n+m = l 길이 문자열을 만들면 lCn 개의 문자열 존재.
        // 순열의 규칙? 사전순인 경우. l=6 일때 6C3 = 20
        // aaazzz (2C2) (6-2 = 4번째 자리수까지 고정) (z는 마지막 문자열
        // aazazz aazzaz aazzza (3C2) (6-3 = 3번째 자리수 까지 고정)
        // azaazz azazaz azazza azzaaz azzaza azzzaa (4C2)
        //  zzzaaa 1까지 고정 // 5C2
        StringBuilder stringBuilder = new StringBuilder();
//        System.out.println(l + "C" + m + " = " + pascal[l][m]);
//        System.out.println("index: " + index + ", processing: " + pascal[l][m] + ", k:" + k);
        if(index + pascal[l][m] < k) return "-1";
        if(l==1) {
            if(m==1) return "z";
            return "a";
        }
        for(int i = m-1; i < l; i++) {
        // 현재 인덱스에서 파스칼 값을 더했을 때 k와 같거나 크면 k 구간 안에 있는거임
            if(m==0) {
                for(int aCount = 0; aCount < l; aCount++) stringBuilder.append('a');
                return stringBuilder.toString();
            }
            if (index + pascal[i][m-1] >= k) {
                for(int preACount = 0; preACount < l-i-1; preACount++) stringBuilder.append('a');
                stringBuilder.append('z');
                if(stringBuilder.length() == l) {
                    return stringBuilder.toString();
                }
                else {
                    return stringBuilder.append(find(i, m - 1, index)).toString();
                }
            }
            // 구간에 없으면 인덱스 옮김 0 1 4 10 20
            index += pascal[i][m-1];
        }
        return "-1";
    }
}
