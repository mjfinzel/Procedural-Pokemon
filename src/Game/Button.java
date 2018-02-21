package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Button {
	Point position = new Point(0,0);
	int buttonIndex = 0;
	String buttonType;
	Font font = new Font("Iwona Heavy",Font.PLAIN,18);
	public Button(int x, int y, String type){
		buttonType = type;
		position.x = x;
		position.y = y;
		if(buttonType.equalsIgnoreCase("Fight")){
			buttonIndex = 1;
		}
		else if(buttonType.equalsIgnoreCase("Pokemon")){
			buttonIndex = 2;
		}
		else if(buttonType.equalsIgnoreCase("Bag")){
			buttonIndex = 3;
		}
		else if(buttonType.equalsIgnoreCase("Run")){
			buttonIndex = 4;
		}
		else if(buttonType.equalsIgnoreCase("Call")){
			buttonIndex = 5;
		}
		else if(buttonType.equalsIgnoreCase("Ball")){
			buttonIndex = 6;
		}
		else if(buttonType.equalsIgnoreCase("Bait")){
			buttonIndex = 7;
		}
	}
	public void pressButton(){
		if(GamePanel.currentBattle!=null){
			if(buttonType.equalsIgnoreCase("Fight")){
				System.out.println("Pressed button named: "+buttonType);
				GamePanel.currentBattle.state=GamePanel.currentBattle.FIGHT;
				GamePanel.currentBattle.currentPlayerMove=GamePanel.currentBattle.playerPokemon.moves.get(0);
			}
			else if(buttonType.equalsIgnoreCase("Pokemon")){
				System.out.println("Pressed button named: "+buttonType);
				GamePanel.showPokemon = true;
			}
			else if(buttonType.equalsIgnoreCase("Bag")){
				System.out.println("Pressed button named: "+buttonType);
				GamePanel.showInventory = true;
			}
			else if(buttonType.equalsIgnoreCase("Run")){
				System.out.println("Pressed button named: "+buttonType);
				//Determine if you can escape
				int playerSpeed = GamePanel.currentBattle.playerPokemon.Speed;
				int foeSpeed = GamePanel.currentBattle.enemyPokemon.Speed;
				if(foeSpeed==0)foeSpeed=1;
				int f = (((playerSpeed*128)/foeSpeed)+30*GamePanel.currentBattle.runAttempts)%256;
				if(GamePanel.randomNumber(0, 255, false)<f){
					GamePanel.currentBattle.state=GamePanel.currentBattle.TEXT;
					GamePanel.currentBattle.messageID=GamePanel.currentBattle.GOTAWAY;
				}
				else{
					GamePanel.currentBattle.state=GamePanel.currentBattle.TEXT;
					GamePanel.currentBattle.messageID=GamePanel.currentBattle.CANTESCAPE;
				}
				GamePanel.currentBattle.runAttempts++;
			}
			else if(buttonType.equalsIgnoreCase("Call")){
				System.out.println("Pressed button named: "+buttonType);
			}
			else if(buttonType.equalsIgnoreCase("Ball")){
				System.out.println("Pressed button named: "+buttonType);
			}
			else if(buttonType.equalsIgnoreCase("Bait")){
				System.out.println("Pressed button named: "+buttonType);
			}
		}
		//if not in a battle
		else{
			if(buttonType.equalsIgnoreCase("Pokedex")){
				System.out.println(buttonType+" was pressed.");
			}
			else if(buttonType.equalsIgnoreCase("Pokemon")){
				System.out.println(buttonType+" was pressed.");
				GamePanel.showPokemon = true;
			}
			else if(buttonType.equalsIgnoreCase("Bag")){
				GamePanel.showInventory=true;
				GamePanel.showMenu = false;
			}
			else if(buttonType.equalsIgnoreCase("Pokegear")){
				System.out.println(buttonType+" was pressed.");
			}
			else if(buttonType.equalsIgnoreCase("PlayerName")){
				System.out.println(buttonType+" was pressed.");
			}
			else if(buttonType.equalsIgnoreCase("Save")){
				System.out.println(buttonType+" was pressed.");
			}
			else if(buttonType.equalsIgnoreCase("Options")){
				System.out.println(buttonType+" was pressed.");
			}
			else if(buttonType.equalsIgnoreCase("Exit")){
				System.out.println(buttonType+" was pressed.");
			}
		}
	}
	public void Draw(Graphics2D g){
		if(GamePanel.currentBattle!=null){
			g.drawImage(GamePanel.textures.battleButtonIcons[0][buttonIndex],position.x,position.y,null);
			if(GamePanel.currentBattle.currentButton==this){
				g.drawImage(GamePanel.textures.battleButtonIcons[0][0],position.x,position.y,null);
			}
		}
		else{
			g.setColor(new Color(30,30,60));
			g.fillRect(position.x-1, position.y-1, 192, 46);
			g.setFont(font);
			FontMetrics m = g.getFontMetrics(font);
			g.drawImage(GamePanel.textures.moveButtonIcons[0][1], position.x,position.y,null);
			g.setColor(Color.black);
			g.drawString(buttonType, position.x+95-(m.stringWidth(buttonType)/2), position.y+30);
			if(GamePanel.menu[GamePanel.currentButton]==this){
				g.drawImage(GamePanel.textures.moveButtonIcons[0][0], position.x,position.y,null);
			}
		}
	}
}
