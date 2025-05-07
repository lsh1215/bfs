import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int array[] = new int[100001];
    static Queue<Integer> queue = new LinkedList<>();

    static void bfs(int n, int m) {
        queue.add(n);
        array[n] = 1;

        while (true) {
            int x = queue.poll();

            if (x == m) {
                System.out.println(array[x] - 1);
                break;
            }

            for (int i = 0; i <= 2; i++) {
                int nx;

                if (i == 0) {
                    nx = x - 1;
                } else if (i == 1) {
                    nx = x + 1;
                } else {
                    nx = x * 2;
                }

                if (nx >= 0 && nx <= 100000 && array[nx] == 0) {
                    array[nx] = array[x] + 1;
                    queue.add(nx);
                }

            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        bfs(n, m);

    }
}

