package adndroid.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent receivedOrderIntent = getIntent();

        String userName = receivedOrderIntent.getStringExtra("userNameForIntent");
        TextView userNameOrderTextView = findViewById(R.id.userNameOrderTextView);
        userNameOrderTextView.setText(userName);

        String goodsName = receivedOrderIntent.getStringExtra("goodsNameForIntent");
        TextView goodsNameOrderTextView = findViewById(R.id.goodsNameOrderTextView);
        goodsNameOrderTextView.setText(goodsName);

        String quantity = receivedOrderIntent.getStringExtra("QuantityForIntent");
        TextView quantityOrderTextView = findViewById(R.id.quantityOrderTextView);
        quantityOrderTextView.setText(quantity);

        String orederprice = receivedOrderIntent.getStringExtra("orderPriceForIntent");
        TextView priceOrderTextView = findViewById(R.id.priceOrderTextView);
        priceOrderTextView.setText(orederprice);




    }

}
