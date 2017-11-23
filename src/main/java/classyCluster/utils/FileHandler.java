package classyCluster.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileHandler {
    public List<String> read() throws IOException, URISyntaxException {
        URI uri = this.getClass().getResource("/blogdata.txt").toURI();
        return Files.readAllLines(Paths.get(uri), Charset.defaultCharset());
    }
}