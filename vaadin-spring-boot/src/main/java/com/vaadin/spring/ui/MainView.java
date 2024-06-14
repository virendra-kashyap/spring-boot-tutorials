package com.vaadin.spring.ui;

import org.springframework.web.client.RestTemplate;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.spring.dto.UserDTO;

@Route()
public class MainView extends VerticalLayout {
	
	private final RestTemplate restTemplate = new RestTemplate();
	private final String baseUrl = "http://localhost:8080/api/users/";
	private final Grid<UserDTO> grid = new Grid<>(UserDTO.class);
	private final TextField firstName = new TextField("firstName");
	private final TextField lastName = new TextField("lastName");
	private final TextField emailId = new TextField("emailId");
	private final TextField id = new TextField("ID");
	
	public MainView() {
		add(firstName, lastName, emailId, new Button("Add", e -> save() ));
//		add(id, new Button("Find by ID", e -> findPersonById()));
		loadGrid();
        add(grid);
	}
	
	private void save() {
        UserDTO user = new UserDTO();
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setEmailId(user.getEmailId());
        restTemplate.postForObject(baseUrl, user, UserDTO.class);
        Notification.show("Person added");
        loadGrid();
    }
	
//	private void findPersonById() {
//        Long id = Long.parseLong(id.);
//        UserDTO person = restTemplate.getForObject(baseUrl + "/" + id, UserDTO.class);
//        if (person != null) {
//            Notification.show("Person found: " + person.getName() + ", " + person.getEmail());
//        } else {
//            Notification.show("Person not found");
//        }
//    }
	
	private void loadGrid() {
		UserDTO[] persons = restTemplate.getForObject(baseUrl, UserDTO[].class);
        grid.setItems(persons);
    }
}