import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/*
* 사이클 게임
* N개의 점, M개의 선
* 두 점을 선택해 연결
* 사이클이 생기면 종료
*
* 유니온 파인드로 사이클 확인
* - 연결하려는 두 점이 이미 같은 집합에 속해있으면 사이클이 생김
* */
public class 20040 {

    public static void main(String[] args)throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] lines = new int[M][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            lines[i][0] = Integer.parseInt(st.nextToken());
            lines[i][1] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(N, M, lines));
    }

    static int solution(int N, int M, int[][] lines){
        int[] group = IntStream.range(0, N).toArray();

        for (int i = 0; i < M; i++) {
            int a = find(lines[i][0], group);
            int b = find(lines[i][1], group);

            if(a == b) return i + 1; //사이클이 만들어짐

            group[Math.max(a, b)] = Math.min(a, b);
        }
        return 0;
    }

    static int find(int n, int[] group) {
        if (n != group[n]) group[n] = find(group[n], group);
        return group[n];
    }
}
