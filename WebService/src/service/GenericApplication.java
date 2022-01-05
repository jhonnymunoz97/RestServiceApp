package service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("resources")
public class GenericApplication extends Application {
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();

        // Register root resources.
        classes.add(ProductImp.class);
        classes.add(CustomerImp.class);
        classes.add(OrderImp.class);
        classes.add(PersonaImp.class);
        classes.add(OrderDetailImp.class);

        // Register provider classes.

        return classes;
    }
}
