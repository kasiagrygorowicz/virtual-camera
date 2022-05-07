package world;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import world.utils.ROTATION_TYPE;
import world.utils.TransformationUtility;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class Camera extends JPanel {

    private static Logger log = LoggerFactory.getLogger(Camera.class);

    //  == camera parameters ==
    private final int width = 12;
    private final int height = 12;
    private double zoom = 5;


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
        this.rotationAxisX = 0.0;
        this.rotationAxisY = 0.0;
        this.rotationAxisZ = 0.0;
    }

    //  == move camera ==

    public void moveAxisX(double v) {
        for (Line l : objects) {
            l.getP1().setX(l.getP1().getX() + v);
            l.getP2().setX(l.getP2().getX() + v);
        }
    }

    public void moveAxisY(double v) {
        for (Line l : objects) {
            l.getP1().setY(l.getP1().getY() + v);
            l.getP2().setY(l.getP2().getY() + v);
        }
    }

    public void moveAxisZ(double v) {
        for (Line l : objects) {
            l.getP1().setZ(l.getP1().getZ() + v);
            l.getP2().setZ(l.getP2().getZ() + v);
        }
    }

    //  == zoom ==
    public void setZoom(double v) {
        zoom += v;
    }


    //  == rotate camera ==
    public void rotateAxisX(double v) {
        rotationAxisX += v;
        log.debug("Axis x rotation: {}",rotationAxisX);
        for (Line l : objects)
            TransformationUtility.rotate(l,ROTATION_TYPE.X,rotationAxisX,rotationAxisY,rotationAxisZ);
    }

    public void rotateAxisY(double v) {
        rotationAxisY += v;

        for (Line l : objects)
            TransformationUtility.rotate(l,ROTATION_TYPE.Y,rotationAxisX,rotationAxisY,rotationAxisZ);
    }

    public void rotateAxisZ(double v) {
        rotationAxisZ += v;
        for (Line l : objects)
            TransformationUtility.rotate(l,ROTATION_TYPE.Z,rotationAxisX,rotationAxisY,rotationAxisZ);

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        if (objects != null) {
            for (Line line : objects) {
                Line transformed = line;
//todo ?
//                if (transformed.getP1().getZ() > 0 || transformed.getP2().getZ() > 0) {
                double x1, y1, x2, y2;
                int xScaled, yScaled, xPositioned, yPositioned;

                x1 = transformed.getP1().getX() * zoom / (transformed.getP1().getZ() + zoom);
                y1 = -transformed.getP1().getY() * zoom / (transformed.getP1().getZ() + zoom);
                x2 = transformed.getP2().getX() * zoom / (transformed.getP2().getZ() + zoom);
                y2 = -transformed.getP2().getY() * zoom / (transformed.getP2().getZ() + zoom);

//                  == scale ==
                xScaled = getSize().width / width;
                yScaled = getSize().height / height;

                xPositioned = 300;
                yPositioned = getSize().height / 2;

//                log.debug("\tx1: {}\ty1: {}\tx2: {}\ty2: {}", (int) Math.ceil(x1 * xScaled + xPositioned), (int) Math.ceil(y1 * yScaled + yPositioned), (int) Math.ceil(x2 * xScaled + xPositioned), (int) Math.ceil(y2 * yScaled + yPositioned));


                g2D.setStroke(new BasicStroke(2));
                g2D.setColor(new Color(204, 204, 204));
                g2D.drawLine(
                        (int) Math.ceil(x1 * xScaled + xPositioned),
                        (int) Math.ceil(y1 * yScaled + yPositioned),
                        (int) Math.ceil(x2 * xScaled + xPositioned),
                        (int) Math.ceil(y2 * yScaled + yPositioned)
                );
//                }
            }
        }
    }
}
