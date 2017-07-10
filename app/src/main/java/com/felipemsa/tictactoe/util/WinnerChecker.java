package com.felipemsa.tictactoe.util;

import com.felipemsa.tictactoe.model.Choice;
import com.felipemsa.tictactoe.model.Winner;
import com.felipemsa.tictactoe.model.WinnerType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Felipe Almeida on 07/09/2017.
 */

public class WinnerChecker {

	public static Winner check(Choice choice, Choice hash[][]) {
		Winner winner = null;

		winner = check(choice, WinnerType.LINE, hash);
		if (winner != null)
			return winner;
		winner = check(choice, WinnerType.COLUMN, hash);
		if (winner != null)
			return winner;
		winner = check(choice, WinnerType.DIAGONAL, hash);
		if (winner != null)
			return winner;

		return winner;
	}

	private static Winner check(Choice choice, WinnerType type, Choice hash[][]) {
		List<Choice> trio;

		for (int i = 0; i < 3; i++) {
			if (type == WinnerType.LINE)
				trio = getLine(i, hash);
			else if (type == WinnerType.COLUMN)
				trio = getColumn(i, hash);
			else
				trio = getDiagonal(i, hash);

			if (trio.size() == 0)
				continue;

			if (choice == trio.get(0) && choice == trio.get(1) && choice == trio.get(2))
				return new Winner(choice, type, i);
		}
		return null;
	}

	private static List<Choice> getLine(int i, Choice hash[][]) {
		List<Choice> line = new ArrayList<>();

		line.add(hash[i][0]);
		line.add(hash[i][1]);
		line.add(hash[i][2]);

		return line;
	}

	private static List<Choice> getColumn(int i, Choice hash[][]) {
		List<Choice> column = new ArrayList<>();

		column.add(hash[0][i]);
		column.add(hash[1][i]);
		column.add(hash[2][i]);

		return column;
	}

	private static List<Choice> getDiagonal(int i, Choice hash[][]) {
		List<Choice> diagonal = new ArrayList<>();

		if (i == 0) {
			diagonal.add(hash[0][0]);
			diagonal.add(hash[1][1]);
			diagonal.add(hash[2][2]);
		} else if (i == 2) {
			diagonal.add(hash[2][0]);
			diagonal.add(hash[1][1]);
			diagonal.add(hash[0][2]);
		}

		return diagonal;
	}

}
