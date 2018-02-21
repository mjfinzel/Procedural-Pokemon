package Game;

import java.awt.Point;
import java.util.ArrayList;

public class Type {
	public static int 	NONE = -1,
						NORMAL = 0,
						FIGHTING = 1,
						FLYING = 2,
						POISON = 3,
						GROUND = 4,
						ROCK = 5,
						BUG = 6,
						GHOST = 7,
						STEEL = 8,
						FIRE = 9,
						WATER = 10,		
						GRASS = 11,	
						ELECTRIC = 12,
						PSYCHIC = 13,
						ICE = 14,
						DRAGON = 15,
						DARK = 16,
						FAIRY = 17;
	
	public static double[][] typeChart = {// NL  FT  FG  PN  GD  RK  BG  GT  SL  FE  WR  GS  EC  PC  IE  DN  DK  FY									
									{1.0,1.0,1.0,1.0,1.0,0.5,1.0,0.0,0.5,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0},//NORMAL
									{2.0,1.0,0.5,0.5,1.0,2.0,0.5,0.0,2.0,1.0,1.0,1.0,1.0,0.5,2.0,1.0,2.0,0.5},//FIGHTING
									{1.0,2.0,1.0,1.0,1.0,0.5,2.0,1.0,0.5,1.0,1.0,2.0,0.5,1.0,1.0,1.0,1.0,1.0},//FLYING
									{1.0,1.0,1.0,0.5,0.5,0.5,1.0,0.5,0.0,1.0,1.0,2.0,1.0,1.0,1.0,1.0,1.0,2.0},//POISON
									{1.0,1.0,0.0,2.0,1.0,2.0,0.5,1.0,2.0,2.0,1.0,0.5,2.0,1.0,1.0,1.0,1.0,1.0},//GROUND
									{1.0,0.5,2.0,1.0,0.5,1.0,2.0,1.0,0.5,2.0,1.0,1.0,1.0,1.0,2.0,1.0,1.0,1.0},//ROCK
									{1.0,0.5,0.5,0.5,1.0,1.0,1.0,0.5,0.5,0.5,1.0,2.0,1.0,2.0,1.0,1.0,2.0,0.5},//BUG
									{0.0,1.0,1.0,1.0,1.0,1.0,1.0,2.0,1.0,1.0,1.0,1.0,1.0,2.0,1.0,1.0,0.5,1.0},//GHOST
									{1.0,1.0,1.0,1.0,1.0,2.0,1.0,1.0,0.5,0.5,0.5,1.0,0.5,1.0,2.0,1.0,1.0,2.0},//STEEL
									{1.0,1.0,1.0,1.0,1.0,0.5,2.0,1.0,2.0,0.5,0.5,2.0,1.0,1.0,2.0,0.5,1.0,1.0},//FIRE
									{1.0,1.0,1.0,1.0,2.0,2.0,1.0,1.0,1.0,2.0,0.5,0.5,1.0,1.0,1.0,0.5,1.0,1.0},//WATER
									{1.0,1.0,0.5,0.5,2.0,2.0,0.5,1.0,0.5,0.5,2.0,0.5,1.0,1.0,1.0,0.5,1.0,1.0},//GRASS
									{1.0,1.0,2.0,1.0,0.0,1.0,1.0,1.0,1.0,1.0,2.0,0.5,0.5,1.0,1.0,0.5,1.0,1.0},//ELECTRIC
									{1.0,2.0,1.0,2.0,1.0,1.0,1.0,1.0,0.5,1.0,1.0,1.0,0.5,1.0,1.0,1.0,0.0,1.0},//PSYCHIC
									{1.0,1.0,2.0,1.0,2.0,1.0,1.0,1.0,0.5,0.5,0.5,2.0,1.0,1.0,0.5,2.0,1.0,1.0},//ICE
									{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,0.5,1.0,1.0,1.0,1.0,1.0,1.0,2.0,1.0,0.0},//DRAGON
									{1.0,0.5,1.0,1.0,1.0,1.0,1.0,2.0,1.0,1.0,1.0,1.0,1.0,2.0,1.0,1.0,0.5,0.5},//DARK
									{1.0,2.0,1.0,0.5,1.0,1.0,1.0,1.0,0.5,0.5,1.0,1.0,1.0,1.0,1.0,2.0,2.0,1.0},//FAIRY
								  };
	public static double getTypeDmgMultiplier(Point attackerType, Point defenderType, int attackType){
		System.out.println("attack type, defender type.x: "+attackType+", "+ defenderType.x);
		double dmgMultiplier = typeChart[attackType][defenderType.x];
		if(defenderType.y!=NONE){
			dmgMultiplier = dmgMultiplier*typeChart[attackType][defenderType.y];
		}
		//STAB
		if(attackerType.x==attackType||attackerType.y==attackType){
			dmgMultiplier = dmgMultiplier*1.5;
		}
		return dmgMultiplier;
	}
}
