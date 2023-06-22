package com.amirtha.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import com.amirtha.helper.EventHelper;
import com.amirtha.helper.UserHelper;
import com.amirtha.model.Event;
import com.amirtha.model.User;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EventDashboardController implements Initializable {

	private User sessionUser;

	private User currentSelectedUser;

	private EventHelper eventHelper;

	private UserHelper userHelper;

	@FXML
	private Button close;

	@FXML
	private Button minimize;

	@FXML
	private Label username;

	private ObservableList<Event> listEvents;

	private ObservableList<User> userList;

	// FORMS
	@FXML
	private AnchorPane main_form;

	@FXML
	private AnchorPane upcoming_event_form;

	@FXML
	private AnchorPane all_event_form;
	
	@FXML
	private AnchorPane add_event_form;

	@FXML
	private AnchorPane user_management_form;

	// MENU BUTTONS
	@FXML
	private Button active_evnt_btn;

	@FXML
	private Button all_evnt_btn;

	@FXML
	private Button add_evnt_btn;

	@FXML
	private Button mgnt_usr_btn;

	@FXML
	private Button logout;
	
	

	@FXML
	private Label logoutlabel;

	// ---------------------------------------

	@FXML
	private TableView<Event> eventTableView;

	@FXML
	private TableColumn<Event, String> event_col_id;

	@FXML
	private TableColumn<Event, String> event_col_department;

	@FXML
	private TableColumn<Event, String> event_col_name;

	@FXML
	private TableColumn<Event, String> event_col_venue;

	@FXML
	private TableColumn<Event, Date> event_col_from_date;

	@FXML
	private TableColumn<Event, Date> event_col_to_date;

	@FXML
	private TableColumn<Event, String> event_col_time;

	@FXML
	private TableColumn<Event, String> event_col_description;

	// ALL EVENT TABLE VIEW
	@FXML
	private TableView<Event> allEventTableView;

	@FXML
	private TableColumn<Event, String> all_event_col_id;

	@FXML
	private TableColumn<Event, String> all_event_col_department;

	@FXML
	private TableColumn<Event, String> all_event_col_name;

	@FXML
	private TableColumn<Event, String> all_event_col_venue;

	@FXML
	private TableColumn<Event, Timestamp> all_event_col_from_date;

	@FXML
	private TableColumn<Event, Timestamp> all_event_col_to_date;

	@FXML
	private TableColumn<Event, String> all_event_col_time;

	@FXML
	private TableColumn<Event, String> all_event_col_description;

	// USERS TABLE VIEW
	@FXML
	private TableView<User> users_tableView;

	@FXML
	private TableColumn<User, String> user_col_id;

	@FXML
	private TableColumn<User, String> user_col_name;

	@FXML
	private TableColumn<User, String> user_col_password;

	@FXML
	private TableColumn<User, Date> user_col_updated_at;

	@FXML
	private TableColumn<User, Date> user_col_created_at;

	// ADD USERS BUTTONS
	@FXML
	private Button user_addBtn;

	@FXML
	private Button user_delete;

	@FXML
	private Button user_change_password;

	@FXML
	private Button user_clear;

	// ADD USERS TEXT FIELDS
	@FXML
	private TextField adduser_userid_textView;

	@FXML
	private TextField adduser_username_textView;

	@FXML
	private TextField adduser_password_textView;
	

	public void convertToActiveEventTableView() {
		event_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		event_col_department.setCellValueFactory(new PropertyValueFactory<>("departmentName"));
		event_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		event_col_venue.setCellValueFactory(new PropertyValueFactory<>("venue"));
		event_col_from_date.setCellValueFactory(new PropertyValueFactory<>("fromDate"));
		event_col_to_date.setCellValueFactory(new PropertyValueFactory<>("toDate"));
		event_col_time.setCellValueFactory(new PropertyValueFactory<>("fromTime"));
		event_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
		eventTableView.setItems(this.listEvents);
	}
	
	public void convertToAllEventTableView() {
		all_event_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		all_event_col_department.setCellValueFactory(new PropertyValueFactory<>("departmentName"));
		all_event_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		all_event_col_venue.setCellValueFactory(new PropertyValueFactory<>("venue"));
		all_event_col_from_date.setCellValueFactory(new PropertyValueFactory<>("fromDate"));
		all_event_col_to_date.setCellValueFactory(new PropertyValueFactory<>("toDate"));
		all_event_col_time.setCellValueFactory(new PropertyValueFactory<>("fromTime"));
		all_event_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
		allEventTableView.setItems(this.listEvents);
	}

	public void convertToUserTableView() {
		user_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		user_col_name.setCellValueFactory(new PropertyValueFactory<>("username"));
		user_col_password.setCellValueFactory(new PropertyValueFactory<>("password"));
		user_col_updated_at.setCellValueFactory(new PropertyValueFactory<>("updatedAt"));
		user_col_created_at.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
		users_tableView.setItems(this.userList);
	}

	private double x = 0;
	private double y = 0;

	public void LoginLogout() throws IOException {
		
		if(this.sessionUser==null) {
			
			Parent root = FXMLLoader.load(getClass().getResource("/com/amirtha/view/LoginView.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            
            root.setOnMousePressed((MouseEvent event) ->{
                x = event.getSceneX();
                y = event.getSceneY();
            });
            
            root.setOnMouseDragged((MouseEvent event) ->{
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
            });
            
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
            
            all_evnt_btn.getScene().getWindow().hide();
			
		}else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation Message");
			alert.setHeaderText(null);
			alert.setContentText("Are you sure you want to logout?");
			Optional<ButtonType> option = alert.showAndWait();
			try {
				if (option.get().equals(ButtonType.OK)) {

					logout.getScene().getWindow().hide();
					Parent root = FXMLLoader.load(getClass().getResource("/com/amirtha/view/EventDashboard.fxml"));
					Stage stage = new Stage();
					Scene scene = new Scene(root);

					root.setOnMousePressed((MouseEvent event) -> {
						x = event.getSceneX();
						y = event.getSceneY();
					});

					root.setOnMouseDragged((MouseEvent event) -> {
						stage.setX(event.getScreenX() - x);
						stage.setY(event.getScreenY() - y);

						stage.setOpacity(.8);
					});

					root.setOnMouseReleased((MouseEvent event) -> {
						stage.setOpacity(1);
					});

					stage.initStyle(StageStyle.TRANSPARENT);

					stage.setScene(scene);
					stage.show();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void close() {
		System.exit(0);
	}

	public void minimize() {
		Stage stage = (Stage) main_form.getScene().getWindow();
		stage.setIconified(true);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Platform.runLater(() -> {
			
//			User user=new User(null, "user", "pass", "Admin", false, null, null);
//			this.sessionUser=user;

			activeEventMenuSwitch(true);
			allEventMenuSwitch(false);
			addEventMenuSwitch(false);
			manageUserMenuSwitch(false);

			if (this.sessionUser != null && !this.sessionUser.getRole().equalsIgnoreCase("ADMIN")) {
				mgnt_usr_btn.setVisible(false);
				mgnt_usr_btn.setDisable(true);
			}

			if (this.sessionUser == null) {
				logoutlabel.setText("SIGN IN");
				FontAwesomeIcon fntIcon = new FontAwesomeIcon();
				fntIcon.setFill(Color.WHITE);
				fntIcon.setSize("2em");
				fntIcon.setGlyphName("SIGN_IN");
				logout.setGraphic(fntIcon);
				
				mgnt_usr_btn.setVisible(false);
				mgnt_usr_btn.setDisable(true);

				add_evnt_btn.setVisible(false);
				add_evnt_btn.setDisable(true);
				
				username.setVisible(false);
			}
			
			if(this.sessionUser!=null) {
				logoutlabel.setText("SIGN OUT");
				FontAwesomeIcon fntIcon = new FontAwesomeIcon();
				fntIcon.setFill(Color.WHITE);
				fntIcon.setSize("2em");
				fntIcon.setGlyphName("SIGN_OUT");
				logout.setGraphic(fntIcon);
				username.setText(String.valueOf(sessionUser.getUsername().toUpperCase()));
			}

	    });
		
		
	}

	public void initData(User user) {
		this.sessionUser = user;
	}
	

	public void changePage(ActionEvent event) {

		if (event.getSource() == active_evnt_btn) {
			activeEventMenuSwitch(true);
			allEventMenuSwitch(false);
			addEventMenuSwitch(false);
			manageUserMenuSwitch(false);
		}

		if (event.getSource() == all_evnt_btn) {
			activeEventMenuSwitch(false);
			allEventMenuSwitch(true);
			addEventMenuSwitch(false);
			manageUserMenuSwitch(false);
		}
		
		if (event.getSource() == add_evnt_btn) {
			activeEventMenuSwitch(false);
			allEventMenuSwitch(false);
			addEventMenuSwitch(true);
			manageUserMenuSwitch(false);
		}

		if (event.getSource() == mgnt_usr_btn) {
			activeEventMenuSwitch(false);
			allEventMenuSwitch(false);
			addEventMenuSwitch(false);
			manageUserMenuSwitch(true);
		}
	}
	
	private void LoadActiveEvents() {
		
		if(this.eventHelper==null) {
			this.eventHelper = new EventHelper();
		}
		
		if(this.listEvents!=null) {
			this.listEvents.clear();
		}
		
		this.listEvents = eventHelper.getActiveEvents();
		this.convertToActiveEventTableView();
	}
	
	private void LoadAllEvents() {
		
		if(this.eventHelper==null) {
			this.eventHelper = new EventHelper();
		}
		
		if(this.listEvents!=null) {
			this.listEvents.clear();
		}
		
		this.listEvents = eventHelper.getAllEvents();
		this.convertToAllEventTableView();
	}

	private void loadUsers() {

		if (this.userHelper == null) {
			this.userHelper = new UserHelper();
		}

		if (userList != null) {
			userList.clear();
		}
		userList = userHelper.getNonAdminUsers();
		this.convertToUserTableView();
	}

	public void userSelect() {

		this.currentSelectedUser = users_tableView.getSelectionModel().getSelectedItem();
		int num = users_tableView.getSelectionModel().getSelectedIndex();

		if ((num - 1) < -1) {
			return;
		}

		if (currentSelectedUser != null) {
			adduser_userid_textView.setText(String.valueOf(currentSelectedUser.getId()));
			adduser_username_textView.setText(String.valueOf(currentSelectedUser.getUsername()));
			adduser_password_textView.setText(String.valueOf(currentSelectedUser.getPassword()));
		}
	}

	public void clearUser() {
		currentSelectedUser = null;
		adduser_userid_textView.setText("");
		adduser_username_textView.setText("");
		adduser_password_textView.setText("");
	}

	public void deleteUser() {

		if (this.currentSelectedUser != null) {
			Alert alert;
			
			if(!this.userHelper.checkUserExists(currentSelectedUser.getUsername())) {
				alert = new Alert(AlertType.WARNING);
				alert.setTitle("Invalid user to delete..");
				alert.setHeaderText(null);
				alert.setContentText("Invalid user to delete..");
				alert.showAndWait();
				return;
			}
			
			if (this.userHelper.deleteUser(currentSelectedUser.getUsername())) {
				clearUser();
				loadUsers();
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("User delete success");
				alert.setHeaderText(null);
				alert.setContentText("User delete success ");
				alert.showAndWait();
				
			} else {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("User delete failed");
				alert.setHeaderText(null);
				alert.setContentText("User delete failed");
				alert.showAndWait();
			}
		}
		currentSelectedUser = null;
		adduser_userid_textView.setText("");
		adduser_username_textView.setText("");
		adduser_password_textView.setText("");
	}

	public void addUser() {

		String username = this.adduser_username_textView.getText();
		String password = this.adduser_password_textView.getText();

		Alert alert;

		if (username.isBlank() || password.isBlank()) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("Please provide username and password");
			alert.setHeaderText(null);
			alert.setContentText("Please provide username and password");
			alert.showAndWait();
			return;
		} else {

			if (username.length() < 4 || username.length() > 10) {
				alert = new Alert(AlertType.WARNING);
				alert.setTitle("Username must be atleast 4 digit");
				alert.setHeaderText(null);
				alert.setContentText("Username must be atleast 4 digit");
				alert.showAndWait();
				return;
			}

			if (password.length() < 4 || password.length() > 10) {
				alert = new Alert(AlertType.WARNING);
				alert.setTitle("Password must be atleast 4 digit and not greater than 10");
				alert.setHeaderText(null);
				alert.setContentText("Password must be atleast 4 digit and not greater than 10");
				alert.showAndWait();
				return;
			}

			if (this.userHelper.checkUserExists(username)) {
				alert = new Alert(AlertType.WARNING);
				alert.setTitle("User already exists");
				alert.setHeaderText(null);
				alert.setContentText("User already exists");
				alert.showAndWait();
				return;
			}

			if (this.userHelper.addUser(new User(Long.valueOf(0), username, password, null, false, null, null))) {

				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("User add success");
				alert.setHeaderText(null);
				alert.setContentText("User add success");
				alert.showAndWait();
				clearUser();
				loadUsers();
			} else {
				alert = new Alert(AlertType.WARNING);
				alert.setTitle("User add failed");
				alert.setHeaderText(null);
				alert.setContentText("User add failed");
				alert.showAndWait();
			}
		}
	}
	
	public void changePassword() {
		
		Alert alert;
		
		String password=adduser_password_textView.getText();

		if (this.currentSelectedUser==null) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("Please select user to change password");
			alert.setHeaderText(null);
			alert.setContentText("Please select user to change password");
			alert.showAndWait();
			return;
		} 
		
		if(password.isBlank() || password.length()<4 || password.length()>10) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("Password validation failed");
			alert.setHeaderText(null);
			alert.setContentText("Password length must be grater than 4 and less than 10 digit..");
			alert.showAndWait();
			return;
		}
		
		if(!this.userHelper.checkUserExists(this.currentSelectedUser.getUsername())) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("Invalid user");
			alert.setHeaderText(null);
			alert.setContentText("Invalid user to change password...");
			alert.showAndWait();
			return;
		}
		
		if(this.userHelper.changePassword(new User(null, this.currentSelectedUser.getUsername(), password, null, false, null, null))) {
			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("password change success");
			alert.setHeaderText(null);
			alert.setContentText("password change success...");
			alert.showAndWait();
			clearUser();
			loadUsers();
			return;
		}else {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("password change failed");
			alert.setHeaderText(null);
			alert.setContentText("password change failed...");
			alert.showAndWait();
			return;
		}
	}
	
	public void activeEventMenuSwitch(boolean flag) {
		if(flag) {
			LoadActiveEvents();
			upcoming_event_form.setVisible(true);
			active_evnt_btn.setDisable(true);
			active_evnt_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
		}else {
			upcoming_event_form.setVisible(false);
			active_evnt_btn.setStyle("-fx-background-color:transparent");
			active_evnt_btn.setDisable(false);
		}
	}
	
	public void allEventMenuSwitch(boolean flag) {
		if(flag) {
			LoadAllEvents();
			all_event_form.setVisible(true);
			all_evnt_btn.setDisable(true);
			all_evnt_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
		}else {
			all_event_form.setVisible(false);
			all_evnt_btn.setStyle("-fx-background-color:transparent");
			all_evnt_btn.setDisable(false);
		}
	}
	
	public void addEventMenuSwitch(boolean flag) {
		if(flag) {
			add_event_form.setVisible(true);
			add_evnt_btn.setDisable(true);
			add_evnt_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
		}else {
			add_event_form.setVisible(false);
			add_evnt_btn.setStyle("-fx-background-color:transparent");
			add_evnt_btn.setDisable(false);
		}
	}
	
	public void manageUserMenuSwitch(boolean flag) {
		if(flag) {
			this.loadUsers();
			user_management_form.setVisible(true);
			mgnt_usr_btn.setDisable(true);
			mgnt_usr_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
		}else {
			user_management_form.setVisible(false);
			mgnt_usr_btn.setStyle("-fx-background-color:transparent");
			mgnt_usr_btn.setDisable(false);
		}
	}

}
