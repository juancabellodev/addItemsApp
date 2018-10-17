package com.juanapps.shopwme;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;



public class MainActivity extends AppCompatActivity  {

    public CardView newWorld, countDown, pakNSave, fourSquare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    // Switch cases to customize each store page
    public void onClick(View view){

        GroceryStore store = new GroceryStore();
        switch (view.getId()){
            case R.id.cardViewNW:
                store.setGroceryStoreName("New World Specials");
                store.setTextViewBackgroundColor(R.drawable.gradient_red);
                store.setAddBackgroundColor(R.color.startRedGradient);
                store.setTextColor(R.color.white);
                store.setStoreName("New World");


                break;
            case R.id.cardViewCD:
                store.setGroceryStoreName("Countdown Specials");
                store.setTextViewBackgroundColor(R.drawable.gradient_green);
                store.setAddBackgroundColor(R.color.startGreenGradient);
                store.setTextColor(R.color.white);
                store.setStoreName("Countdown");
                break;
            case R.id.cardViewPS:
                store.setGroceryStoreName("Pak N Save Specials");
                store.setTextViewBackgroundColor(R.drawable.gradient_yellow);
                store.setAddBackgroundColor(R.color.startYellowGradient);
                store.setTextColor(R.color.black);
                store.setStoreName("Pak N Save");
                break;
            case R.id.cardViewFS:
                store.setGroceryStoreName("Four Square Specials");
                store.setTextViewBackgroundColor(R.drawable.gradient_orange);
                store.setAddBackgroundColor(R.color.startOrangeGradient);
                store.setTextColor(R.color.white);
                store.setStoreName("Four Square");
                break;


        }
        // Send information to the StoreActivity class
        Intent i = new Intent (this, StoreActivity.class);
        i.putExtra("title", store.groceryStoreName);
        i.putExtra("bgTextColor", store.textViewBackgroundColor);
        i.putExtra("backGroundColor", store.addBackgroundColor);
        i.putExtra("textcolor", store.addTextColor);
        i.putExtra("storeName", store.getStoreName());
        startActivity(i);

    }






}
