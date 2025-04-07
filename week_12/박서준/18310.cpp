#include <iostream>
#include <vector>
#include <stack>
#include <algorithm>
using namespace std;

/*
* 18310 안테나
* 일직선상에 있는 집들 중 나머지와의 거리 합이 최소가 되는 집
* 
* 최대한 가운데 있으면 되지 않나?
* 집이 겹쳐있을 수 있음...
* 겹친 집은 가중치로 둔다.
* 1 5 7 9: (1+5+7+9) / 4 = 5.5
* 값의 평균에서 가장 가까운 위치로 하면 될듯?
* 안되는데요
* 정렬된 집 중에서 중간쯤 있는애로 그냥 골라보면?
* 뭐지? 걍 답으로 나오는데? 이거맞음?
* 
* 입력: N(집 수): 2x10^5, houses(집 위치): 10^5
* 최대: 10^10 정도? 에바임
*/
int solution(int N, vector<int>& houses)
{
	sort(houses.begin(), houses.end(), [](int a, int b) {
			return a < b;
		});

	int mediumIdx = N % 2 == 0 ? N / 2 : N / 2 + 1;
	return houses[mediumIdx];
}

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int N;
	cin >> N;
	vector<int> houses(N + 1);
	for (int i = 1;i <= N;i++)
	{
		cin >> houses[i];
	}

	cout << solution(N, houses);
	return 0;
}
