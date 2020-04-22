package nc.unc;

import org.junit.Test;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


public class DMUtilsTest {

    @Test
    public void getLeftPointTest (){
        List<Point> tableau = new ArrayList<>();
        tableau.add(new Point(1,1));
        tableau.add(new Point(1,2));
        tableau.add(new Point(1,3));
        tableau.add(new Point(2,1));
        tableau.add(new Point(2,2));
        tableau.add(new Point(2,3));
        tableau.add(new Point(1,1));
        tableau.add(new Point(2,2));
        tableau.add(new Point(3,3));

        assertEquals(DMUtils.getPointOnLeft(tableau, new Point(1,1)), Optional.empty());
        assertEquals(DMUtils.getPointOnLeft(tableau, new Point(3,1)), Optional.of(new Point(2,1)));

    }
}
