package com.cognizant.truyum.dao;

import java.util.List;

import com.cognizant.truyum.exception.CartEmptyException;
import com.cognizant.truyum.exception.MenuItemNotFoundException;
import com.cognizant.truyum.exception.UserNotExistsException;
import com.cognizant.truyum.model.MenuItem;

public interface CartDao {
	public void addCartItem(String user, long menuItemId) throws MenuItemNotFoundException;

	public List<MenuItem> getAllCartItems(String userId) throws CartEmptyException, UserNotExistsException;
	public void deleteCartItem(String userId, long menuItemId) throws UserNotExistsException, CartEmptyException, MenuItemNotFoundException;
}
