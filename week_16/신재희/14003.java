import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bufferedReader.readLine());

        var arr = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, arr));
    }

    static String solution(int N, int[] arr){
        var maxLen = new int[N];

        var size = findList(N, arr, maxLen);

        var result = new int[size];

        var now = size-1;

        for (int i=N-1; i>=0 && now>=0; i--){
            if (maxLen[i] == now) {
                result[now--] = arr[i];
            }
        }

        var sb = new StringBuilder();
        sb.append(size);
        sb.append('\n');
        sb.append(Arrays.stream(result).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        return sb.toString();
    }

    static int findList(int N, int[] arr, int[] maxLen) {
        List<Integer> dp = new ArrayList<>();
        dp.add(arr[0]);
        for (int i=1; i<N; i++) {
            var n = find(arr[i], dp);
            if (n==dp.size()) dp.add(arr[i]);
            else dp.set(n, arr[i]);
            maxLen[i] = n;
        }

        return dp.size();
    }

    static int find(int n, List<Integer> arr){
        int s = 0;
        int e = arr.size()-1;

        if (arr.get(s)>=n) return s;
        if (arr.get(e)<n) return arr.size();

        while (s+1<e) {
            var mid = (s+e)/2;
            if (arr.get(mid)<n) s = mid;
            else e = mid;
        }
        return e;
    }
}