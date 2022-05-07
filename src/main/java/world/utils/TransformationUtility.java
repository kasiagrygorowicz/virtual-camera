package world.utils;

import world.Line;
import world.Point;

public class TransformationUtility {

    public static void rotate(Line line, ROTATION_TYPE type, double rotationX,double rotationY,double rotationZ) {
        double x1 = line.getP1().getX();
        double y1 = line.getP1().getY();
        double z1 = line.getP1().getZ();
        double x2 = line.getP2().getX();
        double y2 = line.getP2().getY();
        double z2 = line.getP2().getZ();
//        double x1 = line.getP1().getX() + xp;
//        double y1 = line.getP1().getY() + yp;
//        double z1 = line.getP1().getZ() + zp;
//        double x2 = line.getP2().getX() + xp;
//        double y2 = line.getP2().getY() + yp;
//        double z2 = line.getP2().getZ() + zp;
        double rotationRadiansX = (rotationX * Math.PI) / 180;
        double rotationRadiansY = (rotationY * Math.PI) / 180;
        double rotationRadiansZ = (rotationZ * Math.PI) / 180;

//        if (ROTATION_TYPE.X == type) {
            y1 = y1 * Math.cos(rotationRadiansX) - z1 * Math.sin(rotationRadiansX);
            z1 = y1 * Math.sin(rotationRadiansX) + z1 * Math.cos(rotationRadiansX);
            y2 = y2 * Math.cos(rotationRadiansX) - z2 * Math.sin(rotationRadiansX);
            z2 = y2 * Math.sin(rotationRadiansX) + z2 * Math.cos(rotationRadiansX);
//        }

//        if (ROTATION_TYPE.Y == type) {
            x1 = z1 * Math.sin(rotationRadiansY) + x1 * Math.cos(rotationRadiansY);
            z1 = z1 * Math.cos(rotationRadiansY) - x1 * Math.sin(rotationRadiansY);
            x2 = z2 * Math.sin(rotationRadiansY) + x2 * Math.cos(rotationRadiansY);
            z2 = z2 * Math.cos(rotationRadiansY) - x2 * Math.sin(rotationRadiansY);
//        }
//        if (ROTATION_TYPE.Z == type) {
            x1 = x1 * Math.cos(rotationRadiansZ) - y1 * Math.sin(rotationRadiansZ);
            y1 = x1 * Math.sin(rotationRadiansZ) + y1 * Math.cos(rotationRadiansZ);
            x2 = x2 * Math.cos(rotationRadiansZ) - y2 * Math.sin(rotationRadiansZ);
            y2 = x2 * Math.sin(rotationRadiansZ) + y2 * Math.cos(rotationRadiansZ);
//        }

        line.getP1().setX(x1);
        line.getP1().setY(y1);
        line.getP1().setZ(z1);
        line.getP2().setX(x2);
        line.getP2().setY(y2);
        line.getP2().setZ(z2);

    }
}
