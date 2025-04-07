#include <iostream>
#include <vector>
#include <map>
#include <stack>
#include <algorithm>
#include <climits>
using namespace std;
/*
* 1311 할 일 정하기 1
* 근데 이제 비트연산으로
* 
* N 명의 사람, N 개의 일
* 각각 하나씩 일해야함
* 근데 일할 때 드는 비용이 다름
* 이걸 최소로
* 
* K+1개의 일 최소비용 = K 개의 일 최소비용 + 새로운 일 최소비용
* 안되는데..?
* 
* 3
* 1 2 3
* 2 100 1
* 3 1 100
* 
* 이걸 못찾음;
* 
* 입력: 행렬 크기(N: 20x20)
* 최대: 20x20x20 인듯
* 
*/

void solution(int N, vector<vector<int>>& work)
{
	vector<int> dp(N + 1, INT_MAX);
	dp[0] = 0;
	for (int dpIdx = 1;dpIdx <= N;dpIdx++)
	{
		for (int workerIdx = 1;workerIdx <= N;workerIdx++)
		{
			for (int workIdx = 1;workIdx <= N;workIdx++)
			{
				int task = work[workerIdx - 1][workIdx - 1];
				if (dp[dpIdx - 1] + task < dp[dpIdx])
				{
					dp[dpIdx] = dp[dpIdx - 1] + task; 
				}
			}
		}
	}

	cout << dp[N];
}

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int N;
	cin >> N;
	vector<vector<int>> work(N, vector<int>(N));
	for (int i = 0;i < N;i++)
	{
		for (int j = 0;j < N;j++)
		{
			cin >> work[i][j];
		}
	}

	solution(N, work);
	return 0;
}
