package game;

/* Add comments here
 * 
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


public class MainGame {
	//inner class
	class DrawingPanel extends JPanel {
		DrawingPanel() {
			this.setBackground(Color.BLACK);
			this.setPreferredSize(new Dimension(PANW,PANH));  //remember that the JPanel size is more accurate than JFrame.
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
//			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			g2.setStroke(new BasicStroke(2));	
		}
	}
	
	
	
	//methods
	void createAndShowGUI() {
//		JFrame frame = new JFrame("Wrrrreeeeee");
//		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );		
//		frame.setResizable(false);
//		
//		frame.add(drawingPanel);
//		frame.pack();
//		frame.setLocationRelativeTo( null );		
//		frame.setVisible(true);		
	}
	
	
	
// required for calling
//	Scanner scanner=new Scanner(System.in);
//	if (scanner.hasNext()) {
//		handelCommand(scanner.nextLine());
//	}
	void handelCommand() {
		Scanner scanner=new Scanner(System.in);
		if (scanner.hasNext()) {
			String[] command=scanner.nextLine().split(" ");
			
			
			
		}
	}
	
	
	
	//global
	//setting
	final static int PANW = 600;
	final static int PANH = 600;
	
	//panel
//	DrawingPanel drawingPanel = new DrawingPanel();
	static int floor = 1;
	
	//command list
	
	//constructor
	MainGame() {
//		createAndShowGUI();
		handelCommand();
	}
	
	public static void main(String[] args) {
		new MainGame();

	}
}

