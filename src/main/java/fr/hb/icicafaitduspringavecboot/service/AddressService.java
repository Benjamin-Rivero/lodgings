package fr.hb.icicafaitduspringavecboot.service;

import fr.hb.icicafaitduspringavecboot.dto.AddressDto;
import fr.hb.icicafaitduspringavecboot.entity.Address;
import fr.hb.icicafaitduspringavecboot.entity.User;
import fr.hb.icicafaitduspringavecboot.repository.AddressRepository;
import fr.hb.icicafaitduspringavecboot.repository.UserRepository;
import fr.hb.icicafaitduspringavecboot.service.interfaces.ServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@AllArgsConstructor
@Service
public class AddressService implements ServiceInterface<Address,Long, AddressDto,AddressDto> {

    private final AddressRepository addressRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public Address create(AddressDto object) {
        return addressRepository.saveAndFlush(toEntity(object));
    }

    public Address createWithUser(AddressDto addressDto, Principal principal){
        Address address = create(addressDto);
        User user = userService.findByEmail(principal.getName());
        user.getAddresses().add(address);
        userRepository.save(user);
        return address;
    }

    private Address toEntity(AddressDto object) {
        Address address = new Address();
        address.setNumber(object.getNumber());
        address.setStreet(object.getStreet());
        address.setCity(object.getCity());
        address.setZipCode(object.getZipCode());
        address.setCountry(object.getCountry());
        address.setLatitude(object.getLatitude());
        address.setLongitude(object.getLongitude());
        address.setMore(object.getMore());
        address.setIsBilling(false);
        return address;
    }

    @Override
    public Address update(AddressDto object, Long id) {
        Address address = toEntity(object);
        address.setId(id);
        addressRepository.saveAndFlush(address);
        return address;
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
