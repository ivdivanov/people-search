package net.isbg.people.service;

import net.isbg.people.entity.Addresses;

public interface AddressesService {
    void saveAddress(Long personId, Addresses address);
    void updateAddress(Long PersonId, Addresses address);
    void deleteAddress(Long addressId);
}
