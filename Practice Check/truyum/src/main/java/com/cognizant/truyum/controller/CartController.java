package com.cognizant.truyum.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.truyum.exception.CartEmptyException;
import com.cognizant.truyum.exception.MenuItemNotFoundException;
import com.cognizant.truyum.exception.UserNotExistsException;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.service.CartService;

@RequestMapping("/carts")
@RestController
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	private final static Logger LOGGER = LoggerFactory.getLogger(CartController.class);
	
	@PostMapping("/{userId}/{menuItemId}")
	public void addCartItem(@PathVariable String userId, @PathVariable long menuItemId) throws MenuItemNotFoundException
	{
		LOGGER.info("Inside Add to Cart");
		cartService.addCartItem(userId, menuItemId);
		LOGGER.info("Added to Cart");
	}
	
	@GetMapping("/{userId}")
	public List<MenuItem> getAllCartItems(@PathVariable String userId) throws CartEmptyException, UserNotExistsException{
		LOGGER.info("Inside get all Cart items inside controller");
		List<MenuItem> list=cartService.getAllCartItems(userId);
		LOGGER.debug("Menu Items for User "+userId+" are : {} ",list);
		return list;
		
	}
	
	@DeleteMapping("/{userId}/{menuItemId}")
	public void deleteCartItem(@PathVariable String userId,@PathVariable long menuItemId) throws UserNotExistsException, CartEmptyException, MenuItemNotFoundException
	{
		LOGGER.info("Inside delete Cart Item inside Cart Controller");
		cartService.deleteCartItem(userId, menuItemId);
		LOGGER.debug("Cart Item deleted");
	}
	
}
