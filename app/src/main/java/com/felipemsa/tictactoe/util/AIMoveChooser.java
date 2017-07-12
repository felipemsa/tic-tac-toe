package com.felipemsa.tictactoe.util;

import com.felipemsa.tictactoe.model.AIMove;
import com.felipemsa.tictactoe.model.Choice;

import java.util.List;
import java.util.Random;

import static com.felipemsa.tictactoe.util.TrioHelper.getColumn;
import static com.felipemsa.tictactoe.util.TrioHelper.getDiagonal;
import static com.felipemsa.tictactoe.util.TrioHelper.getLine;

/**
 * Created by Felipe Almeida on 07/10/2017.
 */

public class AIMoveChooser {

	public static AIMove choose(Choice choice, Choice hash[][]) {
		AIMove aiMove = tryToWin(choice, hash);
		if (aiMove != null)
			return aiMove;

		aiMove = disturb(hash);
		if (aiMove != null)
			return aiMove;

		aiMove = new AIMove(new Random().nextInt(3), new Random().nextInt(3));

		if (hash[aiMove.x][aiMove.y] != null) {
			return choose(choice, hash);
		}

		return aiMove;
	}

	private static AIMove tryToWin(Choice choice, Choice hash[][]) {
		List<Choice> trio;

		for (int x = 0; x < 3; x++) {
			trio = getLine(x, hash);
			if (countChoices(choice, trio))
				if (trio.get(0) == trio.get(1) || trio.get(0) == trio.get(2) || trio.get(1) == trio.get(2)) {
					for (int y = 0; y < 3; y++)
						if (trio.get(y) == null)
							return new AIMove(x, y);
				}
		}

		for (int y = 0; y < 3; y++) {
			trio = getColumn(y, hash);
			if (countChoices(choice, trio))
				if (trio.get(0) == trio.get(1) || trio.get(0) == trio.get(2) || trio.get(1) == trio.get(2)) {
					for (int x = 0; x < 3; x++)
						if (trio.get(x) == null)
							return new AIMove(x, y);
				}
		}

		for (int x = 0; x < 3; x++) {
			trio = getDiagonal(x, hash);
			if (trio.size() > 0)
				if (countChoices(choice, trio))
					if (trio.get(0) == trio.get(1) || trio.get(0) == trio.get(2) || trio.get(1) == trio.get(2)) {
						for (int y = 0; y < 3; y++)
							if (trio.get(y) == null) {
								if (x == 0)
									return new AIMove(y, y);
								else {
									if (y == 0)
										return new AIMove(x, y);
									if (y == 2)
										return new AIMove(0, y);
									return new AIMove(y, y);
								}
							}
					}
		}

		return null;
	}

	private static boolean countChoices(Choice choice, List<Choice> choices) {
		int count = 0;
		for (Choice c : choices)
			if (c == choice)
				count++;

		return count == 2;
	}

	private static AIMove disturb(Choice hash[][]) {
		List<Choice> trio;

		for (int x = 0; x < 3; x++) {
			trio = getLine(x, hash);
			if (countNulls(trio))
				if (trio.get(0) == trio.get(1) || trio.get(0) == trio.get(2) || trio.get(1) == trio.get(2)) {
					for (int y = 0; y < 3; y++)
						if (trio.get(y) == null)
							return new AIMove(x, y);
				}
		}

		for (int y = 0; y < 3; y++) {
			trio = getColumn(y, hash);
			if (countNulls(trio))
				if (trio.get(0) == trio.get(1) || trio.get(0) == trio.get(2) || trio.get(1) == trio.get(2)) {
					for (int x = 0; x < 3; x++)
						if (trio.get(x) == null)
							return new AIMove(x, y);
				}
		}

		for (int x = 0; x < 3; x++) {
			trio = getDiagonal(x, hash);
			if (trio.size() > 0)
				if (countNulls(trio))
					if (trio.get(0) == trio.get(1) || trio.get(0) == trio.get(2) || trio.get(1) == trio.get(2)) {
						for (int y = 0; y < 3; y++)
							if (trio.get(y) == null) {
								if (x == 0)
									return new AIMove(y, y);
								else {
									if (y == 0)
										return new AIMove(x, y);
									if (y == 2)
										return new AIMove(0, y);
									return new AIMove(y, y);
								}
							}
					}
		}

		return null;
	}

	private static boolean countNulls(List<Choice> choices) {
		int count = 0;
		for (Choice choice : choices)
			if (choice != null)
				count++;

		return count == 2;
	}
}
