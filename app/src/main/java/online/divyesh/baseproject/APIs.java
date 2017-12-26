package online.divyesh.baseproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by root on 26/12/17.
 */

public interface APIs {

    @GET("/list")
    Call<List<ResponseDatum>> getData();
}
