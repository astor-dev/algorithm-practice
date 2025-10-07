import java.util.*;
import java.time.*;
import java.time.format.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answerList = new ArrayList<>();
        Map<String, Integer> termsMap = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate now = LocalDate.parse(today, formatter);
        for (String term : terms) {
            String[] splitTerm = term.split(" ");
            termsMap.put(splitTerm[0], Integer.parseInt(splitTerm[1]));
        }
        int index = 1;
        for(String privacy: privacies) {
            String[] splitPrivacy = privacy.split(" ");
            int month = termsMap.get(splitPrivacy[1]);
            LocalDate exp = LocalDate.parse(splitPrivacy[0], formatter);
            if(exp.plusMonths(month).isBefore(now) || exp.plusMonths(month).isEqual(now)) {
                answerList.add(index);
            }
            index++;
        }

        int[] answer = answerList.stream().mapToInt(i -> i).toArray();
        return answer;
    }
}