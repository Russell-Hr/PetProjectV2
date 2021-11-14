package com.example.FinalProject.service;

import com.example.FinalProject.command.Timed;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    private static final Logger log = LogManager.getLogger(MyBeanPostProcessor.class);
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Timed) {
            return Proxy.newProxyInstance(MyBeanPostProcessor.class.getClassLoader(), new Class[]{Timed.class},
                    (proxy, method, args) -> {
                        long start = System.currentTimeMillis();
                        final Object invoke = method.invoke(bean);
                        log.info("Method was invoked. Result {}", invoke);
                        long end = System.currentTimeMillis();
                        log.info("Time:", end-start);
                        return invoke;
                    });
        }
        return bean;
    }

//    @Override
//    public Class<? extends Annotation> annotationType() {
//        return null;
//    }
}
