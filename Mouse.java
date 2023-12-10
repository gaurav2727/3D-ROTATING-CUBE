package com.miniproject;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener
{
    private int mouseX = -1;
    private int mouseY = -1;
    private int mouseB = -1;
    private int scroll = 0;

    public int getX()
    {
        return this.mouseX;
    }
    public int getY()
    {
        return this.mouseY;
    }

    public boolean isScrollingUp()
    {
        return this.scroll== -1;
    }

    public boolean isScrollingDown()
    {
        return this.scroll== 1;
    }

    public void resetScroll()
    {
        this.scroll=0;
    }

    public ClickType getButton()
    {
        switch (this.mouseB)
        {
            case 1:
                return ClickType.LeftClick;
            case 3:
                return ClickType.RightClick;
            default:
                return ClickType.Unknown;
        }
    }

    public void resetButton()
    {
        this.mouseB=-1;
    }

    @Override
    public void mouseDragged(MouseEvent event)
    {
        //System.out.println("Mouse dragged");
        this.mouseX = event.getX();
        this.mouseY = event.getY();
    }

    @Override
    public void mouseMoved(MouseEvent event)
    {
        //System.out.println("Mouse moved");
        this.mouseX = event.getX();
        this.mouseY = event.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        System.out.println("Mouse clicked");
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        System.out.println("Mouse pressed");
        this.mouseB = e.getButton();
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        this.mouseB=-1;
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        System.out.println("Mouse entered");
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        System.out.println("Mouse exited");
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent event)
    {
        scroll=event.getWheelRotation();
    }
}
