#include <iostream>
#include <vector>
#include <map>
#include <algorithm>
using namespace std;
/*
* 14718 용감한 용사 진수
* 
* 병사를 K명 이겨야한다(등수)
* 힘,민,지 다 높아야만 이긴거
* 뒤에서 K번째 병사 스탯합?
* 등수가 같은 병사도 있을 수 있음
* 
* 10 1 1 / 4 4 4 병사 있을 때, 둘 다 15포 투자해야 이길 수 있음
* 이전 병사가 9 1 1 이면? 5 5 5 로는 못이김. 스탯합은 아닌 듯
* 
* 1명 이겨야 할 때, 그 병사 스탯합
* 2 명 이겨야 할 때, 그 병사들 스탯 최대치 합
* 오
* 근데 그 병사들은 어케구함
* K 명의 스탯 최대치 합의 최소값
* nCk?
* 
* 입력: 병사 수(N) 100, 등수(K), 각 병사별로 10^6 x 3 스탯 
* 최대: nCk? N^2?
* 
*/
struct status
{
	int strength;
	int agility;
	int intelligence;

	int getSum()
	{
		return strength + agility + intelligence;
	}
};

status best;
void conv(int startIdx, int wins, status nextBest, int N, int K, vector<status>& soldiers)
{
	if (wins == K)
	{
		best = best.getSum() < nextBest.getSum() ? best : nextBest;
		return;
	}
	// 남은 승리 가능 횟수 + 승리 회숫가 K가 안 될 때
	else if ((N - startIdx) + wins < K)
		return;

	for (int i = startIdx;i < N;i++)
	{
		const status& soldier = soldiers[i];
		status curBest = nextBest;
		curBest.strength = soldier.strength > curBest.strength ? soldier.strength : curBest.strength;
		curBest.agility = soldier.agility > curBest.agility ? soldier.agility : curBest.agility;
		curBest.intelligence = soldier.intelligence > curBest.intelligence ? soldier.intelligence : curBest.intelligence;
		conv(i + 1, wins + 1, curBest, N, K, soldiers);
	}
}

int solution(int N, int K, vector<status>& soldiers)
{
	best.strength = 1e6;
	best.agility = 1e6;
	best.intelligence = 1e6;
	status startStatus{ 0,0,0 };

	conv(0, 0, startStatus, N, K, soldiers);

	return best.getSum();
}

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	string sentence;
	int N, K;
	cin >> N >> K;
	vector<status> soldiers(N);
	for (int i = 0;i < N;i++)
	{
		status stat;
		cin >> stat.strength >> stat.agility >> stat.intelligence;
		soldiers[i] = stat;
	}

	cout << solution(N, K, soldiers);
	return 0;
}
