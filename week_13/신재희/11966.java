import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());



        while ((N & 1) == 0) {
            N = N >> 1;
        }

        System.out.println(N == 1 ? 1 : 0);
    }
}