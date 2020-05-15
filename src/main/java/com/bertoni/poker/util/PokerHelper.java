package com.bertoni.poker.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class PokerHelper {

	public static int[] getHandNumbers(String[] hand,boolean isMinA) {
	   int[] handNumbers = new int[5];
	   
	   String s = "";
	   for(int i=0;i<5;i++) {
		   s = hand[i].substring(0, 1);
		   if("T".equals(s)) 
			   handNumbers[i] =10;
		   else if("J".equals(s))
			   handNumbers[i] =11;
		   else if("Q".equals(s))
			   handNumbers[i] =12;
		   else if("K".equals(s))
			   handNumbers[i] =13;
		   else if("A".equals(s)) 
			   handNumbers[i] = isMinA ? 1 : 14;
		   else
		       handNumbers[i] = Integer.parseInt(s);
	   }
	   return handNumbers;
	}

	
	public static int getHighCard(int[] hand,int[] cards_notInclude) {
		int max = 0;
		boolean skip;
		for(int i=0;i<hand.length;i++) {
			skip = false;
			for(int j=0;j<cards_notInclude.length;j++) {
				if(hand[i] == cards_notInclude[j]) {
					skip = true;
					break;
				}	
			}
			if(skip)
				continue;
			
			if(max < hand[i])
			   max = hand[i];
		}
		return max;
		
	}
	public static int[] getOrderedDesc(int[] hand) { 
		
		int[] descendingHand = new int[hand.length];
		Arrays.sort(hand);
		for (int i = 0; i < hand.length; i++) {
			   descendingHand[i] = hand[hand.length-i-1];
		};
		 
		return descendingHand;
		
	}
	
	public static double getHighCardsProbability(double factor , int[] hand,int initPow) { 
		double result = 0;
		for (int i = 0; i < hand.length; i++) {
			result = result + factor*(hand[i]-2)/Math.pow(13, initPow);
			initPow++;
		}
		return result;	
	}
	public static double getHighCardsProbabilityAC(int counter, int[] hand,int index,int limit,double result,int div) { 

		div = div*(13-counter);

		result = result + (hand[index]-2)*1.0/div;

		counter++;
		index++;

		if(counter == limit)
			return result;
		else 
			return getHighCardsProbabilityAC(counter,hand,index,limit,result,div);
	}
	

	public static void writeResultGame(int payer1Wins,int payer2Wins,int tie,Map<Integer, List<Double>> mapProbabilityHand) throws IOException {
		File file = new File("PokerResult.txt");
		file.createNewFile();

		StringBuilder buffer = new StringBuilder();
		buffer.append("1: "+payer1Wins).append(System.lineSeparator());
		buffer.append("2: "+payer2Wins).append(System.lineSeparator());
		buffer.append("3: "+tie).append(System.lineSeparator());
		buffer.append("4: "+System.lineSeparator());
		buffer.append("---------PLAYER 1 --------- | ------ PLAYER 2 -------------- "+System.lineSeparator());
		
		List<Double> probabilitiesPlayer1 = mapProbabilityHand.get(0);
		List<Double> probabilitiesPlayer2 = mapProbabilityHand.get(1);
		int sizeGames = probabilitiesPlayer2.size();
		
		BigDecimal player1Prob,player2Prob;
		for(int i = 0;i < sizeGames;i++) {
			player1Prob = BigDecimal.valueOf(probabilitiesPlayer1.get(i)).setScale(2, RoundingMode.HALF_UP);
			player2Prob = BigDecimal.valueOf(probabilitiesPlayer2.get(i)).setScale(2, RoundingMode.HALF_UP);
			buffer.append("         "+player1Prob+"%             |        "+player2Prob+"%"+System.lineSeparator());
		}

		FileWriter writer = new FileWriter(file);
		writer.write(buffer.toString());
		writer.close();
	}
}
