package Game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.function.Function;

public class Move {
	int ID;
	String name = "This move is broken.";
	int type;
	int category;
	int PP;
	int Power;
	int Accuracy;
	int baseCrit = 0;

	int currentPP;

	int PHYSICAL = 1;
	int SPECIAL = 2;
	int STATUS = 3;
	int KO = -1;
	int NONE = -2;
	int SELF = -3;
	int GUARANTEED = -4;
	int TBD;

	public Move(String name){
		this.name=name;
		if(name.equalsIgnoreCase("Absorb")){
			type = Type.GRASS;
			category = this.SPECIAL;
			PP = 20;
			Power = 20;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Acid")){
			type = Type.POISON;
			category = this.SPECIAL;
			PP = 30;
			Power = 40;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Acid Armor")){
			type = Type.POISON;
			category=this.STATUS;
			PP = 40;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Aerial Ace")){
			type = Type.FLYING;
			category=this.PHYSICAL;
			PP = 20;
			Power = 60;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Aeroblast")){
			type = Type.FLYING;
			category=this.SPECIAL;
			PP = 5;
			Power = 100;
			Accuracy = 95;
		}
		else if(name.equalsIgnoreCase("Agility")){
			type = Type.PSYCHIC;
			category = this.STATUS;
			PP = 30;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Air Cutter")){
			type = Type.FLYING;
			category=this.SPECIAL;
			PP = 25;
			Power = 55;
			Accuracy = 95;
		}
		else if(name.equalsIgnoreCase("Amnesia")){
			type = Type.PSYCHIC;
			category=this.STATUS;
			PP = 20;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Ancient Power")){
			type = Type.ROCK;
			category=this.SPECIAL;
			PP = 5;
			Power = 60;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Arm Thrust")){
			type = Type.FIGHTING;
			category=this.PHYSICAL;
			PP = 20;
			Power = 15;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Aromatherapy")){
			type = Type.GRASS;
			category=this.STATUS;
			PP = 5;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Assist")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 20;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Astonish")){
			type = Type.GHOST;
			category=this.PHYSICAL;
			PP = 15;
			Power = 30;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Attract")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 15;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Aurora Beam")){
			type = Type.ICE;
			category = this.SPECIAL;
			PP = 20;
			Power = 65;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Barrage")){
			type = Type.NORMAL;
			category=this.PHYSICAL;
			PP = 20;
			Power = 15;
			Accuracy = 85;
		}
		else if(name.equalsIgnoreCase("Barrier")){
			type = Type.PSYCHIC;
			category=this.STATUS;
			PP = 30;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Baton Pass")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 40;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Beat Up")){
			type = Type.DARK;
			category=this.PHYSICAL;
			PP = 10;
			Power = 10;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Belly Drum")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 10;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Bide")){
			type = Type.NORMAL;
			category=this.PHYSICAL;
			PP = 10;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Bind")){
			type = Type.NORMAL;
			category = this.PHYSICAL;
			PP = 20;
			Power = 15;
			Accuracy = 75;
		}
		else if(name.equalsIgnoreCase("Bite")){
			type = Type.DARK;
			category = this.PHYSICAL;
			PP = 25;
			Power = 60;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Blast Burn")){
			type = Type.FIRE;
			category=this.SPECIAL;
			PP = 5;
			Power = 150;
			Accuracy = 90;
		}
		else if(name.equalsIgnoreCase("Blaze Kick")){
			type = Type.FIRE;
			category=this.PHYSICAL;
			PP = 10;
			Power = 85;
			Accuracy = 90;
		}
		else if(name.equalsIgnoreCase("Blizzard")){
			type = Type.ICE;
			category = this.SPECIAL;
			PP = 5;
			Power = 120;
			Accuracy = 70;
		}
		else if(name.equalsIgnoreCase("Block")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 5;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Body Slam")){
			type = Type.NORMAL;
			category = this.PHYSICAL;
			PP = 15;
			Power = 85;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Bone Club")){
			type = Type.GROUND;
			category=this.PHYSICAL;
			PP = 20;
			Power = 65;
			Accuracy = 85;
		}
		else if(name.equalsIgnoreCase("Bone Rush")){
			type = Type.GROUND;
			category=this.PHYSICAL;
			PP = 10;
			Power = 25;
			Accuracy = 80;
		}
		else if(name.equalsIgnoreCase("Bonemerang")){
			type = Type.GROUND;
			category=this.PHYSICAL;
			PP = 10;
			Power = 50;
			Accuracy = 90;
		}
		else if(name.equalsIgnoreCase("Bounce")){
			type = Type.FLYING;
			category=this.PHYSICAL;
			PP = 5;
			Power = 85;
			Accuracy = 85;
		}
		else if(name.equalsIgnoreCase("Brick Break")){
			type = Type.FIGHTING;
			category=this.PHYSICAL;
			PP = 15;
			Power = 75;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Body Slam")){
			type = Type.NORMAL;
			category = this.PHYSICAL;
			PP = 15;
			Power = 85;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Bone Club")){
			type = Type.GROUND;
			category=this.PHYSICAL;
			PP = 20;
			Power = 65;
			Accuracy = 85;
		}
		else if(name.equalsIgnoreCase("Bone Rush")){
			type = Type.GROUND;
			category=this.PHYSICAL;
			PP = 10;
			Power = 25;
			Accuracy = 80;
		}
		else if(name.equalsIgnoreCase("Bonemerang")){
			type = Type.GROUND;
			category=this.PHYSICAL;
			PP = 10;
			Power = 50;
			Accuracy = 90;
		}
		else if(name.equalsIgnoreCase("Bounce")){
			type = Type.FLYING;
			category=this.PHYSICAL;
			PP = 5;
			Power = 85;
			Accuracy = 85;
		}
		else if(name.equalsIgnoreCase("Brick Break")){
			type = Type.FIGHTING;
			category=this.PHYSICAL;
			PP = 15;
			Power = 75;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Bubble")){
			type = Type.WATER;
			category = this.SPECIAL;
			PP = 30;
			Power = 20;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("BubbleBeam")){
			type = Type.WATER;
			category = this.SPECIAL;
			PP = 20;
			Power = 65;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Bulk Up")){
			type = Type.FIGHTING;
			category=this.STATUS;
			PP = 20;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Bullet Seed")){
			type = Type.GRASS;
			category=this.PHYSICAL;
			PP = 30;
			Power = 10;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Calm Mind")){
			type = Type.PSYCHIC;
			category=this.STATUS;
			PP = 20;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Camouflage")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 20;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Charge")){
			type = Type.ELECTRIC;
			category=this.STATUS;
			PP = 20;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Charm")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 20;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Clamp")){
			type = Type.WATER;
			category=this.PHYSICAL;
			PP = 10;
			Power = 35;
			Accuracy = 75;
		}
		else if(name.equalsIgnoreCase("Comet Punch")){
			type = Type.NORMAL;
			category = this.PHYSICAL;
			PP = 15;
			Power = 18;
			Accuracy = 85;
		}
		else if(name.equalsIgnoreCase("Confuse Ray")){
			type = Type.GHOST;
			category=this.STATUS;
			PP = 10;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Confusion")){
			type = Type.PSYCHIC;
			category = this.SPECIAL;
			PP = 25;
			Power = 50;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Constrict")){
			type = Type.NORMAL;
			category=this.PHYSICAL;
			PP = 35;
			Power = 10;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Conversion")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 30;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Conversion 2")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 30;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Cosmic Power")){
			type = Type.PSYCHIC;
			category=this.STATUS;
			PP = 20;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Cotton Spore")){
			type = Type.GRASS;
			category=this.STATUS;
			PP = 40;
			Power = NONE;
			Accuracy = 85;
		}
		else if(name.equalsIgnoreCase("Counter")){
			type = Type.FIGHTING;
			category = this.PHYSICAL;
			PP = 20;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Covet")){
			type = Type.NORMAL;
			category=this.PHYSICAL;
			PP = 40;
			Power = 40;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Crabhammer")){
			type = Type.WATER;
			category=this.PHYSICAL;
			PP = 10;
			Power = 90;
			Accuracy = 85;
		}
		else if(name.equalsIgnoreCase("Cross Chop")){
			type = Type.FIGHTING;
			category=this.PHYSICAL;
			PP = 5;
			Power = 100;
			Accuracy = 80;
		}
		else if(name.equalsIgnoreCase("Crunch")){
			type = Type.DARK;
			category=this.PHYSICAL;
			PP = 15;
			Power = 80;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Crush Claw")){
			type = Type.NORMAL;
			category=this.PHYSICAL;
			PP = 10;
			Power = 75;
			Accuracy = 95;
		}
		else if(name.equalsIgnoreCase("Curse")){
			type = Type.GHOST;
			category=this.STATUS;
			PP = 10;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Cut")){
			type = Type.NORMAL;
			category = this.PHYSICAL;
			PP = 30;
			Power = 50;
			Accuracy = 95;
		}
		else if(name.equalsIgnoreCase("Defense Curl")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 40;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Destiny Bond")){
			type = Type.GHOST;
			category=this.STATUS;
			PP = 5;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Detect")){
			type = Type.FIGHTING;
			category=this.STATUS;
			PP = 5;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Dig")){
			type = Type.GROUND;
			category = this.PHYSICAL;
			PP = 10;
			Power = 60;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Disable")){
			type = Type.NORMAL;
			category = this.STATUS;
			PP = 20;
			Power = NONE;
			Accuracy = 55;
		}
		else if(name.equalsIgnoreCase("Dive")){
			type = Type.WATER;
			category=this.PHYSICAL;
			PP = 10;
			Power = 60;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Dizzy Punch")){
			type = Type.NORMAL;
			category=this.PHYSICAL;
			PP = 10;
			Power = 70;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Doom Desire")){
			type = Type.STEEL;
			category=this.SPECIAL;
			PP = 5;
			Power = 120;
			Accuracy = 85;
		}
		else if(name.equalsIgnoreCase("Double Kick")){
			type = Type.FIGHTING;
			category = this.PHYSICAL;
			PP = 30;
			Power = 30;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("DoubleSlap")){
			type = Type.NORMAL;
			category = this.PHYSICAL;
			PP = 10;
			Power = 15;
			Accuracy = 85;
		}
		else if(name.equalsIgnoreCase("Double Team")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 15;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Double-Edge")){
			type = Type.NORMAL;
			category = this.PHYSICAL;
			PP = 15;
			Power = 120;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Dragon Breath")){
			type = Type.DRAGON;
			category=this.SPECIAL;
			PP = 20;
			Power = 60;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Dragon Claw")){
			type = Type.DRAGON;
			category=this.PHYSICAL;
			PP = 15;
			Power = 80;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Dragon Dance")){
			type = Type.DRAGON;
			category=this.PHYSICAL;
			PP = 20;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Dragon Rage")){
			type = Type.DRAGON;
			category = this.SPECIAL;
			PP = 10;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Dream Eater")){
			type = Type.PSYCHIC;
			category=this.SPECIAL;
			PP = 15;
			Power = 100;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Drill Peck")){
			type = Type.FLYING;
			category = this.PHYSICAL;
			PP = 20;
			Power = 80;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Dynamic Punch")){
			type = Type.FIGHTING;
			category=this.PHYSICAL;
			PP = 5;
			Power = 100;
			Accuracy = 50;
		}
		else if(name.equalsIgnoreCase("Earthquake")){
			type = Type.GROUND;
			category = this.PHYSICAL;
			PP = 10;
			Power = 100;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Egg Bomb")){
			type = Type.NORMAL;
			category=this.PHYSICAL;
			PP = 10;
			Power = 100;
			Accuracy = 75;
		}
		else if(name.equalsIgnoreCase("Ember")){
			type = Type.FIRE;
			category = this.SPECIAL;
			PP = 25;
			Power = 40;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Encore")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 5;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Endeavor")){
			type = Type.NORMAL;
			category=this.PHYSICAL;
			PP = 5;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Endure")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 10;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Eruption")){
			type = Type.FIRE;
			category=this.SPECIAL;
			PP = 5;
			Power = 150;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Explosion")){
			type = Type.NORMAL;
			category=this.PHYSICAL;
			PP = 5;
			Power = 250;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Extrasensory")){
			type = Type.PSYCHIC;
			category=this.SPECIAL;
			PP = 30;
			Power = 80;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Extreme Speed")){
			type = Type.NORMAL;
			category=this.PHYSICAL;
			PP = 5;
			Power = 80;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Facade")){
			type = Type.NORMAL;
			category=this.PHYSICAL;
			PP = 20;
			Power = 70;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Fake Out")){
			type = Type.NORMAL;
			category=this.PHYSICAL;
			PP = 10;
			Power = 40;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Fake Tears")){
			type = Type.DARK;
			category=this.STATUS;
			PP = 20;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("False Swipe")){
			type = Type.NORMAL;
			category=this.PHYSICAL;
			PP = 40;
			Power = 40;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Feather Dance")){
			type = Type.FLYING;
			category=this.STATUS;
			PP = 15;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Feint Attack")){
			type = Type.DARK;
			category=this.PHYSICAL;
			PP = 20;
			Power = 60;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Fire Blast")){
			type = Type.FIRE;
			category=this.SPECIAL;
			PP = 5;
			Power = 120;
			Accuracy = 85;
		}
		else if(name.equalsIgnoreCase("Fire Punch")){
			type = Type.FIRE;
			category = this.PHYSICAL;
			PP = 15;
			Power = 75;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Fire Spin")){
			type = Type.FIRE;
			category = this.SPECIAL;
			PP = 15;
			Power = 15;
			Accuracy = 70;
		}
		else if(name.equalsIgnoreCase("Fissure")){
			type = Type.GROUND;
			category = this.PHYSICAL;
			PP = 5;
			Power = NONE;
			Accuracy = 30;
		}
		else if(name.equalsIgnoreCase("Flail")){
			type = Type.NORMAL;
			category=this.PHYSICAL;
			PP = 15;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Flame Wheel")){
			type = Type.FIRE;
			category=this.PHYSICAL;
			PP = 25;
			Power = 60;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Flamethrower")){
			type = Type.FIRE;
			category = this.SPECIAL;
			PP = 15;
			Power = 95;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Flash")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 20;
			Power = NONE;
			Accuracy = 70;
		}
		else if(name.equalsIgnoreCase("Flatter")){
			type = Type.DARK;
			category=this.STATUS;
			PP = 15;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Fly")){
			type = Type.FLYING;
			category = this.PHYSICAL;
			PP = 15;
			Power = 70;
			Accuracy = 95;
		}
		else if(name.equalsIgnoreCase("Focus Energy")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 30;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Focus Punch")){
			type = Type.FIGHTING;
			category=this.PHYSICAL;
			PP = 20;
			Power = 150;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Follow Me")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 20;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Foresight")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 40;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Frenzy Plant")){
			type = Type.GRASS;
			category=this.SPECIAL;
			PP = 5;
			Power = 150;
			Accuracy = 90;
		}
		else if(name.equalsIgnoreCase("Frustration")){
			type = Type.NORMAL;
			category=this.PHYSICAL;
			PP = 20;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Fury Attack")){
			type = Type.NORMAL;
			category = this.PHYSICAL;
			PP = 20;
			Power = 15;
			Accuracy = 85;
		}
		else if(name.equalsIgnoreCase("Fury Cutter")){
			type = Type.BUG;
			category=this.PHYSICAL;
			PP = 20;
			Power = 10;
			Accuracy = 95;
		}
		else if(name.equalsIgnoreCase("Fury Swipes")){
			type = Type.NORMAL;
			category=this.PHYSICAL;
			PP = 15;
			Power = 18;
			Accuracy = 80;
		}
		else if(name.equalsIgnoreCase("Future Sight")){
			type = Type.PSYCHIC;
			category=this.SPECIAL;
			PP = 15;
			Power = 80;
			Accuracy = 90;
		}
		else if(name.equalsIgnoreCase("Giga Drain")){
			type = Type.GRASS;
			category=this.SPECIAL;
			PP = 5;
			Power = 60;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Glare")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 30;
			Power = NONE;
			Accuracy = 75;
		}
		else if(name.equalsIgnoreCase("Grass Whistle")){
			type = Type.GRASS;
			category=this.STATUS;
			PP = 15;
			Power = NONE;
			Accuracy = 55;
		}
		else if(name.equalsIgnoreCase("Growl")){
			type = Type.NORMAL;
			category = this.STATUS;
			PP = 40;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Growth")){
			type = Type.NORMAL;
			category = this.SPECIAL;
			PP = 40;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Grudge")){
			type = Type.GHOST;
			category=this.STATUS;
			PP = 5;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Guillotine")){
			type = Type.NORMAL;
			category = this.PHYSICAL;
			PP = 5;
			Power = NONE;
			Accuracy = 30;
		}
		else if(name.equalsIgnoreCase("Gust")){
			type = Type.FLYING;
			category = this.SPECIAL;
			PP = 35;
			Power = 40;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Hail")){
			type = Type.ICE;
			category=this.STATUS;
			PP = 10;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Harden")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 30;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Haze")){
			type = Type.ICE;
			category=this.STATUS;
			PP = 30;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Headbutt")){
			type = Type.NORMAL;
			category = this.PHYSICAL;
			PP = 15;
			Power = 70;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Heal Bell")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 5;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Heat Wave")){
			type = Type.FIRE;
			category=this.SPECIAL;
			PP = 10;
			Power = 100;
			Accuracy = 90;
		}
		else if(name.equalsIgnoreCase("Helping Hand")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 20;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Hidden Power")){
			type = Type.BUG;
			category=this.SPECIAL;
			PP = 15;
			Power = 70;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("High Jump Kick")){
			type = Type.FIGHTING;
			category=this.PHYSICAL;
			PP = 20;
			Power = 85;
			Accuracy = 90;
		}
		else if(name.equalsIgnoreCase("Horn Attack")){
			type = Type.NORMAL;
			category = this.PHYSICAL;
			PP = 25;
			Power = 65;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Horn Drill")){
			type = Type.NORMAL;
			category = this.PHYSICAL;
			PP = 5;
			Power = NONE;
			Accuracy = 30;
		}
		else if(name.equalsIgnoreCase("Howl")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 40;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Hydro Cannon")){
			type = Type.WATER;
			category=this.SPECIAL;
			PP = 5;
			Power = 150;
			Accuracy = 90;
		}
		else if(name.equalsIgnoreCase("Hydro Pump")){
			type = Type.WATER;
			category = this.SPECIAL;
			PP = 5;
			Power = 120;
			Accuracy = 80;
		}
		else if(name.equalsIgnoreCase("Hyper Beam")){
			type = Type.NORMAL;
			category = this.SPECIAL;
			PP = 5;
			Power = 150;
			Accuracy = 90;
		}
		else if(name.equalsIgnoreCase("Hyper Fang")){
			type = Type.NORMAL;
			category=this.PHYSICAL;
			PP = 15;
			Power = 80;
			Accuracy = 90;
		}
		else if(name.equalsIgnoreCase("Hyper Voice")){
			type = Type.NORMAL;
			category=this.SPECIAL;
			PP = 10;
			Power = 90;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Hypnosis")){
			type = Type.PSYCHIC;
			category = this.STATUS;
			PP = 20;
			Power = NONE;
			Accuracy = 60;
		}
		else if(name.equalsIgnoreCase("Ice Ball")){
			type = Type.ICE;
			category=this.PHYSICAL;
			PP = 20;
			Power = 30;
			Accuracy = 90;
		}
		else if(name.equalsIgnoreCase("Ice Beam")){
			type = Type.ICE;
			category = this.SPECIAL;
			PP = 10;
			Power = 95;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Ice Punch")){
			type = Type.ICE;
			category = this.PHYSICAL;
			PP = 15;
			Power = 75;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Icicle Spear")){
			type = Type.ICE;
			category=this.PHYSICAL;
			PP = 30;
			Power = 10;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Icy Wind")){
			type = Type.ICE;
			category=this.SPECIAL;
			PP = 15;
			Power = 55;
			Accuracy = 95;
		}
		else if(name.equalsIgnoreCase("Imprison")){
			type = Type.PSYCHIC;
			category=this.STATUS;
			PP = 10;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Ingrain")){
			type = Type.GRASS;
			category=this.STATUS;
			PP = 20;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Iron Defense")){
			type = Type.STEEL;
			category=this.STATUS;
			PP = 15;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Iron Tail")){
			type = Type.STEEL;
			category=this.PHYSICAL;
			PP = 15;
			Power = 100;
			Accuracy = 75;
		}
		else if(name.equalsIgnoreCase("Jump Kick")){
			type = Type.FIGHTING;
			category = this.PHYSICAL;
			PP = 25;
			Power = 70;
			Accuracy = 95;
		}
		else if(name.equalsIgnoreCase("Karate Chop")){
			type = Type.FIGHTING;
			category = this.PHYSICAL;
			PP = 25;
			Power = 50;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Kinesis")){
			type = Type.PSYCHIC;
			category=this.STATUS;
			PP = 15;
			Power = NONE;
			Accuracy = 80;
		}
		else if(name.equalsIgnoreCase("Knock Off")){
			type = Type.DARK;
			category=this.PHYSICAL;
			PP = 20;
			Power = 20;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Leaf Blade")){
			type = Type.GRASS;
			category=this.PHYSICAL;
			PP = 15;
			Power = 70;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Leech Life")){
			type = Type.BUG;
			category=this.PHYSICAL;
			PP = 15;
			Power = 20;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Leech Seed")){
			type = Type.GRASS;
			category = this.STATUS;
			PP = 10;
			Power = NONE;
			Accuracy = 90;
		}
		else if(name.equalsIgnoreCase("Leer")){
			type = Type.NORMAL;
			category = this.STATUS;
			PP = 30;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Lick")){
			type = Type.GHOST;
			category=this.PHYSICAL;
			PP = 30;
			Power = 20;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Light Screen")){
			type = Type.PSYCHIC;
			category=this.STATUS;
			PP = 30;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Lock-On")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 5;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Lovely Kiss")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 10;
			Power = NONE;
			Accuracy = 75;
		}
		else if(name.equalsIgnoreCase("Low Kick")){
			type = Type.FIGHTING;
			category = this.PHYSICAL;
			PP = 20;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Luster Purge")){
			type = Type.PSYCHIC;
			category=this.SPECIAL;
			PP = 5;
			Power = 70;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Mach Punch")){
			type = Type.FIGHTING;
			category=this.PHYSICAL;
			PP = 30;
			Power = 40;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Magic Coat")){
			type = Type.PSYCHIC;
			category=this.STATUS;
			PP = 15;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Magical Leaf")){
			type = Type.GRASS;
			category=this.SPECIAL;
			PP = 20;
			Power = 60;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Magnitude")){
			type = Type.GROUND;
			category=this.PHYSICAL;
			PP = 30;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Mean Look")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 5;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Meditate")){
			type = Type.PSYCHIC;
			category = this.STATUS;
			PP = 40;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Mega Drain")){
			type = Type.GRASS;
			category = this.SPECIAL;
			PP = 10;
			Power = 40;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Mega Kick")){
			type = Type.NORMAL;
			category = this.PHYSICAL;
			PP = 5;
			Power = 120;
			Accuracy = 75;
		}
		else if(name.equalsIgnoreCase("Mega Punch")){
			type = Type.NORMAL;
			category = this.PHYSICAL;
			PP = 20;
			Power = 80;
			Accuracy = 85;
		}
		else if(name.equalsIgnoreCase("Megahorn")){
			type = Type.BUG;
			category=this.PHYSICAL;
			PP = 10;
			Power = 120;
			Accuracy = 85;
		}
		else if(name.equalsIgnoreCase("Memento")){
			type = Type.DARK;
			category=this.STATUS;
			PP = 10;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Metal Claw")){
			type = Type.STEEL;
			category=this.PHYSICAL;
			PP = 35;
			Power = 50;
			Accuracy = 95;
		}
		else if(name.equalsIgnoreCase("Metal Sound")){
			type = Type.STEEL;
			category=this.STATUS;
			PP = 40;
			Power = NONE;
			Accuracy = 85;
		}
		else if(name.equalsIgnoreCase("Meteor Mash")){
			type = Type.STEEL;
			category=this.PHYSICAL;
			PP = 10;
			Power = 100;
			Accuracy = 85;
		}
		else if(name.equalsIgnoreCase("Metronome")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 10;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Milk Drink")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 10;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Mimic")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 10;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Mind Reader")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 5;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Minimize")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 20;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Mirror Coat")){
			type = Type.PSYCHIC;
			category=this.SPECIAL;
			PP = 20;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Mirror Move")){
			type = Type.FLYING;
			category=this.STATUS;
			PP = 20;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Mist")){
			type = Type.ICE;
			category = this.STATUS;
			PP = 30;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Mist Ball")){
			type = Type.PSYCHIC;
			category=this.SPECIAL;
			PP = 5;
			Power = 70;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Moonlight")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 5;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Morning Sun")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 5;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Mud Shot")){
			type = Type.GROUND;
			category=this.SPECIAL;
			PP = 15;
			Power = 55;
			Accuracy = 95;
		}
		else if(name.equalsIgnoreCase("Mud Sport")){
			type = Type.GROUND;
			category=this.STATUS;
			PP = 15;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Mud-Slap")){
			type = Type.GROUND;
			category=this.SPECIAL;
			PP = 10;
			Power = 20;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Muddy Water")){
			type = Type.WATER;
			category=this.SPECIAL;
			PP = 10;
			Power = 95;
			Accuracy = 85;
		}
		else if(name.equalsIgnoreCase("Nature Power")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 20;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Needle Arm")){
			type = Type.GRASS;
			category=this.PHYSICAL;
			PP = 15;
			Power = 60;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Night Shade")){
			type = Type.GHOST;
			category=this.SPECIAL;
			PP = 15;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Nightmare")){
			type = Type.GHOST;
			category=this.STATUS;
			PP = 15;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Octazooka")){
			type = Type.WATER;
			category=this.SPECIAL;
			PP = 10;
			Power = 65;
			Accuracy = 85;
		}
		else if(name.equalsIgnoreCase("Odor Sleuth")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 40;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Outrage")){
			type = Type.DRAGON;
			category=this.PHYSICAL;
			PP = 15;
			Power = 90;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Overheat")){
			type = Type.FIRE;
			category=this.SPECIAL;
			PP = 5;
			Power = 140;
			Accuracy = 90;
		}
		else if(name.equalsIgnoreCase("Pain Split")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 20;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Pay Day")){
			type = Type.NORMAL;
			category = this.PHYSICAL;
			PP = 20;
			Power = 40;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Peck")){
			type = Type.FLYING;
			category = this.PHYSICAL;
			PP = 35;
			Power = 35;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Perish Song")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 5;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Petal Dance")){
			type = Type.GRASS;
			category = this.SPECIAL;
			PP = 20;
			Power = 70;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Pin Missile")){
			type = Type.BUG;
			category = this.PHYSICAL;
			PP = 20;
			Power = 14;
			Accuracy = 85;
		}
		else if(name.equalsIgnoreCase("Poison Fang")){
			type = Type.POISON;
			category=this.PHYSICAL;
			PP = 15;
			Power = 50;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Poison Gas")){
			type = Type.POISON;
			category=this.STATUS;
			PP = 40;
			Power = NONE;
			Accuracy = 55;
		}
		else if(name.equalsIgnoreCase("Poison Powder")){
			type = Type.POISON;
			category = this.STATUS;
			PP = 35;
			Power = NONE;
			Accuracy = 75;
		}
		else if(name.equalsIgnoreCase("Poison Sting")){
			type = Type.POISON;
			category = this.PHYSICAL;
			PP = 35;
			Power = 15;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Poison Tail")){
			type = Type.POISON;
			category=this.PHYSICAL;
			PP = 25;
			Power = 50;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Pound")){
			type = Type.NORMAL;
			category = this.PHYSICAL;
			PP = 35;
			Power = 40;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Powder Snow")){
			type = Type.ICE;
			category=this.SPECIAL;
			PP = 25;
			Power = 40;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Present")){
			type = Type.NORMAL;
			category=this.PHYSICAL;
			PP = 15;
			Power = NONE;
			Accuracy = 90;
		}
		else if(name.equalsIgnoreCase("Protect")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 10;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Psybeam")){
			type = Type.PSYCHIC;
			category = this.SPECIAL;
			PP = 20;
			Power = 65;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Psych Up")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 10;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Psychic")){
			type = Type.PSYCHIC;
			category = this.SPECIAL;
			PP = 10;
			Power = 90;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Psycho Boost")){
			type = Type.PSYCHIC;
			category=this.SPECIAL;
			PP = 5;
			Power = 140;
			Accuracy = 90;
		}
		else if(name.equalsIgnoreCase("Psywave")){
			type = Type.PSYCHIC;
			category=this.SPECIAL;
			PP = 15;
			Power = NONE;
			Accuracy = 80;
		}
		else if(name.equalsIgnoreCase("Pursuit")){
			type = Type.DARK;
			category=this.PHYSICAL;
			PP = 20;
			Power = 40;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Quick Attack")){
			type = Type.NORMAL;
			category = this.PHYSICAL;
			PP = 30;
			Power = 40;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Rage")){
			type = Type.NORMAL;
			category = this.SPECIAL;
			PP = 20;
			Power = 20;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Rain Dance")){
			type = Type.WATER;
			category=this.STATUS;
			PP = 5;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Rapid Spin")){
			type = Type.NORMAL;
			category=this.PHYSICAL;
			PP = 40;
			Power = 20;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Razor Leaf")){
			type = Type.GRASS;
			category = this.PHYSICAL;
			PP = 25;
			Power = 55;
			Accuracy = 95;
		}
		else if(name.equalsIgnoreCase("Razor Wind")){
			type = Type.NORMAL;
			category = this.SPECIAL;
			PP = 10;
			Power = 80;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Recover")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 20;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Recycle")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 10;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Reflect")){
			type = Type.PSYCHIC;
			category=this.STATUS;
			PP = 20;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Refresh")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 20;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Rest")){
			type = Type.PSYCHIC;
			category=this.STATUS;
			PP = 10;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Return")){
			type = Type.NORMAL;
			category=this.PHYSICAL;
			PP = 20;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Revenge")){
			type = Type.FIGHTING;
			category=this.PHYSICAL;
			PP = 10;
			Power = 60;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Reversal")){
			type = Type.FIGHTING;
			category=this.PHYSICAL;
			PP = 15;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Roar")){
			type = Type.NORMAL;
			category = this.STATUS;
			PP = 20;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Rock Blast")){
			type = Type.ROCK;
			category=this.PHYSICAL;
			PP = 10;
			Power = 25;
			Accuracy = 80;
		}
		else if(name.equalsIgnoreCase("Rock Slide")){
			type = Type.ROCK;
			category=this.PHYSICAL;
			PP = 10;
			Power = 75;
			Accuracy = 90;
		}
		else if(name.equalsIgnoreCase("Rock Smash")){
			type = Type.FIGHTING;
			category=this.PHYSICAL;
			PP = 15;
			Power = 20;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Rock Throw")){
			type = Type.ROCK;
			category = this.PHYSICAL;
			PP = 15;
			Power = 50;
			Accuracy = 90;
		}
		else if(name.equalsIgnoreCase("Rock Tomb")){
			type = Type.ROCK;
			category=this.PHYSICAL;
			PP = 10;
			Power = 50;
			Accuracy = 80;
		}
		else if(name.equalsIgnoreCase("Role Play")){
			type = Type.PSYCHIC;
			category=this.STATUS;
			PP = 10;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Rolling Kick")){
			type = Type.FIGHTING;
			category = this.PHYSICAL;
			PP = 15;
			Power = 60;
			Accuracy = 85;
		}
		else if(name.equalsIgnoreCase("Rollout")){
			type = Type.ROCK;
			category=this.PHYSICAL;
			PP = 20;
			Power = 30;
			Accuracy = 90;
		}
		else if(name.equalsIgnoreCase("Sacred Fire")){
			type = Type.FIRE;
			category=this.PHYSICAL;
			PP = 5;
			Power = 100;
			Accuracy = 95;
		}
		else if(name.equalsIgnoreCase("Safeguard")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 25;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Sand-Attack")){
			type = Type.GROUND;
			category = this.STATUS;
			PP = 15;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Sand Tomb")){
			type = Type.GROUND;
			category=this.PHYSICAL;
			PP = 15;
			Power = 15;
			Accuracy = 70;
		}
		else if(name.equalsIgnoreCase("Sandstorm")){
			type = Type.ROCK;
			category=this.STATUS;
			PP = 10;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Scary Face")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 10;
			Power = NONE;
			Accuracy = 90;
		}
		else if(name.equalsIgnoreCase("Scratch")){
			type = Type.NORMAL;
			category = this.PHYSICAL;
			PP = 35;
			Power = 40;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Screech")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 40;
			Power = NONE;
			Accuracy = 85;
		}
		else if(name.equalsIgnoreCase("Secret Power")){
			type = Type.NORMAL;
			category=this.PHYSICAL;
			PP = 20;
			Power = 70;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Seismic Toss")){
			type = Type.FIGHTING;
			category = this.PHYSICAL;
			PP = 20;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Self-Destruct")){
			type = Type.NORMAL;
			category=this.PHYSICAL;
			PP = 5;
			Power = 200;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Shadow Ball")){
			type = Type.GHOST;
			category=this.SPECIAL;
			PP = 15;
			Power = 80;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Shadow Punch")){
			type = Type.GHOST;
			category=this.PHYSICAL;
			PP = 20;
			Power = 60;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Sharpen")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 30;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Sheer Cold")){
			type = Type.ICE;
			category=this.SPECIAL;
			PP = 5;
			Power = NONE;
			Accuracy = 30;
		}
		else if(name.equalsIgnoreCase("Shock Wave")){
			type = Type.ELECTRIC;
			category=this.SPECIAL;
			PP = 20;
			Power = 60;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Signal Beam")){
			type = Type.BUG;
			category=this.SPECIAL;
			PP = 15;
			Power = 75;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Silver Wind")){
			type = Type.BUG;
			category=this.SPECIAL;
			PP = 5;
			Power = 60;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Sing")){
			type = Type.NORMAL;
			category = this.STATUS;
			PP = 15;
			Power = NONE;
			Accuracy = 55;
		}
		else if(name.equalsIgnoreCase("Sketch")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 1;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Skill Swap")){
			type = Type.PSYCHIC;
			category=this.STATUS;
			PP = 10;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Skull Bash")){
			type = Type.NORMAL;
			category=this.PHYSICAL;
			PP = 15;
			Power = 100;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Sky Attack")){
			type = Type.FLYING;
			category=this.PHYSICAL;
			PP = 5;
			Power = 140;
			Accuracy = 90;
		}
		else if(name.equalsIgnoreCase("Sky Uppercut")){
			type = Type.FIGHTING;
			category=this.PHYSICAL;
			PP = 15;
			Power = 85;
			Accuracy = 90;
		}
		else if(name.equalsIgnoreCase("Slack Off")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 10;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Slam")){
			type = Type.NORMAL;
			category = this.PHYSICAL;
			PP = 20;
			Power = 80;
			Accuracy = 75;
		}
		else if(name.equalsIgnoreCase("Slash")){
			type = Type.NORMAL;
			category=this.PHYSICAL;
			PP = 20;
			Power = 70;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Sleep Powder")){
			type = Type.GRASS;
			category = this.STATUS;
			PP = 15;
			Power = NONE;
			Accuracy = 75;
		}
		else if(name.equalsIgnoreCase("Sleep Talk")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 10;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Sludge")){
			type = Type.POISON;
			category=this.SPECIAL;
			PP = 20;
			Power = 65;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Sludge Bomb")){
			type = Type.POISON;
			category=this.SPECIAL;
			PP = 10;
			Power = 90;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Smelling Salts")){
			type = Type.NORMAL;
			category=this.PHYSICAL;
			PP = 10;
			Power = 60;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Smog")){
			type = Type.POISON;
			category=this.SPECIAL;
			PP = 20;
			Power = 20;
			Accuracy = 70;
		}
		else if(name.equalsIgnoreCase("Smokescreen")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 20;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Snatch")){
			type = Type.DARK;
			category=this.STATUS;
			PP = 10;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Snore")){
			type = Type.NORMAL;
			category=this.SPECIAL;
			PP = 15;
			Power = 40;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Soft-Boiled")){
			type = Type.NORMAL;
			PP = 10;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("SolarBeam")){
			type = Type.GRASS;
			category = this.SPECIAL;
			PP = 10;
			Power = 120;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Sonic Boom")){
			type = Type.NORMAL;
			category = this.SPECIAL;
			PP = 20;
			Power = NONE;
			Accuracy = 90;
		}
		else if(name.equalsIgnoreCase("Spark")){
			type = Type.ELECTRIC;
			category=this.PHYSICAL;
			PP = 20;
			Power = 65;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Spider Web")){
			type = Type.BUG;
			category=this.STATUS;
			PP = 10;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Spike Cannon")){
			type = Type.NORMAL;
			category=this.PHYSICAL;
			PP = 15;
			Power = 20;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Spikes")){
			type = Type.GROUND;
			category=this.STATUS;
			PP = 20;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Spit Up")){
			type = Type.NORMAL;
			category=this.SPECIAL;
			PP = 10;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Spite")){
			type = Type.GHOST;
			category=this.STATUS;
			PP = 10;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Splash")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 40;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Spore")){
			type = Type.GRASS;
			category = this.STATUS;
			PP = 15;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Steel Wing")){
			type = Type.STEEL;
			category=this.PHYSICAL;
			PP = 25;
			Power = 70;
			Accuracy = 90;
		}
		else if(name.equalsIgnoreCase("Stockpile")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 10;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Stomp")){
			type = Type.NORMAL;
			category = this.PHYSICAL;
			PP = 20;
			Power = 65;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Strength")){
			type = Type.NORMAL;
			category = this.PHYSICAL;
			PP = 15;
			Power = 80;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("String Shot")){
			type = Type.BUG;
			category = this.STATUS;
			PP = 40;
			Power = NONE;
			Accuracy = 95;
		}
		else if(name.equalsIgnoreCase("Struggle")){
			type = Type.NORMAL;
			category=this.PHYSICAL;
			PP = 10;
			Power = 50;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Stun Spore")){
			type = Type.GRASS;
			category = this.STATUS;
			PP = 30;
			Power = NONE;
			Accuracy = 75;
		}
		else if(name.equalsIgnoreCase("Submission")){
			type = Type.FIGHTING;
			category = this.PHYSICAL;
			PP = 25;
			Power = 80;
			Accuracy = 80;
		}
		else if(name.equalsIgnoreCase("Substitute")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 10;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Sunny Day")){
			type = Type.FIRE;
			category=this.STATUS;
			PP = 5;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Super Fang")){
			type = Type.NORMAL;
			category=this.PHYSICAL;
			PP = 10;
			Power = NONE;
			Accuracy = 90;
		}
		else if(name.equalsIgnoreCase("Superpower")){
			type = Type.FIGHTING;
			category=this.PHYSICAL;
			PP = 5;
			Power = 120;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Supersonic")){
			type = Type.NORMAL;
			category = this.STATUS;
			PP = 20;
			Power = NONE;
			Accuracy = 55;
		}
		else if(name.equalsIgnoreCase("Surf")){
			type = Type.WATER;
			category = this.SPECIAL;
			PP = 15;
			Power = 95;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Swagger")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 15;
			Power = NONE;
			Accuracy = 90;
		}
		else if(name.equalsIgnoreCase("Swallow")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 10;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Sweet Kiss")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 10;
			Power = NONE;
			Accuracy = 75;
		}
		else if(name.equalsIgnoreCase("Sweet Scent")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 20;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Swift")){
			type = Type.NORMAL;
			category=this.SPECIAL;
			PP = 20;
			Power = 60;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Swords Dance")){
			type = Type.NORMAL;
			category = this.STATUS;
			PP = 30;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Synthesis")){
			type = Type.GRASS;
			category=this.STATUS;
			PP = 5;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Tackle")){
			type = Type.NORMAL;
			category = this.PHYSICAL;
			PP = 35;
			Power = 35;
			Accuracy = 95;
		}
		else if(name.equalsIgnoreCase("Tail Glow")){
			type = Type.BUG;
			category=this.STATUS;
			PP = 20;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Tail Whip")){
			type = Type.NORMAL;
			category = this.STATUS;
			PP = 30;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Take Down")){
			type = Type.NORMAL;
			category = this.PHYSICAL;
			PP = 20;
			Power = 90;
			Accuracy = 85;
		}
		else if(name.equalsIgnoreCase("Taunt")){
			type = Type.DARK;
			category=this.STATUS;
			PP = 20;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Teeter Dance")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 20;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Teleport")){
			type = Type.PSYCHIC;
			category = this.STATUS;
			PP = 20;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Thief")){
			type = Type.DARK;
			category=this.PHYSICAL;
			PP = 10;
			Power = 40;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Thrash")){
			type = Type.NORMAL;
			category = this.PHYSICAL;
			PP = 20;
			Power = 90;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Thunder")){
			type = Type.ELECTRIC;
			category = this.PHYSICAL;
			PP = 10;
			Power = 120;
			Accuracy = 70;
		}
		else if(name.equalsIgnoreCase("Thunder Punch")){
			type = Type.ELECTRIC;
			category = this.PHYSICAL;
			PP = 15;
			Power = 75;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Thunder Shock")){
			type = Type.ELECTRIC;
			category = this.SPECIAL;
			PP = 30;
			Power = 40;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Thunder Wave")){
			type = Type.ELECTRIC;
			category = this.STATUS;
			PP = 20;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Thunderbolt")){
			type = Type.ELECTRIC;
			category = this.SPECIAL;
			PP = 15;
			Power = 95;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Tickle")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 20;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Torment")){
			type = Type.DARK;
			category=this.STATUS;
			PP = 15;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Toxic")){
			type = Type.POISON;
			category = this.STATUS;
			PP = 10;
			Power = NONE;
			Accuracy = 85;
		}
		else if(name.equalsIgnoreCase("Transform")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 10;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Tri Attack")){
			type = Type.NORMAL;
			category=this.SPECIAL;
			PP = 10;
			Power = 80;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Trick")){
			type = Type.PSYCHIC;
			category=this.STATUS;
			PP = 10;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Triple Kick")){
			type = Type.FIGHTING;
			category=this.PHYSICAL;
			PP = 10;
			Power = 10;
			Accuracy = 90;
		}
		else if(name.equalsIgnoreCase("Twineedle")){
			type = Type.BUG;
			category = this.PHYSICAL;
			PP = 20;
			Power = 25;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Twister")){
			type = Type.DRAGON;
			category=this.SPECIAL;
			PP = 20;
			Power = 40;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Uproar")){
			type = Type.NORMAL;
			category=this.SPECIAL;
			PP = 10;
			Power = 50;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("ViceGrip")){
			type = Type.NORMAL;
			category = this.PHYSICAL;
			PP = 30;
			Power = 55;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Vine Whip")){
			type = Type.GRASS;
			category = this.PHYSICAL;
			PP = 10;
			Power = 35;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Vital Throw")){
			type = Type.FIGHTING;
			category=this.PHYSICAL;
			PP = 10;
			Power = 70;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Volt Tackle")){
			type = Type.ELECTRIC;
			category=this.PHYSICAL;
			PP = 15;
			Power = 120;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Water Gun")){
			type = Type.WATER;
			category = this.SPECIAL;
			PP = 25;
			Power = 40;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Water Pulse")){
			type = Type.WATER;
			category=this.SPECIAL;
			PP = 20;
			Power = 60;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Water Sport")){
			type = Type.WATER;
			category=this.STATUS;
			PP = 15;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Water Spout")){
			type = Type.WATER;
			category=this.SPECIAL;
			PP = 5;
			Power = 150;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Waterfall")){
			type = Type.WATER;
			category=this.PHYSICAL;
			PP = 15;
			Power = 80;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Weather Ball")){
			type = Type.NORMAL;
			category=this.SPECIAL;
			PP = 10;
			Power = 50;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Whirlpool")){
			type = Type.WATER;
			category=this.SPECIAL;
			PP = 15;
			Power = 15;
			Accuracy = 70;
		}
		else if(name.equalsIgnoreCase("Whirlwind")){
			type = Type.NORMAL;
			category = this.STATUS;
			PP = 20;
			Power = NONE;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Will-O-Wisp")){
			type = Type.FIRE;
			category=this.STATUS;
			PP = 15;
			Power = NONE;
			Accuracy = 75;
		}
		else if(name.equalsIgnoreCase("Wing Attack")){
			type = Type.FLYING;
			category = this.PHYSICAL;
			PP = 35;
			Power = 60;
			Accuracy = 100;
		}
		else if(name.equalsIgnoreCase("Wish")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 10;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Withdraw")){
			type = Type.WATER;
			category=this.STATUS;
			PP = 40;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Wrap")){
			type = Type.NORMAL;
			category = this.PHYSICAL;
			PP = 20;
			Power = 15;
			Accuracy = 85;
		}
		else if(name.equalsIgnoreCase("Yawn")){
			type = Type.NORMAL;
			category=this.STATUS;
			PP = 10;
			Power = NONE;
			Accuracy = NONE;
		}
		else if(name.equalsIgnoreCase("Zap Cannon")){
			type = Type.ELECTRIC;
			category=this.SPECIAL;
			PP = 5;
			Power = 100;
			Accuracy = 50;
		}
		else {
			System.out.println(name+" DOES NOT EXIST");
		}
		currentPP=PP;
	}

	public int getCritChance(){
		int totalCrit = baseCrit;
		if(totalCrit==0){
			return 16;
		}
		else if(totalCrit==1){
			return 8;
		}
		else if(totalCrit==2){
			return 4;
		}
		else if(totalCrit==3){
			return 3;
		}
		else{
			return 2;
		}

	}
	public int getDamage(Pokemon user, Pokemon target){
		double atk = user.Attack;
		double def = target.Defense;
		if(category==this.SPECIAL){
			atk = user.SpAtk;
			def = target.SpDef;
		}
		double critical = 1;

		//check if the move is a critical hit
		if(GamePanel.randomNumber(1, getCritChance(), false)==1){
			critical = 1.5;
		}
		double rand = (double)GamePanel.randomNumber(85, 100, false)/100.0;
		double modifier = Type.getTypeDmgMultiplier(user.type, target.type, type)*critical*rand;
		double dmg = (((2.0*(double)user.level+10.0)/250.0)*(atk/def)*Power+2)*modifier;

		return (int)dmg;
	}
	public void testFunc(String txt){
		System.out.println(txt);
	}
	public void use(Pokemon user, Pokemon target){
		if(currentPP>0){
			if(user==GamePanel.currentBattle.playerPokemon){
				GamePanel.currentBattle.state=GamePanel.currentBattle.TEXT;
				GamePanel.currentBattle.messageID=GamePanel.currentBattle.PLAYERUSEDMOVE;
				//draw the take damage animation
				int x = GamePanel.currentBattle.position.x;
				int y = GamePanel.currentBattle.position.y;
				BufferedImage[][] frames = new BufferedImage[6][1];
				for(int i = 0; i<6;i++){
					if(i==1||i==3||i==5){//even numbered frame
						frames[i][0] = GamePanel.currentBattle.enemyPokemon.battleTexture[GamePanel.currentBattle.enemyPokemon.shiny][0];
					}
				}
				GamePanel.currentBattle.enemyTakeDamage=new Animation(x+GamePanel.currentBattle.width-208,y,0,frames,5);
			}
			else{//if the enemy pokemon used the move
				GamePanel.currentBattle.currentEnemyMove = this;
				GamePanel.currentBattle.state=GamePanel.currentBattle.TEXT;
				GamePanel.currentBattle.messageID=GamePanel.currentBattle.ENEMYUSEDMOVE;
				//draw the take damage animation
				int x = GamePanel.currentBattle.position.x;
				int y = GamePanel.currentBattle.position.y;
				BufferedImage[][] frames = new BufferedImage[6][1];
				for(int i = 0; i<6;i++){
					if(i==1||i==3||i==5){//even numbered frame
						frames[i][0] = GamePanel.currentBattle.playerPokemon.battleTexture[GamePanel.currentBattle.playerPokemon.shiny][1];
					}
				}

				GamePanel.currentBattle.playerTakeDamage=new Animation(x,y+GamePanel.currentBattle.height-96-160,0,frames,5);

			}
			//set the target's new HP
			target.newHP = target.currentHP-getDamage(user,target);
			if(target.newHP<0){
				target.newHP=0;
			}
			if(target.newHP>target.HP){
				target.newHP = target.HP;
			}

			currentPP--;

			double dmgMultiplier = Type.typeChart[type][target.type.x];
			if(target.type.y!=Type.NONE){
				dmgMultiplier = dmgMultiplier*Type.typeChart[type][target.type.y];
			}
			if(dmgMultiplier>1.0){
				GamePanel.currentBattle.messageID=GamePanel.currentBattle.SUPEREFFECTIVE;
			}
			else if(dmgMultiplier<1.0){
				GamePanel.currentBattle.messageID=GamePanel.currentBattle.NOTVERYEFFECTIVE;
			}
		}
		else{
			GamePanel.currentBattle.messageID = GamePanel.currentBattle.NOPP;
			GamePanel.currentBattle.state = GamePanel.currentBattle.TEXT;
		}
	}
	public void drawIcon(Graphics2D g, int x, int y){
		g.drawImage(GamePanel.textures.moveButtonIcons[0][1],x,y,null);
		if(GamePanel.currentBattle.currentPlayerMove==this){
			g.drawImage(GamePanel.textures.moveButtonIcons[0][0],x,y,null);
			g.drawImage(GamePanel.textures.types[0][type],GamePanel.currentBattle.position.x+GamePanel.currentBattle.width-64-28,GamePanel.currentBattle.position.y+GamePanel.currentBattle.height-75,null);
		}
		if(PP==TBD||Power==TBD||Accuracy==TBD){
			g.drawImage(GamePanel.textures.underDevelopmentIcon,x+9,y+9,null);
		}
	}
}
