package de.cesar.portal.ui;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.stereotype.Component;

/**
 * @author fatabey
 */
@Component
public class CesarWebApplication extends WebApplication {

    @Override
    public Class<? extends Page> getHomePage() {
        return IndexPage.class;
    }

    @Override
    protected void init() {
        super.init();
        initSpring();
    }

    private void initSpring() {
        getComponentInstantiationListeners().add(new SpringComponentInjector(this));
    }
}
