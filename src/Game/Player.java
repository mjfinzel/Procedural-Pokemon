package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class Player {
	int direction = 0;

	int xpos = GamePanel.tileSize*50;
	int ypos = GamePanel.tileSize*50;
	int oldX=xpos;
	int oldY=ypos;
	int width = GamePanel.tileSize*2;
	int height = GamePanel.tileSize*2;
	double xposDecimal = xpos;
	double yposDecimal = ypos;
	Point nextPos = new Point(0,0);
	int updatesSinceLastMove = 0;
	int updatesPerMove = 20;
	boolean movingUp = false;
	boolean movingDown = false;
	boolean movingLeft = false;
	boolean movingRight = false;
	boolean outside = true;//whether or not the player is in a building

	String name = "Player";
	ArrayList<Pokemon> party = new ArrayList<Pokemon>();
	Inventory inventory = new Inventory();
	int[] totalEncounters = new int[1000];
	Area mostRecentTown = null;

	public Player(String name){
		int[] possibleStarters1 = {1,4,7};
		int[] possibleStarters2 = {152,155,158};
		int[] possibleStarters3 = {252,255,258};
		party.add(new Pokemon(possibleStarters1[GamePanel.randomNumber(0, possibleStarters1.length-1, false)],5));
		party.add(new Pokemon(possibleStarters2[GamePanel.randomNumber(0, possibleStarters2.length-1, false)],5));
		party.add(new Pokemon(possibleStarters3[GamePanel.randomNumber(0, possibleStarters3.length-1, false)],5));
		party.add(new Pokemon(GamePanel.shortGrassPokemon.get(GamePanel.randomNumber(0,GamePanel.shortGrassPokemon.size()-1, false)).x,GamePanel.randomNumber(2, 7, false)));
		//party.add(new Pokemon(GamePanel.tallGrassPokemon.get(GamePanel.randomNumber(0,GamePanel.tallGrassPokemon.size()-1, false)).x,GamePanel.randomNumber(2, 7, false)));
		//party.add(new Pokemon(GamePanel.cavePokemon.get(GamePanel.randomNumber(0,GamePanel.cavePokemon.size()-1, false)).x,GamePanel.randomNumber(2, 7, false)));
		//party.add(new Pokemon(GamePanel.goodRodPokemon.get(GamePanel.randomNumber(0,GamePanel.goodRodPokemon.size()-1, false)).x,GamePanel.randomNumber(2, 7, false)));
		//party.add(new Pokemon(GamePanel.shortGrassPokemon.get(GamePanel.randomNumber(0,GamePanel.shortGrassPokemon.size()-1, false)).x,GamePanel.randomNumber(2, 7, false)));

		inventory.addItem("Repel", 1);
		inventory.addItem("Potion", 3);
		inventory.addItem("Poke Ball", 105);
		inventory.addItem("Great Ball", 37);
		inventory.addItem("Ultra Ball", 9);
		inventory.addItem("HM03 Surf", 1);
		//add berries
		for(int i = 0; i<inventory.possibleBerries.size();i++){
			inventory.addItem(inventory.possibleBerries.get(i), GamePanel.randomNumber(1, 99, false));
		}
		inventory.addItem("Old Rod", 1);
	}
	public void moveUp(){
		if((ypos/GamePanel.tileSize)-1>0){
			if(!movingLeft&&!movingRight&&!movingDown&&!movingUp)
				direction = 3;
			if(GamePanel.currentArea.areaType.equals("outside")) {
				if(GamePanel.godmode==false){
					LevelTile tileBeingEntered = GamePanel.tileMap[(xpos/GamePanel.tileSize)][(ypos/GamePanel.tileSize)-1];
					//check if tileBeingEntered is a door
					if(tileBeingEntered.isEntrance){

					}
					
					else if(GamePanel.tileMap[(xpos/GamePanel.tileSize)][(ypos/GamePanel.tileSize)-1].elevation==GamePanel.tileMap[xpos/GamePanel.tileSize][ypos/GamePanel.tileSize].elevation){
						if((GamePanel.tileMap[(xpos/GamePanel.tileSize)][(ypos/GamePanel.tileSize)-1].hasBuilding==false||(tileBeingEntered.artCrd[1].x==4&&tileBeingEntered.artCrd[1].y==22))&&GamePanel.tileMap[(xpos/GamePanel.tileSize)][(ypos/GamePanel.tileSize)-1].collisionType==0){
							if(!npcAtPosition((xpos/GamePanel.tileSize),(ypos/GamePanel.tileSize)-2)){
								if(!movingLeft&&!movingRight&&!movingDown&&!movingUp){

									movingUp=true;
									nextPos = new Point(xpos,ypos-GamePanel.tileSize);
									updatesSinceLastMove=0;
								}
							}
						}
					}
				}
				else{
					ypos-=GamePanel.tileSize;
					yposDecimal = ypos;
				}
			

				GamePanel.currentArea = GamePanel.areas[(nextPos.x/GamePanel.tileSize)/GamePanel.areaWidth][(nextPos.y/GamePanel.tileSize)/GamePanel.areaHeight];
			}
		}
	}
	public void moveDown(){
		if((ypos/GamePanel.tileSize)+1<GamePanel.worldHeight*GamePanel.areaHeight*GamePanel.tileSize){
			//			if((ypos/GamePanel.tileSize)+1>(GamePanel.currentArea.position.y*GamePanel.areaHeight)+GamePanel.areaHeight-1){
			//				System.out.println("Transitioning to new area");
			//				Point newPos = GamePanel.currentArea.position;
			//				GamePanel.currentArea = GamePanel.areas[newPos.x][newPos.y+1];
			//			}
			if(!movingLeft&&!movingRight&&!movingDown&&!movingUp)
				direction = 0;
			if(GamePanel.currentArea.areaType.equals("outside")) {
				if(GamePanel.godmode==false){

					if(GamePanel.tileMap[(xpos/GamePanel.tileSize)][(ypos/GamePanel.tileSize)+1].elevation==GamePanel.tileMap[xpos/GamePanel.tileSize][ypos/GamePanel.tileSize].elevation){
						if(GamePanel.tileMap[(xpos/GamePanel.tileSize)][(ypos/GamePanel.tileSize)+1].hasBuilding==false&&GamePanel.tileMap[(xpos/GamePanel.tileSize)][(ypos/GamePanel.tileSize)+1].collisionType==0){
							if(!npcAtPosition((xpos/GamePanel.tileSize),(ypos/GamePanel.tileSize))){
								if(!movingUp&&!movingLeft&&!movingRight&&!movingDown){

									movingDown = true;
									nextPos = new Point(xpos,ypos+GamePanel.tileSize);
									updatesSinceLastMove=0;
								}
							}
						}
					}
				}
				else{
					ypos+=GamePanel.tileSize;
					yposDecimal = ypos;
				}
			}
			else{
				System.out.println("tried to walk off of the bottom of the world");
			}

			GamePanel.currentArea = GamePanel.areas[(nextPos.x/GamePanel.tileSize)/GamePanel.areaWidth][(nextPos.y/GamePanel.tileSize)/GamePanel.areaHeight];
		}
	}
	public void moveLeft(){
		if((xpos/GamePanel.tileSize)-1>0){
			if(!movingLeft&&!movingRight&&!movingDown&&!movingUp)
				direction = 1;
			if(GamePanel.currentArea.areaType.equals("outside")) {
				if(GamePanel.godmode==false){
					if(GamePanel.tileMap[(xpos/GamePanel.tileSize)-1][(ypos/GamePanel.tileSize)].elevation==GamePanel.tileMap[xpos/GamePanel.tileSize][ypos/GamePanel.tileSize].elevation){
						if(GamePanel.tileMap[(xpos/GamePanel.tileSize)-1][(ypos/GamePanel.tileSize)].hasBuilding==false&&GamePanel.tileMap[(xpos/GamePanel.tileSize)-1][(ypos/GamePanel.tileSize)].collisionType==0){
							//can't walk onto a tile that is already occupied by an npc
							if(!npcAtPosition((xpos/GamePanel.tileSize)-1,(ypos/GamePanel.tileSize)-1)){
								if(!movingUp&&!movingDown&&!movingRight&&!movingLeft){

									movingLeft = true;
									nextPos = new Point(xpos-GamePanel.tileSize,ypos);
									updatesSinceLastMove=0;
								}
							}
						}
					}
				}
				else{
					xpos-=GamePanel.tileSize;
					xposDecimal = xpos;
				}
			}

			GamePanel.currentArea = GamePanel.areas[(nextPos.x/GamePanel.tileSize)/GamePanel.areaWidth][(nextPos.y/GamePanel.tileSize)/GamePanel.areaHeight];
		}
	}
	public void moveRight(){
		if(!movingLeft&&!movingRight&&!movingDown&&!movingUp)
			direction = 2;
		if(GamePanel.currentArea.areaType.equals("outside")) {
			if(GamePanel.godmode==false){
				if(GamePanel.tileMap[(xpos/GamePanel.tileSize)+1][(ypos/GamePanel.tileSize)].elevation==GamePanel.tileMap[xpos/GamePanel.tileSize][ypos/GamePanel.tileSize].elevation){
					if(GamePanel.tileMap[(xpos/GamePanel.tileSize)+1][(ypos/GamePanel.tileSize)].hasBuilding==false&&GamePanel.tileMap[(xpos/GamePanel.tileSize)+1][(ypos/GamePanel.tileSize)].collisionType==0){
						//can't walk onto a tile that is already occupied by an npc
						if(!npcAtPosition((xpos/GamePanel.tileSize)+1,(ypos/GamePanel.tileSize)-1)){
							if(!movingUp&&!movingLeft&&!movingDown&&!movingRight){

								movingRight = true;
								nextPos = new Point(xpos+GamePanel.tileSize,ypos);
								updatesSinceLastMove=0;
							}
						}
					}
				}
			}
			else{
				xpos+=GamePanel.tileSize;
				xposDecimal = xpos;
			}

			GamePanel.currentArea = GamePanel.areas[(nextPos.x/GamePanel.tileSize)/GamePanel.areaWidth][(nextPos.y/GamePanel.tileSize)/GamePanel.areaHeight];
		}
	}
	public boolean npcAtPosition(int x, int y){
		for(int i = 0; i<GamePanel.currentArea.npcs.size();i++){
			if(GamePanel.currentArea.npcs.get(i).xpos/GamePanel.tileSize==x){
				if(GamePanel.currentArea.npcs.get(i).ypos/GamePanel.tileSize==y){
					//System.out.println("npc at pos");
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 
	 * @return The NPC the player is facing. If the player is not facing an NPC then this returns null
	 */
	public NPC FacingNPC(){
		if(direction==0){//player is looking south
			for(int i = 0; i<GamePanel.currentArea.npcs.size();i++){
				if(GamePanel.currentArea.npcs.get(i).xpos/GamePanel.tileSize==(xpos/GamePanel.tileSize)){
					if(GamePanel.currentArea.npcs.get(i).ypos/GamePanel.tileSize==(ypos/GamePanel.tileSize)){
						return GamePanel.currentArea.npcs.get(i);
					}
				}
			}
		}
		else if(direction==1){//player is looking west
			for(int i = 0; i<GamePanel.currentArea.npcs.size();i++){
				if(GamePanel.currentArea.npcs.get(i).xpos/GamePanel.tileSize==(xpos/GamePanel.tileSize)-1){
					if(GamePanel.currentArea.npcs.get(i).ypos/GamePanel.tileSize==(ypos/GamePanel.tileSize)-1){
						return GamePanel.currentArea.npcs.get(i);
					}
				}
			}
		}
		if(direction==2){//player is looking east
			for(int i = 0; i<GamePanel.currentArea.npcs.size();i++){
				if(GamePanel.currentArea.npcs.get(i).xpos/GamePanel.tileSize==(xpos/GamePanel.tileSize)+1){
					if(GamePanel.currentArea.npcs.get(i).ypos/GamePanel.tileSize==(ypos/GamePanel.tileSize)-1){
						return GamePanel.currentArea.npcs.get(i);
					}
				}
			}
		}
		if(direction==3){//player is looking south
			for(int i = 0; i<GamePanel.currentArea.npcs.size();i++){
				if(GamePanel.currentArea.npcs.get(i).xpos/GamePanel.tileSize==(xpos/GamePanel.tileSize)){
					if(GamePanel.currentArea.npcs.get(i).ypos/GamePanel.tileSize==(ypos/GamePanel.tileSize)-2){
						return GamePanel.currentArea.npcs.get(i);
					}
				}
			}
		}
		return null;
	}
	public void checkForGrass(){
		if(GamePanel.tileMap[nextPos.x/GamePanel.tileSize][nextPos.y/GamePanel.tileSize].id==4){
			//GamePanel.currentArea.tileMap[nextPos.x/GamePanel.tileSize][nextPos.y/GamePanel.tileSize].animation=new Animation(nextPos.x/GamePanel.tileSize, nextPos.y/GamePanel.tileSize, GamePanel.textures.grassSteppedOn, updatesPerMove/4);
			//wild encounter
			if(!GamePanel.currentArea.isTown&&GamePanel.randomNumber(1, 100, false)<=16){
				int encounterLevel = GamePanel.currentArea.avgLevel+GamePanel.randomNumber(-2, 2, false);
				Pokemon wildPokemon = new Pokemon(GamePanel.currentArea.possibleShortGrassEncounters.get(GamePanel.randomNumber(0,GamePanel.currentArea.possibleShortGrassEncounters.size()-1,false)).x,encounterLevel);

				GamePanel.currentBattle = new Battle(GamePanel.player.party.get(0),wildPokemon);
				GamePanel.player.totalEncounters[wildPokemon.ID]++;
				GamePanel.totalWildEncounters++;
			}
		}
	}
	public void update(){
		oldX=xpos;
		oldY=ypos;
		if(updatesSinceLastMove<updatesPerMove&&(movingUp||movingDown||movingLeft||movingRight)){
			if(updatesSinceLastMove==0){
				LevelTile current = GamePanel.tileMap[nextPos.x/GamePanel.tileSize][nextPos.y/GamePanel.tileSize];
				//grass
				if(current.id==4){
					current.animation=new Animation(nextPos.x, nextPos.y, current.biome, GamePanel.textures.grassSteppedOn, updatesPerMove);
				}
			}
			if(updatesSinceLastMove==updatesPerMove/2){
				checkForGrass();
			}
			//updatesSinceLastMove++;
		}
		if(movingUp){
			yposDecimal-=(((double)GamePanel.tileSize)/((double)updatesPerMove));
			ypos = (int)yposDecimal;
			if(updatesSinceLastMove==updatesPerMove){
				movingUp=false;
				updatesSinceLastMove=0;
				xpos = nextPos.x;
				ypos = nextPos.y;
				xposDecimal = xpos;
				yposDecimal = ypos;
				LevelTile tileBeingEntered = GamePanel.tileMap[(xpos/GamePanel.tileSize)][(ypos/GamePanel.tileSize)];
				if(tileBeingEntered.artCrd[1].x==4&&tileBeingEntered.artCrd[1].y==22) {//lab
					System.out.println("Entering lab");
					GamePanel.setCurrentArea(GamePanel.currentArea.getBuildingByName("Lab"));
					return;
				}
			}
			updatesSinceLastMove++;
		}
		if(movingDown){
			yposDecimal+=(((double)GamePanel.tileSize)/((double)updatesPerMove));
			ypos = (int)yposDecimal;
			if(updatesSinceLastMove==updatesPerMove){
				movingDown=false;
				updatesSinceLastMove=0;
				xpos = nextPos.x;
				ypos = nextPos.y;
				xposDecimal = xpos;
				yposDecimal = ypos;
			}
			updatesSinceLastMove++;
		}
		if(movingLeft){
			xposDecimal-=(((double)GamePanel.tileSize)/((double)updatesPerMove));
			xpos = (int)xposDecimal;
			if(updatesSinceLastMove==updatesPerMove){
				movingLeft=false;
				updatesSinceLastMove=0;
				xpos = nextPos.x;
				ypos = nextPos.y;
				xposDecimal = xpos;
				yposDecimal = ypos;
			}
			updatesSinceLastMove++;
		}
		if(movingRight){
			xposDecimal+=(((double)GamePanel.tileSize)/((double)updatesPerMove));
			xpos = (int)xposDecimal;
			if(updatesSinceLastMove==updatesPerMove){
				movingRight=false;
				updatesSinceLastMove=0;
				xpos = nextPos.x;
				ypos = nextPos.y;
				xposDecimal = xpos;
				yposDecimal = ypos;
			}
			updatesSinceLastMove++;
		}

	}
	public void Draw(Graphics2D g){
		//update();
		//g.setColor(Color.pink);
		//g.fillRect((ApplicationUI.windowWidth/2)-(GamePanel.tileSize/2) , (ApplicationUI.windowHeight/2)-(GamePanel.tileSize/2) , GamePanel.tileSize, GamePanel.tileSize);
		//g.setColor(Color.blue);
		//g.drawRect((ApplicationUI.windowWidth/2)-(GamePanel.tileSize/2) , (ApplicationUI.windowHeight/2)-(GamePanel.tileSize/2) , GamePanel.tileSize, GamePanel.tileSize);
		int currentFrame = (int)((double)((double)updatesSinceLastMove/(double)updatesPerMove)*3.99);
		//		if(currentFrame==4){
		//			currentFrame=0;
		//		}
		g.drawImage(GamePanel.playerImage[currentFrame][direction],(ApplicationUI.windowWidth/2)-(width/2)+1,(ApplicationUI.windowHeight/2)-(height/2)-(GamePanel.tileSize/2),GamePanel.tileSize*2,GamePanel.tileSize*2,null);

	}
}
