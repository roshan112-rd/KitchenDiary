package com.example.kitchendiary;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class LogInTest {

    @Rule
    public ActivityTestRule<LogIn> logInTestRule = new ActivityTestRule <LogIn>(LogIn.class);
    private LogIn logIn = null;
    @Before
    public void setUp() throws Exception {
        logIn=logInTestRule.getActivity();
    }
    @Test
    public void testLaunch(){
        View view = logIn.findViewById(R.id.txt_email2);
        assertNotNull(view);
    }
    @Test
    public void testLaunch2(){
        View view = logIn.findViewById(R.id.txt_password2);
        assertNotNull(view);
    }
    @Test
    public void testLaunch3(){
        View view = logIn.findViewById(R.id.sup);
        assertNotNull(view);
    }
    @Test
    public void testLaunch4(){
        View view = logIn.findViewById(R.id.btnLogin);
        assertNotNull(view);
    }
    @Test
    public void testLaunch5(){
        View view = logIn.findViewById(R.id.skip);
        assertNotNull(view);
    }
    @Test
    public void testLaunch6(){
        View view = logIn.findViewById(R.id.logo);
        assertNotNull(view);
    }
    @Test
    public void testLaunch7(){
        View view = logIn.findViewById(R.id.logo2);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        logIn=null;
    }

}