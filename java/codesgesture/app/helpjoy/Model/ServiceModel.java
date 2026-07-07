package codesgesture.app.helpjoy.Model;

import java.io.Serializable;

public class ServiceModel implements Serializable {
    private String id;
    private String category_temp_id;
    private String category_id;
    private String main_category_id;
    private String main_sub_category_id = null;
    private String category_title;
    private String category_name;
    private String category_photo;
    private String category_orderno;
    private String category_status;
    private String category_type;
    private String category_display;
    private String category_banner = null;
    private String super_point;


    // Getter Methods

    public String getId() {
        return id;
    }

    public String getCategory_temp_id() {
        return category_temp_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public String getMain_category_id() {
        return main_category_id;
    }

    public String getMain_sub_category_id() {
        return main_sub_category_id;
    }

    public String getCategory_title() {
        return category_title;
    }

    public String getCategory_name() {
        return category_name;
    }

    public String getCategory_photo() {
        return category_photo;
    }

    public String getCategory_orderno() {
        return category_orderno;
    }

    public String getCategory_status() {
        return category_status;
    }

    public String getCategory_type() {
        return category_type;
    }

    public String getCategory_display() {
        return category_display;
    }

    public String getCategory_banner() {
        return category_banner;
    }

    public String getSuper_point() {
        return super_point;
    }

    // Setter Methods

    public void setId(String id) {
        this.id = id;
    }

    public void setCategory_temp_id(String category_temp_id) {
        this.category_temp_id = category_temp_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public void setMain_category_id(String main_category_id) {
        this.main_category_id = main_category_id;
    }

    public void setMain_sub_category_id(String main_sub_category_id) {
        this.main_sub_category_id = main_sub_category_id;
    }

    public void setCategory_title(String category_title) {
        this.category_title = category_title;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public void setCategory_photo(String category_photo) {
        this.category_photo = category_photo;
    }

    public void setCategory_orderno(String category_orderno) {
        this.category_orderno = category_orderno;
    }

    public void setCategory_status(String category_status) {
        this.category_status = category_status;
    }

    public void setCategory_type(String category_type) {
        this.category_type = category_type;
    }

    public void setCategory_display(String category_display) {
        this.category_display = category_display;
    }

    public void setCategory_banner(String category_banner) {
        this.category_banner = category_banner;
    }

    public void setSuper_point(String super_point) {
        this.super_point = super_point;
    }
}