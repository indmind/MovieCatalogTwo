package com.indmind.moviecataloguetwo;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.indmind.moviecataloguetwo.adapters.SectionStatePagerAdapter;
import com.indmind.moviecataloguetwo.fragments.FavoriteFragment;
import com.indmind.moviecataloguetwo.fragments.MoviesFragment;
import com.indmind.moviecataloguetwo.fragments.TvShowFragment;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.nav_bottom)
    BottomNavigationView bottomNavigation;
    @BindView(R.id.view_container)
    ViewPager viewPager;

    @BindString(R.string.movies)
    String movie;
    @BindString(R.string.tv_show)
    String tvShow;
    @BindString(R.string.favorite)
    String favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setTitle(movie);
        setupViewPager(viewPager);

        bottomNavigation.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.nav_movies:
                    viewPager.setCurrentItem(0, true);
                    return true;
                case R.id.nav_tv_show:
                    viewPager.setCurrentItem(1, true);
                    return true;
                case R.id.nav_favorite:
                    viewPager.setCurrentItem(2, true);
                    return true;
                case R.id.nav_lang_setting:
                    Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);

                    startActivity(intent);
                    return true;
            }

            return false;
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        setTitle(movie);
                        bottomNavigation.findViewById(R.id.nav_movies).performClick();
                        break;
                    case 1:
                        setTitle(tvShow);
                        bottomNavigation.findViewById(R.id.nav_tv_show).performClick();
                        break;
                    case 2:
                        setTitle(favorite);
                        bottomNavigation.findViewById(R.id.nav_favorite).performClick();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionStatePagerAdapter adapter = new SectionStatePagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new MoviesFragment());
        adapter.addFragment(new TvShowFragment());
        adapter.addFragment(new FavoriteFragment());

        viewPager.setAdapter(adapter);
    }

    public void setTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }
}
