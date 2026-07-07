package codesgesture.app.helpjoy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import codesgesture.app.helpjoy.Adapter.ServiceAdapter;
import codesgesture.app.helpjoy.Model.ServiceModel;
import codesgesture.app.helpjoy.Services.CallJson;
import codesgesture.app.helpjoy.Services.JsonCallbacks;
import codesgesture.app.helpjoy.Services.NetParam;

public class PageAllService extends AppCompatActivity {
    String s,m;
    RecyclerView rvservice;
    ServiceAdapter serviceAdapter;
    ArrayList<ServiceModel> serviceModels=new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allserv);
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
        title.setText("Services");

        rvservice=findViewById(R.id.rvservice);
        GridLayoutManager mLayoutManager = new GridLayoutManager(PageAllService.this, 3);
        rvservice.setLayoutManager(mLayoutManager);
        serviceAdapter = new ServiceAdapter(PageAllService.this, serviceModels, R.layout.row_category);
        rvservice.setAdapter(serviceAdapter);
        rvservice.setItemViewCacheSize(serviceModels.size());
        GetData();


    }

    private void GetData() {
        serviceModels.clear();
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(PageAllService.this);
        jc.SendRequest(s,"getallcategory", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                JSONArray array = new JSONArray(json);
                for (int s = 0; s < array.length(); s++) {
                    JSONObject obj = array.getJSONObject(s);
                    ServiceModel product = new ServiceModel();
                    product.setCategory_name(obj.getString("category_name"));
                    product.setCategory_photo(obj.getString("category_photo"));
                    product.setId(obj.getString("id"));
                    product.setMain_category_id(obj.getString("main_category_id"));
                    product.setCategory_id(obj.getString("category_id"));
                    serviceModels.add(product);
                }
                serviceAdapter.notifyDataSetChanged();
            }

            @Override
            public void onPostError(String msg) {
            }
        }, "", "Loading..");
    }

}
