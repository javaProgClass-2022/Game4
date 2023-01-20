package game;

// Justin, Lousia, Jenifer ~ Jan. 10, 2023
//Object class for the consumable items within the text adventure

public class Consumable extends Loot {
	String name;
	static final String CONSUMEABLENAMES[] = {"Mystery potion", "Unknown elixir", "Enigmatic brew", "Cryptic tonic", "Mystical potion", "Occult elixir", "Enigmatic juice", 
			"Abstruse potion", "Arcane elixir", "Esoteric potion", "Vague potion", "Mysterious food", "Unknown meal", "Enigmatic dish", "Cryptic cuisine",
			"Mystical sustenance", "Occult nourishment", "Enigmatic fare", "Abstruse snack", "Arcane food", "Esoteric meal", "Vague sustenance", "Diet Coke",
			"Pizza", "Pasta", "Chicken"
	};

	int hpGain;

	Consumable(){
		hpGain = (int) ((Math.random()*6)+1);	
		name = CONSUMEABLENAMES[(int) ((Math.random()*CONSUMEABLENAMES.length))];

	}
	
	public String toString() {return name;}
}

