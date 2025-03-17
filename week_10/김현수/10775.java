/*
* 공항
* G개의 게이트, P개의 비행기
* 각 비행기는 1~G번 게이트 중 하나에 도킹
* 이미 도킹된 게이트에 도킹할 수 없음
* 최대한 많은 비행기를 도킹시키려면?
*
* - 유니온 파인드로 해결
* - 각 게이트를 각 루트로 초기화
* - 각 게이트에 비행기를 도킹 하면 이전 게이트와 연결 (같은 게이트에 도킹하면 이전 게이트에 도킹된다)
* - 이전 게이트가 0이면 종료
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());
        int[] gi = new int[P];
        for (int i = 0; i < P; i++) {
            gi[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(solution(G, P,  gi));
    }
    static int solution(int G, int P,  int[] gi) {
        int[] gates = IntStream.range(0, G+1).toArray();

        int result = 0;
        for (int i = 0; i < P; i++) {
            int g = find(gi[i], gates);
            if(g == 0) break;
            union(g, g-1, gates);
            result++;
        }
        return result;
    }
    static int find(int n, int[] group){
        if(n != group[n]) group[n] = find(group[n], group);
        return group[n];
    }
    static void union(int a, int b, int[] group) {
        a = find(a, group);
        b = find(b, group);
        if (a != b) group[Math.max(a, b)] = Math.min(a, b);
    }
}
