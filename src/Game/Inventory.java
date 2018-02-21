package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Inventory {
	ArrayList<Item> items = new ArrayList<Item>();
	ArrayList<Item> medicine = new ArrayList<Item>();
	ArrayList<Item> pokeBalls = new ArrayList<Item>();
	ArrayList<Item> tmAndHM = new ArrayList<Item>();
	ArrayList<Item> berries = new ArrayList<Item>();
	ArrayList<Item> keyItems = new ArrayList<Item>();

	ArrayList<String> possibleItems = new ArrayList<String>();
	ArrayList<String> possibleMedicine = new ArrayList<String>();
	ArrayList<String> possiblePokeBalls = new ArrayList<String>();
	ArrayList<String> possibleTMandHM = new ArrayList<String>();
	ArrayList<String> possibleBerries= new ArrayList<String>();
	ArrayList<String> possibleKeyItems = new ArrayList<String>();

	int xpos = 0;
	int ypos = 0;

	int currentMenu = 0;
	int currentItem = 0;
	int currentMedicine = 0;
	int currentPokeBall = 0;
	int currentTMorHM = 0;
	int currentBerry = 0;
	int currentKeyItem = 0;

	public Inventory(){
		//general items

		possibleItems.add("Repel");

		//medicine

		possibleMedicine.add("Potion");

		//poke balls

		possiblePokeBalls.add("Poke Ball");
		possiblePokeBalls.add("Great Ball");
		possiblePokeBalls.add("Ultra Ball");

		//TMs and HMs

		possibleTMandHM.add("HM03 Surf");

		//Berries

		possibleBerries.add("Cheri Berry");
		possibleBerries.add("Chesto Berry");
		possibleBerries.add("Pecha Berry");
		possibleBerries.add("Rawst Berry");
		possibleBerries.add("Aspear Berry");
		possibleBerries.add("Leppa Berry");
		possibleBerries.add("Oran Berry");
		possibleBerries.add("Persim Berry");
		possibleBerries.add("Lum Berry");
		possibleBerries.add("Sitrus Berry");
		possibleBerries.add("Figy Berry");
		possibleBerries.add("Wiki Berry");
		possibleBerries.add("Mago Berry");
		possibleBerries.add("Aguav Berry");
		possibleBerries.add("lapapa Berry");
		possibleBerries.add("Razz Berry");
		possibleBerries.add("Bluk Berry");
		possibleBerries.add("Oran Berry");
		possibleBerries.add("Nanab Berry");
		possibleBerries.add("Wepear Berry");
		possibleBerries.add("Pinap Berry");
		possibleBerries.add("Pomeg Berry");
		possibleBerries.add("Kelpsy Berry");
		possibleBerries.add("Qualot Berry");
		possibleBerries.add("Hondew Berry");
		possibleBerries.add("Grepa Berry");
		possibleBerries.add("Tamato Berry");
		possibleBerries.add("Cornn Berry");
		possibleBerries.add("Magost Berry");
		possibleBerries.add("Rabuta Berry");
		possibleBerries.add("Nomel Berry");
		possibleBerries.add("Spelon Berry");
		possibleBerries.add("Pamtre Berry");
		possibleBerries.add("Watmel Berry");
		possibleBerries.add("Durin Berry");
		possibleBerries.add("Belue Berry");
		possibleBerries.add("Occa Berry");
		possibleBerries.add("Passho Berry");
		possibleBerries.add("Wacan Berry");
		possibleBerries.add("Rindo Berry");
		possibleBerries.add("Yache Berry");
		possibleBerries.add("Chople Berry");
		possibleBerries.add("Kebia Berry");
		possibleBerries.add("Shuca Berry");
		possibleBerries.add("Coba Berry");
		possibleBerries.add("Payapa Berry");
		possibleBerries.add("Tanga Berry");
		possibleBerries.add("Charti Berry");
		possibleBerries.add("Kasib Berry");
		possibleBerries.add("Haban Berry");
		possibleBerries.add("Colbur Berry");
		possibleBerries.add("Babiri Berry");
		possibleBerries.add("Chilan Berry");
		possibleBerries.add("Liechi Berry");
		possibleBerries.add("Ganlon Berry");
		possibleBerries.add("Salac Berry");
		possibleBerries.add("Petaya Berry");
		possibleBerries.add("Apicot Berry");
		possibleBerries.add("Lansat Berry");
		possibleBerries.add("Starf Berry");
		possibleBerries.add("Enigma Berry");
		possibleBerries.add("Micle Berry");
		possibleBerries.add("Custap Berry");
		possibleBerries.add("Jaboca Berry");
		possibleBerries.add("Rowap Berry");
		possibleBerries.add("Roseli Berry");
		possibleBerries.add("Kee Berry");
		possibleBerries.add("Maranga Berry");

		//Key Items

		possibleKeyItems.add("Old Rod");
	}
	public void addItem(String name, int amount){
		Item item = new Item(name);
		item.amount=amount;


		if(possibleItems.contains(name)){
			for(int i = 0; i<items.size();i++){
				if(items.get(i).name.equals(name)){
					items.get(i).amount+=amount;
					return;
				}
			}
			items.add(item);
		}
		else if(possibleMedicine.contains(name)){
			for(int i = 0; i<medicine.size();i++){
				if(medicine.get(i).name.equals(name)){
					medicine.get(i).amount+=amount;
					return;
				}
			}
			medicine.add(item);
		}
		else if(possiblePokeBalls.contains(name)){
			for(int i = 0; i<pokeBalls.size();i++){
				if(pokeBalls.get(i).name.equals(name)){
					pokeBalls.get(i).amount+=amount;
					return;
				}
			}
			pokeBalls.add(item);
		}
		else if(possibleTMandHM.contains(name)){
			for(int i = 0; i<tmAndHM.size();i++){
				if(tmAndHM.get(i).name.equals(name)){
					tmAndHM.get(i).amount+=amount;
					return;
				}
			}
			tmAndHM.add(item);
		}
		else if(possibleBerries.contains(name)){
			for(int i = 0; i<berries.size();i++){
				if(berries.get(i).name.equals(name)){
					berries.get(i).amount+=amount;
					return;
				}
			}
			berries.add(item);
		}
		else if(possibleKeyItems.contains(name)){
			for(int i = 0; i<keyItems.size();i++){
				if(keyItems.get(i).name.equals(name)){
					keyItems.get(i).amount+=amount;
					return;
				}
			}
			keyItems.add(item);
		}
		else{
			System.out.println("Failed to add item: "+name+", This item is not a possible item!");
		}
	}
	public Item getCurrentItem(){
		switch(currentMenu){
		case 0:
			return items.get(currentItem);
		case 1:
			return medicine.get(currentMedicine);
		case 2:
			return pokeBalls.get(currentPokeBall);
		case 3:
			return tmAndHM.get(currentTMorHM);
		case 4:
			return berries.get(currentBerry);
		case 5:
			return keyItems.get(currentKeyItem);
		}
		return null;
	}
	public void DrawSection(String label, int x, int y, Graphics2D g){
		int maxHeight = 10;
		int menuIndex = 0;
		int menuCurrentItem = 0;


		ArrayList<Item> list = null;
		if(label.equals("Items")){
			list = items;
			menuIndex = 0;
			menuCurrentItem = currentItem;
		}
		else if(label.equals("Medicine")){
			list = medicine;
			menuIndex = 1;
			menuCurrentItem = currentMedicine;
		}
		else if(label.equals("Poke Balls")){
			list = pokeBalls;
			menuIndex = 2;
			menuCurrentItem = currentPokeBall;
		}
		else if(label.equals("TMs & HMs")){
			list = tmAndHM;
			menuIndex = 3;
			menuCurrentItem = currentTMorHM;
		}
		else if(label.equals("Berries")){
			list = berries;
			menuIndex = 4;
			menuCurrentItem = currentBerry;
		}
		else if(label.equals("Key Items")){
			list = keyItems;
			menuIndex = 5;
			menuCurrentItem = currentKeyItem;
		}

		g.setColor(Color.black);
		g.setFont(new Font("Iwona Heavy",Font.BOLD,13));

		g.setColor(new Color(240,240,240));
		g.fillRect(x, y, 158, (maxHeight*24)+6-12);
		g.setColor(Color.black);
		g.drawRect(x, y, 158, (maxHeight*24)+6-12);
		g.setColor(Color.red);
		g.drawRect(x+1, y+1, 156, (maxHeight*24)+4-12);
		g.setColor(Color.black);
		g.drawRect(x+2, y+2, 154, (maxHeight*24)+2-12);
		g.drawImage(GamePanel.textures.inventoryLabel,x+19,y-30,null);
		g.setFont(new Font("Iwona Heavy",Font.BOLD,16));
		FontMetrics m = g.getFontMetrics(new Font("Iwona Heavy",Font.BOLD,16));
		g.drawString(label, x+79-(m.stringWidth(label)/2), y-10);
		g.setClip(new Rectangle(x+3,y+3,154,(maxHeight*24)+1-12));
		g.setFont(new Font("Iwona Heavy",Font.BOLD,13));
		int startingIndex = 0;
		int offset = -6;
		if(menuCurrentItem>=maxHeight-2){
			startingIndex = menuCurrentItem-(maxHeight-2);
		}
		if(startingIndex==0){
			offset = 0;
		}
		for(int i = 0; i+startingIndex<list.size()&&i<maxHeight;i++){
			g.drawImage(GamePanel.textures.itemBox[0][0],x+4,y+4+((i)*24)+offset,null);
			if(currentMenu==menuIndex && startingIndex+i==menuCurrentItem){
				g.drawImage(GamePanel.textures.itemBox[0][1],x+4,y+4+((i)*24)+offset,null);
			}
			g.drawString(list.get(startingIndex+i).name, x+14, y+(i*24)+21+offset);
			g.drawString("x"+list.get(startingIndex+i).amount, x+14+105, y+(i*24)+21+offset);
		}
		g.setClip(null);
	}
	public void Draw(Graphics2D g){
		g.setClip(null);

		xpos = (ApplicationUI.windowWidth/2)-475;
		ypos = (ApplicationUI.windowHeight/2)-140;

		g.setColor(new Color(200,255,200));
		g.fillRect(xpos-10, ypos-40, 970, 330);
		g.setColor(Color.black);
		g.drawRect(xpos-10, ypos-40, 970, 330);
		g.setColor(Color.red);
		g.drawRect(xpos-9, ypos-39, 968, 328);
		g.setColor(Color.black);
		g.drawRect(xpos-8, ypos-38, 966, 326);

		DrawSection("Items",xpos-4,ypos-4,g);
		DrawSection("Medicine",xpos+156,ypos-4,g);
		DrawSection("Poke Balls",xpos+316,ypos-4,g);
		DrawSection("TMs & HMs",xpos+476,ypos-4,g);
		DrawSection("Berries",xpos+636,ypos-4,g);
		DrawSection("Key Items",xpos+796,ypos-4,g);
		//		g.setColor(Color.black);
		//		g.setFont(new Font("Iwona Heavy",Font.BOLD,13));
		//
		//		//draw items
		//		g.setColor(new Color(240,240,240));
		//		g.fillRect(xpos-4, ypos-4, 158, 488);
		//		g.setColor(Color.black);
		//		g.drawRect(xpos-4, ypos-4, 158, 488);
		//		g.setColor(Color.red);
		//		g.drawRect(xpos-3, ypos-3, 156, 486);
		//		g.setColor(Color.black);
		//		g.drawRect(xpos-2, ypos-2, 154, 484);
		//		g.drawImage(GamePanel.textures.inventoryLabel,xpos+10,ypos-34,null);
		//		for(int i = 0; i<items.size()&&i<20;i++){
		//			if(currentMenu==0 && i==currentItem){
		//				g.drawImage(GamePanel.textures.itemBox[0][1],xpos,ypos+(i*24),null);
		//			}
		//			else{
		//				g.drawImage(GamePanel.textures.itemBox[0][0],xpos,ypos+(i*24),null);
		//			}
		//			g.drawString(items.get(i).name, xpos+10, ypos+(i*24)+17);
		//			g.drawString("x"+items.get(i).amount, xpos+10+105, ypos+(i*24)+17);
		//		}
		//		//draw medicine
		//		g.setColor(new Color(240,240,240));
		//		g.fillRect(xpos+156, ypos-4, 158, 488);
		//		g.setColor(Color.black);
		//		g.drawRect(xpos+156, ypos-4, 158, 488);
		//		g.setColor(Color.red);
		//		g.drawRect(xpos+157, ypos-3, 156, 486);
		//		g.setColor(Color.black);
		//		g.drawRect(xpos+158, ypos-2, 154, 484);
		//		for(int i = 0; i<medicine.size()&&i<20;i++){
		//			if(currentMenu==1 && i==currentItem){
		//				g.drawImage(GamePanel.textures.itemBox[0][1],xpos+160,ypos+(i*24),null);
		//			}
		//			else{
		//				g.drawImage(GamePanel.textures.itemBox[0][0],xpos+160,ypos+(i*24),null);
		//			}
		//			g.drawString(medicine.get(i).name, xpos+170, ypos+(i*24)+17);
		//			g.drawString("x"+medicine.get(i).amount, xpos+170+105, ypos+(i*24)+17);
		//		}
		//		//draw poke balls
		//		g.setColor(new Color(240,240,240));
		//		g.fillRect(xpos+316, ypos-4, 158, 488);
		//		g.setColor(Color.black);
		//		g.drawRect(xpos+316, ypos-4, 158, 488);
		//		g.setColor(Color.red);
		//		g.drawRect(xpos+317, ypos-3, 156, 486);
		//		g.setColor(Color.black);
		//		g.drawRect(xpos+318, ypos-2, 154, 484);
		//		for(int i = 0; i<pokeBalls.size()&&i<20;i++){
		//			if(currentMenu==2 && i==currentItem){
		//				g.drawImage(GamePanel.textures.itemBox[0][1],xpos+320,ypos+(i*24),null);
		//			}
		//			else{
		//				g.drawImage(GamePanel.textures.itemBox[0][0],xpos+320,ypos+(i*24),null);
		//			}
		//			g.drawString(pokeBalls.get(i).name, xpos+330, ypos+(i*24)+17);
		//			g.drawString("x"+pokeBalls.get(i).amount, xpos+330+105, ypos+(i*24)+17);
		//		}
		//		//draw TMs and HMs
		//		g.setColor(new Color(240,240,240));
		//		g.fillRect(xpos+476, ypos-4, 158, 488);
		//		g.setColor(Color.black);
		//		g.drawRect(xpos+476, ypos-4, 158, 488);
		//		g.setColor(Color.red);
		//		g.drawRect(xpos+477, ypos-3, 156, 486);
		//		g.setColor(Color.black);
		//		g.drawRect(xpos+478, ypos-2, 154, 484);
		//		for(int i = 0; i<tmAndHM.size()&&i<20;i++){
		//			if(currentMenu==3 && i==currentItem){
		//				g.drawImage(GamePanel.textures.itemBox[0][1],xpos+480,ypos+(i*24),null);
		//			}
		//			else{
		//				g.drawImage(GamePanel.textures.itemBox[0][0],xpos+480,ypos+(i*24),null);
		//			}
		//			g.drawString(tmAndHM.get(i).name, xpos+490, ypos+(i*24)+17);
		//			g.drawString("x"+tmAndHM.get(i).amount, xpos+490+105, ypos+(i*24)+17);
		//		}
		//		//draw berries
		//		g.setColor(new Color(240,240,240));
		//		g.fillRect(xpos+636, ypos-4, 158, 488);
		//		g.setColor(Color.black);
		//		g.drawRect(xpos+636, ypos-4, 158, 488);
		//		g.setColor(Color.red);
		//		g.drawRect(xpos+637, ypos-3, 156, 486);
		//		g.setColor(Color.black);
		//		g.drawRect(xpos+638, ypos-2, 154, 484);
		//		for(int i = 0; i<berries.size()&&i<20;i++){
		//			if(currentMenu==4 && i==currentItem){
		//				g.drawImage(GamePanel.textures.itemBox[0][1],xpos+640,ypos+(i*24),null);
		//			}
		//			else{
		//				g.drawImage(GamePanel.textures.itemBox[0][0],xpos+640,ypos+(i*24),null);
		//			}
		//			g.drawString(berries.get(i).name, xpos+650, ypos+(i*24)+17);
		//			g.drawString("x"+berries.get(i).amount, xpos+650+105, ypos+(i*24)+17);
		//		}
		//		//draw key items
		//		g.setColor(new Color(240,240,240));
		//		g.fillRect(xpos+796, ypos-4, 158, 488);
		//		g.setColor(Color.black);
		//		g.drawRect(xpos+796, ypos-4, 158, 488);
		//		g.setColor(Color.red);
		//		g.drawRect(xpos+797, ypos-3, 156, 486);
		//		g.setColor(Color.black);
		//		g.drawRect(xpos+798, ypos-2, 154, 484);
		//		for(int i = 0; i<keyItems.size()&&i<20;i++){
		//			if(currentMenu==5 && i==currentItem){
		//				g.drawImage(GamePanel.textures.itemBox[0][1],xpos+800,ypos+(i*24),null);
		//			}
		//			else{
		//				g.drawImage(GamePanel.textures.itemBox[0][0],xpos+800,ypos+(i*24),null);
		//			}
		//			g.drawString(keyItems.get(i).name, xpos+810, ypos+(i*24)+17);
		//			g.drawString("x"+keyItems.get(i).amount, xpos+810+105, ypos+(i*24)+17);
		//		}
	}
}
