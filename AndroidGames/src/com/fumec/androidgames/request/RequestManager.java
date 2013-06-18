package com.fumec.androidgames.request;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.fumec.androidgames.cache.LruBitmapCache;

/**
 * Manager for the queue
 * 
 *
 */
public class RequestManager {
	
	/**
	 * the queue :-)
	 */
	private static RequestQueue mRequestQueue;
	private static ImageLoader mImageLoader;

	/**
	 * Nothing to see here.
	 */
	private RequestManager() {
	 // no instances
	} 

	/**
	 * @param context
	 * 			application context
	 */
	public static void init(Context context) {
		mRequestQueue = Volley.newRequestQueue(context);
		mImageLoader = new ImageLoader(mRequestQueue, new LruBitmapCache(1024*1024*10)); //10MB
	}

	/**
	 * @return
	 * 		instance of the queue
	 * @throws
	 * 		IllegalStatException if init has not yet been called
	 */
	public static RequestQueue getRequestQueue() {
	    if (mRequestQueue != null) {
	        return mRequestQueue;
	    } else {
	        throw new IllegalStateException("Not initialized");
	    }
	}
	
	public static ImageLoader getImageLoader() {
		if (mImageLoader != null) {
	        return mImageLoader;
	    } else {
	        throw new IllegalStateException("Not initialized");
	    }
	}
}
