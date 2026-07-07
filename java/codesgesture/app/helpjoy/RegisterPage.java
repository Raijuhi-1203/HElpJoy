package codesgesture.app.helpjoy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import codesgesture.app.helpjoy.Model.CustomerModel;
import codesgesture.app.helpjoy.Services.CallJson;
import codesgesture.app.helpjoy.Services.JsonCallbacks;
import codesgesture.app.helpjoy.Services.NetParam;
import codesgesture.app.helpjoy.Services.UserUtil;
import codesgesture.app.helpjoy.Services.Utility;
import codesgesture.app.helpjoy.Utils.SessionManage;

public class RegisterPage extends AppCompatActivity implements JsonCallbacks {
    Button btnsubmit;
    EditText pass,mail;
    String s;
    ArrayList<NetParam> param;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        s=getString(R.string.con);
        btnsubmit=findViewById(R.id.btnsubmit);
        pass=findViewById(R.id.pass);
        mail=findViewById(R.id.mail);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mail.getText().length()==0){
                    mail.setError("Please enter mail");
                }else if (pass.getText().length()==0){
                    pass.setError("Please enter mail");
                }else {
                    param = new ArrayList<NetParam>();
                    CallJson jc = new CallJson(RegisterPage.this);
                    param.add(new NetParam("mail", mail.getText().toString()));
                    param.add(new NetParam("password", pass.getText().toString()));
                    jc.SendRequest(s,"register_user", param, RegisterPage.this, "register_user", "Please wait while verifying..");
                }
            }
        });
    }

    @Override
    public void onPostError(String msg) {

    }

    @Override
    public void onPostSuceess(String json, String method) throws JSONException {
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
                Intent act = new Intent(RegisterPage.this, DashBoard.class);
                startActivity(act);
                UserUtil.ShowMsg("Register Successfully !", RegisterPage.this);
                finish();
            } else {
                Utility.ShowMEssage(RegisterPage.this, "Invalid user and Password!");
            }
        } catch (JSONException e) {
            Utility.ShowMEssage(RegisterPage.this, "Invalid user and Password!");
            e.printStackTrace();
        }
    }

}
