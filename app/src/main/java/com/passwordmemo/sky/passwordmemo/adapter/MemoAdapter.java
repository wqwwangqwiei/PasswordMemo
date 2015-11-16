package com.passwordmemo.sky.passwordmemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.passwordmemo.sky.passwordmemo.R;
import com.passwordmemo.sky.passwordmemo.dao.UserPassword;

import org.w3c.dom.Text;

import java.util.List;


/**
 * Created by sky on 2015/11/10.
 */
public class MemoAdapter extends BaseAdapter {


    private Context mContext;
    private LayoutInflater mInflater;
    List<UserPassword> listdata;        //Adapter数据源

    public final class ViewHolder {
        public TextView describe;
        public TextView username;
        public TextView password;
    }


        public MemoAdapter(Context context, List<UserPassword> list){
        mContext=context;
        listdata=list;
        mInflater=LayoutInflater.from(mContext);

    }

    @Override
    public int getCount() {
        return listdata.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder=null;
        if (convertView==null){
            holder = new ViewHolder();
            convertView=mInflater.inflate(R.layout.adapter_account_item,null);
            holder.describe=(TextView)convertView.findViewById(R.id.adapter_account_describe);
            holder.username=(TextView)convertView.findViewById(R.id.adapter_account_username);
            holder.password=(TextView)convertView.findViewById(R.id.adapter_account_password);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.describe.setText(listdata.get(position).getDescribe());
        holder.username.setText(listdata.get(position).getAccount());
        holder.password.setText(listdata.get(position).getPassword());



        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return listdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listdata.get(position).getId();
    }
}
