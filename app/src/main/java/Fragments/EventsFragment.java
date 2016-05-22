package Fragments;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.idorf.materialdesign2.R;

import java.util.ArrayList;
import java.util.List;

import APIConsumer.ServiceGenerator;
import APIConsumer.UrlClient;
import adapter.EventAdapter;
import adapter.EventAdapter2;
import model.Event;
import model.Event3;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsFragment extends Fragment {

    static int[] images = new int[] {R.drawable.beach, R.drawable.moviedog, R.drawable.kongeligeteater, R.drawable.paintball, R.drawable.concert, R.drawable.movieinterior};

    UrlClient client = ServiceGenerator.createService(UrlClient.class);
    public ArrayList<Event> eventList = new ArrayList<>();

    private List<Event3> event3s;
    private RecyclerView rv;

    public EventsFragment() {


    }
    public static EventsFragment newInstance() {
        return new EventsFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Activity activity = getActivity();

        View layout = inflater.inflate(R.layout.recycle_view, container, false);
        rv = (RecyclerView) layout.findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(activity);
        rv.setLayoutManager(llm);
        event3s = new ArrayList<>();

        for(int i=1; i<=100; i++){

            run(i);
        }





        // Inflate the layout for this fragment
        return layout;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void initializeData(int i){

       // event3s.add(eventList.get(i));
       // event3s.add(new Event3("KoncertHuset", "3 km", "Beethoven & Brahms", R.drawable.koncerthuset));

    }

/*    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(persons);
        rv.setAdapter(adapter);
    }*/
    private void initializeAdapter(){

        EventAdapter adapter = new EventAdapter(eventList);
        rv.setAdapter(adapter);
    }

    public void run(int i){

        Call<Event> callSelectEvent= client.selectEvent(i);

        callSelectEvent.enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {

                System.out.println("onResponse");

                if (response.isSuccessful()) {
                    System.out.println("isSuccessful");

                    int imageId = (int)(Math.random() * images.length);

                    response.body().setPictureTemp(images[imageId]);
                    eventList.add(response.body());

                  //  initializeData(i);
                    initializeAdapter();

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
/*


    private void initializeData(){

        event3s = new ArrayList<>();
        event3s.add(new Event3("KoncertHuset", "3 km", "Beethoven & Brahms", R.drawable.koncerthuset));
        event3s.add(new Event3("Østre gasværk", "10 km", "Skammerens Datter", R.drawable.oestre_gasvaerk));
        event3s.add(new Event3("Palads", "5 km", "CAPTAIN AMERICA: CIVIL WAR", R.drawable.palads));





    }*/
