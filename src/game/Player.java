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
	
	final int MAXHP = 40;

	//Player Inventory
	HashMap<String, Consumable> inventory = new HashMap <String, Consumable>();
	Armour playerArmour;
	Weapon playerWeapon;

	Player(){
		hp = 40;
		def = 1;
		critChance = 10;
		critDmg = 1.5;
		playerWeapon = new Weapon("default");
		playerArmour = new Armour("default");
	}


	//returns true when player's hp <=0
	boolean takeDmg(int dmg,String enemyName){
		int dmgTaken = dmg - def;
		if (dmgTaken < 0) dmgTaken = 0;
		
		
		if(hp<=dmgTaken) {//if player takes more damage than current hp
			hp=0;
			return true;
		}else hp -= dmgTaken;
		return false;
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
			MainGame.displayDialogue = ("You Missed! ");
		}
		return dmg;
	}

	void useItem(Consumable item) {
		hp += item.hpGain;
		MainGame.displayDialogue="You Consumed " + item.name + " and Healed " + item.hpGain + " HP";
		
		//hp value should never go above maxHP
		if (hp>MAXHP) {
			hp=MAXHP;
			MainGame.displayDialogue="You Consumed " + item.name + " and Healed to max HP";
		}
		inventory.remove((item.name.toLowerCase()));
	}


	void pickupUpgrade (Upgrade up ) {
		def+=up.defenseUp;
		critChance += up.critChanceUp;
		critDmg += up.critDmgUp;
		MainGame.displayDialogue = ("Random Stat Upgraded");
	}


	void pickUpArmour(Armour armour) {
		playerArmour = armour;
		def -= playerArmour.defense;	//Subtracting current armour defense to avoid needing a def buff variable
		def += armour.defense;
		MainGame.displayDialogue = ( "Armour " + armour.name + "equiped");
	}


	void pickUpWeapon(Weapon weapon) {
		playerWeapon = weapon;
		MainGame.displayDialogue = ("Weapon " + weapon.name + " equiped");
	}
	
	void pickUpConsumable(Consumable food) {
		inventory.put((food.name.toLowerCase()), food);
		MainGame.displayDialogue = (food.name + " added to inventory");
	}
}
