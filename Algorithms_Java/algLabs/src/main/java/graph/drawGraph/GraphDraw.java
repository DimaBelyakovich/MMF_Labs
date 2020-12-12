package graph.drawGraph;

import graph.Edge;
import graph.Vertex;

import java.util.*;
import java.awt.*;
import javax.swing.*;

public class GraphDraw extends JFrame {
    private int width;
    private int height;

    private ArrayList<NodeFrame> nodes;
    private ArrayList<EdgeFrame> edges;

    public GraphDraw(String title) {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.nodes = new ArrayList<NodeFrame>();
        this.edges = new ArrayList<EdgeFrame>();
        this.width = 30;
        this.height = 30;
    }

    public void addNode(Vertex vertex, int x, int y) {
        this.nodes.add(new NodeFrame(vertex,x,y));
        this.repaint();
    }

    public void addEdge(Edge e) {
        this.edges.add(new EdgeFrame(e));
        this.repaint();
    }

    public void paint(Graphics g) {
        FontMetrics f = g.getFontMetrics();
        int nodeHeight = Math.max(height, f.getHeight());

        g.setColor(Color.black);
        for (EdgeFrame e : edges) {
            int xFrom = nodes.get(e.from).x;
            int yFrom = nodes.get(e.from).y;
            int xTo = nodes.get(e.to).x;
            int yTo = nodes.get(e.to).y;

            g.drawLine(xFrom, yFrom, xTo, yTo);
        }

        for (NodeFrame n : nodes) {
            int nodeWidth = Math.max(width, f.stringWidth(n.name) + width/2);

            g.setColor(Color.white);
            g.fillOval(n.x-nodeWidth/2, n.y-nodeHeight/2, nodeWidth, nodeHeight);

            g.setColor(Color.black);
            g.drawOval(n.x-nodeWidth/2, n.y-nodeHeight/2, nodeWidth, nodeHeight);

            g.drawString(String.valueOf(n.name), n.x-f.stringWidth(n.name)/2, n.y+f.getHeight()/2);
        }
    }
}


