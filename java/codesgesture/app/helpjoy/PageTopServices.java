package codesgesture.app.helpjoy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import codesgesture.app.helpjoy.Adapter.ProductAdapter;
import codesgesture.app.helpjoy.Model.ProductModel;
import codesgesture.app.helpjoy.Services.CallJson;
import codesgesture.app.helpjoy.Services.JsonCallbacks;
import codesgesture.app.helpjoy.Services.NetParam;

public class PageTopServices extends AppCompatActivity {
    RecyclerView rvtopbook;
    ArrayList<ProductModel> productModels2=new ArrayList<>();
    ProductAdapter productAdapter2;
    String s,m;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topservices);
        s=getString(R.string.con);
        m=getString(R.string.maincon);
        ImageView btback=findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title=findViewById(R.id.title);
        title.setText("Top-Booked Services");
        rvtopbook=findViewById(R.id.rvtopbook);

        GridLayoutManager mLayoutManager3 = new GridLayoutManager(PageTopServices.this, 2);
        rvtopbook.setLayoutManager(mLayoutManager3);
        productAdapter2 = new ProductAdapter(PageTopServices.this, productModels2, R.layout.row_services);
        rvtopbook.setAdapter(productAdapter2);
        rvtopbook.setItemViewCacheSize(productModels2.size());
        GetDataTP();

    }

    private void GetDataTP() {
        productModels2.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(PageTopServices.this);
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

}
