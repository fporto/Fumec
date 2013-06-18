package com.fumec.androidgames;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.fumec.androidgames.adapter.ScreenSlidePagerAdapter;
import com.fumec.androidgames.entity.Game;
import com.fumec.androidgames.request.GamesRequest;
import com.fumec.androidgames.request.RequestManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GamesActivity extends SherlockFragmentActivity {
	
	private ViewPager viewPager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_games);
		
		//Inicializa RequestManager para ser possivel realizar requizições das imagens
		RequestManager.init(this);
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put(Configuration.HEADER_AUTHORIXATION, Configuration.KEY_AUTHORIXATION);
		
		GamesRequest request = new GamesRequest(Method.GET, Configuration.URL + Configuration.PATH_LIST_GAMES_POPULA, headers, null, gamesPopularListner, gamesPopularErrorListner);
		RequestManager.getRequestQueue().add(request);
		
		viewPager = (ViewPager)findViewById(R.id.pager);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.games, menu);
		
		MenuItem item = menu.findItem(R.id.ver_todos);
		
		item.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				return false;
			}
		});
		
		return true;
	}
	
	private Listener<JSONObject> gamesPopularListner = new Listener<JSONObject>() {

		@Override
		public void onResponse(JSONObject jsonObject) {
			Log.i("GAMES", jsonObject.toString());
			parsePopularGames(jsonObject);
		}
	};
	
	private ErrorListener gamesPopularErrorListner = new ErrorListener() {
		@Override
		public void onErrorResponse(VolleyError error) {
			Log.d("GAMES_ERROR", error.getLocalizedMessage(), error);
		}
	};
	
	private void parsePopularGames(JSONObject jsonObject) {
			
		try {
			
			JSONArray array = jsonObject.getJSONArray("games");//Chave do array
			
			ScreenSlidePagerAdapter adapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
			
			Type listType = new TypeToken<ArrayList<Game>>(){}.getType();//TypeToken --> Classe que retorna tipo do objeto que é passado, usado somente em lista
            List<Game> listGames = new Gson().fromJson(array.toString(), listType);
			
			adapter.addGame(listGames.get(0));
			adapter.addGame(listGames.get(1));
			adapter.addGame(listGames.get(2));
			
			viewPager.setAdapter(adapter);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
