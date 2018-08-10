package tech.sadana.mujconnectadmin;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();



    }

    public void loginNow(final View view){
        email = findViewById(R.id.emailid);
        password = findViewById(R.id.password);

        String semail = email.getText().toString().trim();
        String spassword = password.getText().toString().trim();
        if(semail.isEmpty() || spassword.isEmpty()){
            Toast.makeText(view.getContext(),"Empty Fields",Toast.LENGTH_SHORT).show();
        }
        else{
        mAuth.signInWithEmailAndPassword(semail, spassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(view.getContext(),"Successfully Logged In "+user.getEmail(), Toast.LENGTH_SHORT).show();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(view.getContext(),"Incorrect Credentials",Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
    }

    public void updateUI(FirebaseUser user){
        Intent intent;
        if(user.getEmail().trim().equals("mujaperture@gmail.com")){
            intent = new Intent(MainActivity.this, ApertureShow.class);

        }
        else{
            intent = null;
        }
        startActivity(intent);

    }

}
