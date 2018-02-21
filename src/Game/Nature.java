package Game;

public class Nature {
	String name;
	double attackMulti = 1.0;
	double defenseMulti = 1.0;
	double spAtkMulti = 1.0;
	double spDefMulti = 1.0;
	double speedMulti = 1.0;
	public Nature(int id){
		switch(id){
			case 1:
				name = "Hardy";
				attackMulti = 1.0;
				defenseMulti = 1.0;
				spAtkMulti = 1.0;
				spDefMulti = 1.0;
				speedMulti = 1.0;
				break;
			case 2:
				name = "Lonely";
				attackMulti = 1.1;
				defenseMulti = .9;
				spAtkMulti = 1.0;
				spDefMulti = 1.0;
				speedMulti = 1.0;
				break;
			case 3:
				name = "Brave";
				attackMulti = 1.1;
				defenseMulti = 1.0;
				spAtkMulti = 1.0;
				spDefMulti = 1.0;
				speedMulti = .9;
				break;
			case 4:
				name = "Adamant";
				attackMulti = 1.1;
				defenseMulti = 1.0;
				spAtkMulti = .9;
				spDefMulti = 1.0;
				speedMulti = 1.0;
				break;
			case 5:
				name = "Naughty";
				attackMulti = 1.1;
				defenseMulti = 1.0;
				spAtkMulti = 1.0;
				spDefMulti = .9;
				speedMulti = 1.0;
				break;
			case 6:
				name = "Bold";
				attackMulti = .9;
				defenseMulti = 1.1;
				spAtkMulti = 1.0;
				spDefMulti = 1.0;
				speedMulti = 1.0;
				break;
			case 7:
				name = "Docile";
				attackMulti = 1.0;
				defenseMulti = 1.0;
				spAtkMulti = 1.0;
				spDefMulti = 1.0;
				speedMulti = 1.0;
				break;
			case 8:
				name = "Relaxed";
				attackMulti = 1.0;
				defenseMulti = 1.1;
				spAtkMulti = 1.0;
				spDefMulti = 1.0;
				speedMulti = .9;
				break;
			case 9:
				name = "Impish";
				attackMulti = 1.0;
				defenseMulti = 1.1;
				spAtkMulti = .9;
				spDefMulti = 1.0;
				speedMulti = 1.0;
				break;
			case 10:
				name = "Lax";
				attackMulti = 1.0;
				defenseMulti = 1.1;
				spAtkMulti = 1.0;
				spDefMulti = .9;
				speedMulti = 1.0;
				break;
			case 11:
				name = "Timid";
				attackMulti = .9;
				defenseMulti = 1.0;
				spAtkMulti = 1.0;
				spDefMulti = 1.0;
				speedMulti = 1.1;
				break;
			case 12:
				name = "Hasty";
				attackMulti = 1.0;
				defenseMulti = .9;
				spAtkMulti = 1.0;
				spDefMulti = 1.0;
				speedMulti = 1.1;
				break;
			case 13:
				name = "Serious";
				attackMulti = 1.0;
				defenseMulti = 1.0;
				spAtkMulti = 1.0;
				spDefMulti = 1.0;
				speedMulti = 1.0;
				break;
			case 14:
				name = "Jolly";
				attackMulti = 1.0;
				defenseMulti = 1.0;
				spAtkMulti = .9;
				spDefMulti = 1.0;
				speedMulti = 1.1;
				break;
			case 15:
				name = "Naive";
				attackMulti = 1.0;
				defenseMulti = .9;
				spAtkMulti = 1.0;
				spDefMulti = 1.0;
				speedMulti = 1.1;
				break;
			case 16:
				name = "Modest";
				attackMulti = .9;
				defenseMulti = 1.0;
				spAtkMulti = 1.1;
				spDefMulti = 1.0;
				speedMulti = 1.0;
				break;
			case 17:
				name = "Mild";
				attackMulti = 1.0;
				defenseMulti = .9;
				spAtkMulti = 1.1;
				spDefMulti = 1.0;
				speedMulti = 1.0;
				break;
			case 18:
				name = "Quiet";
				attackMulti = 1.0;
				defenseMulti = 1.0;
				spAtkMulti = 1.1;
				spDefMulti = 1.0;
				speedMulti = .9;
				break;
			case 19:
				name = "Bashful";
				attackMulti = 1.0;
				defenseMulti = 1.0;
				spAtkMulti = 1.0;
				spDefMulti = 1.0;
				speedMulti = 1.0;
				break;
			case 20:
				name = "Rash";
				attackMulti = 1.0;
				defenseMulti = 1.0;
				spAtkMulti = 1.1;
				spDefMulti = .9;
				speedMulti = 1.0;
				break;
			case 21:
				name = "Calm";
				attackMulti = .9;
				defenseMulti = 1.0;
				spAtkMulti = 1.0;
				spDefMulti = 1.1;
				speedMulti = 1.0;
				break;
			case 22:
				name = "Gentle";
				attackMulti = 1.0;
				defenseMulti = .9;
				spAtkMulti = 1.0;
				spDefMulti = 1.1;
				speedMulti = 1.0;
				break;
			case 23:
				name = "Sassy";
				attackMulti = 1.0;
				defenseMulti = 1.0;
				spAtkMulti = 1.0;
				spDefMulti = 1.1;
				speedMulti = .9;
				break;
			case 24:
				name = "Careful";
				attackMulti = 1.0;
				defenseMulti = 1.0;
				spAtkMulti = .9;
				spDefMulti = 1.1;
				speedMulti = 1.0;
				break;
			case 25:
				name = "";
				attackMulti = 1.0;
				defenseMulti = 1.0;
				spAtkMulti = 1.0;
				spDefMulti = 1.0;
				speedMulti = 1.0;
				break;
		}
	}
}
