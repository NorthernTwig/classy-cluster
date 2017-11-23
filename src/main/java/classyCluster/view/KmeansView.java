package classyCluster.view;

import classyCluster.models.Centroid;

import java.util.ArrayList;

public class KmeansView {
    public static String get(ArrayList<Centroid> clusteredCentroids) {
        StringBuilder builder = new StringBuilder();

        for (Centroid clusteredCentroid : clusteredCentroids) {
            if (clusteredCentroid.cluster.size() > 0) {
                builder.append("\n");
                builder.append("<hr>");
                builder.append("<p>").append("Cluster Size: " + clusteredCentroid.cluster.size()).append("</p>");
                builder.append("<ul>");
                clusteredCentroid.cluster.forEach(article -> {
                    builder.append("<li>").append(article.name).append("</li>");
                });
                builder.append("</ul>");
            }
        }

        return builder.toString();
    }
}
