// 골드 1 - 1119번 : 그래프
// 작성자 : free4760(jeonghoe22)

#include <iostream>
#include <vector>
#include <algorithm>
#include <set>
#include <string>

using namespace std;

// 제거 가능한 간선 1개 당 -> 그룹 한개 + 1 커버 가능

vector<int> g_parents;
set<int> g_groups;

// 부모 노드 찾기
int find_parent(const int idx)
{
    // 부모가 본인이면, 그냥 반환
    if(g_parents[idx] == idx) return idx;

    // 부모가 본인이 아니라면, 가장 높은 부모를 찾고 반환
    g_parents[idx] = find_parent(g_parents[idx]);
    return g_parents[idx];
}

// 연결
int union_city(const int a, const int b)
{
    int p_1 = find_parent(a);
    int p_2 = find_parent(b);

    // 서로 연결되어 있음 - 제거해도 되는 간선 1개
    if(p_1 == p_2) return 1;

    // 연결되어 있지 않음
    g_parents[max(p_1, p_2)] = min(p_1, p_2);
    return 0;
}

// 그룹별로 찾기
int search_group()
{
    // 그룹별로 가장 최상단 부모만 넣기
    int size = g_parents.size();
    for(int i = 1; i < size; i++)
    {
        g_groups.insert(find_parent(i));
    }

    return g_groups.size();
}

int main()
{
    // 입출력 최적화
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    // 도시 N개
    int N;
    cin >> N;

    // 도시 정보 초기화
    g_parents = vector<int>(N+1);
    for(int i = 0; i < N+1; i++)
    {
        g_parents[i] = i;
    }

    // 도시 연결 정보 입력
    string connects;
    int removable_count = 0;
    bool b_always_not_found = false;
    for(int i = 1 ; i <= N; i++)
    {
        cin >> connects;

        bool b_isolate = true;
        for(int j = 1; j <= N; j++)
        {
            // 연결되어 있지 않으면, 무시
            if(connects[j-1] == 'N') continue;

            b_isolate = false;
            // i+1번째(절반)부터 연결 시도해도 됨
            if(i < j)
            {
                // 연결 시도
                removable_count += union_city(i, j);
            }
        }
        // 고립된 도시가 있음을 알림
        if(b_isolate) b_always_not_found = true;
    }

    // 그룹 개수 구하기
    int group_count = search_group();
    
    // 제거 가능한 간선으로 그룹 수를 모두 커버하지 못한다면, 연결 불가능
    int result = -1;
    if(b_always_not_found == false || N == 1) result = group_count <= (removable_count+1) ? group_count-1 : -1;
    cout << result << "\n";
}