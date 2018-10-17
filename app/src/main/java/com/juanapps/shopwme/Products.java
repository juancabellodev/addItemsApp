package com.juanapps.shopwme;

public class Products {

    private int _id;
    private String _productName;
    private String _productPrice;
    private String _productStore;
    private String _productDate;
    private String _productLocation;
    private int _productImage;

    public Products(){

    }

    public Products(String productName, String productPrice, String productStore, String productDate, String productLocation){
        this._productName = productName;
        this._productPrice = productPrice;
        this._productStore = productStore;
        this._productDate = productDate;
        this._productLocation = productLocation;
    }

    public int get_id() {
        return _id;
    }

    public String get_productName() {
        return _productName;
    }

    public String get_productPrice() {
        return _productPrice;
    }

    public String get_productStore() {
        return _productStore;
    }

    public String get_productLocation() {
        return _productLocation;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_productName(String _productName) {
        this._productName = _productName;
    }

    public void set_productPrice(String _productPrice) {
        this._productPrice = _productPrice;
    }

    public void set_productStore(String _productStore) {
        this._productStore = _productStore;
    }

    public void set_productLocation(String _productLocation) {
        this._productLocation = _productLocation;
    }

    public String get_productDate() {
        return _productDate;
    }

    public void set_productDate(String _productDate) {
        this._productDate = _productDate;
    }

    public int get_productImage() {
        return _productImage;
    }

    public void set_productImage(int _productImage) {
        this._productImage = _productImage;
    }
}
