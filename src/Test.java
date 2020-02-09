import java.io.IOException;
import java.util.*;

public class Test {
    public static void main(String[] args) throws IOException {
        System.out.println("Test...");

        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(1, 11);
        map.put(4, 11);
        map.put(2, 11);

        System.out.println(Arrays.toString(map.keySet().toArray()));
    }


}

class TweetCounts {

    Map<String, TreeMap<Integer, Integer>> map = new HashMap<>();

    public TweetCounts() {

    }

    public void recordTweet(String tweetName, int time) {
        if (!map.containsKey(tweetName)) {
            map.put(tweetName, new TreeMap<>());
        }
        TreeMap<Integer, Integer> treeMap = map.get(tweetName);
        treeMap.put(time, treeMap.getOrDefault(time, 0) + 1);
    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        TreeMap<Integer, Integer> treeMap = map.get(tweetName);
        List<Integer> list = new ArrayList<>();
        Set<Map.Entry<Integer, Integer>> entries = treeMap.entrySet();
        Iterator<Map.Entry<Integer, Integer>> iterator = entries.iterator();
        int inter = 0;

        if (freq.equals("minute"))
            inter = 60;
        else if (freq.equals("hour"))
            inter = 3600;
        else
            inter = 86400;

        int startN = startTime / inter;
        int n = endTime / inter - startN;
        for (int i = 0; i < n + 1; i++) list.add(0);

        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> next = iterator.next();
            Integer key = next.getKey();
            if (key >= startTime && key <= endTime) {
                list.set(key / inter - startN, list.get(key / inter - startN) + 1);
            }
        }
        return list;
    }
}
