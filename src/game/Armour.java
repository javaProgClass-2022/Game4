package game;

// Justin, Lousia, Jenifer ~ Jan. 10, 2023
//Object class for the player Armour within the text adventure

public class Armour extends Loot {
	String name;
	static final String ARMOURNAMES[] = {"Dragonscale Armor", "Demonforged Plate", "Elvish Chainmail", "Goblinhide Jerkin", "Plate of the Minotaur", "Trollhide Hauberk",
			"Orcish Warplate", "Skeleton King's Cuirass", "Zombie Horde Shield", "Wraithblade Gauntlets", "Necromancer's Robe", "Witch Hunter's Hauberk", 
			"Warlock's Mantle", "Demon's Bane Cuirass", "Devil's Dread Helm", "Succubus's Seduction Armor", "Incubus's Temptation Armor", "Vampire's Cloak",
			"Werewolf's Fur", "Mummy's Wrappings", "Ghostly Shroud", "Poltergeist's Protection", "Frankenstein's Monster's Plates", "Medusa's Gaze Helm", 
			"Minotaur's Horned Helm", "Hydra's Scales", "Kraken's Tentacles", "Cerberus's Bite Helm", "Chimera's Mane", "Phoenix's Feathers", 
			"Unicorn's Glittering Armor", "Pegasus's Winged Helm", "Dragon Turtle's Shell", "Gargoyle's Stone Plates", 
			"Basilisk's Gaze Shield", "Manticore's Spikes", "Harpy's Talons", "Siren's Scales", "Naga's Scales", "Lamia's Coils",
			"Cockatrice's Beak Helm", "Kelpie's Hide", "Banshee's Wail Helm", "Dryad's Leaves", "Nymph's Dew Armor", "Satyr's Hooves", 
			"Centaur's Hide", "Gnome's Mithril Plates"
	};

	int defense;

	Armour(){
		name = ARMOURNAMES[(int) ((Math.random()*ARMOURNAMES.length))];
		defense = (int) ((Math.random()*4)+2)*MainGame.floor;
	}
	
	Armour(String defaultArmour){
		name = "Iron Armour";
		defense = 1;	
	}
	
	public String toString() {return name;}
}

