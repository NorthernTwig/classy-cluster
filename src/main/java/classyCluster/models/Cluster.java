package classyCluster.models;

public class Cluster {
    Cluster left;
    Cluster right;
    Cluster parent;
    Article article;
    double distance;

    public Cluster() {}
    public Cluster(Article article) {
        this.article = article;
    }

    public Article getArticle() {
        return article;
    }

    public Cluster merge(Cluster oc, double distance) {

        // Parent Cluster
        Cluster p = new Cluster();

        p.left = this;
        this.parent = p;
        p.right = oc;
        oc.parent = p;

        // Merge articles
        Article article = new Article("");

        for (int i = 0; i < article.getSize(); i++) {
            Word firstWords = this.article.getWord(i);
            Word secondWords = oc.article.getWord(i);
            double averageCount = (firstWords.getCount() + secondWords.getCount()) / 2.0;
            article.addWord(new Word(firstWords.getWord(), averageCount));
        }

        p.article = article;

        System.out.println(article);

        p.distance = distance;

        return p;
    }
}
