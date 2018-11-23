package com.example.huwt.oldmanassistant;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ContactLayout extends LinearLayout {
    // 定义变量
    private TextView tvContactName;
    public ContactLayout(Context context) {
        super(context);
    }

    // 设置要显示的联系人姓名
    public void setContantName(String contactName){
        tvContactName.setText(contactName);
    }

}
