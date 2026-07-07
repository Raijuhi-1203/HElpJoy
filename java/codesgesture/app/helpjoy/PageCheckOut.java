package codesgesture.app.helpjoy;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import codesgesture.app.helpjoy.Model.AddressModel;
import codesgesture.app.helpjoy.Model.CartModel;
import codesgesture.app.helpjoy.Model.CustomerModel;
import codesgesture.app.helpjoy.Model.ProductModel;
import codesgesture.app.helpjoy.Services.CallJson;
import codesgesture.app.helpjoy.Services.CallJsonWithoutProgress;
import codesgesture.app.helpjoy.Services.JsonCallbacks;
import codesgesture.app.helpjoy.Services.NetParam;
import codesgesture.app.helpjoy.Services.UserUtil;
import codesgesture.app.helpjoy.Utils.SessionManage;

public class PageCheckOut extends AppCompatActivity {
    String carttotal,market_price_total;
    CustomerModel customerModel;
    TextView txmrp,txsmrp,txdis,txcdis,txpamt,btorder;
    EditText couponcode;
    Button btapply;
    RadioGroup radioGroup;
    RadioButton radioButton;
    ArrayList<CartModel> productModels;
    ArrayList<AddressModel> addressModels;
    String s;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_details);
        customerModel=(CustomerModel) SessionManage.getCurrentUser(getApplicationContext());
        carttotal=getIntent().getStringExtra("total");
        market_price_total=getIntent().getStringExtra("market_price_total");
        productModels=(ArrayList<CartModel>)getIntent().getSerializableExtra("cartModels");
        addressModels=(ArrayList<AddressModel>)getIntent().getSerializableExtra("address");

        s=getString(R.string.con);
        radioGroup=findViewById(R.id.radioGroup);
        btapply=findViewById(R.id.btapply);
        couponcode=findViewById(R.id.couponcode);
        txmrp=findViewById(R.id.txmrp);
        txsmrp=findViewById(R.id.txsmrp);
        txdis=findViewById(R.id.txdis);
        txcdis=findViewById(R.id.txcdis);
        txpamt=findViewById(R.id.txpamt);
        btorder=findViewById(R.id.btorder);

        txmrp.setText(market_price_total);
        Double finalp,mprice,dis;
        finalp=Double.parseDouble(carttotal);
        mprice=Double.parseDouble(market_price_total);
        dis=mprice-finalp;

        txdis.setText(String.format("%.02f",dis));
        txpamt.setText(String.format("%.02f",finalp));
        txsmrp.setText("0.00");
        txcdis.setText("0.00");

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioButton=findViewById(i);
                radioButton.getText().toString();
            }
        });

        btorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderNow();
            }
        });

        ImageView btback=findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView title=findViewById(R.id.title);
        title.setText("Checkout");

    }

    private void OrderNow() {
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        String orderitms= new Gson().toJson(productModels);
        String address= new Gson().toJson(addressModels);
        CallJson jc = new CallJson(PageCheckOut.this);
        param.add(new NetParam("payment_mode",radioButton.getText().toString()));
        param.add(new NetParam("coupan_value",txcdis.getText().toString()));
        param.add(new NetParam("coupan_code",couponcode.getText().toString()));
        param.add(new NetParam("total_order_amount",txpamt.getText().toString()));
        param.add(new NetParam("items",orderitms));
        param.add(new NetParam("address",address));
        param.add(new NetParam("customer_id",customerModel.getCustomer_id()));
        jc.SendRequest(s,"addorder", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                startActivity(new Intent(PageCheckOut.this,PageSuccefull.class));
                UserUtil.ShowMsg("Order Placed !!", PageCheckOut.this);
            }
            @Override
            public void onPostError(String msg) {

            }
        }, " ", "Loading..");
    }


}
