package tech.sadana.mujconnectadmin;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LitmusShow extends AppCompatActivity {
    DatabaseReference databaseReference;

    ProgressDialog progressDialog;

    List<Details> list = new ArrayList<>();

    RecyclerView recyclerView;

    RecyclerViewAdapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_litmus_show);recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        adapter = new RecyclerViewAdapter(LitmusShow.this, null);
        recyclerView.setAdapter(adapter);

        progressDialog = new ProgressDialog(LitmusShow.this);

        progressDialog.setMessage("Loading Data from Firebase Database");

        progressDialog.show();

        databaseReference = FirebaseDatabase.getInstance().getReference("litmus");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                list.clear();
                Log.w("Litmus", snapshot.toString());
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Details studentDetails = dataSnapshot.getValue(Details.class);

                    list.add(studentDetails);
                }

                adapter = new RecyclerViewAdapter(LitmusShow.this, list);

                recyclerView.setAdapter(adapter);

                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                progressDialog.dismiss();

            }
        });
    }
}
