package com.mmkjflb.mmloan.utils.view.progressbar;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import com.mmkjflb.mmloan.R;

/**
 * Created by
 * Date: On 2018/3/2
 * Author: wangjieyan
 * Email: wangjieyan9308@163.com
 */

public class ProgressbarView extends View{

    private final int DEFAULT_WIDTH = ViewGroup.LayoutParams.MATCH_PARENT;
    private final int DEFAULT_HEIGTH = 40;
    private final int DEFAULT_MAX = 100;
    private final int DEFAULT_PROGRESS = 0;
    private final int DEFAULT_NUMBER = 0;
    private final int DEFAULT_PROGRESS_COLOR = Color.parseColor("#FF6867");
    private final int DEFAULT_PROGRESS_BACK_COLOR = Color.parseColor("#EFEFEF");

    private int progress = DEFAULT_PROGRESS;
    private int max = DEFAULT_MAX;
    private float mwidth = DEFAULT_WIDTH;
    private float mhight = DEFAULT_HEIGTH;
    private int proColor = DEFAULT_PROGRESS_COLOR;
    private int proBackColor = DEFAULT_PROGRESS_BACK_COLOR;
    private int progressSegmentColor = proColor;
    private int progressDoubleSegColor = Color.GRAY;
    private int progressSegmentNumber=DEFAULT_NUMBER;
    private float startX;
    private float startY;
    private Paint paint;

    public ProgressbarView(Context context) {
        this(context, null);
    }

    public ProgressbarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressbarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        // Load the styled attributes and set their properties
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.ProgressbarView, defStyleAttr, 0);
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density = dm.density;
        mwidth = dm.widthPixels;
        //mwidth = attributes.getDimension(R.styleable.ProgressbarView_p_width, DEFAULT_WIDTH);
        mhight = attributes.getDimension(R.styleable.ProgressbarView_p_height, DEFAULT_HEIGTH);

        max = attributes.getInteger(R.styleable.ProgressbarView_p_maxValue, DEFAULT_MAX);
        progress = attributes.getInteger(R.styleable.ProgressbarView_p_progressValue, DEFAULT_PROGRESS);
        proColor = attributes.getColor(R.styleable.ProgressbarView_p_progressColor, DEFAULT_PROGRESS_COLOR);
        proBackColor = attributes.getColor(R.styleable.ProgressbarView_p_progressBackColor, DEFAULT_PROGRESS_BACK_COLOR);
        progressSegmentColor = attributes.getColor(R.styleable.ProgressbarView_p_progressSegmentColor, DEFAULT_PROGRESS_COLOR);
        progressDoubleSegColor = attributes.getColor(R.styleable.ProgressbarView_p_progressDoubleSegColor, Color.GRAY);
        progressSegmentNumber = attributes.getInteger(R.styleable.ProgressbarView_p_progressSegmentNumber, DEFAULT_PROGRESS);
        paint = new Paint();

        paint.setAntiAlias(true);
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.parseColor("#EFEFEF"));
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(proBackColor);
        paint.setStrokeWidth(10);
        startX = 0;
        startY = 0;
        //绘制progressbarView背景色
        canvas.drawRect(startX, startY, mwidth, mhight, paint);
        //绘制progressbarView进度背景色
        paint.setColor(proColor);
        canvas.drawRect(startX, startY, ((float) progress / max) * mwidth,
                mhight, paint);
        //绘制progressbarView分隔线颜色
        paint.setStrokeWidth(1);
        paint.setColor(progressDoubleSegColor);
        if (progressSegmentNumber> 0) {
            for (int i = 1; i <progressSegmentNumber; i++) {
                canvas.drawLine(((float) i/progressSegmentNumber) * mwidth, startY,
                        ((float) i/progressSegmentNumber) * mwidth, startY +
                                (mhight - startY), paint);
            }
        }
    }


    public void setMax(int max) {
        this.max =max;
    }
    //
    public void setProgress(int lenght) {
        for(int i=0;i<lenght;i++){
            if (progress <max) {
                this.progress +=max/(progressSegmentNumber);
                invalidate();
            }
        }
    }
    public void removeProgress() {
        for(int i=0;i<4;i++) {
            if (progress > 0) {
                this.progress -= max / (progressSegmentNumber);
                invalidate();
            }
        }
    }
}
