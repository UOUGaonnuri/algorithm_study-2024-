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

bool unionFind(int p1, int p2, vector<int>& unions)
{
	bool bIsCycle = false;

	int iRoot = findRoot(p1, unions);
	int jRoot = findRoot(p2, unions);

	if (iRoot != jRoot)
	{
		unions[jRoot] = iRoot;
	}
	else
	{
		bIsCycle = true;
	}

	return bIsCycle;
}

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int N, M;
	cin >> N >> M;
	vector<int> unions(N);
	for (int i = 0; i < N; i++) {
		unions[i] = i;  // 초기화: 자기 자신이 루트
	}

	int result = 0;
	for (int i = 1;i <= M;i++)
	{
		int p1, p2;
		cin >> p1 >> p2;

		bool bIsCycle = unionFind(p1, p2, unions);
		if (bIsCycle)
		{
			result = i;
			break;
		}
	}

	cout << result;
	return 0;
}
