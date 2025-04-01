#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int findRoot(int idx, vector<int>& unions)
{
	if (unions[idx] == idx)
		return idx;
	return unions[idx] = findRoot(unions[idx], unions);
}

void unionFind(int i, int j, vector<int>& unions)
{
	int iRoot = findRoot(i, unions);
	int jRoot = findRoot(j, unions);

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

	int N;
	cin >> N;
	vector<int> unions(N + 1);
	for (int i = 1;i <= N;i++)
	{
		unions[i] = i;
	}
	int result = -1;
	for (int i = 1;i <= N;i++)
	{
		string input;
		cin >> input;
		for (int j = i;j <= N;j++)
		{
			if (input[j - 1] == 'Y')
			{
				unionFind(i, j, unions);
			}
		}
	}

	cout << result;
	return 0;
}
