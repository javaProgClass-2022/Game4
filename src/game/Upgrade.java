package game;
//Justin, Jenifer, Lousia ~ Jan. 11, 2023
//Object class for the player upgrades

public class Upgrade {
 int hpUp;
 int defenseUp;
 int speedUp;
 int critChanceUp;
 int critDmgUp;
 
 Upgrade(){
	int typeRandomizer = (int) ((Math.random()*5));
	
	if (typeRandomizer == 0) hpUp = 1;
	if (typeRandomizer == 1) defenseUp = 1;
	if (typeRandomizer == 2) speedUp = 1;
	if (typeRandomizer == 3) critChanceUp = 1;
	if (typeRandomizer == 4) critDmgUp = 1;
 }
}