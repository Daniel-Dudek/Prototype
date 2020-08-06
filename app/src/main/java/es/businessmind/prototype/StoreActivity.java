package es.businessmind.prototype;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public class StoreActivity extends AppCompatActivity {

    RecyclerView recyclerViewItems;
    RecyclerViewAdapter adaptadorItems;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        recyclerViewItems = findViewById(R.id.recyclerListItems);
        recyclerViewItems.setLayoutManager(new LinearLayoutManager(this));

        MyDBHandler dbHandler = new MyDBHandler(getApplicationContext());

        adaptadorItems = new RecyclerViewAdapter(dbHandler.mostrarItems(), this);
        recyclerViewItems.setAdapter(adaptadorItems);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.appbar, menu);
        this.menu = menu;
        menu.findItem(R.id.logout).setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Get option selected
        switch (item.getItemId()) {
            //case R.id.logout:
            //logout();
//                return true;
            case R.id.carrito:
                //View para ver los items que tenemos seleccionados
                return true;
            case R.id.item1:
                return true;
            case R.id.item2:
                break;
            case R.id.item3:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}