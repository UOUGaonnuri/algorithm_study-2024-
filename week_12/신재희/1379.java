import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        var N = Integer.parseInt(bf.readLine());

        var pq = new PriorityQueue<Lecture>(Comparator.comparingInt(Lecture::getStart));

        for (int i=0; i<N; i++) {
            pq.add(new Lecture(Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()));
        }

        solution(N, pq);
    }

    static void solution(int N, PriorityQueue<Lecture> nextLecture) {
        var result = 0;
        var t = 0;

        var nowLecture = new PriorityQueue<Lecture>(Comparator.comparingInt(Lecture::getEnd));
        var room = new LinkedList<Integer>();
        var lectures = new ArrayList<Lecture>();

        while (!nextLecture.isEmpty()) {
            var lecture = nextLecture.peek();

            while (!nowLecture.isEmpty() && nowLecture.peek().getEnd() <= lecture.getStart()) {
                var l = nowLecture.remove();
                room.add(l.room);
                lectures.add(l);
            }

            while (!nextLecture.isEmpty() && nextLecture.peek().getStart() == lecture.getStart()) {
                int r;
                if (room.isEmpty()) {
                    room.add(++result);
                }
                var l = nextLecture.remove();
                l.setRoom(room.remove());
                nowLecture.add(l);
            }
        }

        while (!nowLecture.isEmpty()) {
            lectures.add(nowLecture.remove());
        }

        lectures.sort(Comparator.comparingInt(Lecture::getN));

        System.out.println(result);

        for (Lecture l: lectures) {
            System.out.println(l.room);
        }
    }

    static class Lecture {
        int n;
        int start;
        int end;
        int room;


        Lecture(int[] input) {
            this.n = input[0];
            this.start = input[1];
            this.end = input[2];
        }

        public int getN() {
            return n;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public void setRoom(int room) {
            this.room = room;
        }
    }
}