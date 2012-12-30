package de.cesar.portal.ui;

import de.cesar.portal.domain.Customer;
import de.cesar.portal.service.api.CustomerService;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;

/**
 * @author fatabey
 */
public class IndexPage extends WebPage {

    private static final long serialVersionUID = -7537193856132168209L;

    @SpringBean
    private CustomerService customerService;

    public IndexPage(PageParameters parameters) {
        super(parameters);

        add(customerList());
    }

    private Component customerList() {
        return new ListView<Customer>("customerList", customerListModel()) {
            private static final long serialVersionUID = -2537439711525479764L;

            @Override
            protected void populateItem(ListItem<Customer> item) {
                item.add(new Label("name", new PropertyModel<String>(item.getModel(),"name")));
            }
        };
    }

    private IModel<List<Customer>> customerListModel() {
        return new LoadableDetachableModel<List<Customer>>() {
            @Override
            protected List<Customer> load() {
                return customerService.findAllCustomers();
            }
        };
    }
}
