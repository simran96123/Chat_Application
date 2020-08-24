package com.example.chatting_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_Activity extends AppCompatActivity {

    EditText userETLogin , passETLogin;
    Button LoginBtn;

    //Firebase
    FirebaseAuth auth;
    FirebaseUser firebaseUser;

//    @Override
//    protected void onStart() {
//
//        super.onStart();
//        //firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//
//        // checking for users existence
//        if (auth.getCurrentUser()!= null){
//            Intent i = new Intent(Login_Activity.this , MainActivity.class);
//            startActivity(i);
//            finish();
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        userETLogin = findViewById(R.id.admin_Email);
        passETLogin = findViewById(R.id.admin_passwrd);
        LoginBtn = findViewById(R.id.login_btn);

        //Firebase Auth;
        auth = FirebaseAuth.getInstance();

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userETLogin.getText().toString().isEmpty()) {
                    userETLogin.setError("E-mail");
                    return;
                } else {
                    userETLogin.setError(null);
                }
                if (passETLogin.getText().toString().isEmpty()) {
                    passETLogin.setError("Password");
                    return;
                } else {
                    passETLogin.setError(null);
                }

                firebaseLogin();
            }
        });

        if (auth.getCurrentUser() != null) {
            Intent intent = new Intent(Login_Activity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

        private  void firebaseLogin() {
            auth.signInWithEmailAndPassword(userETLogin.getText().toString(), passETLogin.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Toast.makeText(Login_Activity.this, "Successfull Login", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(Login_Activity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(Login_Activity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
      //  }






        //Login Button

//        LoginBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String email_text = userETLogin.getText().toString();
//                String pass_text = passETLogin.getText().toString();
//
//
//                //checking if it empty
//                if (TextUtils.isEmpty(email_text) || TextUtils.isEmpty(pass_text)){
//                    Toast.makeText(Login_Activity.this, "Please fill the fields", Toast.LENGTH_SHORT).show();
//                }
//
//                  else {
//                      auth.signInWithEmailAndPassword(email_text , pass_text)
//                              .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                                  @Override
//                                  public void onComplete(@NonNull Task<AuthResult> task) {
//                                    if (task.isSuccessful()) {
//                                        Toast.makeText(Login_Activity.this, "Successfull Login", Toast.LENGTH_SHORT).show();
//
//                                        Intent intent = new Intent(Login_Activity.this, MainActivity.class);
//                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                                        startActivity(intent);
//                                        finish();
//                                    }
//                                    else {
//                                        Toast.makeText(Login_Activity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
//                                    }
//                                  }
//                              });
//
//
//
//
//
//
//
//
//
//
//                }
//            }
//        });
//
//
//    }
}