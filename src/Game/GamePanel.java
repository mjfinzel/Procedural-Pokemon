package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.plaf.synth.SynthSeparatorUI;

public class GamePanel extends JPanel{

	int mapUpdates = 0;
	private static final long serialVersionUID = 496501089018037548L;

	//textures
	static BufferedImage[][] ground = Images.cut("/Textures/Ground.png", 16, 16);
	static BufferedImage[][] cliff = Images.cut("/Textures/Cliff.png", 16, 16);
	static BufferedImage[][] martAndCenter = Images.cut("/Textures/MartAndCenter.png", 16, 16);
	public static BufferedImage[][] playerImage = Images.cut("/Textures/player.png", 32, 32);
	public static Textures textures = new Textures();

	public static Status statusConditions = new Status();
	public static NameList names = new NameList();
	public static DialogBox dialogBox = new DialogBox((ApplicationUI.windowWidth/2)-(512/2),(ApplicationUI.windowHeight/2)-(384/2));

	public static boolean inInventory = false;
	public static boolean paused = false;
	public static int biome = 0;

	public static boolean showMap = false;
	public static boolean godmode = false;
	public static boolean loading = false;
	public static boolean drawMap = false;

	public static boolean switchingPokemon = false;

	public static boolean inBattle = false;

	public static int HM_Strength_Level = 42;
	public static int HM_Surf_Level = 21;
	public static int HM_Fly_Level = 32;

	static Random worldGenRandom;
	static Random random;
	static Random shinyCheck;

	public static int totalWildEncounters = 0;

	public static int worldWidth = 14;//14
	public static int worldHeight = 14;//14
	public static int areaWidth = 100;
	public static int areaHeight = 100;

	public static Area currentArea = null;
	public static int totalRoadForks = 0;

	public static int tileSize = 16;

	public static Area[][] areas = new Area[worldWidth][worldHeight];
	public static LevelTile[][] tileMap = new LevelTile[worldWidth*areaWidth][worldHeight*areaHeight];
	public static ArrayList<Area> areasList = new ArrayList<Area>();

	public static Button[] menu = new Button[8];

	public static int currentButton = 0;
	public static int pokemonScreenSelectorPosition = 0;
	public static int pokemonScreenSwitchSelectorPosition = 0;
	public static Battle currentBattle;
	public static int selectedOptionForSelectedPokemon = 0;

	public static Player player;
	public static Dialog dialog = new Dialog();

	public static boolean showVisualMap = false;
	public static boolean showMenu = false;
	public static boolean showPokemon = false;
	public static boolean pokemonIsSelected = false;
	public static boolean showInventory = false;
	public static boolean showPokedexRegistrationComplete = false;

	public static ArrayList<Pokemon> possiblePokemon = new ArrayList<Pokemon>();
	public static ArrayList<Point> shortGrassPokemon = new ArrayList<Point>();//all pokemon which can be found in short grass
	public static ArrayList<Point> tallGrassPokemon = new ArrayList<Point>();//all pokemon which can be found in tall grass
	public static ArrayList<Point> cavePokemon = new ArrayList<Point>();//all pokemon which can be found in a cave
	public static ArrayList<Point> surfPokemon = new ArrayList<Point>();//all pokemon which can be found while surfing
	public static ArrayList<Point> oldRodPokemon = new ArrayList<Point>();//all pokemon which can be found with the old rod
	public static ArrayList<Point> goodRodPokemon = new ArrayList<Point>();//all pokemon which can be found with the good rod
	public static ArrayList<Point> superRodPokemon = new ArrayList<Point>();//all pokemon which can be found with the super rod
	public static ArrayList<Point> graveyardPokemon = new ArrayList<Point>();

	public static int [] obstacleOrder = new int[8];//The order that the player will gain access to each HM

	//the numbers that represent each individual hm
	public static int CUT = 1;
	public static int FLY = 2;
	public static int SURF = 3;
	public static int STRENGTH = 4;
	public static int FLASH = 5;
	public static int ROCKSMASH = 6;
	public static int WATERFALL = 7;
	public static int DIVE = 8;

	BufferedImage mapImage = new BufferedImage(areaWidth*worldWidth*4, areaHeight*worldHeight*4, BufferedImage.TYPE_INT_ARGB);

