package com.gladysz.beanlifecycle.controller;

import com.gladysz.beanlifecycle.bean.MrBean;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/bean-lifecycle")
public class BeanLifecycleController {

    private final ApplicationContext context;

    public BeanLifecycleController(ApplicationContext context) {
        this.context = context;
    }


    @GetMapping("/bean")
    public String createBean() {

        MrBean mrBean = context.getBean(MrBean.class);

        return mrBean.present();
    }
}
