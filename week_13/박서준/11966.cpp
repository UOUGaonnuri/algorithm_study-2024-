#include <iostream>
#include <vector>
#include <map>
#include <stack>
#include <algorithm>
using namespace std;
/*
* 11966 2의 제곱인가?
* 근데 이제 비트연산으로
* 
* 2의 제곱인지?
* 2의 제곱이면 1개만 1이고 나머지는 0
* 
* 입력: 2^30. 2^10^3 이니까 10^9 정도? int범위 안
* 
*/

int solution(int N)
{
	int count = 0;

	while (N != 0)
	{
		count += N & 1;

		if (count > 1)
			break;

		N >>= 1;
	}	

	return count == 1;
}

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int N;
	cin >> N;

	cout << solution(N);
	return 0;
}
