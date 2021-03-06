package com.lg.wheelviewdemo;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * 作者：rjhsmile
 * 时间： 2017年3月31日16:38:11
 */
public class MainActivity extends AppCompatActivity {
    public static TextView tv;
    private ArrayList<String> mDatas = new ArrayList<>();
    public String mText = "0";//解决用户未滑动数据显示
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
    }

    public void onClick(View view) {
        couponSuccessDialog();
    }


    private void couponSuccessDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.activity_wheel, null);// 得到加载view
        final Dialog loadingDialog = new Dialog(this, R.style.MyDialog);// 创建自定义样式dialog
        loadingDialog.setCancelable(true);// 不可以用“返回键”取消
        loadingDialog.setCanceledOnTouchOutside(false);
        PickerView pickerView = (PickerView) view.findViewById(R.id.pickerview);
        mText = "";
        mDatas.clear();
        for (int i = 1; i < 60; i++) {
            mDatas.add("" + i);
        }
        pickerView.setData(mDatas);
        mText = mDatas.get(0);
        pickerView.setSelected(mText);
        pickerView.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                mText = text;
                tv.setText(mText);
            }
        });
        //确定按钮
        Button btnOk = (Button) view.findViewById(R.id.btn);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText(mText);
                loadingDialog.dismiss();
            }
        });
        loadingDialog.setContentView(view, new LinearLayout.LayoutParams(
                getDeviceWidth(this),
                getDeviceHeigh(this)));// 设置布局
        loadingDialog.show();
    }

    public static int getDeviceWidth(Activity activity) {
        WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }

    public static int getDeviceHeigh(Activity activity) {
        WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        int heigh = wm.getDefaultDisplay().getHeight();
        return heigh;
    }
}
