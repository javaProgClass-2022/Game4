package game;

// Justin, Lousia, Jenifer ~ Jan. 10, 2023
//Object class for the enemies within the text adventure

public class Enemy {
	String name;
	static final String ENEMYNAMES[] = {"Hobgoblin", "Oger", "Dwarf", "Forst Sprite", "Ghost", "Water Spirt", "Elf", "Goblin", "Zombie", "Snake", "Gargoyle",
			"Blue Slime", "Green Slime", "Red Slime", "Flegling Dragons", "Golem", "Demon", "Troll", "Vampire", "Minotaur", "Phoenix", "Griffin",
			"Harpy", "Kelpie", "Dryad", "Satyr", "Centaur","Gnome", "Will o' wisp", "Wraith"
	};


	int hp;
	int atkDmg;
	int speed;

	Enemy(){
		name = ENEMYNAMES[(int) ((Math.random()*ENEMYNAMES.length))];
		hp = (int) ((Math.random()*10)) + MainGame.floor*5;
		atkDmg = (int) ((Math.random()*3)) + MainGame.floor*2;
		speed = (int) ((Math.random()*100))+1;
	}


	void takeDmg(int dmg){
		hp -=dmg;
		if(hp<0) hp=0;
		MainGame.displayDialogue += ("The " + name + " took " + dmg + " damage");
		
		
		for(int i=MainGame.displayDialogue.length()%48;i<=48;i++) MainGame.displayDialogue += " ";
		MainGame.displayDialogue +=(hp +" hp left" );
		
		for(int i=MainGame.displayDialogue.length()%48;i<=48;i++) MainGame.displayDialogue += " ";
		
		if (hp <= 0) MainGame.displayDialogue += ("The " + name + " has been slain");
		
	}
}

