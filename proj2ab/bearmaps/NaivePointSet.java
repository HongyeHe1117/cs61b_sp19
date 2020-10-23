package bearmaps;
import java.util.List;

public class NaivePointSet implements PointSet {
    private List<Point> points;
    public NaivePointSet(List<Point> points) {
        this.points = points;
    }

    private static double distance(double x1, double x2, double y1, double y2) {
        return Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);
    }

    @Override
    public Point nearest(double x, double y) {
        Point minpoint = null;
        double minDistance = Integer.MAX_VALUE;
        for (Point point: points) {
            double currDistance = NaivePointSet.distance(x, point.getX(), y, point.getY());
            if (currDistance < minDistance) {
                minDistance = currDistance;
                minpoint = point;
            }
        }
        return minpoint;
    }

    public static void main(String[] args) {
        Point p1 = new Point(1.1, 2.2);
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0);
        ret.getX();
        ret.getY();
    }
}
