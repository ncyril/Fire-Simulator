/**
 * @author Nigel Cyril
 * @author Jonathan Chung
 * @date 11/11/2014
 * @class 17200 Principles of Comp Sci II
 * 
 */
import java.util.Random;

public class FireSim {
	
	//Data Members
	public Terrain t;
	public int Width;
	public int Height;
	public Random Rand = new Random();
	
	//Constructor
	public FireSim(int i, int j) {
		Width = i;
		Height = j;
		
	}
	public void setTerrain(Terrain ter){
		t = ter;
	}
	//Sets speed for which both water and fire to spread
	public static void delay(int millsec) {
		try {
			Thread.sleep(millsec);
		} catch (Exception e) {
			System.out.println("Error in delay function.");
		}
	}
	//Starts the fire at random spots
	public void startRandFire(){
		int randomH = Rand.nextInt((Width-2));
		int randomW = Rand.nextInt((Height-2));
		
		t.setBurning(randomW, randomH);
	}
	
	//Water at random spots
	public void startFireFight(){
		int randomH = Rand.nextInt((Width-2));
		int randomW = Rand.nextInt((Height-2));
		
		t.setWet(randomW, randomH);
	}
	//Spreads the fire out
	public Boolean spread(){
		int random = (int)(Math.random()*4+1);
		if (random>2){
			return true;
		} else {
			return false;
		}
	}
	//Spreads the water out
	public Boolean spreadW(){
		int random = (int)(Math.random()*4+1);
		if (random>2){
			return true;
		} else {
			return false;
		}
	}

	
//Main Methods
public static void main(String[] args){
	//Settings
	Terrain t = new Terrain(8,10);
	FireSim fs = new FireSim(8,10);
	fs.setTerrain(t);
	fs.startRandFire();
	fs.startFireFight();
	
	//Runs Program
	while(true){
		t.update();
		delay(400);
		for (int i=0; i<t.getW()-1;i++){
			for (int j=0; j<t.getH()-1;j++){
				if(t.hasBurningNeighbor(i, j)){
					if(fs.spread()){
						t.setBurning(i, j);
					}
				}else if(t.hasWetNeighbor(i, j)){
					if(fs.spreadW()){
						t.setWet(i, j);
					}
				}
				}
			}
		
		for (int i=0; i<t.getW();i++){
			for (int j=0; j<t.getH();j++){
				if(t.wasBurning(i, j)){
					t.setEmpty(i, j);
				}else if(t.wasWet(i, j)){
					t.setForrest(i, j);
				}
				}
			}
		}
}//main methods
}//end class

