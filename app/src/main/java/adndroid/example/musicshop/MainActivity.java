package adndroid.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.*;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int quantity = 1;
    Spinner spinner;
    ArrayList spinnerArrayList;
    ArrayAdapter spinnerAdapter;
    HashMap goodsMap;
    String goodsName;
    double price;
    EditText userNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameEditText = findViewById(R.id.enterYourName);

        createSpinner();
        createMap();


    }

    void createMap () {

        goodsMap = new HashMap();
        goodsMap.put("HSS Electric Guitar - Indian Laurel",100.0);
        goodsMap.put("Ibanez RG421 MOL Electric Guitars",300.0);
        goodsMap.put("Cort X100 6-String Electric Guitar",400.0);
    }
    void createSpinner (){
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList();

        spinnerArrayList.add(0,"HSS Electric Guitar - Indian Laurel");
        spinnerArrayList.add(1,"Ibanez RG421 MOL Electric Guitars");
        spinnerArrayList.add(2,"Cort X100 6-String Electric Guitar");

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(spinnerAdapter);
    }

    public void increaseQuantity(View view) {
        quantity = quantity + 1;
        TextView quantityValueTextView = findViewById(R.id.quantityValueTextView);
        quantityValueTextView.setText("" +quantity);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText(" " + quantity * price);
    }
    public void decreaseQuantity(View view) {
        TextView quantityValueTextView = findViewById(R.id.quantityValueTextView);
        quantity = quantity - 1;
        if (quantity < 1) {
            quantity = 1;
        }
        quantityValueTextView.setText("" +quantity);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText(" " + quantity * price);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    goodsName = spinner.getSelectedItem().toString();
    price = (double)goodsMap.get(goodsName);
    TextView priceTextView = findViewById(R.id.priceTextView);
    priceTextView.setText(" " + quantity * price);
        ImageView goodItemImageView = findViewById(R.id.goodItemImageView);
        switch (goodsName) {
        case "HSS Electric Guitar - Indian Laurel":
            goodItemImageView.setImageResource(R.drawable.guitars);
                    break;
            case "Ibanez RG421 MOL Electric Guitars":
                goodItemImageView.setImageResource(R.drawable.guitar_2);
                break;
            case "Cort X100 6-String Electric Guitar":
                goodItemImageView.setImageResource(R.drawable.guitar_3);
                break;
                default:
                    goodItemImageView.setImageResource(R.drawable.guitars);
                    break;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addToCart (View view){
        Order order = new Order();
        order.userName = userNameEditText.getText().toString();
        order.goodsName = goodsName;
        order.quantity = quantity;
        order.orderprice = quantity * price;


     //  Log.d("Quantity", Integer.toString(order.quantity));

        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("userNameForIntent", order.userName);
        orderIntent.putExtra("goodsNameForIntent", order.goodsName);
        orderIntent.putExtra("QuantityForIntent", Integer.toString(order.quantity));
        orderIntent.putExtra("orderPriceForIntent", Double.toString(order.orderprice));
        startActivity(orderIntent);

    }

}


