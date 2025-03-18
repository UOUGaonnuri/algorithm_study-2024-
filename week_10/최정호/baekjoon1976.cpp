// 골드 4 - 1976번 : 여행 가자
// 작성자 : free4760(jeonghoe22)

#include <iostream>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;

vector<int> g_parents;

// 부모 찾기
int find_parent(const int idx)
{
    // 본인 노드가 이미 부모인 경우
    if(g_parents[idx] == idx) return idx;

    // 해당 도시의 부모가 최상단이 아닌 경우에는, 추가로 설정 - 시간 단축
    g_parents[idx] = find_parent(g_parents[idx]);
    return g_parents[idx];
}

// 연결 정보 합치기
void union_city(const int a, const int b)
{
    int p_1 = find_parent(a);
    int p_2 = find_parent(b);

    // 서로 이미 연결되어 있으면, 무시
    if(p_1 == p_2) return;

    // 아닌 경우에는 연결
    g_parents[max(p_1, p_2)] = min(p_1, p_2);
}

// 두 도시로 이동이 가능한지 확인
bool search(const int a, const int b)
{
    return find_parent(a) == find_parent(b);
}

int main()
{
    // 입출력 최적화
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    // 도시 수 N, 여행 계획 속한 도시 M
    int N, M;
    cin >> N >> M;

    // 각 도시별로 부모 초기화
    g_parents = vector<int>(N+1);
    for(int i = 0; i < N+1; i++)
    {
        g_parents[i] = i;
    }

    // 도시 연결 정보 입력
    int connect;
    for(int city = 1; city <= N; city++)
    {
        for(int other = 1; other <= N; other++)
        {
            cin >> connect;

            // 연결되어 있다고 입력하는 경우에만, 연결
            if(connect == 1) union_city(city, other);
        }
    }

    // 여행 계획 입력 및 검사
    bool state = true;
    int seq = 0;
    int parent = -1;
    for(int i = 0; i < M; i++)
    {
        cin >> seq;

        // 제일 처음 검사
        if(parent == -1)
        {
            parent = find_parent(seq);
        }
        // 여행하지 못하는 경우
        else if(parent != find_parent(seq))
        {
            state = false;
            break;
        }
    }

    string result = state ? "YES" : "NO";
    cout << result << "\n";
}