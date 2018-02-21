package Game;

public class Team {
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
	int[] type = new int[2];
	String name;
	String goal;
	public Team(int type1, int type2){
		type[0] = type1;
		type[1] = type2;
		if(hasTypes(Type.NORMAL, Type.NONE)){
			name = "Team Prosaic";
			goal = "Steal pokemon.";
		}
		else if(hasTypes(Type.FIGHTING, Type.NONE)){
			goal = "To eliminate the weak.";
		}
		else if(hasTypes(Type.FLYING, Type.NONE)){
			name = "Team Aerial";
			goal = "Freedom";
		}
		else if(hasTypes(Type.POISON, Type.NONE)){
			name = "Team Contagion";
			goal = "Eradicate opposition";
		}
		else if(hasTypes(Type.GROUND, Type.NONE)){
			name = "Team Dust";
			goal = "Rebuild the past and ressurect fossils.";
		}
		else if(hasTypes(Type.ROCK, Type.NONE)){
			name = "Team Boulder";
			goal = "";
		}
		else if(hasTypes(Type.BUG, Type.NONE)){
			name = "Team Vermin";
			goal = "";
		}
		else if(hasTypes(Type.GHOST, Type.NONE)){
			name = "Team Spectral";
			goal = "Transform the world to accomodate the dead.";
		}
		else if(hasTypes(Type.STEEL, Type.NONE)){
			name = "Team Girder";
			goal = "Build a better world.";
		}
		else if(hasTypes(Type.FIRE, Type.NONE)){
			name = "Team Inferno";
			goal = "Cleanse the world.";
		}
		else if(hasTypes(Type.WATER, Type.NONE)){
			name = "Team Hydro";
			goal = "";
		}
		else if(hasTypes(Type.GRASS, Type.NONE)){
			name = "Team Preservation";
			goal = "Restore the world to it's natural state.";
		}
		else if(hasTypes(Type.ELECTRIC, Type.NONE)){
			name = "Team Voltaic";
			goal = "Sell electricity.";
		}
		else if(hasTypes(Type.PSYCHIC, Type.NONE)){
			name = "Team Mystic";
			goal = "";
		}
		else if(hasTypes(Type.ICE, Type.NONE)){
			name = "Team Glacier";
			goal = "";
		}
		else if(hasTypes(Type.DRAGON, Type.NONE)){
			name = "Team Wyvern";
			goal = "";
		}
		else if(hasTypes(Type.DARK, Type.NONE)){
			name = "Team Shadow";
			goal = "Return the world to darkness.";
		}
		else if(hasTypes(Type.FAIRY, Type.NONE)){
			name = "Team Allure";
			goal = "";
		}
	}
	public boolean hasTypes(int t1, int t2){
		if(type[0]==t1 && type[1]==t2){
			return true;
		}
		else if(type[0]==t2 && type[1]==t1){
			return true;
		}
		return false;
	}
}
