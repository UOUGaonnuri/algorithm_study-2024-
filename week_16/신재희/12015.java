import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bufferedReader.readLine());
        var arr = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(solution(N, arr));
    }

    static int solution(int N, int[] arr){
        var dp = new ArrayList<Integer>();

        for (int i: arr){
            if (dp.isEmpty()) {
                dp.add(i);
                continue;
            }
            var n = find(i, dp);
            if (n==dp.size()) dp.add(i);
            else dp.set(n, i);
        }
        return dp.size();
    }

    static int find(int n, List<Integer> dp) {
        var s = 0;
        var e = dp.size()-1;

        if (dp.get(s)>=n) return 0;
        if (dp.get(e)<n) return dp.size();

        while (s+1!=e) {
            var mid = (s+e)/2;
            if (dp.get(mid)<n) {
                s = mid;
            } else {
                e = mid;
            }
        }

        return e;
    }
}