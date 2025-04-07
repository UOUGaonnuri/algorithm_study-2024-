import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());

        var arr = new char[N][N];

        for (int i=0; i<N; i++) arr[i] = bf.readLine().strip().toCharArray();

        System.out.println(solution(N, arr));
    }

    static int solution(int N, char[][] arr) {
        var group = IntStream.range(0, N).toArray();
        var isG = new boolean[N];
        var cnt = 0;
        for (int i=0; i<N; i++) {
            for (int j=i+1; j<N; j++) {
                if (arr[i][j]=='N') continue;
                isG[j] = isG[i] = true;
                cnt++;
                var ig = find(i, group);
                var jg = find(j, group);
                group[Math.max(ig, jg)] = Math.min(ig, jg);
            }
        }
        if (N==1) return 0;
        if (cnt<N-1) return -1;

        var result = 0;
        for (int i=0; i<N; i++) {
            if (!isG[i]) return -1;
            if (group[i]==i) result++;
        }
        return result-1;
    }

    static int find(int n, int[] group) {
        if (n!=group[n]) group[n] = find(group[n], group);
        return group[n];
    }
}