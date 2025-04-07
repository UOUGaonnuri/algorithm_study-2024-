// 실버 2 - 14400번 : 편의점 2
// 작성자 : free4760(jeonghoe22)

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

// 모든 고객들의 거리의 합을 최소
// 거리 = abs(x1 - x2) + abs(y1 - y2)

// n명 고객 : 10^5명
// 입력 : x, y

// 모든 고객들의 거리 합 최소 -> 편의점
// 출력 : 최소 거리 합

// y좌표와 x좌표를 별도로 취급

// y좌표
// 2 4 6 9

// y = 2 -> 0 2 4 7 = 13
// y = 3 -> 1 1 3 6 = 11
// y = 4 -> 2 0 2 5 = 9
// y = 5 -> 3 1 1 4 = 9
// y = 6 -> 4 2 0 3 = 9
// y = 7 -> 5 3 1 2 = 11
// y = 8 -> 6 4 2 1 = 13
// y = 9 -> 7 5 3 0 = 15

// x좌표
// 1 2 3 5

// x = 1 -> 0 1 2 4 = 7
// x = 2 -> 1 0 1 3 = 5
// x = 3 -> 2 1 0 2 = 5
// x = 4 -> 3 2 1 1 = 7
// x = 5 -> 4 3 2 0 = 9

// N이 짝수 : 정렬 후, 가운데 두 수 사이에 있는 위치 아무데나 선택
// N이 홀수 : 정렬 후, 가운데 수 선택

// 기준 -> 짝수와 홀수 무관하게 N / 2를 하면 됨.

typedef long long ll;

int main()
{
    // 입출력 최적화
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    // 고객의 수 N
    int N;
    cin >> N;

    // 좌표 입력
    vector<int> x_locs(N);
    vector<int> y_locs(N);
    for(int i = 0; i < N; i++)
    {
        cin >> x_locs[i] >> y_locs[i];
    }

    // 오름차순 정렬
    sort(x_locs.begin(), x_locs.end());
    sort(y_locs.begin(), y_locs.end());

    // 기준 정하기
    int idx = N / 2;

    // 거리 계산
    ll result = 0;
    for(int i = 0; i < N; i++)
    {
        result += abs(x_locs[idx] - x_locs[i]) + abs(y_locs[idx] - y_locs[i]);
    }

    cout << result << "\n";
}