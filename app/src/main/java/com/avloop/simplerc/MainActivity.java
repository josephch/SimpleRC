/*
 * Copyright 2016 Christo (christo.programmer@gmail.com)
 * This file is part of SimpleRC.
 *

 * SimpleRC is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.

 * FileOrganizer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with FileOrganizer.  If not, see <http://www.gnu.org/licenses/>.
 */


package com.avloop.simplerc;

import android.hardware.ConsumerIrManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.avloop.simplerc.remotes.Remote;
import com.avloop.simplerc.remotes.TataSkyRemote;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="MainActivity";
    private static Remote remote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConsumerIrManager mCIR = (ConsumerIrManager)getSystemService(CONSUMER_IR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Log.e(TAG, "mCIR.hasIrEmitter(): " + mCIR.hasIrEmitter());
        }
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        remote = new TataSkyRemote(mCIR);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);


        viewPager.setAdapter(new SectionPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        /*Button fab = (Button) findViewById(R.id.power);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remote.sentIR(Keys.POWER);
            }
        });
        */
    }
    class SectionPagerAdapter extends FragmentPagerAdapter {

        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new FragmentMain();
                case 1:
                default:
                    return new NumberAndColorsFragment();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Main";
                case 1:
                default:
                    return "Num/Col";
            }
        }
    }
    public void buttonClicked( View view) {
        int id = view.getId();
        int keyCode = -1;
        switch (id) {
            /*Main keys*/
            case R.id.power:
                keyCode = Keys.POWER;
                break;
            case R.id.guide:
                keyCode = Keys.GUIDE;
                break;
            case R.id.chup:
                keyCode = Keys.CHUP;
                break;
            case R.id.chdown:
                keyCode = Keys.CHDOWN;
                break;
            case R.id.up:
                keyCode = Keys.UP;
                break;
            case R.id.down:
                keyCode = Keys.DOWN;
                break;
            case R.id.left:
                keyCode = Keys.LEFT;
                break;
            case R.id.right:
                keyCode = Keys.RIGHT;
                break;
            case R.id.select:
                keyCode = Keys.SELECT;
                break;
            case R.id.back:
                keyCode = Keys.BACK;
                break;
            case R.id.rew:
                keyCode = Keys.REW;
                break;
            case R.id.ffw:
                keyCode = Keys.FF;
                break;
            case R.id.play:
                keyCode = Keys.PLAY;
                break;
            case R.id.pause:
                keyCode = Keys.PAUSE;
                break;
            case R.id.tvon:
                keyCode = Keys.TV_POWER;
                break;
            case R.id.planner:
                keyCode = Keys.PLANNER;
                break;
            case R.id.record:
                keyCode = Keys.REC;
                break;
            case R.id.livetv:
                keyCode = Keys.LIVETV;
                break;
            case R.id.info:
                keyCode = Keys.INFO;
                break;

            /*Numbers*/
            case R.id.no0:
                keyCode = 0;
                break;
            case R.id.no1:
                keyCode = 1;
                break;
            case R.id.no2:
                keyCode = 2;
                break;
            case R.id.no3:
                keyCode = 3;
                break;
            case R.id.no4:
                keyCode = 4;
                break;
            case R.id.no5:
                keyCode = 5;
                break;
            case R.id.no6:
                keyCode = 6;
                break;
            case R.id.no7:
                keyCode = 7;
                break;
            case R.id.no8:
                keyCode = 8;
                break;
            case R.id.no9:
                keyCode = 9;
                break;
            /*Color keys*/
            case R.id.red:
                keyCode = Keys.RED;
                break;
            case R.id.green:
                keyCode = Keys.GREEN;
                break;
            case R.id.blue:
                keyCode = Keys.BLUE;
                break;
            case R.id.yellow:
                keyCode = Keys.YELLOW;
                break;
            default:
                Log.w(TAG, "Not supported button :"+view);
                break;
        }
        if ( keyCode != -1) {
            remote.sentIR(keyCode);
        }
        else {
            Log.w( TAG, "Not sending IR");
        }
    }
}
