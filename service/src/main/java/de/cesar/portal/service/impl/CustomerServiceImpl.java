package de.cesar.portal.service.impl;

import de.cesar.portal.domain.Customer;
import de.cesar.portal.service.api.CustomerService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author fatabey
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public List<Customer> findAllCustomers() {
        return Arrays.asList(new Customer("Lehel"), new Customer("Blutenburg"));
    }
}
