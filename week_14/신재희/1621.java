import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/*
이거 DP 문제임
내가 보기엔...
그래서 어떻게 구하냐
K개 전하고 바로 전을 비교
DP[i-K] + C, DP[i-1] + W[i]
이거다 지렸다 ㄱㄱ
근데 이거하고 찾을때 되돌아오는 로직을 만들어야할듯
 */


/*
반례
5
3 3
4 4 4 1 1
 */

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int K = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        long[] W = new long[N+1];

        st = new StringTokenizer(bf.readLine());

        for (int i=1; i<=N; i++) {
            W[i] = Long.parseLong(st.nextToken());
        }

        System.out.println(solution(N, K, C, W));
    }

    public static Result solution(int N, int K, int C, long[] W) {

        long[] DP = new long[N+1];

        DP[0] = 0;

        for (int i=1; i<=N; i++) {
            if (i >= K) DP[i] = Math.min(DP[i-K] + C, DP[i-1] + W[i]);
            else DP[i] = DP[i-1] + W[i];
        }

        LinkedList<Integer> l = new LinkedList<>();

        int idx = N;

        while (idx >= K) {
            if (DP[idx] == DP[idx-1] + W[idx]) {
                idx--;
            } else {
                idx-=K;
                l.addFirst(idx+1);
            }
        }

        return new Result(DP[N], l);
    }

    public static class Result {
        long time;
        List<Integer> list;

        Result(long time, List<Integer> list) {
            this.time = time;
            this.list = list;
        }

        @Override
        public String toString() {
            StringJoiner sj = new StringJoiner("\n");

            sj.add(String.valueOf(time));

            sj.add(String.valueOf(list.size()));

            sj.add(list.stream().map(String::valueOf).collect(Collectors.joining(" ")));

            return sj.toString();
        }
    }
}
