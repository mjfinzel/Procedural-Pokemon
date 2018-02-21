package Game;

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Controller implements KeyListener,MouseListener,MouseMotionListener,MouseWheelListener,DragSourceListener{
	private JPanel gamePanel;
	static boolean mousePressed = false;
	static Point mousePosition = new Point(0,0);
	static Point oldMousePosition = new Point(0,0);

	static int updatesSinceLastPress = 0;

	private static boolean[] keyboardState = new boolean[525];
	private static boolean[] buttonReleasedSinceLastPress = new boolean[525];
	public static int currPok = 0;

	public static boolean keyboardKeyState(int key)
	{
		return keyboardState[key];
	}
	public static boolean buttonReleasedSinceLastPress(int key)
	{
		return buttonReleasedSinceLastPress[key];
	}
	public void setGamePanel(JPanel panelRef) {
		gamePanel = panelRef;
		gamePanel.addKeyListener(this);
		gamePanel.addMouseListener(this);
		gamePanel.addMouseMotionListener(this);
		gamePanel.addMouseWheelListener(this);
		for(int i = 0; i<buttonReleasedSinceLastPress.length;i++){
			buttonReleasedSinceLastPress[i]=true;
		}
	}
	public void mouseWheelMoved(MouseWheelEvent e) {

	}

	public void mouseDragged(MouseEvent e) {
		mousePosition.x = (int) e.getPoint().getX();
		mousePosition.y = (int) e.getPoint().getY();

	}

	public void mouseMoved(MouseEvent e) {
		mousePosition.x = (int) e.getPoint().getX();
		mousePosition.y = (int) e.getPoint().getY();
		// TODO Auto-generated method stub

	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		mousePressed = true;
		mousePosition.x = (int) e.getPoint().getX();
		mousePosition.y = (int) e.getPoint().getY();


	}

	public void mouseReleased(MouseEvent e) {

		mousePressed = false;

	}

	public void keyPressed(KeyEvent e) {
		keyboardState[e.getKeyCode()] = true;
		if(e.getKeyCode()==KeyEvent.VK_1){
			GamePanel.dialogBox.addMessage("If the target has an Ability that activates upon contact, each strike counts individually, enabling the Ability to activate multiple times. DoubleSlap may now continue attacking after breaking a substitute.");
		}
		if(e.getKeyCode()==KeyEvent.VK_2){
			if(GamePanel.getListOfAreas().size()>0){
				GamePanel.setCurrentArea(GamePanel.getListOfAreas().get(GamePanel.randomNumber(0, GamePanel.getListOfAreas().size()-1,false)));
			}
			else{
				System.out.println("ERROR! There were no other areas to switch to.");
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_3){
			ArrayList<Area> areas = GamePanel.getListOfAreas(); 
			if(areas.size()>0){
				ArrayList<Area> possibleAreas = new ArrayList<Area>();
				for(int i = 0; i<areas.size();i++){
					if(areas.get(i).avgLevel<=GamePanel.player.party.get(0).level+3&&areas.get(i).avgLevel>=GamePanel.player.party.get(0).level-3){
						possibleAreas.add(areas.get(i));
					}
				}
				GamePanel.setCurrentArea(possibleAreas.get(GamePanel.randomNumber(0, possibleAreas.size()-1,false)));
			}
			else{
				System.out.println("ERROR! There were no other areas to switch to.");
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_8){
			if(GamePanel.biome<5){
				GamePanel.biome++;
			}
			else{
				GamePanel.biome=0;
			}
			GamePanel.updateTiles();
		}
		if(keyboardKeyState(KeyEvent.VK_M)){

			if(GamePanel.drawMap==false){
				GamePanel.drawMap=true;
			}
			else{
				GamePanel.drawMap=false;
			}

		}
		if(keyboardKeyState(KeyEvent.VK_N)){

			if(GamePanel.showVisualMap==false){
				GamePanel.showVisualMap=true;
			}
			else{
				GamePanel.showVisualMap=false;
			}

		}
		if(e.getKeyCode()==KeyEvent.VK_G){
			if(GamePanel.godmode){
				GamePanel.godmode=false;
			}
			else{
				GamePanel.godmode=true;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_Z){
			if(GamePanel.tileSize==8){
				GamePanel.tileSize=16;
			}
			else if(GamePanel.tileSize==16){
				GamePanel.tileSize=32;
			}
			else if(GamePanel.tileSize==32){
				GamePanel.tileSize=8;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_9){
			if(GamePanel.currentBattle!=null){
				GamePanel.currentBattle.enemyPokemon.currentHP=1;
			}
		}
		if(keyboardKeyState(KeyEvent.VK_X)){
			if(GamePanel.currentBattle!=null){
				if(GamePanel.currentBattle.state==GamePanel.currentBattle.FIGHT){
					GamePanel.currentBattle.state=GamePanel.currentBattle.PICKOPTION;
				}
				if(GamePanel.showPokemon&&GamePanel.pokemonIsSelected) {
					GamePanel.pokemonIsSelected=false;
				}

			}
			else{
				if(GamePanel.showPokemon){
					GamePanel.showPokemon=false;
					GamePanel.showMenu=true;
				}
				else if(GamePanel.pokemonIsSelected){
					GamePanel.pokemonIsSelected=false;
					GamePanel.showPokemon=true;
					GamePanel.switchingPokemon=false;
					GamePanel.pokemonScreenSwitchSelectorPosition=0;
				}
				else if(GamePanel.showInventory){
					GamePanel.showInventory=false;
				}
				else if(GamePanel.showMenu){
					GamePanel.showMenu=false;
				}
			}
		}



	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		keyboardState[e.getKeyCode()] = false;
		buttonReleasedSinceLastPress[e.getKeyCode()]=true;
	}

	public void keyTyped(KeyEvent e) {


	}
	/*
	 * Constantly checks for changes in key state by being called by the game loop in applicationUI
	 */
	public static void checkKeys(){
		updatesSinceLastPress++;
		//check if mouse was pressed

		oldMousePosition.x = mousePosition.x;
		oldMousePosition.y = mousePosition.y;

		if (keyboardKeyState(KeyEvent.VK_B)){
			if(buttonReleasedSinceLastPress(KeyEvent.VK_B)&&GamePanel.currentArea.isTown==false){
				System.out.println(currPok);
				int encounterLevel = GamePanel.currentArea.avgLevel+GamePanel.randomNumber(-2, 2, false);
				//Pokemon wildPokemon = new Pokemon(GamePanel.currentArea.possibleShortGrassEncounters.get(GamePanel.randomNumber(0,GamePanel.currentArea.possibleShortGrassEncounters.size()-1,false)).x,encounterLevel);
				//Pokemon wildPokemon = new Pokemon(GamePanel.possiblePokemon.get(GamePanel.randomNumber(0,GamePanel.possiblePokemon.size()-1,false)).ID,1);
				Pokemon wildPokemon = new Pokemon(currPok+1,5);
				GamePanel.player.party.set(0,wildPokemon);
				if(currPok<7){
					currPok++;
				}
				else{
					currPok=0;
				}
				//Pokemon  wildPokemon = new Pokemon(GamePanel.randomNumber(1, 9, false),5);
				GamePanel.currentBattle = new Battle(GamePanel.player.party.get(0),wildPokemon);
				GamePanel.player.totalEncounters[wildPokemon.ID]++;
				GamePanel.totalWildEncounters++;
			}
			buttonReleasedSinceLastPress[KeyEvent.VK_B]=false;
		}
		if (keyboardKeyState(KeyEvent.VK_ENTER)){
			if(buttonReleasedSinceLastPress(KeyEvent.VK_ENTER)){
				if(!GamePanel.showMenu){
					GamePanel.showMenu=true;
				}
				else{
					GamePanel.showMenu = false;
				}
			}
			buttonReleasedSinceLastPress[KeyEvent.VK_ENTER]=false;
		}

		if(keyboardKeyState(KeyEvent.VK_LEFT)){
			if(GamePanel.showVisualMap) {
				GamePanel.mapPosition.x+=2;
			}
			else {
				if(GamePanel.showInventory){
					if(updatesSinceLastPress>=20){
						updatesSinceLastPress=0;
						if(GamePanel.player.inventory.currentMenu>0){
							GamePanel.player.inventory.currentMenu--;
						}
						else{
							GamePanel.player.inventory.currentMenu=5;
						}
					}
				}
				else if(GamePanel.currentBattle==null&&GamePanel.dialogBox.message==""&&!GamePanel.showPokemon){
					if(GamePanel.showMenu==false&&!GamePanel.showInventory){
						GamePanel.player.moveLeft();
					}
					else if(GamePanel.showInventory){

					}
				}
				else{
					if(GamePanel.currentBattle!=null){
						if(GamePanel.currentBattle.state==GamePanel.currentBattle.PICKOPTION){
							if(GamePanel.currentBattle.currentButton.buttonType.equalsIgnoreCase("Bag")){
								GamePanel.currentBattle.currentButton=GamePanel.currentBattle.pickOptionButtons[0][0];
							}
							if(GamePanel.currentBattle.currentButton.buttonType.equalsIgnoreCase("Run")){
								GamePanel.currentBattle.currentButton=GamePanel.currentBattle.pickOptionButtons[0][1];
							}
						}
						else if(GamePanel.currentBattle.state==GamePanel.currentBattle.FIGHT){
							if(GamePanel.currentBattle.playerPokemon.moves.size()>1){
								if(GamePanel.currentBattle.currentPlayerMove.equals(GamePanel.currentBattle.playerPokemon.moves.get(1))){
									GamePanel.currentBattle.currentPlayerMove = GamePanel.currentBattle.playerPokemon.moves.get(0);
								}
							}
							if(GamePanel.currentBattle.playerPokemon.moves.size()>3){
								if(GamePanel.currentBattle.currentPlayerMove.equals(GamePanel.currentBattle.playerPokemon.moves.get(3))){
									GamePanel.currentBattle.currentPlayerMove = GamePanel.currentBattle.playerPokemon.moves.get(2);
								}
							}
						}
					}
				}
			}
		}
		if(keyboardKeyState(KeyEvent.VK_RIGHT)){
			if(GamePanel.showVisualMap) {
				GamePanel.mapPosition.x-=2;
			}
			else {
				if(GamePanel.showInventory){
					if(updatesSinceLastPress>=20){
						updatesSinceLastPress=0;
						if(GamePanel.player.inventory.currentMenu<5){
							GamePanel.player.inventory.currentMenu++;
						}
						else{
							GamePanel.player.inventory.currentMenu=0;
						}
					}
				}
				else if(GamePanel.currentBattle==null&&GamePanel.dialogBox.message==""){
					if(GamePanel.showMenu==false&&!GamePanel.showInventory&&!GamePanel.showPokemon){
						GamePanel.player.moveRight();
					}
					else if(GamePanel.showInventory){

					}
				}
				else{
					if(GamePanel.currentBattle!=null){
						if(GamePanel.currentBattle.state==GamePanel.currentBattle.PICKOPTION){
							if(GamePanel.currentBattle.currentButton.buttonType.equalsIgnoreCase("Fight")){
								GamePanel.currentBattle.currentButton=GamePanel.currentBattle.pickOptionButtons[1][0];
							}
							if(GamePanel.currentBattle.currentButton.buttonType.equalsIgnoreCase("Pokemon")){
								GamePanel.currentBattle.currentButton=GamePanel.currentBattle.pickOptionButtons[1][1];
							}
						}
						else if(GamePanel.currentBattle.state==GamePanel.currentBattle.FIGHT){
							if(GamePanel.currentBattle.playerPokemon.moves.size()>1){
								if(GamePanel.currentBattle.currentPlayerMove.equals(GamePanel.currentBattle.playerPokemon.moves.get(0))){
									GamePanel.currentBattle.currentPlayerMove = GamePanel.currentBattle.playerPokemon.moves.get(1);
								}
							}
							if(GamePanel.currentBattle.playerPokemon.moves.size()>3){
								if(GamePanel.currentBattle.currentPlayerMove.equals(GamePanel.currentBattle.playerPokemon.moves.get(2))){
									GamePanel.currentBattle.currentPlayerMove = GamePanel.currentBattle.playerPokemon.moves.get(3);
								}
							}
						}
					}
				}
			}
		}
		if(keyboardKeyState(KeyEvent.VK_UP)){
			if(GamePanel.showVisualMap) {
				GamePanel.mapPosition.y+=2;
			}
			else {
				if(GamePanel.showInventory){
					if(updatesSinceLastPress>=20){
						updatesSinceLastPress=0;
						//generic items
						if(GamePanel.player.inventory.currentMenu==0){
							if(GamePanel.player.inventory.currentItem>0){
								GamePanel.player.inventory.currentItem--;
							}
							else{
								GamePanel.player.inventory.currentItem=GamePanel.player.inventory.items.size()-1;
							}
						}
						//medicine
						else if(GamePanel.player.inventory.currentMenu==1){
							if(GamePanel.player.inventory.currentMedicine>0){
								GamePanel.player.inventory.currentMedicine--;
							}
							else{
								GamePanel.player.inventory.currentMedicine=GamePanel.player.inventory.medicine.size()-1;
							}
						}
						//poke balls
						else if(GamePanel.player.inventory.currentMenu==2){
							if(GamePanel.player.inventory.currentPokeBall>0){
								GamePanel.player.inventory.currentPokeBall--;
							}
							else{
								GamePanel.player.inventory.currentPokeBall=GamePanel.player.inventory.pokeBalls.size()-1;
							}
						}
						//TMs & HMs
						else if(GamePanel.player.inventory.currentMenu==3){
							if(GamePanel.player.inventory.currentTMorHM>0){
								GamePanel.player.inventory.currentTMorHM--;
							}
							else{
								GamePanel.player.inventory.currentTMorHM=GamePanel.player.inventory.tmAndHM.size()-1;
							}
						}
						//Berries
						else if(GamePanel.player.inventory.currentMenu==4){
							if(GamePanel.player.inventory.currentBerry>0){
								GamePanel.player.inventory.currentBerry--;
							}
							else{
								GamePanel.player.inventory.currentBerry=GamePanel.player.inventory.berries.size()-1;
							}
						}
						//Key items
						else if(GamePanel.player.inventory.currentMenu==5){
							if(GamePanel.player.inventory.currentKeyItem>0){
								GamePanel.player.inventory.currentKeyItem--;
							}
							else{
								GamePanel.player.inventory.currentKeyItem=GamePanel.player.inventory.keyItems.size()-1;
							}
						}
					}
				}
				else if(GamePanel.showPokemon){
					if(updatesSinceLastPress>=20){
						updatesSinceLastPress=0;
						if(!GamePanel.pokemonIsSelected){
							System.out.println("1");
							if(GamePanel.pokemonScreenSelectorPosition>0){
								GamePanel.pokemonScreenSelectorPosition--;
							}
							else{
								GamePanel.pokemonScreenSelectorPosition = GamePanel.player.party.size()-1;
							}
						}
						else if(GamePanel.pokemonIsSelected&&!GamePanel.switchingPokemon){
							System.out.println("2");
							if(GamePanel.selectedOptionForSelectedPokemon>0){
								GamePanel.selectedOptionForSelectedPokemon--;
							}
							else{
								GamePanel.selectedOptionForSelectedPokemon = 3;
							}
						}
						else if(GamePanel.switchingPokemon){
							if(GamePanel.pokemonScreenSwitchSelectorPosition>0){
								GamePanel.pokemonScreenSwitchSelectorPosition--;
							}
							else{
								GamePanel.pokemonScreenSwitchSelectorPosition=GamePanel.player.party.size()-1;
							}
						}
					}
				}
				else if(GamePanel.currentBattle==null&&GamePanel.dialogBox.message==""){
					if(GamePanel.showMenu==false&&GamePanel.showPokemon==false&&!GamePanel.showInventory){
						GamePanel.player.moveUp();
					}
					else{
						if(updatesSinceLastPress>=10){
							updatesSinceLastPress=0;
							if(GamePanel.showInventory){

							}
							else if(GamePanel.showMenu){
								if(GamePanel.currentButton>0){
									GamePanel.currentButton--;
								}
								else{
									GamePanel.currentButton = GamePanel.menu.length-1;
								}
							}

						}
					}
				}
				else{
					if(GamePanel.currentBattle!=null){
						if(GamePanel.currentBattle.state==GamePanel.currentBattle.PICKOPTION){
							if(GamePanel.currentBattle.currentButton.buttonType.equalsIgnoreCase("Pokemon")){
								GamePanel.currentBattle.currentButton=GamePanel.currentBattle.pickOptionButtons[0][0];
							}
							if(GamePanel.currentBattle.currentButton.buttonType.equalsIgnoreCase("Run")){
								GamePanel.currentBattle.currentButton=GamePanel.currentBattle.pickOptionButtons[1][0];
							}
						}
						else if(GamePanel.currentBattle.state==GamePanel.currentBattle.FIGHT){
							if(GamePanel.currentBattle.playerPokemon.moves.size()>2){
								if(GamePanel.currentBattle.currentPlayerMove.equals(GamePanel.currentBattle.playerPokemon.moves.get(2))){
									GamePanel.currentBattle.currentPlayerMove = GamePanel.currentBattle.playerPokemon.moves.get(0);
								}
							}
							if(GamePanel.currentBattle.playerPokemon.moves.size()>3){
								if(GamePanel.currentBattle.currentPlayerMove.equals(GamePanel.currentBattle.playerPokemon.moves.get(3))){
									GamePanel.currentBattle.currentPlayerMove = GamePanel.currentBattle.playerPokemon.moves.get(1);
								}
							}
						}
					}
				}
			}
		}
		if(keyboardKeyState(KeyEvent.VK_DOWN)){
			if(GamePanel.showVisualMap) {
				GamePanel.mapPosition.y-=2;
			}
			else {
				if(GamePanel.showInventory){
					if(updatesSinceLastPress>=10){
						updatesSinceLastPress=0;
						//generic items
						if(GamePanel.player.inventory.currentMenu==0){
							if(GamePanel.player.inventory.currentItem<GamePanel.player.inventory.items.size()-1){
								GamePanel.player.inventory.currentItem++;
							}
							else{
								GamePanel.player.inventory.currentItem=0;
							}
						}
						//medicine
						else if(GamePanel.player.inventory.currentMenu==1){
							if(GamePanel.player.inventory.currentMedicine<GamePanel.player.inventory.medicine.size()-1){
								GamePanel.player.inventory.currentMedicine++;
							}
							else{
								GamePanel.player.inventory.currentMedicine=0;
							}
						}
						//poke balls
						else if(GamePanel.player.inventory.currentMenu==2){
							if(GamePanel.player.inventory.currentPokeBall<GamePanel.player.inventory.pokeBalls.size()-1){
								GamePanel.player.inventory.currentPokeBall++;
							}
							else{
								GamePanel.player.inventory.currentPokeBall=0;
							}
						}
						//TMs & HMs
						else if(GamePanel.player.inventory.currentMenu==3){
							if(GamePanel.player.inventory.currentTMorHM<GamePanel.player.inventory.tmAndHM.size()-1){
								GamePanel.player.inventory.currentTMorHM++;
							}
							else{
								GamePanel.player.inventory.currentTMorHM=0;
							}
						}
						//Berries
						else if(GamePanel.player.inventory.currentMenu==4){
							if(GamePanel.player.inventory.currentBerry<GamePanel.player.inventory.berries.size()-1){
								GamePanel.player.inventory.currentBerry++;
							}
							else{
								GamePanel.player.inventory.currentBerry=0;
							}
						}
						//Key items
						else if(GamePanel.player.inventory.currentMenu==5){
							if(GamePanel.player.inventory.currentKeyItem<GamePanel.player.inventory.keyItems.size()-1){
								GamePanel.player.inventory.currentKeyItem++;
							}
							else{
								GamePanel.player.inventory.currentKeyItem=0;;
							}
						}
					}
				}
				else if(GamePanel.showPokemon){
					if(updatesSinceLastPress>=20){
						updatesSinceLastPress=0;
						if(!GamePanel.pokemonIsSelected){
							if(GamePanel.pokemonScreenSelectorPosition<GamePanel.player.party.size()-1){
								GamePanel.pokemonScreenSelectorPosition++;
							}
							else{
								GamePanel.pokemonScreenSelectorPosition = 0;
							}
						}
						else if(GamePanel.pokemonIsSelected&&!GamePanel.switchingPokemon){
							if(GamePanel.selectedOptionForSelectedPokemon<3){
								GamePanel.selectedOptionForSelectedPokemon++;
							}
							else{
								GamePanel.selectedOptionForSelectedPokemon = 0;
							}
						}
						else if(GamePanel.switchingPokemon){
							if(GamePanel.pokemonScreenSwitchSelectorPosition<GamePanel.player.party.size()-1){
								GamePanel.pokemonScreenSwitchSelectorPosition++;
							}
							else{
								GamePanel.pokemonScreenSwitchSelectorPosition=0;
							}
						}
					}
				}
				else if(GamePanel.currentBattle==null&&GamePanel.dialogBox.message==""){
					if(GamePanel.showMenu==false&&GamePanel.showPokemon==false&&!GamePanel.showInventory){
						GamePanel.player.moveDown();
					}
					else{
						if(updatesSinceLastPress>=20){
							updatesSinceLastPress=0;
							if(GamePanel.showInventory){

							}
							if(GamePanel.showMenu){
								if(GamePanel.currentButton<GamePanel.menu.length-1){
									GamePanel.currentButton++;
								}
								else{
									GamePanel.currentButton = 0;
								}
							}

						}
					}
				}
				else{
					if(GamePanel.currentBattle!=null){
						if(GamePanel.currentBattle.state==GamePanel.currentBattle.PICKOPTION){
							if(GamePanel.currentBattle.currentButton.buttonType.equalsIgnoreCase("Fight")){
								GamePanel.currentBattle.currentButton=GamePanel.currentBattle.pickOptionButtons[0][1];
							}
							if(GamePanel.currentBattle.currentButton.buttonType.equalsIgnoreCase("Bag")){
								GamePanel.currentBattle.currentButton=GamePanel.currentBattle.pickOptionButtons[1][1];
							}
						}
						else if(GamePanel.currentBattle.state==GamePanel.currentBattle.FIGHT){
							if(GamePanel.currentBattle.playerPokemon.moves.size()>2){
								if(GamePanel.currentBattle.currentPlayerMove.equals(GamePanel.currentBattle.playerPokemon.moves.get(0))){
									GamePanel.currentBattle.currentPlayerMove = GamePanel.currentBattle.playerPokemon.moves.get(2);
								}
							}
							if(GamePanel.currentBattle.playerPokemon.moves.size()>3){
								if(GamePanel.currentBattle.currentPlayerMove.equals(GamePanel.currentBattle.playerPokemon.moves.get(1))){
									GamePanel.currentBattle.currentPlayerMove = GamePanel.currentBattle.playerPokemon.moves.get(3);
								}
							}
						}
					}
				}
			}
		}
		if(keyboardKeyState(KeyEvent.VK_C)){
			if(buttonReleasedSinceLastPress(KeyEvent.VK_C)){
				//System.out.println("Pressed C");
				if(GamePanel.showPokemon){
					if(!GamePanel.pokemonIsSelected){
						//if cancel button is selected
						if(GamePanel.pokemonScreenSelectorPosition==6){
							GamePanel.showPokemon=false;
							if(GamePanel.currentBattle==null){
								GamePanel.showMenu=true;
							}
							GamePanel.pokemonScreenSelectorPosition = 0;
						}
						else{
							GamePanel.pokemonIsSelected = true;
							GamePanel.selectedOptionForSelectedPokemon=0;
						}
					}
					else if(GamePanel.pokemonIsSelected){
						if(GamePanel.switchingPokemon){
							Pokemon temp = GamePanel.player.party.get(GamePanel.pokemonScreenSelectorPosition);
							GamePanel.player.party.set(GamePanel.pokemonScreenSelectorPosition,GamePanel.player.party.get(GamePanel.pokemonScreenSwitchSelectorPosition));
							GamePanel.player.party.set(GamePanel.pokemonScreenSwitchSelectorPosition, temp);
							GamePanel.switchingPokemon = false;
							GamePanel.pokemonIsSelected = false;
							GamePanel.pokemonScreenSwitchSelectorPosition = 0;
							if(GamePanel.currentBattle!=null){
								GamePanel.currentBattle.playerPokemon = GamePanel.player.party.get(0);
							}
						}
						else{//not switching pokemon
							if(GamePanel.selectedOptionForSelectedPokemon==0){

							}
							if(GamePanel.selectedOptionForSelectedPokemon==1){
								if(GamePanel.player.party.get(0).currentHP>0||GamePanel.currentBattle==null){
									GamePanel.switchingPokemon = true;
								}
								else{
									System.out.println("pressed send out");
									Pokemon temp = GamePanel.player.party.get(GamePanel.pokemonScreenSelectorPosition);
									if(temp.currentHP>0){//the pokemon being chosen to send out is not already feinted
										GamePanel.player.party.set(GamePanel.pokemonScreenSelectorPosition,GamePanel.player.party.get(0));
										GamePanel.player.party.set(0, temp);
										GamePanel.currentBattle.playerPokemon = GamePanel.player.party.get(0);
										GamePanel.currentBattle.state=GamePanel.currentBattle.PICKOPTION;
										GamePanel.showPokemon = false;
										GamePanel.switchingPokemon = false;
										GamePanel.pokemonIsSelected = false;
									}
									else{//selected pokemon has already feinted
										GamePanel.currentBattle.state=GamePanel.currentBattle.TEXT;
										GamePanel.currentBattle.messageID = GamePanel.currentBattle.ALREADYDEAD;
										GamePanel.switchingPokemon = false;
										GamePanel.pokemonIsSelected = false;
									}
								}
							}
							if(GamePanel.selectedOptionForSelectedPokemon==2){

							}
							if(GamePanel.selectedOptionForSelectedPokemon==3){//cancel
								GamePanel.pokemonIsSelected=false;
								GamePanel.showPokemon=true;
							}
						}
					}
				}
				else if(GamePanel.currentBattle!=null){
					int TEXT = GamePanel.currentBattle.TEXT;
					int APPEARED = GamePanel.currentBattle.APPEARED;
					int PICKOPTION = GamePanel.currentBattle.PICKOPTION;
					//if text is being displayed
					if(GamePanel.currentBattle.state==TEXT){
						if(GamePanel.currentBattle.messageID==APPEARED){
							GamePanel.currentBattle.state=PICKOPTION;
						}
						else if(GamePanel.currentBattle.messageID==GamePanel.currentBattle.GOTAWAY){
							GamePanel.currentBattle=null;
						}
						else if(GamePanel.currentBattle.messageID==GamePanel.currentBattle.CANTESCAPE){
							GamePanel.currentBattle.state=PICKOPTION;
						}
						else if(GamePanel.currentBattle.messageID==GamePanel.currentBattle.NOPP){
							GamePanel.currentBattle.state=PICKOPTION;
						}
						else if(GamePanel.currentBattle.messageID==GamePanel.currentBattle.ENEMYFAINTED){
							GamePanel.currentBattle.messageID=GamePanel.currentBattle.GAINEDXP;
						}
						else if(GamePanel.currentBattle.messageID==GamePanel.currentBattle.GAINEDXP){
							GamePanel.currentBattle.state=GamePanel.currentBattle.GAININGXP;
						}
						else if(GamePanel.currentBattle.messageID == GamePanel.currentBattle.SUPEREFFECTIVE||GamePanel.currentBattle.messageID == GamePanel.currentBattle.NOTVERYEFFECTIVE){
							if(GamePanel.currentBattle.state!=GamePanel.currentBattle.BATTLEOVER&&GamePanel.currentBattle.state!=GamePanel.currentBattle.GAININGXP&&GamePanel.currentBattle.messageID!=GamePanel.currentBattle.ENEMYFAINTED){
								if(GamePanel.currentBattle.playerTakeDamage==null){//the player is not currently taking damage
									//enemy pokemon should attack
									Pokemon enemy = GamePanel.currentBattle.enemyPokemon;
									Pokemon plrpkmn = GamePanel.currentBattle.playerPokemon;
									GamePanel.currentBattle.enemyPokemon.moves.get(GamePanel.randomNumber(0, GamePanel.currentBattle.enemyPokemon.moves.size()-1, false)).use(enemy, plrpkmn);
									GamePanel.currentBattle.delayCounter=90;
									GamePanel.currentBattle.state = PICKOPTION;
									System.out.println("Set state to PICKOPTION");
								}

							}
						}
						else if(GamePanel.currentBattle.messageID==GamePanel.currentBattle.PLAYERFAINTED){
							boolean allFeinted = true;
							for(int i = 0; i<GamePanel.player.party.size();i++){
								if(GamePanel.player.party.get(i).currentHP>0){
									allFeinted = false;
									break;
								}
							}
							if(!allFeinted){//if there is at least one pokemon in the party that hasn't feinted yet
								GamePanel.showPokemon = true;
							}
							else{//if all pokemon in the party have feinted
								GamePanel.currentBattle.messageID=GamePanel.currentBattle.NOPOKEMONLEFT;
							}

						}
						else if(GamePanel.currentBattle.messageID==GamePanel.currentBattle.NOPOKEMONLEFT){
							GamePanel.currentBattle.messageID=GamePanel.currentBattle.BLACKEDOUT;
						}
						else if(GamePanel.currentBattle.messageID==GamePanel.currentBattle.BLACKEDOUT){
							System.out.println("pressed c on blacked out message");
							//teleport to most recent town's poke center
							if(GamePanel.player.mostRecentTown!=null){
								GamePanel.setCurrentArea(GamePanel.player.mostRecentTown);
								GamePanel.player.xpos=GamePanel.player.mostRecentTown.pokecenterDoorPos.x*GamePanel.tileSize;
								GamePanel.player.ypos=(GamePanel.player.mostRecentTown.pokecenterDoorPos.y+1)*GamePanel.tileSize;
								GamePanel.player.nextPos = new Point(GamePanel.player.xpos,GamePanel.player.ypos);
								GamePanel.player.direction=0;

								GamePanel.currentBattle=null;
								for(int j = 0;j<GamePanel.player.party.size();j++){
									GamePanel.player.party.get(j).currentHP=GamePanel.player.party.get(j).HP;
								}
							}
							else{
								System.out.println("Teleporting to first town");
								ArrayList<Area> areas = GamePanel.getListOfAreas();
								for(int i  = 0; i<areas.size();i++){
									if(areas.get(i).isFirstTown){
										GamePanel.setCurrentArea(areas.get(i));
										GamePanel.player.xpos=areas.get(i).pokecenterDoorPos.x*GamePanel.tileSize;
										GamePanel.player.ypos=(areas.get(i).pokecenterDoorPos.y+1)*GamePanel.tileSize;
										GamePanel.player.nextPos = new Point(GamePanel.player.xpos,GamePanel.player.ypos);
										GamePanel.player.direction=0;

										GamePanel.currentBattle=null;
										for(int j = 0;j<GamePanel.player.party.size();j++){
											GamePanel.player.party.get(j).currentHP=GamePanel.player.party.get(j).HP;
										}
										break;
									}
								}

							}
						}
						else if(GamePanel.currentBattle.messageID==GamePanel.currentBattle.ZEROSHAKES){

						}
						else if(GamePanel.currentBattle.messageID==GamePanel.currentBattle.ONESHAKE){

						}
						else if(GamePanel.currentBattle.messageID==GamePanel.currentBattle.TWOSHAKES){

						}
						else if(GamePanel.currentBattle.messageID==GamePanel.currentBattle.THREESHAKES){

						}
						else if(GamePanel.currentBattle.messageID==GamePanel.currentBattle.CAUGHTPOKEMON){
							//add caught pokemon to party
							if(GamePanel.player.party.size()<6){
								GamePanel.player.party.add(GamePanel.currentBattle.enemyPokemon);
								GamePanel.currentBattle.state = GamePanel.currentBattle.NAMEPOKEMON;
							}
							//add caught pokemon to pc
							else{

							}
						}

					}
					else if(GamePanel.currentBattle.state==PICKOPTION){
						if(!GamePanel.showInventory){
							GamePanel.currentBattle.currentButton.pressButton();
						}
						else{
							GamePanel.showInventory=false;
							System.out.println("Selected: "+GamePanel.player.inventory.getCurrentItem().name);
							//use item
							GamePanel.player.inventory.getCurrentItem().useOnOpponent(GamePanel.currentBattle.enemyPokemon);
						}
					}
					else if(GamePanel.currentBattle.state==GamePanel.currentBattle.FIGHT){
						if(GamePanel.currentBattle.currentPlayerMove!=null){
							GamePanel.currentBattle.currentPlayerMove.use(GamePanel.currentBattle.playerPokemon, GamePanel.currentBattle.enemyPokemon);
						}
					}
				}
				else{
					if(GamePanel.showMenu){
						System.out.println("Pressed a button!");
						GamePanel.menu[GamePanel.currentButton].pressButton();
					}
					else{
						NPC npcBeingInteractedWith = GamePanel.player.FacingNPC();
						//if there is an npc in front of the player
						if(npcBeingInteractedWith!=null){
							//System.out.println("Pressed c on an npc with ID="+npcBeingInteractedWith.id);
							if(npcBeingInteractedWith.id<0){//the npc is a pokemon
								//remove the npc from the world
								GamePanel.currentArea.npcs.remove(npcBeingInteractedWith);

								int encounterLevel = GamePanel.currentArea.avgLevel+GamePanel.randomNumber(-2, 2, false);
								Pokemon wildPokemon = new Pokemon(npcBeingInteractedWith.id,encounterLevel);

								GamePanel.currentBattle = new Battle(GamePanel.player.party.get(0),wildPokemon);
								GamePanel.player.totalEncounters[wildPokemon.ID]++;
								GamePanel.totalWildEncounters++;
							}
							else{
								if(GamePanel.dialogBox.message==""){
									npcBeingInteractedWith.talk();
								}
								else{
									GamePanel.dialogBox.advanceMessage();
								}
							}
						}
					}


				}
			}
			buttonReleasedSinceLastPress[KeyEvent.VK_C]=false;
		}


	}

	public void dragDropEnd(DragSourceDropEvent arg0) {
		// TODO Auto-generated method stub

	}
	public void dragEnter(DragSourceDragEvent e) {
		// TODO Auto-generated method stub

	}
	public void dragExit(DragSourceEvent e) {
		// TODO Auto-generated method stub

	}
	public void dragOver(DragSourceDragEvent arg0) {
		// TODO Auto-generated method stub

	}
	public void dropActionChanged(DragSourceDragEvent arg0) {
		// TODO Auto-generated method stub

	}



}
