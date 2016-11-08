package com.example.hei123.lolcustom.Me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.hei123.lolcustom.R;

/**
 * Created by hei123 on 10/21/2016.
 * CopyRight @hei123
 */

public class CombatFragment extends Fragment {

    private View view;
    private ListView lv_combat;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.me_combat_fragment, null);
        initView();
        initData();
        return view;
    }

    /**
     * 初始化数据
     */
    private void initData() {
        getDataFromService();
    }

    /**
     * 从服务器划去数据
     */
    private void getDataFromService() {

    }

    /**
     * 初始化控件
     */
    private void initView() {
        lv_combat = (ListView) view.findViewById(R.id.lv_combat);
        lv_combat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
    class ListCombatAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }
}
