		import java.io.*;
		import java.util.*;
		
		
		public class Main {
			public static String ERROR = "ERROR";
			public static int MAX_VALUE = (int) Math.pow(10, 9);
			public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
			public static class Program {
				
				List<String> operations;
				
		
				
				public boolean init() throws IOException {
					this.operations = new ArrayList<String>();
					String operation = br.readLine();
		
					if(operation.equals("QUIT")) {
						return false;
					}
					while (!operation.equals("END")) {			
						this.operations.add(operation);
		
						operation = br.readLine();
					}			
					return true;
				}
				
				public String process(int input) {
					ArrayDeque<Integer> goStack = new ArrayDeque<Integer>();
					goStack.push(input);
					StringTokenizer st;
					for (String operationString: this.operations) {
						st = new StringTokenizer(operationString);
						String operation = st.nextToken();
						try {
							switch(operation) {
			//				NUM X: X를 스택의 가장 위에 저장한다. (0 ≤ X ≤ 109)
								case "NUM":
									int X = Integer.parseInt(st.nextToken());
									goStack.push(X);
									break;
			//				POP: 스택 가장 위의 숫자를 제거한다.
								case "POP":
						            if(goStack.size()==0) throw new Exception();
									goStack.pop();
									break;
			//				INV: 첫 번째 수의 부호를 바꾼다. (42 -> -42)
								case "INV":
						            if(goStack.size()==0) throw new Exception();
									goStack.push(Math.negateExact(goStack.pop()));
									break;
			//				DUP: 첫 번째 숫자를 하나 더 스택의 가장 위에 저장한다.
								case "DUP":
						            if(goStack.size()==0) throw new Exception();
									goStack.push(goStack.peek());
									break;
			//				SWP: 첫 번째 숫자와 두 번째 숫자의 위치를 서로 바꾼다.
								case "SWP":
						            if(goStack.size() < 2) throw new Exception();
									int first = goStack.pop();
									int second = goStack.pop();
									goStack.push(first);
									goStack.push(second);
									break;
			//				ADD: 첫 번째 숫자와 두 번째 숫자를 더한다.
								case "ADD":
						            if(goStack.size() < 2) throw new Exception();
									first = goStack.pop();
									second = goStack.pop();
									int result = first + second;
									if (result < -MAX_VALUE || result > MAX_VALUE) throw new Exception();
									goStack.push(result);
									break;
			//				SUB: 첫 번째 숫자와 두 번째 숫자를 뺀다. (두 번째 - 첫 번째)
								case "SUB":
						            if(goStack.size() < 2) throw new Exception();
									first = goStack.pop();
									second = goStack.pop();
									result = second - first;
									if (result < -MAX_VALUE || result > MAX_VALUE) throw new Exception();
									goStack.push(result);
									break;
			//				MUL: 첫 번째 숫자와 두 번째 숫자를 곱한다.
								case "MUL":
						            if(goStack.size() < 2) throw new Exception();
									first = goStack.pop();
									second = goStack.pop();
									long longResult = (long) first * second;
									if (longResult < -MAX_VALUE || longResult > MAX_VALUE) throw new Exception();
									goStack.push((int) longResult);
									break;
			//				DIV: 첫 번째 숫자로 두 번째 숫자를 나눈 몫을 저장한다. 두 번째 숫자가 피제수, 첫 번째 숫자가 제수이다.
								case "DIV":
						            if(goStack.size() < 2) throw new Exception();
									first = goStack.pop();
									second = goStack.pop();
									if (first == 0) return ERROR;
								    result = Math.abs(second) / Math.abs(first);
								    if ((second < 0 && first > 0) || (second > 0 && first < 0)) {
								    	result = -result;
								    }
								    if (result < -MAX_VALUE || result > MAX_VALUE) throw new Exception();
									goStack.push(result);
									break;
			//				MOD: 첫 번째 숫자로 두 번째 숫자를 나눈 나머지를 저장한다. 두 번째 숫자가 피제수, 첫 번째 숫자가 제수이다.
								case "MOD":
						            if(goStack.size() < 2) throw new Exception();
									first = goStack.pop();
									second = goStack.pop();
									if (first == 0) return ERROR;
									result = Math.abs(second) % Math.abs(first);
								    if (second < 0) {
								    	result = -result;
								    }
								    if (result < -MAX_VALUE || result > MAX_VALUE) throw new Exception();
									goStack.push(result);
									break;
							}
						} catch (Exception e) {
							return ERROR;
						}
					}		
					if(goStack.size() != 1) return ERROR;
					return String.valueOf(goStack.pop());
		
				}
			}
		
			
			public static void main(String[] args) throws IOException {
				Program program = new Program();
				StringBuilder sb = new StringBuilder();
		
				while(program.init()) {
					int N = Integer.parseInt(br.readLine());
					for (int i = 0; i < N; i++) {
						int input = Integer.parseInt(br.readLine());
						String result = program.process(input);
						sb.append(result).append("\n");
					}
					br.readLine();
					sb.append("\n");
				}
				System.out.print(sb);
			}
		}
