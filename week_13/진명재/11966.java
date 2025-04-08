import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 11966 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long N = Long.parseLong(br.readLine());

        long i = 1;
        boolean chk = false;
        while (N >= i) {
            if (i == N) {
                chk = true;
            }
            i = i << 1;
        }

        System.out.println(chk ? 1 : 0);
    }
}
