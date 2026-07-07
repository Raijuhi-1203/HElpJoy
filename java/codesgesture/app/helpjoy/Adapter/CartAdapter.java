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

import java.util.ArrayList;

import codesgesture.app.helpjoy.Model.CartModel;
import codesgesture.app.helpjoy.R;
import codesgesture.app.helpjoy.ServiceDetails;
import codesgesture.app.helpjoy.Services.CallJson;
import codesgesture.app.helpjoy.Services.CallJsonWithoutProgress;
import codesgesture.app.helpjoy.Services.JsonCallbacks;
import codesgesture.app.helpjoy.Services.NetParam;
import codesgesture.app.helpjoy.Services.UserUtil;
import codesgesture.app.helpjoy.Utils.SessionManage;
import codesgesture.app.helpjoy.interfaces.ExtraCallBack;
import codesgesture.app.helpjoy.interfaces.Notify;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private ArrayList<CartModel> arrayList;
    private Context context;
    String Userid="";
    private int layout;
    String cm,s;
    Notify notify;
    ExtraCallBack ecb;

    public CartAdapter(Context context, ArrayList<CartModel> arrayList, int layout,Notify notify1) {
        this.arrayList = arrayList;
        this.context = context;
        this.layout=layout;
        cm=context.getString(R.string.maincon);
        s=context.getString(R.string.con);
        this.notify=notify1;
        this.Userid = SessionManage.getCurrentUser(context.getApplicationContext()).getCustomer_id();
    }

    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new CartAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final CartAdapter.ViewHolder holder, final int i) {
        final CartModel data = arrayList.get(i);

        holder.name.setText(data.getProduct_full_name());

        double fsp = Double.parseDouble(data.getProduct_final_sell_price());
        int a = data.getCart_qty();
        double fp = fsp * a;
        String result = String.format("%.2f", fp);
        holder.mrp.setText("₹ "+result);

        holder.qty.setText("Quantity : "+data.getCart_qty());
        holder.cartqty.setText(String.valueOf(data.getCart_qty()));

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cq =data.getCart_qty();
                cq++;
                data.setQty(cq);
                data.setCart_qty(cq);
                String price = data.getProduct_final_sell_price() ;
                double totalamt = cq * Double.parseDouble(price) ;
                String result = String.format("%.2f", totalamt);
                holder.cartqty.setText(String.valueOf(data.getCart_qty()));
                holder.qty.setText("Quantity : "+String.valueOf(data.getCart_qty()));
                holder.mrp.setText("₹ "+result);
                notify.onRemove(null);
                QtyChangeCart(totalamt,data.getQty(),Userid,data.getProduct_id(),data.getId1());

            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cq = data.getCart_qty();
                if (cq>1) {
                    cq--;
                    data.setQty(cq);
                    data.setCart_qty(cq);
                }
                String price = data.getProduct_final_sell_price() ;
                double totalamt = cq * Double.parseDouble(price) ;
                String result = String.format("%.2f", totalamt);
                holder.cartqty.setText(String.valueOf(data.getCart_qty()));
                holder.qty.setText("Quantity : "+String.valueOf(data.getCart_qty()));
                holder.mrp.setText("₹ "+result);
                QtyChangeCart(totalamt,data.getQty(),Userid,data.getProduct_id(),data.getId1());

            }
        });

        Glide.with(context).load(cm+data.getPhoto_path()).into(holder.img);

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RemoveCart(Userid,data.getProduct_id(),data.getId1());
            }
        });

        holder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(context, ServiceDetails.class);
//                intent.putExtra("data",data);
//                context.startActivity(intent);
            }
        });

    }

    private void RemoveCart(String userid, String product_id, String id1) {
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson((Activity) context);
        param.add(new NetParam("customer_id",userid));
        param.add(new NetParam("product_id",product_id));
        param.add(new NetParam("product_price_id",id1));
        jc.SendRequest(s,"removetocart", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                UserUtil.ShowMsg("Service removed from cart !",context);
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
        TextView name,mrp,qty,cartqty;
        ImageView img,remove,minus,add;
        LinearLayout click;
        ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            mrp = view.findViewById(R.id.mrp);
            qty = view.findViewById(R.id.qty);
            cartqty = view.findViewById(R.id.cartqty);
            img = view.findViewById(R.id.img);
            remove = view.findViewById(R.id.remove);
            minus = view.findViewById(R.id.minus);
            add = view.findViewById(R.id.add);
            click = view.findViewById(R.id.click);

        }
    }

    public void updateList(ArrayList<CartModel> list) {
        arrayList = list;
        notifyDataSetChanged();
    }

    private void QtyChangeCart(double totalamt, int cart_qty, String userid, String product_id, String product_final_sell_price) {
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJsonWithoutProgress jc = new CallJsonWithoutProgress((Activity) context);
        param.add(new NetParam("product_id",product_id));
        param.add(new NetParam("cart_qty",String.valueOf(cart_qty)));
        param.add(new NetParam("product_price_id",product_final_sell_price));
        param.add(new NetParam("customer_id",userid));
        param.add(new NetParam("total_order_amount",String.valueOf(String.format("%.02f",totalamt))));
        jc.SendRequest(s,"addtocart_changeqty", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                if (ecb != null){
                    ecb.OnCompleted("removed");
                }
                notify.onAdd(null);
            }
            @Override
            public void onPostError(String msg) {
            }
        }, " ", "Loading..");
    }

}