package codesgesture.app.helpjoy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import org.json.JSONException;

import java.util.ArrayList;

import codesgesture.app.helpjoy.Model.CustomerModel;
import codesgesture.app.helpjoy.Model.ProductModel;
import codesgesture.app.helpjoy.Services.CallJson;
import codesgesture.app.helpjoy.Services.JsonCallbacks;
import codesgesture.app.helpjoy.Services.NetParam;
import codesgesture.app.helpjoy.Services.UserUtil;
import codesgesture.app.helpjoy.Utils.SessionManage;

public class ServiceDetails extends AppCompatActivity {
    ProductModel productModel;
    CustomerModel customerModel;
    TextView catname,name,mrp,cartqty,descp;
    RatingBar ratingBar;
    ImageView minus,add,fb,wtsapp,twtr,img;
    Button btnbook;
    String s,m,totalorderamt;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdetails);
        customerModel=(CustomerModel) SessionManage.getCurrentUser(getApplicationContext());
        productModel=(ProductModel)getIntent().getSerializableExtra("data");
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
        title.setText(productModel.getProduct_full_name());

        BindID();

        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Addcart();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cq =1;
                cq++;
                productModel.setQty(cq);
                productModel.setCart_qty(cq);
                String price = productModel.getProduct_final_sell_price() ;
                double totalamt = cq * Double.parseDouble(price) ;
                cartqty.setText(String.valueOf(productModel.getCart_qty()));
                totalorderamt=String.valueOf(totalamt);
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cq =1;
                if (cq>1) {
                    cq--;
                    productModel.setQty(cq);
                    productModel.setCart_qty(cq);
                }
                String price = productModel.getProduct_final_sell_price() ;
                double totalamt = cq * Double.parseDouble(price) ;
                cartqty.setText(String.valueOf(productModel.getCart_qty()));
                totalorderamt=String.valueOf(totalamt);
            }
        });



    }

    private void Addcart() {
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(ServiceDetails.this);
        param.add(new NetParam("cart_qty",cartqty.getText().toString()));
        param.add(new NetParam("customer_id",customerModel.getCustomer_id()));
        param.add(new NetParam("product_id",productModel.getProduct_id()));
        param.add(new NetParam("product_price_id",productModel.getId1()));
        param.add(new NetParam("total_order_amount",totalorderamt));
        param.add(new NetParam("product_name",productModel.getProduct_full_name()));
        param.add(new NetParam("product_market_price",productModel.getProduct_market_price()));
        param.add(new NetParam("product_sell_price",productModel.getProduct_sell_price()));
        param.add(new NetParam("product_discount_percentage",productModel.getProduct_discount_percentage()));
        param.add(new NetParam("product_discount_price",productModel.getProduct_discount_price()));
        param.add(new NetParam("product_with_gst_Price",productModel.getProduct_with_gst_Price()));
        param.add(new NetParam("product_final_sell_price",productModel.getProduct_final_sell_price()));
        param.add(new NetParam("total_market_price",productModel.getProduct_market_price()));
        jc.SendRequest(s,"addtocart", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                UserUtil.ShowMsg("Service Add in Cart !",ServiceDetails.this);
            }

            @Override
            public void onPostError(String msg) {

            }
        }, "", "Loading..");
    }

    private void BindID() {
        catname=findViewById(R.id.catname); name=findViewById(R.id.name);
        mrp=findViewById(R.id.mrp); cartqty=findViewById(R.id.cartqty);
        descp=findViewById(R.id.descp);

        minus=findViewById(R.id.minus); add=findViewById(R.id.add);
        fb=findViewById(R.id.fb); wtsapp=findViewById(R.id.wtsapp);
        twtr=findViewById(R.id.twtr); img=findViewById(R.id.img);

        ratingBar=findViewById(R.id.ratingBar);
        btnbook=findViewById(R.id.btnbook);

        name.setText(productModel.getProduct_full_name());
        mrp.setText("₹ "+productModel.getProduct_final_sell_price());
        descp.setText(Html.fromHtml(productModel.getProduct_full_description()));
        catname.setText(productModel.getProduct_parent_category_name()+"/"+productModel.getProduct_sub_category_name());
        String s = productModel.getReview_star();
        if (s.equals("null") || s.equals(null)){
            ratingBar.setRating(0);
        }else {
            float d = Float.parseFloat(s);
            ratingBar.setRating(d);
        }

        Glide.with(ServiceDetails.this).load(m+productModel.getPhoto_path()).into(img);


    }


}
