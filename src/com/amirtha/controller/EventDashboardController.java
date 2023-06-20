package com.amirtha.controller;

import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import com.amirtha.helper.EventHelper;
import com.amirtha.model.Event;
import com.amirtha.model.User;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class EventDashboardController implements Initializable {
	
	private User user;
	
	private EventHelper eventHelper;

    

    @FXML
    private Button close;

    @FXML
    private Button minimize;

    @FXML
    private Label username;
    
    private ObservableList<Event> listAllEvents;
    
    //FORMS
    @FXML
    private AnchorPane main_form;
    
    @FXML
    private AnchorPane active_event_form;
    
    @FXML
    private AnchorPane all_event_form;
    
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
    
  //---------------------------------------
    
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
    private TableColumn<Event, Date> all_event_col_from_date;
    
    @FXML
    private TableColumn<Event, Date> all_event_col_to_date;
    
    @FXML
    private TableColumn<Event, String> all_event_col_time;
    
    @FXML
    private TableColumn<Event, String> all_event_col_description;
    
    
   
   
    public void switchForm(ActionEvent event) {

//        if (event.getSource() == home_btn) {
//            home_form.setVisible(true);
//            active_event_form.setVisible(false);
//            salary_form.setVisible(false);
//
//            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
//            addEmployee_btn.setStyle("-fx-background-color:transparent");
//            salary_btn.setStyle("-fx-background-color:transparent");
//
//            homeTotalEmployees();
//            homeEmployeeTotalPresent();
//            homeTotalInactive();
//            homeChart();
//
//        } else if (event.getSource() == addEmployee_btn) {
//            home_form.setVisible(false);
//            active_event_form.setVisible(true);
//            salary_form.setVisible(false);
//
//            addEmployee_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
//            home_btn.setStyle("-fx-background-color:transparent");
//            salary_btn.setStyle("-fx-background-color:transparent");
//
//            addEmployeeGendernList();
//            addEmployeePositionList();
//            addEmployeeSearch();
//
//        } else if (event.getSource() == salary_btn) {
//            home_form.setVisible(false);
//            active_event_form.setVisible(false);
//            salary_form.setVisible(true);
//
//            salary_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
//            addEmployee_btn.setStyle("-fx-background-color:transparent");
//            home_btn.setStyle("-fx-background-color:transparent");
//
//            salaryShowListData();
//
//        }

    }
    
    public void convertToEventTableView() {
    	event_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
    	event_col_department.setCellValueFactory(new PropertyValueFactory<>("departmentName"));
    	event_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
    	event_col_venue.setCellValueFactory(new PropertyValueFactory<>("venue"));
    	event_col_from_date.setCellValueFactory(new PropertyValueFactory<>("fromDate"));
    	event_col_to_date.setCellValueFactory(new PropertyValueFactory<>("toDate"));
    	event_col_time.setCellValueFactory(new PropertyValueFactory<>("fromTime"));
    	event_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        eventTableView.setItems(this.listAllEvents);
    }

    private double x = 0;
    private double y = 0;

    public void logout() {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> option = alert.showAndWait();
        try {
            if (option.get().equals(ButtonType.OK)) {

                logout.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
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

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    	User user=new User();
    	user.setRole("ADMIN");
    	
    	this.user=user;
    	
    	    	
    	active_event_form.setVisible(true);
    	active_evnt_btn.setDisable(true);
    	active_evnt_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
    	
    	all_event_form.setVisible(false);
    	
    	
        this.eventHelper=new EventHelper();
        
        this.listAllEvents=eventHelper.getAllEvents();
        this.convertToEventTableView();
        
        
        if(this.user!=null && !this.user.getRole().equalsIgnoreCase("ADMIN")) {
        	mgnt_usr_btn.setVisible(false);
        	mgnt_usr_btn.setDisable(true);
        }
       
        if(this.user==null) {
        	logout.setDisable(true);
        	logout.setVisible(false);
        	logoutlabel.setVisible(false);
        	
        	mgnt_usr_btn.setVisible(false);
        	mgnt_usr_btn.setDisable(true);
        	
        	add_evnt_btn.setVisible(false);
        	add_evnt_btn.setDisable(true);
        }
    }
    
    
    public void initData(User user) {
    	this.user=user;
    }
    
    
    public void changePage(ActionEvent event) {
    	
    	if (event.getSource() == active_evnt_btn) {
    		
    		active_event_form.setVisible(true);
    		active_evnt_btn.setDisable(true);
    		active_evnt_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
        	
    		//all event form
    		all_event_form.setVisible(false);
    		all_evnt_btn.setStyle("-fx-background-color:transparent");
    		all_evnt_btn.setDisable(false);
    		
    		
    		//manageuser form
    		user_management_form.setVisible(false);
    		mgnt_usr_btn.setStyle("-fx-background-color:transparent");
    		mgnt_usr_btn.setDisable(false);
    		
    		
    		//add event form
    		//all_event_form.setVisible(false);
    		add_evnt_btn.setStyle("-fx-background-color:transparent");
    		add_evnt_btn.setDisable(false);
    	}
    	
    	if(event.getSource() == all_evnt_btn) {
    		
    		all_event_form.setVisible(true);
    		all_evnt_btn.setDisable(true);
    		all_evnt_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
        	
    		//active event form
    		active_event_form.setVisible(false);
    		active_evnt_btn.setStyle("-fx-background-color:transparent");
    		active_evnt_btn.setDisable(false);
    		
    		//manageuser form
    		user_management_form.setVisible(false);
    		mgnt_usr_btn.setStyle("-fx-background-color:transparent");
    		mgnt_usr_btn.setDisable(false);
    		
    		//add event form
    		//all_event_form.setVisible(false);
    		add_evnt_btn.setStyle("-fx-background-color:transparent");
    		add_evnt_btn.setDisable(false);
    	}
    	
    	
    	if(event.getSource() == mgnt_usr_btn) {
    		
    		user_management_form.setVisible(true);
    		mgnt_usr_btn.setDisable(true);
    		mgnt_usr_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
        	
    		//all event form
    		all_event_form.setVisible(false);
    		all_evnt_btn.setStyle("-fx-background-color:transparent");
    		all_evnt_btn.setDisable(false);
    		
 
    		//active event form
    		active_event_form.setVisible(false);
    		active_evnt_btn.setStyle("-fx-background-color:transparent");
    		active_evnt_btn.setDisable(false);
    		    		
    		//add event form
    		//all_event_form.setVisible(false);
    		add_evnt_btn.setStyle("-fx-background-color:transparent");
    		add_evnt_btn.setDisable(false);
    	}
    	
    	
    }

}
