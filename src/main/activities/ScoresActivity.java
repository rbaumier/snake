package main.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import main.dao.database;
import main.models.Player;
import net.epsi.YoloSnake.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ScoresActivity extends Activity {
  private static final int maxBestPlayers = 10;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.scores);
    showScores();
  }

  private void showScores() {
    ArrayList<Player> scores = database.getScores();
    Collections.sort(scores, new Comparator<Player>() {
      public int compare(Player p1, Player p2) {
        return p2.getScore() - p1.getScore();
      }
    });

    ListView scoresList = (ListView) findViewById(R.id.scoresList);
    String[] bestScores = formatPlayers(
      scores.subList(0, Math.min(scores.size(), maxBestPlayers ))
    );

    scoresList.setAdapter(new ArrayAdapter<String>(
      this,
      android.R.layout.simple_list_item_1,
      android.R.id.text1,
      bestScores
    ));
  }

  private String[] formatPlayers(List<Player> players) {
    String[] scores = new String[players.size()];
    for (int i = 0; i < players.size(); i++) {
      Player current = players.get(i);
      scores[i] = i + 1 + ". " + current.getName() + " - " + current.getScore();
    }
    return scores;
  }

}
