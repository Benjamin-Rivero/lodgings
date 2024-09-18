package fr.hb.icicafaitduspringavecboot.service;

import fr.hb.icicafaitduspringavecboot.dto.LodgingDto;
import fr.hb.icicafaitduspringavecboot.entity.Lodging;
import fr.hb.icicafaitduspringavecboot.repository.LodgingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LodgingService {

    private final LodgingRepository lodgingRepository;
    private final AddressService addressService;

    public Lodging createLodging(LodgingDto lodgingDto){
        return lodgingRepository.saveAndFlush(toEntity(lodgingDto));
    }

    public Lodging toEntity(LodgingDto lodgingDto){
        Lodging lodging = new Lodging();
        lodging.setAddress(addressService.getById(lodgingDto.getAddressId()));
        lodging.setCapacity(lodgingDto.getCapacity());
        lodging.setDescription(lodgingDto.getDescription());
        lodging.setName(lodgingDto.getName());
        lodging.setNightPrice(lodgingDto.getNightPrice());
        lodging.setSlug(lodgingDto.getSlug());
        return lodging;
    }


}
