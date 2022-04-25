package world;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Point {

    //  == coordinates ==
    private double x;
    private double y;
    private double z;

    public double[] getCoordinates() {
        return new double[]{x, y, z};
    }

    public void setCoordinates(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

}
