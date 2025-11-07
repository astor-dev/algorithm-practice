    import java.io.BufferedReader;
    import java.io.InputStreamReader;
    import java.util.*;


    public class Main {
        static int L;
        static int C;
        static int[] pointers;
        static int pointerCursor;
        static ArrayList<Character> chars = new ArrayList<>();
        static boolean flag = true;

        public static void main(String[] args) throws Exception {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            L = Integer.parseInt(stringTokenizer.nextToken());
            C = Integer.parseInt(stringTokenizer.nextToken());
            String input = bufferedReader.readLine();
            for (int i = 0; i < input.length(); i += 2){
                chars.add(input.charAt(i));
            }
            Collections.sort(chars);
            pointers = new int[L];
            for (int i = 0; i < L; i++) pointers[i] = i;
            pointerCursor = pointers.length - 1;
            StringBuilder stringBuilder =new StringBuilder();
            while(flag) {
                char[] password = new char[L];
                int vowelCounter = 0;
                int consonentCounter = 0;
                for (int i = 0; i < L; i++) {
                    password[i] = chars.get(pointers[i]);
                    if (password[i] == 'a' || password[i] == 'e' || password[i] == 'i' || password[i] == 'o' || password[i] == 'u') {
                        vowelCounter++;
                    } else {
                        consonentCounter++;
                    }
                }
                if(vowelCounter >= 1 && consonentCounter >= 2)
                    stringBuilder.append(String.valueOf(password)).append("\n");
                movePointer();
            }
            System.out.print(stringBuilder);
        }

        static void movePointer() {
            // 첫번째 포인터이고 최대치에 도달한 경우 처리
            if(pointerCursor == 0 && (pointers[pointerCursor+1] == pointers[pointerCursor] +1)) {
                flag = false;
                return;
            }

            if(!shouldMoveCursor()) {
                pointers[pointerCursor]++;
                return;
            }

            while(true) {
                pointerCursor--;

                // 커서를 움직인 뒤 다시 움직일 필요가 없으면
                // 포인터를 1 더하고 난 뒤 뒷 값을 밀고 커서를 다시 맨뒤로 움직임
                if(!shouldMoveCursor()) {
                    pointers[pointerCursor]++;
                    for (int i = pointerCursor + 1; i < L; i++) {
                        pointers[i] = pointers[pointerCursor] + i - pointerCursor;
                    }

                    pointerCursor = L - 1;
                    return;
                }

                // 첫번째 포인터이고 최대치에 도달한 경우 처리
                if(pointerCursor == 0 && (pointers[pointerCursor+1] == pointers[pointerCursor] +1)) {
                    flag = false;
                    return;
                }

            }
        }

        static boolean shouldMoveCursor(){
            if(pointerCursor == L-1) {
                return pointers[pointerCursor] == C-1;
            }
            return (pointers[pointerCursor+1] == pointers[pointerCursor] +1);
        }
    }
