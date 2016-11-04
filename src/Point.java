/**
 * Created by chris on 11/1/16.
 */
public class Point {
    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public boolean isInUnitSquare(){
        return (x<=1&&x>=0&&y<=1&&y>=0);
    }
}
