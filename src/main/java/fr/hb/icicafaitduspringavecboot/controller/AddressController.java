package fr.hb.icicafaitduspringavecboot.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.hb.icicafaitduspringavecboot.dto.AddressDto;
import fr.hb.icicafaitduspringavecboot.entity.Address;
import fr.hb.icicafaitduspringavecboot.jsonviews.JsonViewAddress;
import fr.hb.icicafaitduspringavecboot.repository.AddressRepository;
import fr.hb.icicafaitduspringavecboot.service.AddressService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@AllArgsConstructor
@RequestMapping("/api/address")
public class AddressController {

    private final AddressRepository addressRepository;
    private final AddressService addressService;

    @PostMapping
    @JsonView(JsonViewAddress.AddressMinimalView.class)
    public Address create(@Valid @RequestBody AddressDto addressDto, Principal principal){
        if(principal!=null){
            return addressService.createWithUser(addressDto,principal);
        }
        return null;
    }

}