	Sound sound;
	public static Point mapPosition = new Point(0,0);
	public GamePanel(){
		//populate the options menu
		Point menuPos = new Point((ApplicationUI.windowWidth/2)+100,(ApplicationUI.windowHeight/2)-149);
		menu[0] = new Button(menuPos.x,menuPos.y,"Pokedex");
		menu[1] = new Button(menuPos.x,menuPos.y+44,"Pokemon");
		menu[2] = new Button(menuPos.x,menuPos.y+88,"Bag");
		menu[3] = new Button(menuPos.x,menuPos.y+132,"Pokegear");
		menu[4] = new Button(menuPos.x,menuPos.y+176,"PlayerName");
		menu[5] = new Button(menuPos.x,menuPos.y+220,"Save");
		menu[6] = new Button(menuPos.x,menuPos.y+264,"Options");
		menu[7] = new Button(menuPos.x,menuPos.y+308,"Exit");

		worldGenRandom = new Random(32);
		random = new Random(22);
		shinyCheck = new Random(777);
		Point startPosition = new Point(worldWidth/2,worldWidth/2);

		//determine the order in which HMs will be found (needed for terrain generation)
		determineHMOrder();

		//generate 4 main paths from the starting town
		for(int i = 0; i<4;i++){
			boolean succeeded = generateWorldLayout(startPosition,startPosition,startPosition,5);
			int tries = 0;
			while(!succeeded&&tries<200){
				tries++;
				//System.out.println(tries);
				ArrayList<Area> mainPathAreas = getListOfAreas();
				Point newStartPos = areas[startPosition.x][startPosition.y].connectedAreas.get(randomNumber(0,areas[startPosition.x][startPosition.y].connectedAreas.size()-1,true)).position;
				succeeded = generateWorldLayout(startPosition,newStartPos,newStartPos,5);
			}
			totalRoadForks++;
		}

		//generate new connected areas until one is generated with a level of 50 or more and there are atleast 5 forks in the roads
		int tries = 0;
		while((getHighestLevelArea().avgLevel<50||totalRoadForks<5)&&tries<500){
			ArrayList<Area> mainPathAreas = getListOfAreas();
			startPosition = mainPathAreas.get(randomNumber(0,mainPathAreas.size()-1,true)).position;
			if(areas[startPosition.x][startPosition.y].avgLevel<50){
				boolean succeeded = generateWorldLayout(startPosition,startPosition,startPosition,randomNumber(1,4,true));//(1,4)
				if(succeeded){
					totalRoadForks++;
				}
			}
			tries++;
		}
		if(tries==500){
			System.out.println("Failed to generate the world properly.");
			System.out.println("Failed to generate the world properly.");
			System.out.println("Failed to generate the world properly.");
		}

		//connect 30% of areas that have similar levels and are adjacent to each other
		connectSimilarLevelAreas(30);

		//fill the world with one type of tile
		initializeTileMap(5,2);

		//create all of the areas of the world
		for(int i = 0; i<worldWidth; i++){
			for(int j = 0; j<worldHeight; j++){
				if(areas[i][j]==null){
					areas[i][j] = new Area(new Point(i,j), "outside");
				}
			}
		}
		generateAreas();

		//update tiles so they match up with their adjacent tiles
		updateTiles();

		//setCurrentArea(areasList.get(3));
		generateMap();
		fillAreaEncounterLists();

		//create the player
		initializePlayer();

		for(int i = 1; i<=264;i++){
			possiblePokemon.add(new Pokemon(i,1));
		}

		for(int i = 0; i<areasList.size();i++){
			areasList.get(i).getPossibleEncounters();
		}
		random = new Random();

		updateTiles();
	}
	public void initializePlayer() {
		player = new Player("player");
		player.xpos = (currentArea.position.x*areaWidth*tileSize)+((areaWidth/2)*tileSize);
		player.ypos = (currentArea.position.y*areaHeight*tileSize)+((areaHeight/2)*tileSize);
		player.xposDecimal=player.xpos;
		player.yposDecimal=player.ypos;
	}
	public void generateAreas() {
		//determine biomes for all the areas
		for(int i = 0; i<worldWidth; i++){
			for(int j = 0; j<worldHeight; j++){
				if(areas[i][j].isTown){
					int roll = GamePanel.randomNumber(1, 8, true);
					//87.5% chance to be normal
					areas[i][j].biome = 0;
					//12.5% chance to be snowy
					if(roll == 1){
						areas[i][j].biome = 1;
					}


				}
				else{
					int roll = GamePanel.randomNumber(1, 100, true);
					//normal (70%)
					if(roll <= 70){
						areas[i][j].biome = 0;
					}
					//snow (10%)
					else if(roll > 70 && roll <=80){
						areas[i][j].biome = 1;
					}
					//desert (6%)
					else if(roll>80 && roll <= 86){
						areas[i][j].biome = 2;
					}
					//ash (6%)
					else if(roll>86 && roll <= 92){
						areas[i][j].biome = 3;
					}
					//graveyard (6%)
					else if(roll>92 && roll <= 98){
						areas[i][j].biome = 4;
					}
					//??? (2%)
					else{
						areas[i][j].biome = 5;
					}

				}
				for(int x = 0; x<areaWidth; x++){
					for(int y= 0; y<areaHeight;y++){
						GamePanel.tileMap[(i*areaWidth)+x][(j*areaHeight)+y].biome = areas[i][j].biome;
					}
				}
			}
		}
		//generate all the areas
		for(int i = 0; i<worldWidth; i++){
			for(int j = 0; j<worldHeight; j++){
				if(areas[i][j].avgLevel>0){
					if(areas[i][j].isTown){
						areas[i][j].generateAsTown();
					}
					else{
						areas[i][j].generateAsRoute();
					}
				}
				else{
					areas[i][j].generateAsOcean();
				}
			}
		}
	}
	public static void fillAreaEncounterLists() {
		//pokemon that can be found in short grass
		shortGrassPokemon.add(new Point(1, 1)); //Bulbasaur
		shortGrassPokemon.add(new Point(4, 1)); //Charmander
		shortGrassPokemon.add(new Point(7, 1)); //Squirtle
		shortGrassPokemon.add(new Point(10, 1)); //Caterpie
		shortGrassPokemon.add(new Point(13, 1)); //Weedle
		shortGrassPokemon.add(new Point(16, 1)); //Pidgey
		shortGrassPokemon.add(new Point(19, 1)); //Rattata
		shortGrassPokemon.add(new Point(21, 1)); //Spearow
		shortGrassPokemon.add(new Point(23, 1)); //Ekans
		shortGrassPokemon.add(new Point(25, 1)); //Pikachu
		shortGrassPokemon.add(new Point(27, 1)); //Sandshrew
		shortGrassPokemon.add(new Point(29, 1)); //Nidoran
		shortGrassPokemon.add(new Point(32, 1)); //Nidoran
		shortGrassPokemon.add(new Point(37, 1)); //Vulpix
		shortGrassPokemon.add(new Point(39, 1)); //Jigglypuff
		shortGrassPokemon.add(new Point(43, 1)); //Oddish
		shortGrassPokemon.add(new Point(52, 1)); //Meowth
		shortGrassPokemon.add(new Point(54, 1)); //Psyduck
		shortGrassPokemon.add(new Point(56, 1)); //Mankey
		shortGrassPokemon.add(new Point(58, 1)); //Growlithe
		shortGrassPokemon.add(new Point(60, 1)); //Poliwag
		shortGrassPokemon.add(new Point(63, 1)); //Abra
		shortGrassPokemon.add(new Point(66, 1)); //Machop
		shortGrassPokemon.add(new Point(69, 1)); //Bellsprout
		shortGrassPokemon.add(new Point(77, 1)); //Ponyta
		shortGrassPokemon.add(new Point(79, 1)); //Slowpoke
		shortGrassPokemon.add(new Point(81, 1)); //Magnemite
		shortGrassPokemon.add(new Point(83, 1)); //Farfetch'd
		shortGrassPokemon.add(new Point(84, 1)); //Doduo
		shortGrassPokemon.add(new Point(88, 1)); //Grimer
		shortGrassPokemon.add(new Point(92, 1)); //Ghastly
		shortGrassPokemon.add(new Point(96, 1)); //Drowzee
		shortGrassPokemon.add(new Point(101, 1)); //Electrode
		shortGrassPokemon.add(new Point(108, 1)); //Lickitung
		shortGrassPokemon.add(new Point(109, 1)); //Koffing
		shortGrassPokemon.add(new Point(113, 1)); //Chansey
		shortGrassPokemon.add(new Point(125, 1)); //Electabuzz
		shortGrassPokemon.add(new Point(132, 1)); //Ditto
		shortGrassPokemon.add(new Point(133, 1)); //Eevee

		shortGrassPokemon.add(new Point(152, 1)); //Chikorita
		shortGrassPokemon.add(new Point(155, 1)); //Cyndaquil
		shortGrassPokemon.add(new Point(158, 1)); //Totodile
		shortGrassPokemon.add(new Point(161, 1)); //Sentret
		shortGrassPokemon.add(new Point(163, 1)); //Hoothoot
		shortGrassPokemon.add(new Point(177, 1)); //Natu
		shortGrassPokemon.add(new Point(179, 1)); //Mareep
		shortGrassPokemon.add(new Point(183, 1)); //Marill
		shortGrassPokemon.add(new Point(193, 1)); //Yanma
		shortGrassPokemon.add(new Point(198, 1)); //Murkrow
		shortGrassPokemon.add(new Point(203, 1)); //Girafarig
		shortGrassPokemon.add(new Point(204, 1)); //Pineco
		shortGrassPokemon.add(new Point(209, 1)); //Snubbull
		shortGrassPokemon.add(new Point(228, 1)); //Houndour
		shortGrassPokemon.add(new Point(231, 1)); //Phanpy
		shortGrassPokemon.add(new Point(239, 1)); //Elekid

		shortGrassPokemon.add(new Point(252, 1)); //Treecko
		shortGrassPokemon.add(new Point(255, 1)); //Torchic
		shortGrassPokemon.add(new Point(258, 1)); //Mudkip
		shortGrassPokemon.add(new Point(261, 1)); //Poochyena
		shortGrassPokemon.add(new Point(263, 1)); //Zigzagoon

		//pokemon that can be found in tall grass
		tallGrassPokemon.add(new Point(1, 1)); //Bulbasaur
		tallGrassPokemon.add(new Point(4, 1)); //Charmander
		tallGrassPokemon.add(new Point(7, 1)); //Squirtle
		tallGrassPokemon.add(new Point(152, 1)); //Chikorita
		tallGrassPokemon.add(new Point(155, 1)); //Cyndaquil
		tallGrassPokemon.add(new Point(158, 1)); //Totodile
		tallGrassPokemon.add(new Point(252, 1)); //Treecko
		tallGrassPokemon.add(new Point(255, 1)); //Torchic
		tallGrassPokemon.add(new Point(258, 1)); //Mudkip
		tallGrassPokemon.add(new Point(21, 1)); //Spearow
		tallGrassPokemon.add(new Point(23, 1)); //Ekans
		tallGrassPokemon.add(new Point(25, 1)); //Pikachu
		tallGrassPokemon.add(new Point(37, 1)); //Vulpix
		tallGrassPokemon.add(new Point(43, 1)); //Oddish
		tallGrassPokemon.add(new Point(56, 1)); //Mankey
		tallGrassPokemon.add(new Point(84, 1)); //Doduo
		tallGrassPokemon.add(new Point(101, 1)); //Electrode
		tallGrassPokemon.add(new Point(102, 1)); //Exeggcute
		tallGrassPokemon.add(new Point(108, 1)); //Lickitung
		tallGrassPokemon.add(new Point(113, 1)); //Chansey
		tallGrassPokemon.add(new Point(114, 1)); //Tangela
		tallGrassPokemon.add(new Point(115, 1)); //Kangaskhan
		tallGrassPokemon.add(new Point(122, 1)); //Mr. Mime
		tallGrassPokemon.add(new Point(123, 1)); //Scyther
		tallGrassPokemon.add(new Point(125, 1)); //Electabuzz
		tallGrassPokemon.add(new Point(127, 1)); //Pinsir
		tallGrassPokemon.add(new Point(128, 1)); //Tauros

		tallGrassPokemon.add(new Point(165, 1)); //Ledyba
		tallGrassPokemon.add(new Point(167, 1)); //Spinarak
		tallGrassPokemon.add(new Point(187, 1)); //Hoppip
		tallGrassPokemon.add(new Point(191, 1)); //Sunkern
		tallGrassPokemon.add(new Point(193, 1)); //Yanma
		tallGrassPokemon.add(new Point(190, 1)); //Aipom
		tallGrassPokemon.add(new Point(203, 1)); //Girafarig
		tallGrassPokemon.add(new Point(214, 1)); //Heracross
		tallGrassPokemon.add(new Point(227, 1)); //Skarmory
		tallGrassPokemon.add(new Point(234, 1)); //Stantler
		tallGrassPokemon.add(new Point(241, 1)); //Miltank

		//pokemon that can be found in caves
		cavePokemon.add(new Point(19, 1)); //Rattata
		cavePokemon.add(new Point(25, 1)); //Pikachu
		cavePokemon.add(new Point(27, 1)); //Sandshrew
		cavePokemon.add(new Point(41, 1)); //Zubat
		cavePokemon.add(new Point(46, 1)); //Paras
		cavePokemon.add(new Point(48, 1)); //Venonat
		cavePokemon.add(new Point(50, 1)); //Diglett
		cavePokemon.add(new Point(66, 1)); //Machop
		cavePokemon.add(new Point(74, 1)); //Geodude
		cavePokemon.add(new Point(79, 1)); //Slowpoke
		cavePokemon.add(new Point(81, 1)); //Magnemite
		cavePokemon.add(new Point(88, 1)); //Grimer
		cavePokemon.add(new Point(92, 1)); //Ghastly
		cavePokemon.add(new Point(95, 1)); //Onix
		cavePokemon.add(new Point(100, 1)); //Voltorb
		cavePokemon.add(new Point(104, 1)); //Cubone
		cavePokemon.add(new Point(109, 1)); //Koffing
		cavePokemon.add(new Point(111, 1)); //Rhyhorn
		cavePokemon.add(new Point(124, 1)); //Jynx
		cavePokemon.add(new Point(126, 1)); //Magmar
		cavePokemon.add(new Point(127, 1)); //Pinsir
		cavePokemon.add(new Point(202, 1)); //Wobbuffet
		cavePokemon.add(new Point(206, 1)); //Dunsparce
		cavePokemon.add(new Point(207, 1)); //Gligar
		cavePokemon.add(new Point(213, 1)); //Shuckle
		cavePokemon.add(new Point(215, 1)); //Sneasel
		cavePokemon.add(new Point(216, 1)); //Teddiursa
		cavePokemon.add(new Point(218, 1)); //Slugma
		cavePokemon.add(new Point(220, 1)); //Swinub
		cavePokemon.add(new Point(225, 1)); //Delibird
		cavePokemon.add(new Point(231, 1)); //Phanpy
		cavePokemon.add(new Point(246, 1)); //Larvitar



		cavePokemon.add(new Point(200, 1)); //Misdreavus

		//pokemon that can be found while surfing
		surfPokemon.add(new Point(54, 1)); //Psyduck
		surfPokemon.add(new Point(60, 1)); //Poliwag
		surfPokemon.add(new Point(72, 1)); //Tentacool
		surfPokemon.add(new Point(86, 1)); //Seel
		surfPokemon.add(new Point(98, 1)); //Krabby
		surfPokemon.add(new Point(116, 1)); //Horsea
		surfPokemon.add(new Point(118, 1)); //Goldeen
		surfPokemon.add(new Point(120, 1)); //Staryu
		surfPokemon.add(new Point(129, 1)); //Magikarp
		surfPokemon.add(new Point(131, 1)); //Lapras

		surfPokemon.add(new Point(194, 1)); //Wooper
		surfPokemon.add(new Point(226, 1)); //Mantine

		//pokemon that can be found with the old rod
		oldRodPokemon.add(new Point(129, 1)); //Magikarp

		//pokemon that can be found with the good rod
		goodRodPokemon.add(new Point(90, 1)); //Shellder
		goodRodPokemon.add(new Point(98, 1)); //Krabby
		goodRodPokemon.add(new Point(116, 1)); //Horsea
		goodRodPokemon.add(new Point(118, 1)); //Goldeen
		goodRodPokemon.add(new Point(120, 1)); //Staryu

		goodRodPokemon.add(new Point(170, 1)); //Chinchou
		goodRodPokemon.add(new Point(211, 1)); //Qwilfish
		goodRodPokemon.add(new Point(222, 1)); //Corsola
		goodRodPokemon.add(new Point(223, 1)); //Remoraid

		//pokemon that can be found with the super rod
		superRodPokemon.add(new Point(90, 1)); //Shellder
		superRodPokemon.add(new Point(98, 1)); //Krabby
		superRodPokemon.add(new Point(116, 1)); //Horsea
		superRodPokemon.add(new Point(118, 1)); //Goldeen
		superRodPokemon.add(new Point(120, 1)); //Staryu
		superRodPokemon.add(new Point(147, 1)); //Dratini

		superRodPokemon.add(new Point(170, 1)); //Chinchou
		superRodPokemon.add(new Point(211, 1)); //Qwilfish
		superRodPokemon.add(new Point(222, 1)); //Corsola
		superRodPokemon.add(new Point(223, 1)); //Remoraid

		//pokemon that can be found in a graveyard
		graveyardPokemon.add(new Point(13, 1)); //Weedle
		graveyardPokemon.add(new Point(19, 1)); //Rattata
		graveyardPokemon.add(new Point(23, 1)); //Ekans
		graveyardPokemon.add(new Point(41, 1)); //Zubat
		graveyardPokemon.add(new Point(48, 1)); //Venonat
		graveyardPokemon.add(new Point(50, 1)); //Diglett
		graveyardPokemon.add(new Point(92, 1)); //Ghastly
		graveyardPokemon.add(new Point(104, 1)); //Cubone
		graveyardPokemon.add(new Point(109, 1)); //Koffing
		graveyardPokemon.add(new Point(114, 1)); //Tangela
		graveyardPokemon.add(new Point(163, 1)); //Hoothoot
		graveyardPokemon.add(new Point(167, 1)); //Spinarak
		graveyardPokemon.add(new Point(198, 1)); //Murkrow
		graveyardPokemon.add(new Point(200, 1)); //Misdreavus
		graveyardPokemon.add(new Point(207, 1)); //Gligar
		graveyardPokemon.add(new Point(228, 1)); //Houndour
		graveyardPokemon.add(new Point(261, 1)); //Poochyena
	}
	public static void determineHMOrder(){

		Integer [] initialHMs = {CUT,SURF,STRENGTH,FLASH,ROCKSMASH};
		ArrayList<Integer> possibleHMs = new ArrayList<Integer>();
		possibleHMs.add(CUT);
		possibleHMs.add(SURF);
		possibleHMs.add(STRENGTH);
		possibleHMs.add(FLASH);
		possibleHMs.add(ROCKSMASH);

		boolean surfAdded = false;

		//add the first 4 HMs
		for(int i = 0;i<8;i++){
			//The 4th HM found will always be fly
			if(i==3){
				obstacleOrder[i] = FLY;
			}
			else{
				obstacleOrder[i] = possibleHMs.remove(randomNumber(0,possibleHMs.size()-1,true));
				//if surf has been added then dive and waterfall should become available
				if(obstacleOrder[i]==SURF){
					possibleHMs.add(DIVE);

					possibleHMs.add(WATERFALL);
				}
			}

		}

	}
	public static void updateTiles(){
		for(int i = 1; i<(areaWidth*worldWidth) - 1;i++){
			for(int j = 1; j<(areaHeight*worldWidth) - 1;j++){
				GamePanel.tileMap[i][j].updateArt(areas[i/areaWidth][j/areaHeight]);
			}
		}
	}
	/*
	 *	Fill the entire world with the specified tile type at the specified elevation
	 */
	public void initializeTileMap(int tileType, int elevation){
		int width = areaWidth*worldWidth;
		int height = areaHeight*worldHeight;
		for(int i = 0; i<width;i++){
			for(int j = 0; j<height;j++){
				tileMap[i][j] = new LevelTile(i,j,tileType,elevation);
			}
		}
	}
	public static void printPossibleEncounters(){
		System.out.println("Pokemon that can be found in short grass: ");
		System.out.println("[Spoiler]");
		for(int i = 0; i<shortGrassPokemon.size();i++){
			System.out.println(new Pokemon(shortGrassPokemon.get(i).x,0).species);
		}
		System.out.println("[/Spoiler]");
		System.out.println("Pokemon that can be found in tall grass: ");
		System.out.println("[Spoiler]");
		for(int i = 0; i<tallGrassPokemon.size();i++){
			System.out.println(new Pokemon(tallGrassPokemon.get(i).x,0).species);
		}
		System.out.println("[/Spoiler]");
		System.out.println("Pokemon that can be found walking in caves: ");
		System.out.println("[Spoiler]");
		for(int i = 0; i<cavePokemon.size();i++){
			System.out.println(new Pokemon(cavePokemon.get(i).x,0).species);
		}
		System.out.println("[/Spoiler]");
		System.out.println("Pokemon that can be found while surfing: ");
		System.out.println("[Spoiler]");
		for(int i = 0; i<surfPokemon.size();i++){
			System.out.println(new Pokemon(surfPokemon.get(i).x,0).species);
		}
		System.out.println("[/Spoiler]");
		System.out.println("Pokemon that can be found with the old rod: ");
		System.out.println("[Spoiler]");
		for(int i = 0; i<oldRodPokemon.size();i++){
			System.out.println(new Pokemon(oldRodPokemon.get(i).x,0).species);
		}
		System.out.println("[/Spoiler]");
		System.out.println("Pokemon that can be found with the good rod: ");
		System.out.println("[Spoiler]");
		for(int i = 0; i<goodRodPokemon.size();i++){
			System.out.println(new Pokemon(goodRodPokemon.get(i).x,0).species);
		}
		System.out.println("[/Spoiler]");
		System.out.println("Pokemon that can be found with the super rod: ");
		System.out.println("[Spoiler]");
		for(int i = 0; i<superRodPokemon.size();i++){
			System.out.println(new Pokemon(superRodPokemon.get(i).x,0).species);
		}
		System.out.println("[/Spoiler]");
	}
	public static void setCurrentArea(Area newArea){
		currentArea = newArea;
		if(currentArea.isTown&&player!=null){
			player.mostRecentTown = currentArea;
		}
	}
	public void removeOutcrops(int currentType ,int newType, int currentElevation, int newElevation){
		//System.out.println("removing outcrops at elevation "+currentElevation+" and replacing them with elevation: " +newElevation);
		int totalOutcropsRemoved = -1;
		while(totalOutcropsRemoved!=0){
			totalOutcropsRemoved = 0;
			for(int x = 1; x<(areaWidth*worldWidth)-1;x++){
				for(int y = 1; y<(areaHeight*worldHeight)-1;y++){
					int totalTopBottom = 0;
					int totalLeftRight = 0;

					boolean topLeft = false;
					boolean topRight = false;
					boolean bottomLeft = false;
					boolean bottomRight = false;

					if(tileMap[x][y].elevation==currentElevation&&tileMap[x][y].id==currentType){
						//check all tiles adjacent to this tile
						for(int i = x-1; i<=x+1; i++){
							for(int j = y-1; j<=y+1;j++){
								//exclude corners
								if(i==x||j==y){
									//exclude (x,y)
									if(!(i==x&&j==y)){
										//top or bottom
										if(i==x){
											if(tileMap[i][j].elevation==currentElevation&&tileMap[i][j].id==currentType)
												totalTopBottom++;
										}
										//left or right
										if(j==y){
											if(tileMap[i][j].elevation==currentElevation&&tileMap[i][j].id==currentType)
												totalLeftRight++;
										}
									}
								}
								else{
									//topleft
									if(i==x-1&&j==y-1){
										if(tileMap[i][j].elevation==currentElevation&&tileMap[i][j].id==currentType)
											topLeft = true;
									}
									//topright
									if(i==x+1&&j==y-1){
										if(tileMap[i][j].elevation==currentElevation&&tileMap[i][j].id==currentType)
											topRight = true;
									}
									//bottomleft
									if(i==x-1&&j==y+1){
										if(tileMap[i][j].elevation==currentElevation&&tileMap[i][j].id==currentType)
											bottomLeft = true;
									}
									//bottomright
									if(i==x+1&&j==y+1){
										if(tileMap[i][j].elevation==currentElevation&&tileMap[i][j].id==currentType)
											bottomRight = true;
									}
								}
							}
						}
						if(totalTopBottom<1||totalLeftRight<1||(topLeft&&!bottomLeft&&!topRight&&bottomRight&&totalTopBottom==2&&totalLeftRight==2)||(!topLeft&&bottomLeft&&topRight&&!bottomRight&&totalTopBottom==2&&totalLeftRight==2)){
							totalOutcropsRemoved++;
							tileMap[x][y].id=newType;
							tileMap[x][y].elevation=newElevation;
							tileMap[x][y].updateArt(areas[x/areaWidth][y/areaHeight]);
						}
					}
				}
			}
			//updateTiles();
		}
	}
	public ArrayList<LevelTile> getListOfTilesAtElevation(int elevation){
		ArrayList<LevelTile> tiles = new ArrayList<LevelTile>();
		for(int i = 0; i<areaWidth*worldWidth; i++){
			for(int j = 0; j<areaHeight*worldHeight; j++){
				if(tileMap[i][j].elevation==elevation){//&&tileMap[i][j].isEdge==false&&tileMap[i][j].tileType!="pine"){
					tiles.add(tileMap[i][j]);
				}
			}
		}
		return tiles;
	}
	public boolean addPlateau(ArrayList<LevelTile> tilesAtOriginalElevation, int desiredSize){
		ArrayList<LevelTile> tilesAdded = new ArrayList<LevelTile>();
		while(tilesAdded.size()<desiredSize){
			//if there is at least one tile in this plateau
			if(tilesAdded.size()>0){
				//pick a random tile from the list of tiles already added to this plateau
				int chosenTile = randomNumber(0,tilesAdded.size()-1,true);
				Point positionOfChosenTile = new Point(tilesAdded.get(chosenTile).xpos/tileSize,tilesAdded.get(chosenTile).ypos/tileSize);

				//get a list of all tiles that are adjacent to the chosen tile and have a 1 unit lower elevation than the chosen tile
				ArrayList<Point> availableNearbyLocations = getPositionsOfAdjacentTilesWithElevation(positionOfChosenTile,tilesAdded.get(chosenTile).elevation-1);

				//if there are available nearby locations(tiles that can be turned into plateau to expand this plateau)
				if(availableNearbyLocations.size()>0){

				}
				else{//there are no available adjacent locations

				}
				//repeat
			}
			else{//if not a single tile has been added to this plateau yet
				//if a possible spot to put a plateau exists at this elevation
				if(tilesAtOriginalElevation.size()>0){
					//pick a random tile at the original elevation
					int chosenTile = randomNumber(0,tilesAtOriginalElevation.size()-1,true);

					//change the elevation of the chosen tile to match the plateau's elevation
					tilesAtOriginalElevation.get(chosenTile).elevation+=1;

					//add the chosen tile to the list of tiles added
					tilesAdded.add(tilesAtOriginalElevation.get(chosenTile));

					//remove the chosen tile from the list of tiles at the original elevation
					tilesAtOriginalElevation.remove(chosenTile);
				}
				else{
					return false;//return false so we will know that a plateau was unable to be generated at this elevation
				}
			}
		}
		return true;
	}

