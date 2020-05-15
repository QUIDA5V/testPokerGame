package com.bertoni.poker;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.bertoni.poker.util.PokerHelper;

public class TestPokerGameComparison {
	int player1 = 1;
	int player2 = 2;
	
	@Test
	public void compareHighCard_thereIsAWinner(){
    	String[] hand1 = "9C JD AC 8D KC".split(" ");
    	String[] hand2 = "AS KD 3D JD 8H".split(" ");
    	
		int[] cardsnumber1 = PokerHelper.getHandNumbers(hand1,false);
		int[] cardsnumber2 = PokerHelper.getHandNumbers(hand2,false);
		
		PokerGame.payer1Wins = 0;

		PokerGame.compareHighCard(cardsnumber1,cardsnumber2,null,0,0);

		assertEquals(1,PokerGame.payer1Wins);
    }
	@Test
	public void compareHighCard_tie(){
    	String[] hand1 = "9S TD 6D 7D 8H".split(" ");
    	String[] hand2 = "9C TD 7C 6D 8C".split(" ");
    	
		int[] cardsnumber1 = PokerHelper.getHandNumbers(hand1,false);
		int[] cardsnumber2 = PokerHelper.getHandNumbers(hand2,false);
		
		PokerGame.tie = 0;

		PokerGame.compareHighCard(cardsnumber1,cardsnumber2,null,0,0);

		assertEquals(1,PokerGame.tie);
    }
    @Test
	public void compareOnePair_thereIsAWinner() {
    	String[] hand1 = "AS KD 3D JD AH".split(" ");
    	String[] hand2 = "9C 7D 7C 6D TC".split(" ");
		PokerGame.getTypeOfHand(hand1, player1);
		PokerGame.getTypeOfHand(hand2, player2);
		
		int[] cardsnumber1 = PokerHelper.getHandNumbers(hand1,false);
		int[] cardsnumber2 = PokerHelper.getHandNumbers(hand2,false);

		PokerGame.payer1Wins = 0;
		PokerGame.payer2Wins = 0;
		PokerGame.compareOnePair(PokerGame.cardResult[player1-1],
				                 PokerGame.cardResult[player2-1],
				                 cardsnumber1,cardsnumber2);

		
		assertEquals(1,PokerGame.payer1Wins);
		
    	hand1 = "AS 5D 3D JD AH".split(" ");
    	hand2 = "9C KD AC 6D AC".split(" ");
		PokerGame.getTypeOfHand(hand1, player1);
		PokerGame.getTypeOfHand(hand2, player2);
		
		cardsnumber1 = PokerHelper.getHandNumbers(hand1,false);
		cardsnumber2 = PokerHelper.getHandNumbers(hand2,false);

		
		PokerGame.compareOnePair(PokerGame.cardResult[player1-1],
				                 PokerGame.cardResult[player2-1],
				                 cardsnumber1,cardsnumber2);	
		assertEquals(1,PokerGame.payer2Wins);
    }
    
	@Test
	public void compareOnePair_tie() {
    	String[] hand1 = "AS KD 3D JD AH".split(" ");
    	String[] hand2 = "JC 3D KC AD AC".split(" ");
		PokerGame.getTypeOfHand(hand1, player1);
		PokerGame.getTypeOfHand(hand2, player2);
		
		int[] cardsnumber1 = PokerHelper.getHandNumbers(hand1,false);
		int[] cardsnumber2 = PokerHelper.getHandNumbers(hand2,false);

		PokerGame.tie = 0;
		
		PokerGame.compareOnePair(PokerGame.cardResult[player1-1],
				                 PokerGame.cardResult[player2-1],
				                 cardsnumber1,cardsnumber2);

		
		assertEquals(1,PokerGame.tie);
    }
    
