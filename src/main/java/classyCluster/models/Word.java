package classyCluster.models;

public class Word {
    private String word;
    private double count;

    String getWord() {
        return word;
    }

    double getCount() {
        return count;
    }

    void setCount(double value) {
        count = value;
    }

    public Word(String word, double count) {
        this.word = word;
        this.count = count;
    }
}
