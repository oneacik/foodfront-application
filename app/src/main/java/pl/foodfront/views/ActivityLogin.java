package pl.foodfront.views;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import pl.foodfront.R;

/**
 * Created by Michał Stobiński on 2016-02-04.
 */
public class ActivityLogin extends Activity implements ILoginCallback {

    private EditText etUser;
    private EditText etPass;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        etUser = (EditText) findViewById(R.id.loginEtUser);
        etPass = (EditText) findViewById(R.id.loginEtPass);

        final Intent intent = new Intent(this, Main.class);

        findViewById(R.id.loginButConfirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUser.getText().toString();
                String password = etPass.getText().toString();
                boolean userCondition = !(username.equals(null) || username.equals(""));
                boolean passCondition = !(password.equals(null) || password.toString().equals(""));

                if (userCondition && passCondition) {
                    // TODO walidacja użytkownika
                    startActivity(intent);
                } else if (!(userCondition && passCondition)) {
                    etUser.setHintTextColor(Color.RED);
                    etPass.setHintTextColor(Color.RED);
                    return;
                } else if (userCondition) {
                    etUser.setHintTextColor(Color.RED);
                    etPass.setHintTextColor(Color.LTGRAY);
                } else if (passCondition) {
                    etUser.setHintTextColor(Color.LTGRAY);
                    etPass.setHintTextColor(Color.RED);
                }
            }
        });

        findViewById(R.id.loginButCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

    }

    @Override
    public void loginInfo(Boolean logged, String msg) {
        // TODO zareagowanie na informację zwrotną o procesie logowania
    }
}
