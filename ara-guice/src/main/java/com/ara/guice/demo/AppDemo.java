/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.guice.demo;

import com.ara.guice.model.DisplayInfo;
import com.ara.guice.service.DisplayService;
import com.ara.guice.service.intergration.guice.DisplayServiceDBModule;
import com.ara.guice.service.intergration.guice.DisplayServiceModule;
import com.google.common.collect.Lists;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;

import java.util.List;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-7-1 上午11:59
 */
public class AppDemo {

    private final List<Module> modules = Lists.newArrayList();

    @Inject
    private DisplayService displayService;

    public AppDemo() {
        addModule(new DisplayServiceDBModule());
        addModule(new DisplayServiceModule());
    }

    private void addModule(Module module) {
        modules.add(module);
    }

    public void run() {
        Injector injector = Guice.createInjector(modules);
        injector.injectMembers(this);

        DisplayInfo info = displayService.getInfo();
        System.out.println(info);
    }

    public static void main(String[] args) {
        new AppDemo().run();
    }

}
