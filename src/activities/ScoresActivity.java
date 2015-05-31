package activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import DAO.database;
import models.Player;
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
    ArrayList<Player> players = database.getPlayers();
    Collections.sort(players, new Comparator<Player>() {
      public int compare(Player p1, Player p2) {
        return p2.score - p1.score;
      }
    });

    ListView scoresList = (ListView) findViewById(R.id.scoresList);
    String[] bestPlayers = formatPlayers(
      players.subList(0, Math.min(players.size(), maxBestPlayers ))
    );

    scoresList.setAdapter(new ArrayAdapter<String>(
      this,
      android.R.layout.simple_list_item_1,
      android.R.id.text1,
      bestPlayers
    ));
  }

  private String[] formatPlayers(List<Player> players) {
    String[] scores = new String[players.size()];
    for (int i = 0; i < players.size(); i++) {
      Player current = players.get(i);
      scores[i] = i + 1 + ". " + current.name + " - " + current.score;
    }
    return scores;
  }

}
