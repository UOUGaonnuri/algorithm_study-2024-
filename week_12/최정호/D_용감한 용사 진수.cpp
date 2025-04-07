// 골드 4 - 14718번 : 용감한 용사 진수
// 작성자 : free4760(jeonghoe22)

#include <iostream>
#include <vector>
#include <algorithm>
#include <limits>
#include <cmath>

using namespace std;

// N명 병사

// 이기는 조건
// 1. 적 병사보다 진수의 힘이 크거나 같음.
// 2. 적 병사보다 진수의 민첩이 크거나 같음.
// 3. 적 병사보다 진수의 지능이 크거나 같음.

// 최대한 많은 적을 이기는게 목표
// 스탯 포인트 스스로 분배

// 적어도 K명 병사를 이길 수 있게 하는 최소의 스탯 포인트 구하기

// 병사 능력치의 총합을 오름차순으로 나열
// 해당 병사를 이길려면, 해당 병사의 능력치 총합만큼 스탯을 투자해야하기 때문

// 오름차순된 병사 목록 중에서 능력치 별로 최댓값을 구하기

// 총량이 다르더라도, 편차가 적을수록 좋은 것?

// 반례 1.
// 3 2
// 80 80 140
// 100 100 100
// 101 101 101
// ans = 303

// 반례 2.
// 3 2
// 99 99 102
// 100 100 100
// 101 100 99
// ans = 301

// N과 K의 최댓값은 100
// 각 몬스터의 힘, 민첩, 지능을 순서대로 선택
// 이길 수 있는 몬스터 수 세기 -> K보다 크면 힘,민첩,지능의 합이 최솟값인지 확인
// 100 * 100 * 100 * 100 = 10^8

struct Stat
{
    int str = 0;
    int dex = 0;
    int inte = 0;
};

vector<Stat> g_enemies;

// 적 병사 정보 입력 및 정렬
void inputEnemy(const int N)
{
    g_enemies = vector<Stat>(N);

    for(Stat& enemy : g_enemies)
    {
        cin >> enemy.str >> enemy.dex >> enemy.inte;
    }
}

// 능력치 최소 투자 비용 구하기
int search(const int N, const int K)
{
    int minTotal = numeric_limits<int>::max();

    for(int str_idx = 0; str_idx < N; str_idx++)
    {
        for(int dex_idx = 0; dex_idx < N; dex_idx++)
        {
            for(int inte_idx = 0; inte_idx < N; inte_idx++)
            {
                // 각 몬스터별로 능력치를 하나 선택
                int cur_str = g_enemies[str_idx].str;
                int cur_dex = g_enemies[dex_idx].dex;
                int cur_inte = g_enemies[inte_idx].inte;

                int count = 0;
                // 해당 능력치로 몬스터 몇 명을 이기는지 검사
                for(int monster = 0; monster < N; monster++)
                {
                    // 현재 캐릭터 능력치로 몬스터를 이길 수 있는 조건
                    if(g_enemies[monster].str <= cur_str 
                        && g_enemies[monster].dex <= cur_dex 
                        && g_enemies[monster].inte <= cur_inte)
                        count++;
                }

                // 몬스터 해치울 수 있는 수가 K 이상일 때, 최솟값 초기화
                if(count >= K)
                {
                    minTotal = min(minTotal, cur_str + cur_dex + cur_inte);
                }
            }
        }
    }

    return minTotal;
}

int main()
{
    // 입출력 최적화
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    // 병사 수 N, 용사 K
    int N, K;
    cin >> N >> K;

    // 적 병사 정보 입력 및 정렬
    inputEnemy(N);

    // 능력치 최소 투자 비용 구하기
    int result = search(N, K);

    // 출력
    cout << result << "\n";
}