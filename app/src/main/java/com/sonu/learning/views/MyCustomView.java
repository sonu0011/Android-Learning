package com.sonu.learning.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyCustomView extends View {

    private Paint paint;

    private final int faceColor = Color.YELLOW;
    private final int eyesColor = Color.BLACK;
    private final int mouthColor = Color.BLACK;
    private final int borderColor = Color.BLACK;
    // Face border width in pixels
    private final float borderWidth = 4.0f;
    // View size in pixels
    private final int size = 320;



    public MyCustomView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawFace(canvas);

        drawEyes(canvas);

        drawMouth(canvas);
    }

    private void drawFace(Canvas canvas) {
        paint.setColor(faceColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(size/2f , size /2f , size/2f , paint);
    }

    private void drawEyes(Canvas canvas) {
    }

    private void drawMouth(Canvas canvas) {
    }
}
