package classyCluster.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class FileHandler {
    public List<String> read() throws IOException, URISyntaxException {
        URI uri = this.getClass().getResource("/blogdata.txt").toURI();
        return Files.readAllLines(Paths.get(uri), Charset.defaultCharset());
    }

    public void writeWiki(String wiki) throws IOException {
        Files.write(Paths.get("src/main/resources/wikidata.txt"), wiki.getBytes());
    }

    public List<Path> readAll() throws IOException {
        return Files.walk(Paths.get("src/main/resources/words"))
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());
    }
}