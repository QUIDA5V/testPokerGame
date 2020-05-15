package com.bertoni.poker;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.bertoni.poker.util.ConstantPoker;
import com.bertoni.poker.util.PokerHelper;
import com.bertoni.poker.util.PokerProbability;

public class TestProbability {

	@Test
	public void probability_HighCard() {
    	String[] hand = "AS KD 3D JD 8H".split(" ");
    	int[] cardsnumber = PokerHelper.getHandNumbers(hand,false);

    	double factor = 100.0-ConstantPoker.ONE_PAIR_CUM;

 
    	double expected =  (14-2)/13.0+
    			           (13-2)/(13.0*12)+
    			           (11-2)/(13.0*12*11)+
    			           (8-2)/(13.0*12*11*10)+
    			           (3-2)/(13.0*12*11*10*9);
  
    	expected = factor * expected;
    	
    	double actual = PokerProbability.getProbabilityWinHighCard(cardsnumber);
		assertTrue(actual == expected);	
    }

	@Test
	public void probability_OnePair() {
    	String[] hand = "3S AH TD 8D 8H".split(" ");
    	int[] cardsnumber = PokerHelper.getHandNumbers(hand,false);

    	double factor = ConstantPoker.ONE_PAIR_TIE;
 
    	double expected =  (14-2)/13.0+
    			           (10-2)/(13.0*12)+
    			           (3-2)/(13.0*12*11);

    	expected = ConstantPoker.ONE_PAIR_WIN + factor * ((8-2)/13.0 + expected/13.0);
    	
    	double actual = PokerProbability.getProbabilityWinOne(8, cardsnumber);

		assertTrue(actual == expected);	
    }
	
	@Test
	public void probability_TwoPair() {
    	String[] hand = "3S 3H TD 8D 8H".split(" ");
    	int[] cardsnumber = PokerHelper.getHandNumbers(hand,false);

    	double factor = ConstantPoker.TWO_PAIRS_TIE;
 
    	double expected =  (8-2)/13.0+
    			           (3-2)/(13.0*13)+
    			           (10-2)/(13.0*13*13);

    	expected = ConstantPoker.TWO_PAIRS_WIN + factor * expected;
    	
    	double actual = PokerProbability.getProbabilityWinTwo(8, 3, cardsnumber);
    	
		assertTrue(actual == expected);	
    }
	
	@Test
	public void probability_ThreeOfAKind() {
    	String[] hand = "4S 4H TD 8D 4C".split(" ");
    	int[] cardsnumber = PokerHelper.getHandNumbers(hand,false);

    	double factor = ConstantPoker.THREE_TIE;
 
    	double expected =  (4-2)/13.0+
    			           (10-2)/(13.0*13)+
    			           (8-2)/(13.0*13*12);

    	expected = ConstantPoker.THREE_WIN + factor *(expected);
    	
    	double actual = PokerProbability.getProbabilityWinThree(4,cardsnumber);
    	
		assertTrue(actual == expected);	
    }
	
	@Test
	public void probability_straight() {
    	String[] hand = "5S 4H 6D 8D 7C".split(" ");
 
    	double factor = ConstantPoker.STRAIGHT_TIE;
 
    	double expected =  (8-5)/10.0;

    	expected = ConstantPoker.STRAIGHT_WIN + factor *(expected);
    	
    	double actual = PokerProbability.getProbabilityWinStraight(8);
    	
		assertTrue(actual == expected);	
    }
	
	@Test
	public void probability_Flush() {
    	String[] hand = "AS QD 5D JD 8D".split(" ");
    	int[] cardsnumber = PokerHelper.getHandNumbers(hand,false);

    	double factor = ConstantPoker.FLUSH_TIE;

 
    	double expected =  (14-2)/13.0+
    			           (12-2)/(13.0*12)+
    			           (11-2)/(13.0*12*11)+
    			           (8-2)/(13.0*12*11*10)+
    			           (5-2)/(13.0*12*11*10*9);
  
    	expected = ConstantPoker.FLUSH_WIN + factor * expected;
    	
    	double actual = PokerProbability.getProbabilityWinFlush(cardsnumber);
		assertTrue(actual == expected);	
    }
	@Test
	public void probability_FullHouse() {
    	String[] hand = "4S JD 4D JC 4H".split(" ");

    	double factor = ConstantPoker.FULL_HOUSE_TIE;

 
    	double expected =  (4-2)/13.0+
    			           (11-2)/(13.0*13);

    	expected = ConstantPoker.FULL_HOUSE_WIN + factor * expected;
    	
    	double actual = PokerProbability.getProbabilityWinFullHouse(4, 11);

		assertTrue(actual == expected);	
    }
	@Test
	public void probability_FourOfAKind() {
    	String[] hand = "4S 4C 4D TC 4H".split(" ");

    	double factor = ConstantPoker.FOUR_TIE;

 
    	double expected =  (4-2)/13.0+
    			           (10-2)/(13.0*13);

    	expected = ConstantPoker.FOUR_WIN + factor * expected;
    	
    	double actual = PokerProbability.getProbabilityWinFour(4, 10);

		assertTrue(actual == expected);	
    }
	@Test
	public void probability_StraightFlush() {
    	String[] hand = "4S 5S 8S 7S 6S".split(" ");

    	double factor = ConstantPoker.STRAIGHT_FLUSH_TIE;

 
    	double expected =  (8-5)/10.0;

    	expected = ConstantPoker.STRAIGHT_FLUSH_WIN + factor * expected;
    	
    	double actual = PokerProbability.getProbabilityWinStraightFlush(8);

		assertTrue(actual == expected);	
    }
	@Test
	public void probability_RoyalFlush() {

    	double expected = ConstantPoker.ROYAL_FLUSH_WIN;
    	double actual = PokerProbability.getProbabilityWinRoyalFlush();
		assertTrue(actual == expected);	
    }
}
