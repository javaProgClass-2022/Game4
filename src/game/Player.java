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
	}

	int dealWeaponDmg() {
		return playerWeapon.atkDmg + atkBoost; 
	}

	void useAbility(Enemy target) {

		if (playerAbility.cooldownRoomAmt <= playerAbility.roomsSinceUse) {
			hp += playerAbility.hpGain;
			target.hp -= playerAbility.atkDmg;
			playerAbility.roomsSinceUse = 0;
		}
		else {System.out.println("Ability is not off cooldown");}
	}

	void useItem(Consumable item) {
		hp += item.hpGain;
	}

	void pickupUpgrade (Upgrade up ) {
		def+=up.defenseUp;
		speed += up.speedUp;
		critChance += up.critChanceUp;
		critDmg += up.critDmgUp;
	}
	
	void pickUpArmour(Armour armour) {
		playerArmour = armour;
		weight = playerArmour.weight + playerWeapon.weight;
	}
	
	void pickUpWeapon(Weapon weapon) {
		playerWeapon = weapon;
		weight = playerArmour.weight + playerWeapon.weight;
	}


}
