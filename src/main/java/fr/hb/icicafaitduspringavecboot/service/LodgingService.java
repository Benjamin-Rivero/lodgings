package fr.hb.icicafaitduspringavecboot.service;

import fr.hb.icicafaitduspringavecboot.dto.LodgingDto;
import fr.hb.icicafaitduspringavecboot.entity.Address;
import fr.hb.icicafaitduspringavecboot.entity.Lodging;
import fr.hb.icicafaitduspringavecboot.repository.AddressRepository;
import fr.hb.icicafaitduspringavecboot.repository.LodgingRepository;
import fr.hb.icicafaitduspringavecboot.service.interfaces.ServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LodgingService implements ServiceInterface<Lodging,String> {

    private final LodgingRepository lodgingRepository;
    private final AddressService addressService;
    private final AddressRepository addressRepository;

    public Lodging create(LodgingDto lodgingDto){
        Lodging lodging = toEntity(lodgingDto);
        Address address = addressService.findById(lodging.getAddress().getId());
        lodgingRepository.saveAndFlush(lodging);
//        address.setLodging(lodging);
        addressRepository.saveAndFlush(address);
        return create(lodging);
    }

    public Lodging toEntity(LodgingDto lodgingDto){
        Lodging lodging = new Lodging();
        lodging.setAddress(addressService.findById(lodgingDto.getAddressId()));
        lodging.setCapacity(lodgingDto.getCapacity());
        lodging.setDescription(lodgingDto.getDescription());
        lodging.setName(lodgingDto.getName());
        lodging.setNightPrice(lodgingDto.getNightPrice());
        return lodging;
    }

    @Override
    public Lodging create(Lodging object) {
        return lodgingRepository.saveAndFlush(object);
    }

    @Override
    public Lodging update(Lodging object, String id) {
        object.setId(id);
        lodgingRepository.flush();
        return object;
    }

    @Override
    public void delete(Lodging object) {
        if(object!=null) lodgingRepository.delete(object);
    }

    public Lodging findById(String id){
        return lodgingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }


}
