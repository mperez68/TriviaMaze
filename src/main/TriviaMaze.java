package main;

import java.awt.EventQueue;

import game.GameMap;

public class TriviaMaze {

	/**
	 * Begins program.
	 * @param theArgs System parameters.
	 */
	public static void main(String[] theArgs) {
		GameMap m = new GameMap(5,5);
		
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
        		m.start();
            }
        });
	}
}
