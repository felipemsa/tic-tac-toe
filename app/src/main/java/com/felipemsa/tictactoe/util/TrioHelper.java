package com.felipemsa.tictactoe.util;

import com.felipemsa.tictactoe.model.Choice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Felipe Almeida on 11-Jul-17.
 */

public class TrioHelper {
	public static List<Choice> getLine(int i, Choice hash[][]) {
		List<Choice> line = new ArrayList<>();

		line.add(hash[i][0]);
		line.add(hash[i][1]);
		line.add(hash[i][2]);

		return line;
	}

	public static List<Choice> getColumn(int i, Choice hash[][]) {
		List<Choice> column = new ArrayList<>();

		column.add(hash[0][i]);
		column.add(hash[1][i]);
		column.add(hash[2][i]);

		return column;
	}

	public static List<Choice> getDiagonal(int i, Choice hash[][]) {
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
