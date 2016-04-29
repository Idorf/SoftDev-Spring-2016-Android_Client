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


public class UserFragment extends Fragment {

    private List<Person> persons;
    private RecyclerView rv;

    public UserFragment() {


    }
    public static UserFragment newInstance() {
        return new UserFragment();
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

    private void initializeData()
    {
        persons = new ArrayList<>();
        persons.add(new Person("Troll face", "23 years", R.drawable.troll_face));
        persons.add(new Person("Yao Ming", "35 years", R.drawable.yao_ming));
        persons.add(new Person("Forever alone guy", "40 years", R.drawable.forever_alone));
    }

       private void initializeAdapter(){
            RVAdapter adapter = new RVAdapter(persons);
            rv.setAdapter(adapter);
        }
}