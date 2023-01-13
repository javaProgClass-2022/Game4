package game;
//Justin, Jenifer, Lousia ~ Jan. 12, 2023
//Object class for player

import java.util.HashMap;

public class Player {
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
	}
	

	void takeDmg(int dmg){
		int dmgTaken = dmg - def;
		hp =- dmgTaken;
	}

	int dealWeaponDmg() {
		return playerWeapon.atkDmg; 
	}
	
	void useAbility(Enemy target) {
		
		if (playerAbility.cooldownRoomAmt <= playerAbility.roomsSinceUse) {
		hp += playerAbility.hpGain;
		target.hp -= playerAbility.atkDmg;
		playerAbility.roomsSinceUse = 0;
		}
		else {System.out.println("Ability is not off cooldown");}
	}
	
	void pickupItem(Consumable item) {
		hp += item.hpGain;
	}
	
	void pickupUpgrade (Upgrade up ) {
		def+=up.defenseUp;
		speed += up.speedUp;
		critChance += up.critChanceUp;
		critDmg += up.critDmgUp;
	}
	
	
}
