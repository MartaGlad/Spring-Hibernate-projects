package com.gladysz.beanlifecycle;

import com.gladysz.beanlifecycle.bean.LibraryManager;
import com.gladysz.beanlifecycle.bean.MrBean;
import com.gladysz.beanlifecycle.configuration.AppConfiguration;
import com.gladysz.securitycommon.SecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;


@Import(SecurityConfiguration.class)
@SpringBootApplication
public class BeanLifecycleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeanLifecycleApplication.class, args);
        /*AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfiguration.class);
        context.refresh();

        MrBean mrBean = context.getBean(MrBean.class);

        //LibraryManager manager = context.getBean(LibraryManager.class);
        System.out.println("Context and beans are set up and ready to work");
        context.close();*/
    }
}
