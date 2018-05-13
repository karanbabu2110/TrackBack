package ga.raja007.clientapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    String value = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Location");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                    value = dataSnapshot.getValue(String.class);
                    TextView textView = (TextView) findViewById(R.id.locText);
                    textView.setText(value);
                    String[] seperated = value.split(",");
                    String latipos = seperated[0].trim();
                    String longipos = seperated[1].trim();
                    double dLat = Double.parseDouble(latipos);
                    double dLong = Double.parseDouble(longipos);

                    Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                    intent.putExtra("LOCVAL", value);
                    startActivity(intent);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });
    }

    /*public void buttonClicked(View view)
    {
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        intent.putExtra("LOCVAL", value);
        startActivity(intent);
    }*/

}
