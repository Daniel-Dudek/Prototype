package es.businessmind.prototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsItems extends AppCompatActivity {

    TextView tituloItem, descripcionItem, precioItem;
    ImageView imgItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_items);

        Intent in = getIntent();
        String id = in.getStringExtra(RecyclerViewAdapter.ITEM_KEY);

        tituloItem = findViewById(R.id.txt_tituloItem);
        imgItem = findViewById(R.id.imgFoto);
        descripcionItem = findViewById(R.id.txt_descripcionItem);
        precioItem = findViewById(R.id.txt_precioItem);

    }
}