// 골드 3 - 1833번 : 고속철도 설계하기
// 작성자 : free4760(jeonghoe22)

#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <utility>

using namespace std;

bool union_city(const int x, const int y, const bool b_ignore_cycle);

// 연결 비용 정보 구조체
struct Line
{
    int x = 0;
    int y = 0;
    int cost = 0;

    // 우선순위 큐에서 비용 기준 오름차순
    bool operator<(const Line& other) const
    {
        return cost > other.cost;
    }
};

vector<int> g_parent;
priority_queue<Line> g_pq;
vector<pair<int ,int>> g_add_lines;
int g_cost = 0;

// 초기화
void initCity(const int N)
{
    g_parent = vector<int>(N+1);
    for(int i = 0; i < N+1; i++)
    {
        g_parent[i] = i;
    }
}

// 연결 비용 입력
void inputConnect(const int N)
{
    int cost;
    for(int i = 1; i <= N; i++)
    {
        for(int j = 1; j <= N; j++)
        {
            cin >> cost;

            // 중복을 방지하기 위해, 절반만 진행
            if(i < j == false) continue;

            // 음수인 경우 - 이미 연결되어 있음
            if(cost < 0)
            {
                union_city(i, j, true);
                g_cost += cost;
            }
            // 양수인 경우 - 추가로 놓을 수 있는 선로
            else
            {
                g_pq.push(Line{i, j, cost});
            }
        }
    }
}

// 부모 찾기
int find_parent(const int idx)
{
    if(idx == g_parent[idx]) return idx;

    g_parent[idx] = find_parent(g_parent[idx]);
    return g_parent[idx];
}

// 두 도시 가능 여부를 확인하고, 연결하기
bool union_city(const int x, const int y, const bool b_ignore_cycle)
{
    int px = find_parent(x);
    int py = find_parent(y);

    // 사이클 발생했으니, 무시
    // 단, 사이클을 발생해도 상관없는 경우(이미 연결되어 있는 선)에는 다음으로 진행
    if(px == py && (b_ignore_cycle == false)) return false;

    g_parent[max(px, py)] = min(px, py);
    return true;
}

// 도시 사이의 선로를 연결할 수 있는지 확인하고 최소값 반환
int search()
{
    int result = 0;
    while(g_pq.empty() == false)
    {
        Line line = g_pq.top();
        g_pq.pop();

        // 선로를 연결가능한 경우에만, 연결
        if(union_city(line.x, line.y, false))
        {
            result += line.cost;
            g_add_lines.push_back({line.x, line.y});
        }
    }
    return result;
}

int main()
{
    // 입출력 최적화
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    // 도시 개수
    int N;
    cin >> N;

    // 도시 정보 초기화
    initCity(N);

    // 연결 비용 입력
    inputConnect(N);

    g_cost *= -1;
    g_cost += search();

    cout << g_cost << " " << g_add_lines.size() << "\n";
    for(auto& line : g_add_lines)
    {
        cout << line.first << " " << line.second << "\n";
    }
}