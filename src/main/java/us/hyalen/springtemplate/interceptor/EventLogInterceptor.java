package us.hyalen.springtemplate.interceptor;

import org.hibernate.EmptyInterceptor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class EventLogInterceptor extends EmptyInterceptor implements ApplicationContextAware, ApplicationListener<ContextRefreshedEvent> {
    private ApplicationContext applicationContext;

    protected EventLogInterceptor() throws Exception {
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.applicationContext = context;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

    }
}