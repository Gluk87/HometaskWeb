package mypackage.test;

import mypackage.app.Application;
import org.junit.After;
import org.junit.Before;

public class BaseRunner {
    private ThreadLocal<Application> tlApp = new ThreadLocal<>();
    Application app;

    @Before
    public void setUp() {
        if (tlApp.get() != null) {
            app = tlApp.get();
            return;
        }
        app = new Application();
        tlApp.set(app);
    }

    @After
    public void tearDown() {
        app.quit();
    }
}
