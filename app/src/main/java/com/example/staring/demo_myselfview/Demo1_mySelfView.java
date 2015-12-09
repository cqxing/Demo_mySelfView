package com.example.staring.demo_myselfview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by staring on 2015/12/8.
 * <p/>
 * 利用已经存在的控件 组成xml 然后做成控件
 */
public class Demo1_mySelfView extends LinearLayout implements View.OnClickListener {

    private Context context;
    private TextView tv;
    private Button btn;

    public Demo1_mySelfView(Context context, AttributeSet attrs) {
//        这里可以获取上下文 和attrs属性
        super(context, attrs);
        this.context = context;
        View.inflate(context, R.layout.demo1_myself_view, this);
        //如何得到attrs里面的属性呢
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        tv = (TextView) findViewById(R.id.tv);
        btn = (Button) findViewById(R.id.button);
        //1.得到TypeArray 2.通过TypeArray得到值 3.TypeArray.recycle
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Demo1_mySelfView);
        //然后就是如何得到里面的属性了
        if (typedArray != null) {
            //这里改变一下toast的输出
//            Toast toast = Toast.makeText(context, typedArray.getString(R.styleable.Demo1_mySelfView_TextViewText) + "--" + typedArray.getDimension(R.styleable.Demo1_mySelfView_ButtonSize, 18), Toast.LENGTH_LONG);
//            toast.setGravity(Gravity.CENTER, 10, 20);
//            toast.show();
            btn.setText(typedArray.getString(R.styleable.Demo1_mySelfView_ButtonText));
            btn.setTextSize(typedArray.getDimension(R.styleable.Demo1_mySelfView_ButtonSize, 18));
            tv.setText(typedArray.getString(R.styleable.Demo1_mySelfView_TextViewText));
            tv.setTextSize(typedArray.getDimension(R.styleable.Demo1_mySelfView_TextViewSize, 18));
        }
        typedArray.recycle();
        tv.setOnClickListener(this);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv:
                showToast("TextView被点击了");
//                Toast.makeText(context, "TextView被点击了", Toast.LENGTH_LONG).show();
            case R.id.button:
                showToast("Button被点击了");
//                Toast.makeText(context, "Button被点击了", Toast.LENGTH_LONG).show();
        }
    }

    private void showToast(String str) {
        //顺便看看LayoutInflater的3种获取方法  都是调用了我的这个方法啊
//        LayoutInflater inflater = LayoutInflater.from(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.toast_layout,
                (ViewGroup) findViewById(R.id.toast_layout_root));

        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(str);

        Toast toast = new Toast(context.getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);     //主要在这里setView试图哈哈
        toast.show();
    }
}
