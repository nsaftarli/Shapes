import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D.Double;
import java.awt.geom.*;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class EdgeNode extends GraphElement
{
	private static final int WIDTH = 70;
	private static final int HEIGHT = 20;
	private int x, y;
	Line2D edge;

	public EdgeNode()
	{
		edge = new Line2D.Double();
		
	}
/**
Creates an EdgeNode that starts at given parameters.
@param x1 x co-ordinate at which to create the object
@param y1 y co-ordinate at which to create the object
**/
	public EdgeNode(int x1, int y1)
	{
		edge = new Line2D.Double(x1, y1, x1, y1);
	}
/**
Responsible for drawing the object. 
@param g2 Graphics2D object to be drawn
**/
	public void draw(Graphics2D g2)
	{
		g2.setColor(color);
		g2.draw(edge);
	}
/**
Returns true if x and y co-ordinates are fewer than 10 square pixels away from the object
@param x x co-ordinate to be checked for proximity
@param y y co-ordinate to be checked for proximity
**/
	public boolean isClicked(double x, double y)
	{
			if(edge.ptSegDist(x, y) < 10)
			{
				selected = true;
			}
			else
			{
				selected = false;
			}
			return selected;
	
	}
/**
Always returns false, as a Label can not be applied to this object.
**/
	public boolean applyLabel()
	{
			return false;
	}

/**
Method to specify the movement of end point of the line. Start point remains fixed
@param xLoc x co-ordinate to which to move the end point.
@param yLoc y co-ordinate to which to move the end point.
**/
	public void moveTo (double xLoc, double yLoc)
	{
	    super.moveTo(xLoc, yLoc);
	    edge.setLine(edge.getX1(), edge.getY1(), xLoc, yLoc);
	}
}
