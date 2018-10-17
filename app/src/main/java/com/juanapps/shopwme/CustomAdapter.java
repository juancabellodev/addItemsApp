package com.juanapps.shopwme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


public class CustomAdapter extends ArrayAdapter<Products> {

    private List<Products> products;
    StoreActivity storeActivity;
    Context context;
    TextView itemNameText, itemPriceText, itemAisleText, itemDateText;
    ImageView itemImageView;
    MenuItem photoitem;


    public CustomAdapter(StoreActivity context, ArrayList<Products> items) {
        super(context, R.layout.activity_item_row, items);

        this.context = context;
        this.storeActivity = context;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater groceryInflater = LayoutInflater.from(getContext());

        // Load the activity_item_row elements
        View customView =groceryInflater.inflate(R.layout.activity_item_row, parent, false);
        final Products specialItem = getItem(position);
        itemNameText = (TextView) customView.findViewById(R.id.itemNameText);
        itemPriceText = (TextView) customView.findViewById(R.id.itemPriceText);
        itemAisleText = (TextView) customView.findViewById(R.id.itemAisleText);
        itemDateText = (TextView) customView.findViewById(R.id.itemDateText);
        itemImageView = (ImageView) customView.findViewById(R.id.itemImage);
        RelativeLayout rowID = customView.findViewById(R.id.rowId);
        photoitem = (MenuItem) customView.findViewById(R.id.photoItem);


        // Get product details and display it
        itemNameText.setText(specialItem.get_productName());
        itemPriceText.setText(specialItem.get_productPrice());
        itemAisleText.setText(specialItem.get_productLocation());
        itemDateText.setText(specialItem.get_productDate());


        // On Click listener for the item menu
        final ImageView  menuImage= (ImageView)customView.findViewById(R.id.menuImage);
        menuImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu pm = new PopupMenu(context, menuImage);
                pm.getMenuInflater().inflate(R.menu.main_menu, pm.getMenu());
                pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.photoItem:
                                //call the takePhoto method
                                storeActivity.takePhoto();
                                return true;
                            case R.id.deleteItem:
                                //Remove item from list
                                storeActivity.removeItem();
                                return true;
                        }
                        return false;
                    }
                });
                pm.show();
            }
        });

        return customView;
        /*rowID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                storeActivity.alertDialog();


            }
        });*/
    }
}
