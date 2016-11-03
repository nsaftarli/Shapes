//Nariman Saftarli, 500615448

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import javax.swing.JLabel;

/*
This is the main method of the assignment. This class creates the frame, panel, and 
buttons necessary to run the program 
*/


public class GraphAssignment1
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		JTextField inputField = new JTextField("Insert Label Here:");
		frame.setLayout(new BorderLayout());
		JPanel buttonPanel = new JPanel();
		JButton rectangleButton = new JButton("Rectangle");
		JButton labelButton = new JButton("Label");
		JButton ellipseButton = new JButton("Ellipse");
		JButton edgeButton = new JButton("Edge");
		GraphDrawComponent graphComp = new GraphDrawComponent();

		buttonPanel.add(rectangleButton);
		buttonPanel.add(ellipseButton);
		buttonPanel.add(labelButton);
		buttonPanel.add(edgeButton);
		buttonPanel.add(inputField);
		frame.add(buttonPanel, BorderLayout.NORTH);
		frame.add(graphComp, BorderLayout.CENTER);

		frame.setSize(800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		class RectangleButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				/*sets the whichButton variable in graphComp to a 1, which signifies a
				Rectangle*/
				graphComp.setButton(1);
			}
		}

		ActionListener rectList = new RectangleButtonListener();
		rectangleButton.addActionListener(rectList);
		
		class EllipseButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				/*sets the whichButton variable in graphComp to a 2, which signifies an
				Ellipse */
				graphComp.setButton(2);
			}
		}
		ActionListener ellList = new EllipseButtonListener();
		ellipseButton.addActionListener(ellList);

		class EdgeButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				/* Sets the whichButton variable in graphComp to a 3, which signifies an 
				Edge */
				graphComp.setButton(3);
			}
		}
		ActionListener edgeList = new EdgeButtonListener();
		edgeButton.addActionListener(edgeList);

		class LabelButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				for(int i = 0; i < graphComp.elements.size(); i++)
				{
					/* If an element at the ith position of graphComp is selected, a label
					is applied */
					if(graphComp.elements.get(i).selected)
					{
						graphComp.addLabel(inputField.getText());
					}
				}
			}
		}
		ActionListener labList = new LabelButtonListener();
		labelButton.addActionListener(labList);
		frame.setVisible(true);

	}
}
