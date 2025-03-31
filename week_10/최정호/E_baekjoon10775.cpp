// 골드 2 - 10775번 : 공항
// 작성자 : free4760(jeonghoe22)

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

// {1} {2} {3} {4}
// -> 4의 부모를 3 가리키기
// {1} {2} {3,4}
// -> 1의 부모를 0 가리키기
// {1} {2} {3,4}
// 다시 부모가 0이면, 접근 불가능

vector<int> g_parents;

// 부모 찾기
int find_parent(const int idx)
{
    if(idx == g_parents[idx]) return idx;

    // 현재 최상단의 부모 찾기
    g_parents[idx] = find_parent(g_parents[idx]);
    return g_parents[idx];
}

// 앞에와 서로 합치기
void union_set(const int idx)
{
    int parent = find_parent(idx);

    g_parents[parent] = parent-1;
}

int main()
{
    // 입출력 최적화
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
    // 게이트 수 G, 비행기 수 P
    int G, P;
    cin >> G >> P;

    // 부모 가리키는 노드 정보 초기화
    g_parents = vector<int>(G+1, 0);
    for(int i = 0; i < G+1; i++)
    {
        g_parents[i] = i;
    }

    bool b_continue = true;
    int result = 0, plane = 0;
    for(int i = 0; i < P; i++)
    {
        cin >> plane;

        // 0이 아닐 경우, 사용 가능한 게이트가 있음.
        if(find_parent(plane) != 0 && b_continue)
        {
            result++;
            union_set(plane);
        }
        // 0일 경우, 사용 가능한 게이트가 없음.
        else
        {
            b_continue = false;;
        }
    } 
    cout << result << "\n";
}