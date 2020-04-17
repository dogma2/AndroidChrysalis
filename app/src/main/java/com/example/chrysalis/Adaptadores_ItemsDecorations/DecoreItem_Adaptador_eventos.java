package com.example.chrysalis.Adaptadores_ItemsDecorations;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class DecoreItem_Adaptador_eventos extends RecyclerView.ItemDecoration {
    private int Space;

    public DecoreItem_Adaptador_eventos(int space) {
        Space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //outRect.left = Space;
        //outRect.right = Space;
        outRect.bottom = Space;
    }
}
