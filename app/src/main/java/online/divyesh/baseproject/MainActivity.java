package online.divyesh.baseproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView rv = findViewById(R.id.rv);

        rv.setLayoutManager(new LinearLayoutManager(this));


        APIs api = Client.getRetrofit().create(APIs.class);
        api.getData().enqueue(new Callback<List<ResponseDatum>>() {
            @Override
            public void onResponse(Call<List<ResponseDatum>> call, Response<List<ResponseDatum>> response) {
                SQLiteHelper helper = new SQLiteHelper(MainActivity.this);
                helper.addEmps(response.body());

                DataAdapter adapter = new DataAdapter(helper.getAllEmp());
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ResponseDatum>> call, Throwable t) {
                Log.e("CallFailed",t.getMessage());
            }
        });

    }
}
