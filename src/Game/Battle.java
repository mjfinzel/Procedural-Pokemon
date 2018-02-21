package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Battle {
	Pokemon playerPokemon;
	Pokemon enemyPokemon;
	ArrayList<Animation> animations = new ArrayList<Animation>();
	Animation enemyTakeDamage;
	Animation playerTakeDamage;

	int width = 512;
	int height = 384;
	Font font = new Font("Iwona Heavy",Font.PLAIN,18);

	int state = 0;
	int messageID;

	int TEXT = 0;
	int PICKOPTION = 1;
	int FIGHT = 2;
	int GAININGXP = 3;
	int BATTLEOVER = 4;

	int APPEARED = 0;
	int GOTAWAY = 1;
	int CANTESCAPE = 2;
	int NOPP = 3;
	int PLAYERUSEDMOVE = 4;
	int ENEMYFAINTED = 5;
	int GAINEDXP = 6;
	int SUPEREFFECTIVE = 7;
	int NOTVERYEFFECTIVE = 8;
	int ENEMYUSEDMOVE = 9;
	int PLAYERFAINTED = 10;
	int ALREADYDEAD = 11;
	int NOPOKEMONLEFT = 12;
	int BLACKEDOUT = 13;
	int ZEROSHAKES = 14;
	int ONESHAKE = 15;
	int TWOSHAKES = 16;
	int THREESHAKES = 17;
	int CAUGHTPOKEMON = 18;
	int NAMEPOKEMON = 19;

	int runAttempts = 0;

	Button[][] pickOptionButtons = new Button[2][2];
	Button currentButton;
	Move currentPlayerMove;
	Move currentEnemyMove;
	String oldText = "";

	int updatesPerBounce = 40;
	int updatesSinceLastBounce = 0;
	int updatesSinceMoveUsed = 0;
	int xpAdded = 0;
	int updatesSinceXPAdded = 0;

	int enemyPokemonX = -160;

	int updatesSinceBattleStart = 0;

	Point position = new Point();
	int delayCounter = 0;

	Animation pokeBallThrowAnim = null;
	int[] pokeBallThrowOffsetCoordinates = {
			150,145,141,137,133,129,126,123,120,117,
			114,111,108,105,101,97,93,90,87,84,82,
			80,78,76,74,72,70,68,66,64,62,60,58,56,
			54,52,50,48,46,45,44,43,42,41,
			40,40,40,40,40,40,40,41,42,43,44,45,47,49,50,51};//x = 0-372
	int pokeBallLocationIndex = 0;
	int thrownPokeBallIndex = 0;
	int updatesSinceBallReachedTarget = 0;
	int numberOfBallShakes = -1;
	Animation pokeBallShakeAnim = null;

	public Battle(Pokemon player, Pokemon enemy){
		position = new Point((ApplicationUI.windowWidth/2)-(width/2),(ApplicationUI.windowHeight/2)-(height/2));
		enemyPokemonX = position.x-208;
		playerPokemon = player;
		enemyPokemon = enemy;
		pickOptionButtons[0][0]= new Button(0,0,"Fight");
		pickOptionButtons[0][1]= new Button(0,0,"Pokemon");
		pickOptionButtons[1][0]= new Button(0,0,"Bag");
		pickOptionButtons[1][1]= new Button(0,0,"Run");
		currentButton = pickOptionButtons[0][0];

	}

	public void update(){
		pickOptionButtons[0][0].position = new Point(position.x+250,position.y+height-92);
		pickOptionButtons[1][0].position = new Point(position.x+250+130,position.y+height-92);
		pickOptionButtons[0][1].position = new Point(position.x+250,position.y+height-46);
		pickOptionButtons[1][1].position = new Point(position.x+250+130,position.y+height-46);
		if(updatesSinceLastBounce<updatesPerBounce){
			updatesSinceLastBounce++;
		}
		else{
			updatesSinceLastBounce = 0;
		}
		if(enemyPokemonX+5<position.x+width-208){
			enemyPokemonX+=5;
		}
		else{
			enemyPokemonX=position.x+width-208;
		}
		if(updatesSinceBattleStart<height/2){
			updatesSinceBattleStart++;
		}
		if(enemyPokemon.shiny==1){
			if(GamePanel.randomNumber(1, 90, false)==1){
				animations.add(new Animation(enemyPokemonX+GamePanel.randomNumber(-48, 112, false), position.y+GamePanel.randomNumber(-48, 112, false),enemyPokemon.shineType, GamePanel.textures.sparkles, 5));
			}
			if(updatesSinceBattleStart<=(height/2)-60&&updatesSinceBattleStart>=(height/2)-100){
				if(GamePanel.randomNumber(1, 2, false)==1){
					animations.add(new Animation(enemyPokemonX+GamePanel.randomNumber(-48, 112, false), position.y+GamePanel.randomNumber(-48, 112, false),enemyPokemon.shineType, GamePanel.textures.sparkles, 2));
				}
			}
		}

	}
	public void Draw(Graphics2D g){
		//set font
		g.setFont(font);
		FontMetrics m = g.getFontMetrics(font);

		update();

		//set position and drawing region
		position = new Point((ApplicationUI.windowWidth/2)-(width/2),(ApplicationUI.windowHeight/2)-(height/2));
		g.clip(new Rectangle(position.x,position.y,width,height+5));

		//draw background
		g.drawImage(GamePanel.textures.battleBacks[0][GamePanel.currentArea.biome],position.x, position.y,null);

		//draw enemy baseplate
		g.drawImage(GamePanel.textures.enemyBases[0][GamePanel.currentArea.biome],enemyPokemonX-48, position.y+90,null);

		//draw opponent
		if(enemyTakeDamage==null){//enemy not taking damage
			if(pokeBallLocationIndex<pokeBallThrowOffsetCoordinates.length){
				g.drawImage(enemyPokemon.battleTexture[enemyPokemon.shiny][0],enemyPokemonX, position.y,null);
			}
			else{
				if(updatesSinceBallReachedTarget<40){
					g.drawImage(enemyPokemon.battleTexture[2][0],enemyPokemonX+(updatesSinceBallReachedTarget*2), position.y+(updatesSinceBallReachedTarget),160-(updatesSinceBallReachedTarget*4),160-(updatesSinceBallReachedTarget*4),null);
				}
			}
			//gaining xp
			if(state==GAININGXP){
				if(playerPokemon.newXP>0){
					if(xpAdded<playerPokemon.newXP){

						double experienceGain = (playerPokemon.xpToNextLevel-playerPokemon.xpToCurrentLevel)/50.0;
						if(xpAdded+experienceGain>playerPokemon.newXP){
							experienceGain = playerPokemon.newXP-xpAdded;
							playerPokemon.newXP=0;
							xpAdded=0;
						}
						playerPokemon.experience+=experienceGain;
						xpAdded+=experienceGain;

						//check if the pokemon leveled up
						if(playerPokemon.experience>playerPokemon.xpToNextLevel){
							playerPokemon.setLevel(playerPokemon.level+1);
						}
					}

				}
				else if(updatesSinceXPAdded<60){
					updatesSinceXPAdded++;
				}
				else{
					state = BATTLEOVER;
					updatesSinceXPAdded=0;
				}
			}

		}
		else{
			enemyTakeDamage.Draw(g);
			if(enemyTakeDamage.done){
				//enemy can start losing hp now
				if(enemyPokemon.currentHP!=enemyPokemon.newHP){
					if(enemyPokemon.currentHP>enemyPokemon.newHP){
						enemyPokemon.currentHP--;
						if(enemyPokemon.currentHP<=0){
							System.out.println("feinted");
							feintPokemon(enemyPokemon);
						}
					}
					else{
						enemyPokemon.currentHP++;
					}
				}
				else{


					//enemy done taking damage
					enemyTakeDamage=null;
					if(messageID == SUPEREFFECTIVE||messageID == NOTVERYEFFECTIVE){
						GamePanel.currentBattle.state = GamePanel.currentBattle.TEXT;
					}
					else if(state!=BATTLEOVER&&state!=GAININGXP&&messageID!=ENEMYFAINTED){
						state = PICKOPTION;
						//enemy pokemon should attack
						enemyPokemon.moves.get(GamePanel.randomNumber(0, enemyPokemon.moves.size()-1, false)).use(enemyPokemon, playerPokemon);

					}

				}
			}
		}
		if(state!=BATTLEOVER){
			//draw player baseplate
			g.drawImage(GamePanel.textures.playerBases[0][GamePanel.currentArea.biome],position.x-128, position.y+height-96-64,null);

			//draw player
			if(playerTakeDamage==null){
				if(updatesSinceLastBounce<(updatesPerBounce/2)){
					g.drawImage(playerPokemon.battleTexture[playerPokemon.shiny][1],position.x, position.y+height-96-160,null);
				}
				else{
					g.drawImage(playerPokemon.battleTexture[playerPokemon.shiny][1],position.x, position.y+height-96-160+2,null);
				}
			}
			else{
				playerTakeDamage.Draw(g);
				if(playerTakeDamage.done){
					//player can start losing hp now
					if(playerPokemon.currentHP!=playerPokemon.newHP){
						if(playerPokemon.currentHP>playerPokemon.newHP){
							playerPokemon.currentHP--;
							if(playerPokemon.currentHP<=0){
								System.out.println("player feinted");
								feintPokemon(playerPokemon);
							}
						}
						else{
							playerPokemon.currentHP++;
						}
					}
					else{

						if(delayCounter<=0||true){
							//enemy done taking damage
							playerTakeDamage=null;
							if(messageID == SUPEREFFECTIVE||messageID == NOTVERYEFFECTIVE){
								GamePanel.currentBattle.state = GamePanel.currentBattle.TEXT;
							}
							else if(state!=BATTLEOVER&&state!=GAININGXP&&messageID!=ENEMYFAINTED){
								if(playerPokemon.currentHP>0){
									state = PICKOPTION;
								}
							}

						}

					}
					if(delayCounter>0){
						delayCounter--;
					}
				}

			}

			//draw foe hp and stuff
			g.drawImage(GamePanel.textures.battleFoeBox,position.x, position.y,null);

			//calculate foe's total perfect IV's
			int totalPerfectIVs = 0;
			if(enemyPokemon.AttackIV==31) totalPerfectIVs++;
			if(enemyPokemon.DefenseIV==31) totalPerfectIVs++;
			if(enemyPokemon.SpAtkIV==31) totalPerfectIVs++;
			if(enemyPokemon.SpDefIV==31) totalPerfectIVs++;
			if(enemyPokemon.SpeedIV==31) totalPerfectIVs++;
			if(enemyPokemon.HPIV==31) totalPerfectIVs++;

			//calculate foe's total IV's that are at least 20
			int totalGoodIVs = 0;
			if(enemyPokemon.AttackIV>=20) totalGoodIVs++;
			if(enemyPokemon.DefenseIV>=20) totalGoodIVs++;
			if(enemyPokemon.SpAtkIV>=20) totalGoodIVs++;
			if(enemyPokemon.SpDefIV>=20) totalGoodIVs++;
			if(enemyPokemon.SpeedIV>=20) totalGoodIVs++;
			if(enemyPokemon.HPIV>=20) totalGoodIVs++;

			//draw foe perfect IV stars
			for(int i = 0;i<totalPerfectIVs;i++){
				g.drawImage(GamePanel.textures.stars[0][0],position.x+10+(8*i),position.y+38,null);
			}
			//draw foe good IV stars
			for(int i = 0;i<totalGoodIVs;i++){
				g.drawImage(GamePanel.textures.stars[1][0],position.x+10+(8*totalPerfectIVs)+(8*i),position.y+38,null);
			}
			//draw foe's name
			g.setColor(Color.black);
			g.drawString(enemyPokemon.getName(), position.x+10, position.y+30);
			//draw foe's gender
			g.drawImage(GamePanel.textures.genderIcons[enemyPokemon.gender][0],position.x+m.stringWidth(enemyPokemon.getName())+15,position.y+18,null);
			//draw foe's level
			g.drawString("Lv"+enemyPokemon.level, position.x+190, position.y+30);
			//draw foe's hp bar
			int foeHpBarWidth = (int)(double)(((double)enemyPokemon.currentHP/(double)enemyPokemon.HP)*96.0);
			g.setColor(new Color(24,192,32));
			g.fillRect(position.x+118, position.y+40, foeHpBarWidth, 4);

			//draw player hp and stuff
			int offset = 2;
			if(updatesSinceLastBounce<(updatesPerBounce/2)&&state!=GAININGXP){
				offset = 0;
			}
			g.drawImage(GamePanel.textures.battlePlayerBox,position.x+width-260, position.y+height-96-86-offset,null);
			//draw player pokemon's name
			g.setColor(Color.black);
			g.drawString(playerPokemon.getName(), position.x+width-216, position.y+height-96-55-offset);
			//draw player pokemon's gender
			g.drawImage(GamePanel.textures.genderIcons[playerPokemon.gender][0], (position.x+width-216)+5+m.stringWidth(playerPokemon.getName()), position.y+height-96-67-offset,null);
			//draw player pokemon's level
			g.drawString("Lv"+playerPokemon.level, position.x+width-55, position.y+height-96-55-offset);
			//draw hp numbers
			int xOffset = m.stringWidth(playerPokemon.currentHP+"/"+playerPokemon.HP)/2;
			g.drawString(playerPokemon.currentHP+"/"+playerPokemon.HP, position.x+width-76-xOffset, position.y+height-96-22-offset);
			//draw player pokemon's hp bar
			int hpBarWidth = (int)(double)(((double)playerPokemon.currentHP/(double)playerPokemon.HP)*96.0);
			g.setColor(new Color(24,192,32));
			g.fillRect(position.x+width-124, position.y+height-140-offset, hpBarWidth, 4);
			//draw player pokemon's xp bar
			int xpBarWidth = (int)(double)(((double)(playerPokemon.experience-playerPokemon.xpToCurrentLevel)/(double)(playerPokemon.xpToNextLevel-playerPokemon.xpToCurrentLevel))*192.0);


			g.setColor(new Color(0,148,255));
			g.fillRect(position.x+width-220, position.y+height-104-offset, xpBarWidth, 2);

			//draw animations
			for(int i = 0; i<animations.size();i++){
				if(animations.get(i).done == true){
					animations.remove(i);
					if(i>0) i--;
				}
				else{
					animations.get(i).Draw(g);
				}
			}

			//draw pokeball throw animation if it exists
			if(pokeBallThrowAnim!=null){

				if(pokeBallLocationIndex<pokeBallThrowOffsetCoordinates.length){
					pokeBallThrowAnim.Draw(g);
					if(pokeBallThrowAnim.done){
						pokeBallThrowAnim.currentFrame=0;
						pokeBallThrowAnim.done=false;
					}
					if(pokeBallLocationIndex==pokeBallThrowOffsetCoordinates.length-1){
						pokeBallThrowAnim.position.x=position.x+372;
					}
					else{
						pokeBallThrowAnim.position.x +=6;
					}
					pokeBallThrowAnim.position.y = position.y+pokeBallThrowOffsetCoordinates[pokeBallLocationIndex];
					pokeBallLocationIndex++;
				}
				else{//reached destination

					
					if(updatesSinceBallReachedTarget<40){
						updatesSinceBallReachedTarget++;
						g.drawImage(GamePanel.textures.pokeballsOpen[thrownPokeBallIndex][0],pokeBallThrowAnim.position.x-5,pokeBallThrowAnim.position.y-8,null);
					
					}	
					else{
						if(updatesSinceBallReachedTarget<80){
							updatesSinceBallReachedTarget++;
							g.drawImage(GamePanel.textures.pokeballThrowFrames[0][thrownPokeBallIndex],pokeBallThrowAnim.position.x,pokeBallThrowAnim.position.y+((updatesSinceBallReachedTarget-40)*2), null);
						}
						else{
							if(pokeBallShakeAnim==null||pokeBallShakeAnim.done){
								System.out.println("got here 1");
								if(numberOfBallShakes==0){
									//reset all pokeball related information
									pokeBallThrowAnim = null;
									thrownPokeBallIndex = 0;
									updatesSinceBallReachedTarget = 0;
									numberOfBallShakes = -1;
								}
								else{
									System.out.println("got here 2");
									if(pokeBallShakeAnim==null||pokeBallShakeAnim.done){
										numberOfBallShakes--;
										
										pokeBallShakeAnim = new Animation(pokeBallThrowAnim.position.x,pokeBallThrowAnim.position.y+((updatesSinceBallReachedTarget-40)*2),thrownPokeBallIndex,GamePanel.textures.pokeBallShakeFrames,5);
									}
									
								}
							}
							else{
								int rollOffset = 0;
								if(pokeBallShakeAnim.currentFrame<3){
									rollOffset+=2;
								}
								else{
									rollOffset-=2;
								}
								pokeBallShakeAnim.position.x+=rollOffset;
								pokeBallShakeAnim.Draw(g);
								System.out.println("drawing shake animation");
							}
						}
					}
				}
			}

			//draw bottom thingy
			GamePanel.drawTextBox(g);

			//draw box things that open when the battle starts

			//fade
			int alpha = 160-(int)(double)(((double)updatesSinceBattleStart/((double)height/2.0))*255.0);
			if(alpha<0) alpha=0;
			g.setColor(new Color(0,0,0,alpha));
			g.fillRect(position.x, position.y, width, height);
			g.setColor(Color.black);
			//top rectangle
			g.fillRect(position.x, position.y, width, (height/2)-(updatesSinceBattleStart*2));
			//bottom rectangle
			g.fillRect(position.x, position.y+(height/2)+(updatesSinceBattleStart*2), width, (height/2)-(updatesSinceBattleStart*2));
		}
		else{
			GamePanel.currentBattle=null;
		}
	}
	public void feintPokemon(Pokemon pokemon){
		if(pokemon == enemyPokemon){
			playerPokemon.newXP = pokemon.getExperienceYield();
			state = TEXT;
			messageID = ENEMYFAINTED;
		}
		else{
			state = TEXT;
			messageID = PLAYERFAINTED;

		}
	}

}
