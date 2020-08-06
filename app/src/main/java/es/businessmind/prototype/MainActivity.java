package es.businessmind.prototype;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.appbar);
        setSupportActionBar(myToolbar);
    }

    public void showDialog(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);;

        LayoutInflater inflater = getLayoutInflater();

        view = inflater.inflate(R.layout.login_popup, null);

        final EditText username = view.findViewById(R.id.et_username);
        final EditText password = view.findViewById(R.id.et_password);
        Button btn_login = view.findViewById(R.id.loginDialogButton);
        Button btn_cancel = view.findViewById(R.id.cancelDialogButton);

        alert.setView(view);
        alert.setCancelable(false);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if(user.equals("admin") && pass.equals("admin")) {
                    startActivity(new Intent(MainActivity.this, BaseDeDatos.class));
                    Toast.makeText(getApplicationContext(), "Bienvenido: "+user, Toast.LENGTH_LONG).show();
                } else if (user.equals("user") && pass.equals("user1234")) {
                    startActivity(new Intent(MainActivity.this, StoreActivity.class));
                    Toast.makeText(getApplicationContext(), "Bienvenido: "+user, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Usuario incorrecto: "+user, Toast.LENGTH_LONG).show();
                }
            }
        });

        final AlertDialog dialog = alert.create();
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.show();

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}