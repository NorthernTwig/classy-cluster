package classyCluster.routes;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import classyCluster.structure.algorithms.Kmeans;
import classyCluster.utils.Analyzer;
import classyCluster.utils.FileHandler;
import classyCluster.structure.Centroid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import classyCluster.view.KmeansView;

@RestController
public class KmeansRoute {

    @RequestMapping("/kmeans")
    public String kmeans() throws IOException, URISyntaxException {
        FileHandler handler = new FileHandler();
        List<String> data = handler.read();

        Analyzer analyzer = new Analyzer();
        analyzer.analyzeText(data);

        Kmeans kmeans = new Kmeans(analyzer.getArticles());
        ArrayList<Centroid> clusteredCentroids = kmeans.generateCentroids();

        return KmeansView.get(clusteredCentroids);
    }
}