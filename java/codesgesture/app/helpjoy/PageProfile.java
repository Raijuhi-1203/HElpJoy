package codesgesture.app.helpjoy;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

import codesgesture.app.helpjoy.Model.CustomerModel;
import codesgesture.app.helpjoy.Services.CallJson;
import codesgesture.app.helpjoy.Services.JsonCallbacks;
import codesgesture.app.helpjoy.Services.NetParam;
import codesgesture.app.helpjoy.Services.UserUtil;
import codesgesture.app.helpjoy.Services.Utility;
import codesgesture.app.helpjoy.Utils.SessionManage;

public class PageProfile extends AppCompatActivity {
    EditText txnm,txmail,txmob,txdob,txcp,txnp;
    Spinner spnrgender;
    String spnrid;
    DatePickerDialog datePickerDialog;
    CustomerModel customerModel;
    Button btn_submit2,btn_submit3;
    String s,m;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acc);
        customerModel=(CustomerModel)SessionManage.getCurrentUser(getApplicationContext());
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
        title.setText("Account");

        customerModel=(CustomerModel) SessionManage.getCurrentUser(getApplicationContext());

        txnm=findViewById(R.id.txnm);
        txmob=findViewById(R.id.txmob);
        txmail=findViewById(R.id.txmail);
        txdob=findViewById(R.id.txdob);
        txcp=findViewById(R.id.txcp);
        txnp=findViewById(R.id.txnp);
        spnrgender=findViewById(R.id.spnrgender);
        btn_submit2=findViewById(R.id.btn_submit2);
        btn_submit3=findViewById(R.id.btn_submit3);
        txnm.setText(customerModel.getCustomer_name());
        txmob.setText(customerModel.getCustomer_mobileno());
        txmail.setText(customerModel.getCustomer_email());
        txdob.setText(customerModel.getCustomer_dob());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrgender.setAdapter(adapter);

        spnrgender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spnrid = spnrgender.getSelectedItem().toString();;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        txdob.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                BindDate();
                return false;
            }
        });

        btn_submit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txcp.getText().length()==0){
                    txcp.setError("Please enter current password");
                }else if(txnp.getText().length()==0){
                    txnp.setError("Please enter new password");
                }else {
                    UpdatePassword();
                }
            }
        });

        btn_submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txnm.getText().length()==0){
                    txnm.setError("Please enter name");
                }else  if (txmail.getText().length()==0){
                    txmail.setError("Please enter mail id");
                }else if (txmob.getText().length()==0){
                    txmob.setError("Please enter mobile no");
                }else if (txdob.getText().length()==0){
                    txdob.setError("Please enter Dob");
                }else {
                    UpdateData();
                }
            }
        });




    }

    private void UpdatePassword() {
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(PageProfile.this);
        param.add(new NetParam("password",txcp.getText().toString()));
        param.add(new NetParam("newpass",txnp.getText().toString()));
        param.add(new NetParam("custid",customerModel.getCustomer_id()));
        jc.SendRequest(s,"update_password", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                UserUtil.ShowMsg("Password Updated !!",PageProfile.this);
            }
            @Override
            public void onPostError(String msg) {

            }
        }, " ", "Loading..");
    }

    private void UpdateData() {
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(PageProfile.this);
        param.add(new NetParam("name",txnm.getText().toString()));
        param.add(new NetParam("mobile",txmob.getText().toString()));
        param.add(new NetParam("gender",spnrgender.getSelectedItem().toString()));
        param.add(new NetParam("mail",txmail.getText().toString()));
        param.add(new NetParam("dob",txdob.getText().toString()));
        jc.SendRequest(s,"update_user", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                UserUtil.ShowMsg("Details Updated !!",PageProfile.this);
                CustomerModel sd = new CustomerModel();
                try {
                    JSONObject obj = UserUtil.ConvertStringToJsonObject(json);
                    if (obj.length() != 1) {
                        sd.setCustomer_date(obj.getString("customer_date"));
                        sd.setId(obj.getString("id"));
                        sd.setCustomer_dob(obj.getString("customer_dob"));
                        sd.setCustomer_email(obj.getString("customer_email"));
                        sd.setCustomer_gender(obj.getString("customer_gender"));
                        sd.setCustomer_id(obj.getString("customer_id"));
                        sd.setCustomer_mobileno(obj.getString("customer_mobileno"));
                        sd.setCustomer_name(obj.getString("customer_name"));
                        sd.setCustomer_password(obj.getString("customer_password"));
                        sd.setCustomer_profilephoto(obj.getString("customer_profilephoto"));
                        sd.setCustomer_status(obj.getString("customer_status"));
                        sd.setCustomer_temp_id(obj.getString("customer_temp_id"));
                        sd.setOtp(obj.getString("otp"));
                        sd.setCustcode(obj.getString("custcode"));
                        sd.setEmail_verification_code(obj.getString("email_verification_code"));
                        sd.setEmail_verified(obj.getString("email_verified"));
                        sd.setMobileno_verified(obj.getString("mobileno_verified"));
                        sd.setCustomer_time(obj.getString("customer_time"));
                        SessionManage.SetCustomerSessions(getApplicationContext(), sd);
                        finish();
                    }
                } catch (JSONException e) {
                    Utility.ShowMEssage(PageProfile.this, "Invalid OTP !");
                    e.printStackTrace();
                }
            }
            @Override
            public void onPostError(String msg) {

            }
        }, " ", "Loading..");
    }

    private void BindDate() {
        // perform click event on text view
        txdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(PageProfile.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text

                                int mnth = monthOfYear+1;
                                int day = dayOfMonth;

                                if(mnth <= 9 && day <= 9){
                                    txdob.setText(year + "-0" + (monthOfYear + 1) + "-0" + dayOfMonth);
                                }else if(mnth <= 9){
                                    txdob.setText(year + "-0" + (monthOfYear + 1) + "-" + dayOfMonth);
                                }else if(day <= 9){
                                    txdob.setText(year + "-" + (monthOfYear + 1) + "-0" + dayOfMonth);
                                }else {
                                    txdob.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                }
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }


}
