package com.fumec.androidgames.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.fumec.androidgames.R;
import com.fumec.androidgames.entity.Game;
import com.fumec.androidgames.request.RequestManager;

public class GamesAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private ArrayList<Game> itens;

	public GamesAdapter(Context context, ArrayList<Game> itens) {
		// Itens que preencheram o listview
		this.itens = itens;

		// responsavel por pegar o Layout do item.
		mInflater = LayoutInflater.from(context);
	}


	@Override
	public int getCount() {

		return itens.size();
	}

	@Override
	public Game getItem(int position) {
		// TODO Auto-generated method stub
		return itens.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup arg2) {
		// Pega o item de acordo com a posção.
		Game item = itens.get(position);

		// infla o layout para preencher os dados
		view = mInflater.inflate(R.layout.item_list_games, null);

		// atravez do layout pego pelo LayoutInflater, pegamos cada id relacionado
		// ao item e definimos as informações.
		TextView name_game = (TextView) view.findViewById(R.id.name_game);
		NetworkImageView imgGame = (NetworkImageView) view.findViewById(R.id.img_list_view);
		
		name_game.setText(item.getName());
		imgGame.setImageUrl(item.getThumbnail_url(), RequestManager.getImageLoader());
		
		return view;
	}

}
