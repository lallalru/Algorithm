package Week04;
import java.util.Arrays;
import java.util.Comparator;


class XY {
    public Double X;
    public Double Y;

    public XY(Double x, Double y) {
        X = x;
        Y = y;
    }

    public String toString() {
        return "(" + X + ", " + Y + ")";
    }
}

public class X_Sort implements Comparator<XY> {
    @Override
    public int compare(XY xy1, XY xy2) {
        return (int) (xy1.X - xy2.X);
    }
}

