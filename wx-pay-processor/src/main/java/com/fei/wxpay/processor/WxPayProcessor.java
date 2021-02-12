package com.fei.wxpay.processor;

import com.fei.wxpay.annotation.WxPay;
import com.google.auto.service.AutoService;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/**
 * @ClassName: WxPayProcessor
 * @Description: java类作用描述
 * @Author: Fei
 * @CreateDate: 2021-02-12 14:07
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-02-12 14:07
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@AutoService(Processor.class)
public class WxPayProcessor extends AbstractProcessor {

    private Filer mFiler;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mFiler = processingEnv.getFiler();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> set = new LinkedHashSet<>();
        for (Class<? extends Annotation> clazz : getSupportAnnotation()) {
            set.add(clazz.getCanonicalName());
        }
        return set;
    }

    private Set<Class<? extends Annotation>> getSupportAnnotation() {
        Set<Class<? extends Annotation>> classes = new LinkedHashSet<>();
        classes.add(WxPay.class);
        return classes;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        //处理wxPay注解逻辑
        generationWxPay(roundEnv);


        //这里还可以写其它

        return false;
    }

    /**
     * 处理不同注解
     *
     * @param roundEnv
     * @return
     */
    private void generationWxPay(RoundEnvironment roundEnv) {
        WxPayVisitor visitor = new WxPayVisitor(mFiler);
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(WxPay.class);
        scan(elements, visitor);
    }

    private void scan(Set<? extends Element> elements, AnnotationValueVisitor visitor) {
        for (Element element : elements) {
            List<? extends AnnotationMirror> annotationMirrors =
                    element.getAnnotationMirrors();
            for (AnnotationMirror annotationMirror : annotationMirrors) {
                Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues =
                        annotationMirror.getElementValues();
                for (AnnotationValue value : elementValues.values()) {
                    value.accept(visitor,null);
                }
//                for (ExecutableElement executableElement : elementValues.keySet()) {
//                    executableElement.getDefaultValue().accept(visitor, null);
//                }
            }
        }
    }
}
