package com.example.huwt.oldmanassistant;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ContactLayout extends LinearLayout {
    // 定义变量
    private TextView tvContactName;
    private ImageView ivContactImg;
    public ContactLayout(Context context) {
        super(context);
        initView(context);  // 导入布局
    }

    public ContactLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray mTypedArray=context.obtainStyledAttributes(attrs,R.styleable.ContactLayout);
        tvContactName.setText( mTypedArray.getString(R.styleable.ContactLayout_contact_name) );
        //获取资源后要及时回收
        mTypedArray.recycle();
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_contact, this, true);
        ivContactImg = findViewById(R.id.iv_contact_img);
        tvContactName = findViewById(R.id.tv_contact_name);
    }

    /**
     * 设置联系人头像
     * @param resId 头像
     */
    public void setIvContactImg(int resId){
        ivContactImg.setImageResource(resId);
    }

    /**
     * 设置要显示的联系人姓名
     * @param contactName 姓名
     */
    public void setContantName(String contactName){
        tvContactName.setText(contactName);
    }


}
