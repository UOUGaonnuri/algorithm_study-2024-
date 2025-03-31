// 실버 3 - 18310번 : 안테나
// 작성자 : free4760(jeonghoe22)

#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

// 집의 수 N
// 집 좌표 i

// 동일한 위치에 여러 집 존재 가능
// 한 개의 안테나 : x
// 총 거리 합 최소
// 거리 = abs(x - i)

// 5 1 7 9 -> 정렬

// 1 5 7 9
// x = 1 -> 0 4 6 8 = 20
// x = 2 -> 1 3 5 7 = 16
// x = 3 -> 2 2 4 6 = 14
// x = 4 -> 3 1 3 5 = 12
// x = 5 -> 4 0 2 4 = 10
// x = 6 -> 5 1 1 3 = 10
// x = 7 -> 6 2 0 2 = 10
// x = 8 -> 7 3 1 1 = 12
// x = 9 -> 8 4 2 0 = 14

// 1 2 5
// x = 1 -> 0 1 4 = 5
// x = 2 -> 1 0 3 = 4
// x = 3 -> 2 1 2 = 5
// x = 4 -> 3 2 1 = 6
// x = 5 -> 4 3 0 = 7

// 가장 작은 위치를 출력

// N이 짝수 : 가운데에 있는 두 수 중 앞의 위치
// N이 홀수 : 가운데에 있는 수



typedef long long ll;

int main()
{
    // 입출력 최적화
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    // 집의 수 N
    int N;
    cin >> N;

    // 집의 좌표 입력
    vector<int> houses(N);
    for(int& house : houses){cin >> house;}

    // 오름차순 정렬
    sort(houses.begin(), houses.end());

    // 기준 구하기
    int idx = (N % 2 == 0) ? (N / 2 - 1) : (N / 2);

    int result = houses[idx];

    // 출력
    cout << result << "\n";
}