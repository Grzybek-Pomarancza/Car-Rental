package com.example.demo.service;

import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.Address;
import com.example.demo.model.Car;
import com.example.demo.model.Office;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.OfficeRepository;
import com.example.demo.validator.OfficeValidator;
import com.example.demo.validator.components.AddressValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfficeService {
    private final OfficeRepository officeRepository;
    private final OfficeValidator officeValidator;
    private final AddressValidator addressValidator;
    private final AddressRepository addressRepository;


    @Autowired
    public OfficeService(OfficeRepository officeRepository, OfficeValidator officeValidator, AddressValidator addressValidator, AddressRepository addressRepository) {
        this.officeRepository = officeRepository;
        this.officeValidator = officeValidator;
        this.addressValidator = addressValidator;
        this.addressRepository = addressRepository;
    }

    public List<Car> getCarsInOffice(Long id) {
        Office office = officeRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
        return office.getCars();
    }

    public boolean addNewOffice(Office office) {
        officeValidator.validate(office);
        officeRepository.save(office);
        return true;
    }

    public boolean patchOffice(Office office) {
        officeValidator.validateToPatch(office);
        Office toPatch = officeRepository.findById(office.getId()).orElseThrow(ObjectNotFoundException::new);
        toPatch.setName(office.getName());
        toPatch.setCordX(office.getCordX());
        toPatch.setCordY(office.getCordY());
        officeRepository.save(toPatch);
        return true;
    }

    public boolean patchOfficeAddress(Address address, Long id) {
        Office toPatch = officeRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
        addressValidator.validate(address);
        Optional<Address> address1 =
                addressRepository.findByCityAndCodeAndNumberAndStreet(address.getCity(), address.getCode(),
                        address.getNumber(), address.getStreet());
        //if address exist get it and patch
        address1.ifPresent(toPatch::setAddress);
        //if address doesn't exist save it and patch
        if (address1.isEmpty()) {
            toPatch.setAddress(addressRepository.save(new Address(address)));
        }
        officeRepository.save(toPatch);
        return true;
    }

    public List<Office> getAllOffices() {
        return officeRepository.findAll();
    }
}