	public ArrayList<Point> getNearbyLocationsAtElevation(Point position, int elevation){
		ArrayList<Point> positions = new ArrayList<Point>();
		for(int i = position.x-1;i<=position.x+1;i++){
			for(int j = position.y-1;j<=position.y+1;j++){
				if(i>=0&&i<=(areaWidth*worldWidth)-1&&j>=0&&j<=(areaHeight*worldHeight)-1){	
					if(GamePanel.tileMap[i][j].elevation==elevation){
						if(!(i==position.x&&j==position.y)){
							positions.add(new Point(i,j));
						}
					}
				}
			}
		}
		return positions;
	}
	public ArrayList<Point> getPositionsOfAdjacentTilesWithElevation(Point pos, int elevation){
		ArrayList<Point> positions = new ArrayList<Point>();

		for(int i = pos.x-1; i<=pos.x+1;i++){
			for(int j = pos.y-1; j<=pos.y+1;j++){
				//exclude corners
				if((i==pos.x||j==pos.y)&&!(i==pos.x&&j==pos.y)){
					//make sure not to go out of bounds of the tileMap
					if(i>=0&&i<=(areaWidth*worldWidth)-1&&j>=0&&j<=(areaHeight*worldHeight)-1){	
						//check if it's the same elevation and not an edge
						if(elevation==GamePanel.tileMap[i][j].elevation){//&&!GamePanel.tileMap[i][j].isEdge){
							positions.add(new Point(i,j));
						}
					}
				}
			}
		}

		return positions;
	}
	public static ArrayList<Area> getListOfAreas(){
		ArrayList<Area> areasList = new ArrayList<Area>();
		for(int i = 0; i<worldWidth;i++){
			for(int j = 0; j<worldHeight;j++){
				if(areas[i][j]!=null){
					areasList.add(areas[i][j]);
				}
			}
		}
		return areasList;
	}
	public static Area getHighestLevelArea(){
		ArrayList<Area> areaList = getListOfAreas();
		Area highestLevel = areaList.get(0);
		for(int i = 0; i<areaList.size();i++){
			if(areaList.get(i).avgLevel>highestLevel.avgLevel){
				highestLevel = areaList.get(i);
			}
		}
		return highestLevel;
	}
	public void connectSimilarLevelAreas(int percentChance){
		ArrayList<Area> areaList = getListOfAreas();
		for(int i = 0; i<areaList.size();i++){
			//for each adjacent area
			ArrayList<Area> adjacentAreas = getAdjacentAreas(areaList.get(i));
			for(int k = 0; k<adjacentAreas.size();k++){
				//if these two areas are within 3 avgLevel of each other
				if(Math.abs(areaList.get(i).avgLevel-adjacentAreas.get(k).avgLevel)<=3){
					//if these two areas are adjacent

					if(adjacentAreas.contains(adjacentAreas.get(k))){
						if(randomNumber(1,100,true)<=percentChance){
							if(!areaList.get(i).connectedAreas.contains(adjacentAreas.get(k))){
								areaList.get(i).connectedAreas.add(adjacentAreas.get(k));
							}
							if(!adjacentAreas.get(k).connectedAreas.contains(areaList.get(i))){
								adjacentAreas.get(k).connectedAreas.add(areaList.get(i));
							}
						}
					}

				}
			}
		}

	}
	public static ArrayList<Area> getAdjacentAreas(Area area){
		Point position = area.position;
		ArrayList<Area> adjacentAreas = new ArrayList<Area>();

		for(int i = position.x-1; i<=position.x+1;i++){
			for(int j = position.y-1; j<=position.y+1;j++){

				//exclude corners
				if(i==position.x||j==position.y){
					if(i>=1&&i<worldWidth-1&&j>=1&&j<worldHeight-1&&!(i==position.x&&j==position.y)){

						if(areas[i][j]!=null){
							adjacentAreas.add(areas[i][j]);
						}
					}
				}
			}
		}

		return adjacentAreas;
	}
	public static boolean generateWorldLayout(Point originalStartPos,Point startPosition, Point currentPosition, int totalAreas){

		ArrayList<Point> path = new ArrayList<Point>();
		path.add(currentPosition);
		if(areas[originalStartPos.x][originalStartPos.y]==null){
			areas[startPosition.x][startPosition.y] = new Area(startPosition, "outside");
			areas[startPosition.x][startPosition.y].avgLevel = 3;
			areas[startPosition.x][startPosition.y].isFirstTown=true;
			areas[startPosition.x][startPosition.y].isTown=true;
			setCurrentArea(areas[startPosition.x][startPosition.y]);
		}

		ArrayList<Point> nearbyUnoccupiedAreas;

		//determine level of the new area
		int startingLevel = 3;
		if(areas[startPosition.x][startPosition.y]!=null){
			startingLevel = areas[startPosition.x][startPosition.y].avgLevel;
		}

		int tries = 0;

		Area previous = null;


		for(int i = 0;path.size()<totalAreas;i++){
			nearbyUnoccupiedAreas = getNearbyPassablePoints(currentPosition,path);

			if(nearbyUnoccupiedAreas.size()>0&&path.size()<totalAreas){

				path.add(nearbyUnoccupiedAreas.get(randomNumber(0,nearbyUnoccupiedAreas.size()-1,true)));
				currentPosition = path.get(path.size()-1);
				//add a new area
				Area newArea = new Area(path.get(path.size()-1), "outside");
				newArea.position = new Point(path.get(path.size()-1).x,path.get(path.size()-1).y);

				previous = areas[path.get(path.size()-2).x][path.get(path.size()-2).y];
				if(previous!=null){

					if(!previous.connectedAreas.contains(newArea)){
						previous.connectedAreas.add(newArea);
					}
					if(!newArea.connectedAreas.contains(previous)){
						newArea.connectedAreas.add(previous);
					}
				}

				if((i==0||randomNumber(1,4,true)==1)){
					boolean hasAdjacentTown = false;
					for(int j = 0; j<newArea.connectedAreas.size();j++){
						if(newArea.connectedAreas.get(j).isTown){
							hasAdjacentTown = true;
						}
					}
					if(!hasAdjacentTown){
						newArea.isTown=true;

					}
				}
				newArea.avgLevel = startingLevel+((path.size()-1)*4)+randomNumber(-1,1,true);

				if(newArea.avgLevel<3){
					newArea.avgLevel=2;
				}
				areas[newArea.position.x][newArea.position.y] = newArea;

			}
			else if(nearbyUnoccupiedAreas.size()==0){
				tries++;
				//clear newly created areas
				for(int j = 0; j<path.size();j++){
					if(!(path.get(j).x==startPosition.x&&path.get(j).y==startPosition.y)){
						//clear connected areas that no longer exist
						//for each connected area
						for(int k = 0; k<areas[path.get(j).x][path.get(j).y].connectedAreas.size();k++){
							//for each of it's connected areas
							for(int g = 0; g<areas[path.get(j).x][path.get(j).y].connectedAreas.size();g++){
								if(areas[path.get(j).x][path.get(j).y].connectedAreas.get(g).connectedAreas.contains(areas[path.get(j).x][path.get(j).y])){
									areas[path.get(j).x][path.get(j).y].connectedAreas.get(g).connectedAreas.remove(areas[path.get(j).x][path.get(j).y]);
								}
							}

						}
						areas[path.get(j).x][path.get(j).y]=null;
					}
				}
				path.clear();
				path.add(startPosition);
				currentPosition = startPosition;
				i=0;
				previous = null;
			}
			if(tries>100){
				return false;
			}


		}

		//update the list of all areas in the game
		areasList = getListOfAreas();

		return true;
	}
	/*
	 * Get all the nearby areas of the world that aren't already occupied
	 */
	public static ArrayList<Point> getNearbyPassablePoints(Point position, ArrayList<Point> path){
		ArrayList<Point> positions = new ArrayList<Point>();

		for(int i = position.x-1; i<=position.x+1;i++){
			for(int j = position.y-1; j<=position.y+1;j++){

				//exclude corners
				if(i==position.x||j==position.y){
					//stay within bounds of the area
					if(i>=1&&i<worldWidth-2&&j>=1&&j<worldHeight-2&&!(i==position.x&&j==position.y)){

						boolean pathHadPoint = false;
						for(int k = 0; k<path.size();k++){
							if(path.get(k).x>=position.x-1&&path.get(k).x<=position.x+1){
								if(path.get(k).y>=position.y-1&&path.get(k).y<=position.y+1){
									if(path.get(k).x==position.x||path.get(k).y==position.y){
										if((path.get(k).x==i&&path.get(k).y==j)){
											pathHadPoint = true;
										}
									}
								}
							}
						}
						if(!pathHadPoint){
							if(areas[i][j]==null){
								positions.add(new Point(i,j));
							}
						}

					}
				}
			}
		}
		return positions;
	}

