package Game;

import java.awt.image.BufferedImage;

public class Textures {
	int numPokemon = 264;
	public BufferedImage[][] tileset = Images.cut("/Textures/tiles/Ground.png",16,16);
	public BufferedImage[][] interior = Images.cut("/Textures/tiles/Interior.png",16,16);
	public BufferedImage[][][] battlers = new BufferedImage[numPokemon+1][2][2];
	public BufferedImage[][][] followers = new BufferedImage[numPokemon+1][2][2];
	public BufferedImage[][][] followersShiny = new BufferedImage[numPokemon+1][2][2];
	
	public BufferedImage[][] enemyBases = Images.cut("/Textures/EnemyBases.png",256,128);
	public BufferedImage[][] playerBases = Images.cut("/Textures/PlayerBases.png",512,64);
	public BufferedImage[][] battleBacks = Images.cut("/Textures/BattleBacks.png",512,288);
	
	public BufferedImage[] footprints  = new BufferedImage[numPokemon+1];
	public BufferedImage battleCommand = Images.load("/Textures/battleCommand.png");
	public BufferedImage battleBackgroundField= Images.load("/Textures/battlebgField.png");
	public BufferedImage enemyBaseGrassy = Images.load("/Textures/enemybaseGrassy.png");
	public BufferedImage playerBaseGrassy = Images.load("/Textures/playerbaseGrassy.png");
	public BufferedImage battlePlayerBox = Images.load("/Textures/battlePlayerBoxS.png");
	public BufferedImage battleFoeBox = Images.load("/Textures/battleFoeBoxS.png");
	public BufferedImage battleMessage = Images.load("/Textures/battleMessage.png");
	public BufferedImage battleFight = Images.load("/Textures/battleFight.png");
	public BufferedImage partyHP = Images.load("/Textures/partyHP.png");
	public BufferedImage[][] genderIcons = Images.cut("/Textures/GenderIcons.png",7,13);
	public BufferedImage[][] battleButtonIcons = Images.cut("/Textures/battleButtons.png",130,46);
	public BufferedImage[][] moveButtonIcons = Images.cut("/Textures/moveButtons.png",190,44);
	public BufferedImage underDevelopmentIcon = Images.load("/Textures/underDevelopmentIcon.png");
	public BufferedImage[][] types = Images.cut("/Textures/types.png",56,24);
	public BufferedImage[][] sparkles = Images.cut("/Textures/Sparkles.png",96,96);
	public BufferedImage[][] grassSteppedOn = Images.cut("/Textures/GrassSteppedOnAnim.png",16,16);
	public BufferedImage[][] portrait = Images.cut("/Textures/Portrait.png",88,88);
	public BufferedImage[][] stars = Images.cut("/Textures/IVstars.png",7,7);
	public BufferedImage[][] waterAnim = Images.cut("/Textures/ShallowWaterAnim.png",16,16);
	public BufferedImage[][] exitButton = Images.cut("/Textures/ExitButton.png",80,30);
	public BufferedImage[][] cancelButton = Images.cut("/Textures/CancelButton.png",60,30);
	public BufferedImage[][] itemBox = Images.cut("/Textures/ItemBox.png",150,24);
	public BufferedImage inventoryLabel = Images.load("/Textures/InventoryLabel.png");
	public BufferedImage pokedexRegistrationComplete = Images.load("/Textures/PokedexRegistrationComplete.png");
	
	public BufferedImage[][] mapIcons = Images.cut("/Textures/MapIcons.png",32,32);
	
	public BufferedImage[][][] npcImages = new BufferedImage[10][4][4];
	public BufferedImage[][] pokeballThrowFrames = Images.cut("/Textures/PokeBallSpin.png",24,24);
	public BufferedImage[][] pokeballsOpen = Images.cut("/Textures/OpenPokeBalls.png",32,32);
	public BufferedImage[][] pokeBallShakeFrames = Images.cut("/Textures/PokeBallShake.png",24,24);
	public BufferedImage[][] advanceIndicatorFrames = Images.cut("/Textures/AdvanceIndicator.png",20,20);
	
	
	public Textures(){
		npcImages[0] = Images.cut("/Textures/HGSS_000.png",32,32);
		npcImages[1] = Images.cut("/Textures/HGSS_001.png",32,32);
		npcImages[2] = Images.cut("/Textures/HGSS_110.png",32,32);
		npcImages[3] = Images.cut("/Textures/HGSS_118.png",32,32);
		npcImages[4] = Images.cut("/Textures/HGSS_119.png",32,32);
		npcImages[5] = Images.cut("/Textures/HGSS_120.png",32,32);
		npcImages[6] = Images.cut("/Textures/HGSS_122.png",32,32);
		npcImages[7] = Images.cut("/Textures/HGSS_123.png",32,32);
		npcImages[8] = Images.cut("/Textures/HGSS_126.png",32,32);
		npcImages[9] = Images.cut("/Textures/HGSS_130.png",32,32);
		for(int i = 1; i<=numPokemon;i++){
			String extraZeros = "";
			if(i<10){
				extraZeros = "00";
			}
			else if(i<100){
				extraZeros = "0";
			}
			//System.out.println("Loading: "+"/BattleTextures/"+extraZeros+i+".png");
			battlers[i]=Images.cut("/BattleTextures/"+extraZeros+i+".png", 160, 320);
			footprints[i]=Images.load("/Footprints/footprint"+extraZeros+i+".png");
			followers[i] = Images.cut("/NPC/"+extraZeros+i+".png", 64, 64);
			followersShiny[i] = Images.cut("/NPC/"+extraZeros+i+"s.png", 64, 64);
		}
	}
}
