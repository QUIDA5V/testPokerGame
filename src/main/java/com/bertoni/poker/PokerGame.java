package com.bertoni.poker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bertoni.poker.util.ConstantPoker;
import com.bertoni.poker.util.PokerProbability;
import com.bertoni.poker.util.PokerHelper;

public class PokerGame {

	public static int payer1Wins;
	public static int payer2Wins;
	public static int tie;
	public static Map<Integer, Integer> mapHand = new HashMap<Integer, Integer>();
	public static Map<Integer, List<Double>> mapProbabilityHand = new HashMap<Integer, List<Double>>();
	public static int[] cardResult = new int[2];
	public static int[][] cardsResult = new int[2][2];
	
	public static void main(String[] args) {
		
        InputStream inputStream = null;
        BufferedReader br = null;
		try {

				payer1Wins = 0;
				payer2Wins = 0;
				
	            ClassLoader classLoader = new PokerGame().getClass().getClassLoader();

	            File file = new File(classLoader.getResource("pokerdata.txt").getFile());
	            inputStream = new FileInputStream(file);
	            
	            br = new BufferedReader(new InputStreamReader(inputStream));
	            String line;
	            
	    		String[] hands = new String[10];
	    		String[] hand1 = new String[5];
	    		String[] hand2 = new String[5];

	            while ((line = br.readLine()) != null) {	            	
	            	hands = line.split(" ");
	        		for(int i=0;i < 10;i++) {
	        			if(i<5)
	        			  hand1[i] = hands[i];
	        			else 
	        			  hand2[i-5] = hands[i];
	        		}
	        		playPoker(hand1,hand2);	
	            }
 
	            PokerHelper.writeResultGame(payer1Wins,payer2Wins,tie,mapProbabilityHand);


		}catch(Exception e) {
			System.out.println("Error:"+e.getMessage());
		}finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(br != null) {
            	try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
            	
        }
	}
	
	public static void playPoker(String[] hand1,String[] hand2) {
		int typeOfHand1= getTypeOfHand(hand1,1);
		int typeOfHand2 = getTypeOfHand(hand2,2);

		int[] cardsnumber1 = PokerHelper.getHandNumbers(hand1,false);
		int[] cardsnumber2 = PokerHelper.getHandNumbers(hand2,false);
		
		if(typeOfHand1 > typeOfHand2) 
			payer1Wins++;
		else if(typeOfHand1 < typeOfHand2) 
			payer2Wins++;
		else { 
			if(typeOfHand1 == ConstantPoker.HIGH_CARD) 
				compareHighCard(cardsnumber1,cardsnumber2,null,0,0);
			else if(typeOfHand1 == ConstantPoker.ONE_PAIR) 
				compareOnePair(cardResult[0],cardResult[1],cardsnumber1,cardsnumber2);
			else if(typeOfHand1 == ConstantPoker.TWO_PAIRS) 
				compareTwoPair(cardsResult[0],cardsResult[1],cardsnumber1,cardsnumber2);
			else if(typeOfHand1 == ConstantPoker.THREE_OF_A_KIND) 			
				compareThreeOfAKind(cardResult[0],cardResult[1],cardsnumber1,cardsnumber2);
			else if(typeOfHand1 == ConstantPoker.STRAIGHT)
				compareStraight(cardResult[0],cardResult[1]);
			else if(typeOfHand1 == ConstantPoker.FLUSH)
				compareFlush(cardsnumber1,cardsnumber2);
			else if(typeOfHand1 == ConstantPoker.FULL_HOUSE)
				compareFullHouse(cardsResult[0],cardsResult[1]);
			else if(typeOfHand1 == ConstantPoker.FOUR_OF_A_KIND)
				compareFourOfAKind(cardsResult[0],cardsResult[1]);
			else if(typeOfHand1 == ConstantPoker.STRAIGHT_FLUSH)
				compareStraightFlush(cardResult[0],cardResult[1]);
			else
				tie++;
		}
		
		
		calculeProbabilityWin(typeOfHand1,0,cardsnumber1);
		calculeProbabilityWin(typeOfHand2,1,cardsnumber2);

	}
	
	
	
