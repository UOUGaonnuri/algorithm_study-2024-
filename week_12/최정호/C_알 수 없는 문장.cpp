// 골드 5 - 1099번 : 알 수 없는 문장
// 작성자 : free4760(jeonghoe22)

#include <iostream>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;

// 단어 N개
// 각 단어 : 0번 또는 그 이상 나타날 수 있음

// 문장
// 문자의 순서 변경 가능
// 단어를 공백 없이 붙여서 쓴 것

// 비용 : 원래 단어 위치와 다른 위치에 있는 문자의 개수만큼 그 순서를 바꾼 단어를 만드는 비용
// abc -> abc : 0
// abc -> acb, cba, bac : 2
// abc -> bca, cab : 3

// 문장읠 길이 최대 50
// 둘째 줄부터 단어 개수 N

// 정답 출력, 없으면 -1

// ex)
// neotowheret
// one : o != n, n != e, e != 0 -> 비용 3
// two : t == t, w != o, o != w -> 비용 2
// three : t != h, h != e, r == r, e == e, e != t -> 비용 3
// 총합 8

// abba
// ab -> ab = 0
// ab -> ba = 2
// 총합 2

// 현재 글자를 검사했을 때, 단어의 구성이 같다면,
// 현재 글자 위치 - 단어 개수와 이전에 검사했을 때의 값 중 최솟값을 남김

// 마지막 글자를 검사하지 못한다면, -1을 반환

const int MAX_VALUE = 1'000'000'000;

string g_input;
vector<string> g_checks;

// 입력
void inputString()
{
    cin >> g_input;

    int N;
    cin >> N;
    g_checks = vector<string>(N);

    for(string& check : g_checks)
    {
        cin >> check;
    }
}

// 글자 구성이 같은지 확인
bool compareChars(vector<int>& input_char, vector<int>& check_char)
{
    bool b_state = true;
    for(int i = 0; i < 26; i++)
    {
        // 글자 구성이 다르면, 반환
        if(input_char[i] != check_char[i]) b_state = false;
        
        // 글자 구성 초기화
        input_char[i] = 0;
        check_char[i] = 0;
    }

    return b_state;
}

// 검사
int search()
{
    int input_length = g_input.length();
    int check_size = g_checks.size();

    vector<int> input_char(26, 0);
    vector<int> check_char(26, 0);

    vector<int> dp(input_length+1, MAX_VALUE);
    dp[0] = 0;
    // 문자 하나마다 검사
    for(int i = 0; i < input_length; i++)
    {
        for(const string& check : g_checks)
        {
            // 검사할 수 있는 문자열이 더 짧은 경우는 무시
            if(check.length() > i+1) continue;

            int count = 0;
            // 글자 구성 담기
            for(int offset = check.length()-1; offset >= 0; offset--)
            {
                input_char[g_input[i-offset] - 'a']++;
                check_char[check[check.length()-1-offset] - 'a']++;

                // 글자가 달라 순서 재배치 필요
                if(g_input[i-offset] != check[check.length()-1-offset]) count++;
            }

            // 구성이 다르면, 무시
            if(compareChars(input_char, check_char) == false) continue;
            // 구성이 같으면, 비교해서 추가
            dp[i+1] = min(dp[i+1], dp[i+1-check.length()] + count);
        }
    }
    
    // 모든 글자에 경우의 수가 존재해야 함.
    if(dp[input_length] == MAX_VALUE) return -1;
    return dp[input_length];
}

int main()
{
    // 입출력 최적화
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    // 입력
    inputString();
    // 검사
    int result = search();
    // 출력
    cout << result << "\n";
}