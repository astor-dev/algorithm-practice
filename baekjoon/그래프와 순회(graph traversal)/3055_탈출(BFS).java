		import java.io.*;
		import java.util.*;
		
		
		public class Main {
			public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			static int[][] move = {{1,0},{-1,0},{0,1},{0,-1}};
			
			static class Node implements Comparable<Node>{
				int x;
				int y;
				boolean isWater;
				int time;
				
				Node(int x, int y, boolean isWater, int time) {
					this.x = x;
					this.y = y;
					this.isWater = isWater;
					this.time = time;
				}
				
				@Override
				public int compareTo(Node target) {
					if(this.time == target.time) return this.isWater ? -1 : 1; 
					return this.time - target.time;
				}
				
			}
			
			public static void main(String[] args) throws IOException {
				StringTokenizer st = new StringTokenizer(br.readLine());
	
				int R = Integer.parseInt(st.nextToken()), C = Integer.parseInt(st.nextToken());
					char[][] graph = new char[R][C];
					boolean[][] sVisited = new boolean[R][C];

					PriorityQueue<Node> queue = new PriorityQueue<Node>();
					for(int i = 0; i < R; i++) {
						String row = br.readLine();
						for(int j = 0; j < C; j++) {
							char node = row.charAt(j);
							graph[i][j] = node;
							if(node == 'S') {								
								queue.add(new Node(i,j, false, 0));
							}
							if(node == '*') {
								queue.add(new Node(i,j, true, 0));
							}
						}
				}
	
					
				while(!queue.isEmpty()) {
					Node node  = queue.poll();
					for (int[] d : move) {
						int dx = node.x + d[0];
						int dy = node.y + d[1];
						if (dx < 0 || dx >= R || dy < 0 || dy >= C) continue;
						if(node.isWater) {							
							if(graph[dx][dy] == '.') { 
								graph[dx][dy] = '*';
								queue.add(new Node(dx,dy, true, node.time+1));
							}
						} else {
							// 비버의 경로인 경우
							if(sVisited[dx][dy]) continue;
							if(graph[dx][dy] == '.') {
								queue.add(new Node(dx,dy, false, node.time+1));
								sVisited[dx][dy] = true; 
							} else if (graph[dx][dy] == 'D') {
								System.out.print(node.time+1);

								return;
							}
						}
					}
					
				}
				System.out.print("KAKTUS");											
			}
		}
