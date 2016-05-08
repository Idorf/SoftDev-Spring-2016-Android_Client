package Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.idorf.materialdesign2.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import APIConsumer.Car;
import APIConsumer.ServiceGenerator;
import APIConsumer.UrlClient;
import model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserRegistrationFragment extends Fragment {

    String username;
    String password;
    String email;

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    public UserRegistrationFragment() {
    }

    public static UserRegistrationFragment newInstance() {
        return new UserRegistrationFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Activity activity = getActivity();

        View layout = inflater.inflate(R.layout.fragment_user_registration, container, false);

        final TextInputLayout usernameWrapper = (TextInputLayout) layout.findViewById(R.id.usernameWrapper);
        final TextInputLayout emailWrapper = (TextInputLayout) layout.findViewById(R.id.emailWrapper);
        final TextInputLayout passwordWrapper = (TextInputLayout) layout.findViewById(R.id.passwordWrapper);
        final TextInputLayout confirmPasswordWrapper = (TextInputLayout) layout.findViewById(R.id.confirmPasswordWrapper);

        final Button btn = (Button) layout.findViewById(R.id.btn);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                 username = usernameWrapper.getEditText().getText().toString();
                 password = passwordWrapper.getEditText().getText().toString();
                String confirmPassword = confirmPasswordWrapper.getEditText().getText().toString();
                email = emailWrapper.getEditText().getText().toString();

                if (!validateEmail(email)) {
                    emailWrapper.setError("Not a valid email address!");
                } else if (!validatePasswordLength(password)) {
                    passwordWrapper.setError("Not a valid password!");
                } else if (!validatePassword(password, confirmPassword)) {
                    passwordWrapper.setError("Passwords don't match!");
                } else {
                    usernameWrapper.setErrorEnabled(false);
                    passwordWrapper.setErrorEnabled(false);
                    doLogin();
                }
            }
        });

        return layout;
    }

    public void doLogin() {
        Toast.makeText(getActivity().getApplicationContext(), "OK! I'm performing login.", Toast.LENGTH_SHORT).show();
        // TODO: login procedure; not within the scope of this tutorial.


/*
        UrlClient client = ServiceGenerator.createService(UrlClient.class);

      //  User user = new User(username, password, 0, email);

        Car car = new Car(9999,"Pink", 1000);
        Call<Car> call3 = client.createCar(car);
        Call<User> callCreateUser = client.createUser(user);

        Call<Car> call =
                client.contributors(1);
        Call<Car> call2 =
                client.contributors(2);
        //   Car car = new Car(9999,"Pink", 1000);
        //  Call<Car> call3 =
        //        client.createCar(car);


        //  client.contributors("square", "retrofit");

        callCreateUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                System.out.println("onResponse");

                if (response.isSuccessful()) {
                    System.out.println("isSuccessful");

                    //   for (Car contributor : response.body()) {
                    System.out.println("Username: " + response.body().getUserName() + "Email: -" + response.body().getEmail() + "Email: -" + response.body().getPassword());
                    //}
                } else {
                    // error response, no access to resource?
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });*/


























    }

    public boolean validatePasswordLength(String password) {
        return password.length() > 5;
    }

    public boolean validatePassword(String password, String confirmPassword) {
        return (password.equals(confirmPassword));
    }

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }


}
