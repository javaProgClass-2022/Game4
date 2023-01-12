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
		enemyAmt = (int) ((Math.random()*2)) + MainGame.floor;
		lootAmt = (int) ((Math.random()*3));
		explored = false;
		ladder = false;
		spawnRoom = false;
		
	}
}

