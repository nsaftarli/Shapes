//Nariman Saftarli, 500615448

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.awt.Rectangle;
import java.awt.Font;

//Subclass of GraphElement. Holds values and methods necessary to construct a Rectangle node

public class RectangleNode extends GraphElement
{
	private static final int WIDTH = 70;
	private static final int HEIGHT = 30;
	private int x, y;
	Rectangle rec;

	public RectangleNode()
	{
		rec = new Rectangle();
		
	}
/**
Constructs a rectangle node with fixed size at given co-ordinates
@param x x co-ordinate to create rectangle node at
@param y y co-ordinate to create rectangle node at
**/
	public RectangleNode(int x, int y)
	{
		rec = new Rectangle(x, y, WIDTH, HEIGHT);

	}
//draw method allows shape to be drawn, and for its color to be changed when selected
	public void draw(Graphics2D g2)
	{
		g2.setColor(color);
		g2.draw(rec);
		g2.setFont(new Font("SansSerif", Font.PLAIN, 10));
		g2.drawString(getLabel(), (int) rec.getX(), (int) rec.getY() + 15);

	}
/**
Checks to see if object contains x and y values passed to it. Changes select variable
and returns it.
@param x X value
@param y Y value
@return returns True or False if object contains parameters
**/

	public boolean isClicked(double x, double y)
	{
			if(rec.contains(x, y))
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
Checks if a label can be attached to the object.
@return True if a label can be attached, false otherwise.
**/
	public boolean applyLabel()
	{
		
			if(this.getLabel() == "")
			{
				return true;
			}
			else
			{
				return false;
			}
		

	}
/**
Sets the x, y values of the object to ones in parameters
@param xLoc x co-ordinate to be moved to
@param yLoc y co-ordinate to be moved to
**/
	public void moveTo (double xLoc, double yLoc)
	{
	    super.moveTo(xLoc, yLoc);
	    rec.setLocation((int)xLoc, (int) yLoc);
	}


}
