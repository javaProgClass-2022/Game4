package game;

//A rougelike text adventure made by Lousia, Justin, and Jennifer
//Explore randomly generated rooms and fight enemies untll you make it to the next floor, then do it all again!

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;


public class MainGame {
	

	//global
	final static int PANW = 600;
	final static int PANH = 600;

	boolean playerDead=false;
	boolean startScreen=true;

	KL keyL=new KL();
	KLforStartScreen keyL2=new KLforStartScreen();
	Timer deathTimer=new Timer(45, new DeathTimer());
	int curtainLayer=0;//the closing curtain on death

	JFrame frame = new JFrame("Wrrrreeeeee");
	DrawingPanel dPanel = new DrawingPanel();
	
	JTextArea commandBox;
	String lastCommand;
	Player player=new Player();
	static int floor = 1;


	int[] currRoomLocation=new int[2];

	String[] functionTrigger={"move","consume","eat","attack","take","grab","cast","climb"};
	String[] menuTrigger={"help","inventory","status","achievement","staff","investigate"};
	String[] currCommand;
	static String displayDialogue="";
	static Room[][] map=new Maps().convertMap();

	
	public static void main(String[] args) {
		new MainGame();

	}
	//constructor
		MainGame() {
			dPanel.setLayout(null);
			panelFrameSetup();
		}
	

		void panelFrameSetup() {
			frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );		
			frame.setResizable(false);


			commandBox=new JTextArea();
			commandBox.addKeyListener(keyL);
			commandBox.setBounds(145,390,415,40);

			commandBox.setFont(new Font("Bradley Hand",Font.PLAIN,15));
			commandBox.setMargin(new Insets(10,10,10,10));

			frame.addKeyListener(keyL2);
			
