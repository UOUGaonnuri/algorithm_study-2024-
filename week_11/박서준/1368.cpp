#include <iostream>
#include <vector>
#include <stack>
#include <algorithm>
using namespace std;
struct WaterInfo
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

int solution(int N, vector<int>& digCosts, vector<vector<int>>& unionCosts)
{
	int answer = 0;
	vector<int> unions(N + 1);
	for (int i = 1;i <= N;i++)
	{
		unions[i] = i;
	}
	vector<WaterInfo> waterInfos;
	for (int i = 1;i <= N;i++)
	{
		for (int j = i + 1;j <= N;j++)
		{
			WaterInfo waterInfo;
			waterInfo.a = i;
			waterInfo.b = j;
			waterInfo.v = unionCosts[i][j];
			waterInfos.push_back(waterInfo);
		}
	}

	sort(waterInfos.begin(), waterInfos.end(), 
		[](const WaterInfo& a, const WaterInfo& b)
		{
			return a.v < b.v;
		});

	for (const WaterInfo& waterInfo : waterInfos)
	{
		int aRoot = findRoot(unions, waterInfo.a);
		int bRoot = findRoot(unions, waterInfo.b);

		//사이클이면 스킵
		if (aRoot == bRoot) continue;

		// 연결이 더 싸면
		int cheaperRoot = digCosts[aRoot] < digCosts[bRoot] ? aRoot : bRoot;
		int shitRoot = digCosts[aRoot] < digCosts[bRoot] ? bRoot : aRoot;
		if(waterInfo.v  < digCosts[shitRoot])
		{
			// 루트를 땅 파기 싼 친구로 설정
			unions[shitRoot] = cheaperRoot;

			answer += waterInfo.v;
		}
	}

	// 땅 파기
	for (int i = 1;i <= N;i++)
	{
		// 루트들만
		int unionRoot = findRoot(unions, i);
		if (i == unionRoot)
		{
			answer += digCosts[unionRoot];
		}
	}

	return answer;
}

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int N;
	cin >> N;
	vector<int> digCosts(N + 1);
	vector<vector<int>> unionCosts(N + 1, vector<int>(N + 1));
	for (int i = 1;i <= N;i++)
	{
		cin >> digCosts[i];
	}
	for (int i = 1;i <= N;i++)
	{
		for (int j = 1;j <= N;j++)
		{
			cin >> unionCosts[i][j];
		}
	}

	cout << solution(N, digCosts, unionCosts);
	return 0;
}
