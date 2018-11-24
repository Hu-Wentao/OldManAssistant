package com.example.huwt.oldmanassistant;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ContactView extends LinearLayout {
    // 定义变量
    private TextView tvContactName;
    private ImageView ivContactImg;

    public ContactView(Context context) {
        this(context, null);
    }

    public ContactView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ContactView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context, attrs);
    }

    /**
     * 初始化控件
     * @param context
     * @param attrs
     */
    private void initView(Context context, AttributeSet attrs) {
//        LayoutInflater.from(context).inflate(R.layout.view_contact, this, true);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ContactView);
        // 从布局文件中获取设置内容
        int contactImg = typedArray.getResourceId(R.styleable.ContactView_contact_img, R.drawable.call);
        String contactName = typedArray.getString(R.styleable.layout_contact_contact_name);
        typedArray.recycle();
        LayoutInflater.from(context).inflate(R.layout.view_contact, this);
        // 获取控件ID
        ivContactImg = findViewById(R.id.iv_contact_img);
        tvContactName = findViewById(R.id.tv_contact_name);

        //设置获取到的内容
        ivContactImg.setImageResource(contactImg);
        tvContactName.setText(contactName);
    }

    /**
     * 设置联系人头像
     * @param resId 头像
     */
    private void setIvContactImg(int resId){
        ivContactImg.setImageResource(resId);
    }

    /**
     * 设置要显示的联系人姓名
     * @param contactName 姓名
     */
    private void setContantName(String contactName){
        tvContactName.setText(contactName);
    }


}
