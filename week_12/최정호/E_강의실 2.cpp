// 골드 3 - 1379번 : 강의실 2
// 작성자 : free4760(jeonghoe22)

#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

// N개의 강의
// 최대한 적은 수의 강의실 -> 모든 강의
// 입력 : 강의 번호, 시작 시간, 종료 시간

// 1. 한 강의실에서는 1개 강의만 진행
// 2. 종료시간과 다른 강의의 시작 시간 겹치는 것은 상관 x

// 출력
// 필요한 강의실 최소 개수 K개
// 둘째 줄부터 N줄 걸쳐서 배정된 강의실 번호 출력

// 1. 강의를 시작 시간 기준 오름차순 정렬하고 강의 우선순위 큐에 넣기
// 2. 강의실 우선순위 큐에 사용 가능한 강의실이 있는 경우, 해당 강의실 사용
// 3. 강의실 우선순위 큐에 사용 가능한 강의실이 없는 경우, 강의실 추가

struct Lecture
{
    int number = 0;
    int start = 0;
    int end = 0;
    int room = 0;

    // 우선순위 큐 - 시작 시간 기준 오름차순으로 정렬
    bool operator<(const Lecture& other) const
    {
        return start > other.start;
    }
};

struct Room
{
    int number = 0;
    int end = 0;

    // 우선순위 큐 - 강의실에 대한 종류 시간 기준 오름차순으로 정렬
    bool operator<(const Room& other) const
    {
        return end > other.end;
    }
};

vector<Lecture> g_lectures;
priority_queue<Lecture> g_pq;

// 강의 정보 입력 및 우선순위 큐에 추가
void initLecture(const int N)
{
    g_lectures = vector<Lecture>(N);

    for(Lecture& lecture : g_lectures)
    {
        cin >> lecture.number >> lecture.start >> lecture.end;
        g_pq.push(lecture);
    }

    // 벡터에 있는 강의 정보는 강의 번호 순으로 정렬
    sort(g_lectures.begin(), g_lectures.end(), [](const Lecture& a, const Lecture& b)
    {
        return a.number < b.number;
    });
}

// 강의실 시뮬레이션
int simulate()
{
    priority_queue<Room> pq_room;
    int room_cnt = 0;

    // 강의에 대해서 모두 검사
    while(g_pq.empty() == false)
    {   
        Lecture lecture = g_pq.top();
        g_pq.pop();

        bool b_add_room = false;
        // 사용할 수 있는 강의실이 있는지 확인
        Room room;
        // 1. 비어 있으면 추가
        if(pq_room.empty()) b_add_room = true;
        // 2. 사용 가능한 강의실이 없음.
        else
        {
            room = pq_room.top();
            // 강의실에 있는 강의 종료 시간보다 강의 시작 시간이 더 적으면, 강의실 추가
            if(room.end > lecture.start) b_add_room = true;
        }

        // 강의실 추가
        if(b_add_room)
        {
            room_cnt++;
            pq_room.push({room_cnt, lecture.end});
            g_lectures[lecture.number-1].room = room_cnt;
        }
        // 기존 강의실에서 강의 진행
        else
        {
            pq_room.pop();
            room.end = lecture.end;
            pq_room.push(room);

            g_lectures[lecture.number-1].room = room.number;
        }
    }

    return room_cnt;
}

// 결과 출력
void printResult(const int K)
{
    cout << K << "\n";
    for(const Lecture& lecture : g_lectures)
    {
        cout << lecture.room << "\n";
    }
}

int main()
{
    // 입출력 최적화
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    // 강의 수 N
    int N;
    cin >> N;

    // 강의 정보 입력 및 우선순위 큐에 추가
    initLecture(N);

    // 강의실 시뮬레이션
    int K = simulate();

    // 결과 출력
    printResult(K);
}