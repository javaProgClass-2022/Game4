package game;

// Justin, Lousia, Jenifer ~ Jan. 10, 2023
//Object class for the player Armour within the text adventure

public class Armour {
	String armourNames[] = {""};
	
	int defense;
	int weight;

	Armour(){
		defense = (int) ((Math.random()*4)+1)*MainGame.floor;
		weight = (int) ((Math.random()*50)+1);		
	}
}

