package codesgesture.app.helpjoy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import codesgesture.app.helpjoy.Adapter.ProductAdapter;
import codesgesture.app.helpjoy.Adapter.ServiceAdapter;
import codesgesture.app.helpjoy.Model.ProductModel;
import codesgesture.app.helpjoy.Model.ServiceModel;
import codesgesture.app.helpjoy.Model.SliderModel;
import codesgesture.app.helpjoy.Services.CallJson;
import codesgesture.app.helpjoy.Services.JsonCallbacks;
import codesgesture.app.helpjoy.Services.NetParam;

public class DashBoard extends AppCompatActivity {
    TextView btncatall,btnservcall;
    LinearLayout account,cart,service,booking,home;
    ImageView menu,lg;
    ArrayList<SliderModel> sliderModels=new ArrayList<>();
    SliderLayout slider;
    String s,m;
    RecyclerView rvservice,rvac,rvtopbook;
    ServiceAdapter serviceAdapter;
    ArrayList<ServiceModel> serviceModels=new ArrayList<>();
    ArrayList<ProductModel> productModels=new ArrayList<>();
    ProductAdapter productAdapter;
    ArrayList<ProductModel> productModels2=new ArrayList<>();
    ProductAdapter productAdapter2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        s=getString(R.string.con);
        m=getString(R.string.maincon);

        BindID();

        FetchSlider();

        GridLayoutManager mLayoutManager = new GridLayoutManager(DashBoard.this, 3);
        rvservice.setLayoutManager(mLayoutManager);
        serviceAdapter = new ServiceAdapter(DashBoard.this, serviceModels, R.layout.row_category);
        rvservice.setAdapter(serviceAdapter);
        rvservice.setItemViewCacheSize(serviceModels.size());
        GetData();

        GridLayoutManager mLayoutManager2 = new GridLayoutManager(DashBoard.this, 2);
        rvac.setLayoutManager(mLayoutManager2);
        productAdapter = new ProductAdapter(DashBoard.this, productModels, R.layout.row_services);
        rvac.setAdapter(productAdapter);
        rvac.setItemViewCacheSize(productModels.size());
        GetDataP();

