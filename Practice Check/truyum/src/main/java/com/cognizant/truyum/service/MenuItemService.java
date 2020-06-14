package com.cognizant.truyum.service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.truyum.dao.MenuItemDao;
import com.cognizant.truyum.exception.MenuItemNotFoundException;
import com.cognizant.truyum.model.MenuItem;

@Service
public class MenuItemService {

	@Autowired
	private MenuItemDao menuItemDao;

	public ArrayList<MenuItem> getMenuItemListCustomer() {
//		ApplicationContext context=new ClassPathXmlApplicationContext("truyum.xml");
//		menuItemDao=(MenuItemDaoCollectionImpl)context.getBean("menuItemDaoCollectionImpl");
		// if it was not autowired we ought to use above lines
		return menuItemDao.getMenuItemListCustomer();
	}

	public MenuItem getMenuItem(long id) throws MenuItemNotFoundException {
		return menuItemDao.getMenuItem(id);
	}

	public void save(MenuItem menuItem) throws MenuItemNotFoundException {
		menuItemDao.modifyMenuItem(menuItem);
	}
}
