package com.lion.lazyfragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * lazy load target fragment
 *
 * @author lion
 */
public class LazyFragment extends Fragment {

    private static final String TAG = LazyFragment.class.getSimpleName();
    protected FragmentFactory fragmentFactory;
    protected String fragmentName;
    private boolean isInit = false;//whether iew finish init
    private View view;
    //    private FrameLayout fragmentContainer;
    private TextView loadingTv;
    private Fragment fragment;

    public static LazyFragment newInstance(FragmentFactory fragmentFactory, String fragmentName) {
        LazyFragment fragment = new LazyFragment();
        fragment.fragmentFactory = fragmentFactory;
        fragment.fragmentName = fragmentName;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lazy_loading, container, false);
//        fragmentContainer = view.findViewById(R.id.fragment_container);
        loadingTv = view.findViewById(R.id.tip_loading);
        Log.i(TAG, "onCreateView getUserVisibleHint=" + getUserVisibleHint() + ", isInit=" +
                isInit);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //two cases load target fragment：1.page resume from system recycling；2.page visible and not init
        if (savedInstanceState != null || getUserVisibleHint() && !isInit) {
            lazyLoad();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i(TAG, "setUserVisibleHint() called with: " + "isVisibleToUser=" + isVisibleToUser +
                ", isInit=" + isInit);
        //if isVisibleToUser==true and isInit==false -> load target fragment
        if (isVisibleToUser && !isInit && view != null) {
            lazyLoad();
        }

        if (fragment != null) {
            fragment.setUserVisibleHint(isVisibleToUser);
        }
    }

    public void refreshTaskCenterFragment() {}

    private void lazyLoad() {
        if (!isAdded() || !isVisible() || isDetached()) {
            return;
        }
//        if (fragmentContainer != null) {
//            fragmentContainer.removeAllViews();
//        }
        if (fragmentFactory == null) {
            return;
        }

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        fragment = fragmentFactory.createFragment(fragmentName);
        if (transaction != null && fragment != null) {
            try {
                transaction.replace(R.id.fragment_container, fragment, fragmentName).runOnCommit(hideLoadingTvRunnable).commit();
                isInit = true;
            } catch (Exception e) {
                Log.e(TAG, e.getLocalizedMessage());
            }
        }

    }

    private Runnable hideLoadingTvRunnable = new Runnable() {
        @Override
        public void run() {
            loadingTv.setVisibility(View.GONE);
            Log.i(TAG, "hideLoadingRunnable run()");
        }
    };

    public String getFragmentName() {
        return fragmentName;
    }

}
