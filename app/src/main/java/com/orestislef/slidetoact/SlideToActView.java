package com.orestislef.slidetoact;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SlideToActView extends RelativeLayout {

    public SlideToActView(Context context) {
        super(context);
        init();
    }

    public SlideToActView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SlideToActView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.slide_to_act_view, this, true);

        TextView titleTextView = findViewById(R.id.titleTextView);
        ImageView iconImageView = findViewById(R.id.iconImageView);
        View sliderView = findViewById(R.id.sliderView);

        final Animation slideInAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in);
        final Animation slideOutAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_out);

        sliderView.setOnTouchListener(new OnTouchListener() {
            float x1, x2;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        float deltaX = x2 - x1;
                        if (deltaX > sliderView.getWidth() * 0.5) {
                            // Slide completed successfully
                            sliderView.startAnimation(slideOutAnimation);
                        } else {
                            // Slide canceled
                            sliderView.startAnimation(slideInAnimation);
                        }
                        break;
                }
                return true;
            }
        });
    }

}
