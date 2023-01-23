package game;
//Justin, Jenifer, Lousia ~ Jan. 12, 2023
//Object class for the player

import java.util.HashMap;

public class Player {
//	int weight;
	int hp;
//	int speed;
	int def;
	int atkBoost;
	int critChance;
	double critDmg;

	//Player Inventory
	HashMap<String, Consumable> inventory = new HashMap <String, Consumable>();
	Armour playerArmour;
	Weapon playerWeapon;
	Ability playerAbility;

	Player(){
		hp = 100;
//		speed = 100;
		def = 1;
		critChance = 10;
		critDmg = 1.5;
		playerWeapon = new Weapon("default");
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
		return dmg;
	}

	//Note Abilities are Armour piercing
	void useAbility(Enemy target) {
		if (playerAbility.cooldownRoomAmt <= playerAbility.roomsSinceUse) {
			hp += playerAbility.hpGain;
			target.takeDmg(playerAbility.atkDmg);
			playerAbility.roomsSinceUse = 0;
			MainGame.displayDialogue = ("You healed " + playerAbility.hpGain +" Health" );
		}
		else {MainGame.displayDialogue = ("Ability is not off cooldown");}
	}


	void useItem(Consumable item) {
		hp += item.hpGain;
		MainGame.displayDialogue="You Consumed " + item.name + " and Healed " + item.hpGain + " HP";
	}


	void pickupUpgrade (Upgrade up ) {
		def+=up.defenseUp;
//		speed += up.speedUp;
		critChance += up.critChanceUp;
		critDmg += up.critDmgUp;
		MainGame.displayDialogue = ("Random Stat Upgraded");
	}


	void pickUpArmour(Armour armour) {
		playerArmour = armour;
		MainGame.displayDialogue = (armour.name + " equiped");
//		weight = playerArmour.weight + playerWeapon.weight;
	}


	void pickUpWeapon(Weapon weapon) {
		playerWeapon = weapon;
		MainGame.displayDialogue = (weapon.name + " equiped");
//		weight = playerArmour.weight + playerWeapon.weight;
	}
	
	void pickUpConsumable(Consumable food) {
		inventory.put(food.name, food);
		MainGame.displayDialogue = (food.name + " added to inventory");
	}


	void pickUpConsumable(Consumable food) {
		inventory.put(food.name, food);
		MainGame.displayDialogue = (food.name + " added to inventory");
	}


}