	public static int randomNumber(int min, int max, boolean forWorldGen){
		if(min>max){
			int temp = min;
			min = max;
			max = temp;
		}
		if(max==min){
			return max;
		}
		int randNum = min;
		if(forWorldGen){
			randNum = worldGenRandom.nextInt((max-min)+1) + min;
		}
		else{
			randNum = random.nextInt((max-min)+1)+min;
		}
		return randNum;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Draw((Graphics2D)g);
	}
	public void generateMap(){
		int scale = 4;
		for(int i = 0; i<worldWidth*areaWidth;i++){
			for(int j  = 0; j<worldHeight*areaHeight;j++){
				Graphics tmpG = mapImage.getGraphics();
				LevelTile cur = tileMap[i][j];

				tmpG.drawImage(textures.tileset[cur.artCrd[0].x+(cur.biome*scale)][cur.artCrd[0].y], i*scale, j*scale, scale, scale, null);
				if(tileMap[i][j].artCrd[1].x > 0)
					tmpG.drawImage(textures.tileset[cur.artCrd[1].x+(cur.biome*scale)][cur.artCrd[1].y], i*scale, j*scale, scale, scale, null);

				if(tileMap[i][j].id==3) {
					tmpG.setColor(new Color(96,160,216));
					tmpG.fillRect(i*scale, j*scale, scale, scale);
				}
			}
		}
	}
	public void drawMap(Graphics2D g) {
		//System.out.println("drawing the map");
		int x = (ApplicationUI.windowWidth/2)-(worldWidth*25);
		int y = (ApplicationUI.windowHeight/2)-(worldHeight*25);
		for(int i = 0; i<worldWidth;i++){
			for(int j = 0; j<worldHeight;j++){
				g.setColor(new Color(0,155,0));
				g.fillRect(x+(i*32), y+(j*32), 32, 32);
				if(areas[i][j]!=null){

					boolean hasTop = false;
					boolean hasBottom = false;
					boolean hasLeft = false;
					boolean hasRight = false;
					for(int k = 0; k<areas[i][j].connectedAreas.size();k++){
						if(areas[i][j].connectedAreas.get(k).position.y<j){
							hasTop= true;
						}
						else if(areas[i][j].connectedAreas.get(k).position.y>j){
							hasBottom= true;
						}
						else if(areas[i][j].connectedAreas.get(k).position.x<i){
							hasLeft= true;
						}
						else if(areas[i][j].connectedAreas.get(k).position.x>i){
							hasRight= true;
						}
					}

					if(hasTop&&hasBottom&&hasLeft&&hasRight){
						g.drawImage(textures.mapIcons[6][0],x+(i*32),y+(j*32),null);
					}
					else if(!hasTop&&hasBottom&&hasLeft&&hasRight){
						g.drawImage(textures.mapIcons[2][0],x+(i*32),y+(j*32),null);
					}
					else if(hasTop&&!hasBottom&&hasLeft&&hasRight){
						g.drawImage(textures.mapIcons[3][1],x+(i*32),y+(j*32),null);
					}
					else if(hasTop&&hasBottom&&!hasLeft&&hasRight){
						g.drawImage(textures.mapIcons[2][1],x+(i*32),y+(j*32),null);
					}
					else if(hasTop&&hasBottom&&hasLeft&&!hasRight){
						g.drawImage(textures.mapIcons[3][0],x+(i*32),y+(j*32),null);
					}
					else if(hasTop&&hasBottom&&!hasLeft&&!hasRight){
						g.drawImage(textures.mapIcons[4][1],x+(i*32),y+(j*32),null);
					}
					else if(!hasTop&&!hasBottom&&hasLeft&&hasRight){
						g.drawImage(textures.mapIcons[4][0],x+(i*32),y+(j*32),null);
					}

					else if(hasTop&&!hasBottom&&hasLeft&&!hasRight){
						g.drawImage(textures.mapIcons[1][1],x+(i*32),y+(j*32),null);
					}
					else if(hasTop&&!hasBottom&&!hasLeft&&hasRight){
						g.drawImage(textures.mapIcons[0][1],x+(i*32),y+(j*32),null);
					}
					else if(!hasTop&&hasBottom&&hasLeft&&!hasRight){
						g.drawImage(textures.mapIcons[1][0],x+(i*32),y+(j*32),null);
					}
					else if(!hasTop&&hasBottom&&!hasLeft&&hasRight){
						g.drawImage(textures.mapIcons[0][0],x+(i*32),y+(j*32),null);
					}

					else if(!hasTop&&!hasBottom&&!hasLeft&&hasRight){
						g.drawImage(textures.mapIcons[7][0],x+(i*32),y+(j*32),null);
					}
					else if(!hasTop&&!hasBottom&&hasLeft&&!hasRight){
						g.drawImage(textures.mapIcons[8][1],x+(i*32),y+(j*32),null);
					}
					else if(hasTop&&!hasBottom&&!hasLeft&&!hasRight){
						g.drawImage(textures.mapIcons[7][1],x+(i*32),y+(j*32),null);
					}
					else if(!hasTop&&hasBottom&&!hasLeft&&!hasRight){
						g.drawImage(textures.mapIcons[8][0],x+(i*32),y+(j*32),null);
					}

					//if it is a town
					if(areas[i][j].isTown){
						//if it is the first town
						if(areas[i][j].isFirstTown){
							g.drawImage(textures.mapIcons[5][0],x+(i*32),y+(j*32),null);
						}
						else{
							g.drawImage(textures.mapIcons[5][1],x+(i*32),y+(j*32),null);
						}
					}
					else{
						if(areas[i][j].avgLevel>0){
							g.setColor(Color.black);
							Font font = new Font("Iwona Heavy",Font.PLAIN,9);
							g.setFont(font);
							FontMetrics m = g.getFontMetrics();
							g.drawString(""+areas[i][j].avgLevel, x+(i*32)+11, y+(j*32)+19);
						}
					}
					if(areas[i][j]==currentArea){
						g.setColor(Color.white);
						g.drawRect(x+(i*32)+6, y+(j*32)+6, 19, 19);
					}
				}
			}
		}
	}
	public void drawPokedexEntry(Graphics2D g) {
		int x = (ApplicationUI.windowWidth/2)-(512/2);
		int y = (ApplicationUI.windowHeight/2)-(384/2);
		g.drawImage(textures.pokedexRegistrationComplete,x,y,null);
		Font font = new Font("Iwona Heavy",Font.PLAIN,24);
		g.setFont(font);
		FontMetrics m = g.getFontMetrics();
		g.drawString("POKeDEX registration completed.", x+60, y+24);
		g.drawImage(currentBattle.enemyPokemon.battleTexture[currentBattle.enemyPokemon.shiny][0],x+34, y+52,null);
		g.drawString("#"+currentBattle.enemyPokemon.ID, x+224, y+84);
		g.drawString(currentBattle.enemyPokemon.species, x+300, y+84);
		g.drawString(currentBattle.enemyPokemon.category, x+212, y+114);
		g.drawString("HT", x+224, y+170);
		g.drawString("WT", x+224, y+210);
		g.drawString(currentBattle.enemyPokemon.weight, x+300, y+170);
		g.drawString(currentBattle.enemyPokemon.height, x+300, y+210);
		g.drawImage(textures.footprints[currentBattle.enemyPokemon.ID],x+432,y+167,null);


		//draw description text
		g.setColor(new Color(10,10,10));
		String[] words = currentBattle.enemyPokemon.description.split(" ");
		int currentWordsPixelLength = 0;
		int currentY = y+280;
		while(m.stringWidth(currentBattle.enemyPokemon.description)>1670&&font.getSize()>18){
			font = new Font("Iwona Heavy",Font.PLAIN,font.getSize()-2);
			g.setFont(font);
			m = g.getFontMetrics();
		}
		for(int i = 0;i<words.length;i++){
			//if the length of the first line is longer than 440 pixels
			if(currentWordsPixelLength+(m.stringWidth(words[i])+5)<450){
				//draw the current word after the previous words
				g.drawString(words[i], x+20+currentWordsPixelLength, currentY);
				currentWordsPixelLength+=(m.stringWidth(words[i])+5);
			}
			else{
				currentY+=30;
				currentWordsPixelLength = 0;
				g.drawString(words[i], x+20+currentWordsPixelLength, currentY);
				currentWordsPixelLength+=(m.stringWidth(words[i])+5);
				//g.drawString(words[i], x+20+secondLinePixelLength, y+250);
				//secondLinePixelLength+=(m.stringWidth(words[i])+5);
			}

		}
	}
	public void Draw(Graphics2D g){
		//Point((ApplicationUI.windowWidth/2)-(width/2),(ApplicationUI.windowHeight/2)-(height/2));
		player.update();
		//if there is not a battle atm
		if(currentBattle==null){

			if(drawMap){
				drawMap(g);
			}//end of draw map

			else if(showVisualMap){
				g.drawImage(mapImage,mapPosition.x,mapPosition.y,null);
				g.setColor(new Color(randomNumber(1,255,false),randomNumber(1,255,false),randomNumber(1,255,false)));
				g.fillRect(80+(player.xpos/tileSize)-1, 80+(player.ypos/tileSize)-1, 2, 2);
			}
			else{
				if(currentArea!=null){
					if(currentArea.areaType.equals("outside")) {

						//draw areas that are on screen
						int viewX = ApplicationUI.windowWidth/2;
						int viewY = ApplicationUI.windowHeight/2;
						for(int x = (player.xpos/(areaWidth*tileSize))-((viewX/(areaWidth*32))+1);x<=(player.xpos/(areaWidth*tileSize))+((viewX/(areaWidth*32))+3);x++){
							for(int y = (player.ypos/(areaHeight*tileSize))-((viewY/(areaHeight*32))+1);y<=(player.ypos/(areaHeight*tileSize))+((viewY/(areaHeight*32))+2);y++){
								if(x>=0&&y>=0&&x<worldWidth&&y<worldHeight){
									if(areas[x][y]!=null&&areas[x][y]!=currentArea){
										areas[x][y].Draw(g);
									}
								}
							}
						}

						currentArea.Draw(g);

						currentArea.DrawPossibleEncounters(g);
					}
					else {
						currentArea.Draw(g);
					}
				}

			}

			//draw area name
			g.setColor(new Color(200,255,200));
			g.fillRect(ApplicationUI.windowWidth-250, 30, 220, 40);
			g.setColor(Color.black);
			g.drawRect(ApplicationUI.windowWidth-250, 30, 220, 40);
			g.setColor(Color.red);
			g.drawRect(ApplicationUI.windowWidth-249, 31, 218, 38);
			g.setColor(Color.black);
			g.drawRect(ApplicationUI.windowWidth-248, 32, 216, 36);
			g.setColor(Color.black);
			Font font = new Font("Iwona Heavy",Font.BOLD,24);
			g.setFont(font);
			g.drawString(currentArea.areaName, ApplicationUI.windowWidth-240, 60);
		}
		//if there is a battle atm
		else{

			//draw the battle
			if(!showPokedexRegistrationComplete){
				currentBattle.Draw(g);
			}
			else{//draw the new pokedex entry
				drawPokedexEntry(g);

			}
		}
		if(showMenu){
			for(int i = 0; i<menu.length;i++){
				menu[i].Draw(g);
			}
		}
		if(showInventory){
			player.inventory.Draw(g);
		}
		else if(showPokemon){
			int x = (ApplicationUI.windowWidth/2);
			int y = (ApplicationUI.windowHeight/2)-((44*player.party.size())/2)-100;
			showMenu = false;
			g.setColor(new Color(200,255,200));
			g.fillRect(x-100, y-5, 220, 304);
			g.setColor(Color.black);
			g.drawRect(x-100, y-5, 220, 304);
			g.setColor(Color.red);
			g.drawRect(x-99, y-4, 218, 302);
			g.setColor(Color.black);
			g.drawRect(x-98, y-3, 216, 300);
			for(int i = 0; i<player.party.size();i++){
				g.setColor(Color.black);
				g.setFont(menu[0].font);

				g.drawImage(textures.moveButtonIcons[0][1], x-85,y+(i*44),null);
				g.drawImage(player.party.get(i).battleTexture[player.party.get(i).shiny][0], x-83,y+(i*44)-2,40,40,null);
				g.drawImage(textures.genderIcons[player.party.get(i).gender][0], x+91,y+(i*44)+7,null);
				g.drawString(player.party.get(i).getName(), x-50, y+(i*44)+20);

				g.setFont(new Font("Iwona Heavy",Font.BOLD,12));
				g.drawString("Lv."+player.party.get(i).level, x+55, y+(i*44)+20);
				//hp bar
				g.drawImage(textures.partyHP, x-32,y+(i*44)+26,null);
				//fill the hp bar
				int hpBarWidth = (int)(double)(((double)player.party.get(i).currentHP/(double)player.party.get(i).HP)*96.0);
				if(hpBarWidth>48){
					g.setColor(new Color(24,192,32));
				}
				else if(hpBarWidth>19){
					g.setColor(new Color(248,224,56));
				}
				else{
					g.setColor(new Color(227,91,65));
				}
				g.fillRect(x, y+(i*44)+28, hpBarWidth, 8);
			}
			g.setColor(Color.black);
			g.setFont(new Font("Iwona Heavy",Font.BOLD,13));
			if(!pokemonIsSelected){
				g.drawString("Choose a pokemon.", x-95, y+285);
			}
			else{
				g.drawString("Do what with "+player.party.get(pokemonScreenSelectorPosition).getName()+"?", x-95, y+285);
			}
			g.drawImage(textures.cancelButton[0][1],x+55,y+265,60,30,null);
			g.setFont(new Font("Iwona Heavy",Font.BOLD,13));
			g.drawString("Cancel", x+63, y+285);
			if(pokemonScreenSelectorPosition<6){
				g.drawImage(textures.moveButtonIcons[0][0],x-85,y+(pokemonScreenSelectorPosition*44),null);
			}
			else{
				g.drawImage(textures.exitButton[0][0],x+55,y+265,null);
			}
			if(pokemonIsSelected){
				if(switchingPokemon==false){
					g.setColor(new Color(200,255,200));
					g.fillRect(x+30, y-5, 90, (4*30)+10);
					g.setColor(Color.black);
					g.drawRect(x+30, y-5, 90, (4*30)+10);
					g.setColor(Color.red);
					g.drawRect(x+31, y-4, 88, (4*30)+8);
					g.setColor(Color.black);
					g.drawRect(x+32, y-3, 86, (4*30)+6);
					for(int i = 0; i<4;i++){
						g.drawImage(textures.exitButton[0][1],x+35,y+(i*30),null);
						if(i==0){
							g.drawString("Info", x+55+8, y+20+(i*30));
						}
						if(i==1){
							if(player.party.get(0).currentHP>0){
								g.drawString("Switch", x+55, y+20+(i*30));
							}
							else{
								g.drawString("Send Out", x+45, y+20+(i*30));
							}
						}
						if(i==2){
							g.drawString("Item", x+55+8, y+20+(i*30));
						}
						if(i==3){
							g.drawString("Cancel", x+53, y+20+(i*30));
						}
					}
					g.drawImage(textures.exitButton[0][0],x+35,y+(selectedOptionForSelectedPokemon*30),null);
				}
				else{
					g.drawImage(textures.moveButtonIcons[0][2],x-85,y+(pokemonScreenSwitchSelectorPosition*44),null);
				}
			}
		}
		dialogBox.Draw(g);

	}
	public static void drawTextBox(Graphics2D g){
		if(GamePanel.currentBattle!=null){
			g.setColor(Color.black);
			if(currentBattle.state==currentBattle.TEXT&&!showPokedexRegistrationComplete){
				g.setFont(currentBattle.font);
				FontMetrics m = g.getFontMetrics(currentBattle.font);

				g.drawImage(GamePanel.textures.battleMessage,currentBattle.position.x, currentBattle.position.y+currentBattle.height-96,null);

				//determine text
				String text = "No known message to display";
				if(currentBattle.messageID==currentBattle.APPEARED){
					text = "A wild "+currentBattle.enemyPokemon.species+ " appeared!";
				}
				if(currentBattle.messageID==currentBattle.GOTAWAY){
					text = "Got away safely!";
				}
				if(currentBattle.messageID==currentBattle.CANTESCAPE){
					text = "Can't escape!";
				}
				if(currentBattle.messageID==currentBattle.NOPP){
					text = "There's no PP left for this move!";
				}
				if(currentBattle.messageID==currentBattle.PLAYERUSEDMOVE){
					text = currentBattle.playerPokemon.getName()+" used "+currentBattle.currentPlayerMove.name+"!";
				}
				if(currentBattle.messageID==currentBattle.ENEMYUSEDMOVE){
					text = currentBattle.enemyPokemon.getName()+" used "+currentBattle.currentEnemyMove.name+"!";
				}
				if(currentBattle.messageID==currentBattle.ENEMYFAINTED){
					text = "Wild "+currentBattle.enemyPokemon.getName()+" fainted!";
				}
				if(currentBattle.messageID==currentBattle.PLAYERFAINTED){
					text = currentBattle.playerPokemon.getName()+" fainted!";
				}
				if(currentBattle.messageID==currentBattle.GAINEDXP){
					text = currentBattle.playerPokemon.getName()+" gained "+currentBattle.enemyPokemon.getExperienceYield()+" EXP. Points!";
				}
				if(currentBattle.messageID==currentBattle.SUPEREFFECTIVE){
					text = "It's super effective!";
				}
				if(currentBattle.messageID==currentBattle.NOTVERYEFFECTIVE){
					text = "It's not very effective...";
				}
				if(currentBattle.messageID==currentBattle.ALREADYDEAD){
					if(pokemonScreenSelectorPosition<6){
						text = GamePanel.player.party.get(GamePanel.pokemonScreenSelectorPosition).getName()+" has no energy left to battle!";
					}
				}
				if(currentBattle.messageID==currentBattle.NOPOKEMONLEFT){
					text = player.name+" is out of usable pokemon!";
				}
				if(currentBattle.messageID==currentBattle.BLACKEDOUT){
					text = player.name+" blacked out!";
				}
				if(currentBattle.messageID==currentBattle.ZEROSHAKES){
					text = "Oh no! The pokemon broke free!";
				}
				if(currentBattle.messageID==currentBattle.ONESHAKE){
					text = "Aww! It appeared to be caught!";
				}
				if(currentBattle.messageID==currentBattle.TWOSHAKES){
					text = "Aargh! Almost had it!";
				}
				if(currentBattle.messageID==currentBattle.THREESHAKES){
					text = "Shoot! It was so close, too!";
				}
				if(currentBattle.messageID==currentBattle.CAUGHTPOKEMON){
					text = "Gotcha! "+GamePanel.currentBattle.enemyPokemon.getName()+" was caught!";
				}
				if(currentBattle.messageID==currentBattle.NAMEPOKEMON){
					text = "Give a nickname to captured "+currentBattle.enemyPokemon.getName()+"?";
				}

				//draw text
				String[] words = text.split(" ");
				int currentWordsPixelLength = 0;
				int secondLinePixelLength = 0;
				for(int i = 0;i<words.length;i++){
					if(currentWordsPixelLength+(m.stringWidth(words[i])+5)<472){
						g.drawString(words[i], currentBattle.position.x+20+currentWordsPixelLength, currentBattle.position.y+currentBattle.height-60);
						currentWordsPixelLength+=(m.stringWidth(words[i])+5);
					}
					else{
						g.drawString(words[i], currentBattle.position.x+20+secondLinePixelLength, currentBattle.position.y+currentBattle.height-30);
						secondLinePixelLength+=(m.stringWidth(words[i])+5);
					}

				}

			}
			else if(currentBattle.state==currentBattle.PICKOPTION){
				g.setFont(currentBattle.font);
				FontMetrics m = g.getFontMetrics(currentBattle.font);

				g.drawImage(GamePanel.textures.battleCommand,currentBattle.position.x, currentBattle.position.y+currentBattle.height-96,null);

				//determine text
				String text = "What will "+currentBattle.playerPokemon.getName()+ " do?";

				//draw text
				String[] words = text.split(" ");
				int currentWordsPixelLength = 0;
				int secondLinePixelLength = 0;
				for(int i = 0;i<words.length;i++){
					if(currentWordsPixelLength+(m.stringWidth(words[i])+5)<225){
						g.drawString(words[i], currentBattle.position.x+25+currentWordsPixelLength, currentBattle.position.y+currentBattle.height-60);
						currentWordsPixelLength+=(m.stringWidth(words[i])+5);
					}
					else{
						g.drawString(words[i], currentBattle.position.x+25+secondLinePixelLength, currentBattle.position.y+currentBattle.height-30);
						secondLinePixelLength+=(m.stringWidth(words[i])+5);
					}

				}
				for(int i = 0;i<2;i++){
					for(int j = 0; j<2;j++){
						currentBattle.pickOptionButtons[i][j].Draw(g);

					}
				}
			}
			else if(currentBattle.state==currentBattle.FIGHT){
				g.setFont(currentBattle.font);
				g.setColor(Color.black);
				FontMetrics m = g.getFontMetrics(currentBattle.font);

				g.drawImage(GamePanel.textures.battleFight,currentBattle.position.x, currentBattle.position.y+currentBattle.height-96,null);
				//draw moves
				if(currentBattle.playerPokemon.moves.size()>0){
					currentBattle.playerPokemon.moves.get(0).drawIcon(g, currentBattle.position.x+4, currentBattle.position.y+currentBattle.height-90);
					//draw move name
					g.drawString(currentBattle.playerPokemon.moves.get(0).name, currentBattle.position.x+99-(m.stringWidth(currentBattle.playerPokemon.moves.get(0).name)/2), currentBattle.position.y+currentBattle.height-62);
				}
				if(currentBattle.playerPokemon.moves.size()>1){
					currentBattle.playerPokemon.moves.get(1).drawIcon(g, currentBattle.position.x+194, currentBattle.position.y+currentBattle.height-90);
					//draw move name
					g.drawString(currentBattle.playerPokemon.moves.get(1).name, currentBattle.position.x+289-(m.stringWidth(currentBattle.playerPokemon.moves.get(1).name)/2), currentBattle.position.y+currentBattle.height-62);
				}
				if(currentBattle.playerPokemon.moves.size()>2){
					currentBattle.playerPokemon.moves.get(2).drawIcon(g, currentBattle.position.x+4, currentBattle.position.y+currentBattle.height-46);
					//draw move name
					g.drawString(currentBattle.playerPokemon.moves.get(2).name, currentBattle.position.x+99-(m.stringWidth(currentBattle.playerPokemon.moves.get(2).name)/2), currentBattle.position.y+currentBattle.height-18);
				}
				if(currentBattle.playerPokemon.moves.size()>3){
					currentBattle.playerPokemon.moves.get(3).drawIcon(g, currentBattle.position.x+194, currentBattle.position.y+currentBattle.height-46);
					//draw move name
					g.drawString(currentBattle.playerPokemon.moves.get(3).name, currentBattle.position.x+289-(m.stringWidth(currentBattle.playerPokemon.moves.get(3).name)/2), currentBattle.position.y+currentBattle.height-18);
				}

				//if the pp is less than or equal to 50% make it yellow/orange
				int w = m.stringWidth("PP: "+currentBattle.currentPlayerMove.currentPP+"/"+currentBattle.currentPlayerMove.PP)/2;
				g.drawString("PP: ", currentBattle.position.x+currentBattle.width-64-w, currentBattle.position.y+currentBattle.height-25);
				if(currentBattle.currentPlayerMove.currentPP==0){
					g.setColor(new Color(255,0,0));
				}
				else if((double)currentBattle.currentPlayerMove.currentPP/(double)currentBattle.currentPlayerMove.PP<=.2){
					g.setColor(new Color(255,110,0));
				}
				else if((double)currentBattle.currentPlayerMove.currentPP/(double)currentBattle.currentPlayerMove.PP<=.5){
					g.setColor(new Color(255,180,0));
				}

				//draw the PP of the current move
				g.drawString(currentBattle.currentPlayerMove.currentPP+"/"+currentBattle.currentPlayerMove.PP, currentBattle.position.x+currentBattle.width+m.stringWidth("PP: ")+-64-w, currentBattle.position.y+currentBattle.height-25);
			}
		}
		g.setColor(Color.white);
		g.drawString("FPS: "+ApplicationUI.currentFPS, ApplicationUI.windowWidth-100, 50);
	}
}
