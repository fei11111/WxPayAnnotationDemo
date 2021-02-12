package com.fei.wxpay.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: WxPay
 * @Description: java类作用描述
 * @Author: Fei
 * @CreateDate: 2021-02-12 14:06
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-02-12 14:06
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface WxPay {
    //生成WxPayActivity的包名
    String packageName();

    //生成WxPayActivity所继承的类
    Class<?> activityClazz();
}
