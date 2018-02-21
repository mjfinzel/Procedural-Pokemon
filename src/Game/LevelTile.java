package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class LevelTile {
	int biome = 3;
	int WALKABLE_GRASS = 0;
	int PATH = 1;
	int ROCK = 2;
	int WATER = 3;
	int SHORT_GRASS = 4;
	int BIG_TREE = 5;
	int LEDGE = 6;
	int FLOOR = 7;

	//coordinates of textures at each layer
	Point[]artCrd = new Point[3];

	int xpos = 0;
	int ypos = 0;
	int id = -1;
	int elevation = -1;
	int collisionType = 0;

	boolean isEntrance = false;
	boolean hasBuilding = false;
	boolean hasBoulder = false;
	boolean flagged = false;

	Animation animation;

	public LevelTile(int x, int y, int id, int elevation){
		this.id = id;
		this.xpos = x*GamePanel.tileSize;
		this.ypos = y*GamePanel.tileSize;
		this.elevation = elevation;

		initializeArtCrd();

	}
	public void initializeArtCrd() {
		artCrd[0] = new Point(0,0);
		artCrd[1] = new Point(-1,-1);
		artCrd[2] = new Point(-1,-1);
	}
	public void updateArt(Area area){
		/*
		 * Update the artwork on the tile to make it align properly with adjacent tiles
		 */
		boolean north = false;
		boolean south = false;
		boolean east = false;
		boolean west = false;
		boolean northwest = false;
		boolean southwest = false;
		boolean northeast = false;
		boolean southeast;
		if(area.areaType.equals("outside")) {
			north = GamePanel.tileMap[(xpos/GamePanel.tileSize)][(ypos/GamePanel.tileSize)-1].id!=id;
			south = GamePanel.tileMap[(xpos/GamePanel.tileSize)][(ypos/GamePanel.tileSize)+1].id!=id;
			east = GamePanel.tileMap[(xpos/GamePanel.tileSize)+1][(ypos/GamePanel.tileSize)].id!=id;
			west = GamePanel.tileMap[(xpos/GamePanel.tileSize)-1][(ypos/GamePanel.tileSize)].id!=id;
			northwest = GamePanel.tileMap[(xpos/GamePanel.tileSize)-1][(ypos/GamePanel.tileSize)-1].id!=id;
			southwest = GamePanel.tileMap[(xpos/GamePanel.tileSize)-1][(ypos/GamePanel.tileSize)+1].id!=id;
			northeast = GamePanel.tileMap[(xpos/GamePanel.tileSize)+1][(ypos/GamePanel.tileSize)-1].id!=id;
			southeast = GamePanel.tileMap[(xpos/GamePanel.tileSize)+1][(ypos/GamePanel.tileSize)+1].id!=id;
		}
		else {
			if((ypos/GamePanel.tileSize)-1>=0)
				north = area.tileMap[(xpos/GamePanel.tileSize)][(ypos/GamePanel.tileSize)-1].id!=id;
			if((ypos/GamePanel.tileSize)+1<area.areaWidth)
				south = area.tileMap[(xpos/GamePanel.tileSize)][(ypos/GamePanel.tileSize)+1].id!=id;
			east = area.tileMap[(xpos/GamePanel.tileSize)+1][(ypos/GamePanel.tileSize)].id!=id;
			west = area.tileMap[(xpos/GamePanel.tileSize)-1][(ypos/GamePanel.tileSize)].id!=id;
			northwest = area.tileMap[(xpos/GamePanel.tileSize)-1][(ypos/GamePanel.tileSize)-1].id!=id;
			southwest = area.tileMap[(xpos/GamePanel.tileSize)-1][(ypos/GamePanel.tileSize)+1].id!=id;
			northeast = area.tileMap[(xpos/GamePanel.tileSize)+1][(ypos/GamePanel.tileSize)-1].id!=id;
			southeast = area.tileMap[(xpos/GamePanel.tileSize)+1][(ypos/GamePanel.tileSize)+1].id!=id;
		}

		if(id == WALKABLE_GRASS){
			artCrd[0].x = 0;
			artCrd[0].y = GamePanel.randomNumber(0, 2, true);
			if(GamePanel.randomNumber(1, 200, true) == 1){
				artCrd[0].y = 3;
			}
			collisionType = 0;
		}
		else if(id == PATH){
			artCrd[0].x = 2;
			artCrd[0].y = 5;
			if(north && south && east && west){
				artCrd[0].x = 0;
				artCrd[0].y = 7;
			}
			else if(north && east && west){
				artCrd[0].x = 0;
				artCrd[0].y = 4;
			}
			else if(south && east && west){
				artCrd[0].x = 0;
				artCrd[0].y = 6;
			}
			else if(north && south && east){
				artCrd[0].x = 3;
				artCrd[0].y = 7;
			}
			else if(north && south && west){
				artCrd[0].x = 1;
				artCrd[0].y = 7;
			}
			else if(north && south){
				artCrd[0].x = 2;
				artCrd[0].y = 7;
			}
			else if(east && west){
				artCrd[0].x = 0;
				artCrd[0].y = 5;
			}
			else if(north && west){
				artCrd[0].x = 1;
				artCrd[0].y = 4;
			}
			else if(north && east){
				artCrd[0].x = 3;
				artCrd[0].y = 4;
			}
			else if(south && west){
				artCrd[0].x = 1;
				artCrd[0].y = 6;
			}
			else if(south && east){
				artCrd[0].x = 3;
				artCrd[0].y = 6;
			}
			else if(north){
				artCrd[0].x = 2;
				artCrd[0].y = 4;
			}
			else if(south){
				artCrd[0].x = 2;
				artCrd[0].y = 6;
			}
			else if(west){
				artCrd[0].x = 1;
				artCrd[0].y = 5;
			}
			else if(east){
				artCrd[0].x = 3;
				artCrd[0].y = 5;
			}
		}
		else if(id == ROCK){
			artCrd[0].x = 2;
			artCrd[0].y = 9;
		}
		else if (id == WATER){
			if(animation==null) {
				if(north&&south&&east&&west)
					animation = new Animation(xpos, ypos, 0, GamePanel.textures.waterAnim, 30);
				else if(north&&!south&&!east&&west)
					animation = new Animation(xpos, ypos, 1, GamePanel.textures.waterAnim, 30);
				else if(!north&&!south&&!east&&west)
					animation = new Animation(xpos, ypos, 2, GamePanel.textures.waterAnim, 30);
				else if(!north&&south&&!east&&west)
					animation = new Animation(xpos, ypos, 3, GamePanel.textures.waterAnim, 30);
				else if(north&&!south&&!east&&!west)
					animation = new Animation(xpos, ypos, 5, GamePanel.textures.waterAnim, 30);
				else if(!north&&south&&!east&&!west)
					animation = new Animation(xpos, ypos, 7, GamePanel.textures.waterAnim, 30);
				else if(north&&!south&&east&&!west)
					animation = new Animation(xpos, ypos, 9, GamePanel.textures.waterAnim, 30);
				else if(!north&&!south&&east&&!west)
					animation = new Animation(xpos, ypos, 10, GamePanel.textures.waterAnim, 30);
				else if(!north&&south&&east&&!west)
					animation = new Animation(xpos, ypos, 11, GamePanel.textures.waterAnim, 30);
				else if(!north&&!south&&!east&&!west&&!northeast&&!northwest&&!southeast&&southwest)
					animation = new Animation(xpos, ypos, 12, GamePanel.textures.waterAnim, 30);
				else if(!north&&!south&&!east&&!west&&!northeast&&northwest&&!southeast&&!southwest)
					animation = new Animation(xpos, ypos, 13, GamePanel.textures.waterAnim, 30);
				else if(!north&&!south&&!east&&!west&&!northeast&&!northwest&&southeast&&!southwest)
					animation = new Animation(xpos, ypos, 14, GamePanel.textures.waterAnim, 30);
				else if(!north&&!south&&!east&&!west&&northeast&&!northwest&&!southeast&&!southwest)
					animation = new Animation(xpos, ypos, 15, GamePanel.textures.waterAnim, 30);
				else
					animation = new Animation(xpos, ypos, 4, GamePanel.textures.waterAnim, 30);
			}

		}
		else if(id == SHORT_GRASS){
			artCrd[0].x = 3;
			artCrd[0].y = 3;
		}
		else if(id == BIG_TREE){
			collisionType = 1;
			//left
			if(xpos%(GamePanel.tileSize*2)==0){
				artCrd[0].x = 2;
			}
			//right
			else{
				artCrd[0].x = 3;
			}
			//top
			if(ypos%(GamePanel.tileSize*2)==0){
				artCrd[0].y = 1;
			}
			//bottom
			else{
				artCrd[0].y = 2;
			}
			//top left
			if(artCrd[0].x==2 && artCrd[0].y==1){
				if(east || south || southeast){
					id=0;
					updateArt(area);
				}
			}
			//top right
			else if(artCrd[0].x==3 && artCrd[0].y==1){
				if(west || south || southwest){
					id=0;
					updateArt(area);
				}
			}
			//bottom left
			if(artCrd[0].x==2 && artCrd[0].y==2){
				if(east || north || northeast){
					id=0;
					updateArt(area);
				}
			}
			//bottom right
			if(artCrd[0].x==3 && artCrd[0].y==2){
				if(west || north || northwest){
					id=0;
					updateArt(area);
				}
			}
		}
		else if(id==LEDGE) {
			if(north&&!south&&!east&&west)
				artCrd[1] = new Point(0,13);
			if(north&&south&&!east&&!west)
				artCrd[1] = new Point(1,13);
			if(north&&!south&&east&&!west)
				artCrd[1] = new Point(2,13);
			if(!north&&!south&&east&&west)
				artCrd[1] = new Point(0,14);
			if(!north&&!south&&east&&west)
				artCrd[1] = new Point(2,14);
			if(!north&&south&&east&&!west)
				artCrd[1] = new Point(0,15);
			if(!north&&south&&!east&&west)
				artCrd[1] = new Point(2,15);
		}
		else if(id==FLOOR) {
			artCrd[0] = new Point(0,25);
		}
		if(hasBoulder){
			artCrd[1].x = 0;
			artCrd[1].y = GamePanel.randomNumber(8, 12, true);
		}
		//Add treetops if needed
		if(GamePanel.tileMap[(xpos/GamePanel.tileSize)][(ypos/GamePanel.tileSize)+1].id==BIG_TREE && ypos%(GamePanel.tileSize*2)!=0 && biome!=5 && biome != 2){
			artCrd[2].y = 0;
			if(xpos%(GamePanel.tileSize*2)==0){//left side
				artCrd[2].x = 2;
			}
			else{//right side
				artCrd[2].x = 3;
			}
		}
		else{
			artCrd[2].x = -1;
			artCrd[2].y = -1;
		}

	}
	public void drawFirstLayer(Graphics2D g){
		int xOffset = (ApplicationUI.windowWidth/2)-(GamePanel.tileSize/2);
		int yOffset = (ApplicationUI.windowHeight/2)-(GamePanel.tileSize/2);
		BufferedImage[][] texture = GamePanel.textures.tileset;
		if(id==FLOOR) {
			texture = GamePanel.textures.interior;
		}
		if(animation==null) {
			g.drawImage(texture[(biome*4)+artCrd[0].x][artCrd[0].y], (xpos-GamePanel.player.xpos)+xOffset,(ypos-GamePanel.player.ypos)+yOffset, GamePanel.tileSize, GamePanel.tileSize, null);
		}
		else{
			g.drawImage(texture[(biome*4)][0], (xpos-GamePanel.player.xpos)+xOffset,(ypos-GamePanel.player.ypos)+yOffset, GamePanel.tileSize, GamePanel.tileSize, null);
			animation.position.x=(xpos-GamePanel.player.xpos)+xOffset;
			animation.position.y=(ypos-GamePanel.player.ypos)+yOffset;
			//System.out.println("drawing animation");
			animation.Draw(g);
			if(animation.done){
				if(this.id!=SHORT_GRASS) {
					animation.currentFrame=0;
					animation.totalUpdates=0;
					animation.done=false;
				}
				else {
					animation=null;
				}
			}
		}

		if(flagged) {
			g.setColor(Color.red);
			g.fillRect((xpos-GamePanel.player.xpos)+xOffset,(ypos-GamePanel.player.ypos)+yOffset, 16, 16);
		}
	}
	public void drawSecondLayer(Graphics2D g){
		if(artCrd[1].x>=0 && artCrd[1].y>=0){
			BufferedImage[][] texture = GamePanel.textures.tileset;
			int xOffset = (ApplicationUI.windowWidth/2)-(GamePanel.tileSize/2);
			int yOffset = (ApplicationUI.windowHeight/2)-(GamePanel.tileSize/2);
			g.drawImage(texture[(biome*4)+artCrd[1].x][artCrd[1].y], (xpos-GamePanel.player.xpos)+xOffset,(ypos-GamePanel.player.ypos)+yOffset, GamePanel.tileSize, GamePanel.tileSize, null);
			//g.drawString(id+"",(xpos-GamePanel.player.xpos)+xOffset,(ypos-GamePanel.player.ypos)+yOffset+12);
		}

	}
	public void drawThirdLayer(Graphics2D g){
		if(artCrd[2].x>=0 && artCrd[2].y>=0){
			BufferedImage[][] texture = GamePanel.textures.tileset;
			int xOffset = (ApplicationUI.windowWidth/2)-(GamePanel.tileSize/2);
			int yOffset = (ApplicationUI.windowHeight/2)-(GamePanel.tileSize/2);
			g.drawImage(texture[(biome*4)+artCrd[2].x][artCrd[2].y], (xpos-GamePanel.player.xpos)+xOffset,(ypos-GamePanel.player.ypos)+yOffset, GamePanel.tileSize, GamePanel.tileSize, null);

		}

	}

}
