import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.awt.Font;
import java.awt.geom.Ellipse2D;

public class EllipseNode extends GraphElement
{
	private static final int WIDTH = 70;
	private static final int HEIGHT = 30;
	private int x, y;
	Ellipse2D ell;
	
	public EllipseNode()
	{
		ell = new Ellipse2D.Double();
	}
/**
Creates an EllipseNode at given locations
@param x x co-ordinate at which to create the ellipse node
@param y y co-ordinate at which to create the ellipse node
**/
	public EllipseNode(int x, int y)
	{
		ell = new Ellipse2D.Double(x, y, WIDTH, HEIGHT);
	}
	
/**
Responsible for drawing the node and its labels. 
@param g2 the Graphics2D object to be drawn
**/
	public void draw(Graphics2D g2)
	{
		g2.setColor(color);
		g2.draw(ell);
		g2.setFont(new Font("SansSerif", Font.PLAIN, 10));
		g2.drawString(getLabel(), (int) ell.getX(), (int) ell.getY() + 15);

	}
/**
Checks if object contains x and y co-ordinates given
@param x checks if given x co-ordinate exists in object
@param y checks if given y co-ordinate exists in object
@return True if the node contains both values, false otherwise
**/
	public boolean isClicked(double x, double y)
	{
			if(ell.contains(x, y))
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
Checks if object can have a label applied to it
@return returns true if a label can be applied, false otherwise.
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
Responsible for moving the object
@param xLoc x co-ordinate to which to move the object
@param yLoc y co-ordinate to which to move the object
**/
	public void moveTo (double xLoc, double yLoc)
	{
	    super.moveTo(xLoc, yLoc);
	    ell.setFrame(getXPos(), getYPos(), WIDTH, HEIGHT);
	}
}
