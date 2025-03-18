import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var NM = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        var N = NM[0];
        var M = NM[1];

        var streets = new PriorityQueue<Street>(Comparator.comparingInt(Street::getValue));

        for (int i=0; i<M; i++){
            var now = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            streets.add(new Street(now[0], now[1], now[2]));
        }

        System.out.println(solution(N, M, streets));
    }

    static int solution(int N, int M, PriorityQueue<Street> streets){
        var graph = IntStream.range(0, N+1).toArray();
        var cnt = 0;
        var sum = 0;
        while (!streets.isEmpty()) {
            var now = streets.remove();
            var s = findMom(now.start, graph);
            var d = findMom(now.dest, graph);
            if (s==d) continue;
            cnt++;
            if (cnt==N-1) break;
            sum+=now.value;
            graph[Math.max(s,d)] = Math.min(s,d);
        }
        return sum;
    }

    static int findMom(int n, int[] graph) {
        if (graph[n] == n) return n;
        graph[n] = findMom(graph[n], graph);
        return graph[n];
    }
    static class Street {
        int start;
        int dest;
        int value;

        Street(int start, int dest, int value) {
            this.start = start;
            this.dest = dest;
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}