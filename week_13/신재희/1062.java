import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] words;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var NK = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        var N = NK[0];
        var K = NK[1];

        words = new int[N];
        for (int n=0; n<N; n++) {
            var now = bufferedReader.readLine().strip();
            for (char c: now.substring(4, now.length()-4).toCharArray()) {
                words[n] = words[n]|(1<<(c-'a'));
            }
        }
        System.out.println(solution(N, K));
    }

    static int solution(int N, int K){
        // antic
        var bits = 0;
        for (char c: new char[]{'a', 'n', 't', 'i', 'c'}) {
            bits = bits | (1<<(c - 'a'));
        }
        return dfs(bits, -1, K-5);
    }

    static int dfs(int bits, int now, int depth) {
        if (depth<0) return 0;
        if (depth==0) return findWords(bits);
        var result = 0;
        for (int i=now+1; i<26; i++) {
            if ((bits&(1<<i))>0) continue;
            result = Math.max(result, dfs(bits|(1<<i), i, depth-1));
        }
        return result;
    }

    static int findWords(int bits){
        var result = 0;
        for (int word: words) {
            if ((bits&word)==word) result++;
        }
        return result;
    }
}