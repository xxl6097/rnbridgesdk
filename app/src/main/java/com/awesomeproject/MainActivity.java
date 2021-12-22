package com.awesomeproject;

import com.facebook.react.ReactActivity;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class MainActivity extends ReactActivity {

  /**
   * Returns the name of the main component registered from JavaScript. This is used to schedule
   * rendering of the component.
   */
  @Override
  protected String getMainComponentName() {
//    Callback callback = null;
//    callback.invoke(0,"sucess");
    return "AwesomeProject";
  }

  public void test(){
    ReactContextBaseJavaModule module;
  }
}
