package fr.hb.icicafaitduspringavecboot.controller;

import fr.hb.icicafaitduspringavecboot.dto.RoomDto;
import fr.hb.icicafaitduspringavecboot.entity.Room;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fr.hb.icicafaitduspringavecboot.service.RoomService;


@AllArgsConstructor
@RestController
@RequestMapping("/api/room")
public class RoomController {


	private final RoomService roomService;


	@PostMapping
	public Room create(@Valid @RequestBody RoomDto roomDto){
		return roomService.create(roomDto);
	}

}