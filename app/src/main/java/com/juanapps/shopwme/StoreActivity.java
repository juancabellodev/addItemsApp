package com.juanapps.shopwme;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;


import java.util.ArrayList;



public class StoreActivity extends AppCompatActivity {

    TextView textStoreName;
    Button addButton, cancelButton;
    FloatingActionButton addStoreButton;
    MyDBHandler dbHandler;
    public ArrayList<Products> productList;
    public CustomAdapter storeAdapter;
    public String storeName;
    static final int REQUEST_IMAGE_CAPTURE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        Toolbar toolbar = (Toolbar)findViewById(R.id.my_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_navigate_before_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });


        // Get Intent information sent from ActivityMain class
        Bundle mainData = getIntent().getExtras();

        String title = mainData.getString("title");
        Integer textbg = mainData.getInt("bgTextColor");
        Integer buttonColor = mainData.getInt("backGroundColor");
        Integer textclr = mainData.getInt("textcolor");
        storeName = mainData.getString("storeName");

        //Initialize DBHandler
        dbHandler = new MyDBHandler(this, null, null, 1);
        //Call products in store
        productList = dbHandler.getProductsForStore(storeName);
        //Show the list
        storeAdapter = new CustomAdapter(this, productList);
        final ListView itemList = (ListView) findViewById(R.id.itemList);
        itemList.setAdapter(storeAdapter);




        // set each store page with customize attributes
        textStoreName = (TextView) findViewById(R.id.titlePage);
        addStoreButton = (FloatingActionButton) findViewById(R.id.fabButton);
        textStoreName.setText(title);
        textStoreName.setTextColor(getResources().getColor(textclr));
        textStoreName.setBackground(ContextCompat.getDrawable(this, textbg));
        addStoreButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(buttonColor)));

        // Get addButton ready to be called
        addStoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    // Method that calls MyDialog class
    public void openDialog() {
        MyDialog dialog = new MyDialog(this);
        dialog.show();
    }


    // Method to call the camera
    public void takePhoto(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if(takePictureIntent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK ) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            ImageView itemImage = (ImageView)findViewById(R.id.itemImage);
            itemImage.setImageBitmap(bitmap);
        }
    }
    // Remove item method
    public void removeItem(){
        TextView itemNameText = (TextView)findViewById(R.id.itemNameText);
        String inputText = itemNameText.getText().toString();
        dbHandler.deleteProduct(inputText);
        refreshData();


    }
    //Refresh the list when it is called
    public void refreshData(){
        dbHandler = new MyDBHandler(this, null, null, 1);
        productList = dbHandler.getProductsForStore(storeName);
        storeAdapter = new CustomAdapter(this, productList);
        final ListView itemList = (ListView) findViewById(R.id.itemList);
        itemList.setAdapter(storeAdapter);
        storeAdapter.notifyDataSetChanged();
    }

   /* public void alertDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.item_view_layout);
        final TextView itemNameView = (TextView) this.findViewById(R.id.itemNameView);
        final TextView itemPriceView = (TextView) this.findViewById(R.id.itemPriceView);
        final TextView itemOfferText = (TextView) this.findViewById(R.id.itemOfferText);
        final TextView itemLocationText = (TextView) this.findViewById(R.id.itemLocationText);

        dialog.show();
    }*/

}


