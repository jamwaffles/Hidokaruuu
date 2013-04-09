import java.util.*;

class Node {
    public final List<Arc> out = new ArrayList<Arc>();
    public Object pathId;
    public Arc pathOut;
    public int label;

    public void addEdge(Node that) {
        Arc arc = new Arc(this, that);
        this.out.add(arc.reverse);
        that.out.add(arc);
    }
}

class Arc {
    public final Node head;
    public final Arc reverse;

    private Arc(Node head, Arc reverse) {
        this.head = head;
        this.reverse = reverse;
    }

    public Arc(Node head, Node tail) {
        this.head = head;
        this.reverse = new Arc(tail, this);
    }
}

public class AV {
    public static void main(String[] args) {
        // construct an n-by-n grid
        int n = 12;
        Node[][] node = new Node[n][n];

        // Fill grid with nodes
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                node[i][j] = new Node();
            }
        }

        // Loop through grid. Create top-left-up edges from current point
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i >= 1) {
                    if (j >= 1) {
                        node[i - 1][j - 1].addEdge(node[i][j]);
                    }
                    node[i - 1][j].addEdge(node[i][j]);
                    if (j < n - 1) {
                        node[i - 1][j + 1].addEdge(node[i][j]);
                    }
                }
                if (j >= 1) {
                    node[i][j - 1].addEdge(node[i][j]);
                }
            }
        }
        Random random = new Random();
        Node source = node[random.nextInt(n)][random.nextInt(n)];
        findPath(n * n - 1, random, source);
        labelPath(source);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%4d", node[i][j].label);
            }
            System.out.println();
        }
    }

    private static void findPath(int length, Random random, Node source) {
        Object pathId = new Object();
        source.pathId = pathId;
        Node sink = source;
        while (length > 0) {
            sink.pathOut = sink.out.get(random.nextInt(sink.out.size()));
            sink = sink.pathOut.head;
            if (sink.pathId != pathId) {
                // extend
                sink.pathId = pathId;
                length--;
            } else {
                // rotate
                sink = sink.pathOut.head;
                Arc reverse = null;
                Node node = sink;
                do {
                    Arc temp = node.pathOut;
                    node.pathOut = reverse;
                    reverse = temp.reverse;
                    node = temp.head;
                } while (node != sink);
            }
        }
    }

    private static void labelPath(Node source) {
        int count = 0;
        while (true) {
            source.label = ++count;
            if (source.pathOut == null) {
                break;
            }
            source = source.pathOut.head;
        }
    }
}