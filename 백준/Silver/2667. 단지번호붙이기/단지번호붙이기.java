import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 단지 번호 붙이기
// 이것도 딱 보는 순간 bfs라는게 느껴짐
// dx, dy로 상하좌우 움직이면서 갈 수 있는 거 까지 가고 이때 count에 값 추가하면 됨

public class Main {

    static int[][] network;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int count = 0; // 단지 수

    static List<Integer> list = new ArrayList<>();

    static int bfs(int x, int y, int n) {
        Queue<int[]> queue = new LinkedList<>();
        network[x][y] = 0;

        queue.add(new int[]{x, y}); // 시작점 (1, 1)로 가정
        int houseCount = 1;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 1 && nx <= n && ny >= 1 && ny <= n && network[nx][ny] == 1) {
                    queue.add(new int[]{nx, ny});
                    network[nx][ny] = 0;
                    houseCount++;
                }
            }
        }
        return houseCount;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        network = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            String line = reader.readLine();
            for (int j = 1; j <= n; j++) {
                network[i][j] = line.charAt(j - 1) - '0';
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (network[i][j] == 1) {
                    count++;
                    list.add(bfs(i, j, n));
                }
            }
        }
        System.out.println(count); // 단지 수 출력
        list.sort(Integer::compareTo);
        for (int num : list) {
            System.out.println(num); // 각 단지의 집 수 출력
        }

    }
}
