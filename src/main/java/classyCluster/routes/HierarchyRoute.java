package classyCluster.routes;

import classyCluster.models.algorithms.Hierarchy;
import classyCluster.utils.Analyzer;
import classyCluster.utils.FileHandler;
import classyCluster.view.HierarchyView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class HierarchyRoute {

    @RequestMapping("/hier/wiki")
    public String hierwiki() throws IOException, URISyntaxException {
        return get("wikidata.txt");
    }

    @RequestMapping("/hier/blog")
    public String hierblog() throws IOException, URISyntaxException {
        return get("blogdata.txt");
    }

    private String get(String file) throws IOException, URISyntaxException {
        FileHandler handler = new FileHandler();
        List<String> data = handler.read(file);

        Analyzer analyzer = new Analyzer();
        analyzer.analyzeText(data);

        Hierarchy hierarchy = new Hierarchy(analyzer.getArticles());
        hierarchy.init();
        while (hierarchy.hasClusterLeft()) hierarchy.iterate();

        HierarchyView view = new HierarchyView(hierarchy.clusters.get(0));
        return view.get();
    }
}
