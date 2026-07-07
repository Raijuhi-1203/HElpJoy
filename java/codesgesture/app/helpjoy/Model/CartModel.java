package codesgesture.app.helpjoy.Model;

import java.io.Serializable;

public class CartModel implements Serializable {
    private String id;
    private String cart_no;
    private String cart_date;
    private int cart_qty;

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    private int qty;
    private String customer_id;
    private String cart_guest_id = null;
    private String product_id;
    private String product_price_id;
    private String product_sellername = null;
    private String product_sellerid = null;
    private String store_location;
    private String id1;
    private String product_temp_id;
    private String product_id1;
    private String product_short_name = null;
    private String product_full_name;
    private String product_description;
    private String product_barcode;
    private String product_sku;
    private String product_hsnORsac;
    private String product_parent_category_id;
    private String product_parent_category_name;
    private String product_sub_category_id;
    private String product_sub_category_name;
    private String verticle_id;
    private String verticle_name;
    private String product_brand_name;
    private String product_COD_status = null;
    private String product_photo = null;
    private String product_full_description;
    private String product_seller_id;
    private String product_seller_name;
    private String product_dealofday = null;
    private String product_dealofdaydate = null;
    private String best_product_status = null;
    private String best_selling_status = null;
    private String offer_product_status = null;
    private String publish_status;
    private String feature_product_status = null;
    private String product_postion_no;
    private String country_of_origin;
    private String meta_title = null;
    private String meta_description = null;
    private String meta_keyword = null;
    private String unit_name = null;
    private String product_GST_type = null;
    private String product_tax_type = null;
    private String product_GST_percentage = null;
    private String product_market_price = null;
    private String no_of_viewed = null;
    private String attribute_title_1 = null;
    private String attribute_title_2 = null;
    private String attribute_title_3 = null;
    private String attribute_value_1 = null;
    private String attribute_value_2 = null;
    private String attribute_value_3 = null;
    private String variants_id = null;
    private String super_point;
    private String id2;
    private String product_id2;
    private String product_unit;
    private String product_unit_value;
    private String product_GST_type1;
    private String product_tax_type1;
    private String product_GST_percentage1;
    private String product_GST_rate;
    private String product_CGST_percentage;
    private String product_CGST_rate;
    private String product_SGST_percentage;
    private String product_SGST_rate;
    private String product_IGST_percentage = null;
    private String product_IGST_rate = null;
    private String product_market_price1;
    private String product_sell_price;
    private String product_discount_percentage;
    private String product_discount_price;
    private String product_with_gst_Price;
    private String product_final_sell_price;
    private String product_shipping_charge;
    private String product_stock;
    private String id3;
    private String product_id3;
    private String photo_path;
    private String display_order = null;
    private String id4 = null;
    private String product_id4 = null;
    private String reviwer_id = null;
    private String seller_id = null;
    private String reviwer_name = null;
    private String reviewer_message = null;
    private String review_star = null;
    private String review_date = null;
    private String review_status = null;


    // Getter Methods 

    public String getId() {
        return id;
    }

    public String getCart_no() {
        return cart_no;
    }

    public String getCart_date() {
        return cart_date;
    }

