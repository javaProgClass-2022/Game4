package game;
//Justin, Jenifer, Lousia ~ Jan. 11, 2023
//Object class for the player upgrades

public class Upgrade extends Loot {
	String name = "Stat Booster";
	int defenseUp;
	int speedUp; 
	int critChanceUp;
	int dmgUp;
	double critDmgUp;

	Upgrade(){
		int typeRandomizer = (int) ((Math.random()*4));

		if (typeRandomizer == 0) dmgUp = 1;
		if (typeRandomizer == 1) defenseUp = 1;
		if (typeRandomizer == 2) critChanceUp = 1;
		if (typeRandomizer == 3) critDmgUp = 0.2;
	}

	@Override
	public String toString() {return name;}
}
