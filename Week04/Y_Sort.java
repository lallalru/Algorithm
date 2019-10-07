package Week04;
import java.util.Arrays;
import java.util.Comparator;


class XY2 {
    public Double X;
    public Double Y;

    public XY2(Double x, Double y) {
        X = x;
        Y = y;
    }

    public String toString() {
        return "(" + X + ", " + Y + ")";
    }
}

public class Y_Sort implements Comparator<XY> {
    @Override
    public int compare(XY xy1, XY xy2) {
        return (int)(xy1.Y - xy2.Y);
    }
}

