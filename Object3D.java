package com.miniproject;
import java.awt.Color;
import java.awt.Graphics;
public class Object3D
{
    private Polygon3D[] polygons;
    private Color color;

    public Object3D(Color color, Polygon3D... polygons)
    {
        this.color = color;
        this.polygons = polygons;
        this.setPolygonColor();
    }

    public Object3D(Polygon3D... polygons)
    {
        //this.color = Color.WHITE;
        this.polygons = polygons;
    }

    public void render(Graphics g)
    {
        for(Polygon3D poly: this.polygons)
        {
            poly.render(g);
        }
    }

    public void rotate(double xDegrees, double yDegrees, double zDegrees)
    {
        for(Polygon3D p: this.polygons)
        {
            p.rotate(xDegrees,yDegrees,zDegrees);
        }
        this.sortPolygons();
    }

    public void sortPolygons()
    {
        Polygon3D.sortPolygons(this.polygons);
    }

    public void setPolygonColor()
    {
        for(Polygon3D poly: this.polygons)
        {
            poly.setColor(this.color);
        }
    }
}
