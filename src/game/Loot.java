package game;
//Justin Watts ~ Jan. 16, 2023

abstract class Loot {
	static int currentId = 0;
	int id;

	Loot(){
		id = currentId+1;
		currentId += 1;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
