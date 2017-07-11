package com.felipemsa.tictactoe.util;

import com.felipemsa.tictactoe.model.AIMove;
import com.felipemsa.tictactoe.model.Choice;

import java.util.Random;

/**
 * Created by Felipe Almeida on 07/10/2017.
 */

public class MoveChooser {

	public static AIMove choose(Choice choice, Choice hash[][]) {
		AIMove aiMove = new AIMove(new Random().nextInt(3), new Random().nextInt(3));

		if (hash[aiMove.x][aiMove.y] != null) {
			return choose(choice, hash);
		}

		return aiMove;
	}

}
