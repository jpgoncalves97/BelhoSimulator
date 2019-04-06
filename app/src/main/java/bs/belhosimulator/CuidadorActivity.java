package bs.belhosimulator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;

public class CuidadorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cuidador_home);

    }

    public void meds(View view){
        finish();
        startActivity(new Intent(this, cuidador_meds.class));
    }
    public void avisos(View view){
        finish();
        startActivity(new Intent(this, cuidador_avisos.class));
    }
}
