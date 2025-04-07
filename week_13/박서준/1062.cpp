#include <iostream>
#include <vector>
#include <map>
#include <stack>
#include <algorithm>
using namespace std;
/*
* 1062 가르침
* 근데 이제 비트연산으로
* 
* 글자 알려주기 K개로
* N개 단어를 최대한 읽어낼 수 있게
* 남극언어는 anta~tica임: a,c,i,n,t 5개는 필수.
* 
* 글자는 최대 26개
* 음.. 2^25까지로 표현 가능한가?
* 'a'를 2^0, 'z'를 2^25 로
* 그럼 모든 알파벳을 치환 후 더해서 2^26-1과 & 연산 시, 1개수만큼이 한 단어를 읽을 때 필요한 글자 수
* 얘네를 and 연산해서 가장 교차를 많이하는 경우를 받아와서, 그 개수를 리턴하면 되나?
* 
* antarctica -> 5+1개
* antahellotica -> 5+3개
* antacartica -> 5+1개
* 
* 모든 단어들을 주인공으로 해서 한번씩 교집합 해본다?
* 단어는 8~15길이. 최대 50개.
* 50x50x15?
* 
* antarctica 기준. 6개.
* antahellotica -> 6+3개.
* antacartica -> 6+0개.
* 
* antahellotica 기준. 8개.(숫자 넘쳐서 끝)
* 
* antacartica 기준. 6개.
* 근데 똑같잖아. 이거 똑같은건 기록 해두면 될거같은데? 어? dp?
* dp는 먼가 안됨...
* 
* 입력: 2^30. 2^10^3 이니까 10^9 정도? int범위 안
* 
*/

int maxLearnCount = 0;
void backtracking(int index, int teached, int teachCount, int K, const vector<int>& wordValues) {
	if (teachCount == K) 
	{
		int cnt = 0;
		for (int word : wordValues) 
		{
			if ((word & teached) == word)
				cnt++;
		}
		maxLearnCount = max(maxLearnCount, cnt);
		return;
	}

	for (int i = index; i < 26; ++i) 
	{
		int alphabetNum = 1 << i;
		// 가르친 알파벳 아니면
		if ((teached & alphabetNum) == 0)
		{
			backtracking(i + 1, teached | alphabetNum, teachCount + 1, K, wordValues);
		}
	}
}

int solution(int N, int K, vector<string>& words)
{
	int teachCount = 0;
	
	// 최소 a,c,i,n,t 는 있어야함
	if (K < 5) return 0;

	vector<int> wordValues(N);
	for (int n = 0;n < N;n++)
	{
		const string& word = words[n];
		for (int i = 0;i < word.size();i++)
		{
			wordValues[n] |= 1 << (word[i] - 'a');
		}
	}

	int teached = 0;
	teached |= 1 << ('a' - 'a');
	teached |= 1 << ('c' - 'a');
	teached |= 1 << ('i' - 'a');
	teached |= 1 << ('n' - 'a');
	teached |= 1 << ('t' - 'a');
	teachCount = 5;

	backtracking(0, teached, teachCount, K, wordValues);

	return maxLearnCount;
}

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int N, K;
	cin >> N >> K;
	vector<string> words(N);
	for (int i = 0;i < N;i++)
	{
		cin >> words[i];
	}

	cout << solution(N, K, words);
	return 0;
}
