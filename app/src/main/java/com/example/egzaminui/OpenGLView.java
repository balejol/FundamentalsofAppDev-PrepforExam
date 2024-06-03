package com.example.egzaminui;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class OpenGLView extends GLSurfaceView {
    public OpenGLView(Context context) {
        super(context);
        init();
    }

    public OpenGLView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setEGLContextClientVersion(3);

        Renderer = new OpenGLRender();
        setRenderer((GLSurfaceView.Renderer) Renderer);

        Renderer.setAnimationFlag(animationFlag);

        if (!getAnimationFlag()) {
            setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        } else {
            setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        }
    }

    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private OpenGLRender Renderer;
    private float PreviousX;
    private float PreviousY;
    private boolean animationFlag = false;

    public void setAnimationFlag(boolean animationFlag) {
        this.animationFlag = animationFlag;
        Renderer.setAnimationFlag(animationFlag);
        if (!getAnimationFlag()) {
            setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        } else {
            setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        }
    }

    public boolean getAnimationFlag() {
        return animationFlag;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (!getAnimationFlag()) {
            float x = e.getX();
            float y = e.getY();
            switch (e.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    float dx = x - PreviousX;
                    float dy = y - PreviousY;
                    if (y > getHeight() / 2) { dx = dx * -1; }
                    if (x < getWidth() / 2) { dy = dy * -1; }
                    Renderer.setAngle(Renderer.getAngle() + ((dx + dy) * TOUCH_SCALE_FACTOR));
                    requestRender();
            }
            PreviousX = x;
            PreviousY = y;
        }
        return true;
    }
}