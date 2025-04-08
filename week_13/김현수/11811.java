
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* 데스스타(비트 마스크)
* 크기 N의 정사각 행렬(음이 아닌 정수),
* i번째 행 j번째 열에 적힌 숫자는 ai와 aj에 비트연산 and를 수행한 결과값
* 행렬의 주 대각선에 있는 숫자를 읽을 수 없게 되었다 -> 원래 배열을 재구성 하자
* 답은 유일하지 않을 수 있지만, 항상 존재하도록 주어진다.(음...)
*
* 입력 : 행렬의 크기 N (1 ≤ N ≤ 1 000) , N개의 줄 행렬의 각 원소인 N개의 숫자 Mij (1 ≤ Mij ≤ 10^9)
* 출력 : 한 줄에 문제의 조건을 만족하는 N개의 음이 아닌 정수( <= 10^9)
*
* - 주어진 행렬로 수열을 복원 해서 행렬의 대각선 값 구하기
* - ai와 aj의 AND연산은 ai와 aj의 공통 비트만 남기고 나머지 비트는 0으로 바뀜
* - 즉, ai가 참여한 모든 AND연산의 결과를 OR연산하면 ai를 복원할 수 있다......
* */
public class 11811 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

            int[] a = solution(N, arr);

            StringBuilder sb = new StringBuilder();
            for (int num : a) {
                sb.append(num).append(" ");
            }
            System.out.println(sb);

    }
    static int[] solution(int N, int[][] arr) {
        int[] result = new int[N];

        for (int i = 0; i < N; i++) {
            int value = 0;
            for (int j = 0; j < N; j++) {
                value |= arr[i][j]; // OR 연산으로 비트를 복원
            }
            result[i] = value;
        }

        return result;
    }
}