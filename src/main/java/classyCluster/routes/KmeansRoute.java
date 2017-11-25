package classyCluster.routes;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import classyCluster.models.algorithms.Kmeans;
import classyCluster.utils.Analyzer;
import classyCluster.utils.FileHandler;
import classyCluster.models.Centroid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import classyCluster.view.KmeansView;

@RestController
public class KmeansRoute {

    @RequestMapping("/kmeans/blog")
    public String kmeanblog() throws IOException, URISyntaxException {
        return get("blogdata.txt");
    }

    @RequestMapping("/kmeans/wiki")
    public String kmeanwiki() throws IOException, URISyntaxException {
        return get("wikidata.txt");
    }

    private String get(String file) throws IOException, URISyntaxException {
        FileHandler handler = new FileHandler();
        List<String> data = handler.read(file);

        Analyzer analyzer = new Analyzer();
        analyzer.analyzeText(data);

        Kmeans kmeans = new Kmeans(analyzer.getArticles());
        ArrayList<Centroid> clusteredCentroids = kmeans.generateCentroids();

        return KmeansView.get(clusteredCentroids);
    }
}