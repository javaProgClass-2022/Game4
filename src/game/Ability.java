package game;

// Justin, Lousia, Jenifer ~ Jan. 11, 2023
//Object class for the player abilities within the text adventure

public class Ability extends Loot {
	String name;
	static final String ABILITYNAMES[] = {"Valkyrie's Blessing", "Cheat Codes", "Sun's Love", "Spirtual Enoucragement", "Deaths Bite", "Zeus's Blessing",
			"Blitz", "Fireball", "Disappointment", "Blood Magic", "Prayer", "Shadow Walk", "Reality Bend", "Dark Ritual", "Time Stop", "Ethereal Shift", 
			"Shadow Strike", "Nature's Grace", "Starfall Furry"
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
	
	public String toString() {return name;}
}

