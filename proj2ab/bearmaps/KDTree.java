package bearmaps;
import java.util.List;

public class KDTree implements PointSet {
    private static final boolean HORIZONTAL = false;
    private Node root;
    private class Node implements Comparable{
        private Node left;
        private Node right;
        private Point p;
        private boolean orientation;

        private Node(Point p, boolean orientation) {
            this.p = p;
            this.orientation = orientation;
        }

        private Point getPoint() {
            return p;
        }

        private boolean getOrientation() {
            return orientation;
        }

        @Override
        public int compareTo(Object o) {
            if (getOrientation()) {
                return Double.compare(p.getY(),((Node) o).getPoint().getY());
            } else {
                return Double.compare(p.getX(),((Node) o).getPoint().getX());
            }
        }

    }
    public KDTree(List<Point> points) {
        for (Point point: points) {
            insert(point);
        }
    }

    public void insert(Point p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        root = insert(p, root, HORIZONTAL);
    }

    private Node insert(Point p, Node T, boolean orientation) {
        if (T == null ) {
            return new Node(p, orientation);
        }
        if (T.getPoint().equals(p)) {
            return T;
        }
        int comp = new Node(p, orientation).compareTo(T);
        if (comp > 0) {
            T.right = insert(p, T.right, !orientation);
        } else {
            T.left = insert(p, T.left, !orientation);
        }
        return T;
    }


    @Override
    public Point nearest(double x, double y) {
        return nearestHelper(root,root, new Point(x, y), HORIZONTAL).getPoint();
    }

    private Node nearestHelper(Node N, Node best, Point target, boolean orientation) {
        Node goodSide;
        Node badSide;
        if (N == null) {
            return best;
        }
        double curDistance = Point.distance(N.getPoint(), target);
        double bestDistance = Point.distance(best.getPoint(),target);
        if (Double.compare(curDistance, bestDistance) < 0) best = N;

        if (new Node(target, orientation).compareTo(N) > 0) {
            goodSide = N.right;
            badSide = N.left;
        } else {
            goodSide = N.left;
            badSide = N.right;
        }
        best = nearestHelper(goodSide, best, target, !orientation);
        if (checker(N, target, bestDistance)) {
            best = nearestHelper(badSide, best, target, !orientation);
        }
        return best;
    }

    private boolean checker(Node cur, Point target, Double Length) {
        if (cur.getOrientation()) {
            return Math.pow(cur.getPoint().getY() - target.getY(), 2) < Length;
        } else {
            return Math.pow(cur.getPoint().getX() - target.getX(), 2) < Length;
        }
    }
}