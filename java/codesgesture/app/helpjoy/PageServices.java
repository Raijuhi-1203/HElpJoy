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
import codesgesture.app.helpjoy.Adapter.ServiceAdapter;
import codesgesture.app.helpjoy.Model.ProductModel;
import codesgesture.app.helpjoy.Model.ServiceModel;
import codesgesture.app.helpjoy.Services.CallJson;
import codesgesture.app.helpjoy.Services.JsonCallbacks;
import codesgesture.app.helpjoy.Services.NetParam;

public class PageServices extends AppCompatActivity {
    ServiceModel serviceModel;
    String s,m;
    RecyclerView rvtopbook;
    ArrayList<ProductModel> productModels=new ArrayList<>();
    ProductAdapter productAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        serviceModel=(ServiceModel)getIntent().getSerializableExtra("data");
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
        title.setText(serviceModel.getCategory_name());
        rvtopbook=findViewById(R.id.rvtopbook);

        GridLayoutManager mLayoutManager2 = new GridLayoutManager(PageServices.this, 2);
        rvtopbook.setLayoutManager(mLayoutManager2);
        productAdapter = new ProductAdapter(PageServices.this, productModels, R.layout.row_services);
        rvtopbook.setAdapter(productAdapter);
        rvtopbook.setItemViewCacheSize(productModels.size());
        GetDataP();

    }

    private void GetDataP() {
        productModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(PageServices.this);
        param.add(new NetParam("product_sub_category_id",serviceModel.getCategory_id()));
        jc.SendRequest(s,"getsubcat_service", param, new JsonCallbacks() {
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

}
