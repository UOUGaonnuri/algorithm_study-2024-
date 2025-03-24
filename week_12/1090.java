/*
N개의 체커
체커에 x, y 좌표가 주어진다.
위, 아래, 오른, 왼 쪽으로 한칸 움직인다
N <= 50 자연수
x, y <= 100만 자연수

같은 칸으로 k개가 모일 때 이동해야하는 최소 횟수

거리 = (x 좌표 차이) + (y 좌표 차이)
점을 하나 찍으면 그 점에서 모든 점까지의 거리 구하면 된다

100만 * 100만 = ?

주어진 좌표만 탐색해도 괜찮겠네?

체커의 x좌표, 체커의 y좌표는 50개 이하

x, y체커가 있는 애들만 조회

50 * 50 최소 횟수 구하는게 총 2500 = 시간복잡도 -> 모든 점 조회

각점의 거리를 구하고 가까운 순서대로 찾아서 더해주면 1개, 2개, 3개~~ 다양한 개수의 점들의 거리
1 2 4 9
1에서 모든 거리 구해보면 0, 1, 3, 8
2에서 모든 거리 구해보면 1, 0, 2, 7
4에서 모든 거리 구해보면 3, 2, 0, 5
9에서 모든 거리 구해보면 8, 7, 5, 0

그리디 작은 애들부터 더하면된다.

(누적합)
1에서 모든 거리 구해보면 0, 1, 3, 8 => 0, 1, 4, 12
2에서 모든 거리 구해보면 0, 1, 2, 7 => 0, 1, 3, 10
4에서 모든 거리 구해보면 0, 2, 3, 5 => 0, 2, 5, 10
9에서 모든 거리 구해보면 0, 5, 7, 8 => 0, 5, 12, 20
각각 최솟값 꺼내주면 > 0, 1, 3, 10

1. 모든 체커의 x, y 각각 조회
2. 그리고 해당 점위치에서 각 체커의 거리를 구해줘요
3. 그리고 그 거리를 정렬
4. 누적합
5. 그리고 결과에서 최솟값으로 계속 갱신
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        int N = read();

        int[] xArr = new int[N];
        int[] yArr = new int[N];

        for (int i = 0; i < N; i++) {
            xArr[i] = read();
            yArr[i] = read();
        }

        System.out.println(solution(N, xArr, yArr));
    }

    public static String solution(int N, int[] xArr, int[] yArr) {
        int[] result = new int[N];

        Arrays.fill(result, Integer.MAX_VALUE);
        
        for (int x : xArr) {
            for (int y : yArr) {
                List<Integer> degree = new ArrayList<>();
                for (int i = 0; i < N; i++) {
                    degree.add(Math.abs(xArr[i] - x) + Math.abs(yArr[i] - y));
                }
                
                degree.sort(Comparator.naturalOrder());
                
                int now = 0;
                
                for (int i = 0; i < N; i++) {
                    now += degree.get(i);
                    result[i] = Math.min(result[i], now);
                }
            }
        }
        
        return Arrays.stream(result).mapToObj(String::valueOf).collect(Collectors.joining(" "));
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