package maze.helpingclasses;

import java.awt.*;
import java.util.Comparator;

public class PointCompare implements Comparator<Point> {
    public int compare(Point a, Point b) {
        if (a.x < b.x) {
            return -1;
        }
        else if (a.x > b.x) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