        GridLayoutManager mLayoutManager3 = new GridLayoutManager(DashBoard.this, 2);
        rvtopbook.setLayoutManager(mLayoutManager3);
        productAdapter2 = new ProductAdapter(DashBoard.this, productModels2, R.layout.row_services);
        rvtopbook.setAdapter(productAdapter2);
        rvtopbook.setItemViewCacheSize(productModels2.size());
        GetDataTP();

    }

    private void GetDataTP() {
        productModels2.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(DashBoard.this);
        jc.SendRequest(s,"gettopbooked_service", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                JSONArray array = new JSONArray(json);
                for (int s = 0; s < array.length(); s++) {
                    JSONObject obj = array.getJSONObject(s);
                    ProductModel product = new ProductModel();
                    product.setProduct_full_name(obj.getString("product_full_name"));
                    product.setProduct_full_description(obj.getString("product_description"));
                    product.setId(obj.getString("id"));
                    product.setProduct_id(obj.getString("product_id"));
                    product.setProduct_discount_percentage(obj.getString("product_discount_percentage"));
                    product.setProduct_discount_price(obj.getString("product_discount_price"));
                    product.setProduct_final_sell_price(obj.getString("product_final_sell_price"));
                    product.setPhoto_path(obj.getString("photo_path"));
                    product.setReview_star(obj.getString("review_star"));
                    product.setProduct_parent_category_name(obj.getString("product_parent_category_name"));
                    product.setProduct_sub_category_name(obj.getString("product_sub_category_name"));
                    product.setCart_qty(Integer.parseInt(obj.getString("cart_qty")));
                    product.setQty(Integer.parseInt(obj.getString("cart_qty")));
                    product.setId1(obj.getString("id1"));
                    product.setProduct_market_price(obj.getString("product_market_price1"));
                    product.setProduct_sell_price(obj.getString("product_sell_price"));
                    product.setProduct_discount_percentage(obj.getString("product_discount_percentage"));
                    product.setProduct_discount_price(obj.getString("product_discount_price"));
                    product.setProduct_with_gst_Price(obj.getString("product_with_gst_Price"));
                    productModels2.add(product);
                }
                productAdapter2.notifyDataSetChanged();
            }

            @Override
            public void onPostError(String msg) {
            }
        }, "", "Loading..");
    }

    private void GetDataP() {
        productModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(DashBoard.this);
        jc.SendRequest(s,"getac_service", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                JSONArray array = new JSONArray(json);
                for (int s = 0; s < array.length(); s++) {
                    JSONObject obj = array.getJSONObject(s);
                    ProductModel product = new ProductModel();
                    product.setProduct_full_name(obj.getString("product_full_name"));
                    product.setProduct_full_description(obj.getString("product_description"));
                    product.setId(obj.getString("id"));
                    product.setProduct_id(obj.getString("product_id"));
                    product.setProduct_discount_percentage(obj.getString("product_discount_percentage"));
                    product.setProduct_discount_price(obj.getString("product_discount_price"));
                    product.setProduct_final_sell_price(obj.getString("product_final_sell_price"));
                    product.setPhoto_path(obj.getString("photo_path"));
                    product.setReview_star(obj.getString("review_star"));
                    product.setProduct_parent_category_name(obj.getString("product_parent_category_name"));
                    product.setProduct_sub_category_name(obj.getString("product_sub_category_name"));
                    product.setCart_qty(Integer.parseInt(obj.getString("cart_qty")));
                    product.setQty(Integer.parseInt(obj.getString("cart_qty")));
                    product.setId1(obj.getString("id1"));
                    product.setProduct_market_price(obj.getString("product_market_price1"));
                    product.setProduct_sell_price(obj.getString("product_sell_price"));
                    product.setProduct_discount_percentage(obj.getString("product_discount_percentage"));
                    product.setProduct_discount_price(obj.getString("product_discount_price"));
                    product.setProduct_with_gst_Price(obj.getString("product_with_gst_Price"));
                    productModels.add(product);
                }
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onPostError(String msg) {
            }
        }, "", "Loading..");
    }

    private void GetData() {
        serviceModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(DashBoard.this);
        jc.SendRequest(s,"getcategory_dash", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                JSONArray array = new JSONArray(json);
                for (int s = 0; s < array.length(); s++) {
                    JSONObject obj = array.getJSONObject(s);
                    ServiceModel product = new ServiceModel();
                    product.setCategory_name(obj.getString("category_name"));
                    product.setCategory_photo(obj.getString("category_photo"));
                    product.setId(obj.getString("id"));
                    product.setMain_category_id(obj.getString("main_category_id"));
                    product.setCategory_id(obj.getString("category_id"));
                    serviceModels.add(product);
                }
                serviceAdapter.notifyDataSetChanged();
            }

            @Override
            public void onPostError(String msg) {
            }
        }, "", "Loading..");
    }

    private void BindID() {
        btncatall=findViewById(R.id.btncatall);
        btnservcall=findViewById(R.id.btnservcall);
       // s1=findViewById(R.id.s1);
        home=findViewById(R.id.home);
        booking=findViewById(R.id.booking);
        service=findViewById(R.id.service);
        cart=findViewById(R.id.cart);
        account=findViewById(R.id.account);
        menu=findViewById(R.id.menu);
        lg=findViewById(R.id.lg);
        slider=findViewById(R.id.slider);
        rvservice=findViewById(R.id.rvservice);
        rvac=findViewById(R.id.rvac);
        rvtopbook=findViewById(R.id.rvtopbook);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashBoard.this,PageMenu.class));
            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashBoard.this,PageProfile.class));
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashBoard.this,DashBoard.class));
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashBoard.this,PageCart.class));
            }
        });

        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashBoard.this,PageOrder.class));
            }
        });
        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashBoard.this,PageAllService.class));
            }
        });

        btnservcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashBoard.this,PageTopServices.class));
            }
        });
        btncatall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashBoard.this,PageAllService.class));
            }
        });
    }

    private void FetchSlider() {
        sliderModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(DashBoard.this);
        jc.SendRequest(s,"getslider", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                if (json.length() > 0) {
                    JSONArray array = new JSONArray(json);
                    for (int s = 0; s < array.length(); s++) {
                        JSONObject obj = array.getJSONObject(s);
                        SliderModel model = new SliderModel();
                        model.setSlider_photo(obj.getString("slider_photo"));
                        model.setSlider_status(obj.getString("slider_status"));
                        sliderModels.add(model);
                        DefaultSliderView defaultSliderView = new DefaultSliderView(DashBoard.this);
                        defaultSliderView.image(m+model.getSlider_photo());

                        slider.addSlider(defaultSliderView);
                        slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                        slider.setCustomAnimation(new DescriptionAnimation());
                        slider.setDuration(5000);

                    }
                }
            }

            @Override
            public void onPostError(String msg) {
            }
        }, "", "Wait Loading...");
    }

}
