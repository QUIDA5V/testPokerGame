package com.bertoni.poker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.bertoni.poker.util.ConstantPoker;

public class TestPokerGame {

	int player1 = 1;
	int player2 = 2;
	
	@Test
	public void getTypeOfHand_HighCard() {
    	String[] hand = "AS KD 3D JD 8H".split(" ");
    	String[] hand2 = "9C 9D 7C 6D TC".split(" ");

		assertEquals(ConstantPoker.HIGH_CARD,PokerGame.getTypeOfHand(hand, player1));
		assertNotEquals(ConstantPoker.HIGH_CARD,PokerGame.getTypeOfHand(hand2, player2));
    }
	@Test
	public void getTypeOfHand_OnePair() {
    	String[] hand = "AS KD 3D AD 8H".split(" ");
    	String[] hand2 = "6C JD 7C 2D TC".split(" ");
    	
		assertEquals(ConstantPoker.ONE_PAIR,PokerGame.getTypeOfHand(hand, player1));
		//Validate the Pair number
		assertEquals(14,PokerGame.cardResult[player1-1]);
		
		assertNotEquals(ConstantPoker.ONE_PAIR,PokerGame.getTypeOfHand(hand2, player2));

    }
	@Test
	public void getTypeOfHand_TwoPair() {
    	String[] hand = "AS 3D 3D AD 8H".split(" ");
    	String[] hand2 = "6C TD 7C 6D TC".split(" ");
    	String[] hand3 = "6C 5D 7C 6D TC".split(" ");
    	
		assertEquals(ConstantPoker.TWO_PAIRS,PokerGame.getTypeOfHand(hand, player1));
		assertEquals(ConstantPoker.TWO_PAIRS,PokerGame.getTypeOfHand(hand2, player2));
		//Compare the Two Pairs ,the highest comes first
		assertEquals(14,PokerGame.cardsResult[player1-1][0]);
		assertEquals(3,PokerGame.cardsResult[player1-1][1]);
		assertEquals(10,PokerGame.cardsResult[player2-1][0]);
		assertEquals(6,PokerGame.cardsResult[player2-1][1]);
		
		assertNotEquals(ConstantPoker.TWO_PAIRS,PokerGame.getTypeOfHand(hand3, player1));
    }
	@Test
	public void getTypeOfHand_ThreeOfAKind() {
    	String[] hand = "AS 3D 3D 4D 3H".split(" ");
    	String[] hand2 = "6C TD 7C 6D TC".split(" ");
    	
		assertEquals(ConstantPoker.THREE_OF_A_KIND,PokerGame.getTypeOfHand(hand, player1));
		assertNotEquals(ConstantPoker.THREE_OF_A_KIND,PokerGame.getTypeOfHand(hand2, player2));
		//Compare the ThreeOfAKind card
		assertEquals(3,PokerGame.cardResult[player1-1]);
    }
	@Test
	public void getTypeOfHand_Straight() {
    	String[] hand = "2S 3D 5D 4D 6H".split(" ");
    	String[] hand2 = "AC TD JC KD QC".split(" ");
    	String[] hand3 = "2S 3D TD 4D 6H".split(" ");
    	String[] hand4 = "9D 4D TD QD JD".split(" ");

		assertEquals(ConstantPoker.STRAIGHT,PokerGame.getTypeOfHand(hand, player1));
		assertEquals(ConstantPoker.STRAIGHT,PokerGame.getTypeOfHand(hand2, player2));
		//Compare the High Card
		assertEquals(6,PokerGame.cardResult[player1-1]);
		assertEquals(14,PokerGame.cardResult[player2-1]);
		
		assertNotEquals(ConstantPoker.STRAIGHT,PokerGame.getTypeOfHand(hand3, player1));
		assertNotEquals(ConstantPoker.STRAIGHT,PokerGame.getTypeOfHand(hand4, player1));
    }
	@Test
	public void getTypeOfHand_Flush() {
    	String[] hand = "2D 3D 8D 4D AD".split(" ");
    	String[] hand2 = "2C TD JC KD QC".split(" ");
    	
		assertEquals(ConstantPoker.FLUSH,PokerGame.getTypeOfHand(hand, player1));
		assertNotEquals(ConstantPoker.FLUSH,PokerGame.getTypeOfHand(hand2, player2));
    }
	@Test
	public void getTypeOfHand_FullHouse() {
    	String[] hand = "2D 2D 2S AS AD".split(" ");
    	String[] hand2 = "2C 2D JC JD QC".split(" ");
    	
		assertEquals(ConstantPoker.FULL_HOUSE,PokerGame.getTypeOfHand(hand, player1));
		assertNotEquals(ConstantPoker.FULL_HOUSE,PokerGame.getTypeOfHand(hand2, player2));
		
		//Compare the Pairs ,the highest comes first
		assertEquals(2,PokerGame.cardsResult[player1-1][0]);
		assertEquals(14,PokerGame.cardsResult[player1-1][1]);
		
    }
	@Test
	public void getTypeOfHand_FourOfAKind() {
    	String[] hand = "2D 2D 2S AS 2D".split(" ");
    	String[] hand2 = "2C 2D JC JD QC".split(" ");
    	
		assertEquals(ConstantPoker.FOUR_OF_A_KIND,PokerGame.getTypeOfHand(hand, player1));
		assertNotEquals(ConstantPoker.FOUR_OF_A_KIND,PokerGame.getTypeOfHand(hand2, player2));
		
		//Compare the fourOfAkind card and the remaining card
		assertEquals(2,PokerGame.cardsResult[player1-1][0]);
		assertEquals(14,PokerGame.cardsResult[player1-1][1]);
		
    }
	@Test
	public void getTypeOfHand_StraightFlush() {
    	String[] hand = "2D 3D 4D AD 5D".split(" ");
    	String[] hand2 = "2D 3S 4D AD 5D".split(" ");
    	
		assertEquals(ConstantPoker.STRAIGHT_FLUSH,PokerGame.getTypeOfHand(hand, player1));
		assertNotEquals(ConstantPoker.STRAIGHT_FLUSH,PokerGame.getTypeOfHand(hand2, player2));
		
		//Compare the high card
		assertEquals(5,PokerGame.cardResult[player1-1]);
    }	
	@Test
	public void getTypeOfHand_RoyalFlush() {
    	String[] hand = "QD TD JD AD KD".split(" ");
    	String[] hand2 = "QD TS JD AD KD".split(" ");
    	
		assertEquals(ConstantPoker.ROYAL_FLUSH,PokerGame.getTypeOfHand(hand, player1));
		assertNotEquals(ConstantPoker.ROYAL_FLUSH,PokerGame.getTypeOfHand(hand2, player2));

    }	



}
