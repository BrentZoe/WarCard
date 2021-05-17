package com.svi.training.handson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {

	// input:
	// number of players,
	// number of times the deck will shuffle

	// output:
	// Display the content

	// Point action of this project:
	// 1) create a file scanner to read the text file for the input.DONE
	// 2) parse and filter the Input Text.DONE
	// 3) create a method for the openFile.DONE
	// 4) create a method for the input number of Players.DONE
	// 5) create a method for the shuffling card.DONE
	// 6) create a method where the game will start Done
	// 7) create an enum class for the list of card types Done
	// 8) you may create Player class that store the currentCard,cardsOfPlayer,Done
	// player name(int).
	// * player 1 | DA |
	// * player 2 | SA |
	// * player 3 | C9 |
	// ** continuiation WarCardsGame.java **

	public static void main(String[] args) throws FileNotFoundException, IOException {

		String[] cards = fileScannerToString(new File("InputFile.txt"));
		Scanner inputScanner = new Scanner(System.in);

		byte players = enterNumberOfPlayers(inputScanner);// first input process
		int numShuffle = enterNumberOfShuffle(inputScanner);// second input process
//		shuffleCards(cards, numShuffle);
		List<String> shuffled = shuffleCards(cards, numShuffle);

		new WarCardsGame(players, shuffled);

//		System.out.println(shuffled);
	}

	private static List<String> shuffleCards(String[] cards, int numShuffle) {
		List<String> shuffledCards = new ArrayList<String>(Arrays.asList(cards));

		for (int i = 0; i < numShuffle; i++) {
			int cardsPerCut = (int) (Math.random() * 52); // the limit is only 52

			// set containers for cutOne and cutTwo
			List<String> cutOne = new ArrayList<String>(Arrays.asList(new String[cardsPerCut]));
			List<String> cutTwo = new ArrayList<String>(Arrays.asList(new String[52 - cardsPerCut]));

			// for cut one
			for (byte j = 0; j < cardsPerCut; j++) {
				cutOne.set(j, shuffledCards.get(j));

			}
			// for cut two
			for (byte k = 0; k < (52 - cardsPerCut); k++) {
				cutTwo.set(k, shuffledCards.get(cutOne.size() + k));
			}

			Collections.shuffle(cutTwo);
			Collections.shuffle(cutOne);

			shuffledCards.clear();// clear all the items of this list
			shuffledCards.addAll(cutTwo);
			shuffledCards.addAll(cutOne);

//		System.out.println(shuffledCards);// <---- uncomment this befor presentation

		}

		return shuffledCards;
	}

	public static String[] fileScannerToString(File textFile) throws IOException {
		FileReader file = new FileReader(textFile);
		BufferedReader br = new BufferedReader(file);

		String[] cards = br.readLine().split(",");
		br.close();
		// this will return in array of strings
		return cards;
	}

	public static int enterNumberOfShuffle(Scanner inputScanner) {

		int numShuffle = 0;

		while (true) {

			try {
				System.out.print("Enter the number of shuffle you want:");
				numShuffle = Integer.parseInt(inputScanner.nextLine());
				if (numShuffle <= 0) {
					throw new InputMismatchException("Invalid number of shuffle...");
				} else {
					inputScanner.close();
					break;
				}

			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println("Invalid input...");
				continue;
			}

		}

		return numShuffle;
	}

	// below is the method of input process
	public static byte enterNumberOfPlayers(Scanner inputScanner) {
		byte players;

		while (true) {
			// you may set it this to true instead of this -> players >= 0 | players <= 52
			// always remember to have a break statement on the loop
			try {
				System.out.print("Enter the number of players: ");
				players = (byte) Integer.parseInt(inputScanner.nextLine());// NOTE: always use nextLine()!

				if (players > 52) {
					throw new InputMismatchException("number of players is too large!!");

				} else if (players <= 1) {
					throw new InputMismatchException("invalid number of players... (Please input from 2 to 52)");
				} else {
					inputScanner.reset();// i use reset kasi gagamtin pa sa kabilang method
					break;// break into the loop until it reach the correct input
				}
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				continue;
			} catch (Exception e) {
				System.out.println("invalid Input...");
				continue;
			}
		}

		return players;

	}

}

//	public static void shuffleCards(String[] cards, int numShuffle) {
//
//		ArrayList<String> initialDeck = new ArrayList<String>(Arrays.asList(cards));
//		System.out.println("initialDeck:" + initialDeck);
//		List<String> newDeck = new ArrayList<String>();
//		for (int i = 0; i < numShuffle; i++) {
//
//			System.out.println(newDeck.size());
//			if (newDeck.isEmpty()) {
////				newDeck = new ArrayList<String>();
//				// below is to cut in half
//				List<String> cutOne = initialDeck.subList(0, 26);
//				List<String> cutTwo = initialDeck.subList(26, 52);
//
//				System.out.println(cutOne);
//				System.out.println(cutTwo);
//
//				for (int j = 0; j < cutOne.size(); j++) {
//					newDeck.add(cutTwo.get(j));
//					newDeck.add(cutOne.get(j));
//
//				}
//				System.out.println("The shuffled deck:" + newDeck);
////			initialDeck.re
//
//			} else {
////				newDeck = new ArrayList<String>();
//				// below is to cut in half
//				List<String> cutOne = newDeck.subList(0, 26);
//				List<String> cutTwo = newDeck.subList(26, 52);
//
//				System.out.println(cutOne);
//				System.out.println(cutTwo);
//
//				newDeck.clear();
//				System.out.println("this.is cut two:" + cutTwo);
//				for (int j = 0; j < cutOne.size(); j++) {
//
//					newDeck.add(cutTwo.get(j));
//
//					newDeck.add(cutOne.get(j));
//
//				}
//
//			}
//			System.out.println("The shuffled deck:" + newDeck);
//
//		}
//
//		return;
//	}