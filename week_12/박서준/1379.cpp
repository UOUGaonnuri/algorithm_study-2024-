#include <iostream>
#include <queue>
#include <vector>
#include <map>
#include <algorithm>
using namespace std;
/*
* 1379 강의실
* 
* 강의실 압축
* 어디서 많이보던?
* 
* 입력: 강의수(10^5), 10억시간..
* 최대: 
* 
*/
struct Lecture
{
	int num;
	long long startTime;
	long long endTime;
};

struct cmp
{
	bool operator()(Lecture a, Lecture b)
	{
		// 종료시간이 같으면 시작시간으로 비교
		if (a.endTime == b.endTime)
		{
			return a.startTime > b.startTime;
		}
		return a.endTime > b.endTime;
	}
};

void solution(int N, vector<Lecture>& inLectures)
{
	priority_queue<Lecture, vector<Lecture>, cmp> lecturesPQ;
	for (const Lecture& lecture : inLectures)
	{
		lecturesPQ.push(lecture);
	}

	vector<vector<Lecture>> rooms;
	map<int, int> roomHash;

	// 시간대 체크.
	while (lecturesPQ.size() > 0) {
		bool bNeedMoreRoom = true;
		Lecture lecture = lecturesPQ.top();
		lecturesPQ.pop();

		for (int roomIdx = 0;roomIdx < rooms.size();roomIdx++)
		{
			vector<Lecture> room = rooms[roomIdx];
			Lecture lastLecture = room[room.size() - 1];
			if (lastLecture.endTime <= lecture.startTime)
			{
				roomHash[lecture.num] = roomIdx + 1;
				room.push_back(lecture);
				bNeedMoreRoom = false;
				break;
			}
		}
		if (bNeedMoreRoom)
		{
			vector<Lecture> newRoom;
			newRoom.push_back(lecture);
			rooms.push_back(newRoom);

			roomHash[lecture.num] = rooms.size();
		}
	}

	cout << rooms.size() << '\n';
	for (const Lecture& lecture : inLectures)
	{
		cout << roomHash[lecture.num] << '\n';
	}

	return;
}

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int N;
	cin >> N;
	vector<Lecture> lectures;
	for (int i = 0;i < N;i++)
	{
		Lecture lecture;
		cin >> lecture.num >> lecture.startTime >> lecture.endTime;
		lectures.push_back(lecture);
	}

	solution(N, lectures);

	return 0;
}
