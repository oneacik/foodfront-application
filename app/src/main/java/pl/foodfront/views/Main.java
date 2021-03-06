package pl.foodfront.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import pl.foodfront.R;
import pl.foodfront.communication.Bridge;
import pl.foodfront.serialized.Spots;

public class Main extends AppCompatActivity implements IMainCallback {

    private Bridge bridge;
    private IMainCallback callback;

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
                bridge.connectActivity(callback);
                bridge.getSpots();
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
    public void invokeSpots(Spots spots) {
        Intent i = new Intent(Main.this, ActivitySearch.class);

        if(spots != null) { i.putExtra("spots", spots); }

        startActivity(i);
    }

}
