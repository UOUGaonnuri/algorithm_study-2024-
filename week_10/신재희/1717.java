import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    static String SUCCESS = "YES";
    static String FAIL = "NO";

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        int[][] commands = new int[M][3];

        for (int i = 0; i < M; i++) {
            input = new StringTokenizer(bf.readLine());

            commands[i][0] = Integer.parseInt(input.nextToken());
            commands[i][1] = Integer.parseInt(input.nextToken());
            commands[i][2] = Integer.parseInt(input.nextToken());
        }
        
        System.out.println(solution(N, M, commands));
    }

    static String solution(int N, int M, int[][] commands) {
        int[] group = IntStream.range(0, N + 1).toArray();

        StringJoiner result = new StringJoiner("\n");

        for (int[] command : commands) {
            if (command[0] == 0) {
                int a = find(command[1], group);
                int b = find(command[2], group);

                group[Math.max(a, b)] = Math.min(a, b);
            } else {
                result.add(find(command[1], group) == find(command[2], group) ? SUCCESS : FAIL);
            }
        }

        return result.toString();
    }

    static int find(int n, int[] group) {
        if (n != group[n]) group[n] = find(group[n], group);
        return group[n];
    }
}