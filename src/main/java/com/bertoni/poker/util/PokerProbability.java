package com.bertoni.poker.util;

public class PokerProbability {

	public static double getProbabilityWinRoyalFlush() {
		return ConstantPoker.ROYAL_FLUSH_WIN;
	}
	public static double getProbabilityWinStraightFlush(int highCard) {
		
		return  ConstantPoker.STRAIGHT_FLUSH_WIN + ConstantPoker.STRAIGHT_FLUSH_TIE*(highCard-5)/10.0;

	}
	public static double getProbabilityWinFour(int fourOfAKind , int numberLeft) {
		double result;
		result = ConstantPoker.FOUR_WIN + ConstantPoker.FOUR_TIE*((fourOfAKind-2)/13.0 +
				(numberLeft-2)*1.0/Math.pow(13, 2));
		return result;
	}
	public static double getProbabilityWinFullHouse(int threeCard , int twoCard) {
		double result;
		result = ConstantPoker.FULL_HOUSE_WIN + ConstantPoker.FULL_HOUSE_TIE*((threeCard-2)/13.0 +
				 (twoCard-2)*1.0/Math.pow(13, 2));
		return result;		
	}

	public static double getProbabilityWinFlush(int[] hand) {
		double result;
		result = ConstantPoker.FLUSH_WIN +
			ConstantPoker.FLUSH_TIE*PokerHelper.getHighCardsProbabilityAC(0,PokerHelper.getOrderedDesc(hand),0,5,0.0,1);
		
		return result;
	}
	public static double getProbabilityWinStraight(int highCard) {
		
		return ConstantPoker.STRAIGHT_WIN + ConstantPoker.STRAIGHT_TIE*(highCard-5)/10.0;
	}

	public static double getProbabilityWinThree(int threeCard,int[] hand) {
		double result;
		
		int[] handAux = new int[2];
		int counter = 0;
		for(int i = 0;i<hand.length; i++) {
			if(hand[i] != threeCard) {
				handAux[counter] = hand[i];
				counter++;
			}
		}

		result = ConstantPoker.THREE_WIN + ConstantPoker.THREE_TIE*( (threeCard-2)/13.0 +
				PokerHelper.getHighCardsProbabilityAC(0,PokerHelper.getOrderedDesc(handAux),0,2,0.0,1)/13.0);

		return result;
	}
	public static double getProbabilityWinTwo(int firstPair,int secondPair,int[] hand) {
		double result;
		
		int handAux = 0;
		for(int i = 0;i<hand.length; i++) {
			if(hand[i] != firstPair && hand[i] != secondPair ) {
				handAux = hand[i];
				break;
			}
		}
		
		result = ConstantPoker.TWO_PAIRS_WIN + ConstantPoker.TWO_PAIRS_TIE*(firstPair-2)/13 +
				 ConstantPoker.TWO_PAIRS_TIE*(secondPair-2)/Math.pow(13, 2)+
				 ConstantPoker.TWO_PAIRS_TIE*(handAux-2)/Math.pow(13, 3);
		
		return result;
	}

	public static double getProbabilityWinOne(int pair,int[] hand) {
		double result;
		
		int[] handAux = new int[3];
		int counter = 0;
		for(int i = 0;i<hand.length; i++) {
			if(hand[i] != pair) {
				handAux[counter] = hand[i];
				counter++;
			}
		}

		result = ConstantPoker.ONE_PAIR_WIN + ConstantPoker.ONE_PAIR_TIE* ((pair-2)/13.0 +
				PokerHelper.getHighCardsProbabilityAC(0,PokerHelper.getOrderedDesc(handAux),0,3,0.0,1)/13.0);

		return result;
	}
	
	public static double getProbabilityWinHighCard(int[] hand) {
		
		return  ConstantPoker.HIGH_CARD_TIE*PokerHelper.getHighCardsProbabilityAC(0,PokerHelper.getOrderedDesc(hand),0,5,0.0,1);
		
	}	
}
