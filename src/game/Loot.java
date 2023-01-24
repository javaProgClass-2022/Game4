package game;
//Justin Watts ~ Jan. 16, 2023
//Using Polymorphism so all equipeable items can be stored neatly

abstract class Loot {
	static int currentId = 0;
	int id;
	String name;

	Loot(){
		id = currentId+1;
		currentId += 1;
	}	
}
