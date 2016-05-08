package Fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;


import android.support.v4.app.FragmentTransaction;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.idorf.materialdesign2.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import APIConsumer.ServiceGenerator;
import APIConsumer.UrlClient;



public class CreateEventFragment extends Fragment  implements GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks {

    private GoogleApiClient mGoogleApiClient;

    private int PLACE_PICKER_REQUEST = 1;

    TextInputLayout titleWrapper;

    TextInputLayout locationWrapper;
    TextInputLayout dateWrapper;
    TextInputLayout timeWrapper;
    ImageView googleplaceBtn;
    ImageView timeBtn;
    ImageView calenderBtn;

    EditText title;


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


      //  locationWrapper = (TextInputLayout) getActivity().findViewById(R.id.locationpWrapper);
      //  googleplaceBtn = (ImageView) getActivity().findViewById(R.id.googleplaceBtn);


        mGoogleApiClient = new GoogleApiClient
                .Builder(getActivity())
                .enableAutoManage(getActivity(), 0, this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_create_event, container, false);

        titleWrapper = (TextInputLayout) layout.findViewById(R.id.titleWrapper);
        googleplaceBtn = (ImageView) layout.findViewById(R.id.googleplaceBtn);
        timeBtn = (ImageView) layout.findViewById(R.id.timeBtn);
        calenderBtn = (ImageView) layout.findViewById(R.id.calenderBtn);
        dateWrapper = (TextInputLayout) layout.findViewById(R.id.dateWrapper);
        timeWrapper = (TextInputLayout) layout.findViewById(R.id.timeWrapper);


        locationWrapper = (TextInputLayout) layout.findViewById(R.id.locationpWrapper);

       // titleWrapper.getEditText().setText("fggghgg");

      //  final Button btn = (Button) layout.findViewById(R.id.API_Button);

     //   final TextInputLayout btn = (TextInputLayout) layout.findViewById(R.id.locationpWrapper);

        googleplaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                doLogin();

            }
        });
        timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showTimePickerDialog(v);

            }
        });
        calenderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDatePickerDialog(v);

            }
        });

        return layout;
    }

    public void doLogin() {
        // TODO: login procedure; not within the scope of this tutorial.

        displayPlacePicker();


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
        if (mGoogleApiClient == null || !mGoogleApiClient.isConnected()) {

            Toast.makeText(getActivity().getApplicationContext(), "not connedted.", Toast.LENGTH_SHORT).show();


            return;
        }
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
        if (requestCode == PLACE_PICKER_REQUEST && resultCode == Activity.RESULT_OK) {

           //locationWrapper.getEditText().setText(PlacePicker.getPlace(data, getActivity()).getAddress().toString());
            locationWrapper.getEditText().setText(PlacePicker.getPlace(data, getActivity()).getAddress().toString());

            System.out.println("------------------------------------" + PlacePicker.getPlace(data, getActivity()).getAddress().toString());

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

    public void showTimePickerDialog(View v) {

        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getChildFragmentManager(), "timePicker");

    }

    public  class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {



            timeWrapper.getEditText().setText(hourOfDay+":" + minute);

            System.out.println( "---------------------------------------" +hourOfDay +minute );


            // Do something with the time chosen by the user
        }
    }


    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getChildFragmentManager(), "datePicker");
    }

    public  class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {

            dateWrapper.getEditText().setText(day+"/" + month +"/"+year);

            System.out.println("---------------------------------------" + year +month + day);

            // Do something with the date chosen by the user
        }
    }











}





/*



    public void doLogin() {
        Toast.makeText(getActivity().getApplicationContext(), "OK! I'm performing login.", Toast.LENGTH_SHORT).show();
        // TODO: login procedure; not within the scope of this tutorial.



        UrlClient client = ServiceGenerator.createService(UrlClient.class);
*/

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
