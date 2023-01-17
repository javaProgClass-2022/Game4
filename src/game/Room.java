package game;

// Justin, Lousia, Jenifer ~ Jan. 9, 2023
//Object class for the rooms within the text adventure

public class Room {
	int enemyAmt;
	int lootAmt;
	boolean explored;
	boolean spawnRoom;
	boolean ladder;
	
	Room(){
		explored = false;
		ladder = false;
		spawnRoom = false;	
		
		
		//Adding the Enemies
		enemyAmt = (int) ((Math.random()*2)) + MainGame.floor;
		Enemy enemies[] = new Enemy[enemyAmt];
		for (int i = 0; i <= enemyAmt; i++) enemies[i] = new Enemy();
		
		//Adding the Loot
		lootAmt = (int) ((Math.random()*3));
		Loot roomLoot[] = new Loot[lootAmt];
		
		for (int i = 0; i <= lootAmt; i ++) {
			int lootType =  (int) ((Math.random()*5));
			if (lootType == 0) roomLoot[i] = new Weapon();
			if (lootType == 1) roomLoot[i] = new Consumable();
			if (lootType == 2) roomLoot[i] = new Armour();;
			if (lootType == 3) roomLoot[i] = new Ability();;
			if (lootType == 4) roomLoot[i] = new Upgrade();;
		}
	}
}

