package fit.cvut.EventPro.controller;

import fit.cvut.EventPro.dto.FilterDto;
import fit.cvut.EventPro.dto.InvitationDto;
import fit.cvut.EventPro.entity.InvitationEntity;
import fit.cvut.EventPro.repository.EventRepo;
import fit.cvut.EventPro.service.EventService;
import fit.cvut.EventPro.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invitation")
public class InvitationController {

    @Autowired
    private EventService eventService;
    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private InvitationService invitationService;

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody InvitationDto invitationDto) {
        try {
            return ResponseEntity.ok(invitationService.addMultiple(invitationDto));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseEntity update(@RequestBody InvitationEntity invitationEntity) {
        try {
            return ResponseEntity.ok(invitationService.updateStatus(invitationEntity));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/get")
    public ResponseEntity getAll(@RequestBody FilterDto filterDto) {
        try {
            return ResponseEntity.ok(invitationService.getAll(filterDto.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to get.");
        }
    }

    @GetMapping("/email/{id}/{status}")
    public ResponseEntity updateStatus(@PathVariable("id") Long id, @PathVariable("status") String status) {
        try {

            InvitationEntity invitation = new InvitationEntity();
            invitation.setId(id);
            invitation.setStatus(status);
            invitationService.updateStatus(invitation);
            return ResponseEntity.ok(" Invitation "+status+" Successfully.");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/getByEvent")
    public ResponseEntity getInvitationList(@RequestBody FilterDto filterDto) {
        try {
            return ResponseEntity.ok(invitationService.getInvitationList(filterDto.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to get.");
        }
    }

}
