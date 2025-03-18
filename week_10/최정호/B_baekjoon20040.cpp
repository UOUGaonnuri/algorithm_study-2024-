// 골드 4 - 20040번 : 사이클 게임
// 작성자 : free4760(jeonghoe22)

#include <iostream>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;

vector<int> g_parents;

// 부모 노드 찾기
int find_parent(const int idx)
{
    // 부모 노드가 자신인 경우, 그냥 반환
    if(g_parents[idx] == idx) return idx;

    // 부모가 아닌 경우, 최상단의 부모를 저장하도록 수정 - 시간 단축
    g_parents[idx] = find_parent(g_parents[idx]);
    return g_parents[idx];
}

// 트리끼리 합치기
bool union_set(const int a, const int b)
{
    int p_1 = find_parent(a);
    int p_2 = find_parent(b);

    // 부모가 같으면, 순환 발생 -> 사이클 발생
    if(p_1 == p_2) return true;

    // 부모가 다르면, 서로 연결
    g_parents[max(p_1, p_2)] = min(p_1, p_2);
    return false;
}

int main()
{
    // 입출력 최적화
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    // 점의 개수 n, 차례 수 m
    int n, m;
    cin >> n >> m;

    // 부모 노드를 초기화
    g_parents = vector<int>(n+1);
    for(int i = 0; i < n+1; i++)
    {
        g_parents[i] = i;
    }

    // 연결 정보 입력
    int result = 0;
    int idx1, idx2;
    for(int i = 1; i <= m; i++)
    {
        cin >> idx1 >> idx2;
        
        // 서로 연결하는데, 순환 발생
        if(union_set(idx1, idx2) && result == 0)
        {
            result = i;
        }
    }
    cout << result << "\n";
}