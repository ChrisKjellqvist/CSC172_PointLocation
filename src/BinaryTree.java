/**
 * Created by chris on 11/1/16.
 */
public class BinaryTree {
    Node root;
    public void insert(Point a, Point b, int name){
        if (root == null){
            root = new Node(new Line(a, b, name));
            root.line.name = name;
        } else {
            root.insert(a, b, name);
        }
    }
    public void insert(Line a){
        if (root == null){
            root = new Node(a);
        } else {
            root.insert(a.points[0], a.points[1], a.name);
        }
    }
    void printPreOrder(){
        if (root != null){
            root.printPreOrder();
        }
    }
    String getPosition(Point p){
        return root.getPosition(p);
    }
}
class Node implements IntersectionConstants{
    public Node(Line l){
        line = l;
    }
    Line line;
    Node counterclockwise;
    Node clockwise;
    public void insert(Point a, Point b, int name){
        int orA = Line.getPointPlacement(line, a);
        int orB = Line.getPointPlacement(line, b);
        if (orA != orB){
            Point clockwisePoint = (orA==CLOCKWISE)?a:b;
            Point counterclockwisePoint = (orA==CLOCKWISE)?b:a;
            if (counterclockwise==null)
                counterclockwise=new Node(new Line(a, b, name));
            else
                counterclockwise.insert(counterclockwisePoint, Line.getIntersection(line, new Line(a, b, name)), name);
            if (clockwise==null)
                clockwise = new Node(new Line(a, b, name));
            else
                clockwise.insert(clockwisePoint, Line.getIntersection(line, new Line(a, b, name)), name);
        } else {
            switch (orA){
                case COUNTER_CLOCKWISE:
                    if (counterclockwise == null)
                        counterclockwise = new Node(new Line(a, b, name));
                    else
                        counterclockwise.insert(a, b, name);
                    break;
                case CLOCKWISE:
                    if (clockwise == null)
                        clockwise = new Node(new Line(a, b, name));
                    else
                        clockwise.insert(a, b, name);
            }
        }
    }
    public void printPreOrder(){
        System.out.print(line.name + " ");
        if (counterclockwise!=null)
            counterclockwise.printPreOrder();
        if (clockwise!=null)
            clockwise.printPreOrder();
    }
    public String getPosition(Point p){
        int orien = Line.getPointPlacement(line, p);
        switch (orien){
            case CLOCKWISE:
                if (clockwise==null)
                    return Integer.toString(orien);
                else
                    return clockwise.getPosition(p) + orien;
            case COUNTER_CLOCKWISE:
                if (counterclockwise==null)
                    return Integer.toString(orien);
                else
                    return counterclockwise.getPosition(p) + orien;
            default:
                return null;
        }
    }
}
