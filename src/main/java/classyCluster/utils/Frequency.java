package classyCluster.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Frequency {
    private int WORD_AMOUNT = 700;
    private int TEXT_AMOUNT = 100;
    private List<Path> paths;

    public Frequency(List<Path> paths) {
       this.paths = paths;
    }

    public String generateWikiData() throws IOException {
        HashSet<String> allWords = getAllWords();
        HashSet<String> wordsToCompare = new HashSet<>();

        allWords.forEach(word -> {
            if (wordsToCompare.size() < WORD_AMOUNT) {
                wordsToCompare.add(word);
            }
        });

        HashMap<String, HashMap<String, Integer>> wikiData = getWikiData(wordsToCompare);


        for (String word : wordsToCompare) {
            wikiData.forEach((fileName, wordOccurance) -> {
                if (!wordOccurance.containsKey(word)) {
                    wordOccurance.put(word, 0);
                }
            });
        }

        StringBuilder header = new StringBuilder();
        StringBuilder body = new StringBuilder();
        header.append("Blog");

        wordsToCompare.forEach(word -> header.append("\t").append(word));

        wikiData.forEach((fileName, wordOccurance) -> {
            body.append("\n");
            body.append(get(fileName));
            wordOccurance.forEach((word, occurance) -> body.append("\t").append(occurance));
        });

        return header.append(body).toString();
    }

    public static String get(String url) {
        return url.substring(url.lastIndexOf("/") + 1, url.length());
    }

    private HashSet<String> getAllWords() throws IOException {
        HashSet<String> words = new HashSet<>();
        for (Path file : paths) {
            Files.lines(file).forEach(line -> {
                words.addAll(Arrays.asList(line.split(" ")));
            });
        }

        return words;
    }

    private HashMap<String, HashMap<String, Integer>> getWikiData(HashSet<String> wordsToCompare) throws IOException {
        HashMap<String, HashMap<String, Integer>> blogs = new HashMap<>();

        for (Path file : paths) {
            if (blogs.size() < TEXT_AMOUNT) {
                blogs.put(file.toString(), new HashMap<>());

                wordsToCompare.forEach(word -> {
                    blogs.get(file.toString()).put(word, 0);
                });

                Files.lines(file).forEach(line -> {
                    for (String word : line.split(" ")) {
                        if (blogs.get(file.toString()).get(word) != null) {
                            int occurance = blogs.get(file.toString()).get(word);
                            HashMap<String, Integer> omt = blogs.get(file.toString());
                            omt.replace(word, occurance + 1);
                            blogs.replace(file.toString(), omt);
                        }
                    }
                });
            }
        }
        return blogs;
    }
}

