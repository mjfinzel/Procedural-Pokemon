package Game;

public class Item {
	String name = "Unknown Item";
	String description = "No description defined.";
	int amount = 0;
	boolean isBall = false;
	double ballModifier = 0;
	public Item(String name){
		this.name = name;
		switch (name){
		case "Ability Capsule":
			description = "Allows a Pokémon with two Abilities to switch between these Abilities.";
			break;
		case "Ability Urge":
			description = "Activates the Ability of an ally Pokémon. Wonder Launcher only.";
			break;
		case "Abomasite":
			description = "Allows Abomasnow to Mega Evolve into Mega Abomasnow.";
			break;
		case "Absolite":
			description = "Allows Absol to Mega Evolve into Mega Absol.";
			break;
		case "Absorb Bulb":
			description = "";
			break;
		case "Acro Bike":
			description = "";
			break;
		case "Adamant Orb":
			description = "";
			break;
		case "Adventure Rules":
			description = "";
			break;
		case "Aerodactylite":
			description = "";
			break;
		case "Aggronite":
			description = "";
			break;
		case "Aguav Berry":
			description = "";
			break;
		case "Air Balloon":
			description = "";
			break;
		case "Air Mail":
			description = "";
			break;
		case "Alakazite":
			description = "";
			break;
		case "Altarianite":
			description = "";
			break;
		case "Amaze Mulch":
			description = "";
			break;
		case "Ampharosite":
			description = "";
			break;
		case "Amulet Coin":
			description = "";
			break;
		case "Antidote 	Antidote":
			description = "";
			break;
		case "Apicot Berry":
			description = "";
			break;
		case "Apricorn Box":
			description = "";
			break;
		case "Aqua Suit":
			description = "";
			break;
		case "Aspear Berry":
			description = "";
			break;
		case "Audinite":
			description = "";
			break;
		case "AuroraTicket":
			description = "";
			break;
		case "Awakening":
			description = "";
			break;
		case "Azure Flute":
			description = "";
			break;
		case "Babiri Berry":
			description = "";
			break;
		case "Balm Mushroom":
			description = "";
			break;
		case "Banettite":
			description = "";
			break;
		case "Basement Key 1":
			description = "";
			break;
		case "Basement Key 2":
			description = "";
			break;
		case "Bead Mail":
			description = "";
			break;
		case "Beedrillite":
			description = "";
			break;
		case "Belue Berry":
			description = "";
			break;
		case "Berry":
			description = "";
			break;
		case "Berry Juice":
			description = "";
			break;
		case "Berry Pots":
			description = "";
			break;
		case "Berry Pouch":
			description = "";
			break;
		case "Berserk Gene":
			description = "";
			break;
		case "Bicycle":
			description = "";
			break;
		case "Big Mushroom":
			description = "";
			break;
		case "Big Nugget":
			description = "";
			break;
		case "Big Pearl":
			description = "";
			break;
		case "Big Root":
			description = "";
			break;
		case "Bike Voucher":
			description = "";
			break;
		case "Binding Band":
			description = "";
			break;
		case "Bitter Berry":
			description = "";
			break;
		case "Black Belt":
			description = "";
			break;
		case "Black Glasses":
			description = "";
			break;
		case "Black Flute":
			description = "";
			break;
		case "Black Sludge":
			description = "";
			break;
		case "Blastoisinite":
			description = "";
			break;
		case "Blazikenite":
			description = "";
			break;
		case "Black Apricorn":
			description = "";
			break;
		case "Blue Apricorn":
			description = "";
			break;
		case "Blue Card":
			description = "";
			break;
		case "Blue Flute":
			description = "";
			break;
		case "Blue Orb":
			description = "";
			break;
		case "Blue Scarf":
			description = "";
			break;
		case "Blue Shard":
			description = "";
			break;
		case "Blu ID Badge":
			description = "";
			break;
		case "Bluk Berry":
			description = "";
			break;
		case "Bonsly Card":
			description = "";
			break;
		case "Bonsly Photo":
			description = "";
			break;
		case "Boost Mulch":
			description = "";
			break;
		case "Brick Piece":
			description = "";
			break;
		case "BridgeMail D":
			description = "";
			break;
		case "BridgeMail M":
			description = "";
			break;
		case "BridgeMail S":
			description = "";
			break;
		case "BridgeMail T":
			description = "";
			break;
		case "BridgeMail V":
			description = "";
			break;
		case "Bright Powder":
			description = "";
			break;
		case "Bug Gem":
			description = "";
			break;
		case "Burn Drive":
			description = "";
			break;
		case "Burn Heal":
			description = "";
			break;
		case "Burnt Berry":
			description = "";
			break;
		case "Calcium":
			description = "";
			break;
		case "Cameruptite":
			description = "";
			break;
		case "Carbos":
			description = "";
			break;
		case "Casteliacone":
			description = "";
			break;
		case "Cell Battery":
			description = "";
			break;
		case "Charcoal":
			description = "";
			break;
		case "Charizardite X":
			description = "";
			break;
		case "Charizardite Y":
			description = "";
			break;
		case "Charti Berry":
			description = "";
			break;
		case "Cheri Berry":
			description = "";
			break;
		case "Cherish Ball":
			description = "";
			break;
		case "Chesto Berry":
			description = "";
			break;
		case "Chilan Berry":
			description = "";
			break;
		case "Chill Drive":
			description = "";
			break;
		case "Chople Berry":
			description = "";
			break;
		case "Choice Band":
			description = "";
			break;
		case "Choice Scarf":
			description = "";
			break;
		case "Choice Specs":
			description = "";
			break;
		case "Claw Fossil":
			description = "";
			break;
		case "Cleanse Tag":
			description = "";
			break;
		case "Clever Wing":
			description = "";
			break;
		case "Coba Berry":
			description = "";
			break;
		case "Coin Case":
			description = "";
			break;
		case "Colbur Berry":
			description = "";
			break;
		case "Comet Shard":
			description = "";
			break;
		case "Cornn Berry":
			description = "";
			break;
		case "Cover Fossil":
			description = "";
			break;
		case "Custap Berry":
			description = "";
			break;
		case "Damp Mulch":
			description = "Causes the soil to dry out more slowly, but also causes the Berry plant to grow more slowly. ";
			break;
		case "Damp Rock":
			description = "";
			break;
		case "Dark Gem":
			description = "";
			break;
		case "Dawn Stone":
			description = "";
			break;
		case "Deep Sea Scale":
			description = "";
			break;
		case "Deep Sea Tooth":
			description = "";
			break;
		case "Destiny Knot":
			description = "";
			break;
		case "Devon Scope":
			description = "";
			break;
		case "Diancite":
			description = "";
			break;
		case "Dire Hit":
			description = "";
			break;
		case "Dive Ball":
			description = "";
			break;
		case "Dome Fossil":
			description = "";
			break;
		case "Douse Drive":
			description = "";
			break;
		case "Poke Ball":
			description = "A device for catching wild Pokémon. It's thrown like a ball at a Pokémon, comfortably encapsulating its target.";
			ballModifier = 1.0;
			break;
		case "Great Ball":
			description = "A good, high-performance Poké Ball that provides a higher Pokémon catch rate than a standard Poké Ball can.";
			ballModifier = 1.5;
			break;
		}
	}
	public void useOnOpponent(Pokemon opponent){//use on an opponent in battle
		System.out.println("Ball Modifier for \""+name+"\" is: "+ballModifier);
		if(name.equals("Poke Ball")){
			GamePanel.currentBattle.thrownPokeBallIndex = 0;
			GamePanel.currentBattle.pokeBallThrowAnim = new Animation(GamePanel.currentBattle.position.x+50,GamePanel.currentBattle.position.y+150,0,GamePanel.textures.pokeballThrowFrames,4);
		}
		else if(name.equals("Great Ball")){
			GamePanel.currentBattle.thrownPokeBallIndex = 1;
			GamePanel.currentBattle.pokeBallThrowAnim = new Animation(GamePanel.currentBattle.position.x+50,GamePanel.currentBattle.position.y+150,1,GamePanel.textures.pokeballThrowFrames,4);
		}
		else if(name.equals("Ultra Ball")){
			GamePanel.currentBattle.thrownPokeBallIndex = 3;
			GamePanel.currentBattle.pokeBallThrowAnim = new Animation(GamePanel.currentBattle.position.x+50,GamePanel.currentBattle.position.y+150,3,GamePanel.textures.pokeballThrowFrames,4);
		}
		double opponentStatusBonus = 1.0;
		if(opponent.status==GamePanel.statusConditions.SLEEP||opponent.status==GamePanel.statusConditions.FREEZE){
			opponentStatusBonus = 2.0;
		}
		else if(opponent.status==GamePanel.statusConditions.PARALYSIS||opponent.status==GamePanel.statusConditions.POISON||opponent.status==GamePanel.statusConditions.BURN){
			opponentStatusBonus = 1.5;
		}
		double modifiedCatchRate = (((3.0*(double)opponent.HP-2.0*(double)opponent.currentHP)*(double)opponent.catchRate*ballModifier)/(3.0*(double)opponent.HP))*opponentStatusBonus;
		System.out.println("modified catch rate is: "+modifiedCatchRate);
		if(modifiedCatchRate>=255){
			//pokemon was caught
			GamePanel.currentBattle.state = GamePanel.currentBattle.TEXT;
			GamePanel.currentBattle.messageID = GamePanel.currentBattle.CAUGHTPOKEMON;
		}
		else{
			double shakeProbability =  1048560.0/Math.sqrt(Math.sqrt(16711680/modifiedCatchRate));
			System.out.println("Shake probability is: "+shakeProbability);
			//determine how many times the ball shakes
			int shakes = 0;
			for(int i = 0;i<4;i++){
				if(GamePanel.randomNumber(0, 65535, false)>=shakeProbability){
					shakes++;
				}
			}
			GamePanel.currentBattle.numberOfBallShakes = shakes;
			GamePanel.currentBattle.state = GamePanel.currentBattle.TEXT;
			if(shakes==0)
				GamePanel.currentBattle.messageID = GamePanel.currentBattle.ZEROSHAKES;
			else if(shakes==1)
				GamePanel.currentBattle.messageID = GamePanel.currentBattle.ONESHAKE;
			else if(shakes==2)
				GamePanel.currentBattle.messageID = GamePanel.currentBattle.TWOSHAKES;
			else if(shakes==3)
				GamePanel.currentBattle.messageID = GamePanel.currentBattle.THREESHAKES;
			else if(shakes==4)
				GamePanel.currentBattle.messageID = GamePanel.currentBattle.CAUGHTPOKEMON;
		}
	}
}
