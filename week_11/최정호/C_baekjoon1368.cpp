// 골드 2 - 1368번 : 물대기
// 작성자 : free4760(jeonghoe22)

#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

// 논의 수가 N이지만, 우물도 하나의 논으로 판단
// 1번 논의 우물 N+1번
// 2번 논의 우물 N+2번
// boolean으로 물의 존재 여부를 판단
// 존재 시, 무시

// 간선 정보
struct Line
{
    int x = 0;
    int y = 0;
    int cost = 0;

    // 우선순위 큐에서 오름차순
    bool operator<(const Line& other) const
    {
        return cost > other.cost;
    }
};

// 논 정보
struct Non
{
    int parent = 0;
    bool b_water = false;
};

vector<Non> g_parents;
priority_queue<Line> g_pq;

// 논 정보 초기화
void initNon(const int N)
{
    int size = N * 2 + 1;
    g_parents = vector<Non>(size);

    for(int i = 0; i < size; i++)
    {
        g_parents[i].parent = i;
    }
}

// 우물 비용에 대한 정보 입력
void inputUnderWater(const int N)
{
    int cost;
    for(int i = 1; i <= N; i++)
    {
        cin >> cost;
        g_pq.push(Line{i, N+i, cost});
    }
}

// 부모 찾기
int find_parent(const int idx)
{
    // 자기 자신인 경우, 바로 반환
    if(g_parents[idx].parent == idx) return idx;

    g_parents[idx].parent = find_parent(g_parents[idx].parent);
    return g_parents[idx].parent;
}

// 논 연결 가능 여부를 확인하고, 가능하다면 연결
bool union_non(const int x, const int y, const int N)
{
    int px = find_parent(x);
    int py = find_parent(y);

    // 사이클 발생한 경우는 무시
    if(px == py) return false;

    // 이미 물이 있는 경우에는 무시
    // y에만 논이 온다고 가정

    // 우물과 연결하는 경우
    if(y > N)
    {
        // 이미 물이 있으면, 무시
        if(g_parents[px].b_water) return false;

        g_parents[px].b_water = true;
    }
    // 논과 논 연결하는 경우
    else
    {
        int max_p = max(px, py);
        int min_p = min(px, py);
        // 서로 물이 있는 경우, 무시
        if(g_parents[max_p].b_water && g_parents[min_p].b_water) return false;
        
        g_parents[max_p].parent = min_p;
        g_parents[min_p].b_water = (g_parents[max_p].b_water || g_parents[min_p].b_water);
    }

    return true;
}

// 논과 논 연결 정보 입력
void inputLines(const int N)
{
    int cost = 0;
    for(int i = 1; i <= N; i++)
    {
        for(int j = 1; j <= N; j++)
        {
            cin >> cost;

            // 입력은 받지만, 중복 간선에 대한 입력 무시
            if(i < j)
            {
                g_pq.push(Line{i, j, cost});
            }
        }
    }
}

// 논에 물을 대는데 필요한 최소 비용 구하기
int search(const int N)
{
    int result_cost = 0;
    while(g_pq.empty() == false)
    {
        Line line = g_pq.top();
        g_pq.pop();

        // 연결 성공한 경우에만 추가
        if(union_non(line.x, line.y, N))
        {
            result_cost += line.cost;
        }
    }
    return result_cost;
}

int main()
{
    // 입출력 최적화
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    // 논 개수
    int N;
    cin >> N;

    // 논 정보 초기화
    initNon(N);

    // 우물 비용에 대한 정보 입력
    inputUnderWater(N);

    // 논과 논 연결 정보 입력
    inputLines(N);

    // 논에 물을 대는데 필요한 최소 비용 구하기
    int result = search(N);
    cout << result << "\n";
}