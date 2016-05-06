package APIConsumer;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Idorf on 05-05-2016.
 */

public interface UrlClientVirker {
  //  @GET("/repos/{owner}/{repo}/contributors")

   @GET("getcar/{id}/")
   Call<Car> contributors(
           @Path("id") int id

   );
}
