package net.isbg.people.web;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.isbg.people.entity.Addresses;
import net.isbg.people.service.AddressesService;


@AllArgsConstructor
@RestController
@RequestMapping("/addresses")
public class AddressesController {

    AddressesService addressesService;

    @PostMapping
    public ResponseEntity<Address> saveAddress(@RequestParam Long personId, @Valid @RequestBody  Addresses address) {
        addressesService.saveAddress(personId, address);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Address> updateAddress(@RequestParam Long personId, @Valid @RequestBody Addresses address) {
        addressesService.updateAddress(personId, address);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Address> deleteAddress(@RequestParam Long addressId) {
        addressesService.deleteAddress(addressId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
