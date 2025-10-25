import java.util.*;

class Solution {

    class PersonalityType implements Comparable<PersonalityType> {
        int value;
        char name;
        PersonalityType(char name) {
            this.value = 0;
            this.name = name;
        }

        @Override
        public int compareTo(PersonalityType target) {
            if(this.value == target.value)
                return target.name - this.name;
            return this.value - target.value;
        }
    }

    class Personality {
        PersonalityType type1;
        PersonalityType type2;
        Personality(char type1, char type2) {
            this.type1 = new PersonalityType(type1);
            this.type2 = new PersonalityType(type2);
        }

        public char getHigherType(){
            if(type1.compareTo(type2) > 0) {
                return type1.name;
            } else {
                return type2.name;
            }
        }

        public void addValue(String survey, int choice) {
            PersonalityType formerType;
            PersonalityType latterType;
            if(survey.charAt(0) == type1.name) {
                formerType = type1;
                latterType = type2;
            } else {
                formerType = type2;
                latterType = type1;
            }
            if(choice <= 3) {
                formerType.value += 4 - choice;
            }
            if(choice >= 5) {
                latterType.value += choice - 4;
            }

        }
    }

    public String solution(String[] survey, int[] choices) {

        Personality rt = new Personality('R', 'T');
        Personality fc = new Personality('F', 'C');
        Personality mj = new Personality('M', 'J');
        Personality na = new Personality('N', 'A');
        Map<String, Personality> personalityMap = Map.of(
                "RT", rt,
                "TR", rt,
                "FC", fc,
                "CF", fc,
                "MJ", mj,
                "JM", mj,
                "NA", na,
                "AN", na
        );

        for(int i = 0; i < survey.length; i++) {
            Personality personality = personalityMap.get(survey[i]);
            personality.addValue(survey[i], choices[i]);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(rt.getHigherType());
        sb.append(fc.getHigherType());
        sb.append(mj.getHigherType());
        sb.append(na.getHigherType());
        return sb.toString();
    }
}