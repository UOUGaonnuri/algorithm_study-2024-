#include <iostream>
#include <vector>
#include <stack>
#include <algorithm>
using namespace std;

/*
* 14400 편의점2
* 또리합 최소
* 최소위치는 중간값으로
* 루프 돌면서 차이값 합치면 될듯
* 안되는데?
* 아.. gpt한테 힌트달라하니까 무슨 맨해튼 거리 이러네..다알려주면 어캄
* 
* 입력: n(고객 수): 10^5, x/y좌표: -10^6~10^6
* 최대: 걍 n 돌기
*/
long long solution(int N, vector<pair<int, int>>& pos)
{
	long long answer = 0;
	vector<int> xPos(N);
	vector<int> yPos(N);
	for (int i = 0;i < N;i++)
	{
		xPos[i] = pos[i].first;
		yPos[i] = pos[i].second;
	}
	sort(xPos.begin(), xPos.end(), [](int a, int b) {
		return a < b;
		});
	sort(yPos.begin(), yPos.end(), [](int a, int b) {
		return a < b;
		});
	int mediumIdx = N % 2 == 0 ? N / 2 -1 : N / 2;
	
	pair<int, int> mediumPos = { xPos[mediumIdx], yPos[mediumIdx]};
	for (int i = 0;i < N;i++)
	{
		answer += abs(mediumPos.first - pos[i].first) + abs(mediumPos.second - pos[i].second);
	}

	return answer;
}

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int N;
	cin >> N;
	vector<pair<int, int>> pos(N);
	for (int i = 0;i < N;i++)
	{
		cin >> pos[i].first >> pos[i].second;
	}

	cout << solution(N, pos);
	return 0;
}
