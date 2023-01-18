package game;

/* Add comments here
 * 
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


//TODO connect all scanner to player input
//TODO replace all output with draw string

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
			g2.setColor(Color.white);

			drawBorder(g2);
		}
	}

	class KL implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {

			//			System.out.println(e.getKeyCode());
			if (e.getKeyCode()==10) {
				//return command line and empty input box
				currCommand=commandBox.getText().split(" ",2);
				commandBox.setText(" ");
				System.out.println(Arrays.toString(currCommand));


				for (String s:adj) {
					if (s.equals(currCommand[0])) run();
				}


			}
		}

	}

	//methods
	void panelFrameSetup() {
		JFrame frame = new JFrame("Wrrrreeeeee");
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );		
		frame.setResizable(false);

		KL keyL=new KL();
		commandBox=new JTextField();
		commandBox.addKeyListener(keyL);
		commandBox.setBounds(145,390,415,40);

		commandBox.setFont(new Font("Bradley Hand",Font.PLAIN,15));
		commandBox.setMargin(new Insets(10,10,10,10));
		dPanel.add(commandBox);

		//		
		frame.add(dPanel);
		frame.pack();
		frame.setLocationRelativeTo( null );		
		frame.setVisible(true);		
	}

	void drawBorder(Graphics2D g2) {
		g2.drawRect(40, 40, 325, 330);
		g2.drawRect(390, 40, 170, 330);

		g2.drawRect(40, 390, 520, 40);
		g2.drawString("command-", 50, 415);
		g2.drawRect(40, 430, 520, 120);


		//character stats
		g2.setFont(new Font("Courier New", Font.BOLD, 17));
		g2.drawString("character stats", 400, 65);

		g2.drawLine(390, 80, 560, 80);
		g2.drawString("hp", 400, 80);
		g2.drawString("def", 400, 80);
		g2.drawString("speed", 400, 80);
		g2.drawString("", 400, 80);
		g2.drawString("", 400, 80);

	}

	void run() {
		//		drawBorder(null);
		dPanel.repaint();
		handelCommand();

	}

	void handelCommand() {
		//find corrisponding command
		if (currCommand[0].equals("consume")) {
			consume(currCommand[1]);
		}
	}


	void consume(String item) {
		//if the name of the item exist in player inventory
		if (player.inventory.containsKey(item)) {
			player.useItem(player.inventory.get(item));
			System.out.println(player.hp);
		}else System.out.println(item+" not found");

	}

	void attack(String name) {
//		if ()
	}

	//global
	final static int PANW = 600;
	final static int PANH = 600;

	DrawingPanel dPanel = new DrawingPanel();
	JTextField commandBox;
	Player player=new Player();
	static int floor = 1;

	//TODO relink to use room in map
	Room currRoom=new Room();
	
	String[] adj={"consume"};
	String[] currCommand;



	//constructor
	MainGame() {
		Consumable c1=new Consumable();
		Consumable c2=new Consumable();
		Consumable c3=new Consumable();

		player.inventory.put(c1.name, c1);
		player.inventory.put(c2.name, c2);
		player.inventory.put(c3.name, c3);
		//
		//
		System.out.println(player.inventory);
		//		System.out.println(player.hp);
		//		handelCommand();
		dPanel.setLayout(null);
		panelFrameSetup();
	}

	public static void main(String[] args) {
		new MainGame();

	}
}

