package com.svi.training.handson;

import java.util.ArrayList;
import java.util.List;

public class Player {
	int playerName;
	String currentCard;
	List<String> cardsOfPlayer = new ArrayList<String>();

	// constructor
	public Player(int playerName) {
		this.playerName = playerName;
	}

	public Player(int playerName, String currentCard) {
		this.playerName = playerName;
		this.currentCard = currentCard;
	}

	public void setCurrentCard() {
//		this.currentCard = cardsOfPlayer.get(0);
		if (cardsOfPlayer.isEmpty()) {
			this.currentCard = null;
		} else {
			this.currentCard = cardsOfPlayer.get(0);
		}
	}

	public String getCurrentCard() {
		return this.currentCard;
	}

	public String getLoseCard() {
		cardsOfPlayer.remove(0);// remove the card from the deck of player
		return currentCard;// return the current Card
	}

	public List<String> getCardsOfPlayer() {
		return this.cardsOfPlayer;
	}

	public void addCardToThePlayersDeck(String card) {
		this.cardsOfPlayer.add(card);// add the card to the players deck
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return " { Player: " + playerName + " | currentCard(String): " + currentCard + " | "
				+ " cardsOfPlayer(List<String>): " + cardsOfPlayer + "}\n";
	}
}
