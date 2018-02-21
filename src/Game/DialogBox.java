package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class DialogBox {
	String message = "";
	String newMessage = "";
	String displayableMessage = "";
	ArrayList<String> displayableWords1 = new ArrayList<String>();
	ArrayList<String> displayableWords2 = new ArrayList<String>();
	int charactersInFirstLine = 0;

	Font font = new Font("Iwona Heavy",Font.PLAIN,18);
	int xpos = 0;
	int ypos = 0;
	int updatesSinceTextIncremented = 0;
	boolean reachedNextLine = false;
	boolean reachedEndOfSecondLine = false;
	Animation advanceIndicator;
	public DialogBox(int x, int y){
		xpos = x;
		ypos = y;
		advanceIndicator = new Animation(xpos+480,ypos+65,0,GamePanel.textures.advanceIndicatorFrames,6);
	}
	public void addMessage(String msg){
		message = "";
		newMessage = msg;
		reachedNextLine = false;
		reachedEndOfSecondLine = false;
		updatesSinceTextIncremented = 0;
	}
	public void advanceMessage(){
		//System.out.println("advance message called");
		if(message.length()<displayableMessage.length()) {
			message = displayableMessage;
			return;
		}
		if(displayableMessage.length()<newMessage.length()){
			//System.out.println("1");
			message = message.substring(displayableMessage.length(), message.length());
			newMessage = newMessage.substring(displayableMessage.length(), newMessage.length());
			displayableMessage = "";
		}
		else{
			//System.out.println("2");
			message = "";
			newMessage = "";
			displayableMessage = "";
		}

		reachedNextLine = false;
		reachedEndOfSecondLine = false;
		updatesSinceTextIncremented = 0;
		displayableWords1.clear();
		displayableWords2.clear();
	}
	public boolean wordIsImportant(String word){
		String[] keyWords = {"Gym", "Badge", "Badges", "leader", "flute", "Team Rocket","Pokemon", "poke", "Mt.", "Moon", "Strength", "Surf", "defense", "attack", "Apricorns", "berries", "Apricorn", "Center", "Power", "Points", "PP", "Safari", "zone"};
		for(int i = 0; i<GamePanel.possiblePokemon.size();i++){
			if(word.equalsIgnoreCase(GamePanel.possiblePokemon.get(i).species) || word.equalsIgnoreCase(GamePanel.possiblePokemon.get(i).species+"s")){
				return true;
			}
		}
		for(int i = 0; i<keyWords.length; i++){
			if(word.equalsIgnoreCase(keyWords[i])){
				return true;
			}
		}
		return false;
	}
	public void calculateWordsToDraw(Graphics2D g){
		displayableWords1.clear();
		displayableWords2.clear();
		displayableMessage = "";
		charactersInFirstLine = 0;
		FontMetrics m = g.getFontMetrics(font);
		boolean nextLine = false;
		//if the new message isn't empty
		if(newMessage.length()>0){
			String[] words = newMessage.split(" ");
			int currentLength1 = 0;
			int currentLength2 = 0;
			//for each word
			for(int i = 0;i<words.length;i++){
				if(words[i]!=" "){
					if(!nextLine && currentLength1+(m.stringWidth(words[i])+5)<472){
						displayableWords1.add(words[i]);
						currentLength1 += (m.stringWidth(words[i])+5);
						displayableMessage += words[i] + " ";
						charactersInFirstLine += (words[i].length()+1);
					}
					else{
						nextLine = true;
					}
					if(nextLine && currentLength2+(m.stringWidth(words[i])+5)<472){
						displayableWords2.add(words[i]);
						currentLength2 += (m.stringWidth(words[i])+5);
						displayableMessage += words[i] + " ";
					}
					else{
						//break;
					}
				}
			}
		}
	}
	public void Draw(Graphics2D g){
		calculateWordsToDraw(g);
		if(message.length() < displayableMessage.length() && updatesSinceTextIncremented == 2){
			updatesSinceTextIncremented = 0;
			message += displayableMessage.charAt(message.length());
		}
		else{
			updatesSinceTextIncremented++;
		}
		g.setColor(Color.black);
		g.setFont(font);
		FontMetrics m = g.getFontMetrics(font);

		if(message != ""){
			g.drawImage(GamePanel.textures.battleMessage,xpos, ypos,null);
			if(reachedEndOfSecondLine){
				advanceIndicator.Draw(g);
				if(advanceIndicator.done){
					advanceIndicator.currentFrame=0;
					advanceIndicator.done=false;
				}
			}

			int currentLength1 = 0;
			String charactersDrawn1 = "";
			for(int i = 0; i<displayableWords1.size();i++){
				g.setColor(Color.black);
				if(displayableWords1.get(i).length() > 0 && (wordIsImportant(displayableWords1.get(i)) || wordIsImportant(displayableWords1.get(i).substring(0,displayableWords1.get(i).length()-1)))){
					g.setColor(Color.blue);
				}
				for(int j = 0; j<displayableWords1.get(i).length();j++){
					if(charactersDrawn1.length()<message.length()){
						if(displayableWords1.get(i).charAt(j) == '!' || displayableWords1.get(i).charAt(j) == '?' || displayableWords1.get(i).charAt(j) == '.' || displayableWords1.get(i).charAt(j) == ','){
							g.setColor(Color.black);
						}
						g.drawString(displayableWords1.get(i).charAt(j)+"", xpos + 20 + currentLength1, ypos+35);
						charactersDrawn1 += displayableWords1.get(i).charAt(j);
						currentLength1 +=(m.stringWidth(displayableWords1.get(i).charAt(j)+""));
					}

				}
				currentLength1 += 5;
				charactersDrawn1 += " ";
			}

			if(charactersDrawn1.length() == charactersInFirstLine){
				int currentLength2 = 0;
				String charactersDrawn2 = "";

				for(int i = 0; i<displayableWords2.size();i++){
					g.setColor(Color.black);
					if(displayableWords2.get(i).length() > 1 && (wordIsImportant(displayableWords2.get(i)) || wordIsImportant(displayableWords2.get(i).substring(0,displayableWords2.get(i).length()-2)))){
						g.setColor(Color.blue);
					}
					for(int j = 0; j<displayableWords2.get(i).length();j++){
						if(charactersDrawn1.length() + charactersDrawn2.length()<message.length()){
							if(displayableWords2.get(i).charAt(j) == '!' || displayableWords2.get(i).charAt(j) == '?' || displayableWords2.get(i).charAt(j) == '.' || displayableWords2.get(i).charAt(j) == ','){
								g.setColor(Color.black);
							}
							g.drawString(displayableWords2.get(i).charAt(j)+"", xpos + 20 + currentLength2, ypos+70);
							charactersDrawn2 += displayableWords2.get(i).charAt(j);
							currentLength2 +=(m.stringWidth(displayableWords2.get(i).charAt(j)+""));
						}
					}
					currentLength2 += 5;
					charactersDrawn2 += " ";
					
				}
				advanceIndicator.Draw(g);
				if(advanceIndicator.done){
					advanceIndicator.currentFrame=0;
					advanceIndicator.done=false;
				}
			}

			
		}
	}
}
