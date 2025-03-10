package org.example;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        int[][] arr = new int[M][2];

        for (int i = 0; i < M; i++) {
            input = new StringTokenizer(bf.readLine());

            arr[i][0] = Integer.parseInt(input.nextToken());
            arr[i][1] = Integer.parseInt(input.nextToken());
        }

        System.out.println(solution(N, M, arr));
    }

    static int solution(int N, int M, int[][] arr) {
        int[] g = IntStream.range(0, N).toArray();

        int result = 0;

        for (int i = 0; i < M; i++) {
            int start = arr[i][0];
            int end = arr[i][1];

            int a = find(start, g);
            int b = find(end, g);

            if (a == b) {
                // 같을 경우 처리
            }

            g[Math.max(a, b)] = Math.min(a, b);
        }

        return result;
    }

    static int find(int n, int[] g) {
        if (g[n] == n) return n;
        return g[n] = find(g[n], g);
    }
}