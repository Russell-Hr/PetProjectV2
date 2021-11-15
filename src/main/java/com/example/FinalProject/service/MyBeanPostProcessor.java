package com.example.FinalProject.service;

import com.example.FinalProject.command.Timed;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import java.lang.reflect.Method;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    private static final Logger log = LogManager.getLogger(MyBeanPostProcessor.class);
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        for (Method method: bean.getClass().getMethods()) {
            if (method.isAnnotationPresent(Timed.class)) {
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(bean.getClass());
                enhancer.setCallback((MethodInterceptor) (obj, interceptedMethod, args, proxy) -> {
                    if (interceptedMethod.isAnnotationPresent(Timed.class)) {
                        StopWatch timer = new StopWatch();
                        timer.start();
                        Object result = proxy.invokeSuper(obj, args);
                        timer.stop();
                        log.info("Method called: {}, Duration: {}",
                                interceptedMethod.getName(),
                                timer.getTotalTimeMillis());
                        return result;
                    }
                    return proxy.invokeSuper(obj, args);
                });
                return enhancer.create();
            }
        }
        return bean;
    }
}
