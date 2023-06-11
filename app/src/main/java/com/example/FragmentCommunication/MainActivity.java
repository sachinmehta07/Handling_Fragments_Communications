package com.example.FragmentCommunication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.hnadlingfragmentscommunications.R;

public class MainActivity extends AppCompatActivity {
    private FrameLayout frameLayout;
    private Button btnFrag1, btnFrag2;
    private Fragment fragment1, fragment2;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = findViewById(R.id.frameLayout);
        btnFrag1 = findViewById(R.id.btnFrag1);
        btnFrag2 = findViewById(R.id.btnFrag2);

        fragmentManager = getSupportFragmentManager();
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();

        // Set initial fragment
        loadFragment(fragment1);

        btnFrag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(fragment1);
            }
        });

        btnFrag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(fragment2);
            }
        });
    }

    private void loadFragment(Fragment fragment) {
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.frameLayout, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();


        Fragment currentFragment = fragmentManager.findFragmentById(R.id.frameLayout);
        if (currentFragment == fragment2 && fragment == fragment1) {
            fragmentManager.popBackStackImmediate();
            updateButtonHighlight(fragment1);
            return;
        }

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

        updateButtonHighlight(fragment);

        if (fragment == fragment1) {
            btnFrag1.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
            btnFrag2.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        } else if (fragment == fragment2) {
            btnFrag1.setBackgroundColor(getResources().getColor(android.R.color.transparent));
            btnFrag2.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
        }
    }

    @Override
    public void onBackPressed() {

        if (fragmentManager.getBackStackEntryCount() > 1) {
            fragmentManager.popBackStackImmediate();
            Fragment currentFragment = fragmentManager.findFragmentById(R.id.frameLayout);
            updateButtonHighlight(currentFragment);
        } else {
            finish();
        }
        
        
//        if (fragmentManager.getBackStackEntryCount() > 1) {
//            fragmentManager.popBackStack();
//            btnFrag1.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
//            btnFrag2.setBackgroundColor(getResources().getColor(android.R.color.transparent));
//        } else {
//            finish();
//        }
    }

    private void updateButtonHighlight(Fragment fragment) {

        if (fragment == fragment1) {
            btnFrag1.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
            btnFrag2.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        } else if (fragment == fragment2) {
            btnFrag1.setBackgroundColor(getResources().getColor(android.R.color.transparent));
            btnFrag2.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
        }

    }
}
