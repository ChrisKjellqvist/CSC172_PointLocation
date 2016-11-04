import java.util.ArrayList;

/**
 * Created by chris on 11/1/16.
 */
public class Line implements IntersectionConstants{
    Point[] points = new Point[2];
    double m;
    double b;
    int name;
    public Line(double x1, double y1, double x2, double y2, int name){
        points[0] = new Point(x1, y1);
        points[1] = new Point(x2, y2);
        m = (y2 - y1)/(x2-x1);
        b = y1 - m * x1;
        this.name = name;
    }
    public Line(Point a, Point b, int name){
        points[0] = a;
        points[1] = b;
        m = (b.y - a.y)/(b.x - a.x);
        this.b = a.y - m * a.x;
        this.name = name;

    }
    public Line(double m, double b){
        this.m = m;
        this.b = b;
    }
    public Point getPoint(double x){
        return new Point(x, m*x + b);
    }
    public static Point getIntersection(Line a, Line b){
        Line c = new Line(a.m - b.m, a.b - b.b);
        double zero = -c.b/c.m;
        return a.getPoint(zero);
    }
    public static int getPointPlacement(Line l, Point p) {
        boolean isUp;
        isUp = l.getPoint(p.x).y < p.y;
        if (l.m>0) {
            return isUp?COUNTER_CLOCKWISE:CLOCKWISE;
        } else {
            return isUp?CLOCKWISE:COUNTER_CLOCKWISE;
        }
    }
}
