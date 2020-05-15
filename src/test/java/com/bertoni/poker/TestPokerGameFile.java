package com.bertoni.poker;

import static org.junit.Assert.assertEquals;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Test;

public class TestPokerGameFile {
	
    
	@Test
	public void playPokerGameTest() throws IOException {
 
        FileReader fileReader = new FileReader("src/test/resources/pokerTest_data.txt"); 
        BufferedReader br = new BufferedReader(fileReader);
        
        String line;
        
		String[] hands = new String[10];
		String[] hand1 = new String[5];
		String[] hand2 = new String[5];
		PokerGame.payer1Wins = 0;
		PokerGame.payer2Wins = 0;
		PokerGame.tie = 0;
		
        while ((line = br.readLine()) != null) {	            	
        	hands = line.split(" ");
    		for(int i=0;i < 10;i++) {
    			if(i<5)
    			  hand1[i] = hands[i];
    			else 
    			  hand2[i-5] = hands[i];
    		}
    		PokerGame.playPoker(hand1,hand2);
    			
        }
        br.close();
        int player1_winsExpected = 9;
        int player2_winsExpected = 7;
        int tieExpected = 4;
        
        int player1_winsActual = PokerGame.payer1Wins;
        int player2_winsActual = PokerGame.payer2Wins;
        int tieActual = PokerGame.tie;

        
		assertEquals(player1_winsExpected,player1_winsActual);
		assertEquals(player2_winsExpected,player2_winsActual);
		assertEquals(tieExpected,tieActual);
    }
	
}
