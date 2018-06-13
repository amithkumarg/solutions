package com.oss.shop.Assignment;

import java.util.Random;

public class Solution {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		new Game().isGameOver();
	}

}

class Game{
	boolean playerOneMove = true;
	int playerOnePoints;
	private Random random = new Random();
	int turnsCount;
	
	public void isGameOver() {

	    if (checkGameIsWon()) {
	        if (playerOneMove) {
	            player1Wins();
	        } else {
	            player2Wins();
	        }
	    } else if (turnsCount == 9) {
	        //draw();
	    } else {
	        playerOneMove = !playerOneMove;
	        if (!playerOneMove) {
	            final Handler handler = new Handler();
	            handler.postDelayed(new Runnable() {
	                public void run() {
	                    computerMove();
	                }
	            }, random.nextInt(2000 - 1000 + 1000) + 1000);
	        }
	    }
	}

	private void player1Wins() {
	    playerOnePoints++;
	    //Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
	    updatePointsText();
	    resetBoard();
	}
	
	private void player2Wins(){
		System.out.println("Player 2 Wins called");
	}

	private void resetBoard() {

	    final Handler handlerReset = new Handler();
	    handlerReset.postDelayed(new Runnable() {
	        public void run() {

	            for (int i = 0; i < 3; i++) {
	                for (int j = 0; j < 3; j++) {
	                    System.out.println("Executing handlerReset " + i + " " + j);
	                }
	            }

	        }
	        },2000);
	}
	
	private void computerMove(){
		System.out.println("Computer Moves");
		System.out.println(playerOneMove);
	}
	
	private void updatePointsText(){
		System.out.println("Text Points Updated");
	}
	
	private boolean checkGameIsWon(){
		return false;
	}
}

class Handler {
	public void postDelayed(Runnable runnable, int number){
		runnable.run();
	}
}
