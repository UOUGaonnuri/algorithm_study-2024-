import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 1379 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        PriorityQueue<Gangui> que = new PriorityQueue<>(Comparator.comparingInt(Gangui::getE));

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int g = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            que.add(new Gangui(g, s, e));
        }

        int[] arr = soulution(N, que);

        for (int i : arr) {
            System.out.println(i);
        }

    }

    static int[] soulution(int N, PriorityQueue<Gangui> que) {
        List<Integer> list = new ArrayList<>();
        int[] arr = new int[N + 1];
        Gangui f = que.poll();
        list.add(f.e);
        arr[f.g] = 1;
        while (!que.isEmpty()) {
            Gangui a = que.poll();
            boolean chk = true;
            for (int i = list.size() - 1; i >= 0; i--) {
                if (list.get(i) <= a.s) {
                    chk = false;
                    list.set(i, a.e);
                    arr[a.g] = i + 1;
                    break;
                }
            }
            if (chk) {
                list.add(a.e);
                arr[a.g] = list.size();
            }

        }
        arr[0] = list.size();

        return arr;
    }


    static class Gangui {
        int g;
        int s;
        int e;


        Gangui(int g, int s, int e) {
            this.g = g;
            this.s = s;
            this.e = e;
        }

        public int getE() {
            return e;
        }
    }
}
