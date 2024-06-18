package com.vaadin.spring.view;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("")
public class MainView extends AppLayout {

	public MainView() {
		
		createHeader();
        createDrawer();
	}
	
	private void createHeader() {
        DrawerToggle toggle = new DrawerToggle();
        Image logo = new Image("images/logo-2150297_640.webp", "My App logo");
        logo.setHeight("44px");

        addToNavbar(toggle, logo);
    }

    private void createDrawer() {
        RouterLink userLink = new RouterLink("Users", UserView.class);
        RouterLink roleLink = new RouterLink("Roles", RoleView.class);

        VerticalLayout layout = new VerticalLayout(userLink, roleLink);
        addToDrawer(layout);
    }

}