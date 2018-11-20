package lion.lazy;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import lion.lazy.lib.FragmentFactory;
import lion.lazy.lib.LazyFragment;

import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mViewPager.setCurrentItem(0, false);
                    return true;
                case R.id.navigation_dashboard:
                    mViewPager.setCurrentItem(1, false);
                    return true;
                case R.id.navigation_notifications:
                    mViewPager.setCurrentItem(2, false);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.viewpager);
        HomeFragmentPageAdapter adapter = new HomeFragmentPageAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(3);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    private class HomeFragmentPageAdapter extends FragmentStatePagerAdapter {

        FragmentFactory fragmentFactory;

        public HomeFragmentPageAdapter(FragmentManager fm) {
            super(fm);
            fragmentFactory = new FragmentFactoryImpl();
        }

        @Override
        public Fragment getItem(int index) {

            Fragment fragment = null;
            if (index == 0) {
                fragment = new FragmentA();
            } else if (index == 1) {
                    fragment = LazyFragment.newInstance(fragmentFactory, FragmentB.class
                            .getSimpleName());
            } else if (index == 2) {
                    fragment = LazyFragment.newInstance(fragmentFactory, FragmentC.class
                            .getSimpleName());
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
