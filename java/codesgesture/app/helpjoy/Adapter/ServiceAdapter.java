package codesgesture.app.helpjoy.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import codesgesture.app.helpjoy.Model.ServiceModel;
import codesgesture.app.helpjoy.PageCategory;
import codesgesture.app.helpjoy.PageServices;
import codesgesture.app.helpjoy.R;
import codesgesture.app.helpjoy.Services.DateFormate;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {
    private ArrayList<ServiceModel> arrayList;
    private Context context;
    String Userid="";
    private int layout;
    String cm;

    public ServiceAdapter(Context context, ArrayList<ServiceModel> arrayList, int layout) {
        this.arrayList = arrayList;
        this.context = context;
        this.layout=layout;
        cm=context.getString(R.string.maincon);
        //   this.Userid = SessionManage.getCurrentUser(context.getApplicationContext()).getStudent_id();
    }

    @Override
    public ServiceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ServiceAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ServiceAdapter.ViewHolder holder, final int i) {
        final ServiceModel data = arrayList.get(i);

        holder.servnm.setText(data.getCategory_name());
        Glide.with(context).load(cm+data.getCategory_photo()).into(holder.img);
        holder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.getMain_category_id().equals("0")){
                    Intent intent=new Intent(context, PageCategory.class);
                    intent.putExtra("data",data);
                    context.startActivity(intent);
                }else {
                    Intent intent=new Intent(context, PageServices.class);
                    intent.putExtra("data",data);
                    context.startActivity(intent);
                }

            }
        });

    }


    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView servnm;
        ImageView img;
        LinearLayout click;
        ViewHolder(View view) {
            super(view);
            servnm = view.findViewById(R.id.servnm);
            img = view.findViewById(R.id.img);
            click = view.findViewById(R.id.click);
        }
    }

    public void updateList(ArrayList<ServiceModel> list) {
        arrayList = list;
        notifyDataSetChanged();
    }


}