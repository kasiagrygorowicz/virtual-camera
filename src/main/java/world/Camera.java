package world;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class Camera extends JPanel {

    private static Logger log = LoggerFactory.getLogger(Camera.class);

    //  == camera parameters ==
    private final int width = 12;
    private final int height = 12;
    private double zoom = 2;

    //  == world position ==
    private double positionAxisX = 0.0;
    private double positionAxisY = 0.0;
    private double positionAxisZ = 0.0;

    //  == world rotation angle==
    private double rotationAxisX = 0.0;
    private double rotationAxisY = 0.0;
    private double rotationAxisZ = 0.0;

    private List<Line> objects = null;

    public Camera() {
        this.setBackground(Color.BLACK);
    }

    public void setObjects(List<Line> objects) {
        this.objects = objects;
        this.positionAxisX = 0.0;
        this.positionAxisY = 0.0;
        this.positionAxisZ = 0.0;
        this.rotationAxisX = 0.0;
        this.rotationAxisY = 0.0;
        this.rotationAxisZ = 0.0;
    }

    //  == move camera ==
    public void moveAxisX(double v) {
        System.out.println(positionAxisX);
        positionAxisX += v;
    }

    public void moveAxisY(double v) {
//        System.out.println("moving y");
        positionAxisY += v;
    }

    //  == zoom ==
    public void setZoom(double v) {

        zoom += v;
        System.out.println(zoom);
    }

    public void moveAxisZ(double v) {
        positionAxisZ += v;
    }

    //  == rotate camera ==
    public void rotateAxisX(double v) {
        rotationAxisX += v;
    }

    public void rotateAxisY(double v) {
        rotationAxisY += v;
    }

    public void rotateAxisZ(double v) {
        rotationAxisZ += v;
    }

    //  == transform coordinates of line ==
    private Line transform(Line line) {

//Get co-ordinates of ferst 3D point
        double newX1 = line.getP1().getX() + positionAxisX;
        double newY1 = line.getP1().getY() + positionAxisY;
        double newZ1 = line.getP1().getZ() + positionAxisZ;

        //get co-ordinates of second 3D point
        double newX2 = line.getP2().getX() + positionAxisX;
        double newY2 = line.getP2().getY() + positionAxisY;
        double newZ2 = line.getP2().getZ() + positionAxisZ;

        //X rotation
        double xRadians = (rotationAxisX * Math.PI) / 180;
        newY1 = newY1*Math.cos(xRadians) - newZ1*Math.sin(xRadians);
        newZ1 = newY1*Math.sin(xRadians) + newZ1*Math.cos(xRadians);
        newY2 = newY2*Math.cos(xRadians) - newZ2*Math.sin(xRadians);
        newZ2 = newY2*Math.sin(xRadians) + newZ2*Math.cos(xRadians);

        //Y rotation
        double yRadians = (rotationAxisY * Math.PI) / 180;
        newX1 = newZ1*Math.sin(yRadians) + newX1*Math.cos(yRadians);
        newZ1 = newZ1*Math.cos(yRadians) - newX1*Math.sin(yRadians);
        newX2 = newZ2*Math.sin(yRadians) + newX2*Math.cos(yRadians);
        newZ2 = newZ2*Math.cos(yRadians) - newX2*Math.sin(yRadians);

        //Z rotation
        double zRadians = (rotationAxisZ * Math.PI) / 180;
        newX1 = newX1*Math.cos(zRadians) - newY1*Math.sin(zRadians);
        newY1 = newX1*Math.sin(zRadians) + newY1*Math.cos(zRadians);
        newX2 = newX2*Math.cos(zRadians) - newY2*Math.sin(zRadians);
        newY2 = newX2*Math.sin(zRadians) + newY2*Math.cos(zRadians);

        return new Line(
                new Point(newX1, newY1, newZ1),
                new Point(newX2, newY2, newZ2)
        );
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
//        System.out.println("painting");


        if (objects != null) {
//            System.out.println("in if");
            for (Line line : objects) {
//                System.out.println("in the loop");
                Line transformed = transform(line);
//                System.out.println(line.toString());
//todo ?
                if (transformed.getP1().getZ() > 0 || transformed.getP2().getZ() > 0) {
                    double x1, y1, x2, y2;
                    int xScaled, yScaled, xPositioned, yPositioned;

                    x1 = transformed.getP1().getX() * zoom / (transformed.getP1().getZ() + zoom);
                    y1 = -transformed.getP1().getY() * zoom / (transformed.getP1().getZ() + zoom);
                    x2 = transformed.getP2().getX() * zoom / (transformed.getP2().getZ() + zoom);
                    y2 = -transformed.getP2().getY() * zoom / (transformed.getP2().getZ() + zoom);

//                  == scale ==
                    xScaled = getSize().width / width;
                    yScaled = getSize().height / height;

                    xPositioned = getSize().width / 2;
                    yPositioned = getSize().height / 2;

                    log.debug("\tx1: {}\ty1: {}\tx2: {}\ty2: {}", (int) Math.ceil(x1 * xScaled + xPositioned), (int) Math.ceil(y1 * yScaled + yPositioned), (int) Math.ceil(x2 * xScaled + xPositioned), (int) Math.ceil(y2 * yScaled + yPositioned));


                    g2D.setStroke(new BasicStroke(2));
                    g2D.setColor(new Color(204, 204, 204));
                    g2D.drawLine(
                            (int) Math.ceil(x1 * xScaled + xPositioned),
                            (int) Math.ceil(y1 * yScaled + yPositioned),
                            (int) Math.ceil(x2 * xScaled + xPositioned),
                            (int) Math.ceil(y2 * yScaled + yPositioned)
                    );
                }
            }
        }
    }
}
