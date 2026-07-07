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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import codesgesture.app.helpjoy.Adapter.AddressAdapter;
import codesgesture.app.helpjoy.Model.AddressModel;
import codesgesture.app.helpjoy.Model.CartModel;
import codesgesture.app.helpjoy.Model.CustomerModel;
import codesgesture.app.helpjoy.Services.CallJson;
import codesgesture.app.helpjoy.Services.JsonCallbacks;
import codesgesture.app.helpjoy.Services.NetParam;
import codesgesture.app.helpjoy.Utils.SessionManage;
import codesgesture.app.helpjoy.interfaces.Notify;

public class PageAddress extends AppCompatActivity  {
    Button btn_add;
    RecyclerView recycler;
    LinearLayout norecord;
    ArrayList<CartModel> productModel;
    TextView btproceed;
    String carttotal,market_price_total;
    CustomerModel customerModel;
    AddressAdapter addressAdapter;
    ArrayList<AddressModel> addressModels=new ArrayList<>();
    String s;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_book);
        customerModel=(CustomerModel) SessionManage.getCurrentUser(getApplicationContext());
        carttotal=getIntent().getStringExtra("total");
        market_price_total=getIntent().getStringExtra("market_price_total");
        productModel=(ArrayList<CartModel>)getIntent().getSerializableExtra("cartModels");

        s=getString(R.string.con);
        norecord=findViewById(R.id.norecord);
        btn_add=findViewById(R.id.btn_add);
        btproceed=findViewById(R.id.btproceed);
        recycler=findViewById(R.id.recycler);

        btproceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PageAddress.this,PageCheckOut.class);
                intent.putExtra("total",carttotal);
                intent.putExtra("market_price_total",market_price_total);
                intent.putExtra("cartModels",productModel);
                intent.putExtra("address",addressModels);
                startActivity(intent);
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PageAddress.this,AddAddress.class);
                intent.putExtra("total",carttotal);
                intent.putExtra("market_price_total",market_price_total);
                intent.putExtra("cartModels",productModel);
                intent.putExtra("id","1");
                startActivity(intent);
            }
        });

        GridLayoutManager mLayoutManager = new GridLayoutManager(PageAddress.this, 1);
        recycler.setLayoutManager(mLayoutManager);
        addressAdapter = new AddressAdapter(PageAddress.this, addressModels, R.layout.item_address, new Notify() {
            @Override
            public void onAdd(CartModel data) {
                GetData();
            }

            @Override
            public void onRemove(CartModel data) {
                GetData();
            }
        },carttotal,market_price_total,productModel);
        recycler.setAdapter(addressAdapter);
        recycler.setItemViewCacheSize(addressModels.size());
        GetData();

        ImageView btback=findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView title=findViewById(R.id.title);
        title.setText("Address");

    }

    private void GetData() {
        addressModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(PageAddress.this);
        param.add(new NetParam("customer_id",customerModel.getCustomer_id()));
        jc.SendRequest(s,"get_address", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                JSONArray array = new JSONArray(json);
                for (int s = 0; s < array.length(); s++) {
                    JSONObject obj = array.getJSONObject(s);
                    AddressModel product = new AddressModel();
                    product.setAddress_city_name(obj.getString("address_city_name"));
                    product.setAddress_default(obj.getString("address_default"));
                    product.setAddress_customer_email(obj.getString("address_customer_email"));
                    product.setAddress_city_name(obj.getString("address_city_name"));
                    product.setAddress_customer_mobileno(obj.getString("address_customer_mobileno"));
                    product.setAddress_customer_name(obj.getString("address_customer_name"));
                    product.setAddress_line_1(obj.getString("address_line_1"));
                    product.setAddress_line_2(obj.getString("address_line_2"));
                    product.setAddress_state_name(obj.getString("address_state_name"));
                    product.setCustomer_id(obj.getString("customer_id"));
                    product.setAddress_pincode(obj.getString("address_pincode"));
                    product.setId(obj.getString("id"));
                    addressModels.add(product);
                }
                addressAdapter.notifyDataSetChanged();
                BindDataToView();
            }

            @Override
            public void onPostError(String msg) {
                BindDataToView();
            }
        }, "", "Loading..");
    }
    private void BindDataToView() {
        if(addressModels.size()>0)
            norecord.setVisibility(View.GONE);
        else
            norecord.setVisibility(View.VISIBLE);
    }

}
