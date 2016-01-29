package pl.foodfront;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import pl.foodfront.communication.Bridge;
import pl.foodfront.serialized.Spots;

public class Main extends AppCompatActivity implements ICallback {

    private static boolean LOGGED = false;
    private Bridge bridge;
    private ICallback callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO zmienna będzie przekazywana do klasy Bridge żeby uzyskać sprzężenie zwrotne
        this.callback = this;
        recallInterruptedConenction(); // w przypadku zmiany konfiguracji próbujemy uzyskać wcześniejszą instancję klasy Bridge

        findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main.this, ActivitySearch.class);
                startActivity(i);
            }
        });

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                findViewById(R.id.login_form).setVisibility(View.INVISIBLE);

            }
        });

        findViewById(R.id.consume).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void recallInterruptedConenction() {

        bridge = (Bridge) this.getLastCustomNonConfigurationInstance();

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
    public Object onRetainCustomNonConfigurationInstance() {
        return bridge;
    }

    @Override
    public void loginInfo(Boolean logged, String msg) {
        // TODO na razie tylko powiadomienie o logowaniu
        this.LOGGED = logged;
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void invokeSpots(Spots spots) {
        // TODO na razie tylko powiadomienie o nowych miejscach
        Toast.makeText(this, "Witaj w " + spots.getPlaces()[0].getTitle(), Toast.LENGTH_SHORT).show();
    }

}
