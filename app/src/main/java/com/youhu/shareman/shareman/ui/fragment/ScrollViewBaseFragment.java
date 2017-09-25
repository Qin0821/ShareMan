package com.youhu.shareman.shareman.ui.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.youhu.shareman.shareman.ui.widget.ISlideCallback;

/**
 * <b>Project:</b> SlideDetailsLayout<br>
 * <b>Create Date:</b> 16/1/25<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class ScrollViewBaseFragment extends Fragment {

    private ISlideCallback mISlideCallback;

    public ScrollViewBaseFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof ISlideCallback)) {
            throw new IllegalArgumentException("Your activity must be implements ISlideCallback");
        }
        mISlideCallback = (ISlideCallback) context;
    }

    protected void open(boolean smooth) {
        mISlideCallback.openDetails(smooth);
    }

    protected void close(boolean smooth) {
        mISlideCallback.closeDetails(smooth);
    }

}
