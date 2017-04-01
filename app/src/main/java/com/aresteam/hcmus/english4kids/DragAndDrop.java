package com.aresteam.hcmus.english4kids;

import android.content.ClipData;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Wolf on 23-Jun-16.
 */
public class DragAndDrop {
    public static class ImageTouchListener implements View.OnTouchListener{

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                    v.startDrag(data, shadowBuilder, v, 0);
                    v.setVisibility(View.INVISIBLE);
                    break;

                default:
                    return false;
            }
            return true;
        }
    }

    public static class LayoutDragListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch(event.getAction()) {
                case DragEvent.ACTION_DROP:

                    ImageView droppedImage = (ImageView) event.getLocalState();

                    int x = (int) (event.getX() - droppedImage.getWidth()/2);
                    int y = (int) (event.getY() - droppedImage.getHeight()/2);

                    ViewGroup parent = (ViewGroup) droppedImage.getParent();

                    if(parent != null) {
                        parent.removeView(droppedImage);
                        ((ViewGroup)v).addView(droppedImage);

                        droppedImage.setX(x);
                        droppedImage.setY(y);
                        droppedImage.setVisibility(View.VISIBLE);
                    }
                    break;
                default:
                    break;
            }
            return true;
        }
    }

    public static class ImageHolderDragListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch(event.getAction()) {
                case DragEvent.ACTION_DROP:
                    ViewGroup imageHolder = (ViewGroup) v;

                    ImageView droppedImage = (ImageView) event.getLocalState();
                    float x = droppedImage.getX();
                    float y = droppedImage.getY();

                    ViewGroup droppedImageParent = (ViewGroup) droppedImage.getParent();

                    if(droppedImageParent != null) {
                        droppedImageParent.removeView(droppedImage);

                        //Frame is holding an ImageView
                        if(imageHolder.getChildCount() == 1) {
                            ImageView swapImage = (ImageView) imageHolder.getChildAt(0);
                            imageHolder.removeView(swapImage);
                            droppedImageParent.addView(swapImage);
                            swapImage.setX(x);
                            swapImage.setY(y);
                        }

                        imageHolder.addView(droppedImage);
                        float newX = (imageHolder.getLayoutParams().width - droppedImage.getLayoutParams().width)/2;
                        float newY = (imageHolder.getLayoutParams().height - droppedImage.getLayoutParams().height)/2;

                        droppedImage.setX(newX);
                        droppedImage.setY(newY);

                        droppedImage.setVisibility(View.VISIBLE);
                    }


                    break;
                default:
                    break;
            }
            return true;
        }
    }
}
