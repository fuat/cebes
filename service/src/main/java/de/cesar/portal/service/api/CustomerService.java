package de.cesar.portal.service.api;

import de.cesar.portal.domain.Customer;

import java.util.List;

/**
 * @author fatabey
 */
public interface CustomerService {

    List<Customer> findAllCustomers();
}
