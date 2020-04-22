package nc.unc;

import java.awt.Point;
import java.util.List;
import java.util.Optional;


public class DMUtils {

    public static Optional<Point> getPointOnLeft(List<Point> tableau, Point p){
        return tableau.stream()
                .filter(point -> point.x == p.x-1)
                .findFirst();
    }

    public static Optional<Point> getPointOnRight(List<Point> tableau, Point p){
        return tableau.stream()
                .filter(point -> point.x == p.x+1)
                .findFirst();
    }

    public static Optional<Point> getPointOnTop(List<Point> tableau, Point p){
        return tableau.stream()
                .filter(point -> point.y == p.y+1)
                .findFirst();
    }

    public static Optional<Point> getPointOnBottom(List<Point> tableau, Point p){
        return tableau.stream()
                .filter(point -> point.x == p.y-1)
                .findFirst();
    }

    public static List<Point> getAttenantsPoints(List<Point> tableau, Point p){
        return null; // TODO
    }
}
