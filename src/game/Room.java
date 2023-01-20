package game;

// Justin, Lousia, Jenifer ~ Jan. 9, 2023
//Object class for the rooms within the text adventure

public class Room {
	int enemyAmt;
	int lootAmt;
	int deadEnemyCounter;
	boolean explored;
	boolean spawnRoom;
	boolean ladder;

	Enemy enemies[];
	Loot roomLoot[];

	Room(){
		explored = false;
		ladder = false;
		spawnRoom = false;	


		//Adding the Enemies
		enemyAmt = (int) ((Math.random()*2)) + MainGame.floor;
		Enemy enemies[] = new Enemy[enemyAmt];
		for (int i = 0; i < enemyAmt; i++) enemies[i] = new Enemy();

		//Adding the Loot
		lootAmt = (int) ((Math.random()*3));
		Loot roomLoot[] = new Loot[lootAmt];

		for (int i = 0; i < lootAmt; i ++) {
			int lootType =  (int) ((Math.random()*5));
			if (lootType == 0) roomLoot[i] = new Weapon();
			if (lootType == 1) roomLoot[i] = new Consumable();
			if (lootType == 2) roomLoot[i] = new Armour();
			if (lootType == 3) roomLoot[i] = new Ability();
			if (lootType == 4) roomLoot[i] = new Upgrade();
		}	
	}

	//Method that checks if a monster has died
	void checkStatus() {
		for (int i = 0; i < enemies.length; i++) {
			if (enemies[i].hp <= 0) {
				enemies[i] = null;
				deadEnemyCounter++;
			}
		}
	}


	//Method that lets the player look around the room
	void look(){
		String display = "";

		//If there are no enemies display the loot in the room
		if (deadEnemyCounter == enemyAmt) {
			display = ("The room countains the following items:        "); //Each line contains 48 characters so to display the text better we add spaces
			for (int i = 0; i < lootAmt; i++) {
				Loot currentLoot = roomLoot[i];
				if  (currentLoot instanceof Weapon) currentLoot = (Weapon)currentLoot;
				if  (currentLoot instanceof Consumable)currentLoot = (Consumable)currentLoot;
				if  (currentLoot instanceof Ability) currentLoot = (Ability)currentLoot;
				if  (currentLoot instanceof Upgrade) currentLoot = (Upgrade)currentLoot;
				if  (currentLoot instanceof Armour) currentLoot = (Armour)currentLoot;

				display += currentLoot.toString();
				display += (48 - currentLoot.toString().length()); //Adding the extra characters to go  to the next line
			}
		}

		//If there are enemies display them
		if (deadEnemyCounter <= enemyAmt) {
			display = ("The room countains the following enemies:       "); //Each line contains 48 characters so to display the text better we add spaces
			for (int i = 0; i < enemies.length; i++) {
				display += enemies[i].toString();
				display += (48 - enemies[i].toString().length()); //Adding the extra characters to go  to the next line
			}
		}
		MainGame.displayDialogue = (display);
	}
}


