package com.felipemsa.tictactoe.util;

import com.felipemsa.tictactoe.model.Choice;
import com.felipemsa.tictactoe.model.Turn;

/**
 * Created by Felipe Almeida on 07/09/2017.
 */

public class PlayerHelper {

	private static PlayerHelper instance;

	private Choice playerChoice;

	private PlayerHelper() {
	}

	public static PlayerHelper getInstance() {
		if (instance == null)
			instance = new PlayerHelper();

		return instance;
	}

	public void setPlayerChoice(Choice choice) {
		playerChoice = choice;
	}

	public Choice getPlayerChoice() {
		return playerChoice != null ? playerChoice : Choice.CROSS;
	}

	public Choice getOtherChoice() {
		switch (getPlayerChoice()) {
			case CROSS:
				return Choice.CIRCLE;
			case CIRCLE:
				return Choice.CROSS;
			default:
				return Choice.CIRCLE;
		}
	}

	public Turn playerTurn() {
		if (getPlayerChoice() == Choice.CROSS)
			return Turn.PLAYER;
		else
			return Turn.OTHER;
	}
}
