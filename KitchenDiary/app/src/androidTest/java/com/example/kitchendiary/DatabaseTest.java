package com.example.kitchendiary;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseTest {
    @Rule
    public ActivityTestRule<Database> databaseTestRule = new ActivityTestRule <Database>(Database.class);
    private Database database = null;
    @Before
    public void setUp() throws Exception {
        database=databaseTestRule.getActivity();
    }
    @Test
    public void testLaunch(){
        View view = database.findViewById(R.id.iv_foodImg);
        assertNotNull(view);
    }
    @Test
    public void testLaunch2(){
        View view = database.findViewById(R.id.txt_rec_name);
        assertNotNull(view);
    }
    @Test
    public void testLaunch3(){
        View view = database.findViewById(R.id.txt_description);
        assertNotNull(view);
    }
    @Test
    public void testLaunch4(){
        View view = database.findViewById(R.id.text_time);
        assertNotNull(view);
    }
    @Test
    public void testLaunch5(){
        View view = database.findViewById(R.id.selimg);
        assertNotNull(view);
    }
    @Test
    public void testLaunch6(){
        View view = database.findViewById(R.id.upbtn);
        assertNotNull(view);
    }
    @Test
    public void testLaunch7(){
        View view = database.findViewById(R.id.txt);
        assertNotNull(view);
    }
    @After
    public void tearDown() throws Exception {
        database=null;
    }

}