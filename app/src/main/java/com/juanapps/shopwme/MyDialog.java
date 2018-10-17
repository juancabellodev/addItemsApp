package com.juanapps.shopwme;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;


import java.util.ArrayList;


public class MyDialog extends Dialog {

    EditText itemName, itemPrice,itemLocation, itemDate;
    Button addButton, cancelButton;
    StoreActivity storeActivity;


    public MyDialog(Context context){
        super(context);
        storeActivity = (StoreActivity)context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_to_list_layout);
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        itemName = (EditText) findViewById(R.id.itemName);
        itemPrice = (EditText) findViewById(R.id.itemPrice);
        itemDate = (EditText) findViewById(R.id.itemDate);
        itemLocation = (EditText) findViewById(R.id.itemLocation);
        addButton = (Button) findViewById(R.id.addButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);

        // Set the addButton onClickListener
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeActivity.productList = new ArrayList<>();
                Products product = new Products();
                product.set_productName(itemName.getText().toString());
                product.set_productPrice(itemPrice.getText().toString());
                product.set_productStore(storeActivity.storeName);
                product.set_productDate(itemDate.getText().toString());
                product.set_productLocation(itemLocation.getText().toString());
                //add products to the database
                storeActivity.dbHandler.addProduct(product);
                storeActivity.productList.add(product);
                storeActivity.refreshData();
                storeActivity.storeAdapter.notifyDataSetChanged();
                dismiss();
            }

        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //storeActivity.dbHandler.deletetable();
                dismiss();
            }
        });
    }

}