	public static int getTypeOfHand(String[] hand,int nroPlayer) {
		String type = hand[0].charAt(1)+"";
		int[] handNumbers = PokerHelper.getHandNumbers(hand,false);
		int nroPairs = 0;
		int temp = 0;
        int min = handNumbers[0];
		int max = min;	
		boolean sameType = true;
		int[] twoPairs = new int[2];
		
		mapHand.clear();
		
		for(int i=0;i<5;i++) {
			if(!type.equals(hand[i].charAt(1)+"")) 
				sameType = false;
			
			mapHand.compute(handNumbers[i],(key, value) -> value == null ? 1 : value + 1);
            if(min > handNumbers[i])
            	min = handNumbers[i];
            if(max < handNumbers[i])
            	max = handNumbers[i];
				
		}
		
		if(mapHand.size() == 5) {
			if(max == 14) {
				if(min == 10) { 
					if(sameType){
						return ConstantPoker.ROYAL_FLUSH;
					}else {
						cardResult[nroPlayer-1] = max;
						return ConstantPoker.STRAIGHT;
					}	
			    }else {
						//Take A as 1
						handNumbers = PokerHelper.getHandNumbers(hand,true);
						
				        min = handNumbers[0];
				        max = min;
						for(int i=0;i<5;i++) {
				            if(min > handNumbers[i])
				            	min = handNumbers[i];
				            if(max < handNumbers[i])
				            	max = handNumbers[i];
						}
						if(max == 5) {
							cardResult[nroPlayer-1] = max;
							if(sameType)
								return ConstantPoker.STRAIGHT_FLUSH;
							else 
								return ConstantPoker.STRAIGHT;

						}else if(sameType){
							return ConstantPoker.FLUSH;
						}else {
							return ConstantPoker.HIGH_CARD;
						}
							
			   }	
				
			}else if(max - min == 4) {
				cardResult[nroPlayer-1] = max;
				
				if(sameType)
					return ConstantPoker.STRAIGHT_FLUSH;
				else
					return ConstantPoker.STRAIGHT;		
				
			}else if(sameType){
				return ConstantPoker.FLUSH;
			}else 
				return ConstantPoker.HIGH_CARD;
			
	
		}else if(mapHand.size() == 4) {

			for (Map.Entry<Integer, Integer> entry : mapHand.entrySet()) {
				if(entry.getValue() == 2 ) {
					cardResult[nroPlayer-1] = entry.getKey();
				    break;
				}    
		    }
			return ConstantPoker.ONE_PAIR;

		}else if(mapHand.size() == 3) {

			for (Map.Entry<Integer, Integer> entry : mapHand.entrySet()) {
				if(entry.getValue() > 2 ) {
					cardResult[nroPlayer-1] = entry.getKey();
					return ConstantPoker.THREE_OF_A_KIND;
				}
					
				if(entry.getValue() == 2) {
					twoPairs[nroPairs] = entry.getKey();
					nroPairs++;
				}
		    }

	        if(twoPairs[0] < twoPairs[1]) {
	        		temp = twoPairs[0];
	        		twoPairs[0] = twoPairs[1];
	        		twoPairs[1] = temp;
	        }
	        cardsResult[nroPlayer-1] = twoPairs;
	        return ConstantPoker.TWO_PAIRS;
	        
		}else {
			boolean isFourOfAKind = false;

			for (Map.Entry<Integer, Integer> entry : mapHand.entrySet()) {
				if(entry.getValue() == 1)
					 cardsResult[nroPlayer-1][1] = entry.getKey();
				else if(entry.getValue() == 2)
					cardsResult[nroPlayer-1][1] = entry.getKey();
				else if(entry.getValue() == 3)
					cardsResult[nroPlayer-1][0] = entry.getKey();
				else if(entry.getValue() == 4) { 
					 cardsResult[nroPlayer-1][0] = entry.getKey();
					 isFourOfAKind = true;
				}
		    }
			
			if(isFourOfAKind) 
			    return ConstantPoker.FOUR_OF_A_KIND;
			else
				return ConstantPoker.FULL_HOUSE;
		}
		
	}
	
