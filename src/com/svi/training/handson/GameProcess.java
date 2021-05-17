package com.svi.training.handson;

import java.util.List;

public abstract class GameProcess {

	public abstract void start(boolean isGameComplete);

	public abstract void distributionOfCards(List<Player> players);

	public abstract void winnerDeclaration(List<Player> players);

	public void process() {

	}

}
