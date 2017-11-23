package classyCluster.structure.algorithms;

import classyCluster.structure.Article;
import classyCluster.structure.Cluster;
import classyCluster.structure.Pearson;

import java.util.ArrayList;

public class Hierarchy {
    ArrayList<Article> articles;
    ArrayList<Cluster> clusters;

    public Hierarchy(ArrayList<Article> articles) {
        this.articles = articles;
        this.clusters = new ArrayList<>();
    }

    public void init() {
        clusters = new ArrayList<>();
        for (Article article : articles) {
            clusters.add(new Cluster(article));
        }
    }

    public void iterate() {

        // Best nodes
        double closest = Double.MAX_VALUE;
        Cluster bestA = null;
        Cluster bestB = null;

        for (int i = 0; i < clusters.size(); i++) {
            for (int j = i+1; j < clusters.size(); j++) {
                Cluster clusterOne = clusters.get(i);
                Cluster clusterTwo = clusters.get(j);

                double distance = Pearson.calculate(clusterOne.getArticle(), clusterTwo.getArticle());
                if (distance < closest) {
                    closest = distance;
                    bestA = clusterOne;
                    bestB = clusterTwo;
                }
            }

        }

        if (bestA != null) {
            Cluster mergedCluster = bestA.merge(bestB, closest);
            clusters.add(mergedCluster);
            clusters.remove(bestA);
            clusters.remove(bestB);
        }
    }

    public boolean hasClusterLeft() {
        return clusters.size() > 1;
    }

}

