package tw.com.test;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PageAdapter extends FragmentStateAdapter {
    accountFragment f1;
    transferFragment f2;
    billFragment f3;
    forexFragment f4;
    setupFragment f5;
    private String account;
    private UserData userData;

    public PageAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("account", account);
        bundle.putParcelable("UserData", userData);
        //position當前ViewPage編號
        switch (position) {
            case 0:
                f1 = new accountFragment();
                f1.setArguments(bundle);
                return f1;
            case 1:
                f2 = new transferFragment();
                f2.setArguments(bundle);
                return f2;
            case 2:
                f3 = new billFragment();
                f3.setArguments(bundle);
                return f3;
            case 3:
                f4 = new forexFragment();
                f4.setArguments(bundle);
                return f4;
            case 4:
                f5 = new setupFragment();
                f5.setArguments(bundle);
                return f5;
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