	@Test
	public void compareTwoPair_thereIsAWinner() {
    	String[] hand1 = "AS JS 3D JD AH".split(" ");
    	String[] hand2 = "AC 7D 7C 6D AC".split(" ");
		PokerGame.getTypeOfHand(hand1, player1);
		PokerGame.getTypeOfHand(hand2, player2);
		
		int[] cardsnumber1 = PokerHelper.getHandNumbers(hand1,false);
		int[] cardsnumber2 = PokerHelper.getHandNumbers(hand2,false);

		PokerGame.payer1Wins = 0;
		PokerGame.payer2Wins = 0;

		PokerGame.compareTwoPair(PokerGame.cardsResult[player1-1],
				                 PokerGame.cardsResult[player2-1],
				                 cardsnumber1,cardsnumber2);

		assertEquals(1,PokerGame.payer1Wins);
		
    	hand1 = "AS 5D JS JD AH".split(" ");
    	hand2 = "KC KD AC 6D AC".split(" ");
		PokerGame.getTypeOfHand(hand1, player1);
		PokerGame.getTypeOfHand(hand2, player2);
		
		cardsnumber1 = PokerHelper.getHandNumbers(hand1,false);
		cardsnumber2 = PokerHelper.getHandNumbers(hand2,false);

		PokerGame.compareTwoPair(PokerGame.cardsResult[player1-1],
                PokerGame.cardsResult[player2-1],
                cardsnumber1,cardsnumber2);	
		
		assertEquals(1,PokerGame.payer2Wins);
		
    	hand1 = "AS 8D KS KD AH".split(" ");
    	hand2 = "KC KD AC 6D AC".split(" ");
		PokerGame.getTypeOfHand(hand1, player1);
		PokerGame.getTypeOfHand(hand2, player2);
		
		cardsnumber1 = PokerHelper.getHandNumbers(hand1,false);
		cardsnumber2 = PokerHelper.getHandNumbers(hand2,false);

		PokerGame.compareTwoPair(PokerGame.cardsResult[player1-1],
                PokerGame.cardsResult[player2-1],
                cardsnumber1,cardsnumber2);	
		
		assertEquals(2,PokerGame.payer1Wins);
    }
	
	@Test
	public void compareTwoPair_tie() {
		String[] hand1 = "AS 8D KS KD AH".split(" ");
		String[] hand2 = "KC KD AC 8D AC".split(" ");
		PokerGame.getTypeOfHand(hand1, player1);
		PokerGame.getTypeOfHand(hand2, player2);
		
		int[] cardsnumber1 = PokerHelper.getHandNumbers(hand1,false);
		int[] cardsnumber2 = PokerHelper.getHandNumbers(hand2,false);
		PokerGame.tie = 0;
		
		PokerGame.compareTwoPair(PokerGame.cardsResult[player1-1],
                PokerGame.cardsResult[player2-1],
                cardsnumber1,cardsnumber2);	
		
		assertEquals(1,PokerGame.tie);
	}
	@Test
	public void compareThreeOfAKind_thereIsAWinner() {
    	String[] hand1 = "AS JS AD JD AH".split(" ");
    	String[] hand2 = "QD 7D QC 6D QH".split(" ");
		PokerGame.getTypeOfHand(hand1, player1);
		PokerGame.getTypeOfHand(hand2, player2);
		
		int[] cardsnumber1 = PokerHelper.getHandNumbers(hand1,false);
		int[] cardsnumber2 = PokerHelper.getHandNumbers(hand2,false);

		PokerGame.payer1Wins = 0;
		PokerGame.payer2Wins = 0;

		PokerGame.compareThreeOfAKind(PokerGame.cardResult[player1-1],
				                      PokerGame.cardResult[player2-1], cardsnumber1, cardsnumber2);

		assertEquals(1,PokerGame.payer1Wins);
		
    	hand1 = "TS 5D TS JD TH".split(" ");
    	hand2 = "TC TD JC 6D TS".split(" ");
		PokerGame.getTypeOfHand(hand1, player1);
		PokerGame.getTypeOfHand(hand2, player2);
		
		cardsnumber1 = PokerHelper.getHandNumbers(hand1,false);
		cardsnumber2 = PokerHelper.getHandNumbers(hand2,false);

		PokerGame.compareThreeOfAKind(PokerGame.cardResult[player1-1],
                PokerGame.cardResult[player2-1], cardsnumber1, cardsnumber2);
		
		assertEquals(1,PokerGame.payer2Wins);
    }	
	@Test
	public void compareThreeOfAKind_tie() {
    	String[] hand1 = "QS TS QD JD QH".split(" ");
    	String[] hand2 = "QD JD QC TD QH".split(" ");
		PokerGame.getTypeOfHand(hand1, player1);
		PokerGame.getTypeOfHand(hand2, player2);
		
		int[] cardsnumber1 = PokerHelper.getHandNumbers(hand1,false);
		int[] cardsnumber2 = PokerHelper.getHandNumbers(hand2,false);

		PokerGame.tie = 0;

		PokerGame.compareThreeOfAKind(PokerGame.cardResult[player1-1],
				                      PokerGame.cardResult[player2-1], cardsnumber1, cardsnumber2);

		assertEquals(1,PokerGame.tie);
	}
	
