import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[][] network;
    static int[] visited;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]); // Number of computers
        int m = Integer.parseInt(input[1]); // Number of connections

        network = new int[n + 1][n + 1];
        visited = new int[n + 1];

        for (int i = 0; i < m; i++) {
            String[] edge = br.readLine().split(" ");
            int a = Integer.parseInt(edge[0]);
            int b = Integer.parseInt(edge[1]);

            network[a][b] = 1;
            network[b][a] = 1;
        }

        count = 0;

        for (int i = 1; i <= n; i++) {
            if (visited[i] == 0) {
                bfs(i);
                count++;
            }
        }

        System.out.println(count);
    }

    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = 1;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int i = 1; i < network.length; i++) {
                if (network[current][i] == 1 && visited[i] == 0) {
                    queue.add(i);
                    visited[i] = 1;
                }
            }
        }
    }

}
