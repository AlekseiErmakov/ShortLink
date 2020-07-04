package com.app;

import com.app.resource.AppResource;
import com.app.resource.MyResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class RestConfig extends Application {
    public Set<Class<?>> getClasses() {
        return new HashSet<Class<?>>(
                Arrays.asList(MyResource.class, AppResource.class));
    }
}