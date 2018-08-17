package developer.alexangan.ru.bidsandpersonal.Interfaces;

import java.util.List;

import developer.alexangan.ru.bidsandpersonal.Models.WorkerItem;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static developer.alexangan.ru.bidsandpersonal.Models.GlobalConstants.API_HOST_URL;

public interface RetrofitAPI
{
    @FormUrlEncoded
    @POST("api/getWorkers")
    Call<List<WorkerItem>> getWorkersList(@Field("token") String tokenStr);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(API_HOST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
