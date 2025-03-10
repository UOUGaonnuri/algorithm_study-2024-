#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

// 시간 관리
// 하루에 할 일 N개
// 각 일마다 끝내야할 시간과 걸리는 시간이 주어짐
// 최대한 늦게 일을 시작하는 것

struct Task
{
	int time = 0;
	int deadLine = 0;
};

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int N;
	cin >> N;
	vector<Task> tasks;
	int maxDeadLine = 0;
	for (int i = 0;i < N;i++)
	{
		Task t;
		cin >> t.time >> t.deadLine;

		if (t.deadLine > maxDeadLine)
			maxDeadLine = t.deadLine;

		tasks.push_back(t);
	}
	vector<bool> timeLine(maxDeadLine, false);
	
	sort(
		tasks.begin(), tasks.end(), 
		[](const Task& a, const Task& b)
		{
			return a.deadLine < b.deadLine;
		});
	
	int minTime = maxDeadLine;
	for (Task& task : tasks)
	{
		for (int i = task.deadLine - 1; i >= 0;--i)
		{
			if (task.time <= 0) 
				break;

			if (timeLine[i] == false)
			{
				timeLine[i] = true;
				task.time--;
			}
		}
		if (task.time > 0)
		{
			minTime = -1;
			break;
		}
	}

	for (int i = 0;i < maxDeadLine;i++)
	{
		if (timeLine[i] == true)
		{
			minTime = i < minTime ? i : minTime;
			break;
		}
	}

	cout << minTime;
	return 0;
}
