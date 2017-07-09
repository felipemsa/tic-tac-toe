package com.felipemsa.tictactoe.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.felipemsa.tictactoe.R;
import com.felipemsa.tictactoe.model.Choose;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

	private Choose mChoose;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		findViewById(R.id.play_with_cross).setSelected(true);
	}

	@OnClick({R.id.play_with_cross, R.id.play_with_circle})
	public void playWith(View selected) {
		findViewById(R.id.play_with_cross).setSelected(false);
		findViewById(R.id.play_with_circle).setSelected(false);

		selected.setSelected(true);

		switch (selected.getId()) {
			case R.id.play_with_circle:
				mChoose = Choose.CIRCLE;
				break;
			case R.id.play_with_cross:
				mChoose = Choose.CROSS;
				break;
		}
	}

	@OnClick({R.id.cell_one_one, R.id.cell_one_two, R.id.cell_one_three,
			R.id.cell_two_one, R.id.cell_two_two, R.id.cell_two_three,
			R.id.cell_three_one, R.id.cell_three_two, R.id.cell_three_three})
	public void cellClick(View cell) {
		ImageView selectedCell = (ImageView) cell;

		if (mChoose == Choose.CIRCLE)
			selectedCell.setImageResource(R.drawable.ic_circle);
		else
			selectedCell.setImageResource(R.drawable.ic_cross);
	}

}
