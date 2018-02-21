package Game;

import java.awt.Graphics2D;

public class NPC {
	int xpos;
	int ypos;
	int id;
	int direction = 0;
	int updatesPerAction = 60;
	int updatesSinceLastAction = 0;
	String dialog = "ERROR: NPC DIALOG IS NULL!";
	boolean shiny = false;

	Animation anim = null;
	public NPC(int ID, int x, int y, int dir, boolean isShiny){
		id = ID;
		xpos = x;
		ypos = y;
		direction = dir;
		shiny = isShiny;
		updatesSinceLastAction = GamePanel.randomNumber(0, updatesPerAction, false);
		if(id >= 0){
			if(GamePanel.dialog.social.size()>0)
				dialog = GamePanel.dialog.social.remove(GamePanel.randomNumber(0, GamePanel.dialog.social.size()-1, true));
			else
				System.out.println("ERROR: NO DIALOG AVAILABLE FOR NPC!");
		}
	}
	public void update(){
		if(updatesSinceLastAction>=updatesPerAction){
			//50% chance to change direction
			if(GamePanel.randomNumber(1, 100, false)<=50){
				//pick a random direction
				direction = GamePanel.randomNumber(0, 3, false);
				//if standing on grass
				//if(GamePanel.tileMap[xpos/GamePanel.tileSize][ypos/GamePanel.tileSize].id==4){
				//	GamePanel.tileMap[ypos/GamePanel.tileSize][ypos/GamePanel.tileSize].animation=new Animation(xpos/GamePanel.tileSize, ypos/GamePanel.tileSize,0, GamePanel.textures.grassSteppedOn, 5);
				//}
			}
			updatesSinceLastAction = 0;
		}
		updatesSinceLastAction++;
	}
	public void talk(){
		GamePanel.dialogBox.addMessage(dialog);
	}
	public void Draw(Graphics2D g){
		//System.out.println("Drawing an npc with ID: "+id);
		update();
		int xOffset = (ApplicationUI.windowWidth/2)-(GamePanel.tileSize/2);
		int yOffset = (ApplicationUI.windowHeight/2)-(GamePanel.tileSize/2);
		if(anim!=null){
			anim.Draw(g);
		}
		else{
			if(id>=0){
				if(ypos<GamePanel.player.oldY){
					g.drawImage(GamePanel.textures.npcImages[id-1][0][direction],(xpos-GamePanel.player.xpos)+xOffset-7,(ypos-GamePanel.player.ypos)+yOffset, GamePanel.tileSize*2, GamePanel.tileSize*2,null);
				}
				else{
					g.drawImage(GamePanel.textures.npcImages[id-1][0][direction],(xpos-GamePanel.player.oldX)+xOffset-7,(ypos-GamePanel.player.oldY)+yOffset, GamePanel.tileSize*2, GamePanel.tileSize*2,null);
				}
			}
			else{
				if(shiny==false){
					if(ypos<GamePanel.player.oldY){
						g.drawImage(GamePanel.textures.followers[-(id)][0][direction],(xpos-GamePanel.player.xpos)+xOffset-7,((ypos-GamePanel.player.ypos)+yOffset),GamePanel.tileSize*2,GamePanel.tileSize*2,null);
					}
					else{
						g.drawImage(GamePanel.textures.followers[-(id)][0][direction],(xpos-GamePanel.player.oldX)+xOffset-7,((ypos-GamePanel.player.oldY)+yOffset),GamePanel.tileSize*2,GamePanel.tileSize*2,null);
					}
				}
				else{
					if(ypos<GamePanel.player.oldY){
						g.drawImage(GamePanel.textures.followersShiny[-(id)][0][direction],(xpos-GamePanel.player.xpos)+xOffset-7,((ypos-GamePanel.player.ypos)+yOffset),GamePanel.tileSize*2,GamePanel.tileSize*2,null);
					}
					else{
						g.drawImage(GamePanel.textures.followersShiny[-(id)][0][direction],(xpos-GamePanel.player.oldX)+xOffset-7,((ypos-GamePanel.player.oldY)+yOffset),GamePanel.tileSize*2,GamePanel.tileSize*2,null);
					}
				}
			}
		}
	}
}
