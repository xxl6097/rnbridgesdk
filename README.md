[TOC]


# 一、Android App工程配置

## 1. app/build.gradle依赖库配置
    dependencies {
        implementation 'com.github.xxxxx:mtscensesdk:0.0.0'
    }

## 2. MainApplication类配置
    public class MainApplication extends Application implements ReactApplication {
    
        private final ReactNativeHost mReactNativeHost =
            new ReactNativeHost(this) {
              @Override
              public boolean getUseDeveloperSupport() {
                return BuildConfig.DEBUG;
            }
    
            @Override
            protected List<ReactPackage> getPackages() {
              @SuppressWarnings("UnnecessaryLocalVariable")
              List<ReactPackage> packages = new PackageList(this).getPackages();
                //添加React Native与java交互类
                packages.add(new AndroidReactPackage());
              return packages;
            }
    
            @Override
            protected String getJSMainModuleName() {
              return "index";
            }
          };
    }

## 2. MainActivity类配置

    public class MainActivity extends ReactActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
          //初始化如下代码
          AndroidApi.init(this);
          super.onCreate(savedInstanceState);
        }
    }


# 二、React Native与Java数据交互

## 1. 获取设备信息调用方式
### 1.1 获取设备所有信息：
    const getAllInfo = () => {
        NativeModules.AndroidNativeModule.invoke({methodName: 'allInfo'}, result => {
            console.log(result.mac);
            console.log(result.ip);
            console.log(result.netType);
            console.log(result.wifiName);
            ...
        });
    };

---

| 参数         | 类型     | 描述                                              |
|:-----------|:-------|-------------------------------------------------|
| methodName | String | 调用Java的函数名称                                     |
| result     | 对象     | result.code=0成功，result.code!=0失败,result.msg描述信息 |


<!--
<table style="border-spacing: 0;  border-collapse: collapse;">
    <thead style="background-color: #2FA2FA;color: #ffffff;">
    <tr>
        <th>参数</th>
        <th>类型</th>
        <th>描述</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>methodName</td>
        <th>String</th>
        <td>调用Java的函数名称</td>
    </tr>
    <tr>
        <td>result</td>
        <th>对象</th>
        <td>Java返回的数据</td>
    </tr>
    </tbody>
</table>
-->

### 1.2 单独获取设备某个参数示例
    //获取设备Mac地址
    const getDeviceMac = () => {
        NativeModules.AndroidNativeModule.invoke({methodName: 'mac'}, result => {
            console.log(result.mac);
        });
    };

### 1.3 目前支持的获取设备信息如下（注意参数大小写）
| 参数                         | 类型     | 描述              |
|:---------------------------|:-------|-----------------|
| mac                        | String | 设备mac地址         |
| netType                    | String | 网络类型            |
| wifiName                   | String | 当前连接的Wi-Fi名称    |
| model                      | String | 设备型号            |
| brand                      | String | 设备品牌            |
| rawSize                    | Double | 设备内存，单位：G       |
| storageMemory              | Double | 设备总存储空间，单位：MB   |
| availableStorageMemorySize | Double | 设备已用空间，单位：MB    |
| screenWidth                | int    | 屏幕宽度            |
| screenHeight               | int    | 屏幕高度            |
| brightness                 | int    | 屏幕亮度，范围：0～100   |
| volume                     | int    | 设备当前音量，范围：0～100 |

## 2.控制设备信息调用方式

### 2.1 设置屏幕旋转：
    //示例：设置屏幕旋转
    const setRotation = value => {
        NativeModules.AndroidNativeModule.invoke({methodName: 'setRotation', degree: value},  result => {
                console.log(result);
            },
        );
    };

| 参数          | 类型     | 描述                                                           |
|:------------|:-------|--------------------------------------------------------------|
| setRotation | String | 调用Java层旋转的函数                                                 |
| degree      | String | degree:可以有4种值（"0"，"90"，"180"，"270"），其他值默认为和"0"一样的处理方式        |
| result      | 对象     | Java函数调用结果反馈，result.code=0成功，result.code!=0失败,result.msg描述信息 |


### 2.2 设置音量：
    const setVolume =(value)=> {
        NativeModules.AndroidNativeModule.invoke({methodName: 'setVolume', volume: value},  result => {
                console.log(result);
            },
        );
    };

| 参数        | 类型     | 描述                                                           |
|:----------|:-------|--------------------------------------------------------------|
| setVolume | String | 调用Java层设置音量的函数                                               |
| volume    | int    | key值名称                                                       |
| value     | int    | 实际需要设置的音量值，取值范围0～100                                         |
| result    | 对象     | Java函数调用结果反馈，result.code=0成功，result.code!=0失败,result.msg描述信息 |


### 2.3 设置屏幕亮度：
    const setScreenBrightness =(value)=> {
        NativeModules.AndroidNativeModule.invoke({methodName: 'setScreenBrightness', brightness: value},  result => {
                console.log(result);
            },
        );
    };

| 参数                  | 类型     | 描述                                                           |
|:--------------------|:-------|--------------------------------------------------------------|
| setScreenBrightness | String | 调用Java层设置音量的函数                                               |
| brightness          | int    | key值名称                                                       |
| value               | int    | 实际需要设置的音量值，取值范围0～100                                         |
| result              | 对象     | Java函数调用结果反馈，result.code=0成功，result.code!=0失败,result.msg描述信息 |


### 2.4 关机：
    const shutdown =()=> {
        NativeModules.AndroidNativeModule.invoke({methodName: 'shutdown'},  result => {
                console.log(result);
            },
        );
    };

### 2.5 重启：
    const reboot =()=> {
        NativeModules.AndroidNativeModule.invoke({methodName: 'reboot'},  result => {
                console.log(result);
            },
        );
    };


### 2.6 开启屏背光：
    const setLCDOn =()=> {
        NativeModules.AndroidNativeModule.invoke({methodName: 'setLCDOn'},  result => {
                console.log(result);
            },
        );
    };

### 2.7 关闭屏背光：
    const setLCDOff =()=> {
        NativeModules.AndroidNativeModule.invoke({methodName: 'setLCDOff'},  result => {
                console.log(result);
            },
        );
    };

### 2.8 调整系统Wi-Fi设置界面：
    const gotoWiFi =()=> {
        NativeModules.AndroidNativeModule.invoke({methodName: 'gotoWiFi'},  result => {
                console.log(result);
            },
        );
    };

### 2.9 调整系统语言设置界面：
    const gotoWiFi =()=> {
        NativeModules.AndroidNativeModule.invoke({methodName: 'gotoLanguage'},  result => {
                console.log(result);
            },
        );
    };
