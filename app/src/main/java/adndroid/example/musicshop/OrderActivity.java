package adndroid.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    String[] addresses = {"iparafin@gmail.com"};
    String subject = "Order From Music Shop";
    String emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent receivedOrderIntent = getIntent();

        String userName = receivedOrderIntent.getStringExtra("userNameForIntent");
        TextView userNameOrderTextView = findViewById(R.id.userNameOrderTextView);
        userNameOrderTextView.setText("Thank you for order "  + userName + "! Hope you will enjoy with your new instrument!");

        String goodsName = receivedOrderIntent.getStringExtra("goodsNameForIntent");
        TextView goodsNameOrderTextView = findViewById(R.id.goodsNameOrderTextView);
        goodsNameOrderTextView.setText(goodsName);

        String quantity = receivedOrderIntent.getStringExtra("QuantityForIntent");
        TextView quantityOrderTextView = findViewById(R.id.quantityOrderTextView);
        quantityOrderTextView.setText("Quantity: " +quantity);

        String orederprice = receivedOrderIntent.getStringExtra("orderPriceForIntent");
        TextView priceOrderTextView = findViewById(R.id.priceOrderTextView);
        priceOrderTextView.setText("Order Price: " +orederprice+ "$");

        emailText = "Customer Name: "+ userName+ "\n" + goodsName + "\n" + "Quantity: "+ quantity +"peace" + "\n" + "Order Price: " + orederprice + "$";

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

    public void submitOrder(View view) {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, addresses);
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, emailText);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }

    }
}
