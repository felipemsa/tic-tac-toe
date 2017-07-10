package com.felipemsa.tictactoe.model;

/**
 * Created by Felipe Almeida on 07/09/2017.
 */

public class Winner {

	public Choice winner;
	public WinnerType type;
	public int number;

	public Winner(Choice winner, WinnerType type, int number) {
		this.winner = winner;
		this.type = type;
		this.number = number;
	}
}
