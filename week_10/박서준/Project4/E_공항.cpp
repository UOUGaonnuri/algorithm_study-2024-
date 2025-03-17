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

void unionFind(int gate, vector<int>& unions)
{
	int iRoot = findRoot(1, unions);
	int gateRoot = findRoot(gate, unions);

	if (iRoot != gateRoot)
	{
		// 값이 낮은 루트로 선택
		if (iRoot < gateRoot)
		{
			unions[gateRoot] = iRoot;
		}
		else
		{
			unions[iRoot] = gateRoot;
		}
	}
}

bool docking(int gate, vector<int>& unions)
{
	bool bSuccess = false;
	int gateRoot = findRoot(gate, unions);

	if (gateRoot == 0)
	{
		unions[gate] = gate;
		bSuccess = true;
	}
	else
	{
		for (int i = gateRoot - 1;i > 0;--i)
		{
			int nextRoot = findRoot(i, unions);
			if (nextRoot == 0)
			{
				unions[gateRoot] = i;
				unions[i] = i;
				bSuccess = true;
				break;
			}
		}
	}

	return bSuccess;
}
int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int G, P;
	cin >> G >> P;
	vector<int> unions(G + 1);
	int result = 0;
	for (int i = 1;i <= P;i++)
	{
		int gate;
		cin >> gate;
		bool bSuccess = docking(gate, unions);
		if (bSuccess == false)
		{
			break;
		}
		result++;
	}

	cout << result;
	return 0;
}
