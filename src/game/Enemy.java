package game;

// Justin, Lousia, Jenifer ~ Jan. 10, 2023
//Object class for the enemies within the text adventure

public class Enemy {
	String enemyNames[] = {"Hobgoblin", "Oger", "Dwarf", "Forst Sprite", "Ghost", "Water Spirt", "Elf", "Goblin", "Zombie", "Snake", "Gargoyle",
			"Blue Slime", "Green Slime", "Red Slime", "Flegling Dragons", "Golem", "Demon", "Troll", "Vampire", "Minotaur", "Phoenix", "Griffin",
			"Harpy", "Kelpie", "Dryad", "Satyr", "Centaur","Gnome", "Will o' wisp", "Wraith"

	};


	int hp;
	int atkDmg;
	int speed;

	Enemy(){
		hp = (int) ((Math.random()*10)) + MainGame.floor*5;
		atkDmg = (int) ((Math.random()*3)) + MainGame.floor*2;
		speed = (int) ((Math.random()*100))+1;
	}
	
	void takeDmg(){}

	void dealDmg(){}
}

