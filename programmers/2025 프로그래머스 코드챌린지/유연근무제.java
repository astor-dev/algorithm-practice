import java.time.*;
import java.time.format.*;

class Solution {
    class Employee {
        LocalTime workTime;
        private LocalTime parseTime(int time) {
            int hour = time / 100;
            int minute = time % 100;
            return LocalTime.of(hour, minute);
        }

        Employee(int scheduleTime) {
            this.workTime = parseTime(scheduleTime).plusMinutes(10);
        }

        private boolean isOnTime(int logTime) {
            LocalTime logLocalTime = parseTime(logTime);
            return !logLocalTime.isAfter(workTime);
        }
    }

    class Day {
        int value;
        Day(int today) {
            this.value = today;
        }
        void nextDay() {
            value++;
            if(value > 7) value = 1;
        }

        boolean isWeekend() {
            return value == 6 || value == 7;
        }
    }
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = schedules.length;
        for(int i = 0; i < schedules.length; i++) {
            Employee employee = new Employee(schedules[i]);
            Day day = new Day(startday);
            for(int time: timelogs[i]) {
                if(!employee.isOnTime(time) && !day.isWeekend()) {
                    answer--;
                    break;
                }
                day.nextDay();
            }
        }
        return answer;
    }
}