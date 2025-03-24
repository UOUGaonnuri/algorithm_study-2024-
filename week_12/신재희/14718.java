import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(input.nextToken());
        int K = Integer.parseInt(input.nextToken());

        List<User> users = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            input = new StringTokenizer(bf.readLine());
            users.add(new User(Integer.parseInt(input.nextToken()), Integer.parseInt(input.nextToken()), Integer.parseInt(input.nextToken())));
        }

        System.out.println(solution(N, K, users));
    }

    public static int solution(int N, int K, List<User> users) {
        users.sort(Comparator.comparingInt(User::getI));

        int result = 3_000_001;

        for (User us : users) {
            for (User ud : users) {
                int count = 0;
                for (User u : users) {
                    if (us.s >= u.s && ud.d >= u.d) count++;
                    if (count == K) {
                        result = Math.min(result, u.i + us.s + ud.d);
                        break;
                    }
                }
            }
        }

        return result;
    }

    public static class User {
        int s;
        int d;
        int i;

        User(int s, int d, int i) {
            this.s = s;
            this.d = d;
            this.i = i;
        }

        int getI() {
            return i;
        }
    }
}