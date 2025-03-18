// 골드 4 - 1043번 : 거짓말
// 작성자 : free4760(jeonghoe22)

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct Group
{
    vector<int> people;
    int size = 0;
    bool b_state = false;
};

vector<int> g_parents;
vector<bool> g_truth;
vector<Group> g_groups;

// 부모 찾기
int find_parent(const int idx)
{
    if(g_parents[idx] == idx) return idx;

    g_parents[idx] = find_parent(g_parents[idx]);
    return g_parents[idx];
}

// 합치기
void union_group(const int a, const int b)
{
    int p1 = find_parent(a);
    int p2 = find_parent(b);

    // 같은 그룹, 무시
    if(p1 == p2) return;

    // 자식도 진실을 아는 경우, 전파
    if(g_truth[p1] || g_truth[p2])
    {
        g_truth[p1] = true;
        g_truth[p2] = true;
    }

    // 부모 설정
    g_parents[max(p1, p2)] = min(p1, p2);
}

// 그룹별로 입력
void inputGroupInfo(const int M)
{
    int people_num, in_idx;
    g_groups = vector<Group>(M);

    for(int i = 0; i < M; i++)
    {
        // 현재 파티의 인원
        cin >> people_num;
        g_groups[i].size = people_num;

        // 파티원별로 검사
        for(int j = 0; j < people_num; j++)
        {
            cin >> in_idx;
            g_groups[i].people.push_back(in_idx);

            // 이미 진실을 아는 사람이 있는 그룹인 경우
            if(g_truth[in_idx]) g_groups[i].b_state = true;

            // 서로 연결
            if(j != 0) union_group(g_groups[i].people[0], in_idx);
        }
    }
}

// 진실을 알지 못하는 그룹 선별
int searchNotTruth(const int M)
{
    int result = M;
    for(int i = 0; i < M; i++)
    {
        // 이미 진실을 아는 사람이 있는 경우는 무시
        if(g_groups[i].b_state)
        {
            result--;
            continue;
        }
        // 그룹 인원별로 체크
        for(const int& each : g_groups[i].people)
        {
            // 진실을 아는 사람과 같은 파티에 참석한 경우, 제외
            if(g_truth[find_parent(each)])
            {
                result--;
                break;
            }
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

    // 사람 수 N, 파티 수 M
    int N, M;
    cin >> N >> M;

    // 각 번호별로 초기화
    g_parents = vector<int>(N+1);
    g_truth = vector<bool>(N+1, false);
    for(int i = 0; i < N+1; i++)
    {
        g_parents[i] = i;
    }

    // 진실 알고 있는 사람 입력
    int people_num, in_idx;
    cin >> people_num;
    for(int i = 0; i < people_num; i++)
    {
        cin >> in_idx;
        g_truth[in_idx] = true;
    }

    inputGroupInfo(M);
    int result = searchNotTruth(M);

    cout << result << "\n";
}