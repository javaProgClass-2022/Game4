package game;
//Justin, Jenifer, Lousia ~ Jan. 12, 2023
//Object class for the player

import java.util.HashMap;

public class Player {
	//Player Stats
	int hp;
	int def;
	int atkBoost;
	int critChance;
	double critDmg;

	//Player Inventory
	HashMap<String, Consumable> inventory = new HashMap <String, Consumable>();
	Armour playerArmour;
	Weapon playerWeapon;

	Player(){
		hp = 100;
		def = 1;
		critChance = 10;
		critDmg = 1.5;
		playerWeapon = new Weapon("default");
		playerArmour = new Armour("default");
	}


	void takeDmg(int dmg){
		int dmgTaken = dmg - def;
		if (dmgTaken < 0) dmgTaken = 0;
		hp =- dmgTaken;
		MainGame.displayDialogue = ("You took " + hp + " damage");
	}


	int dealWeaponDmg() {
		int dmg = playerWeapon.atkDmg + atkBoost;

		//Checking if crit triggers
		int critRandomizer = (int) ((Math.random()*100)+1);	
		if (critRandomizer <= critChance) {
			dmg *= (int) critDmg;
			MainGame.displayDialogue = ("Critical Hit!");
		}
		
		//Checking if the weapon hits
		int accurceyRandomizer = (int) ((Math.random()*100)+1);	
		if (accurceyRandomizer >= playerWeapon.accuracy) {
			dmg = 0;
			MainGame.displayDialogue = ("You Missed!");
		}
		return dmg;
	}

	void useItem(Consumable item) {
		hp += item.hpGain;
		if (hp > 100) hp = 100;
		MainGame.displayDialogue="You Consumed " + item.name + " and Healed " + item.hpGain + " HP";
	}


	void pickupUpgrade (Upgrade up ) {
		def+=up.defenseUp;
		critChance += up.critChanceUp;
		critDmg += up.critDmgUp;
		MainGame.displayDialogue = ("Random Stat Upgraded");
	}


	void pickUpArmour(Armour armour) {
		playerArmour = armour;
		MainGame.displayDialogue = (armour.name + " equiped");
	}


	void pickUpWeapon(Weapon weapon) {
		playerWeapon = weapon;
		MainGame.displayDialogue = (weapon.name + " equiped");
	}

	void pickUpConsumable(Consumable food) {
		inventory.put(food.name, food);
		MainGame.displayDialogue = (food.name + " added to inventory");
	}
}
