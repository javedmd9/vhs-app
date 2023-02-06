package com.vivacom.vhs.controller;

import com.vivacom.vhs.constants.Status;
import com.vivacom.vhs.dto.CartDto;
import com.vivacom.vhs.model.Cart;
import com.vivacom.vhs.model.LabTests;
import com.vivacom.vhs.model.Package;
import com.vivacom.vhs.model.Users;
import com.vivacom.vhs.repository.CartRepository;
import com.vivacom.vhs.repository.LabTestRepository;
import com.vivacom.vhs.repository.PackageRepository;
import com.vivacom.vhs.repository.UserRepository;
import com.vivacom.vhs.utils.StatusDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/cart")
@Slf4j
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private LabTestRepository labTestRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public StatusDto addItemToCart(@RequestBody CartDto cart){
        StatusDto statusDto = new StatusDto();
        try{
            if (cart.getItemType().equalsIgnoreCase("package")){
                Optional<Package> existingPackage = packageRepository.findById(cart.getItemId());
                if (!existingPackage.isPresent())
                    return StatusDto.builder().result(Status.FAILED).message("Package not found").build();

            }
            if (cart.getItemType().equalsIgnoreCase("test")){
                Optional<LabTests> existingLabTest = labTestRepository.findById(cart.getItemId());
                if (!existingLabTest.isPresent()) return StatusDto.builder().result(Status.FAILED).message("Lab test not found").build();
            }
            Cart existingCartItem = cartRepository.findByItemId(cart.getItemId());
            if (existingCartItem != null){
                return StatusDto.builder().result(Status.FAILED).message("Item already added to cart").build();
            }
            Cart cartEntity = convertCartDtoToEntity(cart);
            Users user = userRepository.findByUserName(cart.getUsername());
            if (user == null){
                return StatusDto.builder().result(Status.FAILED).message("Username doesn't exist").build();
            }
            cartEntity.setUser(user);
            Cart save = cartRepository.save(cartEntity);
            statusDto.setResult(Status.SUCCESS);
            statusDto.setMessage("Successfully added item to cart");
            statusDto.setReturnData(String.valueOf(save.getItemId()));
        } catch (Exception e){
            e.printStackTrace();
            statusDto.setResult(Status.FAILED);
            statusDto.setMessage("Some exception occurred");
        }
        return statusDto;
    }

    @RequestMapping(value = "/find-all-item-by-user", method = RequestMethod.POST)
    public List<Cart> findAllItemByUser(@RequestBody CartDto cart){
        Users user = userRepository.findByUserName(cart.getUsername());
        return cartRepository.findByUser(user);
    }

    @RequestMapping(value = "/view-all", method = RequestMethod.GET)
    public List<Cart> viewAllCartItem(){
        return cartRepository.findAll();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public StatusDto deleteAnItemFromCart(@RequestBody CartDto dto){
        StatusDto statusDto = new StatusDto();
        statusDto.setResult(Status.FAILED);
        statusDto.setMessage("Cannot delete. Item not found");
        Optional<Cart> existingCartItem = cartRepository.findById(dto.getId());
        existingCartItem.ifPresent(ele -> {
            cartRepository.deleteById(ele.getId());
            statusDto.setResult(Status.SUCCESS);
            statusDto.setMessage("Selected item is deleted");
        });
        return statusDto;
    }

    private Cart convertCartDtoToEntity(CartDto cartDto){
        Cart cart = new Cart();
        cart.setItemId(cartDto.getItemId());
        cart.setItemType(cartDto.getItemType());
        cart.setItemName(cartDto.getItemName());
        cart.setSubText(cartDto.getSubText());
        cart.setSampleType(cartDto.getSampleType());
        cart.setMrp(cartDto.getMrp());
        cart.setDiscount(cartDto.getDiscount());
        cart.setSellingPrice(cartDto.getSellingPrice());
        cart.setReportDuration(cartDto.getReportDuration());
        cart.setPrecaution(cartDto.getPrecaution());
        Integer handlingCharge = cartDto.getHandlingCharge() == null? 0 : cartDto.getHandlingCharge();
        cart.setHandlingCharge(handlingCharge);
        return cart;
    }
}
