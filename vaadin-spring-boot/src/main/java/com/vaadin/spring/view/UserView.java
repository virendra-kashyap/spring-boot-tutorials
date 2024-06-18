package com.vaadin.spring.view;

import java.util.List;

import org.springframework.web.reactive.function.client.WebClient;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.spring.dto.UserDTO;

@Route(value = "user", layout = MainView.class)
public class UserView extends VerticalLayout {

	private final WebClient webClient;
	private final String API_URL = "http://localhost:8080/api/users/";

	private final Grid<UserDTO> grid = new Grid<>(UserDTO.class);

	private final Button addUserButton = new Button("New User", VaadinIcon.PLUS.create());

	public UserView(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.baseUrl(API_URL).build();

		setSizeFull();
		setPadding(true);

		// Configure the grid
		configureGrid();
		updateList();

		// Add components to layout
		add(grid, addUserButton);

		addUserButton.addClickListener(event -> openAddUserDialog());
	}

	private void configureGrid() {
		grid.setSizeFull();
		grid.setColumns("firstName", "lastName", "emailId");

		grid.addColumn(new ComponentRenderer<>(user -> {
			Button editButton = new Button(VaadinIcon.EDIT.create(), clickEvent -> openEditDialog(user));
			Button deleteButton = new Button(VaadinIcon.TRASH.create(), clickEvent -> openDeleteDialog(user));
			deleteButton.getStyle().set("color", "red");

			HorizontalLayout actions = new HorizontalLayout(editButton, deleteButton);
			return actions;
		})).setHeader("Actions");
	}

	private void updateList() {
		List<UserDTO> users = fetchUsersWithWebClient();
		grid.setItems(users);
	}

	private List<UserDTO> fetchUsersWithWebClient() {
		try {
			return webClient.get().uri("list").retrieve().bodyToFlux(UserDTO.class).collectList().block();
		} catch (Exception e) {
			Notification.show("Failed to fetch users: " + e.getMessage(), 3000, Notification.Position.MIDDLE);
			return List.of();
		}
	}

	private void openAddUserDialog() {
		Dialog dialog = new Dialog();
		FormLayout formLayout = new FormLayout();

		// Create text fields
		TextField firstNameField = new TextField("First Name");
		TextField lastNameField = new TextField("Last Name");
		TextField emailIdField = new TextField("Email ID");

		// Create binder
		Binder<UserDTO> binder = new Binder<>(UserDTO.class);

		// Bind fields explicitly
		binder.bind(firstNameField, UserDTO::getFirstName, UserDTO::setFirstName);
		binder.bind(lastNameField, UserDTO::getLastName, UserDTO::setLastName);
		binder.bind(emailIdField, UserDTO::getEmailId, UserDTO::setEmailId);

		// Create new instance for binding
		UserDTO newUser = new UserDTO();
		binder.setBean(newUser);

		// Add fields to form layout
		formLayout.add(firstNameField, lastNameField, emailIdField);

		// Create buttons
		Button saveButton = new Button("Save", event -> {
			if (binder.writeBeanIfValid(newUser)) {
				// Perform save operation
				webClient.post().uri("save").bodyValue(newUser).retrieve().bodyToMono(UserDTO.class)
						.subscribe(savedUser -> {
							updateList();
							dialog.close();
							showNotification("User added successfully!");
						}, error -> {
							showNotification("Failed to add user: " + error.getMessage());
						});
			} else {
				showNotification("Please fill all required fields correctly.");
			}
		});

		Button cancelButton = new Button("Cancel", event -> dialog.close());

		// Create buttons layout
		HorizontalLayout buttons = new HorizontalLayout(saveButton, cancelButton);

		// Create dialog layout
		VerticalLayout dialogLayout = new VerticalLayout(formLayout, buttons);
		dialog.add(dialogLayout);

		// Open dialog
		dialog.open();
	}

	private void openEditDialog(UserDTO user) {
		Dialog dialog = new Dialog();
		FormLayout formLayout = new FormLayout();

		// Create text fields
		TextField firstNameField = new TextField("First Name");
		TextField lastNameField = new TextField("Last Name");
		TextField emailIdField = new TextField("Email ID");

		// Create binder
		Binder<UserDTO> binder = new Binder<>(UserDTO.class);

		// Bind fields explicitly
		binder.bind(firstNameField, UserDTO::getFirstName, UserDTO::setFirstName);
		binder.bind(lastNameField, UserDTO::getLastName, UserDTO::setLastName);
		binder.bind(emailIdField, UserDTO::getEmailId, UserDTO::setEmailId);

		// Populate fields with existing user data
		binder.setBean(user);

		// Add fields to form layout
		formLayout.add(firstNameField, lastNameField, emailIdField);

		// Create buttons
		Button saveButton = new Button("Save", event -> {
			if (binder.writeBeanIfValid(user)) {
				// Perform update operation
				webClient.post().uri("update/{id}", user.getId()).bodyValue(user).retrieve().bodyToMono(UserDTO.class)
						.subscribe(updatedUser -> {
							updateList();
							dialog.close();
							showNotification("User updated successfully!");
						}, error -> {
							showNotification("Failed to update user: " + error.getMessage());
						});
			} else {
				showNotification("Please fill all required fields correctly.");
			}
		});

		Button cancelButton = new Button("Cancel", event -> dialog.close());

		// Create buttons layout
		HorizontalLayout buttons = new HorizontalLayout(saveButton, cancelButton);

		// Create dialog layout
		VerticalLayout dialogLayout = new VerticalLayout(formLayout, buttons);
		dialog.add(dialogLayout);

		// Open dialog
		dialog.open();
	}

	private void openDeleteDialog(UserDTO user) {
		Dialog dialog = new Dialog();
		dialog.add("Are you sure you want to delete this user?");
		Button confirmButton = new Button("Delete", event -> {
			webClient.post().uri("delete/{id}", user.getId()).retrieve().bodyToMono(Void.class).subscribe(result -> {
				updateList();
				dialog.close();
			}, error -> {
				showNotification("Failed to delete user: " + error.getMessage());
			});
		});
		Button cancelButton = new Button("Cancel", event -> dialog.close());
		dialog.add(new HorizontalLayout(confirmButton, cancelButton));
		dialog.open();
	}
	
	private void showNotification(String message) {
		UI ui = UI.getCurrent();
		if (ui != null) {
			ui.access(() -> {
				Notification.show(message);
			});
		} else {
			System.err.println("UI.getCurrent() returned null when trying to show notification: " + message);
		}
	}
}