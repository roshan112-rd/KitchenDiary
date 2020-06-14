package com.example.kitchendiary;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class SignUpTest {
    @Rule
    public ActivityTestRule<SignUp> signUpTestRule = new ActivityTestRule <SignUp>(SignUp.class);
    private SignUp signUp = null;

    @Before
    public void setUp() throws Exception {
        signUp=signUpTestRule.getActivity();
    }
    @Test
    public void testLaunch(){
        View view = signUp.findViewById(R.id.txt_email);
        assertNotNull(view);
    }

    @Test
    public void testLaunch2(){
        View view = signUp.findViewById(R.id.txt_password);
        assertNotNull(view);
    }

    @Test
    public void testLaunch3(){
        View view = signUp.findViewById(R.id.txt_password2);
        assertNotNull(view);
    }
    @Test
    public void testLaunch4(){
        View view = signUp.findViewById(R.id.regButton);
        assertNotNull(view);
    }
    @Test
    public void testLaunch5(){
        View view = signUp.findViewById(R.id.logbtn);
        assertNotNull(view);
    }
    @Test
    public void testLaunch6(){
        View view = signUp.findViewById(R.id.logo);
        assertNotNull(view);
    }
    @Test
    public void testLaunch7(){
        View view = signUp.findViewById(R.id.logo2);
        assertNotNull(view);
    }
    @After
    public void tearDown() throws Exception {
        signUp=null;
    }
}