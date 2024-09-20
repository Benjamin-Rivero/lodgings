package fr.hb.icicafaitduspringavecboot.service;

import fr.hb.icicafaitduspringavecboot.entity.Address;
import fr.hb.icicafaitduspringavecboot.entity.User;
import fr.hb.icicafaitduspringavecboot.repository.AddressRepository;
import fr.hb.icicafaitduspringavecboot.service.interfaces.ServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AddressService implements ServiceInterface<Address,Long> {

    private final AddressRepository addressRepository;

    @Override
    public Address create(Address object) {
        return addressRepository.saveAndFlush(object);
    }

    @Override
    public Address update(Address object, Long id) {
        object.setId(id);
        addressRepository.flush();
        return object;
    }

    @Override
    public void delete(Address object) {
        if(object!=null) addressRepository.delete(object);
    }

    @Override
    public Address findById(Long id) {
        return addressRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
