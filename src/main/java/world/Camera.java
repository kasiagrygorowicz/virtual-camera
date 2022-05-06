package world;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class Camera extends JPanel {

//  == camera parameters ==
    private final int width = 12;
    private final int height =12;
    private double zoom = 5;

    //  == world position ==
    private double positionAxisX = 0.0;
    private double positionAxisY = 0.0;
    private double positionAxisZ = 0.0;

    //  == world rotation angle==
    private double rotationAxisX = 0.0;
    private double rotationAxisY = 0.0;
    private double rotationAxisZ = 0.0;

    private Set<Line> objects = null;

    public Camera(){
        this.setBackground(Color.BLACK);
    }

    public void setObjects(Set<Line> objects){
        this.objects = objects;
        this.positionAxisX = 0.0;
        this.positionAxisY = 0.0;
        this.positionAxisZ = 0.0;
        this.rotationAxisX = 0.0;
        this.rotationAxisY = 0.0;
        this.rotationAxisZ = 0.0;
    }

//  == move camera ==
    public void moveAxisX(double v){
        System.out.println(positionAxisX);
        positionAxisX += v;
    }

    public void moveAxisY(double v){
//        System.out.println("moving y");
        positionAxisY += v;
    }

//  == zoom ==
    public void setZoom(double v){

        zoom +=v;
        System.out.println(zoom);
    }

    public void moveAxisZ(double v){
        positionAxisZ += v;
    }

//  == rotate camera ==
    public void rotateAxisX(double v){
        rotationAxisX += v;
    }

    public void rotateAxisY(double v){
        rotationAxisY += v;
    }

    public void rotateAxisZ(double v){
        rotationAxisZ += v;
    }

//  == transform coordinates of line ==
    private Line transform(Line line){
//        new coordinates
        double newX1, newY1, newZ1, newX2, newY2, newZ2;
//        point 1
        double x1 = line.getP1().getX();
        double y1 = line.getP1().getY();
        double z1 = line.getP1().getZ();

//        point 2
        double x2 = line.getP2().getX();
        double y2 = line.getP2().getY();
        double z2 = line.getP2().getZ();

//        rotate x axis
        double radiusX = (rotationAxisX *Math.PI) /180;
        newY1 =y1*Math.cos(radiusX) - z1*Math.sin(radiusX);
        newZ1 = y1*Math.sin(radiusX) + z1*Math.cos(radiusX);
        newY2 = y2*Math.cos(radiusX) - z2*Math.sin(radiusX);
        newZ2 = y2*Math.cos(radiusX) + z2*Math.cos(radiusX);

        y1 =newY1; z1 =newZ1; y2 =newY2; z2 = newZ2;

//        rotate y axis
        double radiusY = (rotationAxisY *Math.PI) /180;
        newX1 = z1*Math.sin(radiusY) + x1*Math.cos(radiusY);
        newZ1 = z1*Math.cos(radiusY) - x1*Math.sin(radiusY);
        newX2 = z2*Math.sin(radiusY) + x2*Math.cos(radiusY);
        newZ2 = z2*Math.cos(radiusY) - x2*Math.sin(radiusY);

        x1 = newX1; z1 =newZ1; x2 =newX2; z2 =newZ2;

//        rotate z axis
        double radiusZ = (rotationAxisZ *Math.PI) /180;
        newX1 = x1*Math.cos(radiusZ) - y1*Math.sin(radiusZ);
        newY1 = x1*Math.sin(radiusZ) + y1*Math.cos(radiusZ);
        newX2 = x2*Math.cos(radiusZ) - y2*Math.sin(radiusZ);
        newY2 = x2*Math.sin(radiusZ) + y2*Math.cos(radiusZ);

        x1 = newX1; y1 =newY1; x2 =newX2; y2 =newY2;

        return new Line(new Point(x1,y1,z1),new Point(x2,y2,z2));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
//        System.out.println("painting");


        if(objects!=null){
//            System.out.println("in if");
            for(Line line : objects){
//                System.out.println("in the loop");
                Line transformed = transform(line);
//                System.out.println(line.toString());
//todo ?
                if(transformed.getP1().getZ()>0 || transformed.getP2().getZ() >0){
                    double x1, y1, x2, y2;
                    int xScaled, yScaled, xPositioned, yPositioned;

                    x1 = transformed.getP1().getX()* zoom /(transformed.getP1().getZ()+ zoom);
                    y1 = -transformed.getP1().getY()* zoom /(transformed.getP1().getZ()+ zoom);
                    x2 = transformed.getP2().getX()* zoom /(transformed.getP1().getZ()+ zoom);
                    y2 = -transformed.getP2().getY()* zoom /(transformed.getP1().getZ()+ zoom);

//                  == scale ==
                    xScaled =getSize().width/width;
                    yScaled = getSize().height/height;

                    xPositioned =getSize().width/2;
                    yPositioned =getSize().height/2;

                    g2D.setStroke(new BasicStroke(1));
                    g2D.setColor(new Color(204, 204, 204));
                    g2D.drawLine(
                            (int)Math.ceil(x1*xScaled+xPositioned),
                            (int)Math.ceil(y1*yScaled+yPositioned),
                            (int)Math.ceil(x2*xScaled+xPositioned),
                            (int)Math.ceil(y2*yScaled+yPositioned)
                    );
                }
            }
        }
    }
}
