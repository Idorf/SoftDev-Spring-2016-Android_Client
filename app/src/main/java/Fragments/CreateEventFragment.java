package Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.idorf.materialdesign2.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import APIConsumer.ServiceGenerator;
import APIConsumer.UrlClient;
import model.Event;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CreateEventFragment extends Fragment  implements GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks {

    private GoogleApiClient mGoogleApiClient;

    private int PLACE_PICKER_REQUEST = 1;



    TextInputLayout titleWrapper;
    TextInputLayout descriptionWrapper;
    TextInputLayout datePlaceholderWrapper;
    TextInputLayout locationplaceholderWrapper;

    String title;
    String description;
    String date;
    String location;
    Event event;

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    public CreateEventFragment() {
    }

    public static CreateEventFragment newInstance() {
        return new CreateEventFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_create_event, container, false);

        titleWrapper = (TextInputLayout) layout.findViewById(R.id.titleWrapper);
//          descriptionWrapper = (TextInputLayout) layout.findViewById(R.id.descriptionWrapper);
//          datePlaceholderWrapper = (TextInputLayout) layout.findViewById(R.id.dateWrapper);
//          locationplaceholderWrapper = (TextInputLayout) layout.findViewById(R.id.locationpWrapper);

        final Button btn = (Button) layout.findViewById(R.id.sendButton);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //need refacktoring!!!

                title = titleWrapper.getEditText().getText().toString();
/*                description = descriptionWrapper.getEditText().getText().toString();
                date = datePlaceholderWrapper.getEditText().getText().toString();
                location = locationplaceholderWrapper.getEditText().getText().toString();*/


                doLogin();

                /*
                if (validateLength(title)) {
                    emailWrapper.setError("Not a valid email address!");
                } else if (!validatePasswordLength(password)) {
                    passwordWrapper.setError("Not a valid password!");
                } else if (!validatePassword(password, confirmPassword)) {
                    passwordWrapper.setError("Passwords don't match!");
                } else {
                    usernameWrapper.setErrorEnabled(false);
                    passwordWrapper.setErrorEnabled(false);
                }*/

            }
        });

        return layout;
    }

    public void doLogin() {
        Toast.makeText(getActivity().getApplicationContext(), "OK! I'm performing login.", Toast.LENGTH_SHORT).show();
        // TODO: login procedure; not within the scope of this tutorial.



        UrlClient client = ServiceGenerator.createService(UrlClient.class);

/*

        Call<Event> callCreateEvent = client.createUser(event);

        callCreateEvent.enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {

                System.out.println("onResponse");

                if (response.isSuccessful()) {
                    System.out.println("isSuccessful");

                    //   for (Car contributor : response.body()) {
                //    System.out.println("Username: " + response.body().getTitle() + "Email: -" + response.body().getDescription() + "Email: -" + response.body().getLocation());
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

*/

    }

    public boolean validateLength(String input) {
        return input.length() == 0;
    }

    public boolean validatePassword(String password, String confirmPassword) {
        return (password.equals(confirmPassword));
    }

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mGoogleApiClient != null)
            mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }

    private void displayPlacePicker() {
        if (mGoogleApiClient == null || !mGoogleApiClient.isConnected())
            return;

        try {

            PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();

            Intent intent = intentBuilder.build(getActivity());

            startActivityForResult(intent, PLACE_PICKER_REQUEST);

        } catch (GooglePlayServicesRepairableException e) {

            e.printStackTrace();

        } catch (GooglePlayServicesNotAvailableException e) {

            e.printStackTrace();

        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST && resultCode == getActivity().RESULT_OK) {



            titleWrapper.getEditText().setText(PlacePicker.getPlace(data, getActivity()).getName().toString());
            locationplaceholderWrapper.getEditText().setText(PlacePicker.getPlace(data, getActivity()).getAddress().toString());

            System.out.println("------------------------------------" + PlacePicker.getPlace(data, getActivity()).getLatLng().toString());

        }
    }


    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
