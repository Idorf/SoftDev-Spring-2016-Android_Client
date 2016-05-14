package APIConsumer;


import model.Event;
import model.Event3;
import model.User;
import retrofit2.Call;
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


    @POST("create_User/")
    Call<User> createUser(@Body User user);

    @POST("create_event/")
    Call<Event> createEvent(@Body Event event);

}