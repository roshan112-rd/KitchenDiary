package com.example.kitchendiary;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class DetailActivityTest {

    @Rule
    public ActivityTestRule<DetailActivity> detailActivityTestRule = new ActivityTestRule <DetailActivity>(DetailActivity.class);
    private DetailActivity dActivity = null;
    @Before
    public void setUp() throws Exception {
        dActivity=detailActivityTestRule.getActivity();
    }
    @Test
    public void testLaunch(){
        View view = dActivity.findViewById(R.id.ivImage2);
        assertNotNull(view);
    }
    @Test
    public void testLaunch2(){
        View view = dActivity.findViewById(R.id.txtName);
        assertNotNull(view);
    }
    @Test
    public void testLaunch3(){
        View view = dActivity.findViewById(R.id.txtTime);
        assertNotNull(view);
    }
    @Test
    public void testLaunch4(){
        View view = dActivity.findViewById(R.id.txtDescription);
        assertNotNull(view);
    }
    @Test
    public void testLaunch5(){
        View view = dActivity.findViewById(R.id.edit_text_input);
        assertNotNull(view);
    }
    @Test
    public void testLaunch6(){
        View view = dActivity.findViewById(R.id.button_set);
        assertNotNull(view);
    }
    @Test
    public void testLaunch7(){
        View view = dActivity.findViewById(R.id.edit_text_input);
        assertNotNull(view);
    }
    @Test
    public void testLaunch8(){
        View view = dActivity.findViewById(R.id.text_view_countdown);
        assertNotNull(view);
    }
    @Test
    public void testLaunch9(){
        View view = dActivity.findViewById(R.id.button_start_pause);
        assertNotNull(view);
    }
    @Test
    public void testLaunch10(){
        View view = dActivity.findViewById(R.id.button_reset);
        assertNotNull(view);
    }
    @After
    public void tearDown() throws Exception {
        dActivity=null;
    }

}