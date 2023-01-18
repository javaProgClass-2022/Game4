package game;

// Justin, Lousia, Jenifer ~ Jan. 10, 2023
//Object class for the player weapons within the text adventure

public class Weapon {
	String name;
	String weaponNames[] = {"the Sword of Ares", "the Trident of Posideon", "a Longsword", "a claymore", "Harwood's Keyboard", "Jenifer's Mac's Fan",
			"Kunala's Lighter" 
			};
	
	
	int atkDmg;
	int weight;
	int accuracy;

	Weapon(){
		name = weaponNames[(int) ((Math.random()*weaponNames.length))];
		atkDmg = (int) ((Math.random()*6)) + MainGame.floor*3;
		weight = (int) ((Math.random()*25)+1);
		accuracy = 100;
	}
	
	Weapon(String defaultname){
		name = "Ham";
		atkDmg = 5;
		weight = 10;
		accuracy = 75;
	}
}

