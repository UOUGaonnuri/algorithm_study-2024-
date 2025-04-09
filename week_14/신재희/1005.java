import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var T = Integer.parseInt(bufferedReader.readLine());

        for (int t=0; t<T; t++){
            var NK = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            var N = NK[0];
            var K = NK[1];

            var time = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            Set<Integer>[] before = new Set[N];
            Set<Integer>[] after = new Set[N];
            for (int n=0; n<N; n++) {
                before[n] = new HashSet<Integer>();
                after[n] = new HashSet<Integer>();
            }

            for (int k=0; k<K; k++) {
                var now = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)-1).toArray();
                before[now[1]].add(now[0]);
                after[now[0]].add(now[1]);
            }

            var win = Integer.parseInt(bufferedReader.readLine())-1;
            System.out.println(solution(N, K, time, before, after, win));
        }
    }

    static int solution(int N, int K, int[] time, Set<Integer>[] before, Set<Integer>[] after, int win){
        var result = new int[N];
        var q = new LinkedList<Integer>();
        for (int i=0; i<N; i++) {
            if (before[i].isEmpty()) q.add(i);
        }

        while (!q.isEmpty()) {
            var now = q.remove();
            for (int n: after[now]){
                result[n] = Math.max(result[n], result[now] + time[now]);
                before[n].remove(now);
                if (before[n].isEmpty()) q.add(n);
            }
        }

        return result[win] + time[win];
    }
}