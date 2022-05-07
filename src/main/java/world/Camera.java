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

//        point 1
        double x1 = line.getP1().getX() + positionAxisX;
        double y1 = line.getP1().getY() + positionAxisY;
        double z1 = line.getP1().getZ() + positionAxisZ;

//        point 2
        double x2 = line.getP2().getX() + positionAxisX;
        double y2 = line.getP2().getY() + positionAxisY;
        double z2 = line.getP2().getZ() + positionAxisZ;

//        rotate x axis
        double radiusX = (rotationAxisX * Math.PI) / 180;
        y1 = y1 * Math.cos(radiusX) - z1 * Math.sin(radiusX);
        z1 = y1 * Math.sin(radiusX) + z1 * Math.cos(radiusX);
        y2 = y2 * Math.cos(radiusX) - z2 * Math.sin(radiusX);
        z2 = y2 * Math.cos(radiusX) + z2 * Math.cos(radiusX);

//        rotate y axis
        double radiusY = (rotationAxisY * Math.PI) / 180;
        x1 = z1 * Math.sin(radiusY) + x1 * Math.cos(radiusY);
        z1 = z1 * Math.cos(radiusY) - x1 * Math.sin(radiusY);
        x2 = z2 * Math.sin(radiusY) + x2 * Math.cos(radiusY);
        z2 = z2 * Math.cos(radiusY) - x2 * Math.sin(radiusY);

//        rotate z axis
        double radiusZ = (rotationAxisZ * Math.PI) / 180;
        x1 = x1 * Math.cos(radiusZ) - y1 * Math.sin(radiusZ);
        y1 = x1 * Math.sin(radiusZ) + y1 * Math.cos(radiusZ);
        x2 = x2 * Math.cos(radiusZ) - y2 * Math.sin(radiusZ);
        y2 = x2 * Math.sin(radiusZ) + y2 * Math.cos(radiusZ);

        return new Line(new Point(x1, y1, z1), new Point(x2, y2, z2));
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
