import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class 1833{
    static StringBuilder sb = new StringBuilder();
    static int cnt =0;
    static int result =0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] g = IntStream.rangeClosed(0,N).toArray();

        PriorityQueue<doro> que = new PriorityQueue<>(Comparator.comparingInt(doro::getV));


        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++){
                int V = Integer.parseInt(st.nextToken());
                if (i >= j) continue;
                if(V < 0){
                    result += (-V);
                    group(i,j,g);
                }
                else{
                    que.add(new doro(i,j,V));
                }
            }
        }

        System.out.println(solution(N,que,g) + " " + cnt);
        System.out.println(sb);


    }

    static int solution(int N, PriorityQueue<doro> que, int[] g){

        while(!que.isEmpty()){
            doro a = que.poll();

            if(checkgroup(g,N)){
                break;
            }
            int i = find(a.s,g);
            int j = find(a.e,g);

            if(i!=j) {
                cnt++;
                group(i, j, g);
                result += a.getV();

                sb.append((a.s + 1) + " " + (a.e + 1) + "\n");
            }
        }
        return result;
    }
    static void group(int a, int b , int[] g){
        int i = find(a,g);
        int j = find(b,g);

        g[Math.max(i,j)]= Math.min(i,j);
    }

    static int find(int n, int[] g){
        if(g[n] != n){
            g[n] = find(g[n],g);
        }
        return g[n];
    }

    static boolean checkgroup(int[] g, int N) {
        int root = find(0, g);
        for (int i = 1; i < N; i++) {
            if (find(i, g) != root) {
                return false;
            }
        }
        return true;
    }

    static class doro{
        int s ;
        int e ;
        int v ;

        doro(int s , int e , int v){
            this.s = s;
            this.e = e;
            this.v = v;

        }
        public int getV(){
            return v;
        }

    }
}
