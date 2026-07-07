package codesgesture.app.helpjoy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import codesgesture.app.helpjoy.Model.CartModel;
import codesgesture.app.helpjoy.Model.CityModel;
import codesgesture.app.helpjoy.Model.CustomerModel;
import codesgesture.app.helpjoy.Model.ProductModel;
import codesgesture.app.helpjoy.Model.StateModel;
import codesgesture.app.helpjoy.Services.CallJson;
import codesgesture.app.helpjoy.Services.CallJsonWithoutProgress;
import codesgesture.app.helpjoy.Services.JsonCallbacks;
import codesgesture.app.helpjoy.Services.NetParam;
import codesgesture.app.helpjoy.Services.UserUtil;
import codesgesture.app.helpjoy.Utils.SessionManage;

public class AddAddress extends AppCompatActivity {
    EditText txnm,txmail,txmob,txadd1,txadd2,txpincode,txcity;
    Spinner spnrstate;
    Button btn_save;
    CheckBox chkdefault;
    CustomerModel customerModel;
    String spnrstid,spnrctid,statnm,citynm;
    String carttotal,market_price_total;
    ArrayList<CartModel> productModels;
    ArrayAdapter<StateModel> stateModelArrayAdapter;
    ArrayList<StateModel> stateModels=new ArrayList<>();

    String id,s;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_address);
        customerModel=(CustomerModel) SessionManage.getCurrentUser(getApplicationContext());
        carttotal=getIntent().getStringExtra("total");
        market_price_total=getIntent().getStringExtra("market_price_total");
        productModels=(ArrayList<CartModel>)getIntent().getSerializableExtra("cartModels");
        id=getIntent().getStringExtra("id");
        s=getString(R.string.con);
        Bindids();
        spnrstate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int pos = spnrstate.getSelectedItemPosition();
                spnrstid=String.valueOf(stateModels.get(pos).getState_id());
                statnm=String.valueOf(stateModels.get(pos).getState_name());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        stateModels = new ArrayList<>();
        stateModelArrayAdapter = new ArrayAdapter<StateModel>(AddAddress.this, android.R.layout.simple_spinner_item, stateModels);
        stateModelArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrstate.setAdapter(stateModelArrayAdapter);

        AreaJsonCall();

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txnm.getText().length()==0){
                    txnm.setError("Enter name");
                }else if(txmob.getText().length()==0){
                    txmob.setError("Enter mobile");
                }else if(txmail.getText().length()==0){
                    txmail.setError("Enter mail");
                }else if(txadd1.getText().length()==0){
                    txadd1.setError("Enter address 1");
                }else if(txadd2.getText().length()==0){
                    txadd2.setError("Enter address 2");
                }else if(txpincode.getText().length()==0){
                    txpincode.setError("Enter pincode");
                }else {
                    Add_Address();
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
        title.setText("Add Address");

    }

    private void AreaJsonCall() {
        stateModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(AddAddress.this);
        jc.SendRequest(s,"get_state", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                JSONArray array = new JSONArray(json);
                for (int s = 0; s < array.length(); s++) {
                    JSONObject obj = array.getJSONObject(s);
                    StateModel mod = new StateModel();
                    mod.setState_id(obj.getString("state_id"));
                    mod.setState_name(obj.getString("state_name"));
                    stateModels.add(mod);
                    stateModelArrayAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onPostError(String msg) {
            }
        }, "LOGIN", "Please wait while getting..");
    }
    private void Add_Address() {
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(AddAddress.this);
        param.add(new NetParam("address_customer_name",txnm.getText().toString()));
        param.add(new NetParam("address_customer_mobileno",txmob.getText().toString()));
        param.add(new NetParam("address_customer_email",txmail.getText().toString()));
        param.add(new NetParam("address_line_1",txadd1.getText().toString()));
        param.add(new NetParam("address_line_2",txadd2.getText().toString()));
        param.add(new NetParam("customer_id",customerModel.getCustomer_id()));
        param.add(new NetParam("address_pincode",txpincode.getText().toString()));

        param.add(new NetParam("address_city_id",txcity.getText().toString()));
        param.add(new NetParam("address_city_name",txcity.getText().toString()));
        param.add(new NetParam("address_state_id",spnrstid));
        param.add(new NetParam("address_state_name",statnm));
        String mdefault;
        if(chkdefault.isChecked()){
            mdefault="Yes";
        }else {
            mdefault="No";
        }
        param.add(new NetParam("address_default",mdefault));

        jc.SendRequest(s,"addaddress", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                if (id.equals("1")){
                    Intent intent=new Intent(AddAddress.this,PageAddress.class);
                    intent.putExtra("total",carttotal);
                    intent.putExtra("market_price_total",market_price_total);
                    intent.putExtra("cartModels",productModels);
                    startActivity(intent);
                }else if(id.equals("2")){
                    Intent intent=new Intent(AddAddress.this,AddressBook.class);
                    intent.putExtra("total",carttotal);
                    intent.putExtra("market_price_total",market_price_total);
                    intent.putExtra("cartModels",productModels);
                    startActivity(intent);
                }
            }
            @Override
            public void onPostError(String msg) {
            }
        }, " ", "Loading..");
    }
    private void Bindids() {
        txnm=findViewById(R.id.txnm);txmail=findViewById(R.id.txmail);
        txmob=findViewById(R.id.txmob);txadd1=findViewById(R.id.txadd1);
        txadd2=findViewById(R.id.txadd2);
        // spnrarea=findViewById(R.id.spnrarea);
        spnrstate=findViewById(R.id.spnrstate);txcity=findViewById(R.id.txcity);
        btn_save=findViewById(R.id.btn_save);chkdefault=findViewById(R.id.chkdefault);
        txpincode=findViewById(R.id.txpincode);
    }

}
