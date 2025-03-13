import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var NM = bufferedReader.readLine().strip().split(" ");
        var N = Integer.parseInt(NM[0]);
        var M = Integer.parseInt(NM[1]);

        HashMap<Integer, List<Integer>> userParty = new HashMap<>();
        HashMap<Integer, List<Integer>> partyUser = new HashMap<>();

        Set<Integer> knowSet = new HashSet<>();

        var q = new LinkedList<Integer>();

        for (int i=1; i<=N; i++) userParty.put(i, new ArrayList<>());

        // 알고 있는 사람들 큐에 넣어버림
        var know = Arrays.stream(bufferedReader.readLine().strip().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i=1; i<=know[0]; i++){
            q.add(know[i]);
            knowSet.add(know[i]);
        }

        // 유저가 참석한 파티, 파티에 참석한 유저 기록
        for (int i=0; i<M; i++){
            var party = Arrays.stream(bufferedReader.readLine().strip().split(" ")).mapToInt(Integer::parseInt).toArray();
            partyUser.put(i, new ArrayList<>());
            for (int j=1; j<=party[0]; j++){
                userParty.get(party[j]).add(i);
                partyUser.get(i).add(party[j]);
            }
        }


        while (!q.isEmpty()){
            var user = q.remove();
            var partyList = userParty.get(user);
            for (var party: partyList){
                var userList = partyUser.remove(party);
                if (userList != null){
                    for (var pUser: userList){
                        if (!knowSet.contains(pUser)){
                            knowSet.add(pUser);
                            q.add(pUser);
                        }
                    }
                }
                partyUser.remove(party);
            }
        }

        System.out.println(partyUser.size());

    }
}