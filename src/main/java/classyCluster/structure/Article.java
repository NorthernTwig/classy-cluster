package classyCluster.structure;

import java.util.ArrayList;

public class Article {
    public ArrayList<Word> words;
    public String name;

    public void addWord(Word word) {
        words.add(word);
    }

    public Word getWord(int index) {
        return words.get(index);
    }

    public int getSize() {
        return words.size();
    }

    public Article(String name) {
        this.words = new ArrayList<>();
        this.name = name;
    }
}
