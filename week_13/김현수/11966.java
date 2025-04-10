
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
* 비트 마스킹
* N이 2의 제곱수면 1을 아니면 0을 출력
*
* 비트 연산자 : &,|,~,<<,>>,^, >>>(부호 없는 오른쪽 이동 : 부호 비트 무시)
*
* - N이 2의 제곱수인지 확인하는 방법
* - N(1 ≤ N ≤ 2^30) 이니까 right shift 연산을 통해 2의 제곱수인지 확인 가능
* */
public class 11966 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        System.out.println(solution(N));
    }
    static int solution(int N) {
        for (int i = 0; i <= 30; i++) {
            if (N == (1 << i)) return 1;
        }
        return 0;
    }
}
