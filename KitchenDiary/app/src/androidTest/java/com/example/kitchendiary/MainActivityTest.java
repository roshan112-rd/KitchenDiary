package com.example.kitchendiary;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule <MainActivity>(MainActivity.class);
    private MainActivity mActivity = null;
    @Before
    public void setUp() throws Exception {
        mActivity=mainActivityTestRule.getActivity();
    }
    @Test
    public void testLaunch(){
        View view = mActivity.findViewById(R.id.txt_searchText);
        assertNotNull(view);
    }
    @Test
    public void testLaunch2(){
        View view = mActivity.findViewById(R.id.texy);
        assertNotNull(view);
    }
    @Test
    public void testLaunch3(){
        View view = mActivity.findViewById(R.id.recyclerView);
        assertNotNull(view);
    }
    @After
    public void tearDown() throws Exception {
        mActivity=null;
    }

}