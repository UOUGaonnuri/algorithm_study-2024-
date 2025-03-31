// 골드 3 - 1414번 : 불우이웃돕기
// 작성자 : free4760(jeonghoe22)

#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

// 랜선 정보 구조체
struct Line
{
    int x = 0;
    int y = 0;
    int cost = 0;

    // 우선순위 큐에 비용 기준의 오름차순을 적용
    bool operator<(const Line& other) const
    {
        return cost > other.cost;
    }
};

vector<int> g_coms;
priority_queue<Line> g_pq;

// 컴퓨터 정보 초기화
void initComputers(const int N)
{
    g_coms = vector<int>(N+1);
    for(int i = 0; i < N+1; i++)
    {
        g_coms[i] = i;
    }
}

// 문자열을 숫자로 변환
int changeChar(const char c)
{
    int num = static_cast<int>(c);
    // 대문자
    if(num < 97)
    { 
        num -= 'A';
        num += 27;
    }
    // 소문자
    else
    { 
        num -= 'a'; 
        num += 1;
    }

    return num;
}

// 랜선 정보 입력
int inputLine(const int N)
{
    int total_lines = 0;
    char line;
    for(int i = 1; i <= N; i++)
    {
        for(int j = 1; j <=N; j++)
        {
            cin >> line;
            // 0인 경우는 무시
            if(line == '0') continue;

            int i_line = changeChar(line);

            total_lines += i_line;

            // 같은 컴퓨터의 랜선인 경우에는 검사하지 않아도 됨
            if(i == j) continue;

            g_pq.push(Line{min(i,j), max(i,j), i_line});
        }
    }

    return total_lines;
}

// 연결된 컴퓨터 중, 가장 작은 번호의 컴퓨터 찾기
int findComputer(const int idx)
{
    if(idx == g_coms[idx]) return idx;

    g_coms[idx] = findComputer(g_coms[idx]);
    return g_coms[idx];
}

// 컴퓨터끼리 연결하기 시도
bool connectComputer(const int x, const int y)
{
    int px = findComputer(x);
    int py = findComputer(y);

    // 같은 그룹 내에 존재, 무시
    if(px == py) return false;

    g_coms[max(px, py)] = min(px, py);
    return true;
}

// 컴퓨터끼리 짧은 랜선으로 연결
int search()
{
    int use = 0;
    while(g_pq.empty() == false)
    {
        Line line = g_pq.top();
        g_pq.pop();

        // 연결 성공
        if(connectComputer(line.x, line.y))
        {
            use += line.cost;
        }
    }

    return use;
}

// 모두 같은 그룹에 연결되어 있는지 확인
bool checkAllConnect()
{
    int size = g_coms.size();
    int parent = g_coms[1];

    for(int i = 1; i < size; i++)
    {
        // findComputer로 한 번 더 찾아줘야, 최상단의 부모로 보장이 됨
        if(parent != findComputer(i)) return false;
    }
    return true;
}

int main()
{
    // 입출력 최적화
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    // 컴퓨터 개수 N
    int N;
    cin >> N;

    // 컴퓨터 정보 초기화
    initComputers(N);
    // 랜선 입력
    int result = inputLine(N);

    // 컴퓨터끼리 짧은 랜선으로 연결
    result -= search();

    // 모두 같은 그룹에 연결되어 있는지 확인
    if(checkAllConnect() == false) result = -1;

    cout << result << "\n";
}