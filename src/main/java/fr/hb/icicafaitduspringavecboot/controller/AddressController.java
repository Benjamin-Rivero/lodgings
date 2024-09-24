package fr.hb.icicafaitduspringavecboot.controller;

import fr.hb.icicafaitduspringavecboot.entity.Address;
import fr.hb.icicafaitduspringavecboot.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/address")
public class AddressController {

    private final AddressRepository addressRepository;

}
