package com.lion.lazyfragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.TextUtils;

/**
 * implementation of fragment factory
 *
 * @author lion
 */
public class FragmentFactoryImpl implements FragmentFactory {

    @Override
    @Nullable
    public Fragment createFragment(String name) {
        if (TextUtils.equals(name, FragmentA.class.getSimpleName())) {
            return new FragmentA();
        } else if (TextUtils.equals(name, FragmentB.class.getSimpleName())) {
            return new FragmentB();
        } else if (TextUtils.equals(name, FragmentC.class.getSimpleName())) {
            return new FragmentC();
        }
        return null;
    }

}