	public static void calculeProbabilityWin(int typeOfHand,int nroPlayer,int[] handNumbers) {
	
		if(typeOfHand == ConstantPoker.HIGH_CARD) {
			mapProbabilityHand.computeIfAbsent(nroPlayer, k->new ArrayList<Double>())
			.add(PokerProbability.getProbabilityWinHighCard(PokerHelper.getOrderedDesc(handNumbers)));
			
		}else if(typeOfHand == ConstantPoker.ONE_PAIR) {
			mapProbabilityHand.computeIfAbsent(nroPlayer, k->new ArrayList<Double>())
			.add(PokerProbability.getProbabilityWinOne(cardResult[nroPlayer],PokerHelper.getOrderedDesc(handNumbers)));
			
		}else if(typeOfHand == ConstantPoker.TWO_PAIRS) {
			mapProbabilityHand.computeIfAbsent(nroPlayer, k->new ArrayList<Double>())
			.add(PokerProbability.getProbabilityWinTwo(cardsResult[nroPlayer][0], cardsResult[nroPlayer][1],PokerHelper.getOrderedDesc(handNumbers)));
			
		}else if(typeOfHand == ConstantPoker.THREE_OF_A_KIND) {
			mapProbabilityHand.computeIfAbsent(nroPlayer, k->new ArrayList<Double>())
			.add(PokerProbability.getProbabilityWinThree(cardResult[nroPlayer], PokerHelper.getOrderedDesc(handNumbers)));		
			
		}else if(typeOfHand == ConstantPoker.STRAIGHT) {
			mapProbabilityHand.computeIfAbsent(nroPlayer, k->new ArrayList<Double>())
		    .add(PokerProbability.getProbabilityWinStraight(cardResult[nroPlayer]));			
			
		}else if(typeOfHand == ConstantPoker.FLUSH) {
			mapProbabilityHand.computeIfAbsent(nroPlayer, k->new ArrayList<Double>())
			.add(PokerProbability.getProbabilityWinFlush(PokerHelper.getOrderedDesc(handNumbers)));
			
		}else if(typeOfHand == ConstantPoker.FULL_HOUSE) {
			mapProbabilityHand.computeIfAbsent(nroPlayer, k->new ArrayList<Double>())
			.add(PokerProbability.getProbabilityWinFullHouse(cardsResult[nroPlayer][0], cardsResult[nroPlayer][1]));
			
		}else if(typeOfHand == ConstantPoker.FOUR_OF_A_KIND) {
			mapProbabilityHand.computeIfAbsent(nroPlayer, k->new ArrayList<Double>())
			.add(PokerProbability.getProbabilityWinFour(cardsResult[nroPlayer][0], cardsResult[nroPlayer][1]));
			
		}else if(typeOfHand == ConstantPoker.STRAIGHT_FLUSH) {
			mapProbabilityHand.computeIfAbsent(nroPlayer, k->new ArrayList<Double>())
			.add(PokerProbability.getProbabilityWinStraightFlush(cardResult[nroPlayer]));
			
		}else if(typeOfHand == ConstantPoker.ROYAL_FLUSH) {
			mapProbabilityHand.computeIfAbsent(nroPlayer, k->new ArrayList<Double>())
			.add(PokerProbability.getProbabilityWinRoyalFlush());
		}

	}
	public static void compareStraightFlush(int highCard1,int highCard2) {

		if(highCard1 > highCard2)
			 payer1Wins++;
		else if(highCard1 < highCard2)
			 payer2Wins++;
		else 
			 tie++;
	}
	public static void compareFourOfAKind(int[] fourOfAKind1,int[] fourOfAKind2) {

		if(fourOfAKind1[0] > fourOfAKind2[0] )
			 payer1Wins++;
		else if(fourOfAKind1[0] < fourOfAKind2[0])
			 payer2Wins++;
		else if(fourOfAKind1[1] > fourOfAKind2[1])
			 payer1Wins++;
		else if(fourOfAKind1[1] < fourOfAKind2[1])
			 payer2Wins++;
		else 
			 tie++;
	}
	public static void compareFullHouse(int[] fullHouse_hand1,int[] fullHouse_hand2) {
		if(fullHouse_hand1[0] > fullHouse_hand2[0])
			payer1Wins++;
		else if (fullHouse_hand1[0] < fullHouse_hand2[0])
			payer2Wins++;
		else if(fullHouse_hand1[1] > fullHouse_hand2[1])
			payer1Wins++;
		else if(fullHouse_hand1[1] < fullHouse_hand2[1])
			payer2Wins++;
		else 
			tie++;
	}
	public static void compareFlush( int[] handNumbers1, int[] handNumbers2) {
		compareHighCard(handNumbers1,handNumbers2,null,0,0);
	}
	public static void compareStraight(int highCard1,int highCard2) {
		if(highCard1 > highCard2)
			payer1Wins++;
		else if(highCard1 < highCard2)
			payer2Wins++;
		else 
			tie++;
	}		
	public static void compareThreeOfAKind(int threeCard1,int threeCard2,int[] cards1,int[] cards2) {

		if(threeCard1 > threeCard2)
			payer1Wins++;
		else if(threeCard1 < threeCard2)
			payer2Wins++;
		else 
			compareHighCard(cards1,cards2,null,threeCard1,0);

	}		
	public static void compareTwoPair(int[] two_pair1,int[] two_pair2,int[] cards1,int[] cards2) {
		
		if(two_pair1[0] > two_pair2[0])
			payer1Wins++;
		else if(two_pair1[0] < two_pair2[0])
			payer2Wins++;
		else if(two_pair1[1] > two_pair2[1])
			payer1Wins++;
		else if(two_pair1[1] < two_pair2[1])
			payer2Wins++;
		else {
			 int[] cardsNotToBeCompared = {two_pair1[0],two_pair1[1]};
			 compareHighCard(cards1,cards2,cardsNotToBeCompared,0,0);
		}
		
	}	
	public static void compareOnePair(int one_pair1,int one_pair2,int[] cards1,int[] cards2) {

		if(one_pair1 > one_pair2) 
			payer1Wins++;
		else if(one_pair1 < one_pair2) 
			payer2Wins++;
		else
			compareHighCard(cards1,cards2,null,one_pair1,0);

	}	

