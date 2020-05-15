package com.bertoni.poker.util;

public class ConstantPoker {

	public final static int ROYAL_FLUSH = 10;
	public final static int STRAIGHT_FLUSH = 9;
	public final static int FOUR_OF_A_KIND = 8;
	public final static int FULL_HOUSE = 7;
	public final static int FLUSH = 6;
	public final static int STRAIGHT = 5;
	public final static int THREE_OF_A_KIND = 4;
	public final static int TWO_PAIRS = 3;
	public final static int ONE_PAIR = 2;
	public final static int HIGH_CARD = 1;
	
	public final static double ROYAL_FLUSH_CUM = 0.000154;
	public final static double ROYAL_FLUSH_WIN = 100 - ROYAL_FLUSH_CUM;
	
	public final static double STRAIGHT_FLUSH_CUM = 0.0015;
	public final static double STRAIGHT_FLUSH_WIN = 100 - STRAIGHT_FLUSH_CUM;
	public final static double STRAIGHT_FLUSH_TIE = 100 -  STRAIGHT_FLUSH_WIN - ROYAL_FLUSH_CUM;
	
	public final static double FOUR_CUM = 0.0256;
	public final static double FOUR_WIN = 100 - FOUR_CUM;
	public final static double FOUR_TIE = 100 -  FOUR_WIN - STRAIGHT_FLUSH_CUM;	
	
	public final static double FULL_HOUSE_CUM = 0.17;
	public final static double FULL_HOUSE_WIN = 100 - FULL_HOUSE_CUM;
	public final static double FULL_HOUSE_TIE = 100 -  FULL_HOUSE_WIN - FOUR_CUM;
	
	public final static double FLUSH_CUM = 0.367;
	public final static double FLUSH_WIN = 100 - FLUSH_CUM;
	public final static double FLUSH_TIE = 100 -  FLUSH_WIN - FULL_HOUSE_CUM;
	
	public final static double STRAIGHT_CUM = 0.76;
	public final static double STRAIGHT_WIN = 100 - STRAIGHT_CUM;
	public final static double STRAIGHT_TIE = 100 -  STRAIGHT_WIN - FLUSH_CUM;
	
	public final static double THREE_CUM = 2.87;
	public final static double THREE_WIN = 100 - THREE_CUM;
	public final static double THREE_TIE = 100 -  THREE_WIN - STRAIGHT_CUM;
	
	public final static double TWO_PAIRS_CUM = 7.62;
	public final static double TWO_PAIRS_WIN = 100 - TWO_PAIRS_CUM;
	public final static double TWO_PAIRS_TIE = 100 -  TWO_PAIRS_WIN - THREE_CUM;
	
	public final static double ONE_PAIR_CUM = 49.9;
	public final static double ONE_PAIR_WIN = 100 - ONE_PAIR_CUM;
	public final static double ONE_PAIR_TIE = 100 -  ONE_PAIR_WIN - TWO_PAIRS_CUM;
	
	public final static double HIGH_CARD_CUM = 100;
	public final static double HIGH_CARD_WIN = 100 - HIGH_CARD_CUM;
	public final static double HIGH_CARD_TIE = 100 -  HIGH_CARD_WIN - ONE_PAIR_CUM;

}
