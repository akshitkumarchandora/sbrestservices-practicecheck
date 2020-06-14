package com.cognizant.truyum.controller;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.truyum.exception.MenuItemNotFoundException;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.service.MenuItemService;

@RestController
@RequestMapping("/menu-items")
public class MenuItemController {

	private final static Logger LOGGER = LoggerFactory.getLogger(MenuItemController.class);
	@Autowired
	private MenuItemService menuItemService;

	@GetMapping
	public ArrayList<MenuItem> getAllMenuItems() {
		LOGGER.info("Inside All Menu Items");
		ArrayList<MenuItem> itemList = menuItemService.getMenuItemListCustomer();
		LOGGER.debug("Menu Item List :: {}", itemList);
		return itemList;
	}

	@GetMapping("/{id}")
	public MenuItem getMenuItem(@PathVariable long id) throws MenuItemNotFoundException {
		LOGGER.info("Inside Menu Item by id");
		MenuItem menuItem = menuItemService.getMenuItem(id);
		LOGGER.debug("Menu item :: {}", menuItem);
		return menuItem;
	}

	@PutMapping
	public void modifyMenuItem(@RequestBody MenuItem menuItem) throws MenuItemNotFoundException {
		LOGGER.info("Inside ModifyMenuItem");
		menuItemService.save(menuItem);
		LOGGER.debug("Modified item :: {}", menuItem);
		LOGGER.info("Modified");
	}

}
