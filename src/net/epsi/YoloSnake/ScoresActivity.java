package net.epsi.YoloSnake;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import database.DAO;
import models.Player;

import java.util.ArrayList;

public class ScoresActivity extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.scores);
    showScores();
  }

  private void showScores() {
    ArrayList<Player> players = DAO.getPlayers();
    String[] values = formatPlayers(players);

    ListView scoresList = (ListView) findViewById(R.id.scoresList);
    scoresList.setAdapter(new ArrayAdapter<String>(
      this,
      android.R.layout.simple_list_item_1,
      android.R.id.text1,
      values
    ));
  }

  private String[] formatPlayers(ArrayList<Player> players) {
    String[] scores = new String[players.size()];
    for(int i = 0; i < players.size(); i++) {
      Player current = players.get(i);
      scores[i] = current.name + " - " + current.score;
    }
    return scores;
  }

}
