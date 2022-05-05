package world;

import lombok.*;

@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Line {

    @EqualsAndHashCode.Include
    private Point p1;
    @EqualsAndHashCode.Include
    private Point p2;

    public Point[] getLine() {
        return new Point[] {p1, p2};
    }
}
