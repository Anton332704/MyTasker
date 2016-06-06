package edu.bstu.iipo_15_ivt_1.kuznetsov_anton.mytasker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import iipo.asynctask.AuthorizationTask;

/**
 * Created by user on 05.11.2015.
 */
public class Authorization extends AppCompatActivity {
    EditText editLogin;
    EditText editPassword;
    SharedPreferences spLogin;
    ArrayList<String> arrayList;
    private final static String EXIST = "exist";
    private final static String NOT_EXIST = "not_exist";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authorization);
        spLogin = PreferenceManager.getDefaultSharedPreferences(this);
        String str = spLogin.getString("login", "");
        if(!str.equals(""))
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        arrayList = new ArrayList<String>();
        editLogin = (EditText)findViewById(R.id.input_login);
        editPassword = (EditText)findViewById(R.id.input_password);
        Object o = editLogin.hashCode();
        int k = 0;
    }

    public void enterToMainActivity(View v) throws ExecutionException, InterruptedException {

        String strLogin = editLogin.getText().toString();
        String strPassword = editPassword.getText().toString();

        SharedPreferences.Editor editor = spLogin.edit();
        editor.putString("login", strLogin);
        editor.putString("password", strPassword);
        editor.commit();
        AuthorizationTask authorizationTask = new AuthorizationTask(this, arrayList);
        authorizationTask.execute(strLogin, strPassword);
        if(authorizationTask.get()==NOT_EXIST)
        {
            Toast.makeText(this, "Данного пользователя не существует", Toast.LENGTH_LONG).show();
            return;
        }
        if(authorizationTask.get()=="1")
        {
            Toast.makeText(this, "Проверьте соединение с интернетом", Toast.LENGTH_LONG).show();

        }
        if(authorizationTask.get()=="12345")
        {
            Toast.makeText(this, "Данные не пришли :С", Toast.LENGTH_LONG).show();
            return;
        }
//        arrayList = authorizationTask.get();
//        intent.putStringArrayListExtra("tasks", arrayList);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void startRegistration(View v)
    {
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
        finish();
    }
}
