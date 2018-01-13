package com.example.fidelmomolo.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    Button submit,value_button;
    EditText value;
   private TextView view1;
    FirebaseApp firebaseApp;
    ArrayList<String>arrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        submit=findViewById(R.id.submit);
        value_button=findViewById(R.id.value_button);
        value=findViewById(R.id.value);
        listView=findViewById(R.id.list);




        //used to get the database reference i.e connection to the database url is automatically determined in the json file
        final DatabaseReference rootRef1 = FirebaseDatabase.getInstance().getReference();

        final DatabaseReference rootRef2 = FirebaseDatabase.getInstance()
                .getReferenceFromUrl("https://firethebase-dcc84.firebaseio.com/Name");




        final ArrayAdapter <String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);

        FirebaseListAdapter<String>firebaseListAdapter=new FirebaseListAdapter<String>(
                //usable after implementing the firebase ui dependency
                this,
                String.class,//type of data to be handled
                android.R.layout.simple_list_item_1,//list type the default android one has been used
                rootRef1// data base reference i.e connection to the database


        ) {
            @Override
            protected void populateView(View v, String model, int position) {
                TextView textView=v.findViewById(android.R.id.text1);//references the text view in the android simple_list_item_1 list view style
                textView.setText(model);

            }
        };





        //used when dealing with a list of data
//        rootRef1.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//
//              String value=dataSnapshot.getValue(String.class);
//
//
//
//
//              arrayAdapter.add(value);
//
//              arrayAdapter.notifyDataSetChanged();
//
//
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });




      listView.setAdapter(firebaseListAdapter);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference firebaseChild=rootRef1.child("Name");
                firebaseChild.setValue("Am the beast");


            }
        });


        value_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String finalvalue=value.getText().toString();




                //   The keys are manually generated
                // DatabaseReference firebaseChild=rootRef2.child("First_Name");
                // firebaseChild.setValue(finalvalue);


                    rootRef1.push().setValue(finalvalue);//the keys are automatically generated

//                rootRef1.push().child("Mato").setValue(55);//used to automatically generate the user id

            }
        });


        //code for reading single data value from the database
//       rootRef2.addListenerForSingleValueEvent(new ValueEventListener() {
//           @Override
//           public void onDataChange(DataSnapshot dataSnapshot) {
//               if (dataSnapshot.exists()){
//
//
//                   String man=dataSnapshot.getValue(String.class);
//
//
//                   Toast.makeText(MainActivity.this,man,Toast.LENGTH_LONG).show();
//
//
//
//
//               }
//
//
//           }
//
//           @Override
//           public void onCancelled(DatabaseError databaseError) {
//
//           }
//
//       });







    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent=new Intent(this,Image.class);
            startActivity(intent);



            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
