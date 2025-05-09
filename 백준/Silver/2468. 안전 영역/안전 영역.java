import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제를 푸는 기준을 두 개 정해야 함
 * 1. Queue에 넣는 기준
 * 2. 간선 갯수를 세는 법
 * (1,1) 부터 물 높이보다 낮은 곳인지 확인하고 맞으면 넣기
 * count = 1 시작
 * 그 다음에 상하좌우로 물 높이보다 낮은 곳으로 이동하면서 이어지는 것은 다 체크하기 (visited 배열)
 * 이어지는 것들은 visited 배열에 체크
 * count += 1 하면서 계속 진행
 *
 * */


/*
여기서 의문인 것은 그러면 반복문을 세번 써야 하는건가?
1. 일단 각 지역의 높이 정보 입력받기
2. 물 높이를 2~max까지 반복문을 돌리면서 물 높이 이하의 지역을 체크
 */

public class Main {
    static int[][] arr;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Queue<int[]> queue = new LinkedList<>();

    static void bfs(int i, int j, int k, boolean[][] visited) {
        int nx, ny;
        queue.add(new int[]{i, j});
        while (!queue.isEmpty()) {
            int current[] = queue.poll();

            for (int d = 0; d < 4; d++) {
                ny = current[1] + dy[d];
                nx = current[0] + dx[d];

                if (ny < 1 || nx < 1 || ny > arr.length - 1 || nx > arr.length - 1) {
                    continue;
                }

                if (arr[nx][ny] >= k && !visited[nx][ny]) {
                    queue.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        arr = new int[n + 1][n + 1];

        int max = 0;

        for (int i = 1; i <= n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

            for (int j = 1; j <= n; j++) {
                arr[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if (arr[i][j] > max) {
                    max = arr[i][j];
                }
//                System.out.print(arr[i][j] + " ");
            }
//            System.out.println();
        }

        int maxCount = 0;
        for (int k = 1; k <= max; k++) {
            boolean[][] visited = new boolean[n + 1][n + 1];
            int count = 0;

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (arr[i][j] >= k && !visited[i][j]) {
                        bfs(i, j, k, visited);
                        count++;
                    }
                }
            }
            maxCount = Math.max(maxCount, count);
        }

        System.out.println(maxCount);

    }
}

