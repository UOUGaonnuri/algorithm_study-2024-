import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var S = bf.readLine();

        var N = Integer.parseInt(bf.readLine());

        var words = new ArrayList<String>();
        for (int i=0; i<N; i++) {
            words.add(bf.readLine());
        }

        System.out.println(solution(S, N, words));
    }

    static int solution(String S, int N, List<String> words) {
        var dp = new int[S.length()+1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int now=0; now<S.length(); now++) {
            if (dp[now]==-1) continue;
            Loop: for (String word: words) {
                if (word.length()+now > S.length()) continue;
                var a = new int[26];
                var result = 0;
                for (int i=0; i<word.length(); i++) {
                    if (word.charAt(i) == S.charAt(now+i)) continue;
                    result++;
                    a[word.charAt(i)-'a']--;
                    a[S.charAt(now+i)-'a']++;
                }
                for (int i: a) {
                    if (i!=0) continue Loop;
                }
                if (dp[word.length() + now] == -1) dp[word.length() + now] = result + dp[now];
                else dp[word.length() + now] = Math.min(dp[word.length() + now], result + dp[now]);
            }
        }

        return dp[S.length()];
    }
}