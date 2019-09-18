package com.hflh.demo.ui;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hflh.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddCategoryDialog extends Dialog {


    @BindView(R.id.tv_alert_title)
    TextView tvTitle;
    @BindView(R.id.et_alert_category_name)
    EditText etName;
    @BindView(R.id.et_alert_category_priority)
    EditText etPriority;
    @BindView(R.id.bt_alert_category_back)
    Button btBack;
    @BindView(R.id.bt_alert_category_submit)
    Button btSubmit;

    private String title;

    private OnBackListener backListener;
    private OnSubmitListener submitListener;


    public AddCategoryDialog(Context context) {
        super(context);
    }

    public AddCategoryDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected AddCategoryDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void setTitle(String title){
        this.title = title;
    }
    public void setCancel(OnBackListener backListener){
        this.backListener = backListener;

    }
    public void setConfirm(OnSubmitListener submitListener){
        this.submitListener = submitListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_add_category);
        ButterKnife.bind(this);

        //设置弹窗的宽度
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p =getWindow().getAttributes();
        Point size = new Point();
        d.getSize(size);
        p.width = (int)(size.x * 0.8);//是dialog的宽度为app界面的80%
        getWindow().setAttributes(p);

        tvTitle.setText("添加分类");

    }

    @OnClick(R.id.bt_alert_category_back)
    public void back() {
        backListener.onBack();
        dismiss();
    }

    @OnClick(R.id.bt_alert_category_submit)
    public void submit() {
        submitListener.onSubmit(this,etName.getText().toString(),etPriority.getText().toString());
    }

    public interface OnBackListener {
        void onBack();
    }

    public interface OnSubmitListener {
        void onSubmit(AddCategoryDialog dialog,String categoryName,String priority);
    }


}
