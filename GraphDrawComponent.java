import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

/*
The majority of everything that happens is here.
*/

public class GraphDrawComponent extends JComponent
{
	/* 
	Variables:
	elements is the ArrayList that holds the nodes
	font is mostly just for labeling purposes
	whichButton keeps track of which button was pressed last. Used to make sure that only one object is created at a time.
	dragging is a boolean that keeps track of when the edge is placed at its final location */
	static ArrayList<GraphElement> elements;
	Font font = new Font("SansSerif", Font.PLAIN, 10);
	JLabel label = new JLabel();
	private int numInArray = 0;
	private int x = 50;
	private int y = 50;
	private final int WIDTH = 30;
	private final int HEIGHT = 10;
	private static int whichButton = 0;
	static boolean dragging;

	public GraphDrawComponent()
	{
		elements = new ArrayList<GraphElement>();
		class MouseClickListener implements MouseListener
		{
			public void mousePressed(MouseEvent event)
			{
				/*
				When the mouse is pressed down, method checks to see if the getButton
				variable is set to create an edge. If so, it creates an edge, and sets
				the button back to 0. */
				if(GraphDrawComponent.getButton() == 3)
				{
					GraphDrawComponent.createEdge(event.getX(), event.getY());
					repaint();
					GraphDrawComponent.setButton(0);
				}
			}
			/* When the mouse button is released, the dragging boolean switches to 
			false, which tells the program that the final location of the end point
			for the line has been placed. */
			public void mouseReleased(MouseEvent event)
			{
				GraphDrawComponent.dragging = false;
			}
			public void mouseClicked(MouseEvent event)
			{
				/*
				Similar to the method inside mousePressed, the first two parts of 
				this section check to see if the button is set to 1 or 2, indicating 
				the creation of a RectangleNode or an EllipseNode, respectively. Sets
				the whichButton variable back to 0 afterwards. */
				if(GraphDrawComponent.getButton() == 1)
				{
					GraphDrawComponent.createRectangle(event.getX(), event.getY());
					repaint();
					GraphDrawComponent.setButton(0);
					return;
				}
				else if(GraphDrawComponent.whichButton == 2)
				{
					GraphDrawComponent.createEllipse(event.getX(), event.getY());
					repaint();
					GraphDrawComponent.setButton(0);
					return;
				}
				/*
				Loops through variables inside the ArrayList. If the right mouse button
				is clicked, it checks to see if the variable is selected. If so, it checks
				to see that the right click occurred on top of an array element. If it did,
				it removes the first element, and re-paints the array */
				for(int i = 0; i < elements.size(); i++)
				{
					if(event.getButton() == MouseEvent.BUTTON3)
					{
						if(elements.get(i).selected)
						{
							if(elements.get(i).isClicked(event.getX(), event.getY()))
							{
								elements.remove(i);
								repaint();
							}
							elements.get(i).selected = false;
						}
					}
					/*
					If the left mouse button is clicked, checks to see if the button was
					clicked on top of an ArrayList element. If so, the selected variable
					returns true, and the element turns blue to indicate selection. If 
					clicked outside an object, the object turns black, and selected 
					becomes false */
					else if(event.getButton() == MouseEvent.BUTTON1)
					{
						if(elements.get(i).isClicked(event.getX(), event.getY()))
						{
							elements.get(i).setColor(Color.BLUE);
							repaint();
							return;
						}
						if(!elements.get(i).isClicked(event.getX(), event.getY()))
						{
							elements.get(i).setColor(Color.BLACK);
							elements.get(i).selected = false;
							repaint();
						}
						

					}
				
				}
			}			
			public void mouseEntered(MouseEvent event)
			{}
			public void mouseExited(MouseEvent event)
			{}
		}
		MouseListener clickListener = new MouseClickListener();
		this.addMouseListener(clickListener);
	
		class MouseMoveListener implements MouseMotionListener
		{
			public void mouseDragged(MouseEvent event)
			{
				/* Loops through elements in the ArrayList. Checks to see if dragging
				returns true. If so, the top array element is an edge, and it can be
				drawn by dragging the mouse. Otherwise, it checks to see if an element
				is selected. If any are, it allows the user to move the object by dragging
				the mouse. */
				for(int i = 0; i < elements.size(); i++)
				{
					if(GraphDrawComponent.dragging)
					{
						elements.get(elements.size() - 1).moveTo(event.getX(), event.getY());
						repaint();
					}
					else
					{
						if(elements.get(i).selected)
						{
							elements.get(i).moveTo(event.getX(), event.getY());
							repaint();
						}	
					}
				}
			}
			public void mouseMoved(MouseEvent event)
			{}
		}
		MouseMotionListener moveListener = new MouseMoveListener();
		this.addMouseMotionListener(moveListener);
	}
	/**
	Sets the whichButton variable to whatever is passed to it by the parameter.
	Method is called by main method.
	@param number the number to set the whichButton variable to.
	**/
	public static void setButton(int number)
	{
		whichButton = number;
	}
	/**
	Gets the current value of the whichButton variable.
	@return the value of the whichButton variable
	**/
	public static int getButton()
	{
		return whichButton;
	}
	/**
	Draws all elements in the ArrayList
	@param g the Graphics objects that are passed into this method as parameters
	**/
	public void paintComponent(Graphics g)
	{
		for(int i = 0; i < elements.size(); i++)
		{
			Graphics2D g2 = (Graphics2D) g;
			elements.get(i).draw(g2);
		}
	}
	/**
	Creates a RectangleNode at a given position. Called if whichButton==1 parameters are
	locations of the mouse click on the component
	@param x x co-ordinate at which to create a RectangleNode
	@param y y co-ordinate at which to create a RectangleNode
	**/
	public static void createRectangle(int x, int y)
	{
		elements.add(new RectangleNode(x, y));
	}
	/**
	Creates an EllipseNode at a given position. Called if whichButton==2. Parameters are
	locations of the mouse click on the component. 
	@param x x co-ordinate at which to create an EllipseNode
	@param y y co-ordinate at which to create an EllipseNode
	**/
	public static void createEllipse(int x, int y)
	{
		elements.add(new EllipseNode(x, y));
	}
	/**
	Creates an EdgeNode at a given position. Called if whichButton==3. Parameters are
	locations of the mouse click on the component. 
	@param x x co-ordinate at which to create the anchor of an EdgeNode
	@param y y co-ordinate at which to create the anchor of an EdgeNode
	**/
	public static void createEdge(int x, int y)
	{
		elements.add(new EdgeNode(x, y));
		dragging = true;
	}
	/**
	Adds a label to the object
	@param alabel the string to draw on the object
	**/
	public void addLabel(String alabel)
	{
		/*
		Loops through the array and checks for the selected element. If an element is
		selected, it applies the given string parameter to the object, labeling it */
		for(int i = 0; i < elements.size(); i++)
		{
			if(elements.get(i).selected)
			{
				label.setText(alabel);
				elements.get(i).setLabel(label.getText());
				repaint();
			}
		}
	}
	
}
