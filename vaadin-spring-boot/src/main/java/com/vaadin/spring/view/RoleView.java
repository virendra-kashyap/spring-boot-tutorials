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
import com.vaadin.spring.dto.RoleDTO;

@Route(value = "role", layout = MainView.class)
public class RoleView extends VerticalLayout {

	private final WebClient webClient;
	private final String API_URL = "http://localhost:8080/api/role/";
	private final Grid<RoleDTO> grid = new Grid<>(RoleDTO.class);
	private final Button addRoleButton = new Button("New Role", VaadinIcon.PLUS.create());

	public RoleView(WebClient.Builder webClientBuilder) {

		this.webClient = webClientBuilder.baseUrl(API_URL).build();

		setSizeFull();
		setPadding(true);

		// Configure the grid
		configureGrid();
		updateList();

		// Add components to layout
		add(grid, addRoleButton);

		addRoleButton.addClickListener(event -> openAddRoleDialog());
	}

	private void configureGrid() {
		grid.setSizeFull();
 
		grid.setColumns("id", "name");

		grid.addColumn(new ComponentRenderer<>(role -> {
			Button editButton = new Button(VaadinIcon.EDIT.create(), clickEvent -> openEditDialog(role));
			Button deleteButton = new Button(VaadinIcon.TRASH.create(), clickEvent -> openDeleteDialog(role));
			deleteButton.getStyle().set("color", "red");

			HorizontalLayout actions = new HorizontalLayout(editButton, deleteButton);
			return actions;
		})).setHeader("Actions");
	}

	private void updateList() {
		List<RoleDTO> roles = fetchRolesWithWebClient();
		grid.setItems(roles);
	}

	private List<RoleDTO> fetchRolesWithWebClient() {
		try {
			return webClient.get().uri("list").retrieve().bodyToFlux(RoleDTO.class).collectList().block();
		} catch (Exception e) {
			Notification.show("Failed to fetch roles: " + e.getMessage(), 3000, Notification.Position.MIDDLE);
			return List.of();
		}
	}

	private void openAddRoleDialog() {
		Dialog dialog = new Dialog();
		FormLayout formLayout = new FormLayout();

		// Create text fields
		TextField nameField = new TextField("Name");

		// Create binder
		Binder<RoleDTO> binder = new Binder<>(RoleDTO.class);

		// Bind fields explicitly
		binder.bind(nameField, RoleDTO::getName, RoleDTO::setName);

		// Create new instance for binding
		RoleDTO newRole = new RoleDTO();
		binder.setBean(newRole);

		// Add fields to form layout
		formLayout.add(nameField);

		// Create buttons
		Button saveButton = new Button("Save", event -> {
			if (binder.writeBeanIfValid(newRole)) {
				// Perform save operation
				webClient.post().uri("save").bodyValue(newRole).retrieve().bodyToMono(RoleDTO.class)
						.subscribe(savedRole -> {
							updateList();
							dialog.close();
							showNotification("Role added successfully!");
						}, error -> {
							showNotification("Failed to add role: " + error.getMessage());
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

	private void openEditDialog(RoleDTO role) {
		Dialog dialog = new Dialog();
		FormLayout formLayout = new FormLayout();

		// Create text fields
		TextField nameField = new TextField("Name");

		// Create binder
		Binder<RoleDTO> binder = new Binder<>(RoleDTO.class);

		// Bind fields explicitly
		binder.bind(nameField, RoleDTO::getName, RoleDTO::setName);

		// Populate fields with existing user data
		binder.setBean(role);

		// Add fields to form layout
		formLayout.add(nameField);

		// Create buttons
		Button saveButton = new Button("Save", event -> {
			if (binder.writeBeanIfValid(role)) {
				// Perform update operation
				webClient.post().uri("update/{id}", role.getId()).bodyValue(role).retrieve().bodyToMono(RoleDTO.class)
						.subscribe(updatedRole -> {
							updateList();
							dialog.close();
							showNotification("Role updated successfully!");
						}, error -> {
							showNotification("Failed to update role: " + error.getMessage());
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

	private void openDeleteDialog(RoleDTO role) {
		Dialog dialog = new Dialog();
		dialog.add("Are you sure you want to delete this role?");
		Button confirmButton = new Button("Delete", event -> {
			webClient.post().uri("delete/{id}", role.getId()).retrieve().bodyToMono(Void.class).subscribe(result -> {
				updateList();
				dialog.close();
			}, error -> {
				showNotification("Failed to delete role: " + error.getMessage());
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
