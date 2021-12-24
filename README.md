[TOC]

圆明视界OM系统接口文档  -- ShowDoc
https://www.showdoc.com.cn/yuanshi

# 一、SDK集成

## 1. 请打开一个终端/命令提示行，进入到项目目录中（即包含有 package.json 文件的目录），然后运行下列命令来安装：

    yarn add react-native-rnbridgesdk


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


### 2.1  设置音量：

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


### 2.2 设置屏幕亮度：


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


### 2.3 设置屏幕亮度：

    const setOnOffTime = (
      year1,
      month1,
      day1,
      hour1,
      minute1,
      year2,
      month2,
      day2,
      hour2,
      minute2,
    ) => {
      NativeModules.AndroidNativeModule.invoke(
        {
          methodName: 'setOnOffTime',
          on_year: year1,
          on_month: month1,
          on_day: day1,
          on_hour: hour1,
          on_minute: minute1,
          off_year: year2,
          off_month: month2,
          off_day: day2,
          off_hour: hour2,
          off_minute: minute2,
        },
        result => {
          console.log(result);
        },
      );
    };

| 参数           | 类型     | 描述                                                           |
|:-------------|:-------|--------------------------------------------------------------|
| setOnOffTime | String | 调用Java层设置音量的函数                                               |
| on_year      | int    | 开机 年字段                                                       |
| on_month     | int    | 开机 月字段                                                       |
| on_day       | int    | 开机 日字段                                                       |
| on_hour      | int    | 开机 小时字段                                                      |
| on_minute    | int    | 开机 分钟字段                                                      |
| of_year      | int    | 关机 年字段                                                       |
| of_month     | int    | 关机 月字段                                                       |
| of_day       | int    | 关机 日字段                                                       |
| of_hour      | int    | 关机 小时字段                                                      |
| of_minute    | int    | 关机 分钟字段                                                      |
| result       | 对象     | Java函数调用结果反馈，result.code=0成功，result.code!=0失败,result.msg描述信息 |



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
