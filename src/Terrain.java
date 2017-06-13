import java.awt.Color;
/**
 * @author Nigel Cyril
 * @author Jonathan Chsung
 * @date 11/11/2014
 * @class 17200 Principles of Comp Sci II
 * 
 */

//Class
public class Terrain{

	//Data Members
	private int W;  // Width of Grid
	private int H;  // Height of Grid
	private int[][] state; //0-empty, 1-burning, 2-forest, 3-wet
	private int[][] nextState; //0-empty, 1-burning, 2-forest, 3-wet
	
	/**
	 * constructor for Terrain Class
	 * @param width
	 * @param height
	 */
	
	//Method for Terrain setup
	public Terrain (int width, int height){
  	    W = width;
		H = height;

		state = new int[W][H];
		nextState = new int[W][H];

	    for (int i = 0; i<W; i++){
	    	for (int j=0; j <H; j++){
	    		state[i][j] = 2;
	    		nextState[i][j] = 2;
	    	}
	    }

	    StdDraw.setCanvasSize(500,500);
        StdDraw.setXscale(0, W);
        StdDraw.setYscale(0, H);
	    this.update();        
	}	

	/**
	 * updates terrain by swapping next state as current state and redrawing
	 */
	
	//StdDraw updates
	public void update(){
        for (int i = 0; i<W; i++){
	   		for (int j=0; j <H; j++){
	   			state[i][j] = nextState[i][j];
	    		
	   			Color c = new Color(30,30,30);
	   			if (state[i][j] == 1){ c = new Color(255,30,30);} 
    			else if (state[i][j] == 2){ c = new Color(30,200,30);}
    			else if (state[i][j]==3){ c = new Color(30,60,200);}
	    		StdDraw.setPenColor(c);
	    		StdDraw.filledSquare(i+.45, j+.45, 0.45);
	   		}
	   	}
	}
	
	
	//Getters
	public int getH(){return H;}
	public int getW(){return W;}
	public int getState(int w, int h){return state[w][h];}
	public int getNextState(int w, int h){return nextState[w][h];}
	
	//Configures tiles that have been effect by fire or water.
	public void setBurning(int Width, int Height){
		nextState[Width][Height] = 1;
	}
	public void setEmpty(int Width, int Height){
		nextState[Width][Height] = 0;
	}
	public void setWet(int Width, int Height){
		nextState[Width][Height] = 3;
	}
	public void setForrest(int Width, int Height){
		nextState[Width][Height] = 2;
	}
	
	/**
	 * 
	 * @param Width
	 * @param Height
	 * @return false if fire does not come in contact with neighbors
	 */
	public boolean hasBurningNeighbor(int Width, int Height){
		if(state[Width][Height]!=0&&state[Width][Height]!=3){
			if (Width<W){if (state[Width+1][Height]==1){return true;}}
			if(Width-1>0){if(state[Width-1][Height]==1){return true;}}
			if(Height<H){if(state[Width][Height+1]==1){return true;}}
			if(Height-1>0){if(state[Width][Height-1]==1){return true;}}
		}
		return false;
	}
	//Notes the tile that has water next to it
	public boolean hasWetNeighbor(int Width, int Height){
		if (Width<W){if (state[Width+1][Height]==3){return true;}}
		if(Width-1>0){if(state[Width-1][Height]==3){return true;}}
		if(Height<H){if(state[Width][Height+1]==3){return true;}}
		if(Height-1>0){if(state[Width][Height-1]==3){return true;}}
		return false;
	}
	//Creates burnt tile
	public boolean wasBurning(int Width, int Height){
		if (state[Width][Height]==1){return true;}
		else{return false;}
	}
	//Replenishes Forest
	public boolean wasWet(int Width, int Height){
		if (state[Width][Height]==3){return true;}
		else{return false;}
	}
}//end class