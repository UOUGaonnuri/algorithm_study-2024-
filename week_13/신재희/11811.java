import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        var N = read();

        int[] result = new int[N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int n = read();
                if (i >= j) continue;
                result[i] |= n;
                result[j] |= n;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i : result) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    static int read() throws IOException {
        int n = 0;
        int c;
        while ((c = System.in.read()) >= '0') {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}