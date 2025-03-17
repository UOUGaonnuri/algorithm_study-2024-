#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
#define TrueMan 0

int findRoot(int idx, vector<int>& unions)
{
	if (unions[idx] == idx)
		return idx;
	return unions[idx] = findRoot(unions[idx], unions);
}

void unionFind(int p1, int p2, vector<int>& unions)
{
	int iRoot = findRoot(p1, unions);
	int jRoot = findRoot(p2, unions);

	if (iRoot != jRoot)
	{
		// 값이 낮은 루트로 선택
		if (iRoot < jRoot)
		{
			unions[jRoot] = iRoot;
		}
		else
		{
			unions[iRoot] = jRoot;
		}
	}
}

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int N, M;
	cin >> N >> M;
	vector<int> unions(N + 1);
	for (int i = 1; i <= N; i++) {
		unions[i] = i;  // 초기화: 사람 자기 자신이 루트
	}
	int trueMans;
	cin >> trueMans;
	for (int i = 1;i <= trueMans;i++)
	{
		int trueMan;
		cin >> trueMan;
		unions[trueMan] = TrueMan;
	}

	int result = 0;
	vector<int> firstAttendees(M);
	for (int i = 0;i < M;i++)
	{
		int AttendanceCount;
		cin >> AttendanceCount;

		// 첫 번째 사람을 기준으로 파티 별로 그룹화
		int firstAttendee;
		cin >> firstAttendee;
		firstAttendees[i] = firstAttendee;
		for (int j = 1;j < AttendanceCount;j++)
		{
			int Attendee;
			cin >> Attendee;
			unionFind(firstAttendee, Attendee, unions);
		}
	}

	// 다 하고 루프 돌리는 이유: 트루먼쇼에 참가하면 모두가 트루먼이됨
	for (int i = 0;i < M;i++)
	{
		int partyRoot = findRoot(firstAttendees[i], unions);
		if (partyRoot != TrueMan)
		{
			result++;
		}
	}


	cout << result;
	return 0;
}
