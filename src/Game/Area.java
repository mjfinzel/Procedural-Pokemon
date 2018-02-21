package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Area {
	LevelTile[][] tileMap;
	Point position;
	Font font = new Font("Iwona Heavy",Font.PLAIN,12);
	Font boldFont = new Font("Iwona Heavy",Font.BOLD,12);
	int areaNumber = -1;
	int avgLevel = -1;
	int routeNumber = -1;
	ArrayList<Area> connectedAreas = new ArrayList<Area>();
	boolean isTown = false;
	boolean isFirstTown = false;
	int biome = -4;

	Point topExit = null;
	Point bottomExit = null;
	Point leftExit = null;
	Point rightExit = null;
	ArrayList<Point> exits = new ArrayList<Point>();
	ArrayList<Point> pathPoints = new ArrayList<Point>();
	ArrayList<NPC> npcs = new ArrayList<NPC>();

	ArrayList<ArrayList<Point>> paths = new ArrayList<ArrayList<Point>>();

	int areaWidth = GamePanel.areaWidth;
	int areaHeight = GamePanel.areaHeight;

	ArrayList<Animation> animations = new ArrayList<Animation>();

	Point pokecenterDoorPos = null;

	//list of pokemon that can be found in grass in this area (pokemonID,encounterRate/1000)
	ArrayList<Point> possibleShortGrassEncounters = new ArrayList<Point>();
	String areaType = "none";
	String areaName = "Unknown Area";

	ArrayList<Area> buildings =  new ArrayList<Area>();

	public Area(Point position, String type){
		this.position = position;
		areaType = type;
		if(!type.equals("outside")) areaName = type;
		if(type.equals("Lab")) {
			areaWidth = 14;
			areaHeight = 10;
			tileMap = new LevelTile[areaWidth][areaHeight];
			for(int i = 0; i<areaWidth; i++){
				for(int j = 0; j<areaHeight;j++){
					tileMap[i][j] = new LevelTile(i,j,7,0);
				}
			}
			updateTiles();
		}
	}
	public Area getBuildingByName(String n) {
		for(int i = 0; i<buildings.size();i++) {
			if(buildings.get(i).areaName==n) {
				return buildings.get(i);
			}
		}
		return null;
	}
	public void getPossibleEncounters(){
		//System.out.println(biome);
		if(!isTown){
			int totalPossibleEncounters = GamePanel.randomNumber(2, 5, true);
			//short grass
			for(int i = 0; i<totalPossibleEncounters;i++){
				Point newPokemonType = null;
				while(newPokemonType==null||possibleShortGrassEncounters.contains(newPokemonType)){
					if(biome == 4){
						newPokemonType = new Point(GamePanel.graveyardPokemon.get(GamePanel.randomNumber(0, GamePanel.graveyardPokemon.size()-1, true)));
					}
					else{
						newPokemonType = new Point(GamePanel.shortGrassPokemon.get(GamePanel.randomNumber(0, GamePanel.shortGrassPokemon.size()-1, true)));
					}
					newPokemonType = new Point(new Pokemon(newPokemonType.x,avgLevel-2).ID,newPokemonType.y);
				}
				possibleShortGrassEncounters.add(newPokemonType);
			}
		}
	}

	public void generateExits(){
		//determine where exits connect
		boolean hasTopExit = false;
		boolean hasBottomExit = false;
		boolean hasLeftExit = false;
		boolean hasRightExit = false;

		//if the adjacent area has the same biome then the offset of the exit is 0, if it has a different biome then the offset is larger
		//so that the texture change transition isn't visible to the player
		int topExitOffset = 0;
		int bottomExitOffset = 0;
		int leftExitOffset = 0;
		int rightExitOffset = 0;

		Area top = null;
		Area bottom = null;
		Area left = null;
		Area right =  null;

		for(int i = 0; i<connectedAreas.size();i++){
			//System.out.println("There are "+connectedAreas.size()+" connected areas");
			//top
			if(connectedAreas.get(i).position.y<position.y){
				hasTopExit = true;
				top = connectedAreas.get(i);
				if(top.biome!=biome) {
					topExitOffset=20;
				}
			}
			//bottom
			if(connectedAreas.get(i).position.y>position.y){
				hasBottomExit = true;
				bottom = connectedAreas.get(i);
				if(bottom.biome!=biome) {
					bottomExitOffset=20;
				}
			}
			//left
			if(connectedAreas.get(i).position.x<position.x){
				hasLeftExit = true;
				left = connectedAreas.get(i);
				if(left.biome!=biome) {
					leftExitOffset=20;
				}
			}
			//right
			if(connectedAreas.get(i).position.x>position.x){
				hasRightExit = true;
				right = connectedAreas.get(i);
				if(right.biome!=biome) {
					rightExitOffset=20;
				}
			}
		}

		//determine exit locations
		if(hasTopExit){
			if(top.exits.size()==0){
				topExit = new Point(GamePanel.randomNumber(20,areaWidth-21,true),topExitOffset);
			}
			else{
				topExit = new Point(top.bottomExit.x,topExitOffset);
			}
			exits.add(topExit);
		}
		if(hasBottomExit){
			if(bottom.exits.size()==0){
				bottomExit = new Point(GamePanel.randomNumber(20,areaWidth-21,true),(areaHeight-1)-bottomExitOffset);
			}
			else{
				bottomExit = new Point(bottom.topExit.x,(areaHeight-1)-bottomExitOffset);
			}
			exits.add(bottomExit);
		}
		if(hasLeftExit){
			if(left.exits.size()==0){
				leftExit = new Point(leftExitOffset,GamePanel.randomNumber(20,areaHeight-21,true));
			}
			else{
				leftExit = new Point(leftExitOffset,left.rightExit.y);
			}
			exits.add(leftExit);
		}
		if(hasRightExit){
			if(right.exits.size()==0){
				rightExit = new Point((areaWidth-1)-rightExitOffset,GamePanel.randomNumber(20,areaHeight-21,true));
			}
			else{
				rightExit = new Point((areaWidth-1)-rightExitOffset,right.leftExit.y);
			}
			exits.add(rightExit);
			//GamePanel.tileMap[rightExit.x][rightExit.y].flagged = true;
		}
	}
	public void updateTiles(){

		for(int i = 0; i<areaWidth;i++){
			for(int j = 0; j<areaHeight;j++){
				if(areaType.equals("outside")) {
					GamePanel.tileMap[(position.x*GamePanel.worldWidth)+i][(position.y*GamePanel.worldHeight)+j].updateArt(GamePanel.areas[position.x][position.y]);
				}
				else {
					tileMap[i][j].updateArt(this);
				}
			}
		}
	}
	public void generateAsRoute(){


		generateExits();
		ArrayList<Point> points = new ArrayList<Point>(exits);
		points.add(new Point(GamePanel.randomNumber(50, areaWidth-50, false),GamePanel.randomNumber(50, areaHeight-50, true)));
		for(int i = 1; i<points.size();i++){
			Point start = new Point((position.x*GamePanel.areaWidth)+points.get(i).x,(position.y*GamePanel.areaHeight)+points.get(i).y);
			Point end = new Point((position.x*GamePanel.areaWidth)+points.get(i-1).x,(position.y*GamePanel.areaHeight)+points.get(i-1).y);
			createSnakePathBetweenPoints(start, end);
		}

	}
	public void pickName(){
		if(this.isTown){
			areaName = GamePanel.names.names[GamePanel.randomNumber(0, GamePanel.names.names.length-1, true)]+" Town";
		}
	}
	public void generateAsTown(){

		pickName();
		generateExits();
		//generate roads, then connect them to the exits with path, then generate houses on the roads
		//add paths
		int numberOfPaths = GamePanel.randomNumber(5, 12, true);
		ArrayList<Rectangle> paths = new ArrayList<Rectangle>();

		for(int i = 0; i<numberOfPaths;i++){
			int pathLength = GamePanel.randomNumber(8,12,true);
			addPath(pathLength,paths);
		}

		if(leftExit!=null){
			Rectangle closestPath = paths.get(0);
			for(int i = 0; i<paths.size();i++){
				if(paths.get(i).x<closestPath.x){
					closestPath = paths.get(i);
				}
			}

			Point exitPos = new Point((areaWidth*position.x)+leftExit.x,(areaHeight*position.y)+leftExit.y);
			createSnakePathBetweenPoints(exitPos,new Point(closestPath.x,closestPath.y));
			updateTiles();
		}
		if(rightExit!=null){
			Rectangle closestPath = paths.get(0);
			for(int i = 0; i<paths.size();i++){
				if(paths.get(i).x+paths.get(i).width>closestPath.x+paths.get(i).width){
					closestPath = paths.get(i);
				}
			}

			Point exitPos = new Point((areaWidth*position.x)+rightExit.x,(areaHeight*position.y)+rightExit.y);
			createSnakePathBetweenPoints(exitPos,new Point(closestPath.x,closestPath.y));
			updateTiles();
		}
		if(topExit!=null){
			Rectangle closestPath = paths.get(0);
			for(int i = 0; i<paths.size();i++){
				if(paths.get(i).y<closestPath.y){
					closestPath = paths.get(i);
				}
			}

			Point exitPos = new Point((areaWidth*position.x)+topExit.x,(areaHeight*position.y)+topExit.y);
			createSnakePathBetweenPoints(exitPos,new Point(closestPath.x,closestPath.y));
			updateTiles();
		}
		if(bottomExit!=null){
			Rectangle closestPath = paths.get(0);
			for(int i = 0; i<paths.size();i++){
				if(paths.get(i).y>closestPath.y){
					closestPath = paths.get(i);
				}
			}

			Point exitPos = new Point((areaWidth*position.x)+bottomExit.x,(areaHeight*position.y)+bottomExit.y);
			createSnakePathBetweenPoints(exitPos,new Point(closestPath.x,closestPath.y));
			updateTiles();
		}

		//add structures
		ArrayList<Rectangle> pathsWithoutStructures = new ArrayList<Rectangle>(paths);
		addStructure("Poke Center",pathsWithoutStructures,paths);
		addStructure("Mart",pathsWithoutStructures,paths);
		if(this.isFirstTown) {
			addStructure("Lab",pathsWithoutStructures,paths);
		}
		while(pathsWithoutStructures.size()>1){
			addStructure("Brown House",pathsWithoutStructures,paths);
		}

		//add npc's
		for(int i = 0; i<paths.size();i++){
			if(i<2||GamePanel.randomNumber(1, 100, true)<=60){
				int x = GamePanel.randomNumber(paths.get(i).x, paths.get(i).x+paths.get(i).width-1, true);
				int y = GamePanel.randomNumber(paths.get(i).y, paths.get(i).y+paths.get(i).height-1, true);
				npcs.add(new NPC(GamePanel.randomNumber(1, 10, true),x*GamePanel.tileSize,y*GamePanel.tileSize,GamePanel.randomNumber(0, 3, true),false));
			}
		}
	}

	public void generateAsOcean(){
		//fill the entire tileMap  with water
		for(int i = 0; i<areaWidth;i++){
			for(int j = 0; j<areaHeight;j++){
				GamePanel.tileMap[(position.x*areaWidth)+i][(position.y*areaHeight)+j].id = 3;
				GamePanel.tileMap[(position.x*areaWidth)+i][(position.y*areaHeight)+j].elevation = 1;
			}
		}
	}
	public void generateAsMountain(){

	}
	//	public void generate(){
	//
	//		initializeTileMap("cliff",2);
	//		//add trees
	//		removeOutcrops("cliff","grass",2,1);
	//		ArrayList<Tile> possibleTreeTiles = tilesAtElevation(2);
	//		int numForests = GamePanel.randomNumber(0, 5, true);
	//		for(int i = 0; i<numForests;i++){
	//			addTileGroup("pine", (possibleTreeTiles.size()/10)*2, possibleTreeTiles,-1,false);
	//			possibleTreeTiles = tilesAtElevation(2);
	//		}
	//
	//		removeOutcrops("pine","grass",2,1);
	//		generateExits();
	//
	//		updateTiles();
	//
	//		ArrayList<Point> points = new ArrayList<Point>(exits);
	//		points.add(new Point(GamePanel.randomNumber(50, areaWidth-50, false),GamePanel.randomNumber(50, areaHeight-50, false)));
	//		for(int i = 1; i<points.size();i++){
	//			createSnakePathBetweenPoints(points.get(i),points.get(i-1));
	//		}
	//
	//		updateTiles();
	//
	//		//add terraces
	//		removeOutcrops("cliff","grass",2,1);
	//
	//		updateTiles();
	//		int numTerraces = GamePanel.randomNumber(6, 22,true);
	//		for(int i = 2; i<numTerraces+2;i++){
	//
	//			ArrayList<Tile> possibleTiles = tilesAtElevation(i);
	//
	//			if(i>3){
	//				addTileGroup("cliff", (possibleTiles.size()/10)*8, possibleTiles,1,false);
	//			}
	//			else{
	//				addTileGroup("cliff", -1, possibleTiles,1,false);
	//			}
	//			updateTiles();
	//
	//			removeOutcrops("cliff","cliff",i+1,i);
	//		}
	//
	//		//add grass
	//		updateTiles();
	//		//add some grass
	//		ArrayList<Tile> possibleTiles1 = tilesAtElevation(1);
	//		ArrayList<Tile> possibleTiles = new ArrayList<Tile>();
	//		//remove tree tiles
	//		for(int i = 0; i<possibleTiles1.size();i++){
	//			if(!possibleTiles1.get(i).tileType.equalsIgnoreCase("pine")){
	//				possibleTiles.add(possibleTiles1.get(i));
	//
	//			}
	//		}
	//		//System.out.println("possible tiles:"+possibleTiles.size());
	//		int numberOfPatches = GamePanel.randomNumber(12, 25,true);
	//		for(int i = 0; i<numberOfPatches;i++){
	//			addTileGroup("poke_grass",GamePanel.randomNumber(20, 100,true),possibleTiles,0,true);
	//		}
	//		updateTiles();
	//
	//		//add water
	//	}
	public static double perpindicularAngle (Point a, Point b) {
		double x1 = a.x;
		double y1 = a.y;
		double x2 = b.x;
		double y2 = b.y;
		double xdiff = x1 - x2;
		double ydiff = y1 - y2;
		double atan = Math.atan2(ydiff, xdiff);
		return atan+(Math.PI/2);
	}

	//	public void generateStartingTown(){
	//		initializeTileMap("cliff",2);
	//		//add trees
	//		ArrayList<Tile> possibleTreeTiles = tilesAtElevation(2);
	//		int numForests = GamePanel.randomNumber(0, 5, true);
	//		for(int i = 0; i<numForests;i++){
	//			addTileGroup("pine", (possibleTreeTiles.size()/10)*2, possibleTreeTiles,-1,false);
	//			possibleTreeTiles = tilesAtElevation(2);
	//		}
	//
	//		removeOutcrops("pine","grass",2,1);
	//		//generateExits();
	//
	//		updateTiles();
	//
	//		//add paths
	//		int numberOfPaths = GamePanel.randomNumber(5, 12, true);
	//		ArrayList<Rectangle> paths = new ArrayList<Rectangle>();
	//
	//		for(int i = 0; i<numberOfPaths;i++){
	//			int pathLength = GamePanel.randomNumber(8,12,true);
	//			addPath(pathLength,paths,1);
	//		}
	//		ArrayList<Rectangle> pathsWithoutStructures = new ArrayList<Rectangle>(paths);
	//		removeOutcrops("cliff","grass",2,1);
	//
	//		updateTiles();
	//
	//		//add poke center
	//		addStructure("Poke Center",pathsWithoutStructures,paths,1);
	//		addStructure("Mart",pathsWithoutStructures,paths,1);
	//		while(pathsWithoutStructures.size()>1){
	//			addStructure("Brown House",pathsWithoutStructures,paths,1);
	//		}
	//		removeOutcrops("cliff","grass",2,1);
	//		updateTiles();
	//		//add terraces
	//		int numTerraces = GamePanel.randomNumber(6, 22,true);
	//		for(int i = 2; i<numTerraces+2;i++){
	//
	//			ArrayList<Tile> possibleTiles = tilesAtElevation(i);
	//
	//			if(i>3){
	//				addTileGroup("cliff", (possibleTiles.size()/10)*8, possibleTiles,1,false);
	//			}
	//			else{
	//				addTileGroup("cliff", -1, possibleTiles,1,false);
	//			}
	//			updateTiles();
	//
	//			removeOutcrops("cliff","cliff",i+1,i);
	//		}
	//
	//		updateTiles();
	//
	//		//add npc's
	//		for(int i = 0; i<paths.size();i++){
	//			if(i<2||GamePanel.randomNumber(1, 100, true)<=60){
	//				int x = GamePanel.randomNumber(paths.get(i).x, paths.get(i).x+paths.get(i).width-1, true);
	//				int y = GamePanel.randomNumber(paths.get(i).y, paths.get(i).y+paths.get(i).height-1, true);
	//				npcs.add(new NPC(GamePanel.randomNumber(1, 10, true),x*GamePanel.tileSize,y*GamePanel.tileSize,GamePanel.randomNumber(0, 3, true)));
	//			}
	//		}
	//
	//	}
	public void addStructure(String name,ArrayList<Rectangle> pathsWithoutStructures, ArrayList<Rectangle> paths){
		int width = 0;
		int height = 0;
		if(name.equals("Poke Center")){
			width = 5;
			height = 5;
		}
		else if(name.equals("Mart")){
			width = 4;
			height = 4;
		}
		else if(name.equals("Brown House")){
			width = 4;
			height = 4;
		}
		else if(name.equals("Lab")){
			width = 7;
			height = 5;
		}
		//add poke center
		boolean addedBuilding = false;
		int tries = 0;
		while(!addedBuilding&&tries<50){
			//pick a random path from the list of paths without structures on them
			Rectangle pathChosen = pathsWithoutStructures.get(GamePanel.randomNumber(0, pathsWithoutStructures.size()-1, true));
			//pick a random position north of this path
			Point pos = new Point(GamePanel.randomNumber(pathChosen.x, pathChosen.x+pathChosen.width-width, true),pathChosen.y-height);

			boolean collides = false;
			//check for collision with a path
			for(int i = 0; i<paths.size();i++){
				if(paths.get(i).intersects(new Rectangle(pos.x,pos.y,width,height))){
					collides = true;
					break;
				}
			}
			//check for collision with a structure
			if(!collides){
				for(int i = 0;i<width;i++){
					for(int j = 0; j<height;j++){
						if(GamePanel.tileMap[pos.x+i][pos.y+j].hasBuilding||GamePanel.tileMap[pos.x+i][pos.y+j].id==1){
							collides = true;
							break;
						}
					}
				}
			}

			if(!collides){
				pathsWithoutStructures.remove(pathChosen);

				for(int i = 0;i<width;i++){
					for(int j = 0; j<height;j++){
						GamePanel.tileMap[pos.x+i][pos.y+j].hasBoulder=false;
						GamePanel.tileMap[pos.x+i][pos.y+j].id=0;
						if(name.equalsIgnoreCase("Poke Center")){
							GamePanel.tileMap[pos.x+i][pos.y+j].artCrd[1].x=0+i;
							if(biome == 1){
								GamePanel.tileMap[pos.x+i][pos.y+j].artCrd[1].x=4+i;
							}
							GamePanel.tileMap[pos.x+i][pos.y+j].artCrd[1].y=23+j;
							if(i==2&&j==4){
								pokecenterDoorPos = new Point(pos.x+i,pos.y+j);
							}
						}
						else if(name.equals("Mart")){
							GamePanel.tileMap[pos.x+i][pos.y+j].artCrd[1].x=0+i;
							if(biome == 1){
								GamePanel.tileMap[pos.x+i][pos.y+j].artCrd[1].x=4+i;
							}
							GamePanel.tileMap[pos.x+i][pos.y+j].artCrd[1].y=28+j;
						}
						else if(name.equals("Brown House")){
							GamePanel.tileMap[pos.x+i][pos.y+j].artCrd[1].x=4+i;
							if(biome == 1){
								GamePanel.tileMap[pos.x+i][pos.y+j].artCrd[1].x=8+i;
							}
							GamePanel.tileMap[pos.x+i][pos.y+j].artCrd[1].y=28+j;
						}
						else if(name.equals("Lab")){
							//lab is always in biome 0
							GamePanel.tileMap[pos.x+i][pos.y+j].artCrd[1].x=0+i;
							GamePanel.tileMap[pos.x+i][pos.y+j].artCrd[1].y=18+j;
						}
						GamePanel.tileMap[pos.x+i][pos.y+j].hasBuilding=true;

					}
				}


				addedBuilding = true;
			}
			tries++;
			if(tries==50){
				pathsWithoutStructures.remove(pathChosen);
				break;
			}
		}
		if(addedBuilding) {
			buildings.add(new Area(new Point(-1,-1),name));
		}

		updateTiles();
	}
	public void addPath(int length, ArrayList<Rectangle> existingPaths){
		int width = 2;
		boolean added = false;
		int tries = 0;
		while(added==false&&tries<100){
			tries++;
			LevelTile chosenAdjacentTile = null;
			LevelTile connectionTile = null;
			if(existingPaths.size()>0){
				boolean adjacentFound = false;
				while(adjacentFound==false){
					//pick a random path tile
					Rectangle path = existingPaths.get(GamePanel.randomNumber(0, existingPaths.size()-1, true));
					int x = GamePanel.randomNumber(path.x, path.x+path.width, true);
					int y = GamePanel.randomNumber(path.y, path.y+path.height, true);
					connectionTile = GamePanel.tileMap[x][y];
					ArrayList<LevelTile> adjacentTiles = new ArrayList<LevelTile>();
					for(int i = x-1;i<=x+1;i++){
						for(int j = y-1; j<=y+1;j++){
							if(i>=0&&j>=0&&i<(GamePanel.areaWidth*GamePanel.worldWidth)&&j<(GamePanel.areaHeight*GamePanel.worldHeight)){
								if(GamePanel.tileMap[i][j].id!=1){
									if((i==x||j==y)&&!(i==x&&j==y)){
										adjacentTiles.add(GamePanel.tileMap[i][j]);
										adjacentFound = true;
									}
								}
							}
						}
					}
					if(adjacentFound){
						//pick a random adjacent non path tile
						chosenAdjacentTile = adjacentTiles.get(GamePanel.randomNumber(0, adjacentTiles.size()-1, true));
					}
				}
			}
			else{
				chosenAdjacentTile = GamePanel.tileMap[(position.x*areaWidth)+areaWidth/2][(position.y*areaHeight)+(areaHeight/2)+1];
				connectionTile = GamePanel.tileMap[areaWidth/2][areaHeight/2];
			}
			boolean spaceFound = true;
			Rectangle pathSpace = null;

			//top
			if(chosenAdjacentTile.ypos<connectionTile.ypos){

				pathSpace = new Rectangle(chosenAdjacentTile.xpos/GamePanel.tileSize,(chosenAdjacentTile.ypos/GamePanel.tileSize)-length,width,length);
				//check if there is room for the path in this direction
				for(int i = 0; i<existingPaths.size();i++){
					if(existingPaths.get(i).intersects(pathSpace)){
						spaceFound = false;
					}
				}
			}
			//bottom
			if(chosenAdjacentTile.ypos>connectionTile.ypos){

				pathSpace = new Rectangle(chosenAdjacentTile.xpos/GamePanel.tileSize,(chosenAdjacentTile.ypos/GamePanel.tileSize),width,length);
				//check if there is room for the path in this direction
				for(int i = 0; i<existingPaths.size();i++){
					if(existingPaths.get(i).intersects(pathSpace)){
						spaceFound = false;
					}
				}
			}
			//left
			if(chosenAdjacentTile.xpos<connectionTile.xpos){

				pathSpace = new Rectangle((chosenAdjacentTile.xpos/GamePanel.tileSize)-length,(chosenAdjacentTile.ypos/GamePanel.tileSize),length,width);
				//check if there is room for the path in this direction
				for(int i = 0; i<existingPaths.size();i++){
					if(existingPaths.get(i).intersects(pathSpace)){
						spaceFound = false;
					}
				}
			}
			//right
			if(chosenAdjacentTile.xpos>connectionTile.xpos){

				pathSpace = new Rectangle((chosenAdjacentTile.xpos/GamePanel.tileSize),(chosenAdjacentTile.ypos/GamePanel.tileSize),length,width);
				//check if there is room for the path in this direction
				for(int i = 0; i<existingPaths.size();i++){
					if(existingPaths.get(i).intersects(pathSpace)){
						spaceFound = false;
					}
				}
			}
			if(spaceFound){

				//fill in the space with path tiles
				for(int i = pathSpace.x; i<=pathSpace.x+pathSpace.width;i++){
					for(int j = pathSpace.y; j<=pathSpace.y+pathSpace.height;j++){
						GamePanel.tileMap[i][j].id=1;
						GamePanel.tileMap[i][j].updateArt(GamePanel.areas[position.x][position.y]);
					}
				}
				updateTiles();
				added = true;
				existingPaths.add(pathSpace);
			}
		}
	}
	//	public ArrayList<Tile> getPossiblePathConnectionTiles(ArrayList<Rectangle> paths){
	//		ArrayList<Tile> result = new ArrayList<Tile>();
	//		for(int i = 0; i<paths.size();i++){
	//			for(int j = paths.get(i).x; j<paths.get(i).x+paths.get(i).width;j++){
	//				for(int k = paths.get(i).y; j<paths.get(i).y+paths.get(i).height;k++){
	//					if(tileMap[j][k].tileType=="path"){
	//
	//					}
	//				}
	//			}
	//		}
	//		return result;
	//	}
	//	public void generateRoute(){
	//		//initialize the tile map
	//		initializeTileMap("cliff",2);
	//
	//		//determine where exits connect
	//		boolean hasTopExit = false;
	//		boolean hasBottomExit = false;
	//		boolean hasLeftExit = false;
	//		boolean hasRightExit = false;
	//
	//		for(int i = 0; i<connectedAreas.size();i++){
	//			//top
	//			if(connectedAreas.get(i).position.y<position.y){
	//				hasTopExit = true;
	//			}
	//			//bottom
	//			if(connectedAreas.get(i).position.y>position.y){
	//				hasBottomExit = true;
	//			}
	//			//left
	//			if(connectedAreas.get(i).position.x<position.x){
	//				hasLeftExit = true;
	//			}
	//			//right
	//			if(connectedAreas.get(i).position.x>position.x){
	//				hasRightExit = true;
	//			}
	//		}
	//
	//		//determine exit locations
	//		if(hasTopExit){
	//			topExit = new Point(GamePanel.randomNumber(60,areaWidth-62,true),34);
	//			exits.add(topExit);
	//		}
	//		if(hasBottomExit){
	//			bottomExit = new Point(GamePanel.randomNumber(60,areaWidth-62,true),areaHeight-36);
	//			exits.add(bottomExit);
	//		}
	//		if(hasLeftExit){
	//			leftExit = new Point(60,GamePanel.randomNumber(34,areaHeight-36,true));
	//			exits.add(leftExit);
	//		}
	//		if(hasRightExit){
	//			rightExit = new Point(areaWidth-62,GamePanel.randomNumber(34,areaHeight-36,true));
	//			exits.add(rightExit);
	//		}
	//
	//		//connect the exits
	//		if(exits.size()>=1){
	//			//generate a few points to connect between exits
	//			int numberOfMiddlePoints = GamePanel.randomNumber(2, 8,true);
	//			for(int i = 0; i<numberOfMiddlePoints; i++){
	//				pathPoints.add(new Point(GamePanel.randomNumber(areaWidth/5,(areaWidth/5)*4,true),GamePanel.randomNumber(areaHeight/5,(areaHeight/5)*4,true)));
	//			}
	//			for(int i = 0; i<exits.size();i++){
	//				//pathPoints.add(exits.get(i));
	//			}
	//			//connect each exit to it's closest point
	//			for(int i = 0; i<exits.size();i++){
	//				double distanceToCurrentClosest = areaWidth*areaHeight;
	//				Point currentClosest = pathPoints.get(0);
	//				//for each path point
	//				for(int j = 0; j<pathPoints.size();j++){
	//					//get the distance between this point and the exit
	//					double distance = Math.sqrt(Math.pow((pathPoints.get(j).x-exits.get(i).x),2)+Math.pow((pathPoints.get(j).y-exits.get(i).y),2));
	//					if(distance<distanceToCurrentClosest){
	//						currentClosest = pathPoints.get(j);
	//						distanceToCurrentClosest = distance;
	//					}
	//				}
	//				generatePathBetweenPoints("grass",exits.get(i),currentClosest,GamePanel.randomNumber(2, 5,true),1);
	//				//
	//			}
	//			//connect all pathPoints
	//			for(int i = 0; i<pathPoints.size();i++){
	//				for(int j = 0; j<pathPoints.size();j++){
	//					generatePathBetweenPoints("grass",pathPoints.get(i),pathPoints.get(j),GamePanel.randomNumber(1,1,true),1);
	//				}
	//			}
	//
	//			removeOutcrops("cliff","grass",2,1);
	//
	//			updateTiles();
	//			int numTerraces = GamePanel.randomNumber(6, 22,true);
	//			for(int i = 2; i<numTerraces+2;i++){
	//				System.out.println("Adding terrace at elevation: "+i);
	//				//				for(int x = 0; x<areaWidth;x++){
	//				//					for(int y = 0; y<areaHeight;y++){
	//				//						removeOutcrops(tileMap[x][y],"grass",i-1);
	//				//					}
	//				//				}
	//
	//				//updateTiles();
	//				ArrayList<Tile> possibleTiles = tilesAtElevation(i);
	//				//System.out.println("Total tiles at elevation = "+i+" is "+possibleTiles.size());
	//				if(i>3){
	//					addTileGroup("cliff", (possibleTiles.size()/10)*8, possibleTiles,1,false);
	//				}
	//				else{
	//					addTileGroup("cliff", -1, possibleTiles,1,false);
	//				}
	//				updateTiles();
	//
	//				removeOutcrops("cliff","cliff",i+1,i);
	//
	//
	//				//updateTiles();
	//			}
	//
	//
	//
	//			updateTiles();
	//			//add some grass
	//			ArrayList<Tile> possibleTiles = tilesAtElevation(1);
	//			System.out.println("possible tiles:"+possibleTiles.size());
	//			int numberOfPatches = GamePanel.randomNumber(6, 15,true);
	//			for(int i = 0; i<numberOfPatches;i++){
	//				addTileGroup("poke_grass",GamePanel.randomNumber(20, 100,true),possibleTiles,0,false);
	//			}
	//			updateTiles();
	//
	//
	//
	//		}
	//	}


	public void createSnakePathBetweenPoints(Point a, Point b){
		ArrayList<Point> points = new ArrayList<Point>();
		points.add(a);
		points.add(b);

		double distance = Math.sqrt(Math.pow((a.x-b.x),2)+Math.pow((a.y-b.y),2));
		//while the distance between two points in the path is greater than 4
		while(distance>4){

			for(int i = 1; i<points.size();i+=2){
				Point currentA = points.get(i);
				Point currentB = points.get(i-1);

				Point center = new Point((currentA.x+currentB.x)/2,(currentA.y+currentB.y)/2);

				double perpAngle = perpindicularAngle(currentA,currentB);
				double distanceFromCenter = (int)Math.sqrt(Math.pow((currentA.x-center.x),2)+Math.pow((currentA.y-center.y),2));

				if(Math.abs(distanceFromCenter)>Math.abs(currentA.x-currentB.x)){
					distanceFromCenter=Math.abs(currentA.x-currentB.x);
				}
				if(Math.abs(distanceFromCenter)>Math.abs(currentA.y-currentB.y)){
					distanceFromCenter=Math.abs(currentA.y-currentB.y);
				}

				int randomDist = GamePanel.randomNumber(0, (int)distanceFromCenter, true);
				double sign = 1;
				if(GamePanel.randomNumber(0, 1, true)==1){
					sign=-1;
				}
				double x = center.x+(Math.sin(perpAngle)*randomDist*sign);
				double y = center.y+(Math.cos(perpAngle)*randomDist*sign);

				Point newPos = new Point((int)x,(int)y);
				points.add(i,newPos);

				distance = Math.sqrt(Math.pow((currentA.x-currentB.x),2)+Math.pow((currentA.y-currentB.y),2));
			}

		}
		int radius = GamePanel.randomNumber(2, 5, true);
		for(int i = 1; i<points.size();i++){
			if(GamePanel.randomNumber(1, 100, true)<90){
				//generatePathBetweenPoints(int type, Point a, Point b, int pathRadius)
				generatePathBetweenPoints(0,points.get(i),points.get(i-1),radius);//ground
			}
			else{
				//generatePathBetweenPoints(int type, Point a, Point b, int pathRadius)
				if(GamePanel.randomNumber(1,2,true)==1)
					generatePathBetweenPoints(4,points.get(i),points.get(i-1),radius);//grass
				else
					generatePathBetweenPoints(3,points.get(i),points.get(i-1),radius);//water
			}
			if(radius>2&&radius<5) radius = GamePanel.randomNumber(radius-1, radius+1, true);
			else if(radius>2) radius = GamePanel.randomNumber(radius-1, radius, true);
			else if(radius<5) radius = GamePanel.randomNumber(radius, radius+1, true);
		}
	}
	//generate path between points
	public void generatePathBetweenPoints(int type, Point a, Point b, int pathRadius){
		//draw a line between these points
		Line2D line = new Line2D.Double(a.x*GamePanel.tileSize,a.y*GamePanel.tileSize,b.x*GamePanel.tileSize,b.y*GamePanel.tileSize);

		ArrayList<Point> linePoints = new ArrayList<Point>();
		for(int i = 0; i<areaWidth;i++){
			for(int j = 0; j<areaHeight;j++){
				int x = (position.x*areaWidth)+i;
				int y = (position.y*areaHeight)+j;
				if(GamePanel.tileMap[x][y]!=null){

					Rectangle rect = new Rectangle((x*GamePanel.tileSize),(y*GamePanel.tileSize),GamePanel.tileSize,GamePanel.tileSize);
					//if this tile is on the path
					if(line.intersects(rect)){

						linePoints.add(new Point(i, j));
						//loop through all nearby tiles
						for(int k = x-pathRadius;k<=x+pathRadius;k++){
							for(int l = y-pathRadius;l<=y+pathRadius;l++){
								//calculate the distance between this tile and the line point
								double distance = Math.sqrt(Math.pow((x-k),2)+Math.pow((y-l),2));
								//if the distance is within the path's radius (radius*2 = path width)
								if(distance<=pathRadius&&((type==3&&GamePanel.tileMap[k][l].hasBuilding==false)||(GamePanel.tileMap[k][l].id!=1&&GamePanel.tileMap[k][l].id!=4&&GamePanel.tileMap[k][l].id!=3))){


									GamePanel.tileMap[k][l].id=type;
									if(l%20==0&&k%5==0&&GamePanel.tileMap[k][l].id!=3)
										GamePanel.tileMap[k][l].id=6;
									int upperBound = 200;
									if(biome >= 4) upperBound = 60;
									if(GamePanel.randomNumber(1, upperBound, true) == 1){
										GamePanel.tileMap[k][l].hasBoulder=true;
									}


									//GamePanel.tileMap[k][l].elevation = elevation;
								}
								//for generating actual path tiles
								if(distance<=1&&type==0&&GamePanel.tileMap[k][l].id!=3&&GamePanel.tileMap[k][l].id!=1&&GamePanel.tileMap[k][l].id!=6){
									//GamePanel.tileMap[k][l].generateAsPassable = true;
									GamePanel.tileMap[k][l].id=1;
									GamePanel.tileMap[k][l].hasBoulder = false;
									//GamePanel.tileMap[k][l].elevation = elevation;
								}

							}
						}
					}
				}
			}
		}
	}

	public void findAllPaths(ArrayList <Point>path, Point current, Point destination){   

		Point temp = new Point(current.x,current.y);
		path.add(temp);
		//if the current position is the destination
		if(path.size() > 0&&temp.x==destination.x&&temp.y==destination.y){
			System.out.println("reached destination when finding paths! size of path is: "+path.size());
			ArrayList <Point> temperary = new ArrayList <Point>();
			for(int i = 0; i<path.size();i++){
				temperary.add(path.get(i));
			}
			if(temperary.size()<areaWidth){
				paths.add(temperary);
			}

		}
		else{
			if(path.size()<areaWidth){
				ArrayList<Point> available = getAvailableLocations(path);
				for(int i = 0; i<available.size();i++){
					findAllPaths(path, available.get(i),destination);
				}
			}

		}
		//path.remove(temp);

	}
	public void findAllPaths2(ArrayList <Point>path, Point node, Point destination)
	{   

		Point temp = new Point(node.y,node.x);
		path.add(temp);
		//System.out.println("path is visiting: "+node.x+","+node.y);
		if(path.size() > 0&&temp.x==destination.x&&temp.y==destination.y){//&&path.size()<=unit.moveRange
			//System.out.println("Added a path! This path is size: "+path.size());
			ArrayList <Point> temperary = new ArrayList <Point>();
			for(int i = 0; i<path.size();i++){
				temperary.add(path.get(i));
			}

			paths.add(temperary);
			//System.out.println(paths.size());
			//System.out.println("Paths: "+paths.toString());
			//System.out.println("Paths after adding: "+paths.toString());
			//System.out.println("number of paths:" +paths.size());
			//System.out.println("complete");
		}
		ArrayList<Point> available = getAvailableLocations(path);
		for(int i = 0; i<available.size();i++){
			if(path.size()<areaWidth*2) findAllPaths2(new ArrayList<Point>(path), available.get(i),destination);
		}
		path.remove(temp);

	}
	public ArrayList<Point> getAvailableLocations(ArrayList<Point> path){
		ArrayList<Point> positions = new ArrayList<Point>();
		Point pos = path.get(path.size()-1);
		for(int i = pos.x-1; i<=pos.x+1;i++){
			for(int j = pos.y-1; j<=pos.y+1;j++){
				//exclude corners
				if((i==pos.x||j==pos.y)&&!(i==pos.x&&j==pos.y)){
					//make sure not to go out of bounds of the tileMap
					if(i>=0&&i<=areaWidth-1&&j>=0&&j<=areaHeight-1){
						//if the point is not already in the path
						boolean found = false;
						for(int k = 0; k<path.size();k++){
							if(path.get(k).x==i&&path.get(k).y==j){
								found = true;
							}
						}
						if(!found){
							positions.add(new Point(i,j));
						}
					}
				}
			}
		}
		//System.out.println(positions.size());

		return positions;
	}

	public void DrawInterior(Graphics2D g) {
		System.out.println("drawing interior");
		for(int i = 0; i<areaWidth; i++){
			for(int j = 0; j<areaHeight;j++){
				tileMap[i][j].drawFirstLayer(g);
			}
		}
	}
	public void Draw(Graphics2D g) {
		if(areaType.equals("outside"))
			DrawOutside(g);
		else
			DrawInterior(g);
	}
	public void DrawOutside(Graphics2D g){
		int xViewDistance = (ApplicationUI.windowWidth/GamePanel.tileSize/2)+2;
		int yViewDistance = (ApplicationUI.windowHeight/GamePanel.tileSize/2)+2;

		for(int i = 0; i<areaWidth; i++){
			for(int j = 0; j<areaHeight;j++){
				if((position.x*areaWidth)+i>(GamePanel.player.xpos/GamePanel.tileSize)-xViewDistance&&(position.x*areaWidth)+i<(GamePanel.player.xpos/GamePanel.tileSize)+xViewDistance){
					if((position.y*areaHeight)+j>(GamePanel.player.ypos/GamePanel.tileSize)-yViewDistance&&(position.y*areaHeight)+j<(GamePanel.player.ypos/GamePanel.tileSize)+yViewDistance){
						if(GamePanel.tileMap[(position.x*areaWidth)+i][(position.y*areaHeight)+j]!=null){
							GamePanel.tileMap[(position.x*areaWidth)+i][(position.y*areaHeight)+j].drawFirstLayer(g);
						}
					}
				}
			}
		}
		for(int i = 0; i<exits.size();i++) {
			GamePanel.tileMap[(position.x*areaWidth)+exits.get(i).x][(position.y*areaHeight)+exits.get(i).y].flagged=true;
		}
		//draw overlay tiles
		for(int i = 0; i<areaWidth; i++){//for(int i = (GamePanel.player.xpos/GamePanel.tileSize)-xViewDistance; i<(GamePanel.player.xpos/GamePanel.tileSize)+xViewDistance;i++){
			for(int j = 0; j<areaHeight;j++){//for(int j = (GamePanel.player.ypos/GamePanel.tileSize)-yViewDistance; j<(GamePanel.player.ypos/GamePanel.tileSize)+yViewDistance;j++){
				if((position.x*areaWidth)+i>(GamePanel.player.xpos/GamePanel.tileSize)-xViewDistance&&(position.x*areaWidth)+i<(GamePanel.player.xpos/GamePanel.tileSize)+xViewDistance){
					if((position.y*areaHeight)+j>(GamePanel.player.ypos/GamePanel.tileSize)-yViewDistance&&(position.y*areaHeight)+j<(GamePanel.player.ypos/GamePanel.tileSize)+yViewDistance){
						if(GamePanel.tileMap[(position.x*areaWidth)+i][(position.y*areaHeight)+j]!=null){
							GamePanel.tileMap[(position.x*areaWidth)+i][(position.y*areaHeight)+j].drawSecondLayer(g);

							if(GamePanel.tileMap[(position.x*areaWidth)+i][(position.y*areaHeight)+j].animation!=null){
								GamePanel.tileMap[(position.x*areaWidth)+i][(position.y*areaHeight)+j].animation.Draw(g);
								if(GamePanel.tileMap[(position.x*areaWidth)+i][(position.y*areaHeight)+j].animation.done&&GamePanel.tileMap[position.x+i][position.y+j].id!=3){
									GamePanel.tileMap[(position.x*areaWidth)+i][(position.y*areaHeight)+j].animation=null;
								}
							}

							//chance to add a wandering pokemon
							if(GamePanel.tileMap[(position.x*areaWidth)+i][(position.y*areaHeight)+j].id==4){
								if(possibleShortGrassEncounters.size()>0&&npcs.size()<4&&GamePanel.randomNumber(1, 100000, false)==1){
									boolean npcAlreadyAtThisPosition = false;
									for(int k = 0; k<npcs.size();k++){
										if(npcs.get(k).xpos==((position.x*areaWidth)+i)*GamePanel.tileSize&&npcs.get(k).ypos==((position.y*areaHeight)+j-1)*GamePanel.tileSize){
											npcAlreadyAtThisPosition = true;
											break;
										}
									}
									if(!npcAlreadyAtThisPosition){
										int encounterLevel = GamePanel.currentArea.avgLevel+GamePanel.randomNumber(-2, 2, false);
										Pokemon wildPokemon = new Pokemon(possibleShortGrassEncounters.get(GamePanel.randomNumber(0,possibleShortGrassEncounters.size()-1,false)).x,encounterLevel);
										//Pokemon wildPokemon = new Pokemon(184,1);
										npcs.add(new NPC(-wildPokemon.ID,((position.x*areaWidth)+i)*GamePanel.tileSize,((position.y*areaHeight)+j-1)*GamePanel.tileSize,GamePanel.randomNumber(0, 3, false),wildPokemon.shiny==1));
									}
								}
							}

						}

					}
				}
			}
		}

		//show exit locations
		int xOffset = (ApplicationUI.windowWidth/2)-(GamePanel.tileSize/2);
		int yOffset = (ApplicationUI.windowHeight/2)-(GamePanel.tileSize/2);
		for(int i = 0; i<exits.size();i++){

			g.setColor(new Color(255,0,0,150));

			g.fillRect(((exits.get(i).x*(GamePanel.tileSize))-GamePanel.player.xpos)+xOffset, ((exits.get(i).y*(GamePanel.tileSize))-GamePanel.player.ypos)+yOffset, (GamePanel.tileSize), (GamePanel.tileSize));
		}
		for(int i = 0; i<npcs.size();i++){
			if(npcs.get(i).ypos<GamePanel.player.oldY){
				npcs.get(i).Draw(g);
			}
		}
		if(GamePanel.currentArea==this)
			GamePanel.player.Draw(g);
		for(int i = 0; i<npcs.size();i++){
			if(npcs.get(i).ypos>=GamePanel.player.oldY){
				npcs.get(i).Draw(g);
			}
		}

		//draw tree tops
		for(int i = 0; i<areaWidth; i++){//for(int i = (GamePanel.player.xpos/GamePanel.tileSize)-xViewDistance; i<(GamePanel.player.xpos/GamePanel.tileSize)+xViewDistance;i++){
			for(int j = 0; j<areaHeight; j++){//for(int j = (GamePanel.player.ypos/GamePanel.tileSize)-yViewDistance; j<(GamePanel.player.ypos/GamePanel.tileSize)+yViewDistance;j++){
				if((position.x*areaWidth)+i>(GamePanel.player.xpos/GamePanel.tileSize)-xViewDistance&&(position.x*areaWidth)+i<(GamePanel.player.xpos/GamePanel.tileSize)+xViewDistance){
					if((position.y*areaHeight)+j>(GamePanel.player.ypos/GamePanel.tileSize)-yViewDistance&&(position.y*areaHeight)+j<(GamePanel.player.ypos/GamePanel.tileSize)+yViewDistance){
						if(GamePanel.tileMap[(position.x*areaWidth)+i][(position.y*areaHeight)+j]!=null){
							GamePanel.tileMap[(position.x*areaWidth)+i][(position.y*areaHeight)+j].drawThirdLayer(g);
						}
					}
				}
			}
		}
	}
	public void DrawPossibleEncounters(Graphics2D g){
		FontMetrics m = g.getFontMetrics(boldFont);
		g.setColor(Color.BLACK);
		//draw pokemon that can be found in the area
		for(int i = 0; i<possibleShortGrassEncounters.size();i++){
			g.setFont(boldFont);
			g.drawImage(GamePanel.textures.portrait[0][0],10+(88*i),30,88,88,null);
			if(GamePanel.player.totalEncounters[GamePanel.possiblePokemon.get(possibleShortGrassEncounters.get(i).x-1).ID]==0){
				g.drawImage(GamePanel.possiblePokemon.get(possibleShortGrassEncounters.get(i).x-1).battleTexture[2][0],10+(88*i)+4,34,80,80,null);
				//draw name
				g.drawImage(GamePanel.textures.portrait[1][0],10+(88*i),118,88,88,null);
				int width = m.stringWidth("???");
				g.drawString("???", 10+(88*i)+44-(width/2),118+16);

			}
			else{
				g.drawImage(GamePanel.possiblePokemon.get(possibleShortGrassEncounters.get(i).x-1).battleTexture[0][0],10+(88*i)+4,34,80,80,null);
				//draw name
				g.drawImage(GamePanel.textures.portrait[1][0],10+(88*i),118,88,88,null);
				int width = m.stringWidth(GamePanel.possiblePokemon.get(possibleShortGrassEncounters.get(i).x-1).species);
				g.drawString(GamePanel.possiblePokemon.get(possibleShortGrassEncounters.get(i).x-1).species, 10+(88*i)+44-(width/2),118+16);
				g.setFont(font);
				g.drawString("#Seen: "+GamePanel.player.totalEncounters[GamePanel.possiblePokemon.get(possibleShortGrassEncounters.get(i).x-1).ID], 10+(88*i)+6,118+30);
			}

		}
	}
}
