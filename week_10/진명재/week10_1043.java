package week_10.진명재;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class week10_1043 {
    static ArrayList<Integer>[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(st.nextToken());

        int[] g = IntStream.rangeClosed(0, n + 1).toArray();
        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int[] truth = new int[k];
        for (int i = 0; i < k; i++) {
            truth[i] = Integer.parseInt(st.nextToken());
        }

        arr = new ArrayList[m];
        for (int i = 0; i < m; i++) {
            arr[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            for (int j = 0; j < p; j++) {
                arr[i].add(Integer.parseInt(st.nextToken()));
            }
        }



        for (int i = 0; i < m; i++) {
            int first_man = arr[i].get(0);
            for (int j = 1; j < arr[i].size(); j++) {
                group(first_man, arr[i].get(j),g);
            }
        }
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            int leader = arr[i].get(0);
            boolean flag = true;
            for (int j = 0; j < k; j++) {
                if (find(leader,g) == find(truth[j],g)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    static void group(int n, int m, int[] g) {
        int a = find(n, g);
        int b = find(m, g);

        g[Math.max(a, b)] = Math.min(a, b);
    }

    static int find(int n, int[] g) {
        if (g[n] != n) {
            g[n] = find(g[n], g);
        }
        return g[n];
    }
}
