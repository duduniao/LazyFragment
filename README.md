# LazyFragment
android lazy fragment in FragmentStatePagerAdapter, use a simple nested approach

### Usage

first:
implement your FragmentFactoryImpl

second:
use it in FragmentStatePagerAdapter:

    @Override
    public Fragment getItem(int index) {

        Fragment fragment = null;
        if (index == 0) {
            fragment = new FragmentA();
        } else if (index == 1) {
            fragment = LazyFragment.newInstance(fragmentFactory, FragmentB.class
                    .getSimpleName());
        }
        return fragment;
    }

