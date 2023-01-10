package game;

// Justin, Lousia, Jenifer ~ Jan. 10, 2023
//Object class for the player Armour within the text adventure

public class Armour {
	int defense;
	int weight;

	Armour(int hpGain, int dmgBuff, int defenseBuff, int duration){
		defense = (int) ((Math.random()*4)+1)*MainGame.floor;
		weight = (int) ((Math.random()*50)+1);		
	}
}

