#include <iostream>
#include <vector>
#include <stack>
#include <algorithm>
using namespace std;
struct RailInfo
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

void solution(int N, vector<vector<int>>& railCosts)
{
	int totalCost = 0;
	vector<int> unions(N + 1);
	for (int i = 1;i <= N;i++)
	{
		unions[i] = i;
	}

	int maxRails = N - 1;
	vector<RailInfo> railInfos;
	for (int i = 1;i <= N;i++)
	{
		for (int j = i + 1;j <= N;j++)
		{
			int railCost = railCosts[i][j];
			if (railCost < 0)
			{
				int aRoot = findRoot(unions, i);
				int bRoot = findRoot(unions, j);

				unions[max(aRoot, bRoot)] = min(aRoot, bRoot);
				totalCost -= railCost;
				// 사이클 아니면 최대개수 감소
				if (aRoot != bRoot)
					maxRails--;				
			}
			else
			{
				RailInfo railInfo;
				railInfo.a = i;
				railInfo.b = j;
				railInfo.v = railCosts[i][j];
				railInfos.push_back(railInfo);
			}	
		}
	}

	sort(railInfos.begin(), railInfos.end(),
		[](const RailInfo& a, const RailInfo& b)
		{
			return a.v < b.v;
		});

	int newRails = 0;
	vector<RailInfo> newRailInfos;
	for (const RailInfo& railInfo : railInfos)
	{
		if (newRails >= maxRails) break;

		int aRoot = findRoot(unions, railInfo.a);
		int bRoot = findRoot(unions, railInfo.b);

		//사이클이면 스킵
		if (aRoot == bRoot) continue;

		unions[max(aRoot, bRoot)] = min(aRoot, bRoot);
		totalCost += railCosts[railInfo.a][railInfo.b];
		newRailInfos.push_back(railInfo);
		newRails++;
	}

	cout << totalCost << ' ' << newRails << '\n';
	for (const RailInfo& railInfo : newRailInfos)
	{
		cout << railInfo.a << ' ' << railInfo.b << '\n';
	}
	return;
}

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int N;
	cin >> N;
	vector<vector<int>> railCosts(N + 1, vector<int>(N + 1));
	for (int i = 1;i <= N;i++)
	{
		for (int j = 1;j <= N;j++)
		{
			cin >> railCosts[i][j];
		}
	}

	solution(N, railCosts);
	return 0;
}
