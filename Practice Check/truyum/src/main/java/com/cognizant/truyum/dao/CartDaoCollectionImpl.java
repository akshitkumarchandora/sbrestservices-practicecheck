package com.cognizant.truyum.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cognizant.truyum.exception.CartEmptyException;
import com.cognizant.truyum.exception.MenuItemNotFoundException;
import com.cognizant.truyum.exception.UserNotExistsException;
import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;

@Service
public class CartDaoCollectionImpl implements CartDao {
	private static HashMap<String, Cart> userCarts;

	public CartDaoCollectionImpl() {
		super();
		if (userCarts == null) {
			userCarts = new HashMap<String, Cart>();
		}
	}

	@Override
	public void addCartItem(String user, long menuItemId) {
		MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();
		MenuItem menuItem=null;
		try {
			menuItem = menuItemDao.getMenuItem(menuItemId);
		} catch (MenuItemNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (userCarts.containsKey(user)) {
			List<MenuItem> menuItemList = userCarts.get(user).getMenuItemList();
			menuItemList.add(menuItem);
		} else {
			Cart cart = new Cart(new ArrayList<MenuItem>(), 0.0);
			cart.getMenuItemList().add(menuItem);
			userCarts.put(user, cart);
		}
	}

	@Override
	public List<MenuItem> getAllCartItems(String userId) throws CartEmptyException, UserNotExistsException {
		if (userCarts.containsKey(userId)) {
			List<MenuItem> menuItemList = userCarts.get(userId).getMenuItemList();
			if (menuItemList.isEmpty()) {
				throw new CartEmptyException("Cart is Empty");
			} else {
				double total = 0.0;
				for (int i = 0; i < menuItemList.size(); i++) {
					total += menuItemList.get(i).getPrice();
				}
				userCarts.get(userId).setTotal(total);
			}
			return menuItemList;
		} else {
			throw new UserNotExistsException("User with id "+userId+" does not exists");
		}

	}

	@Override
	public void deleteCartItem(String userId, long menuItemId) throws UserNotExistsException, CartEmptyException, MenuItemNotFoundException {
		if(userCarts.size()==0)
			throw new UserNotExistsException();
		if(userCarts.containsKey(userId)) {
			
			List<MenuItem> menuItemList = userCarts.get(userId).getMenuItemList();
			if (menuItemList.isEmpty()) {
				throw new CartEmptyException("Cart is Empty");
			} 
			boolean deleted=false;
			for (int i = 0; i < menuItemList.size(); i++) {
				if (menuItemList.get(i).getId() == menuItemId) {
					menuItemList.remove(i);
					deleted=true;
					break;
				}
			}
			if(!deleted)
				throw new MenuItemNotFoundException("Menu Item with menuItemId "+menuItemId+" does not exists for User with userId "+ userId);
		}else
			throw new UserNotExistsException("User with userid "+userId+" does not exist");
		
	}

}