    public int getCart_qty() {
        return cart_qty;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public String getCart_guest_id() {
        return cart_guest_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getProduct_price_id() {
        return product_price_id;
    }

    public String getProduct_sellername() {
        return product_sellername;
    }

    public String getProduct_sellerid() {
        return product_sellerid;
    }

    public String getStore_location() {
        return store_location;
    }

    public String getId1() {
        return id1;
    }

    public String getProduct_temp_id() {
        return product_temp_id;
    }

    public String getProduct_id1() {
        return product_id1;
    }

    public String getProduct_short_name() {
        return product_short_name;
    }

    public String getProduct_full_name() {
        return product_full_name;
    }

    public String getProduct_description() {
        return product_description;
    }

    public String getProduct_barcode() {
        return product_barcode;
    }

    public String getProduct_sku() {
        return product_sku;
    }

    public String getProduct_hsnORsac() {
        return product_hsnORsac;
    }

    public String getProduct_parent_category_id() {
        return product_parent_category_id;
    }

    public String getProduct_parent_category_name() {
        return product_parent_category_name;
    }

    public String getProduct_sub_category_id() {
        return product_sub_category_id;
    }

    public String getProduct_sub_category_name() {
        return product_sub_category_name;
    }

    public String getVerticle_id() {
        return verticle_id;
    }

    public String getVerticle_name() {
        return verticle_name;
    }

    public String getProduct_brand_name() {
        return product_brand_name;
    }

    public String getProduct_COD_status() {
        return product_COD_status;
    }

    public String getProduct_photo() {
        return product_photo;
    }

    public String getProduct_full_description() {
        return product_full_description;
    }

    public String getProduct_seller_id() {
        return product_seller_id;
    }

    public String getProduct_seller_name() {
        return product_seller_name;
    }

    public String getProduct_dealofday() {
        return product_dealofday;
    }

    public String getProduct_dealofdaydate() {
        return product_dealofdaydate;
    }

    public String getBest_product_status() {
        return best_product_status;
    }

    public String getBest_selling_status() {
        return best_selling_status;
    }

    public String getOffer_product_status() {
        return offer_product_status;
    }

    public String getPublish_status() {
        return publish_status;
    }

    public String getFeature_product_status() {
        return feature_product_status;
    }

    public String getProduct_postion_no() {
        return product_postion_no;
    }

    public String getCountry_of_origin() {
        return country_of_origin;
    }

    public String getMeta_title() {
        return meta_title;
    }

    public String getMeta_description() {
        return meta_description;
    }

    public String getMeta_keyword() {
        return meta_keyword;
    }

    public String getUnit_name() {
        return unit_name;
    }

    public String getProduct_GST_type() {
        return product_GST_type;
    }

    public String getProduct_tax_type() {
        return product_tax_type;
    }

    public String getProduct_GST_percentage() {
        return product_GST_percentage;
    }

    public String getProduct_market_price() {
        return product_market_price;
    }

    public String getNo_of_viewed() {
        return no_of_viewed;
    }

    public String getAttribute_title_1() {
        return attribute_title_1;
    }

    public String getAttribute_title_2() {
        return attribute_title_2;
    }

    public String getAttribute_title_3() {
        return attribute_title_3;
    }

    public String getAttribute_value_1() {
        return attribute_value_1;
    }

    public String getAttribute_value_2() {
        return attribute_value_2;
    }

    public String getAttribute_value_3() {
        return attribute_value_3;
    }

    public String getVariants_id() {
        return variants_id;
    }

    public String getSuper_point() {
        return super_point;
    }

    public String getId2() {
        return id2;
    }

    public String getProduct_id2() {
        return product_id2;
    }

    public String getProduct_unit() {
        return product_unit;
    }

    public String getProduct_unit_value() {
        return product_unit_value;
    }

    public String getProduct_GST_type1() {
        return product_GST_type1;
    }

    public String getProduct_tax_type1() {
        return product_tax_type1;
    }

    public String getProduct_GST_percentage1() {
        return product_GST_percentage1;
    }

    public String getProduct_GST_rate() {
        return product_GST_rate;
    }

    public String getProduct_CGST_percentage() {
        return product_CGST_percentage;
    }

    public String getProduct_CGST_rate() {
        return product_CGST_rate;
    }

    public String getProduct_SGST_percentage() {
        return product_SGST_percentage;
    }

    public String getProduct_SGST_rate() {
        return product_SGST_rate;
    }

    public String getProduct_IGST_percentage() {
        return product_IGST_percentage;
    }

    public String getProduct_IGST_rate() {
        return product_IGST_rate;
    }

    public String getProduct_market_price1() {
        return product_market_price1;
    }

    public String getProduct_sell_price() {
        return product_sell_price;
    }

    public String getProduct_discount_percentage() {
        return product_discount_percentage;
    }

    public String getProduct_discount_price() {
        return product_discount_price;
    }

    public String getProduct_with_gst_Price() {
        return product_with_gst_Price;
    }

    public String getProduct_final_sell_price() {
        return product_final_sell_price;
    }

    public String getProduct_shipping_charge() {
        return product_shipping_charge;
    }

    public String getProduct_stock() {
        return product_stock;
    }

    public String getId3() {
        return id3;
    }

    public String getProduct_id3() {
        return product_id3;
    }

    public String getPhoto_path() {
        return photo_path;
    }

    public String getDisplay_order() {
        return display_order;
    }

    public String getId4() {
        return id4;
    }

    public String getProduct_id4() {
        return product_id4;
    }

    public String getReviwer_id() {
        return reviwer_id;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public String getReviwer_name() {
        return reviwer_name;
    }

    public String getReviewer_message() {
        return reviewer_message;
    }

    public String getReview_star() {
        return review_star;
    }

    public String getReview_date() {
        return review_date;
    }

    public String getReview_status() {
        return review_status;
    }

    // Setter Methods 

    public void setId(String id) {
        this.id = id;
    }

    public void setCart_no(String cart_no) {
        this.cart_no = cart_no;
    }

    public void setCart_date(String cart_date) {
        this.cart_date = cart_date;
    }

    public void setCart_qty(int cart_qty) {
        this.cart_qty = cart_qty;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public void setCart_guest_id(String cart_guest_id) {
        this.cart_guest_id = cart_guest_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setProduct_price_id(String product_price_id) {
        this.product_price_id = product_price_id;
    }

    public void setProduct_sellername(String product_sellername) {
        this.product_sellername = product_sellername;
    }

    public void setProduct_sellerid(String product_sellerid) {
        this.product_sellerid = product_sellerid;
    }

    public void setStore_location(String store_location) {
        this.store_location = store_location;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public void setProduct_temp_id(String product_temp_id) {
        this.product_temp_id = product_temp_id;
    }

    public void setProduct_id1(String product_id1) {
        this.product_id1 = product_id1;
    }

    public void setProduct_short_name(String product_short_name) {
        this.product_short_name = product_short_name;
    }

    public void setProduct_full_name(String product_full_name) {
        this.product_full_name = product_full_name;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public void setProduct_barcode(String product_barcode) {
        this.product_barcode = product_barcode;
    }

    public void setProduct_sku(String product_sku) {
        this.product_sku = product_sku;
    }

    public void setProduct_hsnORsac(String product_hsnORsac) {
        this.product_hsnORsac = product_hsnORsac;
    }

    public void setProduct_parent_category_id(String product_parent_category_id) {
        this.product_parent_category_id = product_parent_category_id;
    }

    public void setProduct_parent_category_name(String product_parent_category_name) {
        this.product_parent_category_name = product_parent_category_name;
    }

    public void setProduct_sub_category_id(String product_sub_category_id) {
        this.product_sub_category_id = product_sub_category_id;
    }

    public void setProduct_sub_category_name(String product_sub_category_name) {
        this.product_sub_category_name = product_sub_category_name;
    }

    public void setVerticle_id(String verticle_id) {
        this.verticle_id = verticle_id;
    }

    public void setVerticle_name(String verticle_name) {
        this.verticle_name = verticle_name;
    }

    public void setProduct_brand_name(String product_brand_name) {
        this.product_brand_name = product_brand_name;
    }

    public void setProduct_COD_status(String product_COD_status) {
        this.product_COD_status = product_COD_status;
    }

    public void setProduct_photo(String product_photo) {
        this.product_photo = product_photo;
    }

    public void setProduct_full_description(String product_full_description) {
        this.product_full_description = product_full_description;
    }

    public void setProduct_seller_id(String product_seller_id) {
        this.product_seller_id = product_seller_id;
    }

    public void setProduct_seller_name(String product_seller_name) {
        this.product_seller_name = product_seller_name;
    }

    public void setProduct_dealofday(String product_dealofday) {
        this.product_dealofday = product_dealofday;
    }

    public void setProduct_dealofdaydate(String product_dealofdaydate) {
        this.product_dealofdaydate = product_dealofdaydate;
    }

    public void setBest_product_status(String best_product_status) {
        this.best_product_status = best_product_status;
    }

    public void setBest_selling_status(String best_selling_status) {
        this.best_selling_status = best_selling_status;
    }

    public void setOffer_product_status(String offer_product_status) {
        this.offer_product_status = offer_product_status;
    }

    public void setPublish_status(String publish_status) {
        this.publish_status = publish_status;
    }

    public void setFeature_product_status(String feature_product_status) {
        this.feature_product_status = feature_product_status;
    }

    public void setProduct_postion_no(String product_postion_no) {
        this.product_postion_no = product_postion_no;
    }

    public void setCountry_of_origin(String country_of_origin) {
        this.country_of_origin = country_of_origin;
    }

    public void setMeta_title(String meta_title) {
        this.meta_title = meta_title;
    }

    public void setMeta_description(String meta_description) {
        this.meta_description = meta_description;
    }

    public void setMeta_keyword(String meta_keyword) {
        this.meta_keyword = meta_keyword;
    }

    public void setUnit_name(String unit_name) {
        this.unit_name = unit_name;
    }

    public void setProduct_GST_type(String product_GST_type) {
        this.product_GST_type = product_GST_type;
    }

    public void setProduct_tax_type(String product_tax_type) {
        this.product_tax_type = product_tax_type;
    }

    public void setProduct_GST_percentage(String product_GST_percentage) {
        this.product_GST_percentage = product_GST_percentage;
    }

    public void setProduct_market_price(String product_market_price) {
        this.product_market_price = product_market_price;
    }

    public void setNo_of_viewed(String no_of_viewed) {
        this.no_of_viewed = no_of_viewed;
    }

    public void setAttribute_title_1(String attribute_title_1) {
        this.attribute_title_1 = attribute_title_1;
    }

    public void setAttribute_title_2(String attribute_title_2) {
        this.attribute_title_2 = attribute_title_2;
    }

    public void setAttribute_title_3(String attribute_title_3) {
        this.attribute_title_3 = attribute_title_3;
    }

    public void setAttribute_value_1(String attribute_value_1) {
        this.attribute_value_1 = attribute_value_1;
    }

    public void setAttribute_value_2(String attribute_value_2) {
        this.attribute_value_2 = attribute_value_2;
    }

    public void setAttribute_value_3(String attribute_value_3) {
        this.attribute_value_3 = attribute_value_3;
    }

    public void setVariants_id(String variants_id) {
        this.variants_id = variants_id;
    }

    public void setSuper_point(String super_point) {
        this.super_point = super_point;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public void setProduct_id2(String product_id2) {
        this.product_id2 = product_id2;
    }

    public void setProduct_unit(String product_unit) {
        this.product_unit = product_unit;
    }

    public void setProduct_unit_value(String product_unit_value) {
        this.product_unit_value = product_unit_value;
    }

    public void setProduct_GST_type1(String product_GST_type1) {
        this.product_GST_type1 = product_GST_type1;
    }

    public void setProduct_tax_type1(String product_tax_type1) {
        this.product_tax_type1 = product_tax_type1;
    }

    public void setProduct_GST_percentage1(String product_GST_percentage1) {
        this.product_GST_percentage1 = product_GST_percentage1;
    }

    public void setProduct_GST_rate(String product_GST_rate) {
        this.product_GST_rate = product_GST_rate;
    }

    public void setProduct_CGST_percentage(String product_CGST_percentage) {
        this.product_CGST_percentage = product_CGST_percentage;
    }

    public void setProduct_CGST_rate(String product_CGST_rate) {
        this.product_CGST_rate = product_CGST_rate;
    }

    public void setProduct_SGST_percentage(String product_SGST_percentage) {
        this.product_SGST_percentage = product_SGST_percentage;
    }

    public void setProduct_SGST_rate(String product_SGST_rate) {
        this.product_SGST_rate = product_SGST_rate;
    }

    public void setProduct_IGST_percentage(String product_IGST_percentage) {
        this.product_IGST_percentage = product_IGST_percentage;
    }

    public void setProduct_IGST_rate(String product_IGST_rate) {
        this.product_IGST_rate = product_IGST_rate;
    }

    public void setProduct_market_price1(String product_market_price1) {
        this.product_market_price1 = product_market_price1;
    }

    public void setProduct_sell_price(String product_sell_price) {
        this.product_sell_price = product_sell_price;
    }

    public void setProduct_discount_percentage(String product_discount_percentage) {
        this.product_discount_percentage = product_discount_percentage;
    }

    public void setProduct_discount_price(String product_discount_price) {
        this.product_discount_price = product_discount_price;
    }

    public void setProduct_with_gst_Price(String product_with_gst_Price) {
        this.product_with_gst_Price = product_with_gst_Price;
    }

    public void setProduct_final_sell_price(String product_final_sell_price) {
        this.product_final_sell_price = product_final_sell_price;
    }

    public void setProduct_shipping_charge(String product_shipping_charge) {
        this.product_shipping_charge = product_shipping_charge;
    }

    public void setProduct_stock(String product_stock) {
        this.product_stock = product_stock;
    }

    public void setId3(String id3) {
        this.id3 = id3;
    }

    public void setProduct_id3(String product_id3) {
        this.product_id3 = product_id3;
    }

    public void setPhoto_path(String photo_path) {
        this.photo_path = photo_path;
    }

    public void setDisplay_order(String display_order) {
        this.display_order = display_order;
    }

    public void setId4(String id4) {
        this.id4 = id4;
    }

    public void setProduct_id4(String product_id4) {
        this.product_id4 = product_id4;
    }

    public void setReviwer_id(String reviwer_id) {
        this.reviwer_id = reviwer_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public void setReviwer_name(String reviwer_name) {
        this.reviwer_name = reviwer_name;
    }

    public void setReviewer_message(String reviewer_message) {
        this.reviewer_message = reviewer_message;
    }

    public void setReview_star(String review_star) {
        this.review_star = review_star;
    }

    public void setReview_date(String review_date) {
        this.review_date = review_date;
    }

    public void setReview_status(String review_status) {
        this.review_status = review_status;
    }
}