package com.felipemsa.tictactoe.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.felipemsa.tictactoe.R;
import com.felipemsa.tictactoe.model.Choice;
import com.felipemsa.tictactoe.util.PlayerHelper;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Felipe Almeida on 07/09/2017.
 */

public class PlayerSettingsActivity extends AppCompatActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player_settings);

		ButterKnife.bind(this);
	}

	@OnClick({R.id.play_with_cross, R.id.play_with_circle})
	public void playWithClick(View selected) {
		findViewById(R.id.play_with_cross).setSelected(false);
		findViewById(R.id.play_with_circle).setSelected(false);

		selected.setSelected(true);

		switch (selected.getId()) {
			case R.id.play_with_circle:
				PlayerHelper.getInstance().setPlayerChoice(Choice.CIRCLE);
				break;
			case R.id.play_with_cross:
				PlayerHelper.getInstance().setPlayerChoice(Choice.CROSS);
				break;
		}

		Intent intent = new Intent(PlayerSettingsActivity.this, MainActivity.class);
		intent.putExtra("ai_player", true);
		startActivity(intent);
	}

	@OnClick(R.id.player_vs_player)
	public void vsClick() {
		startActivity(new Intent(PlayerSettingsActivity.this, MainActivity.class));
	}
}
