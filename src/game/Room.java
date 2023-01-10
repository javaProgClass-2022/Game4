package game;

// Justin, Lousia, Jenifer ~ Jan. 9, 2023
//Object class for the rooms within the text adventure

public class Room {
	int enemyAmt;
	int lootAmt;
	int exits;

	Room(){
		enemyAmt = (int) ((Math.random()*2)) + MainGame.floor;
		lootAmt = (int) ((Math.random()*3));
		exits = (int) ((Math.random()*4)) + 1;
	}
}

