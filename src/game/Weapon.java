package game;

// Justin, Lousia, Jenifer ~ Jan. 10, 2023
//Object class for the player weapons within the text adventure

public class Weapon extends Loot {
	String name;
	static final String WEAPONNAMES[] = {"the Sword of Ares", "the Trident of Posideon", "a Longsword", "a claymore", "Harwood's Keyboard", "Jenifer's Mac's Fan",
			"Kunala's Lighter" 
	};


	int atkDmg;
	int weight;
	int accuracy;

	Weapon(){
		name = WEAPONNAMES[(int) ((Math.random()*WEAPONNAMES.length))];
		atkDmg = (int) ((Math.random()*6)) + MainGame.floor*3;
		weight = (int) ((Math.random()*25)+1);
		accuracy = 100;
	}
}

