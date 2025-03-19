// 골드 4 - 1922번 : 네트워크 연결
// 작성자 : free4760(jeonghoe22)

#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

// 선 구조체
struct Line
{
    int x = 0;
    int y = 0;
    int cost = 0;

    // 우선순위 큐에서 오름차순으로 정렬
    bool operator<(const Line& other) const
    {
        return cost > other.cost;
    }
};

vector<int> g_parents;
priority_queue<Line> g_pq;

// 부모 노드에 대한 초기화
void initParents(const int N)
{
    g_parents = vector<int>(N+1);
    for(int i = 0; i < N+1; i++)
    {
        g_parents[i] = i;
    }
}

// 선의 정보를 입력
void inputLines(const int M)
{
    for(int i = 0; i < M; i++)
    {
        Line line;
        cin >> line.x >> line.y >> line.cost;
        g_pq.push(line);
    }
}

// 부모 노드 찾기
int find_parent(const int idx)
{
    if(g_parents[idx] == idx) return idx;

    // 최상단의 부모가 아닌 경우, 초기화
    g_parents[idx] = find_parent(g_parents[idx]);
    return g_parents[idx];
}

// 간선을 사용할지 말지 판단
bool union_computer(const int x, const int y)
{
    int p1 = find_parent(x);
    int p2 = find_parent(y);

    // 사이클 발생 시, 간선 사용 x
    if(p1 == p2) return false;

    // 간선 사용으로 컴퓨터 연결
    g_parents[max(p1, p2)] = min(p1, p2);
    return true;
}

// 선 검사
int search()
{
    int total_cost = 0;
    // 비용이 작은 선부터 검사
    while(g_pq.empty() == false)
    {
        Line line = g_pq.top();
        g_pq.pop();

        // 연결 가능
        if(union_computer(line.x, line.y))
        {
            total_cost += line.cost;
        }
    }

    return total_cost;
}

int main()
{
    // 입출력 최적화
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    // 컴퓨터 수 N, 선 수 M
    int N, M;
    cin >> N >> M;

    // 부모 노드에 대한 초기화
    initParents(N);

    // 선의 정보를 입력
    inputLines(M);

    // 모든 간선에 대해 사용 여부를 검사
    int result = search();

    cout << result << "\n";
}