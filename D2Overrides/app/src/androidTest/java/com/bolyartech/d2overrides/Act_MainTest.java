package com.bolyartech.d2overrides;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.bolyartech.d2overrides.dagger.DaggerMyDaggerComponent;
import com.bolyartech.d2overrides.dagger.HttpModule;
import com.bolyartech.d2overrides.dagger.MyDaggerComponent;
import com.bolyartech.d2overrides.utils.HttpMockUtils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import okhttp3.OkHttpClient;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class Act_MainTest {
    private static final String EXPECTED_RESPONSE_BODY = "unit test";

    @Rule
    public ActivityTestRule<Act_Main> mActivityRule = new ActivityTestRule<Act_Main>(
            Act_Main.class) {


        @Override
        protected void beforeActivityLaunched() {
            super.beforeActivityLaunched();

            OkHttpClient mockHttp = HttpMockUtils.createWithResponseBody(EXPECTED_RESPONSE_BODY);

            MyDaggerComponent injector = DaggerMyDaggerComponent.
                    builder().httpModule(new HttpModule(mockHttp)).build();

            MyApp app = (MyApp) InstrumentationRegistry.getInstrumentation().
                    getTargetContext().getApplicationContext();

            app.externalInjectorInitialization(injector);

        }
    };


    @Test
    public void testHttpRequest() throws IOException {
        onView(withId(R.id.btn_execute)).perform(click());

        onView(withId(R.id.tv_result))
                .check(matches(withText(EXPECTED_RESPONSE_BODY)));

    }
}
