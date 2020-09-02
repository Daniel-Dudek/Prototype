package es.businessmind.prototype;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class BaseDeDatos extends AppCompatActivity {

    DatabaseReference myDB = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRootChild = myDB.child("texto");

    EditText etTitulo, etDescipcion, etPrecio;
    Button btnAgregar, btnBuscar, btnEditar, btnEliminar, btnMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar myToolbar = findViewById(R.id.appbar);
        setSupportActionBar(myToolbar);
        setContentView(R.layout.activity_base_de_datos);
        etTitulo = findViewById(R.id.edTxt_tituloItem);
        etDescipcion = findViewById(R.id.edTxt_descripcion);
        etPrecio = findViewById(R.id.edTxt_precio);
        btnAgregar = findViewById(R.id.btn_agregar);
        btnMostrar = findViewById(R.id.btn_mostrar);
        btnBuscar = findViewById(R.id.btn_buscar);
        btnEditar = findViewById(R.id.btn_editar);
        btnEliminar = findViewById(R.id.btn_eliminar);


        final MyDBHandler myDBHandler = new MyDBHandler(getApplicationContext());

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Items item = new Items("",etTitulo.getText().toString(), etDescipcion.getText().toString(), etPrecio.getText().toString());
                    myDBHandler.addItem(item);
                    Toast.makeText(getApplicationContext(),"Se agregó el nuevo item", Toast.LENGTH_SHORT).show();
                    etTitulo.setText("");
                    etDescipcion.setText("");
                    etPrecio.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mostrarItems = new Intent(getApplicationContext(),StoreActivity.class);
                startActivity(mostrarItems);
            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Items item = new Items();
                myDBHandler.buscarItems(item,etTitulo.getText().toString());
                etDescipcion.setText(item.getDescripcion());
                etPrecio.setText(item.getPrecio());
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDBHandler.updateItem(etTitulo.getText().toString(), etDescipcion.getText().toString(), etPrecio.getText().toString());
                Toast.makeText(getApplicationContext(), "Se modificó correctamente", Toast.LENGTH_SHORT).show();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDBHandler.deleteItem(etTitulo.getText().toString());
                Toast.makeText(getApplicationContext(), "Se eliminó correctamente", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        myRootChild.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String texto = snapshot.getValue().toString();
                etTitulo.setText(texto);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}