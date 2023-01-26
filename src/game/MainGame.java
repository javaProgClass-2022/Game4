package game;

/* Add comments here
 * 
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;


//TODO make command not case-sensitive

//Loiusa's branch

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
			printBoard(g2);
			printConsole(g2);
			printMap(g2);
		}
	}

	class KL implements KeyListener{

		public void keyTyped(KeyEvent e) {}
		public void keyPressed(KeyEvent e) {}

		@Override
		public void keyReleased(KeyEvent e) {

			if (e.getKeyCode()==38) {
				commandBox.setText(lastCommand);
			}

			if (e.getKeyCode()==10) {
				//return command line and empty input box
				currCommand=commandBox.getText().trim().split(" ",2);

				lastCommand=commandBox.getText().trim();
				commandBox.setText("");

				boolean functional=false;

				if (currCommand.length==2) {
					for (String s:functionTrigger) {
						if (s.equalsIgnoreCase(currCommand[0])) {
							handelCommand();
							functional=true;
							break;
						}
					}
				}else {
					for (String s:menuTrigger) {
						if (s.equalsIgnoreCase(currCommand[0])) {
							menuCommand();
							functional=true;
							break;
						}
					}
				}
				if(!functional) {
					displayDialogue="command unreconized. please try again";
					dPanel.repaint();
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
		commandBox=new JTextArea();
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

	void printBoard(Graphics2D g2) {
		g2.setFont(new Font("Courier New", Font.BOLD, 17));
		g2.drawRect(40, 40, 325, 330);
		g2.drawRect(390, 40, 170, 330);

		g2.drawRect(40, 390, 520, 40);
		g2.drawString("command-", 50, 415);
		g2.drawRect(40, 430, 520, 120);


		//character stats
		g2.drawString("character stats", 400, 65);

		g2.drawLine(390, 80, 560, 80);

		g2.drawString("hp", 445, 110);
		g2.drawString("def", 435, 140);
		g2.drawString("crit%", 415, 170);
		g2.drawString("dmg", 435, 200);


		g2.setFont(new Font("Bradley Hand",Font.BOLD,17));
		g2.drawString(""+player.hp, 485, 110);
		g2.drawString(""+player.def, 485, 140);
		g2.drawString(""+player.critChance, 485, 170);
		g2.drawString(""+player.playerWeapon.atkDmg+player.atkBoost, 485, 200);

		g2.setFont(new Font("Courier New", Font.BOLD, 17));
		g2.setStroke(new BasicStroke(1));
		g2.setColor(Color.LIGHT_GRAY);
		g2.drawLine(475, 100, 475, 205);
		g2.drawLine(430, 225, 520, 225);

		g2.setStroke(new BasicStroke(1));
		g2.setColor(Color.WHITE);

		g2.drawString("help", 455, 255);
		g2.drawString("inventory", 430, 285);
		g2.drawString("achievements", 415, 315);
		g2.drawString("staff", 450, 345);

	}

	void printConsole(Graphics2D g2) {
		g2.setColor(Color.darkGray);
		g2.drawString("---------1---------2---------3---------4-------8", 60, 455);
		g2.drawString("---------1---------2---------3---------4-------8", 60, 480);
		g2.drawString("---------1---------2---------3---------4-------8", 60, 505);
		g2.drawString("---------1---------2---------3---------4-------8", 60, 530);

		g2.setColor(Color.WHITE);

		//		String testing="this is a very long line of random english words that i came up with to test out what ever i am supposed to do. ";

		int locX=60;
		int locY=455;
		int lineH=25;

		//one extra spot for spaces added
		int maxChar=48;
		int charCount=0;
		String line = "";
		String[] splitLine=displayDialogue.split(" ");

		for (String word:splitLine) {
			charCount+=word.length();
			if (charCount<=maxChar) {
				line+=(word+" ");

				//add the space to count
				charCount+=1;

			}else {
				g2.drawString(line, locX, locY);
				line=word+" ";
				locY+=lineH;
				charCount=word.length()+1;
			}
		}
		g2.drawString(line, locX, locY);

	}

	void printMap(Graphics2D g2) {

		int roomSize=40;
		//each room displays 40px wide

		for (int i=0;i<Maps.size;i++) {
			for (int j=0;j<Maps.size;j++) {
				if (map[i][j]!=null) {
					if(map[i][j].explored) {
						g2.setColor(Color.DARK_GRAY.brighter());
						g2.fillRect(62+j*roomSize, 62+i*roomSize, roomSize, roomSize);
						g2.setColor(Color.WHITE);
						g2.drawRect(62+j*roomSize, 62+i*roomSize, roomSize, roomSize);
						if (map[i][j].currentRoom &&map[i][j].ladder) {
							//if player is currently in ladder room
							g2.fillRect(69+j*roomSize, 72+i*roomSize, 2, 20);
							g2.fillRect(78+j*roomSize, 72+i*roomSize, 2, 20);

							g2.fillRect(69+j*roomSize, 76+i*roomSize, 9, 2);
							g2.fillRect(69+j*roomSize, 81+i*roomSize, 9, 2);
							g2.fillRect(69+j*roomSize, 86+i*roomSize, 9, 2);

							g2.drawOval(84+j*roomSize, 77+i*roomSize, 12, 12);
							g2.fillOval(87+j*roomSize, 80+i*roomSize, 6, 6);
							currRoomLocation[0]=i;
							currRoomLocation[1]=j;

						}else if (map[i][j].currentRoom) {
							g2.drawOval(72+j*roomSize, 72+i*roomSize, 20, 20);
							g2.fillOval(77+j*roomSize, 77+i*roomSize, 10, 10);
							currRoomLocation[0]=i;
							currRoomLocation[1]=j;
						}else if (map[i][j].ladder) {
							g2.setColor(Color.LIGHT_GRAY);
							g2.fillRect(75+j*roomSize, 72+i*roomSize, 2, 20);
							g2.fillRect(87+j*roomSize, 72+i*roomSize, 2, 20);

							g2.fillRect(75+j*roomSize, 76+i*roomSize, 12, 2);
							g2.fillRect(75+j*roomSize, 81+i*roomSize, 12, 2);
							g2.fillRect(75+j*roomSize, 86+i*roomSize, 12, 2);
							//							currRoomLocation[0]=i;
							//							currRoomLocation[1]=j;
						}
					}else{
						g2.setColor(Color.LIGHT_GRAY);
						g2.fillRect(62+j*roomSize, 62+i*roomSize, roomSize, roomSize);
						g2.setColor(Color.WHITE);
						g2.drawRect(62+j*roomSize, 62+i*roomSize, roomSize, roomSize);
					}
				}
			}
		}

	}


	void handelCommand() {

		//find corrisponding command
		if (currCommand[0].equalsIgnoreCase("consume")) {
			consume(currCommand[1]);
		}
		if (currCommand[0].equalsIgnoreCase("move")) {
			move(currCommand[1]);
		}
		if (currCommand[0].equalsIgnoreCase("attack")) {
			attack(currCommand[1]);
		}
		if (currCommand[0].equalsIgnoreCase("climb")) {
			climb(currCommand[1]);
		}
		if (currCommand[0].equalsIgnoreCase("take")) {
			take(currCommand[1]);
		}
		dPanel.repaint();

	}

	void menuCommand() {
		if (currCommand[0].equalsIgnoreCase("help")) {
			displayDialogue="-move- move to room up/down/left/right        "
					+ "-consume *item name*    -take *item/weapon name* "
					+ "-cast *ability name*    -attack *enemy name* "
					+ "-investigate- display loot if no enemie exist";
		}
		if (currCommand[0].equalsIgnoreCase("inventory")) {
			displayDialogue="Inventory-  ";
			if(player.inventory.size()==0)displayDialogue+="empty";
			else {
				for(String k:player.inventory.keySet()) {
					displayDialogue=displayDialogue+k+", ";
				}
				displayDialogue=displayDialogue.substring(0,displayDialogue.length()-2);
			}
		}
		if (currCommand[0].equalsIgnoreCase("status")) {
			displayDialogue="armour-  "+ player.playerArmour.name;
			for (int i=(9+player.playerArmour.name.length());i<48;i++) {
				displayDialogue+=" ";
			}
			displayDialogue=displayDialogue+"weapon-  "+ player.playerWeapon.name;
		}
		if (currCommand[0].equalsIgnoreCase("investigate")) {
			investigate();
		}

		dPanel.repaint();


	}



	void consume(String item) {
		//if the name of the item exist in player inventory
		System.out.println(player.inventory);
		System.out.println(item.toLowerCase());
		if (player.inventory.containsKey(item.toLowerCase())) {
			player.useItem(player.inventory.get(item));
		}else displayDialogue=item+" is not in your inventory";

	}

	void move(String direction) {
		//check if room exist on direction

		int i=currRoomLocation[0];
		int j=currRoomLocation[1];

		if(direction.equalsIgnoreCase("up")) i-=1;
		if(direction.equalsIgnoreCase("right"))j+=1;
		if(direction.equalsIgnoreCase("down"))i+=1;
		if(direction.equalsIgnoreCase("left"))j-=1;


		if (i<0 || j<0 || i>map.length || j>map.length) {
			//if out of bounds
			displayDialogue="no room exist on requested direction";
		}else if (map[i][j]==null) {
			//if no room exist
			displayDialogue="no room exist on requested direction";
		}else {
			displayDialogue="you moved to another room";

			map[currRoomLocation[0]][currRoomLocation[1]].currentRoom=false;

			currRoomLocation[0]=i;
			currRoomLocation[1]=j;

			map[currRoomLocation[0]][currRoomLocation[1]].currentRoom=true;
			map[currRoomLocation[0]][currRoomLocation[1]].explored=true;

		}


	}

	void attack(String name) {

		if (map[currRoomLocation[0]][currRoomLocation[1]].enemyAmt>0) {
			for (Enemy n:map[currRoomLocation[0]][currRoomLocation[1]].enemies) {
				if (n!=null && name.equalsIgnoreCase(n.name)) {
					n.takeDmg(player.dealWeaponDmg());
					map[currRoomLocation[0]][currRoomLocation[1]].checkStatus();
					
					if (n.hp>0) {
						player.takeDmg(n.atkDmg, n.name);
//						for(int i=displayDialogue.length()%48;i<48;i++) displayDialogue+=" ";
						displayDialogue += ("You took " + (n.atkDmg-player.def) + " damage from "+n.name);
					}
					break;
				}
			}
		}

	}

	void climb(String ladder) {
		if (ladder.equalsIgnoreCase("ladder")||ladder.equalsIgnoreCase("down")) {

			if (map[currRoomLocation[0]][currRoomLocation[1]].ladder) {
				if(map[currRoomLocation[0]][currRoomLocation[1]].enemyAmt==0) {
					floor+=1;
					map= new Maps().convertMap();
					displayDialogue="you are now at a new floor";
				}else {
					displayDialogue="the ladder is guarded by monsters";
				}

			}else {
				displayDialogue="there is nothing to climb in this room";
			}


		}
	}

	void take(String item) {
		if (map[currRoomLocation[0]][currRoomLocation[1]].enemyAmt==0) {
			boolean itemExist=false;
			
			for(int i=0;i<map[currRoomLocation[0]][currRoomLocation[1]].lootAmt;i++) {
				
				Loot l=map[currRoomLocation[0]][currRoomLocation[1]].roomLoot[i];
				if (item.equalsIgnoreCase(l.toString())) {//if item is a loot in room
					if (l instanceof Weapon) player.pickUpWeapon((Weapon)l);;
					if (l instanceof Consumable) player.pickUpConsumable((Consumable)l);
					if (l instanceof Ability)player.playerAbility=(Ability)l;
					if (l instanceof Upgrade)player.pickupUpgrade((Upgrade)l);
					if (l instanceof Armour) player.pickUpArmour((Armour)l);;
				
					//set the loot to null, the total amout of loot-1
					map[currRoomLocation[0]][currRoomLocation[1]].roomLoot[i]=null;
					map[currRoomLocation[0]][currRoomLocation[1]].lootAmt--;
					displayDialogue="you picked up "+item;
					itemExist=true;
					break;
				}
			}
			if (!itemExist) displayDialogue="there is no "+item+" in the room";
		}else {
			displayDialogue="the loot is guarded by monsters";
		}
	}


	void investigate() {
		map[currRoomLocation[0]][currRoomLocation[1]].look();
		//		System.out.println(map[currRoomLocation[0]][currRoomLocation[1]].enemies.length);;
	}

	//global
	final static int PANW = 600;
	final static int PANH = 600;

	DrawingPanel dPanel = new DrawingPanel();
	JTextArea commandBox;
	String lastCommand;
	Player player=new Player();
	static int floor = 1;


	int[] currRoomLocation=new int[2];

	String[] functionTrigger={"move","consume","attack","take","cast","climb"};
	String[] menuTrigger={"help","inventory","status","achievement","staff","investigate"};
	String[] currCommand;
	static String displayDialogue="";

	//	static Room[][] map=new Maps().convertMap();
	static Room[][] map=new Maps().convertMap();



	//constructor
	MainGame() {
		Consumable c1=new Consumable();
		Consumable c2=new Consumable();
		Consumable c3=new Consumable();
		
		player.pickUpConsumable(c1);
		player.pickUpConsumable(c2);
		player.pickUpConsumable(c3);

		dPanel.setLayout(null);
		panelFrameSetup();
	}

	public static void main(String[] args) {
		new MainGame();

	}
}

