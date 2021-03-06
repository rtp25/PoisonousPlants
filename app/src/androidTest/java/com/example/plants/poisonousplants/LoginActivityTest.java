package com.example.plants.poisonousplants;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.example.plants.poisonousplants.View.activities.LoginActivity;
import com.example.plants.poisonousplants.View.activities.MainActivity;
import com.example.plants.poisonousplants.View.activities.RegisterActivity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * The class is used as a Test class for the Login Activity
 *
 * @author  Rogelio Paniagua
 * @version 1.0
 * @since   2019-04-06
 */
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {


    @Rule
    public ActivityTestRule<LoginActivity> loginActivityRule =
            new ActivityTestRule<>(LoginActivity.class);

    private Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(RegisterActivity.class.getName(), null, false);

    /** Tests for a valid email input
     *
     */
    @Test
    public void testValidateEmailInput_Format_True() {

        String example_email = "user1@email.com";
        boolean result  = loginActivityRule.getActivity().validateUserEmailInput(example_email);
        assertTrue(result);
    }

    /** Tests for a valid password input
     *
     */
    @Test
    public void testValidatePasswordInput_Length_True() {

        String example_password = "password";
        boolean result = loginActivityRule.getActivity().validateUserPasswordInput(example_password);
        assertTrue(result);
    }

    /** Tests for an invalid email input
     *
     */
    @Test
    public void testValidateEmailInput_Format_False() {

        loginActivityRule.getActivity().runOnUiThread(new Runnable() {


            public void run() {
                // your code to update the UI
                String example_email = "ucom";
                boolean result  = loginActivityRule.getActivity().validateUserEmailInput(example_email);
                assertFalse(result);
            }
        });

    }

    /** Tests for an invalid password input
     *
     */
    @Test
    public void testValidatePasswordInput_Length_False() {

        loginActivityRule.getActivity().runOnUiThread(new Runnable() {


            public void run() {
                String example_password = "paw";
                boolean result = loginActivityRule.getActivity().validateUserPasswordInput(example_password);
                assertFalse(result);
            }
        });

    }

    /** Tests for a valid user input
     *
     */
    @Test
    public void testValidateUserInput_True() {

        String ex_user_email = "user10@email.com";
        String ex_password = "password";
        boolean result = loginActivityRule.getActivity().isUserInputValid(ex_user_email, ex_password);
        assertTrue(result);
    }

    /** Tests for an invalid user input
     *
     */
    @Test
    public void testValidateUserInput_False() {

        loginActivityRule.getActivity().runOnUiThread(new Runnable() {

            public void run() {
                // your code to update the UI
                String ex_user_name = "use";
                String ex_password = "pass";

                boolean result = loginActivityRule.getActivity().isUserInputValid(ex_user_name, ex_password);
                assertFalse(result);

            }
        });
    }

    /** Tests for text view click event
     * to transition from Login to Register Activity
     */
    @Test
    public void testLaunchOfRegisterActivityOnTextViewClick() {

        assertNotNull(loginActivityRule.getActivity().findViewById(R.id.tv_CreateAccount));
        onView(withId(R.id.tv_CreateAccount)).perform(click());
        Activity registerActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(registerActivity);
        registerActivity.finish();

    }






}

