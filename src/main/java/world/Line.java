package world;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Line {

    private Point p1;
    private Point p2;

    public Point[] getLine() {
        return new Point[] {p1, p2};
    }
}
