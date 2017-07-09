package com.felipemsa.tictactoe.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.felipemsa.tictactoe.R;
import com.felipemsa.tictactoe.model.Choice;
import com.felipemsa.tictactoe.model.Turn;
import com.felipemsa.tictactoe.util.PlayerHelper;
import com.felipemsa.tictactoe.util.TurnToogle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

	private Choice mPlayerChoice;
	private Choice otherChoice;
	private Turn mTurn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		mPlayerChoice = PlayerHelper.getInstance().getPlayerChoice();
		otherChoice = PlayerHelper.getInstance().getOtherChoice();

		mTurn = PlayerHelper.getInstance().playerTurn();
	}

	@OnClick({R.id.cell_one_one, R.id.cell_one_two, R.id.cell_one_three,
			R.id.cell_two_one, R.id.cell_two_two, R.id.cell_two_three,
			R.id.cell_three_one, R.id.cell_three_two, R.id.cell_three_three})
	public void cellClick(View cell) {
		ImageView selectedCell = (ImageView) cell;
		if (selectedCell.getDrawable() != null)
			return;

		if (mTurn == Turn.PLAYER)
			selectedCell.setImageResource(getDrawableFromChoice(mPlayerChoice));
		else
			selectedCell.setImageResource(getDrawableFromChoice(otherChoice));

		mTurn = TurnToogle.toogle(mTurn);
	}

	private int getDrawableFromChoice(Choice choice) {
		if (choice == Choice.CROSS)
			return R.drawable.ic_cross;
		else
			return R.drawable.ic_circle;
	}

}
