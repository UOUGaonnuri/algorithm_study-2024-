
/*
* 가르침
*
* K개의 글자를 가르침
* 어떤 K(0~26)개의 글자를 가르치면
* 학생들은 K개의 글자로만 이루어진 단어만을 읽을 수 있다.
* 단어의 시작 "anta" 와 끝 "tica"는 항상 포함되어 있음.
* 남극의 언어는 N(1~50)개
* 단어 중복x (8~15) , 소문자
* 입력 : N,K,N개의 단어
* 출력 : K개의 글자를 가르칠 때, 학생들이 읽을 수 있는 단어 개수의 최댓값
*
* 완탐? dfs(중복 제거)
* - 반드시 가르쳐야 할 글자 : 'a', 'n', 't', 'i', 'c' (5개)
* - 각 단어를 비트마스크로 변환하여 저장(index 0~25)
* -> int는 index 0~31까지 표현 가능(단,1<<31은 음수)
* - 남은 K - 5개의 알파벳 조합을 DFS로 선택(이미 배운 글자 중복 제거)
* - 매 조합마다 읽을 수 있는 단어 수를 계산하고 최대값 갱신
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 1062 {
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] words = new int[N];

        // 단어 비트마스크 (a, c, i, n, t)
        int learnedMask = 0;
        learnedMask |= 1 << ('a' - 'a'); //index 0
        learnedMask |= 1 << ('c' - 'a'); //index 2
        learnedMask |= 1 << ('i' - 'a'); //index 8
        learnedMask |= 1 << ('n' - 'a'); //index 13
        learnedMask |= 1 << ('t' - 'a'); //index 19

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            int mask = 0;
            for (char ch : word.toCharArray()) {
                mask |= 1 << (ch - 'a');
            }
            words[i] = mask;
        }

        System.out.println(solution(N, K, learnedMask, words));
    }

    public static int solution(int N, int K, int learnedMask, int[] words) {
        if (K < 5) return 0; // 필수 5글자도 못 배우면 못 읽음
        if (K == 26) return N; // 모든 알파벳 배우면 모두 읽음
        max = 0;

        dfs(K, 0, 0, learnedMask, words);
        return max;
    }

    public static void dfs(int K, int index, int depth, int learned, int[] words) {
        if (depth == K - 5) {
            int count = 0;
            for (int word : words) {
                if ((word & learned) == word) count++; // 단어의 모든 비트가 포함되어 있으면 읽을 수 있음
            }
            max = Math.max(max, count);
            return;
        }

        for (int i = index; i < 26; i++) {
            if ((learned & (1 << i)) != 0) continue; // 이미 배운 글자 넘김
            dfs(K, i + 1, depth + 1, learned | (1 << i), words); // 글자 배우기
        }
    }
}
