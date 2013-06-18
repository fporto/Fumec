package com.fumec.androidgames;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.fumec.androidgames.adapter.GamesAdapter;
import com.fumec.androidgames.entity.Game;
import com.fumec.androidgames.request.GamesRequest;
import com.fumec.androidgames.request.RequestManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ItemListView extends SherlockFragmentActivity {

	public GamesAdapter gamesAdapter;
	private ListView listView;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.list_view_games);
		
		listView = (ListView) findViewById(R.id.list_games);

		Map<String, String> headers = new HashMap<String, String>();
		headers.put(Configuration.HEADER_AUTHORIXATION, Configuration.KEY_AUTHORIXATION);
		
		GamesRequest request = new GamesRequest(Method.GET, Configuration.URL + Configuration.PATH_LIST_GAMES_POPULA, headers, null, gamesPopularListner, gamesPopularErrorListner);
		RequestManager.getRequestQueue().add(request);
		
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
			
						
			Type listType = new TypeToken<ArrayList<Game>>(){}.getType();//TypeToken --> Classe que retorna tipo do objeto que é passado, usado somente em lista
            ArrayList<Game> listGames = new Gson().fromJson(array.toString(), listType);
            
            gamesAdapter =  new GamesAdapter(this, listGames);
            
            listView.setAdapter(gamesAdapter);
            listView.setCacheColorHint(Color.TRANSPARENT);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
