package com.vivacom.vhs.controller;

import com.vivacom.vhs.constants.Status;
import com.vivacom.vhs.dto.MemberAddressDto;
import com.vivacom.vhs.model.Member;
import com.vivacom.vhs.model.MemberAddress;
import com.vivacom.vhs.model.Users;
import com.vivacom.vhs.repository.MemberAddressRepository;
import com.vivacom.vhs.repository.MemberRepository;
import com.vivacom.vhs.repository.UserRepository;
import com.vivacom.vhs.utils.StatusDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/member-address")
@Slf4j
public class MemberAddressController {

    @Autowired
    private MemberAddressRepository memberAddressRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public StatusDto createMemberAddress(@RequestBody MemberAddressDto dto){
        StatusDto statusDto = new StatusDto();
        if (dto.getId() != null){
            return updateMemberAddress(dto);
        }
        log.info("Entering createMemberAddress");
        MemberAddress memberAddress = convertMemberAddressDtoToEntity(dto);
        memberAddressRepository.save(memberAddress);
        statusDto.setResult(Status.SUCCESS);
        statusDto.setMessage("Successfully saved new address");
        log.info("Exiting createMemberAddress");
        return statusDto;
    }


    public StatusDto updateMemberAddress(MemberAddressDto dto){
        log.info("Entering updateMemberAddress");
        StatusDto statusDto = new StatusDto();
        Optional<MemberAddress> memberAddress = memberAddressRepository.findById(dto.getId());
        statusDto.setResult(Status.FAILED);
        statusDto.setMessage("Data not found");
        memberAddress.ifPresent(ele -> {
            ele.setBuildingNo(dto.getBuildingNo());
            ele.setStreetName(dto.getStreetName());
            ele.setCity(dto.getCity());
            ele.setPinCode(dto.getPinCode());
            ele.setState(dto.getState());
            ele.setLandmark(dto.getLandmark());
            memberAddressRepository.save(ele);
            statusDto.setResult(Status.SUCCESS);
            statusDto.setMessage("Successfully updated the data");
        });
        log.info("Exiting updateMemberAddress");
        return statusDto;
    }

    @RequestMapping(value = "/view-member-address", method = RequestMethod.POST)
    public List<MemberAddress> findAddressByMember(@RequestBody MemberAddressDto dto){
        Member member = memberRepository.findById(dto.getMemberId()).orElseThrow(() -> new RuntimeException("Member id not found"));
        return memberAddressRepository.findByMember(member);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public StatusDto deleteMemberAddress(@RequestBody MemberAddressDto dto){
        memberAddressRepository.deleteById(dto.getId());
        return StatusDto.builder().result(Status.SUCCESS).message("Successfully deleted the member address").build();
    }

    private MemberAddress convertMemberAddressDtoToEntity(MemberAddressDto dto){
        MemberAddress memberAddress = new MemberAddress();
        memberAddress.setBuildingNo(dto.getBuildingNo());
        memberAddress.setStreetName(dto.getStreetName());
        memberAddress.setCity(dto.getCity());
        memberAddress.setPinCode(dto.getPinCode());
        memberAddress.setState(dto.getState());
        memberAddress.setLandmark(dto.getLandmark());
        Users user = userRepository.findByUserName(dto.getUsername());
        if (user != null) {
            memberAddress.setUser(user);
        }
        Member member = memberRepository.findById(dto.getMemberId()).orElseThrow(() -> new RuntimeException("Cannot find the member"));
        if (member != null) {
            memberAddress.setMember(member);
        }
        return memberAddress;
    }
}
