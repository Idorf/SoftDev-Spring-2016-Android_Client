package Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.idorf.materialdesign2.R;

import java.util.ArrayList;
import java.util.List;

import APIConsumer.ServiceGenerator;
import APIConsumer.UrlClient;
import adapter.EventAdapter;
import model.Event;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsFragment extends Fragment {

    static int[] images = new int[] {R.drawable.beach, R.drawable.moviedog, R.drawable.kongeligeteater, R.drawable.paintball, R.drawable.concert, R.drawable.movieinterior};

    UrlClient client = ServiceGenerator.createService(UrlClient.class);
    public ArrayList<Event> eventList = new ArrayList<>();

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

        for(int i=1; i<=100; i++){

            run(i);
        }
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

                    initializeAdapter();

                    System.out.println("Username: " + response.body().getTitle() + "Email: -" + response.body().getDescription() + "Email: -" + response.body().getLocation());

                } else {
                }

            }

            @Override
            public void onFailure(Call<Event> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });

    }
}