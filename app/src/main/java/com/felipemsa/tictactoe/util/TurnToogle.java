package com.felipemsa.tictactoe.util;

import com.felipemsa.tictactoe.model.Turn;

/**
 * Created by Felipe Almeida on 07/09/2017.
 */

public class TurnToogle {

	public static Turn toogle(Turn turn) {
		if (turn == Turn.PLAYER)
			return Turn.OTHER;
		else
			return Turn.PLAYER;
	}

}
