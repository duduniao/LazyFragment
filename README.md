# LazyFragment
android lazy fragment in FragmentStatePagerAdapter, use a simple nested approach

see it at Page: https://duduniao.github.io/LazyFragment

### Using LazyFragment

---

#### Gradle

Step 1. Add the JitPack repository to your build file
    Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
    }
	
Step 2. Add the dependency

    dependencies {
        implementation 'com.github.duduniao:LazyFragment:1.0.0'
    }

---

#### Code

first:
implement your FragmentFactoryImpl:

    FragmentFactory fragmentFactory = new FragmentFactoryImpl();

second:
use LazyFragment in FragmentStatePagerAdapter:

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

