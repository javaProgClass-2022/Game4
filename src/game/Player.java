package game;
//Justin, Jenifer, Lousia ~ Jan. 12, 2023
//Object class for player

import java.util.HashMap;

public class Player {
	int hp;
	int speed;
	int def;
	HashMap<String, Consumable> inventory = new HashMap <String, Consumable>();
	Armour playerArmour;
	Weapon playerWepon;
	Ability playerAbilty;

	Player(){
		hp = 100;
		speed = 100;
		def = 1;

	}
	

	void takeDmg(){}

	void dealDmg() {}
	
}