			frame.add(dPanel);
			frame.pack();
			frame.setLocationRelativeTo( null );		
			frame.setVisible(true);		
		}
		
		//start game and death display
				void playerDeath() {
					playerDead=true;
					deathTimer.start();
				}

		void printBoard(Graphics2D g2) {
			g2.setFont(new Font("Courier New", Font.BOLD, 17));
			g2.drawRect(40, 40, 325, 330);
			g2.drawRect(390, 40, 170, 330);

			g2.drawRect(40, 390, 520, 40);
			g2.drawString("command-", 50, 415);
			g2.drawRect(40, 430, 520, 120);


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
			g2.drawString(""+(player.playerWeapon.atkDmg+player.atkBoost), 485, 200);

			g2.setFont(new Font("Courier New", Font.BOLD, 16));
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
			g2.drawString("------------------------------------------------", 60, 455);
			g2.drawString("------------------------------------------------", 60, 480);
			g2.drawString("------------------------------------------------", 60, 505);
			g2.drawString("------------------------------------------------", 60, 530);

			g2.setColor(Color.WHITE);

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
			if (currCommand[0].equalsIgnoreCase("consume")|| currCommand[0].equalsIgnoreCase("eat")) {
				consume(currCommand[1]);
			}
			if (currCommand[0].equalsIgnoreCase("move")) {
				move(currCommand[1]);
			}
			if (currCommand[0].equalsIgnoreCase("attack")) {
				displayDialogue = ""; //Works fine on Mac but needed to clear console on Windows
				attack(currCommand[1]);
			}
			if (currCommand[0].equalsIgnoreCase("climb")) {
				climb(currCommand[1]);
			}
			if (currCommand[0].equalsIgnoreCase("take")||currCommand[0].equalsIgnoreCase("grab")) {

				take(currCommand[1]);
			}
			dPanel.repaint();

		}

		//Method that runs the commands listed in the side menu
		void menuCommand() {
			
			//Runs the help dialogue
			if (currCommand[0].equalsIgnoreCase("help")) {
				displayDialogue="-move- move to room up/down/left/right        "
						+ "-consume *item name*    -take *item/weapon name* "
						+ "-attack *enemy name*                         "
						+ "-investigate- displays loot/enmies           ";
			}
			
			//Runs the inventory dialogue
			if (currCommand[0].equalsIgnoreCase("inventory")) {
				displayDialogue="Inventory -  ";
				if(player.inventory.size()==0)displayDialogue+="empty";
				else {
					for(String k:player.inventory.keySet()) {
						displayDialogue=displayDialogue+k+", ";
					}
					displayDialogue=displayDialogue.substring(0,displayDialogue.length()-2);
				}
			}
			
			//Runs the player status dialogue
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


		//Method to eat food
		void consume(String item) {
			//if the name of the item exist in player inventory
			if (player.inventory.containsKey(item.toLowerCase())) {
				player.useItem(player.inventory.get(item));
			}else displayDialogue=item+" is not in your inventory";

		}

		//Method to move the player to another room
		void move(String direction) {
			//check if room exist on direction
			int i=currRoomLocation[0];
			int j=currRoomLocation[1];

			if(direction.equalsIgnoreCase("up") || direction.equalsIgnoreCase("N")) i-=1;
			if(direction.equalsIgnoreCase("right")|| direction.equalsIgnoreCase("E"))j+=1;
			if(direction.equalsIgnoreCase("down")|| direction.equalsIgnoreCase("S"))i+=1;
			if(direction.equalsIgnoreCase("left")|| direction.equalsIgnoreCase("W"))j-=1;

			if (currRoomLocation[0]==i && currRoomLocation[1]==j) {
				displayDialogue="direction unreconized. please try again";
			}else if (i<0 || j<0 || i>map.length || j>map.length) {
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

		//Method for enemy attacks against the player
		void attack(String name) {

			if (map[currRoomLocation[0]][currRoomLocation[1]].enemyAmt>0) {
				for (Enemy n:map[currRoomLocation[0]][currRoomLocation[1]].enemies) {
					if (n!=null && name.equalsIgnoreCase(n.name)) {
						n.takeDmg(player.dealWeaponDmg());

						map[currRoomLocation[0]][currRoomLocation[1]].checkStatus();

						if (n.hp>0) {
							if(player.takeDmg(n.atkDmg, n.name)) playerDeath();
							displayDialogue += ("You took " + (n.atkDmg-player.def) + " damage from "+n.name);
						}
						break;
					}
				}
			}

		}

		//Method that moves the player to the next floor
		void climb(String ladder) {
			if (ladder.equalsIgnoreCase("ladder")||ladder.equalsIgnoreCase("down")) {

				if (map[currRoomLocation[0]][currRoomLocation[1]].ladder) {
					if(map[currRoomLocation[0]][currRoomLocation[1]].enemyAmt==0) {
						floor+=1;
						map= new Maps().convertMap();
						displayDialogue="you are now at a new floor";
					}else displayDialogue="the ladder is guarded by monsters";

				}else displayDialogue="there is nothing to climb in this room";
				
			}
		}

		//Method that allows the player to pickup items
		void take(String item) {
			if (map[currRoomLocation[0]][currRoomLocation[1]].enemyAmt==0) {
				boolean itemExist=false;

				for(int i=0;i<map[currRoomLocation[0]][currRoomLocation[1]].lootAmt;i++) {

					Loot l=map[currRoomLocation[0]][currRoomLocation[1]].roomLoot[i];
					if (item.equalsIgnoreCase(l.toString())) {//if item is a loot in room
						if (l instanceof Weapon) player.pickUpWeapon((Weapon)l);;
						if (l instanceof Consumable) player.pickUpConsumable((Consumable)l);
						if (l instanceof Upgrade)player.pickupUpgrade((Upgrade)l);
						if (l instanceof Armour) player.pickUpArmour((Armour)l);;

						//set the loot to null, the total amount of loot-1
						map[currRoomLocation[0]][currRoomLocation[1]].roomLoot[i]=null;
						map[currRoomLocation[0]][currRoomLocation[1]].lootAmt--;
						
						itemExist=true;
						break;
					}
				}
				if (!itemExist) displayDialogue="there is no "+item+" in the room";
			}else {
				displayDialogue="the loot is guarded by monsters";
			}
		}

		//Method that displays the enemies/loot in the current room
		void investigate() {
			map[currRoomLocation[0]][currRoomLocation[1]].look();
		}

	
	//Graphics
	class DrawingPanel extends JPanel {

		DrawingPanel() {
			this.setBackground(Color.BLACK);
			this.setPreferredSize(new Dimension(PANW,PANH));  //remember that the JPanel size is more accurate than JFrame.
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;

			g2.setStroke(new BasicStroke(2));	
			g2.setColor(Color.white);

			printBoard(g2);
			printConsole(g2);
			printMap(g2);

			if (playerDead) {
				dPanel.remove(commandBox);
				g2.setColor(new Color(255,255,255,90));

				for(int i=0;i<curtainLayer;i++) {
					g2.fillRect(0, 0, PANW, curtainLayer*10);
				}
				g2.setColor(Color.BLACK);
				g2.setFont(new Font("Bradley Hand",Font.BOLD+Font.ITALIC,45));
				g2.drawString("WASTED", 200, 290);
			}else if (startScreen) {
				g2.setColor(Color.BLACK);
				g2.fillRect(0, 0, PANW, PANH);

				g2.setColor(Color.WHITE);
				g2.drawString("welcome to the text adventure game", 140, 265);
				g2.drawString("press - S - to start", 205, 320);
			}

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

		class KLforStartScreen implements KeyListener{
	
			@Override
			public void keyTyped(KeyEvent e) {}
	
			@Override
			public void keyPressed(KeyEvent e) {}
	
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==83) {//key "s"
					startScreen=false;
					dPanel.add(commandBox);
					frame.removeKeyListener(keyL);
					
					dPanel.repaint();
				}
			}
	
		}


	class DeathTimer implements ActionListener{
		int count=0;

		@Override
		public void actionPerformed(ActionEvent e) {
			if(curtainLayer<65) {
				curtainLayer+=1;
				count++;
				dPanel.repaint();
			}else deathTimer.stop();
		}
	}
}

