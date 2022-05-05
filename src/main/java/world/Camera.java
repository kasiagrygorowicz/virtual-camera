package world;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class Camera extends JPanel {

//  == camera parameters ==
    private final int width = 12;
    private final int height =12;
    private final double zoom =12;

    public final double SHIFT = 0.25;
    public final double TURN = 1.0;
    public final double ZOOM = 1.0;

    //  == world position ==
    private double xPosition = 0.0;
    private double yPosition = 0.0;
    private double zPosition = 0.0;

    //  == world rotation angle==
    private double xRotation = 0.0;
    private double yRotation = 0.0;
    private double zRotation = 0.0;

    private Set<Line> world = null;

    public Camera(){
        this.setBackground(Color.BLACK);
    }

    public void initObjects(Set<Line> objects){
        this.world = world;
        this.xPosition = 0.0;
        this.yPosition = 0.0;
        this.zPosition = 0.0;
        this.xRotation = 0.0;
        this.yRotation = 0.0;
        this.zRotation = 0.0;
    }

//  == move camera ==
    public void mvAxisX(double v){
        xPosition += v;
    }

    public void mvAxisY(double v){
        yPosition += v;
    }

    public void mvAxisZ(double v){
        zPosition += v;
    }

//  == rotate camera ==
    public void rotateAxisX(double v){
        xRotation += v;
    }

    public void rotateAxisY(double v){
        yRotation += v;
    }

    public void rotateAxisZ(double v){
        zRotation += v;
    }


}