	@Test
	public void compareStraight_thereIsAWinner() {
		String[] hand1 = "QD JD TC 9D QH".split(" ");
    	String[] hand2 = "6S TS 7D 8D 9H".split(" ");
		PokerGame.getTypeOfHand(hand1, player1);
		PokerGame.getTypeOfHand(hand2, player2);

		PokerGame.payer1Wins = 0;
		PokerGame.payer2Wins = 0;

		PokerGame.compareStraight(PokerGame.cardResult[player1-1],PokerGame.cardResult[player2-1]);

		assertEquals(1,PokerGame.payer1Wins);
    }	
	@Test
	public void compareStraight_tie() {
		String[] hand1 = "QD JD TC 9D QH".split(" ");
    	String[] hand2 = "JS QS TD 8D 9H".split(" ");
		PokerGame.getTypeOfHand(hand1, player1);
		PokerGame.getTypeOfHand(hand2, player2);

		PokerGame.tie = 0;

		PokerGame.compareStraight(PokerGame.cardResult[player1-1],PokerGame.cardResult[player2-1]);

		assertEquals(1,PokerGame.tie);
    }
	@Test
	public void compareFlush_thereIsAWinner() {
		String[] hand1 = "AD JD 2D 9D 4D".split(" ");
    	String[] hand2 = "6S AS 3S 8S 9S".split(" ");
		PokerGame.getTypeOfHand(hand1, player1);
		PokerGame.getTypeOfHand(hand2, player2);

		int[] cardsnumber1 = PokerHelper.getHandNumbers(hand1,false);
		int[] cardsnumber2 = PokerHelper.getHandNumbers(hand2,false);
		
		PokerGame.payer1Wins = 0;
		PokerGame.payer2Wins = 0;

		PokerGame.compareFlush(cardsnumber1, cardsnumber2);

		assertEquals(1,PokerGame.payer1Wins);
    }
	@Test
	public void compareFlush_tie() {
		String[] hand1 = "AD 6D 3D 9D 8D".split(" ");
    	String[] hand2 = "6S AS 3S 8S 9S".split(" ");
		PokerGame.getTypeOfHand(hand1, player1);
		PokerGame.getTypeOfHand(hand2, player2);

		int[] cardsnumber1 = PokerHelper.getHandNumbers(hand1,false);
		int[] cardsnumber2 = PokerHelper.getHandNumbers(hand2,false);
		
		PokerGame.tie = 0;

		PokerGame.compareFlush(cardsnumber1, cardsnumber2);

		assertEquals(1,PokerGame.tie);
    }
	@Test
	public void compareFull_thereIsAWinner() {
		String[] hand1 = "AS AH 3S AD 3H".split(" ");
		String[] hand2 = "AD AS 2H 2D 4H".split(" ");
		
		PokerGame.getTypeOfHand(hand1, player1);
		PokerGame.getTypeOfHand(hand2, player2);

		PokerGame.payer1Wins = 0;
		PokerGame.payer2Wins = 0;
		
		PokerGame.compareFullHouse(PokerGame.cardsResult[player1-1], PokerGame.cardsResult[player2-1]);
		assertEquals(1,PokerGame.payer1Wins);
 
		hand1 = "QS QH 3S QD 3H".split(" ");
		hand2 = "AD AS 2D 2D 4H".split(" ");
		
		PokerGame.getTypeOfHand(hand1, player1);
		PokerGame.getTypeOfHand(hand2, player2);

		
		PokerGame.compareFullHouse(PokerGame.cardsResult[player1-1], PokerGame.cardsResult[player2-1]);
		assertEquals(1,PokerGame.payer2Wins);
		
	}
	@Test
	public void compareFull_tie() {
		String[] hand1 = "AS AH 3S AD 3H".split(" ");
		String[] hand2 = "AD AS 3H 3D AH".split(" ");
		
		PokerGame.getTypeOfHand(hand1, player1);
		PokerGame.getTypeOfHand(hand2, player2);

		PokerGame.tie = 0;

		PokerGame.compareFullHouse(PokerGame.cardsResult[player1-1], PokerGame.cardsResult[player2-1]);
		assertEquals(1,PokerGame.tie);
	}
	@Test
	public void compareFourOfAKind__thereIsAWinner() {
		String[] hand1 = "KC KH KS AD KD".split(" ");
		String[] hand2 = "JD JS 3H JC JH".split(" ");
		
		PokerGame.getTypeOfHand(hand1, player1);
		PokerGame.getTypeOfHand(hand2, player2);
		
		PokerGame.payer1Wins = 0;
		PokerGame.payer2Wins = 0;

		PokerGame.compareFourOfAKind(PokerGame.cardsResult[player1-1], PokerGame.cardsResult[player2-1]);

		assertEquals(1,PokerGame.payer1Wins);

		hand1 = "KC KH KS QD KD".split(" ");
		hand2 = "KD KS KH KC AH".split(" ");
		
		PokerGame.getTypeOfHand(hand1, player1);
		PokerGame.getTypeOfHand(hand2, player2);
		
		PokerGame.compareFourOfAKind(PokerGame.cardsResult[player1-1], PokerGame.cardsResult[player2-1]);

		assertEquals(1,PokerGame.payer2Wins);
		
	}
	@Test
	public void compareFourOfAKind__tie() {
		String[] hand1 = "KC KH KS AD KD".split(" ");
		String[] hand2 = "KC AH KS KH KD".split(" ");
		
		PokerGame.getTypeOfHand(hand1, player1);
		PokerGame.getTypeOfHand(hand2, player2);
		
		PokerGame.tie = 0;
		PokerGame.compareFourOfAKind(PokerGame.cardsResult[player1-1], PokerGame.cardsResult[player2-1]);

		assertEquals(1,PokerGame.tie);
		
	}
	@Test
	public void compareStraightFlush__thereIsAWinner() {
		String[] hand1 = "KC 9C JC TC QC".split(" ");
		String[] hand2 = "9D JD QD TD 8D".split(" ");
		
		PokerGame.getTypeOfHand(hand1, player1);
		PokerGame.getTypeOfHand(hand2, player2);
		
		PokerGame.payer1Wins = 0;

		PokerGame.compareStraightFlush(PokerGame.cardResult[player1-1], PokerGame.cardResult[player2-1]);

		assertEquals(1,PokerGame.payer1Wins);
	}
	@Test
	public void compareStraightFlush__tie() {
		String[] hand1 = "KC 9C JC TC QC".split(" ");
		String[] hand2 = "9D JD QD TD KD".split(" ");
		
		PokerGame.getTypeOfHand(hand1, player1);
		PokerGame.getTypeOfHand(hand2, player2);
		
		PokerGame.tie = 0;

		PokerGame.compareStraightFlush(PokerGame.cardResult[player1-1], PokerGame.cardResult[player2-1]);

		assertEquals(1,PokerGame.tie);
	}

}
