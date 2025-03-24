#include <iostream>
#include <vector>
#include <stack>
#include <algorithm>
using namespace std;
struct NetInfo
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

int solution(int N, int M, vector<NetInfo>& netInfos)
{
	int answer = 0, netCounts = 0;
	vector<int> computers(N + 1);

	for (int i = 1;i <= N;i++)
	{
		computers[i] = i;
	}

	sort(netInfos.begin(), netInfos.end(), [](const NetInfo& a, const NetInfo& b)
		{
			return a.v < b.v;
		});


	for (NetInfo info : netInfos)
	{
		if (netCounts >= N - 1)
		{
			break;
		}

		int aRoot = findRoot(computers, info.a);
		int bRoot = findRoot(computers, info.b);

		// 사이클이면 스킵
		if (aRoot == bRoot) continue;

		computers[max(aRoot, bRoot)] = min(aRoot, bRoot);
		netCounts++;
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
	vector<NetInfo> netInfos;
	for (int i = 0;i < M;i++)
	{
		NetInfo netInfo;
		cin >> netInfo.a >> netInfo.b >> netInfo.v;
		netInfos.push_back(netInfo);
	}

	cout << solution(N, M, netInfos);
	return 0;
}
