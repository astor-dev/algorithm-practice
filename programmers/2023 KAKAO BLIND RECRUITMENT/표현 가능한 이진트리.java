import java.util.*;

class Solution {
    /**
    트리는 함정
    트리의 노드 수에 해당하는 비트로 표현 가능한가?
    1. x                    1(2^1 -1) 2
    2. x1x                  7(2^3-1)  4
    3. x1x1x1x              127(2^7-1) 8
    4. x1x1x1x1x1x1x1x      2^15 - 1  16
     부모가 1이여야함
    */
    public int[] solution(long[] numbers) {
        StringBuilder sb;
        int[] answer = new int[numbers.length];
        for(int i = 0; i < numbers.length; i++) {
            String binaryString = Long.toBinaryString(numbers[i]);
            int level = 1;
            int binaryLength = 1;
            while(binaryLength < binaryString.length()) {
                level++;
                binaryLength = (int)Math.pow(2, level) - 1;
            }
            sb = new StringBuilder();
            for(int j = 0; j < binaryLength - binaryString.length(); j++) {
                sb.append(0);
            }
            String treeString = sb.append(binaryString).toString();
            int mid = (treeString.length()-1)/2;
            boolean isTree = checkIsTree(treeString.charAt(mid), treeString, 0, treeString.length()-1);
            if(isTree) {
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }

        }
        return answer;
    }

    private boolean checkIsTree(char parent, String tree, int start, int end) {
        int mid = start + (end-start)/2;
        boolean isThisNodeTree = !(parent == '0' && tree.charAt(mid) == '1');
        // node에 도달 함
        if(start == end) {
            return isThisNodeTree;
        }

        return checkIsTree(tree.charAt(mid), tree, start, mid-1) && checkIsTree(tree.charAt(mid), tree, mid+1, end) && isThisNodeTree;
    }
}