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
public class LodgingService implements ServiceInterface<Lodging,String,LodgingDto,LodgingDto> {

    private final LodgingRepository lodgingRepository;
    private final AddressService addressService;
    private final AddressRepository addressRepository;

    public Lodging create(LodgingDto lodgingDto){
        Lodging lodging = toEntity(lodgingDto);
        lodging.setSlug("qsQS");
        return lodgingRepository.save(lodging);
    }

    public Lodging toEntity(LodgingDto lodgingDto){
        Lodging lodging = new Lodging();
        lodging.setAddress(addressService.create(lodgingDto.getAddressDto()));
        lodging.setCapacity(lodgingDto.getCapacity());
        lodging.setDescription(lodgingDto.getDescription());
        lodging.setName(lodgingDto.getName());
        lodging.setNightPrice(lodgingDto.getNightPrice());
        return lodging;
    }

    @Override
    public Lodging update(LodgingDto object, String id) {
        Lodging lodging = toEntity(object);
        lodging.setId(id);
        lodgingRepository.saveAndFlush(lodging);
        return lodging;
    }

    @Override
    public void delete(Lodging object) {
        if(object!=null) lodgingRepository.delete(object);
    }

    @Override
    public Lodging findById(String id){
        return lodgingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }


    public Lodging getOneRandom() {
        return lodgingRepository.getOneRandom();
    }
}
