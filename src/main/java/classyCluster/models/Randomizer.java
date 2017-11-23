package classyCluster.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {
    HashMap<String, Double> max = new HashMap<>();
    HashMap<String, Double> min = new HashMap<>();

    public Randomizer(ArrayList<Article> articles) {
        for (Article article : articles) {
            for (Word word : article.words) {
                addMax(word);
                addMin(word);
            }
        }
    }

    public Centroid createRandom() {
        Article article = new Article("centroid");

        max.forEach((String key, Double value) -> {
            double maxValue = value;
            double minValue = min.get(key);

            double random = ThreadLocalRandom.current().nextDouble(minValue, maxValue + 1);
            Word word = new Word(key, random);
            article.words.add(word);
        });

        Centroid centroid = new Centroid();
        centroid.setArticle(article);
        return centroid;
    }

    private void addMax(Word word) {
       if (max.containsKey(word.getWord())) {
            if (max.get(word.getWord()) < word.getCount()) {
                max.put(word.getWord(), word.getCount());
            }
        } else {
            max.put(word.getWord(), word.getCount());
        }
    }

    private void addMin(Word word) {
       if (min.containsKey(word.getWord())) {
            if (min.get(word.getWord()) < word.getCount()) {
                min.put(word.getWord(), word.getCount());
            }
        } else {
            min.put(word.getWord(), word.getCount());
        }
    }
}
