package classyCluster.models.algorithms;

import classyCluster.models.Article;
import classyCluster.models.Centroid;
import classyCluster.models.Pearson;
import classyCluster.models.Randomizer;

import java.util.ArrayList;

public class Kmeans {
    private ArrayList<Centroid> centroids;
    private ArrayList<Article> articles;

    public Kmeans(ArrayList<Article> articles) {
       this.articles = articles;
    }

    public ArrayList<Centroid> generateCentroids() {
        centroids = new ArrayList<>();
        Randomizer rnd = new Randomizer(articles);
        for (int i = 0; i < 10; i++) {
            centroids.add(rnd.createRandom());
        }

        boolean done = false;
        int count = 0;
        while (!done) {
            clearClusters();
            iterate();

            for (Centroid centroid : centroids) {
                centroid.recalculateCenter();
            }

            done = true;

            for (Centroid centroid : centroids) {
                if (!centroid.matchesPreviousAssignment()) {
                    done = false;
                }
            }
            count++;
        }

        System.out.println("iterations: " + count);
        return centroids;
    }

    private void iterate() {
        for (Article article : articles) {
            double closest = Double.MAX_VALUE;
            Centroid close = null;

            for (Centroid centroid : centroids) {
                double pearson = Pearson.calculate(article, centroid.article);

                if (pearson < closest) {
                    closest = pearson;
                    close = centroid;
                }
            }

            if (close != null) {
                close.cluster.add(article);
            }
        }
    }

    private void clearClusters() {
        for (Centroid centroid : centroids) {
            centroid.previous = centroid.cluster;
            centroid.cluster = new ArrayList<>();
        }
    }
}
