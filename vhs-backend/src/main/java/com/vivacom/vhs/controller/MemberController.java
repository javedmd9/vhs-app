package com.vivacom.vhs.controller;

import com.vivacom.vhs.constants.Status;
import com.vivacom.vhs.dto.MembersDto;
import com.vivacom.vhs.model.Member;
import com.vivacom.vhs.model.Users;
import com.vivacom.vhs.repository.MemberRepository;
import com.vivacom.vhs.repository.UserRepository;
import com.vivacom.vhs.utils.StatusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/member")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public StatusDto createNewMember(@RequestBody MembersDto dto){
        StatusDto statusDto = new StatusDto();
        try{
            Member member = convertMemberDtoToEntity(dto);
            memberRepository.save(member);
            statusDto.setResult(Status.SUCCESS);
            statusDto.setMessage("Successfully saved new member");
        } catch (Exception e){
            e.printStackTrace();
            statusDto.setResult(Status.FAILED);
            statusDto.setMessage("Some exception occurred");
        }
        return statusDto;
    }

    @RequestMapping(value = "/view-all", method = RequestMethod.GET)
    public List<Member> getAllMemberList(){
        return memberRepository.findAll();
    }

    private Member convertMemberDtoToEntity(MembersDto dto){
        Member member = new Member();
        member.setId(dto.getId());
        member.setName(dto.getName());
        member.setGender(dto.getGender());
        member.setDob(dto.getDob());
        member.setRelation(dto.getRelation());
        member.setPhoneNumber(dto.getPhoneNumber());
        member.setEmail(dto.getEmail());
        Users user = userRepository.findByUserName(dto.getUsername());
        if (user != null){
            member.setUser(user);
        }
        return member;
    }

    @RequestMapping(value = "/find-members-by-username", method = RequestMethod.POST)
    public List<Member> findMembersListByUserName(@RequestBody Users user){
        Users existingUser = userRepository.findByUserName(user.getUserName());
        List<Member> members = new ArrayList<>();
        if (existingUser != null){
            members = memberRepository.findByUser(existingUser);
        }
        return members;
    }
}
