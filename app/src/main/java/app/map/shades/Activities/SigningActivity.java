package app.map.shades.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import app.map.shades.R;
import app.map.shades.Fragments.SigninFragment;
import app.map.shades.Fragments.SignupFragment;
import app.map.shades.Adapters.TabAdapterTitle;

public class SigningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signing);

        TabAdapterTitle tabAdapter = new TabAdapterTitle(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        tabAdapter.addFragment(new SigninFragment(), "SIGN IN");
        tabAdapter.addFragment(new SignupFragment(), "SIGN UP");
        ViewPager signViewPager = findViewById(R.id.signViewPager);
        signViewPager.setAdapter(tabAdapter);

        TabLayout signTabLayout = findViewById(R.id.signTabLayout);
        signTabLayout.setupWithViewPager(signViewPager);
    }
}