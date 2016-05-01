package activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.idorf.materialdesign2.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserRegistrationFragment extends Fragment {


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

        View layout = inflater.inflate(R.layout.user_registration, container, false);

        final TextInputLayout usernameWrapper = (TextInputLayout) layout.findViewById(R.id.usernameWrapper);
        final TextInputLayout emailWrapper = (TextInputLayout) layout.findViewById(R.id.emailWrapper);
        final TextInputLayout passwordWrapper = (TextInputLayout) layout.findViewById(R.id.passwordWrapper);
        final TextInputLayout confirmPasswordWrapper = (TextInputLayout) layout.findViewById(R.id.confirmPasswordWrapper);

        final Button btn = (Button) layout.findViewById(R.id.btn);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String username = usernameWrapper.getEditText().getText().toString();
                String password = passwordWrapper.getEditText().getText().toString();
                String confirmPassword = confirmPasswordWrapper.getEditText().getText().toString();
                String email = emailWrapper.getEditText().getText().toString();

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
