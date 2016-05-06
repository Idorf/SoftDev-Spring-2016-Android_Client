package APIConsumer;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Idorf on 05-05-2016.
 */

public interface UrlClient {
  //  @GET("/repos/{owner}/{repo}/contributors")

   @GET("getcar/{id}/")
   Call<Car> contributors(
           @Path("id") int id
   );

    @POST("createcar/")
    Call<Car> createCar(@Body Car car);

}
