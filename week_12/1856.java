/*
단어 w개
알파벳 소문자 25자 이하
길이가 I인 문자열 S 몇개 문자 제외하면 단어의 조합으로 된다
문자열을 표현하기위해 제거해야하는 글자의 최소 개수

1 <= w <= 600
2 <= I <= 300

처음부터 싹다 완탐을 돌려요

냅다 가능한 애들 다 찾아보는거에요

10개를 싹다 없애요

각각의 단어를 끝까지 탐색했다가 실패했다
한글자씩 될때 abcsjdfasklfdsakf
a, b, c, d
600 ^ 300 = ????

browndcodw
      codw
brown -> cow
????? -> cow

dp 이전값 저장하면 되겠구나

dp[현재 살펴본 문자열의 길이] = 최솟값

0번째에서 완탐
1번째에서 모든 단어 완탐
...
n번째까지 모든 단어 완탐

dp[n] -> 정답이 되도록

1. 문자열 시작 지점 탐색
    2. 단어 탐색
        3. s가 다르면 s의 글자 없애주고 같으면 단어로 처리해서 같이 증가
        4. dp 최솟값으로 갱신

*/

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = read();
        int L = read();

        char[] s = bf.readLine().toCharArray();

        char[][] words = new char[N][];

        for (int i = 0; i < N; i++) {
            words[i] = bf.readLine().toCharArray();
        }

        System.out.println(solution(N, L, s, words));
    }

    public static int solution(int N, int L, char[] s, char[][] words) {
        int[] dp = new int[L + 1];

        Arrays.fill(dp, L << 2);

        dp[0] = 0;

        for (int start = 0; start < L; start++) {
            for (char[] word : words) {
                if (word[0] != s[start]) continue;

                int wIdx = 0;
                int sIdx = start;
                int delete = 0;

                while (wIdx < word.length && sIdx < s.length) {
                    if (word[wIdx] == s[sIdx]) {
                        sIdx++;
                        wIdx++;
                    } else {
                        sIdx++;
                        delete++;
                    }
                }

                if (wIdx == word.length) {
                    dp[sIdx] = Math.min(dp[sIdx], dp[start] + delete);
                }
            }

            dp[start + 1] = Math.min(dp[start + 1], dp[start] + 1);
        }

        return dp[L];
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