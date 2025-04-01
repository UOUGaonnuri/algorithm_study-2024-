import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        System.out.println(solution(N, M));
    }

    public static String solution(int N, int M) {
        if (1024 > N) return "No thanks";
        if (((N - 1023) & M) == N - 1023) return "Thanks";
        return "Impossible";
    }
}