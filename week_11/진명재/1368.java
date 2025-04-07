import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class 1368 {
    static int[] g;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];


        for (int i = 1; i < N + 1; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        PriorityQueue<non> que = new PriorityQueue<>(Comparator.comparingInt(non::getV));

        for (int i = 1; i < N + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                int v = Integer.parseInt(st.nextToken());
                if (i >= j) continue;
                que.add(new non(i, j, v));
            }
        }

        System.out.println(solution(N, que, arr));


    }

    static int solution(int N, PriorityQueue<non> que , int[] arr) {
        g = IntStream.rangeClosed(0, N + 1).toArray();

        int result = 0;
        
        for(int o=1;o<=N;o++){
            result += arr[o];
        }
        
        while(!que.isEmpty()){
            non a = que.poll();

            int i = find(a.a, g);
            int j = find(a.b, g);

            if (i == j) {
                continue;
            }
            int max = Math.max(arr[i], arr[j]);

            if(max<a.getV()){
                continue;
            }

            result += a.getV();
            result -= max;
            arr[i] = arr[j] = Math.min(arr[i], arr[j]);
            group(i, j, g);

        }
        return result;
    }

    static void group(int n, int m, int[] g) {
        g[Math.max(n, m)] = Math.min(n, m);
    }

    static int find(int N, int[] g) {
        if (g[N] != N) {
            g[N] = find(g[N], g);
        }
        return g[N];
    }

    static class non {
        int a;
        int b;
        int v;

        non(int a, int b, int v) {
            this.a = a;
            this.b = b;
            this.v = v;

        }

        public int getV() {
            return v;
        }
    }
}
