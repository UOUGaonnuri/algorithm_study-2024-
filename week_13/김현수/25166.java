
/*
* 배고픈 아리의 샌드위치 구매하기(비트 마스크 활용)
*
* 사용되는 동전 1원, 2원, 4원, 8원, 16원, 32원, 64원, 128원, 256원, 512원
* 아리는 10가지의 동전을 각각 1개씩 총 10개의 동전을 가지고 있다.(샌드위치 가격 S)
* 쿠기는 10종류의 동전 중에 모든 종류가 아니라 일부 종류의 동전을 각각 1개씩 가지고 있다.(동전들의 총 금액은 M 원)
* 아리가 가진 돈을 모두 사용한 후에 부족한 금액에 대해서 쿠기에게 돈을 빌림
* 입력 : S(1 ≤ S ≤ 2048): 2^11 , M(1 ≤ M ≤ 1023) : (2^10)-1
* 출력 :  "No thanks","Thanks","Impossible"
*
* - 아리는 모든 동전을 1개씩 가지고 있으므로 총 1023원 ((1 << 10) - 1)
* - 필요한 돈을 구하고 쿠기가 해당 동전을 갖고 있는지 비트 연산으로 확인
* - 쿠기가 갖고 있지 않은 동전들(~M)이 필요한 동전에 없으면 0
* */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 25166 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        System.out.print(solution(S, M));
    }

    static String solution(int S, int M) {
        int ARI_MONEY = (1 << 10) - 1; //아리 돈

        if(ARI_MONEY >= S) return "No thanks";

        int needed = S - ARI_MONEY; //필요한 돈

        if ((needed & ~M) == 0) { // 필요한 동전이 쿠키가 갖고 있지 않은 동전에 없는 경우
       //if((needed & M) == needed) { // 같은말
            return "Thanks";
        }

        return "Impossible";
    }
}
