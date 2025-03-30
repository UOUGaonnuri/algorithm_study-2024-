import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class 1922 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int M = Integer.parseInt(br.readLine());

        PriorityQueue<network> que = new PriorityQueue<>(Comparator.comparingInt(network :: getV)); 

        for(int i=0;i<M;i++){
            StringTokenizer st  =new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            que.add(new network(a,b,c)); 
        }



        System.out.println(solution(que,N));

    }

    static int solution(PriorityQueue<network> que , int N){
        int[] g = IntStream.rangeClosed(0,N).toArray();

        int result = 0;
        int cnt = 0;
        while(!que.isEmpty()){
            network k = que.poll();

            int a  = find(k.e,g);
            int b  = find(k.s,g);

            if(a==b){
                continue;
            }
            cnt++;
            if(cnt==N){
                break;
            }
            result+= k.getV();
            group(a,b,g);
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

    static class network{
        int s ;
        int e ;
        int v ;

        network(int s , int e , int v){
            this.s = s;
            this.e = e;
            this.v = v;

        }
        public int getV(){
            return v;
        }

    }
}

