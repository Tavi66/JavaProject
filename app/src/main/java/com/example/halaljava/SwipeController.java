package com.example.halaljava;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import com.example.halaljava.database.FinanceAdapter;

import static android.support.v7.widget.helper.ItemTouchHelper.*;

enum ButtonState {
    NOT_VISIBLE,
    EDIT_VISIBLE,
    DELETE_VISIBLE,
    VISIBLE
}

class SwipeController extends Callback {

    private boolean swipeBack;
    private static final float buttonWidth = 300;
    private RecyclerView.ViewHolder  currentItemViewHolder = null;
    private ButtonState buttonDisplayState = ButtonState.VISIBLE;
    private RectF editButton;
    private RectF deleteButton;

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, LEFT | RIGHT);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView,RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive)
    {
        FinanceAdapter.FinViewHolder holder = (FinanceAdapter.FinViewHolder) viewHolder;
        holder.itemView.setTranslationX(dX);

        if (actionState == ACTION_STATE_SWIPE) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        } else
            setOnTouchListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        currentItemViewHolder = viewHolder;
        //drawButtons(c,currentItemViewHolder);
    }

    @Override
    public int convertToAbsoluteDirection(int flags,int layoutDirection) {
        if(swipeBack)
        {
            swipeBack = false;
            return 0;
        }
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    @SuppressLint("ClickableViewAccessibility")
    public void setOnTouchListener(Canvas c, RecyclerView recyclerView, final RecyclerView.ViewHolder viewHolder, final float dX, float dY, int actionState, boolean isCurrentlyActive)
    {
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                swipeBack = event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_UP;
                if(swipeBack)
                {
                    //
                }

                swipeBack = false;
                currentItemViewHolder = null;
                return false;
            }
        });
    }

    public void onDraw(Canvas c) {
        if(currentItemViewHolder != null)
        {
            //drawButtons(c, currentItemViewHolder);
        }
    }

    public void drawButtons(Canvas c, RecyclerView.ViewHolder viewHolder) {
        Paint red = new Paint();
        red.setColor(Color.RED);
        View view = viewHolder.itemView;
        RectF deleteButton = new RectF(view.getLeft(),view.getTop(),view.getLeft() + buttonWidth, view.getBottom());
        if(buttonDisplayState == ButtonState.VISIBLE)
        c.drawRect(deleteButton, red);
    }
}

