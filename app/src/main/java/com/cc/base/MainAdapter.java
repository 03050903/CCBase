package com.cc.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by C&C on 2016/6/23.
 */

public class MainAdapter extends BaseAdapter
{
    private Context context;
    private List<MainModel> list;

    public MainAdapter(Context context, List<MainModel> list)
    {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount()
    {
        return list.size();
    }

    @Override
    public Object getItem(int i)
    {
        return list.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        ViewHolder viewHolder;
        if (view==null)
        {
            view=View.inflate(context,R.layout.item_log,null);
            viewHolder=new ViewHolder();
            viewHolder.nameTextView= (TextView) view.findViewById(R.id.tv_name);
            view.setTag(viewHolder);
        }else
        {
            viewHolder= (ViewHolder) view.getTag();
        }
        MainModel mainModel = list.get(i);
        viewHolder.nameTextView.setText(mainModel.getName());
        return view;
    }
    private class ViewHolder
    {
        TextView nameTextView;
    }
}
