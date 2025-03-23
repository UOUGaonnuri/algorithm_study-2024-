#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
#define None 0

int findRoot(int idx, vector<int>& unions)
{
	if (unions[idx] == idx)
		return idx;
	return unions[idx] = findRoot(unions[idx], unions);
}

void unionCity(int i, int j, vector<int>& unions)
{
	int iRoot = findRoot(i, unions);
	int jRoot = findRoot(j, unions);

	if (iRoot != jRoot)
	{
		unions[jRoot] = iRoot;
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
		unions[i] = i;  // 초기화: 모든 도시는 자기 자신이 루트
	}

	for (int i = 1;i <= N;i++)
	{
		for (int j = 1;j <= N;j++)
		{
			bool bIsConnected;
			cin >> bIsConnected;
			// 사선을 경계로 한쪽 면만 보면 되기 때문에 i 부터 시작
			if (j < i) continue;
			// 연결된 경우, i,j를 하나의 그룹으로 묶는다.
			if (bIsConnected)
			{
				unionCity(i, j, unions);
			}
		}
	}
	int myPlan = None;
	for (int i = 0;i < M;i++)
	{
		int planIdx;
		cin >> planIdx;

		int plan = findRoot(planIdx, unions);
		if (myPlan == None)
		{
			myPlan = plan;
		}
		else if (myPlan != plan)
		{
			cout << "NO";
			return 0;
		}
	}

	cout << "YES";
	return 0;
}
