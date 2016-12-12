package com.example.snowiot.snowiotsimple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class MainActivity extends AppCompatActivity {


    ListView mListView;


    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mLedSignalRef = mRootRef.child("ledSignal");    //reference to LED status variable


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            mListView = (ListView) findViewById(R.id.sensorListView);

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://snowtotals-68015.firebaseio.com/Chat");

            mAdapter = new FirebaseListAdapter<Chat>(this, Chat.class, android.R.layout.two_line_list_item, databaseReference) {
                @Override
                protected void populateView(View view, Chat chatMessage, int position) {
                    ((TextView)view.findViewById(android.R.id.text1)).setText(chatMessage.getName());
                    ((TextView)view.findViewById(android.R.id.text2)).setText(chatMessage.getText());

                }
            };
            mListView.setAdapter(mAdapter);


        }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
        }




        }