package game;

// Justin, Lousia, Jenifer ~ Jan. 10, 2023
//Object class for the player weapons within the text adventure

public class Weapon extends Loot {
	String name;
	static final String WEAPONNAMES[] = {"Sword of Ares", "Trident of Posideon", "Longsword", "Claymore", "Harwood's Keyboard", "Jenifer's Mac's Fan",
			"Kunala's Lighter", "Spear", "Bow", "Crossbow", "Fishing Rod", "Stick", "BatBat", "Excalibur", "Calibur", "Blunt Rock", "Pike", "Gun", 
			"Chainsaw", "Rapier", "Broadsword", "Pencil", "Chair", "Shield", "Blade of Perseus", "Staff of Merlin", "Nail Gun", "Arcane Staff", "Shadow Blade",
			"Terrabalade", "Night's Edge"
	};


	int atkDmg;
	int accuracy;

	Weapon(){
		name = WEAPONNAMES[(int) ((Math.random()*WEAPONNAMES.length))];
		atkDmg = (int) ((Math.random()*6)) + MainGame.floor*3;
		accuracy = 100;
	}

	Weapon(String defaultname){
		name = "Ham";
		atkDmg = 5;
		accuracy = 75;
	}

	@Override
	public String toString() {return name;}
}

