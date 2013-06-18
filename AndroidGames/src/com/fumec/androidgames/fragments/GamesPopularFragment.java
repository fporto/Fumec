package com.fumec.androidgames.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.android.volley.toolbox.NetworkImageView;
import com.fumec.androidgames.R;
import com.fumec.androidgames.entity.Game;
import com.fumec.androidgames.request.RequestManager;

public class GamesPopularFragment extends SherlockFragment {
	
	private Game game;
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_games_popular,container, false);
		TextView textNameGame = (TextView)rootView.findViewById(R.id.name_game);
		NetworkImageView imgThumb = (NetworkImageView)rootView.findViewById(R.id.img_thumb);//Recupera imagem
		
		textNameGame.setText(game.getName());
		imgThumb.setImageUrl(game.getThumbnail_url(), RequestManager.getImageLoader());//Baixa imagem da URL e salva em disco

		return rootView;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
