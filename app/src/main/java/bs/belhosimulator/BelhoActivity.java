package bs.belhosimulator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;

public class BelhoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.belho_home);

    }

    public void meds_24(View view){
        finish();
        startActivity(new Intent(this, belho_meds.class));
    }
}
