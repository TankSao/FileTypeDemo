package com.example.administrator.filetypedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements FilesAdapter.OnItenClick {

    @BindView(R.id.files)
    LRecyclerView files;
    private List<Files> list;
    private  String fileStr = "";
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private FilesAdapter mFilesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFiles();
        initView();
        initListener();
    }

    private void initListener() {
        mFilesAdapter.setOnItenClickListener(this);
    }

    private void initView() {
        GridLayoutManager manager = new GridLayoutManager(MainActivity.this, 1, LinearLayoutManager.VERTICAL, false);
        files.setLayoutManager(manager);
        mFilesAdapter = new FilesAdapter(MainActivity.this, R.layout.item_files, "files");
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mFilesAdapter);
        files.setAdapter(mLRecyclerViewAdapter);
        mFilesAdapter.setDataList(list);
        files.setPullRefreshEnabled(false);
        files.setNoMore(true);
    }

    private void initFiles() {
        fileStr = "[{\"id\":\"5b655b4a53c70d482840dce4\",\"user_name\":\"教师刘建军\",\"school_name\":\"济南市经五路小学\",\"book_id\":\"5b31ca65271ba10c54bc3f1a\",\"courses\":[\"5b31ca65271ba10c54bb937c\"],\"file_name\":\"beike.txt\",\"create_time\":1533369162,\"file_state\":3,\"download_count\":8,\"file_addr\":\"https://assistant-res.oss-cn-hangzhou.aliyuncs.com/652240c79bd4f30d2b520093cb3b7d00_b52afd8d8c8e6d41c5e0084197709fbd7142f51f.txt\",\"file_size\":741},{\"id\":\"5b655bf353c70d482840dce5\",\"user_name\":\"教师刘建军\",\"school_name\":\"济南市经五路小学\",\"book_id\":\"5b31ca65271ba10c54bc3f1a\",\"courses\":[\"5b31ca65271ba10c54bb937c\"],\"file_name\":\"123.txt\",\"create_time\":1533369331,\"file_state\":3,\"download_count\":13,\"file_addr\":\"https://assistant-res.oss-cn-hangzhou.aliyuncs.com/7872e78d2ae704f848fed2ebc3148a66_066f37f246f69dd8e618c15afc860ce01abbe464.txt\",\"file_size\":3487}]\n";
        list = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(fileStr);
            for (int i = 0; i < array.length(); i++) {
                JSONObject json = array.getJSONObject(i);
                Files file = new Files();
                String name = json.getString("file_name");
                String url = json.getString("file_addr");
                int size = json.getInt("file_size");
                String type = FileType.getFileType(name);
                file.setName(name);
                file.setType(type);
                file.setUrl(url);
                file.setSize(FileType.getFormatSize(size));
                list.add(file);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(Files files) {
        Toast.makeText(MainActivity.this,"下载地址:"+files.getUrl(),Toast.LENGTH_SHORT).show();
    }
}
