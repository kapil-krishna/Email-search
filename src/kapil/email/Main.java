package kapil.email;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.*;
import java.util.HashMap;


public class Main {

    public static void main(String[] args) throws IOException {

        Path filePath = Paths.get("resources/sample.txt");

        String content = Files.readString(filePath);

        int counter = 0;
        int i = 0;
        Pattern domainNames = Pattern.compile("@\\S*\\w");
        Matcher matcher = domainNames.matcher(content);
        HashMap<String, Integer> domains = new HashMap<>();



        while (matcher.find()) {
            String key = matcher.group();

            Integer currentValue = domains.getOrDefault(key, 0);

            domains.put(key, currentValue + 1);


        }
        System.out.println(domains);

//        for (i = 1; i <= content.length() - 14; i++) {
//            if (content.substring(i, i + 14).equals("@techsmiths.uk")) {
//                counter++;
//            }
//        }
//        System.out.println(counter);
    }
}