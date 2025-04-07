#include <iostream>
#include <vector>
#include <map>
#include <stack>
#include <algorithm>
using namespace std;
/*
* 25166 배고픈 아리의 샌드위치 구매하기
* 근데 이제 비트연산으로
* 
* 두리국
* 화폐가 비트단위임
* 아리는 1~512 각 1개씩 있음 (총 1023)
* 아리돈: 111111111
*
* 샌드위치값 - 아리돈 == (샌드위치값 - 아리돈) & 쿠기돈
* 
* 입력: 가격(S: 2048), 쿠기돈(M: 1023)
* 최대: O(1)인듯
* 
*/

string solution(int S, int N)
{
	int ariMoney = 1023;
	if ((S & ariMoney) == S)
		return "No thanks";
	else if (S - ariMoney == ((S - ariMoney) & N))
		return "Thanks";
	else
		return "Impossible";
}

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int S, N;
	cin >> S >> N;

	cout << solution(S, N);
	return 0;
}
