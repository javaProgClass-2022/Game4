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
	
	if (typeRandomizer == 0) hpUp = (int) ((Math.random()*5));
	if (typeRandomizer == 1) defenseUp = (int) ((Math.random()*5));
	if (typeRandomizer == 2) speedUp = (int) ((Math.random()*5));
	if (typeRandomizer == 3) critChanceUp = (int) ((Math.random()*5));
	if (typeRandomizer == 4)	critDmgUp = (int) ((Math.random()*5));
 }
}
