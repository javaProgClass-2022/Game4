package game;

// Justin, Lousia, Jenifer ~ Jan. 11, 2023
//Object class for the player abilites within the text adventure

public class Ability extends Loot {
	String name;
	static final String ABILITYNAMES[] = {"Valkyrie's Blessing", "Cheat Codes", "Sun's Love", "Spirtual Enoucragement", "Deaths Bite", "Zeus's Blessing",
			"Blitz", ""
	};


	int hpGain;
	int atkDmg;
	int cooldownRoomAmt;
	int roomsSinceUse;

	Ability(){
		name = ABILITYNAMES[(int) ((Math.random()*ABILITYNAMES.length))];
		hpGain = (int) ((Math.random()*20)+1*MainGame.floor);
		atkDmg = (int) ((Math.random()*15)+1*MainGame.floor);
		cooldownRoomAmt = (int) ((Math.random()*10)+1);
	}
}

