package com.bjw.togglebuttondemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/8/29 0029.
 */
public class MyToggleButton extends View {

    private int mBackgroundWidth;
    private int mBackgroundHeight;
    private int mIconWidth;
    private int mIconHeight;
    private int mMaxIconLeftWidth;
    private boolean mState = true;
    private onStateChangeListener mListener;
    private Bitmap mBackground;
    private Bitmap mIcon;
    private int mIconLeft;

    public MyToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        //String namaSpace = " http://schemas.android.com/apk/res-auto";
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable
                .MyToggleButton);

        int backgroundResid = array.getResourceId(R.styleable
                .MyToggleButton_background, -1);
        if (backgroundResid != -1) {
            setBackground(backgroundResid);
        }
        int iconResid = array.getResourceId(R.styleable.MyToggleButton_icon, -1);
        if (iconResid != -1) {
            setIcon(iconResid);
        }
        boolean attributeBooleanValue = array.getBoolean(R.styleable
                .MyToggleButton_state, true);
        setState(attributeBooleanValue);
    }

    public void setBackground(int resid) {
        mBackground = BitmapFactory.decodeResource(getResources(), resid);
        mBackgroundWidth = mBackground.getWidth();
        mBackgroundHeight = mBackground.getHeight();
        mMaxIconLeftWidth = mBackgroundWidth - mIconWidth;
    }

    public void setIcon(int resid) {
        mIcon = BitmapFactory.decodeResource(getResources(), resid);
        mIconWidth = mIcon.getWidth();
        mIconHeight = mIcon.getHeight();
        mMaxIconLeftWidth = mBackgroundWidth - mIconWidth;
    }

    public void setState(boolean state) {
        checkState(state);
        if (state) {
            mIconLeft = mBackgroundWidth - mIconWidth;
        } else {
            mIconLeft = 0;
        }
        invalidate();
    }

    private void checkState(boolean state) {
        if (mState != state) {
            mState = state;
            if (mListener != null) {
                mListener.stateChanged(mState);
            }
        }
    }


    public void setStateChangeListener(onStateChangeListener listener) {
        mListener = listener;
    }

    public interface onStateChangeListener {
        void stateChanged(boolean state);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int newLeft = (int) event.getX() - (mIconWidth / 2);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                if (event.getX() > mBackgroundWidth / 2) {
                    mIconLeft = mBackgroundWidth - mIconWidth;
                    setState(true);
                } else {
                    mIconLeft = 0;
                    setState(false);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (newLeft <= mBackgroundWidth - mIconWidth && newLeft >= 0) {
                    mIconLeft = newLeft;
                }
                break;
            default:

                break;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBackground, 0, 0, null);
        canvas.drawBitmap(mIcon, mIconLeft, 0, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mBackgroundWidth, mBackgroundHeight);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
