package game;

// Justin, Lousia, Jenifer ~ Jan. 10, 2023
//Object class for the consumable items within the text adventure

public class Consumable {
	int hpGain;
	int dmgBuff;
	int defenseBuff;
	int duration;

	Consumable(int hpGain, int dmgBuff, int defenseBuff, int duration){
		this.hpGain = hpGain;
		this.dmgBuff = dmgBuff;
		this.defenseBuff = defenseBuff;
		this.duration = duration;			
	}
}

