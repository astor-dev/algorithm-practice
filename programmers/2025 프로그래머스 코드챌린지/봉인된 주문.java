import java.util.*;

class Solution {
    /*
    [문제 요약]
    # 조건
    알파벳 소문자 11글자 이하 모든 문자열
    1. 글자 수가 적은 주문부터 먼저 기록된다.
    2. 글자 수가 같다면, 사전 순서대로 기록된다.
    몇몇 주문을 삭제했음
    # 목표
    삭제 완료된 주문서의 n번째 주문 삭제
    */

    /*
    [풀이]
    주문이라는 개념 자체는 입력과 무관하게 fixed
    이에 대한 경우의 수를 측정하는 것은 무의미
    탐색임.
    bans길이가 짧음 -> bans에 해당하는 문자열 각각 몇번 째인지 구함
    (n+n까지 벤 된 알파벳) 번째 주문 찾음

    주문을 어떻게 찾는가.
    a-z = abcdefghijklmnopqrstuvwxyz = 26개
    26
    a-za-z
    26 * 26
    a-za-za-z
    26 * 26 * 26
    몇자리인지? n/26 + 1
    */
    static final int ALPHABET_COUNT = 'z' - 'a' + 1;
    public String solution(long n, String[] bans) {
        Arrays.sort(bans, (a, b) -> {
            if (a.length() == b.length()) {
                return a.compareTo(b);
            }
            return a.length() - b.length();
        });
        for(String ban : bans) {
            if(getIndex(ban) <= n) {
                n++;
            }
        }
        return getString(n);
    }

    String getString(long index) {
        // 특정 digit의 알파벳 구하는 법
        // 역순으로 돌면서 빼기
        StringBuilder sb = new StringBuilder();

        while(index > 0) {
            index--;
            sb.insert(0, (char) ('a' + index % 26));
            index /= 26;
        }
        return sb.toString();
    }

    long getIndex(String string) {
        long answer = 0;
        for(int i = 0; i < string.length(); i++) {
            char alphabet = string.charAt(i);
            int priceValue = alphabet - 'a' + 1;
            // 26 * 1
            answer +=  Math.pow(ALPHABET_COUNT, string.length() - i -1)  * priceValue;
        }
        return answer;
    }
}