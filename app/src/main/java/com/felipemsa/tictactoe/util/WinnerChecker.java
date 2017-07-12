package com.felipemsa.tictactoe.util;

import com.felipemsa.tictactoe.model.Choice;
import com.felipemsa.tictactoe.model.Winner;
import com.felipemsa.tictactoe.model.WinnerType;

import java.util.List;

import static com.felipemsa.tictactoe.util.TrioHelper.getColumn;
import static com.felipemsa.tictactoe.util.TrioHelper.getDiagonal;
import static com.felipemsa.tictactoe.util.TrioHelper.getLine;

/**
 * Created by Felipe Almeida on 07/09/2017.
 */

public class WinnerChecker {

	public static Winner check(Choice choice, Choice hash[][]) {
		Winner winner;

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
}
