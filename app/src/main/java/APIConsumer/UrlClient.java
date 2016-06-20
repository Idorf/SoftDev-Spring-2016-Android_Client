package APIConsumer;


import model.Event;
import model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Idorf on 05-05-2016.
 */

public interface UrlClient {

    @GET("select_event")
    Call<Event> selectEvent(@Query("eventID") int id);


    @POST("create_User/")
    Call<User> createUser(@Body User user);

    @POST("create_event/")
    Call<Event> createEvent(@Body Event event);

}