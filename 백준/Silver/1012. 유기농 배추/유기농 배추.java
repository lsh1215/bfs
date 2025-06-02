import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 유기농 배추 문제
// https://www.acmicpc.net/problem/1012
// BFS로 탐색해서 풀 예정
// 먼저 입력을 받아서 network 배열을 초기화하고, 배추가 심어진 위치를 1로 표시
// 그 후 BFS를 통해 연결된 배추의 개수를 세는 방식으로 문제를 해결할 예정

public class Main {

    static int[][] network;
    static int count;
    static int[] dx = {1, 0, -1, 0}; // 상하좌우 이동
    static int[] dy = {0, 1, 0, -1}; // 상하좌우 이동
    static int N; // 행의 개수
    static int M; // 열의 개수

    static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        network[x][y] = 0; // 방문 처리

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M && network[nx][ny] == 1) {
                    network[nx][ny] = 0; // 방문 처리
                    queue.add(new int[]{nx, ny});
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Integer T = Integer.parseInt(reader.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(tokenizer.nextToken());
            M = Integer.parseInt(tokenizer.nextToken());
            Integer K = Integer.parseInt(tokenizer.nextToken());

            network = new int[N][M];
            count = 0;

            for (int i = 0; i < K; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                Integer u = Integer.parseInt(tokenizer.nextToken());
                Integer v = Integer.parseInt(tokenizer.nextToken());
                network[u][v] = 1;
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (network[i][j] == 1) {
                        count++;
                        bfs(i, j);
                    }
                }
            }
            System.out.println(count);
        }

    }
}
