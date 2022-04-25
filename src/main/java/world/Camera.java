package world;

import java.util.List;

public class Camera {

    public final double SHIFT = 0.25;
    public final double TURN = 1.0;
    public final double ZOOM = 1.0;

    //  == world position ==
    private double xPosition = 0.0;
    private double yPosition = 0.0;
    private double zPosition = 0.0;
    //  == world rotation ==
    private double xRotation = 0.0;
    private double yRotation = 0.0;
    private double zRotation = 0.0;

    private List<Line> world = null;


}
