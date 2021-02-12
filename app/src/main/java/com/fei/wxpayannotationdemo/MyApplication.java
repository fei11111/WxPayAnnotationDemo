package com.fei.wxpayannotationdemo;

import android.app.Application;

import com.fei.wxpay.BaseWxEntryPayActivity;
import com.fei.wxpay.annotation.WxPay;

/**
 * @ClassName: MyApplication
 * @Description: java类作用描述
 * @Author: Fei
 * @CreateDate: 2021-02-12 13:55
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-02-12 13:55
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@WxPay(packageName = "com.fei.wxpayannotationdemo", activityClazz = BaseWxEntryPayActivity.class)
public class MyApplication extends Application {
}
