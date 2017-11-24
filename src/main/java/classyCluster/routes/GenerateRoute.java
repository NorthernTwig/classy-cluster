package classyCluster.routes;

import classyCluster.utils.FileHandler;
import classyCluster.utils.Frequency;
import classyCluster.view.GenerateView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
public class GenerateRoute {
    @RequestMapping("/generate")
    public String wiki() throws IOException, URISyntaxException {
        FileHandler handler = new FileHandler();
        Frequency frequency = new Frequency(handler.readAll());
        String wiki = frequency.generateWikiData();
        handler.writeWiki(wiki);
        return GenerateView.get();
    }
}
