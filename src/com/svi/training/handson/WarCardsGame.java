package com.svi.training.handson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * key point action:
 * 1) I need to make object for each player.Done
 * 2) distribute cards to player. Done
 * 3) per round create a method for referencing whose the winner for that round. DONE
 * 			-determine who's the winner DONE
 * 			-get the losing cards and give to the winning player. DONE
	4) createa a loop within winnerDeclaration 
 * 
 */

public class WarCardsGame extends GameProcess {
	byte numOfPlayers;
	byte numberOfCardsInDeckPerPlayer;
	byte highestNumberOfCards;
	List<String> shuffledCards;
	List<Player> players;
	boolean isGameComplete = false;

	// constructor
	public WarCardsGame(byte numOfPlayers, List<String> shuffledCards) {
		this.numOfPlayers = numOfPlayers;
		this.shuffledCards = shuffledCards;
		this.numberOfCardsInDeckPerPlayer = (byte) (52 / numOfPlayers);
		this.highestNumberOfCards = (byte) (numberOfCardsInDeckPerPlayer * numOfPlayers);
		this.players = new ArrayList<Player>(numOfPlayers);
		// below is basically set up the player object and store to arraylist
		for (int i = 0; i < numOfPlayers; i++) {
			players.add(i, new Player(i + 1));
		}

		distributionOfCards(players);
		start(this.isGameComplete);// this is where the process starts
	}

	@Override
	public void start(boolean isGameComplete) {// recurse until the game is finished

		if (isGameComplete != true) {
			for (int i = 0; i < players.size(); i++) {// this for loop is just for scan every round
				var player = players.get(i);// get players object
				this.isGameComplete = player.getCardsOfPlayer().size() == highestNumberOfCards;

				if (this.isGameComplete == true) {
					break;// break with this for loop
				}
			}

			winnerDeclaration(players);
			start(this.isGameComplete);

		}

//		System.out.println(players); // <-- pang debug ng object

	}

	@Override
	public void distributionOfCards(List<Player> players) {

		System.out.println("Initial deck state:" + shuffledCards);
		for (int i = numberOfCardsInDeckPerPlayer - 1; i >= 0; i--) {//
			// use backward iteration if you want to use .remove() of List. if not it will
			// skip some items
			for (int j = players.size() - 1; j >= 0; j--) {

				var player = players.get(j);// get the player object
				player.addCardToThePlayersDeck(shuffledCards.get(j));/// store the cards to the players deck
				shuffledCards.remove(j);

			}

		}

		System.out.println("after distribution deck state:" + shuffledCards);
		shuffledCards = null; // i eeempty ko na even may laman pang natira

	}

	@Override
	public void winnerDeclaration(List<Player> players) {
		List<String> playingCards = new ArrayList<String>();

		// this loop will get the card from players deck
		for (int i = 0; i < players.size(); i++) {
			var player = players.get(i); // get the object of each player
			player.setCurrentCard();
			playingCards.add(player.getCurrentCard());
		}

		System.out.println(players + "\n");
		System.out.println("this is the playing cards:" + playingCards + "\n");

		List<Integer> ranks = rankingThePlayingCards(playingCards);// [c-2, ..... , d-A]

		var winningPlayer = (ranks.indexOf(Collections.max(ranks)) + 1);
//		System.out.println(Collections.max(ranks));
		System.out.println("Player " + winningPlayer + " has won!!" + "\n"); // <- this will get the highest rank on the
																				// list
		/*
		 * next key point actions:
		 * 
		 * -get the cards of each player and store it to the winner player and say who
		 * is the winner
		 */
		getTheLoseCards(playingCards, winningPlayer, players);

	}

	public void getTheLoseCards(List<String> playingCards, int winningPlayer, List<Player> players) {
		var triumpPlayer = players.get(winningPlayer - 1);// get the winning player object

		for (int i = 0; i < playingCards.size(); i++) {
			if (i != (winningPlayer - 1)) {// skip the winning player
				var player = players.get(i);

				if (!player.cardsOfPlayer.isEmpty()) {
					triumpPlayer.addCardToThePlayersDeck(player.getLoseCard());
					player.setCurrentCard();// get card from their decks
				}
			} else {
				continue;
			}
		}

	}

	public List<Integer> rankingThePlayingCards(List<String> playingCards) {
		List<Integer> ranks = new ArrayList<Integer>();
		for (String card : playingCards) {
			ranks.add(Cards.cardsRankList().indexOf(card));// [c-2,...d-a]
		}
//		System.out.println("This is the converted rank: " + ranks);
		System.out.println(ranks);
		return ranks;//
	}

}
