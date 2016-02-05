package pl.foodfront.views;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import pl.foodfront.R;
import pl.foodfront.communication.Bridge;

/**
 * Created by Michał Stobiński on 2016-02-04.
 */
@SuppressWarnings("deprecation")
public class ActivityLogin extends Activity implements ILoginCallback {

    private ILoginCallback callback;
    private EditText etUser;
    private EditText etPass;
    private Bridge bridge;
    private Intent intent;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        this.callback = this;
        recallInterruptedConenction(); // w przypadku zmiany konfiguracji próbujemy uzyskać wcześniejszą instancję klasy Bridge

        etUser = (EditText) findViewById(R.id.loginEtUser);
        etPass = (EditText) findViewById(R.id.loginEtPass);

        intent = new Intent(this, Main.class);

        findViewById(R.id.loginButConfirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUser.getText().toString();
                String password = etPass.getText().toString();
                boolean userCondition = !(username.equals(null) || username.equals(""));
                boolean passCondition = !(password.equals(null) || password.toString().equals(""));

                if (userCondition && passCondition) {
                    bridge.connectActivity(callback);
                    bridge.login(username, password);
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

    private void recallInterruptedConenction() {

        bridge = (Bridge) this.getLastNonConfigurationInstance();

        if(bridge != null) {
            bridge.connectActivity(callback);
        } else {
            bridge = new Bridge();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(bridge != null) {
            bridge.disconnectActivity();
        }
    }

    @Override
    public Object onRetainNonConfigurationInstance() {
        return bridge;
    }

    @Override
    public void loginInfo(Boolean logged, String msg) {
        if(logged) {
            startActivity(intent);
            Toast.makeText(this, "Logowanie zakończone sukcesem", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Logowanie nieudane", Toast.LENGTH_SHORT).show();
        }
    }
}
