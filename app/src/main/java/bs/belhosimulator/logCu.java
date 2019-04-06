package bs.belhosimulator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class logCu extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logcu);
    }
    public void gotomainfromcu(View view){
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    public void codePairing(View view){
        EditText edtxt=findViewById(R.id.editText);
        String code=edtxt.getText().toString();

        finish();
        startActivity(new Intent(this, CuidadorActivity.class));
    }
}
