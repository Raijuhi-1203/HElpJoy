package codesgesture.app.helpjoy;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import codesgesture.app.helpjoy.Model.CustomerModel;
import codesgesture.app.helpjoy.Model.Order;
import codesgesture.app.helpjoy.Model.OrderProductModel;
import codesgesture.app.helpjoy.Services.CallJson;
import codesgesture.app.helpjoy.Services.CallJsonWithoutProgress;
import codesgesture.app.helpjoy.Services.JsonCallbacks;
import codesgesture.app.helpjoy.Services.NetParam;
import codesgesture.app.helpjoy.Services.UserUtil;
import codesgesture.app.helpjoy.Utils.SessionManage;


public class CancelOrderForm extends AppCompatActivity  {

    Order order;
    CustomerModel customerModel;
    Spinner spnr;
    String spnrid;
    EditText reason;
    Button btnsubmit;
    OrderProductModel orderProductModel;
    String s;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cancel_orderform);
        customerModel=(CustomerModel) SessionManage.getCurrentUser(getApplicationContext());
        order=(Order)getIntent().getSerializableExtra("order");
        orderProductModel=(OrderProductModel)getIntent().getSerializableExtra("orderproduct");

        s=getString(R.string.con);
        reason=findViewById(R.id.reason);
        btnsubmit=findViewById(R.id.btnsubmit);
        spnr=findViewById(R.id.spnr);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.cancelorder, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnr.setAdapter(adapter);

        spnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spnrid = spnr.getSelectedItem().toString();;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(reason.getText().length()==0){
                    reason.setError("Please write reason..");
                }else {
                    if (orderProductModel == null){
                        CancelOrder();
                    }else if (order == null) {
                        CancelItem();
                    }
                }
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
        title.setText("Cancel Order");
    }

    private void CancelItem() {
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(CancelOrderForm.this);
        param.add(new NetParam("customer_id",customerModel.getCustomer_id()));
        param.add(new NetParam("product_id",orderProductModel.getProduct_id()));
        param.add(new NetParam("order_return_reason",spnrid));
        param.add(new NetParam("order_return_comment",reason.getText().toString()));
        param.add(new NetParam("order_id",orderProductModel.getOrder_id()));
        jc.SendRequest(s,"Cancel_order_Product", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                UserUtil.ShowMsg("Item Cancel Successfully.",CancelOrderForm.this);
                startActivity(new Intent(CancelOrderForm.this,CancelMessage.class));
            }
            @Override
            public void onPostError(String msg) {
            }
        }, "", "Loading..");
    }

    private void CancelOrder() {
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(CancelOrderForm.this);
        param.add(new NetParam("customer_id",customerModel.getCustomer_id()));
        param.add(new NetParam("order_return_reason",spnrid));
        param.add(new NetParam("order_return_comment",reason.getText().toString()));
        param.add(new NetParam("order_id",order.getOrder_id()));
        jc.SendRequest(s,"Cancel_Allorder", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
               UserUtil.ShowMsg("Order Cancel Successfully.",CancelOrderForm.this);
               startActivity(new Intent(CancelOrderForm.this,CancelMessage.class));
            }
            @Override
            public void onPostError(String msg) {
            }
        }, "", "Loading..");
    }


}
