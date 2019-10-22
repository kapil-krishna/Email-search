package kapil.email;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;

import static java.util.Map.*;


public class Main {

    public static void main(String[] args) throws IOException {

        Path filePath = Paths.get("resources/sample.txt");

        String content = Files.readString(filePath);

        int counter = 0;
        int i = 0;
        Pattern domainNames = Pattern.compile("@\\S*\\w");
        Matcher matcher = domainNames.matcher(content);
        Map<String, Integer> domains = new HashMap<>();

        while (matcher.find()) {
            String key = matcher.group();

            Integer currentValue = domains.getOrDefault(key, 0);

            domains.put(key, currentValue + 1);
        }

        //convert hashmap to entry set
        Set<Entry<String, Integer>> entries = domains.entrySet();

        //create comparator
        Comparator<Entry<String, Integer>> compareValues = new Comparator<Entry<String, Integer>>() {

            @Override
            public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
                Integer value1 = e1.getValue();
                Integer value2 = e2.getValue();
                return value2.compareTo(value1);
            }
        };

        //convert set to list
        List<Entry<String, Integer>> listOfEntries = new ArrayList<Entry<String, Integer>>(entries);

        //sorting values using comparator
        Collections.sort(listOfEntries, compareValues);

        System.out.println(listOfEntries.subList(0, 10));

        List<Entry<String, Integer>> top10Entries = domains.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(10)
                .collect(Collectors.toList());

        System.out.println(top10Entries);

//        LinkedHashMap<String, Integer> sortedDomains = new LinkedHashMap<>(entries.size());
//
//        //copy entries from list to map
//        for (Entry<String, Integer> entry : listOfEntries) {
//            sortedDomains.put(entry.getKey(), entry.getValue());
//        }

    }
}