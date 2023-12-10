package com.miniproject;

import java.awt.*;

public class PointConverter
{
    private static double scale = 1;
    private static final double ZoomFactor = 1.2;

    public static void zoomIn()
    {
        scale*=ZoomFactor;
    }
    public static void zoomOut()
    {
        scale/=ZoomFactor;
    }

    public static Point convertPoint(Point3D point3D)
    {
        double x3d = point3D.y*scale; //Actual x coordinate of the point in 3D
        double y3d = point3D.z*scale; //Actual y coordinate of the point in 3D
        double depth = point3D.x*scale; //Actual z coordinate of the point in 3D
        double[] newVal = scale(x3d,y3d,depth);

        int x2d = (int)(Display.WIDTH/2 + newVal[0]);
        int y2d = (int)(Display.HEIGHT/2 - newVal[1]);
        Point point2d = new Point(x2d,y2d);
        return point2d;
    }

    private static double[] scale(double x3d, double y3d, double depth)
    {
        double dist = Math.sqrt(x3d*x3d+y3d*y3d);
        double theta = Math.atan2(y3d, x3d);
        double depth2 = 15 - depth; //Doubt
        double localScale = Math.abs(1400/(1400+depth2));
        dist *= localScale;
        double[] newVal = new double[2];
        newVal[0] = dist*Math.cos(theta);
        newVal[1] = dist*Math.sin(theta);
        return newVal;
    }

    public static void rotateAxisX(Point3D p, double degrees)
    {
        double radius = Math.sqrt(p.y*p.y+p.z*p.z);
        double theta = Math.atan2(p.z, p.y);
        theta += 2*Math.PI/360*degrees;
        p.y = radius* Math.cos(theta);
        p.z = radius* Math.sin (theta);
    }

    public static void rotateAxisY(Point3D p, double degrees)
    {
        double radius = Math.sqrt(p.x*p.x+p.z*p.z);
        double theta = Math.atan2(p.x, p.z);
        theta += 2*Math.PI/360*degrees;
        p.x = radius* Math.sin(theta);
        p.z = radius* Math.cos(theta);
    }

    public static void rotateAxisZ(Point3D p, double degrees)
    {
        double radius = Math.sqrt(p.y*p.y+p.x*p.x);
        double theta = Math.atan2(p.y, p.x);
        theta += 2*Math.PI/360*degrees;
        p.y = radius* Math.sin(theta);
        p.x = radius* Math.cos(theta);
    }
}

