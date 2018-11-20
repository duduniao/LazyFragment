package lion.lazy.lib;

import androidx.fragment.app.Fragment;

/**
 * fragment factory interface
 *
 * @author lion
 */
public interface FragmentFactory {

    public Fragment createFragment(String name);

}
