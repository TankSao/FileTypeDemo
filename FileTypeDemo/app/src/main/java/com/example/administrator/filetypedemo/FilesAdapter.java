package com.example.administrator.filetypedemo;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/8/8.
 */

public class FilesAdapter extends ListBaseAdapter {
    private Context context;
    private int layout;
    private String flag;
    //创建接口
    public interface OnItenClick {
        void onClick(Files files);
    }

    //声明接口对象
     OnItenClick listener;
    //设置监听器,实例化接口
    public void setOnItenClickListener(OnItenClick listener) {
        this.listener = listener;
    }
    public FilesAdapter(Context context) {
        super(context);
    }
    public FilesAdapter(Context context, int layoutId, String flag) {
        super(context);
        this.context = context;
        this.layout = layoutId;
        this.flag=flag;
    }

    @Override
    public int getLayoutId() {
        return layout;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        TextView type,size,name;
        ImageView icon;
        RelativeLayout rl;
        type = holder.getView(R.id.file_type);
        size = holder.getView(R.id.file_count);
        name = holder.getView(R.id.file_name);
        icon = holder.getView(R.id.iv_file);
        rl = holder.getView(R.id.rl);
        final Files files = (Files) mDataList.get(position);
        type.setText(files.getType());
        name.setText(files.getName());
        size.setText(files.getSize());
        //GlideLoadUtils.getInstance().glideLoad(mContext, HttpUrl.BASE_URL3 + friendCricle.getUser().getAvatar(), avatar, R.mipmap.photo);
        switch (files.getType()){
            case FileType.PICTURE:
                icon.setImageResource(R.mipmap.file_picture);
                break;
            case FileType.AUDIO:
                icon.setImageResource(R.mipmap.file_audio);
                break;
            case FileType.VIDEO:
                icon.setImageResource(R.mipmap.file_video);
                break;
            case FileType.TXT:
                icon.setImageResource(R.mipmap.file_txt);
                break;
            case FileType.WORD:
                icon.setImageResource(R.mipmap.file_word);
                break;
            case FileType.PPT:
                icon.setImageResource(R.mipmap.file_ppt);
                break;
            case FileType.PDF:
                icon.setImageResource(R.mipmap.file_pdf);
                break;
            case FileType.EXCEL:
                icon.setImageResource(R.mipmap.file_excel);
                break;
            case FileType.DEFAULTTYPE:
                icon.setImageResource(R.mipmap.file_default);
                break;
        }
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null) {
                    listener.onClick(files);
                }
            }
        });
    }
}
