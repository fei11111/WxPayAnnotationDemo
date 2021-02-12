package com.fei.wxpay.processor;

import com.fei.wxpay.annotation.WxPay;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

/**
 * @ClassName: WxPayVisitor
 * @Description: java类作用描述
 * @Author: Fei
 * @CreateDate: 2021-02-12 18:00
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-02-12 18:00
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class WxPayVisitor extends SimpleAnnotationValueVisitor7<Filer, Void> {

    private String mPackageName;
    private TypeMirror mTypeMirror;

    public WxPayVisitor(Filer defaultValue) {
        super(defaultValue);
    }

    /**
     * 获取packageName
     *
     * @param s
     * @param aVoid
     * @return
     */
    @Override
    public Filer visitString(String s, Void aVoid) {
        mPackageName = s;
        System.out.println("packageName = " + mPackageName);
        return super.visitString(s, aVoid);
    }

    /**
     * 获取类
     *
     * @param t
     * @param aVoid
     * @return
     */
    @Override
    public Filer visitType(TypeMirror t, Void aVoid) {
        mTypeMirror = t;
        System.out.println("TypeMirror = " + t.toString());
        //创建类文件
        createType();
        return super.visitType(t, aVoid);
    }

    private void createType() {
        TypeName typeName = TypeName.get(mTypeMirror);
        TypeSpec.Builder typeSpec = TypeSpec.classBuilder("WxPayActivity")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .superclass(typeName);
        try {
            JavaFile.builder(mPackageName+".wxapi", typeSpec.build()).build().writeTo(DEFAULT_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
