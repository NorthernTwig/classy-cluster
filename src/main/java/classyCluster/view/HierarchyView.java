package classyCluster.view;

import classyCluster.models.Cluster;

import java.util.ArrayList;

public class HierarchyView {
    ArrayList<String> html;
    Cluster root;

    public HierarchyView(Cluster cluster) {
        html = new ArrayList<>();
        root = cluster;
    }

    public String get() {
        return buildTree();
    }

    public String buildTree() {
        html = new ArrayList<>();
        html.add("<ul>");
        html.add("</ul>");

        addNodes(1, root);
        StringBuilder str = new StringBuilder();
        for (String s : html) {
            str.append(s).append("\n");
        }
        return str.toString();
    }

    private void addNodes(int index, Cluster cluster) {
        if (cluster.right != null) {
            String art = cluster.right.toString();
            addHtml(index, art);
            addNodes(index + 1, cluster.right);
        }

        if (cluster.left != null) {
            String art = cluster.left.toString();
            addHtml(index, art);
            addNodes(index + 1, cluster.left);
        }
    }

    private void addHtml(int index, String article) {
         if (article.equals("")) {
            html.add(index,"<li><ul>");
            html.add(index + 1,"</ul></li>");
        } else {
            article = article.replaceAll("\"", "'");
            html.add(index, "<li>" + article + "</li>");
        }
}
}
