#include <iostream>
#include <vector>
#include <stack>
#include <algorithm>
using namespace std;
struct RoadInfo
{
	int a;
	int b;
	int v;
};

int findRoot(vector<int>& unions, int idx)
{
	if (unions[idx] == idx)
		return idx;
	return unions[idx] = findRoot(unions, unions[idx]);
}

void unionFind(vector<int>& unions, int a, int b)
{
	int aRoot = findRoot(unions, a);
	int bRoot = findRoot(unions, b);

	unions[max(aRoot, bRoot)] = min(aRoot, bRoot);
}

int solution(int N, int M, vector<RoadInfo>& roadInfos)
{
	int answer = 0, roadCounts = 0;
	vector<int> houses(N + 1);

	for (int i = 1;i <= N;i++)
	{
		houses[i] = i;
	}

	sort(roadInfos.begin(), roadInfos.end(), [](const RoadInfo& a, const RoadInfo& b)
		{
			return a.v < b.v;
		});


	for (RoadInfo info : roadInfos)
	{
		// 마을 나누고, 최소 연결된 개수
		if (roadCounts >= N - 2)
		{
			break;
		}

		int aRoot = findRoot(houses, info.a);
		int bRoot = findRoot(houses, info.b);

		// 사이클이면 스킵
		if (aRoot == bRoot) continue;

		houses[max(aRoot, bRoot)] = min(aRoot, bRoot);
		roadCounts++;
		answer += info.v;
	}

	return answer;
}

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int N, M;
	cin >> N >> M;
	vector<RoadInfo> roadInfos;
	for (int i = 0;i < M;i++)
	{
		RoadInfo roadInfo;
		cin >> roadInfo.a >> roadInfo.b >> roadInfo.v;
		roadInfos.push_back(roadInfo);
	}

	cout << solution(N, M, roadInfos);
	return 0;
}
