package controller;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ZoomMouseAdapter extends MouseAdapter 
{
    private int prevX, prevY;
    private JScrollPane scrollPane;

    public ZoomMouseAdapter(JScrollPane scrollPane) 
    {
        this.scrollPane = scrollPane;
    }

    @Override
    public void mouseDragged(MouseEvent e) 
    {
        super.mouseDragged(e);
        int dX = prevX - e.getX();
        int dY = prevY - e.getY();
        scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getValue() + dY);
        scrollPane.getHorizontalScrollBar().setValue(scrollPane.getHorizontalScrollBar().getValue() + dX);
        prevX = e.getX();
        prevY = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {
        prevX = e.getX();
        prevY = e.getY();
    }
}
