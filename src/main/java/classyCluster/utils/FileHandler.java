package classyCluster.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class FileHandler {
    public List<String> read() throws IOException, URISyntaxException {
        URI uri = this.getClass().getResource("/blogdata.txt").toURI();
        return Files.readAllLines(Paths.get(uri), Charset.defaultCharset());
    }

    public void generateWikiData() throws IOException {
        HashMap<String, Integer> words = new HashMap<>();
        List<Path> allFiles = readAll();
        for (Path file : allFiles) {
            System.out.println(file.toString());
            Files.lines(file).forEach(line -> {
                for (String word: line.split(" ")) {
                    if (words.containsKey(word)) {
                        int occurance = words.get(word);
                        words.replace(word, occurance + 1);
                    } else {
                        words.put(word, 1);
                    }
                }
            });
        }
        words.forEach((value, key) -> {
            System.out.println(value);
            System.out.println(key);
        });
    }

    private List<Path> readAll() throws IOException {
        return Files.walk(Paths.get("src/main/resources/words"))
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());
    }
}