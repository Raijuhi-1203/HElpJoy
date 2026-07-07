package codesgesture.app.helpjoy.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import codesgesture.app.helpjoy.Model.ProductModel;
import codesgesture.app.helpjoy.PageCategory;
import codesgesture.app.helpjoy.R;
import codesgesture.app.helpjoy.ServiceDetails;
import codesgesture.app.helpjoy.Services.CallJson;
import codesgesture.app.helpjoy.Services.CallJsonWithoutProgress;
import codesgesture.app.helpjoy.Services.JsonCallbacks;
import codesgesture.app.helpjoy.Services.NetParam;
import codesgesture.app.helpjoy.Services.UserUtil;
import codesgesture.app.helpjoy.Utils.SessionManage;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private ArrayList<ProductModel> arrayList;
    private Context context;
    String Userid="";
    private int layout;
    String cm,s;

    public ProductAdapter(Context context, ArrayList<ProductModel> arrayList, int layout) {
        this.arrayList = arrayList;
        this.context = context;
        this.layout=layout;
        cm=context.getString(R.string.maincon);
        s=context.getString(R.string.con);
        this.Userid = SessionManage.getCurrentUser(context.getApplicationContext()).getCustomer_id();
    }

    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ProductAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ProductAdapter.ViewHolder holder, final int i) {
        final ProductModel data = arrayList.get(i);

        holder.name.setText(data.getProduct_full_name());
        holder.mrp.setText("₹ "+data.getProduct_final_sell_price());
        if (data.getProduct_discount_percentage().equals("null") || data.getProduct_discount_percentage().equals("0")){
            holder.offer.setVisibility(View.GONE);
        }else {
            holder.offer.setText("-"+data.getProduct_discount_percentage()+"%");
        }

        Glide.with(context).load(cm+data.getPhoto_path()).into(holder.img);
        String s = data.getReview_star();
        if (s.equals("null") || s.equals(null)){
            holder.ratingBar.setRating(0);
        }else {
            float d = Float.parseFloat(s);
            holder.ratingBar.setRating(d);
        }


        holder.btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCart("1",Userid,data.getProduct_id(),data.getId1(),data.getProduct_final_sell_price(),data.getProduct_full_name(),data.getProduct_market_price(),data.getProduct_sell_price(),data.getProduct_discount_percentage(),data.getProduct_discount_price(),data.getProduct_with_gst_Price(),data.getProduct_final_sell_price(),data.getProduct_market_price());
            }
        });

        holder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ServiceDetails.class);
                intent.putExtra("data",data);
                context.startActivity(intent);
            }
        });


    }

    private void AddCart(String cartqty, String userid, String product_id, String id1, String product_final_sell_price, String product_full_name, String product_market_price, String product_sell_price, String product_discount_percentage, String product_discount_price, String product_with_gst_price, String product_final_sell_price1, String product_market_price1) {
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson((Activity) context);
        param.add(new NetParam("cart_qty",cartqty));
        param.add(new NetParam("customer_id",userid));
        param.add(new NetParam("product_id",product_id));
        param.add(new NetParam("product_price_id",id1));
        param.add(new NetParam("total_order_amount",product_final_sell_price));
        param.add(new NetParam("product_name",product_full_name));
        param.add(new NetParam("product_market_price",product_market_price));
        param.add(new NetParam("product_sell_price",product_sell_price));
        param.add(new NetParam("product_discount_percentage",product_discount_percentage));
        param.add(new NetParam("product_discount_price",product_discount_price));
        param.add(new NetParam("product_with_gst_Price",product_with_gst_price));
        param.add(new NetParam("product_final_sell_price",product_final_sell_price1));
        param.add(new NetParam("total_market_price",product_market_price1));
        jc.SendRequest(s,"addtocart", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                UserUtil.ShowMsg("Service Add in Cart !",context);
            }

            @Override
            public void onPostError(String msg) {

            }
        }, "", "Loading..");
    }


    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,mrp,offer;
        ImageView img;
        LinearLayout click;
        RatingBar ratingBar;
        Button btnbook;
        ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            mrp = view.findViewById(R.id.mrp);
            offer = view.findViewById(R.id.offer);
            img = view.findViewById(R.id.img);
            click = view.findViewById(R.id.click);
            ratingBar = view.findViewById(R.id.ratingBar);
            btnbook = view.findViewById(R.id.btnbook);
        }
    }

    public void updateList(ArrayList<ProductModel> list) {
        arrayList = list;
        notifyDataSetChanged();
    }


}