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
		//Adding the Enemies
		enemyAmt = (int) ((Math.random()*2)) + MainGame.floor;
		Enemy enemies[] = new Enemy[enemyAmt];
		for (int i = 0; i <= enemyAmt; i++) enemies[i] = new Enemy();
		
		//Adding the Loot
		lootAmt = (int) ((Math.random()*3));
		explored = false;
		ladder = false;
		spawnRoom = false;	
	}
}

