import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/*
앞에서부터 가능한거 PQ에 싹다 넣자
그러고 가장 좋은거부터 계속 가져오면 굿
 */

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        PriorityQueue<Bridge> bridges = new PriorityQueue<>(Comparator.comparingInt(Bridge::getV));

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(bf.readLine());

            for (int j = 0; j < N; j++) {
                int v = Integer.parseInt(input.nextToken());
                if (i >= j) continue;
                bridges.add(new Bridge(i, j, v));
            }
        }

        System.out.println(solution(N, bridges));
    }

    static String solution(int N, PriorityQueue<Bridge> bridges) {
        List<Bridge> result = new ArrayList<>();

        int[] g = IntStream.range(0, N).toArray();
        int count = 0;
        int cost = 0;

        while(!bridges.isEmpty() && (count < N - 1 || bridges.peek().v < 0)) {
            Bridge bridge = bridges.remove();

            int a = find(bridge.a, g);
            int b = find(bridge.b, g);

            if (a > b) {
                a += b;
                b = a - b;
                a -= b;
            }

            if (bridge.v > 0) {
                if (a == b) continue;
                result.add(bridge);
                cost += bridge.v;
            } else {
                cost -= bridge.v;
            }
            if (a != b) count++;
            g[b] = a;
        }

        StringJoiner output = new StringJoiner("\n");
        output.add(cost + " " + result.size());
        for (Bridge b : result) {
            output.add(b.toString());
        }
        return output.toString();
    }

    static int find(int n, int[] g) {
        if (n != g[n]) g[n] = find(g[n], g);
        return g[n];
    }

    static class Bridge {
        int a;
        int b;
        int v;

        public Bridge(int a, int b, int v) {
            this.a = a;
            this.b = b;
            this.v = v;
        }

        public int getV() {
            return v;
        }

        @Override
        public String toString() {
            return (a + 1) + " " + (b + 1);
        }
    }
}