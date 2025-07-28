		import java.io.*;
		import java.util.*;
		
		
		public class Main {
			public static int answer = 0;
			public static int n;
			public static int[] columnPosition;
			public static void main(String[] args) throws IOException {
				n = Integer.parseInt((new BufferedReader(new InputStreamReader(System.in))).readLine());
				columnPosition = new int[n];
				scan(0);
				System.out.print(answer);
				
				
			}
			public static void scan(int row) {
				if (row == n) {
					answer++;
					return;
				}
					
				for (int i = 0; i < n; i++) {
					if(isSafe(row, i)) {
						columnPosition[row] = i;
						scan(row+1);
					}
					
						
				}
				
			}
			
			static boolean isSafe(int row, int col) {
		        for (int searchingRow = 0; searchingRow < row; searchingRow++) {
		            if (columnPosition[searchingRow] == col || Math.abs(columnPosition[searchingRow] - col) == Math.abs(searchingRow  - row)) {
		                return false; // 충돌 발생
		            }
		        }
		        return true; // 충돌 없음
			}
		}
