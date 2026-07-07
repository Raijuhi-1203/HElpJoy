package codesgesture.app.helpjoy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import codesgesture.app.helpjoy.Adapter.CartAdapter;
import codesgesture.app.helpjoy.Adapter.ServiceAdapter;
import codesgesture.app.helpjoy.Model.CartModel;
import codesgesture.app.helpjoy.Model.CustomerModel;
import codesgesture.app.helpjoy.Model.ProductModel;
import codesgesture.app.helpjoy.Model.ServiceModel;
import codesgesture.app.helpjoy.Services.CallJson;
import codesgesture.app.helpjoy.Services.JsonCallbacks;
import codesgesture.app.helpjoy.Services.NetParam;
import codesgesture.app.helpjoy.Services.UserUtil;
import codesgesture.app.helpjoy.Utils.SessionManage;
import codesgesture.app.helpjoy.interfaces.Notify;

public class PageCart extends AppCompatActivity {
    CartAdapter cartAdapter;
    ArrayList<CartModel> cartModels=new ArrayList<>();
    TextView totalamt,checkout;
    RecyclerView rvservice;
    LinearLayout norecord;
    String s,m;
    CustomerModel customerModel;
    double total;
    String totalfinal,price_total;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        customerModel=(CustomerModel) SessionManage.getCurrentUser(getApplicationContext());
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
        title.setText("Cart");

        totalamt=findViewById(R.id.totalamt);checkout=findViewById(R.id.checkout);
        rvservice=findViewById(R.id.rvservice);norecord=findViewById(R.id.norecord);

        rvservice=findViewById(R.id.rvservice);
        GridLayoutManager mLayoutManager = new GridLayoutManager(PageCart.this, 1);
        rvservice.setLayoutManager(mLayoutManager);
        cartAdapter = new CartAdapter(PageCart.this, cartModels, R.layout.row_cart, new Notify() {
            @Override
            public void onAdd(CartModel data) {
                GetData();
            }

            @Override
            public void onRemove(CartModel data) {
                TotalAmount();
            }
        });
        rvservice.setAdapter(cartAdapter);
        rvservice.setItemViewCacheSize(cartModels.size());
        GetData();

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cHECKoUT();
            }
        });

    }

    private void GetData() {
        cartModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(PageCart.this);
        param.add(new NetParam("customer_id",customerModel.getCustomer_id()));
        jc.SendRequest(s,"get_cartproduct", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                JSONArray array = new JSONArray(json);
                for (int s = 0; s < array.length(); s++) {
                    JSONObject obj = array.getJSONObject(s);
                    CartModel product = new CartModel();
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
                    product.setId1(obj.getString("id2"));
                    product.setProduct_market_price(obj.getString("product_market_price1"));
                    product.setProduct_sell_price(obj.getString("product_sell_price"));
                    product.setProduct_discount_percentage(obj.getString("product_discount_percentage"));
                    product.setProduct_discount_price(obj.getString("product_discount_price"));
                    product.setProduct_with_gst_Price(obj.getString("product_with_gst_Price"));
                    cartModels.add(product);
                }
                TotalAmount();
                cartAdapter.notifyDataSetChanged();
            }

            @Override
            public void onPostError(String msg) {
            }
        }, "", "Loading..");
    }

    private void cHECKoUT() {
        if (cartModels.size()<=0){
            UserUtil.ShowMsg("Your cart is empty.",PageCart.this);
        }else {
            totalfinal=String.valueOf(total);
            Intent intent=new Intent(PageCart.this, PageAddress.class);
            intent.putExtra("total",totalfinal);
            intent.putExtra("market_price_total",price_total);
            intent.putExtra("cartModels", cartModels);
            startActivity(intent);
        }

    }
    private void checkcart() {
        if(cartModels.size()<=0)
            norecord.setVisibility(View.VISIBLE);
        else
            norecord.setVisibility(View.GONE);
    }
    private void TotalAmount() {
        Double totalamount = 0.0;
        Double mtotalamount = 0.0;
        for (CartModel cart : cartModels) {
            totalamount += (Double.parseDouble(cart.getProduct_final_sell_price()) * cart.getCart_qty());
            mtotalamount += (Double.parseDouble(cart.getProduct_market_price()) * cart.getCart_qty());
        }
        String price =String.valueOf(String.format("%.02f",totalamount));
        String mprice =String.valueOf(String.format("%.02f",mtotalamount));
        totalamt.setText("₹ "+price);
        total=totalamount;
        price_total=mprice;
        checkcart();
    }


}
