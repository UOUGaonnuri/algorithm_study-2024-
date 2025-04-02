import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 14718 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
        }


        System.out.println(soultion(N, arr, M));


    }

    static int soultion(int N, int[][] arr, int M) {
        int sum = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int l = 0; l < N; l++) {
                    int cnt = 0;
                    for (int k = 0; k < N; k++) {
                        if (arr[i][0] >= arr[k][0] && arr[j][1] >= arr[k][1] && arr[l][2] >= arr[k][2]) {
                            cnt++;
                        }
                        if (cnt >= M) {
                            sum = Math.min(sum, (arr[l][2] + arr[i][0] + arr[j][1]));
                            break;
                        }
                    }
                }

            }
        }

        return sum;
    }
}
