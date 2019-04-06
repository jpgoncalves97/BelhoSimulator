package bs.belhosimulator;


import android.Manifest;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class logBelho extends AppCompatActivity{

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private ListView lstNames;

    ArrayList<String> listItems=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logbelho);

        this.lstNames = findViewById(R.id.listadecontactos);
        lstNames.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lstNames.setSelector(android.R.color.holo_blue_dark);
        showContacts();
    }

    private void showContacts() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        } else {
            // Android version is lesser than 6.0 or the permission is already granted.
            List<contacto> contacts = getContactNames();
            ArrayAdapter<contacto> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contacts);
            lstNames.setAdapter(adapter);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                showContacts();
            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private List<contacto> getContactNames() {
        List<contacto> contacts = new ArrayList<>();

        ContentResolver cr = getContentResolver();

        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                Cursor cursornum = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id,null, null);
                if(cursornum.moveToNext()){
                        String num = cursornum.getString(cursornum.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        contacts.add(new contacto(name, num));
                }
                cursornum.close();
            }
        }





        Collections.sort(contacts,new IgnoreCaseComparator());

        cursor.close();
        return contacts;
    }

    public void gotomainfrombelho(View view){
        finish();
        startActivity(new Intent(this, MainActivity.class));


    }
    public void startPairing(View view){

        ListView lst=findViewById(R.id.listadecontactos);
        int pos = lst.getCheckedItemPosition();
        contacto o = (contacto) lst.getAdapter().getItem(pos);

        //send nudes to o

    }

}

class IgnoreCaseComparator implements Comparator<contacto> {
    public int compare(contacto conA, contacto conB) {
        return conA.Nome.compareToIgnoreCase(conB.Nome);
    }
}

class contacto implements Comparator<contacto> {
    public String Nome;
    public String Num;

    contacto(String nome, String num){
        this.Nome=nome;
        this.Num=num;
    }

    @Override
    public String toString() {
        return Nome;
    }

    public int compare(contacto conA, contacto conB) {
        return conA.Nome.compareToIgnoreCase(conB.Nome);
    }
}
