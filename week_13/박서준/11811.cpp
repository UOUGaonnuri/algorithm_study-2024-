#include <iostream>
#include <vector>
#include <map>
#include <stack>
#include <algorithm>
using namespace std;
/*
* 11811 데스스타
* 근데 이제 비트연산으로
* 
* 데스스타 파괴는 길이 N 의 양의 수열 필요
* 행렬에 각 수열을 and 한 결과가 나옴
* 주 대각선? 아니 어차피 다 1일텐데
* 답은 1개 이상
* 
* 그럼 그냥 교집합됐던애들꺼 다 or 해주면 걔의 최소가 나올텐데
* 
* 입력: 행렬 크기(N: 1000x1000)
* 최대: 1000x1000 인듯
* 
*/

void solution(int N, vector<vector<int>>& note)
{
	vector<int> answers(N);
	for (int i = 0;i < N;i++)
	{
		for (int j = 0;j < N;j++)
		{
			answers[i] |= note[i][j];
		}
	}

	for (int i = 0;i < N;i++)
	{
		cout << answers[i] << ' ';
	}
}

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int N;
	cin >> N;
	vector<vector<int>> note(N, vector<int>(N));
	for (int i = 0;i < N;i++)
	{
		for (int j = 0;j < N;j++)
		{
			cin >> note[i][j];
		}
	}

	solution(N, note);
	return 0;
}
