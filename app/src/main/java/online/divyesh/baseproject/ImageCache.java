package online.divyesh.baseproject;

import android.graphics.Bitmap;
import android.util.LruCache;

import java.security.Key;

/**
 * Created by root on 26/12/17.
 */

public class ImageCache {

    public LruCache<String,Bitmap> mCacheMemory;

    final int maxMemory = (int) (Runtime.getRuntime().maxMemory()/1024);
    final int cacheSize = maxMemory / 8;


    public void init(){

        mCacheMemory = new LruCache<String, Bitmap>(cacheSize){

            @Override
            protected int sizeOf(String key, Bitmap value) {

                return value.getByteCount();
            }
        };

    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap){
        mCacheMemory.put(key,bitmap);
    }

    public Bitmap getBitmapFromCache(String key){
        return mCacheMemory.get(key);
    }
}
