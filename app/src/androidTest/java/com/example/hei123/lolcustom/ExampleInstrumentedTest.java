package com.example.hei123.lolcustom;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.hei123.lolcustom.Helper.NewsHelper;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.hei123.lolcustom", appContext.getPackageName());
    }
    @Test
    public void test_isWork() throws Exception{
        //NewsHelper.writeFileCache("ceshi","这是测试用fsdafsagastrwetgsdztdsfa的文本");
        //NewsHelper.writeFileCache2("ceshi","这是测试用的文本");
        //String ceshi = NewsHelper.readFileByChars("ceshi");
        //Log.i("tag",ceshi);
    }
}
