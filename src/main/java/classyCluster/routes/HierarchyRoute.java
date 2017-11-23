package classyCluster.routes;

import classyCluster.structure.algorithms.Hierarchy;
import classyCluster.utils.Analyzer;
import classyCluster.utils.FileHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class HierarchyRoute {
    @RequestMapping("/hier")
    public String hierarchy() throws IOException, URISyntaxException {
        FileHandler handler = new FileHandler();
        List<String> data = handler.read();

        Analyzer analyzer = new Analyzer();
        analyzer.analyzeText(data);

        Hierarchy hierarchy = new Hierarchy(analyzer.getArticles());
        hierarchy.init();
        while (hierarchy.hasClusterLeft()) hierarchy.iterate();

        return "<h1>Du kom hit! :)</h1>";
    }
}
