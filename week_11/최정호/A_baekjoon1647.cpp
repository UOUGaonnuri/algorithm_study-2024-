// 골드 4 - 1647번 : 도시 분할 계획
// 작성자 : free4760(jeonghoe22)

#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

// 최소 스패닝 트리로 간선을 모두 구하고
// 이을 수 있는 간선 중 가장 비용이 높은 것을 빼면, 됨.

// 간선 정보 구조체
struct Line
{
    int x = 0;
    int y = 0;
    int cost = 0;

    // 우선 순위 큐에서 비용 기준으로 오름차순
    bool operator<(const Line& other) const
    {
        return cost > other.cost;
    }
};

vector<int> g_houses;
priority_queue<Line> g_pq;

// 집 초기화
void initHouse(const int N)
{
    g_houses = vector<int>(N+1);
    for(int i = 0; i < N+1; i++)
    {
        g_houses[i] = i;
    }
}

// 연결 정보 입력
void inputLine(const int M)
{
    for(int i = 0; i < M; i++)
    {
        Line line;
        cin >> line.x >> line.y >> line.cost;

        g_pq.push(line);
    }
}

// 가장 최상단의 집 찾기
int findHouse(const int idx)
{
    if(g_houses[idx] == idx) return idx;

    g_houses[idx] = findHouse(g_houses[idx]);
    return g_houses[idx];
}

// 집과 집을 연결할 수 있는지 확인하고, 가능하다면 연결
bool connectHouse(const int x, const int y)
{
    int px = findHouse(x);
    int py = findHouse(y);

    // 사이클 발생 시, 무시
    if(px == py) return false;

    g_houses[max(px, py)] = min(px, py);
    return true;
}

// 집끼리 서로 연결하기
int search()
{
    int max_cost = 0;
    int total_cost = 0;
    while(g_pq.empty() == false)
    {
        Line line = g_pq.top();
        g_pq.pop();

        // 연결 가능
        if(connectHouse(line.x, line.y))
        {
            total_cost += line.cost;
            max_cost = max(max_cost, line.cost);
        }
    }
    return total_cost - max_cost;
}

int main()
{
    // 입출력 최적화
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    // 집의 개수 N, 길의 수 M
    int N, M;
    cin >> N >> M;
    // 초기화
    initHouse(N);
    // 간선 입력
    inputLine(M);
    // 확인
    int result = search();
    // 출력
    cout << result << "\n";
}