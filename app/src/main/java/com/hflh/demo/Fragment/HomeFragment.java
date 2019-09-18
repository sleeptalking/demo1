package com.hflh.demo.Fragment;

import android.content.Intent;

import android.util.Log;
import android.view.View;

import com.hflh.demo.R;
import com.hflh.demo.activity.ShopListActivity;
import com.hflh.demo.base.BaseFragment;
import com.hflh.demo.util.IntentUtils;
import com.hflh.demo.util.ToastUtils;

import butterknife.OnClick;


public class HomeFragment extends BaseFragment {

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    protected void initView(View view) {

    }

    @OnClick(R.id.tv)
    public void tv(){

        //PermissionUtils.toAppSetting(getActivity());
        Intent intent = new Intent(getActivity(), ShopListActivity.class);
        IntentUtils.toActivity(getActivity(),intent,true);
        // getActivity().startActivity(intent);
    }

    int i = 0;
    @OnClick(R.id.bt1)
    public void bt1(){

        i++;
        Log.i("TAG", "bt1: "+i);
        ToastUtils.showToastByOne(getActivity(),"aaaaaaaaaaaaaa" + i,false);
    }
    @OnClick(R.id.bt2)
    public void bt2(){
        ToastUtils.detach();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }
}
