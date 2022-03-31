package com.unipi.p18240.bookshop;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.unipi.p18240.bookshop.models.BestSellerModel;
import com.unipi.p18240.bookshop.models.PopularModel;
import com.unipi.p18240.bookshop.models.RecommendedModel;
import com.unipi.p18240.bookshop.models.ViewAllModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

public class DetailedActivity extends AppCompatActivity {

    // Initialize variables
    ImageView detailedImg, addItem, removeItem, speaking;
    TextView rating, price, quantity, description, shoppingQuantity;
    Button addToCart;
    Toolbar toolbar;

    String title_cart, author_cart;

    PopularModel popularModel = null;
    BestSellerModel bestSellerModel = null;
    RecommendedModel recommendedModel = null;
    ViewAllModel viewAllModel = null;

    FirebaseFirestore db;
    FirebaseAuth auth;

    TextToSpeech textToSpeech;

    int total_book_quantity, price_cart;

    @SuppressLint({"WrongViewCast", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        // Define the view field based on id from the xml file
        toolbar = findViewById(R.id.toolbar);
        // Get fire base instance
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // Popular books
        final Object object = getIntent().getSerializableExtra("detail");
        if (object instanceof PopularModel) {
            popularModel = (PopularModel) object;
        }

            // Define the view fields based on id from the xml file
            detailedImg = findViewById(R.id.detailed_img);
            addItem = findViewById(R.id.add_item);
            removeItem = findViewById(R.id.remove_item);
            price = findViewById(R.id.detailed_price);
            quantity = findViewById(R.id.detailed_quantity);
            rating = findViewById(R.id.detailed_rating_text);
            description = findViewById(R.id.detailed_description);
            addToCart = findViewById(R.id.add_to_cart);
            shoppingQuantity = findViewById(R.id.shop_quantity);

            if (popularModel != null) {
                // Get view holders and fill them with data to display.
                Glide.with(getApplicationContext()).load(popularModel.getImg_url()).into(detailedImg);
                rating.setText(popularModel.getRating());
                description.setText(popularModel.getDescription());
                price.setText(getResources().getString(R.string.rec_price, popularModel.getPrice()));
                quantity.setText("Quantity: " + popularModel.getQuantity());
                total_book_quantity = popularModel.getQuantity();
                title_cart = popularModel.getTitle();
                author_cart = popularModel.getAuthor();
                price_cart = popularModel.getPrice();
            }

            // Best sellers
            final Object obj = getIntent().getSerializableExtra("detail");
            if (obj instanceof BestSellerModel) {
                bestSellerModel = (BestSellerModel) obj;
            }
            // Define the view fields based on id from the xml file
            detailedImg = findViewById(R.id.detailed_img);
            addItem = findViewById(R.id.add_item);
            removeItem = findViewById(R.id.remove_item);
            price = findViewById(R.id.detailed_price);
            quantity = findViewById(R.id.detailed_quantity);
            rating = findViewById(R.id.detailed_rating_text);
            description = findViewById(R.id.detailed_description);
            addToCart = findViewById(R.id.add_to_cart);
            shoppingQuantity = findViewById(R.id.shop_quantity);

            if (bestSellerModel != null) {
                // Get view holders and fill them with data to display.
                Glide.with(getApplicationContext()).load(bestSellerModel.getImg_url()).into(detailedImg);
                rating.setText(bestSellerModel.getRating());
                description.setText(bestSellerModel.getDescription());
                price.setText(getResources().getString(R.string.rec_price, bestSellerModel.getPrice()));
                quantity.setText("Quantity: " + bestSellerModel.getQuantity());
                total_book_quantity = bestSellerModel.getQuantity();
                title_cart = bestSellerModel.getTitle();
                author_cart = bestSellerModel.getAuthor();
                price_cart = bestSellerModel.getPrice();
            }

            // Recommended books
            final Object obj_1 = getIntent().getSerializableExtra("detail");
            if (obj_1 instanceof RecommendedModel) {
                recommendedModel = (RecommendedModel) obj_1;
            }
            // Define the view fields based on id from the xml file
            detailedImg = findViewById(R.id.detailed_img);
            addItem = findViewById(R.id.add_item);
            removeItem = findViewById(R.id.remove_item);
            price = findViewById(R.id.detailed_price);
            quantity = findViewById(R.id.detailed_quantity);
            rating = findViewById(R.id.detailed_rating_text);
            description = findViewById(R.id.detailed_description);
            addToCart = findViewById(R.id.add_to_cart);
            shoppingQuantity = findViewById(R.id.shop_quantity);

            if (recommendedModel != null) {
                // Get view holders and fill them with data to display.
                Glide.with(getApplicationContext()).load(recommendedModel.getImg_url()).into(detailedImg);
                rating.setText(recommendedModel.getRating());
                description.setText(recommendedModel.getDescription());
                price.setText(getResources().getString(R.string.rec_price, recommendedModel.getPrice()));
                quantity.setText("Quantity: " + recommendedModel.getQuantity());
                total_book_quantity = recommendedModel.getQuantity();
                title_cart = recommendedModel.getTitle();
                author_cart = recommendedModel.getAuthor();
                price_cart = recommendedModel.getPrice();
            }

            // Books from categories
            final Object obj_2 = getIntent().getSerializableExtra("detail");
            if (obj_2 instanceof ViewAllModel) {
                viewAllModel = (ViewAllModel) obj_2;
            }
            // Define the view fields based on id from the xml file
            detailedImg = findViewById(R.id.detailed_img);
            addItem = findViewById(R.id.add_item);
            removeItem = findViewById(R.id.remove_item);
            price = findViewById(R.id.detailed_price);
            quantity = findViewById(R.id.detailed_quantity);
            rating = findViewById(R.id.detailed_rating_text);
            description = findViewById(R.id.detailed_description);
            addToCart = findViewById(R.id.add_to_cart);
            shoppingQuantity = findViewById(R.id.shop_quantity);

            if (viewAllModel != null) {
                // Get view holders and fill them with data to display.
                Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(detailedImg);
                rating.setText(viewAllModel.getRating());
                description.setText(viewAllModel.getDescription());
                price.setText(getResources().getString(R.string.rec_price, viewAllModel.getPrice()));
                quantity.setText("Quantity: " + viewAllModel.getQuantity());
                total_book_quantity = viewAllModel.getQuantity();
                title_cart = viewAllModel.getTitle();
                author_cart = viewAllModel.getAuthor();
                price_cart = viewAllModel.getPrice();
            }

        // text to speech
        // Define the view field based on id from the xml file
        speaking = findViewById(R.id.speaking);

        textToSpeech = new TextToSpeech(getApplicationContext(), i -> {

            // if No error is found then only it will run
            if (i != TextToSpeech.ERROR) {
                // To Choose language of speech
                textToSpeech.setLanguage(Locale.UK);
            }
        });

        // Create on click listener when the user press the button
        speaking.setOnClickListener(view -> textToSpeech.speak(description.getText().toString(), TextToSpeech.QUEUE_FLUSH, null));

        // Define the view field based on id from the xml file
        addToCart = findViewById(R.id.add_to_cart);

        //Create on click listener when the user click the button to add an item to cart
            addToCart.setOnClickListener(v -> addToCart());

            //Create on click listener when the user click the button to increase the amount of items
            addItem.setOnClickListener(v -> {
                int current = Integer.parseInt(shoppingQuantity.getText().toString());
                if (current < total_book_quantity) {
                    shoppingQuantity.setText(Integer.toString(current + 1));
                }
            });
            //Create on click listener when the user click the button to reduce the amount of items
            removeItem.setOnClickListener(v -> {
                int current = Integer.parseInt(shoppingQuantity.getText().toString());
                if (current - 1 >= 0) {
                    shoppingQuantity.setText(Integer.toString(current - 1));
                }
            });
        }

        // Method to add to cart the book and the quantity
        private void addToCart ()
        {
            String saveCurrentDate, saveCurrentTime;
            Calendar calendar = Calendar.getInstance();

            // Pass the exact date
            @SuppressLint("SimpleDateFormat") SimpleDateFormat currentDate = new SimpleDateFormat("dd.MM.yyyy");
            saveCurrentDate = currentDate.format(calendar.getTime());

            // Pass the exact time
            @SuppressLint("SimpleDateFormat") SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
            saveCurrentTime = currentTime.format(calendar.getTime());

            // Create hash map to pass the data
            final HashMap<String, Object> cartMap = new HashMap<>();

            // Fill the hash map
            cartMap.put("title", title_cart);
            cartMap.put("author", author_cart);
            cartMap.put("price", price_cart);
            cartMap.put("date", saveCurrentDate);
            cartMap.put("time", saveCurrentTime);
            cartMap.put("quantity", Integer.parseInt(shoppingQuantity.getText().toString()));
            cartMap.put("total_price", Integer.parseInt(shoppingQuantity.getText().toString()) * price_cart);

            // Check if the quantity of the added item is up to 1
            if (Integer.parseInt(shoppingQuantity.getText().toString()) != 0) {
                db.collection("Cart").document(Objects.requireNonNull(auth.getCurrentUser()).getUid())
                        .collection("Current_user").add(cartMap).addOnCompleteListener(task -> {
                            Toast.makeText(DetailedActivity.this, "Item has been added to cart", Toast.LENGTH_SHORT).show();
                            finish();
                        });
            } else {
                Toast.makeText(DetailedActivity.this, "Item quantity is zero", Toast.LENGTH_SHORT).show();
            }
        }

    @Override
    // Method to stop speech when click the back button
    protected void onPause() {

        if(textToSpeech != null)
        {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onPause();
    }
}



