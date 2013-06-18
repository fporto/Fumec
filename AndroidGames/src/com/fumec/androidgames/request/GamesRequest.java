package com.fumec.androidgames.request;

import java.util.Map;

import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;

public class GamesRequest extends JsonObjectRequest {
	
	 private final Map<String, String> headers;

	 public GamesRequest(int method, String url, Map<String, String> headers, JSONObject jsonRequest,
			 Listener<JSONObject> listener, ErrorListener errorListener) {
		 super(method, url, jsonRequest, listener, errorListener);
		 
		 this.headers = headers;
	 }

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		return headers != null ? headers : super.getHeaders();
	}
	

}