	public static void compareHighCard(int[] hand1,int[] hand2,int[] cards_notIncluded,int cardNotToBeIncluded,int counter) {

		int[] cardsNotToBeIncluded = null;
		int size = 0;
		if(cards_notIncluded == null){
			cardsNotToBeIncluded = new int[5];
			size = 0;
		}else {
			if(cardsNotToBeIncluded == null)
				cardsNotToBeIncluded = new int[5];
			
		    for(int i=0;i<cards_notIncluded.length;i++) {
		    	cardsNotToBeIncluded[i] = cards_notIncluded[i];
		    	if(cards_notIncluded[i] != 0)
		    	   size++;
		    } 
		}
		if(cardNotToBeIncluded != 0) {
			cardsNotToBeIncluded[size] = cardNotToBeIncluded;			
		}

		int tempMax1 = PokerHelper.getHighCard(hand1,cardsNotToBeIncluded);
		int tempMax2 = PokerHelper.getHighCard(hand2,cardsNotToBeIncluded);
		
		if(tempMax1 == 0 && tempMax2 == 0) {
			tie++;
			return;
		}
		if(tempMax1 > tempMax2)
			payer1Wins++;
		else if(tempMax1 < tempMax2)
			payer2Wins++;
		else 
			compareHighCard(hand1,hand2,cardsNotToBeIncluded,tempMax1,counter);
		
		return;
		
	}	
}
