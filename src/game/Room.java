package game;

// Justin, Lousia, Jenifer ~ Jan. 9, 2023
//Object class for the rooms within the text adventure

public class Room {
	int enemyAmt;
	int lootAmt;
	int deadEnemyCounter;
	boolean explored;
	boolean ladder;
	boolean currentRoom;

	Enemy enemies[];
	Loot roomLoot[];

	Room(){
		explored = false;
		ladder = false;


		//Adding the Enemies
		enemyAmt = (int) ((Math.random()*2)) + MainGame.floor;
		
		if (enemyAmt>15) enemyAmt=15;
		
		enemies = new Enemy[enemyAmt];
		for (int i = 0; i < enemyAmt; i++) enemies[i] = new Enemy();

		//Adding the Loot
		lootAmt = (int) (Math.random()*3)+1;
		roomLoot = new Loot[lootAmt];

		for (int i = 0; i < lootAmt; i ++) {
			int lootType =  (int) ((Math.random()*4));
			if (lootType == 0) roomLoot[i] = new Weapon();
			if (lootType == 1) roomLoot[i] = new Consumable();
			if (lootType == 2) roomLoot[i] = new Armour();
			if (lootType == 3) roomLoot[i] = new Upgrade();
		}	
	}

	//Method that checks if a monster has died
	void checkStatus() {
		for (int i = 0; i < enemies.length; i++) {
			if (enemies[i]!=null && enemies[i].hp <= 0) {
				enemies[i] = null;
				deadEnemyCounter++;
				enemyAmt--;
			}
		}
	}

	//Method that lets the player look around the room
	void look(){
		String display = "";
		
		// if there are enemies display them
		if (enemyAmt!=0) {
			display = ("The room countains the following enemies:       "); //Each line contains 48 characters so to display the text better we add spaces
			
			
			for (Enemy e:enemies) {
				if (e!=null)display = display+e.name+", ";
			}
			display=display.substring(0,display.length()-2);//remove ending , 
		}
		
		//If there are no enemies display the loot in the room
		if (enemyAmt==0 && lootAmt!=0) {
			display = ("The room countains the following loot:         "); //Each line contains 48 characters so to display the text better we add spaces
			for (int i = 0; i < lootAmt; i++) {
				Loot currentLoot = roomLoot[i];
				if  (currentLoot instanceof Weapon) currentLoot = (Weapon)currentLoot;
				if  (currentLoot instanceof Consumable)currentLoot = (Consumable)currentLoot;
				if  (currentLoot instanceof Upgrade) currentLoot = (Upgrade)currentLoot;
				if  (currentLoot instanceof Armour) currentLoot = (Armour)currentLoot;

				display = (display + currentLoot.toString()+", ");
			}
			display=display.substring(0,display.length()-2);//remove ending , 
		}

		if (enemyAmt==0 && lootAmt==0) display="the room is empty";
		
		MainGame.displayDialogue = (display);
	}
}


