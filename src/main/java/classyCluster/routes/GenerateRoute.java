package classyCluster.routes;

import classyCluster.utils.FileHandler;
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
        handler.generateWikiData();
        return GenerateView.get();
    }
}
