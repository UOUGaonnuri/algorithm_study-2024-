#include <iostream>
#include <vector>
#include <map>
#include <algorithm>
using namespace std;
/*
* 1099 알 수 없는 문장
* 
* 단어 N개. 문장은 공백 없음
* ?? 단어의 문자 순서를 바꿔도 된다..? 돈만 내면
* 단어(비용) abc: abc(0), acb(2), bca(3)
* 먼저, 단어 별로 루프 돌려서 시작 인덱스부터 단어 개수만큼 체크해봄
* 다 포함되어있으면 이 단어임. 각각 단어의 문자가 같으면 공
* 
* 젠장 효율도 고려해야함.. 백트래킹 들어가서 경우의수 다 확인해봐야 할듯
* 틀렸는데,,?
* 
* 입력: 문장(50), 단어 개수(Nx50)
* 최대: 50x50xN 걍 해도 될듯
* 
*/

// 굴욕적이지만 전역변수 선언...
bool success;
int minCost;
void searchWord(int inStartIdx, int inCost, string sentence, vector<string>& words)
{
	int sentenceSize = sentence.size();
	if (inStartIdx == sentenceSize)
	{
		minCost = inCost < minCost ? inCost : minCost;
		success = true;
		return;
	}

	int nextIdx = 0;
	for (string word : words)
	{
		bool bIsMatched = true;
		int cost = inCost;
		int wordSize = word.size();
		int endIdx = inStartIdx + wordSize;
		// 길이 넘으면 무시
		if (endIdx > sentenceSize) continue;

		vector<bool> used = vector<bool>(wordSize, 0);
		for (int wordIdx = 0; wordIdx < wordSize; ++wordIdx)
		{
			char curChar = word[wordIdx];
			// 해당 위치에 단어가 있으면 바로 공짜로 써버림
			if (curChar == sentence[inStartIdx + wordIdx])
			{
				used[wordIdx] = true;
				continue;
			}

			bool isFound = false;
			for (int i = inStartIdx;i < endIdx;i++)
			{
				// 한 번 써먹은건 빼야함
				if (used[i - inStartIdx]) continue;

				if (word[wordIdx] == sentence[i])
				{
					used[i - inStartIdx] = isFound = true;
					// 공짜인 경우는 미리 했음
					++cost;

					break;
				}
			}

			// 탐색 실패
			if (isFound == false)
			{
				bIsMatched = false;
				break;
			}
		}

		if (bIsMatched)
		{
			// 다음 단어 탐색
			searchWord(endIdx, cost, sentence, words);
		}
	}
}

int solution(string sentence, int N, vector<string>& words)
{
	success = false;
	minCost = sentence.size();
	searchWord(0, 0, sentence, words);
	return success ? minCost : -1;
}

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	string sentence;
	int N;
	cin >> sentence >> N;
	vector<string> words(N);
	for (int i = 0;i < N;i++)
	{
		cin >> words[i];
	}

	cout << solution(sentence, N, words);
	return 0;
}
