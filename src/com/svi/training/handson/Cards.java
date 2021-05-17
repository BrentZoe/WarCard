package com.svi.training.handson;

import java.util.ArrayList;
import java.util.List;

public class Cards {

	// gawa ako ng array and dapat yung rank nila is naka ayon base sa rank nila
	public enum Rank {
		TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"), JACK("J"),
		QUEEN("Q"), KING("K"), ACE("A");

		private String rank;

		private Rank(String rank) {
			this.rank = rank;
		}

		public String getRank() {
			return rank;
		}
	}

	public enum Suit {
		C, S, H, D;

	}

	private static final List<String> cardsRankInArray = new ArrayList<String>();
	// below is to initialize the code in the body to populate the cards in
	// cardsRankInArray

	static {
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				cardsRankInArray.add(suit + "-" + rank.getRank());// [c-2,...,d-A] .indexOf
			}
		}
	}

	public static List<String> cardsRankList() {
		return cardsRankInArray;
	}

}
