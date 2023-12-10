package com.miniproject;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Display extends Canvas implements Runnable
{
    private static final long serialVersionUID = 1L;
    private Thread thread;
    private JFrame frame;
    private static String title ="3DAnimation";

    public static int WIDTH;
    public static int HEIGHT;
    private static boolean running =false;

    private Object3D polyhedron;
    private Mouse mouse;
    public Display()
    {
        this.frame= new JFrame();
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame.setMinimumSize(new Dimension(600,600));
        this.mouse= new Mouse();
        this.addMouseListener(this.mouse);
        this.addMouseMotionListener(this.mouse);
        this.addMouseWheelListener(this.mouse);
    }

    public synchronized void start()
    {
        running = true;
        this.thread=new Thread(this,"Display");
        this.thread.start(); //Causes this thread to begin execution; the JVM calls the run method of this thread
    }

    public synchronized void stop()
    {
        running = false;
        try
        {
            this.thread.join();
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        Display display=new Display();
        display.frame.setTitle((title));
        display.frame.add(display);
        display.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.frame.setLocationRelativeTo(null);
        display.frame.setVisible(true);
        display.start();
    }

    @Override
    public void run()
    {
        init();
        while(running)
        {
            render();
            update();

            //this.polyhedron.rotate(true, 0, 0.3, 0);
        }
        stop();
    }

    public void init()
    {
        int s = 100;
        Point3D p1 = new Point3D(s/2,-s/2,-s/2);
        Point3D p2 = new Point3D(s/2,s/2,-s/2);
        Point3D p3 = new Point3D(s/2,s/2,s/2);
        Point3D p4 = new Point3D(s/2,-s/2,s/2);
        Point3D p5 = new Point3D(-s/2,-s/2,-s/2);
        Point3D p6 = new Point3D(-s/2,s/2,-s/2);
        Point3D p7 = new Point3D(-s/2,s/2,s/2);
        Point3D p8 = new Point3D(-s/2,-s/2,s/2);
        this.polyhedron = new Object3D(
                new Polygon3D(Color.BLUE,p5,p6,p7,p8),
                new Polygon3D(Color.WHITE,p1,p2,p6,p5 ),
                new Polygon3D(Color.YELLOW,p1,p5,p8,p4),
                new Polygon3D(Color.GREEN,p2,p6,p7,p3),
                new Polygon3D(Color.PINK,p4,p3,p7,p8),
                new Polygon3D(Color.RED,p1,p2,p3,p4));
    }

    private void render()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs==null)
        {
            this.createBufferStrategy(2);
            return;
        }
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, screenSize.width, screenSize.height);

        polyhedron.render(g);
        g.dispose();
        bs.show();
    }

    int initialX,initialY;
    double mouseSensitivity=2;
    public void update()
    {
        int x=this.mouse.getX();
        int y=this.mouse.getY();
        if(this.mouse.getButton() == ClickType.LeftClick)
        {
            int xDif=x-initialX;
            int yDif=y-initialY;
            this.polyhedron.rotate(
                    0,
                    yDif/mouseSensitivity,
                    xDif/mouseSensitivity);
        }

        else if(this.mouse.getButton() == ClickType.RightClick)
        {
            int xDif=x-initialX;

            this.polyhedron.rotate(
                    xDif/mouseSensitivity,
                    0,
                    0);
        }

        if(this.mouse.isScrollingUp())
        {
            PointConverter.zoomIn();
        }
        else if(this.mouse.isScrollingDown())
        {
            PointConverter.zoomOut();
        }
        this.mouse.resetScroll();
        initialX=x;
        initialY=y;

        Rectangle r = frame.getBounds();
        WIDTH = r.width;
        HEIGHT = r.height;
    }
}

