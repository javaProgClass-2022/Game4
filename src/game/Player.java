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
	
	final int MAXHP=40;

	//Player Inventory
	HashMap<String, Consumable> inventory = new HashMap <String, Consumable>();
	Armour playerArmour;
	Weapon playerWeapon;
	Ability playerAbility;

	Player(){
		hp = MAXHP;
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
			//TODO player death method
			System.out.println("player dead");
			return true;
		}else hp -= dmgTaken;
		return false;
	}


	int dealWeaponDmg() {
		int dmg = playerWeapon.atkDmg + atkBoost;

		//Checking if crit triggers
		int critRandomizer = (int) ((Math.random()*100)+1);	
		if (critRandomizer <= critChance) {
			dmg *= critDmg;
			MainGame.displayDialogue = ("Critical Hit! ");
		}else MainGame.displayDialogue ="";
		
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
		int actualGain=item.hpGain-(hp/10)*(hp%10);//the actuall hp amount gained (to not go over maxHP)
		MainGame.displayDialogue="You Consumed " + item.name + " and Healed " + actualGain + " HP";
		
		if (hp>MAXHP)hp=MAXHP;//hp value should never go above maxHP
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
	}


	void pickUpWeapon(Weapon weapon) {
		playerWeapon = weapon;
		MainGame.displayDialogue = (weapon.name + " equiped");
	}
	
	void pickUpConsumable(Consumable food) {
		inventory.put((food.name.toLowerCase()), food);
		MainGame.displayDialogue = (food.name + " added to inventory");
	}


}
