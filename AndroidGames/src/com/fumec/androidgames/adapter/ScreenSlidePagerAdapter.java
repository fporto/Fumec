package com.fumec.androidgames.adapter;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.fumec.androidgames.entity.Game;
import com.fumec.androidgames.fragments.GamesPopularFragment;

public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

	public ScreenSlidePagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	private List<Game> games = new ArrayList<Game>();
	

	@Override
	public Fragment getItem(int position) {
		
		GamesPopularFragment fragment = new GamesPopularFragment();
		fragment.setGame(games.get(position));
		return fragment;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return games.size();
	}
	
	public void addGame(Game object) {
		games.add(object);
	}

}
