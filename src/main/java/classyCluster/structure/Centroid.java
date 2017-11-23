package classyCluster.structure;

import java.util.ArrayList;

public class Centroid {
    public ArrayList<Article> cluster;
    public Article article;
    public ArrayList<Article> previous;

    public Centroid() {
        cluster = new ArrayList<>();
        previous = new ArrayList<>();
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public boolean matchesPreviousAssignment() {
        return previous.containsAll(cluster) && cluster.containsAll(previous);
    }

    public void recalculateCenter() {
        for (int i = 0; i < article.words.size(); i++) {
            double average = 0.0;
            for (Article article : cluster) {
                average += article.words.get(i).getCount();
            }

            average /= (double)cluster.size();
            article.words.get(i).setCount(average);
        }

    }
}

