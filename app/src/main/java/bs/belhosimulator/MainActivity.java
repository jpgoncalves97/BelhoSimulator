package bs.belhosimulator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void gotobelho(View view){
        finish();
        startActivity(new Intent(this, logBelho.class));
    }
    public void gotocu(View view){
        finish();
        startActivity(new Intent(this, logCu.class));
    }
}
