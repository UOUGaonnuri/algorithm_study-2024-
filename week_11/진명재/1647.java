import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class 1647 {
    public static void main(String[] args) throws  Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(st.nextToken());


        PriorityQueue<road> que = new PriorityQueue<>(Comparator.comparingInt(road::getv));


        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            que.add(new road(a,b,v));

        }

        System.out.println(solution(que,n));
    }
    static int solution(PriorityQueue<road> que , int n){
        int[] g = IntStream.rangeClosed(0,n+1).toArray();

        int cnt = 0;
        int result = 0;
        while(!que.isEmpty()){
            road now = que.poll();

            int a = find(now.s,g);
            int b = find(now.e,g);

            if(a==b){
                continue;
            }

            cnt++;
            if(cnt ==n-1){
                break;
            }
            result += now.getv();
            g[Math.max(a,b)] = Math.min(a,b);
        }
        return result;

    }

    static int find(int n , int[] g){
        if(g[n]!=n){
            g[n] = find(g[n],g);
        }
        return g[n];
    }

    static class road{
        int s;
        int e;
        int v;

        road(int s, int e, int v) {
            this.s = s;
            this.e = e;
            this.v = v;
        }
        public int getv(){
            return v;
        }
    }
}
