package pl.foodfront;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class Main extends AppCompatActivity implements ICallback {

    private static boolean LOGGED = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO zmienna będzie przekazywana do klasy Bridge żeby uzyskać sprzężenie zwrotne
        final ICallback callback = this;

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

    @Override
    public void loginInfo(Boolean logged, String msg) {
        // TODO na razie tylko powiadomienie o logowaniu
        this.LOGGED = logged;
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
