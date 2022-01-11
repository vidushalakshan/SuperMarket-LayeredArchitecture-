package controller;


import bo.BOFactory;
import bo.custom.OrderBO;
import dto.OrderForm;
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
import view.tm.OrderTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderManageFormController {
    public TableView<OrderTM> tblOrderManage;
    public TableColumn colOrderID;
    public TableColumn colCustomerID;
    public TableColumn colOrderDate;
    public TableColumn colCost;
    public AnchorPane root;

    OrderBO orderDao = (OrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER);

    public void initialize() {


        colOrderID.setCellValueFactory(new PropertyValueFactory<>("oderID"));
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("cutID"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("oderDate"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        try {
            setItemToTable(orderDao.getAllOrder());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void setItemToTable (ArrayList<OrderForm> orders){

        ObservableList<OrderTM> oblist= FXCollections.observableArrayList();
        orders.forEach(e->{  oblist.add(new OrderTM(
                e.getOderID(),e.getCutID(),e.getOderDate(),e.getCost()));


        });
        tblOrderManage.setItems(oblist);

    }


    public void btnDelete(ActionEvent actionEvent) {
        String code = tblOrderManage.getSelectionModel().getSelectedItem().getOderID();
        try {
            if (!existItem(code)) {
                new Alert(Alert.AlertType.ERROR, "There is no such item associated with the id " + code).show();
            }

            orderDao.deleteOrder(code);
            tblOrderManage.getItems().remove(tblOrderManage.getSelectionModel().getSelectedItem());
            tblOrderManage.getSelectionModel().clearSelection();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the item " + code).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return orderDao.ifOrderExist(code);
    }

    public void btnBack(ActionEvent actionEvent) throws IOException {
        Parent parent= FXMLLoader.load(this.getClass().getResource("../view/OrderForm.fxml"));
        Scene scene=new Scene(parent);

        Stage primaryStage=(Stage)this.root.getScene().getWindow();

        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }
}
