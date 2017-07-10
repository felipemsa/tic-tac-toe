package com.felipemsa.tictactoe.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.felipemsa.tictactoe.R;
import com.felipemsa.tictactoe.model.Choice;
import com.felipemsa.tictactoe.model.Turn;
import com.felipemsa.tictactoe.model.Winner;
import com.felipemsa.tictactoe.util.PlayerHelper;
import com.felipemsa.tictactoe.util.WinnerChecker;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.GONE;
import static com.felipemsa.tictactoe.R.id.winner;

public class MainActivity extends AppCompatActivity {

	@BindView(winner)
	LinearLayout mLinearWinner;
	@BindView(R.id.txt_winner)
	TextView mTxtWinner;
	@BindView(R.id.img_winner)
	ImageView mImgWinner;

	private int ids[] = new int[]{
			R.id.cell_one_one, R.id.cell_one_two, R.id.cell_one_three,
			R.id.cell_two_one, R.id.cell_two_two, R.id.cell_two_three,
			R.id.cell_three_one, R.id.cell_three_two, R.id.cell_three_three
	};

	private Choice mPlayerChoice;
	private Choice otherChoice;
	private Turn mTurn;

	private Choice hash[][] = new Choice[3][3];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		mPlayerChoice = PlayerHelper.getInstance().getPlayerChoice();
		otherChoice = PlayerHelper.getInstance().getOtherChoice();

		mTurn = PlayerHelper.getInstance().playerTurn();

		findViewById(R.id.play_with_cross).setSelected(true);
	}

	@OnClick({R.id.cell_one_one, R.id.cell_one_two, R.id.cell_one_three,
			R.id.cell_two_one, R.id.cell_two_two, R.id.cell_two_three,
			R.id.cell_three_one, R.id.cell_three_two, R.id.cell_three_three})
	public void cellClick(View cell) {
		ImageView selectedCell = (ImageView) cell;
		if (selectedCell.getDrawable() != null)
			return;

		Choice localChoice;
		if (mTurn == Turn.PLAYER) {
			localChoice = mPlayerChoice;
			selectedCell.setImageResource(getDrawableFromChoice(mPlayerChoice));
		} else {
			localChoice = otherChoice;
			selectedCell.setImageResource(getDrawableFromChoice(otherChoice));
		}

		switch (cell.getId()) {
			case R.id.cell_one_one:
				select(0, 0, localChoice);
				break;
			case R.id.cell_one_two:
				select(0, 1, localChoice);
				break;
			case R.id.cell_one_three:
				select(0, 2, localChoice);
				break;
			case R.id.cell_two_one:
				select(1, 0, localChoice);
				break;
			case R.id.cell_two_two:
				select(1, 1, localChoice);
				break;
			case R.id.cell_two_three:
				select(1, 2, localChoice);
				break;
			case R.id.cell_three_one:
				select(2, 0, localChoice);
				break;
			case R.id.cell_three_two:
				select(2, 1, localChoice);
				break;
			case R.id.cell_three_three:
				select(2, 2, localChoice);
				break;
		}

		if (gameCompleted()) {
			declareGameResult(null);
//			return;
		}

		Winner winner = WinnerChecker.check(localChoice, hash);
		if (winner != null) {
			declareGameResult(winner);
			return;
		}

		mTurn = toggle(mTurn);
	}

	private void declareGameResult(Winner result) {
		if (result == null) {
			mImgWinner.setVisibility(GONE);
			mTxtWinner.setText("Draw!");
			mLinearWinner.setVisibility(View.VISIBLE);
			findViewById(R.id.play_again).setVisibility(View.VISIBLE);
		} else {
			mImgWinner.setImageResource(getDrawableFromChoice(result.winner));
			mImgWinner.setVisibility(View.VISIBLE);
			mTxtWinner.setText("Winner: ");
			mLinearWinner.setVisibility(View.VISIBLE);
			findViewById(R.id.play_again).setVisibility(View.VISIBLE);
		}
	}

	private void select(int x, int y, Choice choice) {
		hash[x][y] = choice;
	}

	private int getDrawableFromChoice(Choice choice) {
		if (choice == Choice.CROSS)
			return R.drawable.ic_cross;
		else
			return R.drawable.ic_circle;
	}

	private Turn toggle(Turn turn) {
		findViewById(R.id.play_with_cross).setSelected(false);
		findViewById(R.id.play_with_circle).setSelected(false);

		if (turn == Turn.PLAYER) {
			findViewById(R.id.play_with_circle).setSelected(true);
			return Turn.OTHER;
		} else {
			findViewById(R.id.play_with_cross).setSelected(true);
			return Turn.PLAYER;
		}
	}

	private boolean gameCompleted() {
		for (int x = 0; x < 3; x++)
			for (int y = 0; y < 3; y++)
				if (hash[x][y] == null)
					return false;

		return true;
	}

	@OnClick(R.id.play_again)
	public void reset(View button) {
		mTurn = toggle(Turn.OTHER);
		for (int id : ids) {
			((ImageView) findViewById(id)).setImageDrawable(null);
		}
		hash = new Choice[3][3];
		button.setVisibility(GONE);
		mLinearWinner.setVisibility(GONE);
	}

	@Override
	public void onBackPressed() {
		new MaterialDialog.Builder(this)
				.title("Leave game?")
				.positiveText("Yes")
				.onPositive(new MaterialDialog.SingleButtonCallback() {
					@Override
					public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
						finish();
					}
				})
				.negativeText("No")
				.show();
	}
}
