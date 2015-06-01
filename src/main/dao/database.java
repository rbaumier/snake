package main.dao;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.models.Player;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public final class database {
  private static SharedPreferences pref;
  private static SharedPreferences.Editor editor;
  private static final String dbname = "SnakePreferences";
  private static database INSTANCE = new database();
  private static Gson gson = new Gson();

  public static void init(Context ctx) {
    pref = ctx.getSharedPreferences(dbname, Context.MODE_PRIVATE);
    editor = pref.edit();
  }

  public static database getInstance() {
    return INSTANCE;
  }

  public static void putScores(ArrayList<Player> players) {
    Type playerType = new TypeToken<List<Player>>(){}.getType();
    List<Player> playersList = gson.fromJson(
      gson.toJson(players, playerType),
      playerType
    );
    editor.putString("scores", gson.toJson(playersList));
    editor.commit();
  }

  public static void addScore(Player player) {
    ArrayList<Player>  players = getScores();
    players.add(player);
    putScores(players);
  }

  public static ArrayList<Player> getScores() {
    ArrayList<Player> players = gson.fromJson(
      pref.getString("scores", ""),
      new TypeToken<List<Player>>() {}.getType()
    );
    return players != null ? players : new ArrayList<Player>();
  }

  public static void clearScores() {
    editor.putString("scores", "");
    editor.commit();
  }

  public static boolean switchMusicValue() {
    Boolean newMusicValue = !pref.getBoolean("music", false);
    editor.putBoolean("music", newMusicValue);
    editor.commit();
    return newMusicValue;
  }

  public static boolean musicActivated() {
    return pref.getBoolean("music", false);
  }
}
