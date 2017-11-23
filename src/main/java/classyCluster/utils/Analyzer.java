package classyCluster.utils;

import classyCluster.structure.Article;
import classyCluster.structure.Word;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Analyzer {
    private List<String> textData;
    private ArrayList<String> words;
    private ArrayList<Article> articles;

    public Analyzer() throws IOException, URISyntaxException {
        textData = new ArrayList<>();
        words = new ArrayList<>();
        articles = new ArrayList<>();
    }

    public void analyzeText(List<String> text) {
        textData = text;
        getWords();
        createArticles();
        System.out.println(articles);
    }

    private void getWords() {
        String row = textData.get(0);
        String[] wordArr = row.split("\\s+");
        words.addAll(Arrays.asList(wordArr));
    }

    private void createArticles() {
        for (int i = 1; i < textData.size(); i++) {
            String row = textData.get(i);
            String[] arr = row.split("\\t+");
            String name = arr[0];
            Article article = new Article(name);
            for (int i1 = 1; i1 < arr.length; i1++) {
                double count = Integer.parseInt(arr[i1]);
                Word word = new Word(words.get(i1), count);
                article.words.add(word);
            }
            articles.add(article);
        }
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }
}
