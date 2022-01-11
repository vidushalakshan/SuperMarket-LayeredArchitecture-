package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import com.jfoenix.controls.JFXTextField;
import entity.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import dto.Customer;
import view.tm.CustomerTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class customerOderFormController {
    public JFXTextField txtCusId;
    public JFXTextField txtCusTitle;
    public JFXTextField txtCusName;
    public JFXTextField txtCusAddress;
    public JFXTextField txtCusCity;
    public JFXTextField txtCusProvince;
    public JFXTextField txtCusPostalCode;
    public TableView<CustomerTM> tblCustomerDetails;
    public TableColumn colCustomerID;
    public TableColumn colCustomerTitle;
    public TableColumn colCustomerName;
    public TableColumn colCustomerAddress;
    public TableColumn colCustomerCity;
    public TableColumn colCustomerProvince;
    public TableColumn colCustomerPostalCode;
    public AnchorPane root;

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    public void initialize(){

       setTable();

    }

    private void setTable() {
        try {

            colCustomerID.setCellValueFactory(new PropertyValueFactory<>("id"));
            colCustomerTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            colCustomerCity.setCellValueFactory(new PropertyValueFactory<>("city"));
            colCustomerProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
            colCustomerPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalcode"));


            setCustomerToTable(customerBO.getAllCustomer());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void AddCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Customer c1=new Customer(
                txtCusId.getText(),txtCusTitle.getText(),txtCusName.getText(),txtCusAddress.getText(),txtCusCity.getText(),txtCusProvince.getText(),txtCusPostalCode.getText()
        );

        if(customerBO.addCustomer(c1))
            new Alert(Alert.AlertType.CONFIRMATION,"Save........").show();
        else
            new Alert(Alert.AlertType.WARNING,"Try again...");

        setTable();
        clear();
    }

    private void setCustomerToTable (ArrayList<Customer> customers){

        ObservableList<CustomerTM> oblist= FXCollections.observableArrayList();
        customers.forEach(e->{  oblist.add(new CustomerTM(
                e.getId(),e.getTitle(),e.getName(),e.getAddress(),e.getCity(),e.getProvince(),e.getPostalcode()));


        });
        tblCustomerDetails.setItems(oblist);


    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent= FXMLLoader.load(this.getClass().getResource("../view/dashBoardForm.fxml"));
        Scene scene=new Scene(parent);

        Stage primaryStage=(Stage)this.root.getScene().getWindow();

        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }

    public void DeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtCusId.getText();
        if (customerBO.deleteCustomer(id)){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }

        setTable();
        clear();

    }

    public void UpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Customer c1= new Customer(
                txtCusId.getText(),txtCusTitle.getText(),
                txtCusName.getText(),txtCusAddress.getText(),txtCusCity.getText(),txtCusProvince.getText(),txtCusPostalCode.getText()

        );


        if (customerBO.updateCustomer(c1))
            new Alert(Alert.AlertType.CONFIRMATION,"Updated..").show();
        else
            new Alert(Alert.AlertType.WARNING,"Try Again").show();


        setTable();

    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String customerId = txtCusId.getText();

        Customers c1 = customerBO.searchCustomer(customerId);
        if (c1==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(c1);
        }
    }
    void setData(Customers c){
        txtCusId.setText(c.getId());
        txtCusTitle.setText(c.getTitle());
        txtCusName.setText(c.getName());
        txtCusAddress.setText(c.getAddress());
        txtCusCity.setText(c.getCity());
        txtCusProvince.setText(c.getProvince());
        txtCusPostalCode.setText(c.getPostalcode());

    }

    void clear(){
        txtCusId.clear();
        txtCusTitle.clear();
        txtCusName.clear();
        txtCusAddress.clear();
        txtCusCity.clear();
        txtCusProvince.clear();
        txtCusPostalCode.clear();
    }
}
