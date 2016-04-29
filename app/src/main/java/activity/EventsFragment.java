package activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.idorf.materialdesign2.R;

import java.util.ArrayList;
import java.util.List;


public class EventsFragment extends Fragment {

    private List<Event> events;
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

        initializeData();
        initializeAdapter();




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

    private void initializeData(){
        events = new ArrayList<>();
        events.add(new Event("KoncertHuset", "3 km", "Beethoven & Brahms", R.drawable.koncerthuset));
        events.add(new Event("Østre gasværk", "10 km", "Skammerens Datter", R.drawable.oestre_gasvaerk));
        events.add(new Event("Palads", "5 km", "CAPTAIN AMERICA: CIVIL WAR", R.drawable.palads));
    }

/*    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(persons);
        rv.setAdapter(adapter);
    }*/
    private void initializeAdapter(){
        EventAdapter adapter = new EventAdapter(events);
        rv.setAdapter(adapter);
    }
}