package online.divyesh.baseproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 26/12/17.
 */

public class DataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ResponseDatum> data = new ArrayList<>();
    ImageCache imageCache;
    String BASE_IMG = "https://source.unsplash.com";

    public DataAdapter(List<ResponseDatum> allEmp) {
        imageCache = new ImageCache();
        imageCache.init();
        data = allEmp;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null);

        return new DataHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        if (imageCache.getBitmapFromCache(data.get(position).filename) != null) {

            loadBitmap(holder, position);

        } else {

            new AsyncTask<String, Void, Bitmap>() {

                @Override
                protected Bitmap doInBackground(String... urls) {

                    try {
                        Bitmap bitmap = null;
                        URL url = new URL(BASE_IMG + "/" + data.get(position).postUrl.substring(data.get(position).postUrl.lastIndexOf("/") + 1));
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setDoInput(true);
                        connection.setRequestMethod("GET");
                        connection.connect();
                        int code = connection.getResponseCode();
                        if (code == 200) {

                            InputStream inputStream = connection.getInputStream();
                            bitmap = BitmapFactory.decodeStream(inputStream);
                            inputStream.close();
                        }
                        return bitmap;

                    } catch (Exception e) {
                        Log.e("UrlLoadErr", e.toString());
                    }

                    return null;
                }

                @Override
                protected void onPostExecute(Bitmap bitmap) {
                    super.onPostExecute(bitmap);

                    imageCache.addBitmapToMemoryCache(data.get(position).filename, bitmap);
                    loadBitmap(holder, position);

                }
            }.execute();
        }


    }

    public void loadBitmap(RecyclerView.ViewHolder holder, int position) {

        ((DataHolder) holder).iv.setImageBitmap(imageCache.getBitmapFromCache(data.get(position).filename));
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class DataHolder extends RecyclerView.ViewHolder {

        public ImageView iv;

        public DataHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);

        }
    }
}
