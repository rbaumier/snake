package database;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.Player;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public final class DAO {
  private static SharedPreferences pref;
  private static SharedPreferences.Editor editor;
  private static final String dbname = "SnakePreferences";
  private static DAO INSTANCE = new DAO();
  private static Gson gson = new Gson();

  public static void init(Context ctx) {
    pref = ctx.getSharedPreferences(dbname, Context.MODE_PRIVATE);
    editor = pref.edit();
  }

  public static DAO getInstance() {
    return INSTANCE;
  }

  public static void putPlayers(ArrayList<Player> players) {
    Type playerType = new TypeToken<List<Player>>(){}.getType();
    List<Player> playersList = gson.fromJson(
      gson.toJson(players, playerType),
      playerType
    );
    editor.putString("scores", gson.toJson(playersList));
    editor.commit();
  }

  public static ArrayList<Player> getPlayers() {
    return gson.fromJson(
      pref.getString("scores", ""),
      new TypeToken<List<Player>>(){}.getType()
    );
  }
}
