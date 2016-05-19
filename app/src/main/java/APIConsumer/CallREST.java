package APIConsumer;

import android.util.Log;

import java.util.ArrayList;

import model.Event;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Idorf on 13-05-2016.
 */
public class CallREST {

    UrlClient client = ServiceGenerator.createService(UrlClient.class);
   public ArrayList<Event> eventList = new ArrayList<>();

    public CallREST(){

        for(int i=1; i<=37; i++){
            System.out.println("Count is: " + i);
        }

        for(int i=1; i<=37; i++){

            run(i);
        }

        System.out.println("-----------------------------------------------------------------------");
       // System.out.println("Username: " + eventList.get(37).getLocation());

    }

    public void run(int i){

        Call<Event> callSelectEvent= client.selectEvent(i);

        callSelectEvent.enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {

                System.out.println("onResponse");

                if (response.isSuccessful()) {
                    System.out.println("isSuccessful");

                    eventList.add(response.body());

                    //   for (Car contributor : response.body()) {
                    System.out.println("Username: " + response.body().getTitle() + "Email: -" + response.body().getDescription() + "Email: -" + response.body().getLocation());
                    //}
                } else {
                    // error response, no access to resource?
                }

            }

            @Override
            public void onFailure(Call<Event> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });

    }

}
