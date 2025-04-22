import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bufferedReader.readLine());

        var arr = new int[N];

        arr = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, arr));
    }

    static int solution(int N, int[] arr){
        var result1 = new int[N];
        for (int i=0; i<N; i++){
            var now = 1;
            for (int j=i-1; j>=0; j--){
                if (arr[i] > arr[j]) {
                    now = Math.max(now, result1[j]+1);
                }
            }
            result1[i] = now;
        }

        var result2 = new int[N];
        for (int i=N-1; i>=0; i--){
            var now = 1;
            for (int j=i+1; j<N; j++){
                if (arr[i] > arr[j]){
                    now = Math.max(now, result2[j]+1);
                }
            }
            result2[i] = now;
        }

        return IntStream.range(0, N).map(i->result1[i]+result2[i]).max().getAsInt()-1;
    }
}