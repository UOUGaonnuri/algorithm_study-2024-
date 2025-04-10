import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 25166 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        StringTokenizer st = new StringTokenizer(br.readLine());

        int S = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(st.nextToken());

        System.out.println(solution(S, M));
    }

    static String solution(int S, int M) {
        int tmp = S - 1023;
        if (tmp <= 0) {
            return "No thanks";
        }
        if ((tmp & M) == tmp) {
            return "Thanks";
        }
        return "Impossible";
    }
}

