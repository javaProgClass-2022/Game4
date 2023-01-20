package game;
//Justin, Jenifer, Lousia ~ Jan. 12, 2023
//Object class for player

import java.util.HashMap;

public class Player {
	int weight;
	int hp;
	int speed;
	int def;
	int atkBoost;
	int critChance;
	double critDmg;
	HashMap<String, Consumable> inventory = new HashMap <String, Consumable>();
	Armour playerArmour;
	Weapon playerWeapon;
	Ability playerAbility;

	Player(){
		hp = 100;
		speed = 100;
		def = 1;
		critChance = 10;
		critDmg = 1.5;
		playerWeapon=new Weapon("Default");
	}


	void takeDmg(int dmg){
		int dmgTaken = dmg - def;
		if (dmgTaken < 0) dmgTaken = 0;
		hp =- dmgTaken;
		System.out.println("You took " + hp + " damage");
	}

	
	int dealWeaponDmg() {
		int dmg = playerWeapon.atkDmg + atkBoost;
		
		//Checking if crit triggers
		int critRandomizer = (int) ((Math.random()*100)+1);	
		if (critRandomizer <= critChance) {
			dmg *= (int) critDmg;
			System.out.println("Critical Hit!");
		}
		
		return dmg;
	}

	//Note Abilities are Armour piercing
	void useAbility(Enemy target) {
		if (playerAbility.cooldownRoomAmt <= playerAbility.roomsSinceUse) {
			hp += playerAbility.hpGain;
			target.takeDmg(playerAbility.atkDmg);
			playerAbility.roomsSinceUse = 0;
			System.out.println("You healed " + playerAbility.hpGain +" Health" );
		}
		else {System.out.println("Ability is not off cooldown");}
	}

	
	void useItem(Consumable item) {
		hp += item.hpGain;
		MainGame.displayDialogue="You Consumed " + item.name + " and Healed " + item.hpGain + " HP";
	}

	
	void pickupUpgrade (Upgrade up ) {
		def+=up.defenseUp;
		speed += up.speedUp;
		critChance += up.critChanceUp;
		critDmg += up.critDmgUp;
		System.out.println("Random Stat Upgraded");
	}
	
	
	void pickUpArmour(Armour armour) {
		playerArmour = armour;
		weight = playerArmour.weight + playerWeapon.weight;
		System.out.println("New Armour Equipped");
	}

	
	void pickUpWeapon(Weapon weapon) {
		playerWeapon = weapon;
		weight = playerArmour.weight + playerWeapon.weight;
		System.out.println("New Weapon Equipped");
	}


}
