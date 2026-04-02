package patternSearching;

/*
You receive a stream of strings.
Each string is either:
Query: "Q: <query text>"
Log: "L: <log text>"
Assign each query a unique incremental ID starting from 1.

Output:
ACK: <query text>; ID=<id>
Logs
A query matches a log if all words in the query appear in the log (case-insensitive).
Output:
M: <log text>; Q=<comma-separated matching query IDs>

Only include logs that match at least one query.
Given stream/array of Query and Logs, output messages as following:
For query, output an acknowledgement with unique query id: ex) "ACK: database; ID=1".
For log, output comma-separated matched query ids: ex) M: Database service started: Q=1".
Here are more detailed example:

livetail_stream = [
  "Q: database",
  "Q: Stacktrace",
  "Q: loading failed",
  "L: Database service started",
  "Q: snapshot loading",
  "Q: fail",
  "L: Started processing events",
  "L: Loading main DB snapshot",
  "L: Loading snapshot failed no stacktrace available",
]

livetail_output = [
  "ACK: database; ID=1",
  "ACK: Stacktrace; ID=2",
  "ACK: loading failed; ID=3",
  "M: Database service started; Q=1",
  "ACK: snapshot loading; ID=4",
  "ACK: fail; ID=5",
  "M: Loading main DB snapshot; Q=4",
  "M: Loading snapshot failed no stacktrace available; Q=2,3,4",
]
Created hash map from word to array of query ids. For each log, I maintained counter to see which query got fully matched.

*/

import java.util.*;
import java.util.stream.Collectors;
import java.util.Map.Entry;

public class MapReduceInLogText {
    public static int queryId = 1;
    public static Map<String, Set<Integer>> wordToQueryIds = new HashMap<>();
    public static Map<Integer, Integer> queryWordCount = new HashMap<>();

    public static List<String> process(List<String> input){
        List<String> result = new ArrayList<>();

        for (String line : input) {
            String[] words = line.substring(2).trim().toLowerCase().split("\\s+");
            Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));

            if(line.startsWith("Q:")){
                queryWordCount.put(queryId, uniqueWords.size());
                uniqueWords.forEach(word -> {
                    wordToQueryIds.computeIfAbsent(word, w -> new HashSet<Integer>()).add(queryId);
                });

                result.add(String.format("ACK: %s; ID=%d", line.substring(2), queryId++));
            }

            if(line.startsWith("L:")){
                List<Integer> queryIds = wordToQueryIds.entrySet().stream()
                        .filter(entry-> uniqueWords.contains(entry.getKey()))
                        .flatMap(entry -> entry.getValue().stream())
                        .collect(Collectors.groupingBy(k->k, Collectors.counting()))
                        .entrySet().stream()
                        .filter(counterByRequestEntry->counterByRequestEntry.getValue().intValue()== queryWordCount.get(counterByRequestEntry.getKey()))
                        .map(Entry::getKey)
                        .sorted()
                        .collect(Collectors.toList());

                result.add(String.format("M: %s; Q=%s", line.substring(2), String.join(",", queryIds.stream().map(String::valueOf).toArray(String[]::new))));
            }
        }

        return result;
    }

    public static void mainF(String[] args) {

        Deque<String> que=new LinkedList<>();
        que.add("dddd");
        que.add(null);
        que.add("ssss");

        que.poll();
        que.poll();
        que.poll();

        List<String> input = List.of(
                "Q: database",
                "Q: Stacktrace",
                "Q: loading failed",
                "L: Database service started",
                "Q: snapshot loading",
                "Q: fail",
                "L: Started processing events",
                "L: Loading main DB snapshot",
                "L: Loading snapshot failed no stacktrace available"
        );

        List<String> result = process(input);
        for (String line : result) {
            System.out.println(line);
        }
    }
}