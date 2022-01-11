package controller;

import bo.BOFactory;
import bo.custom.ItemBO;
import com.jfoenix.controls.JFXTextField;
import entity.Items;
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
import dto.Item;
import view.tm.ItemTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class itemAddFormController {
    public JFXTextField txtItemCode;
    public JFXTextField txtDescription;
    public JFXTextField txtPackSize;
    public JFXTextField txtUnitePrice;
    public JFXTextField txtQTYOnHand;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colPackSize;
    public TableColumn colUnitePrice;
    public TableColumn colQTYOnHand;
    public TableView<ItemTM> tblItemDetails;
    public AnchorPane root;
    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);


    public void initialize(){

      setTable();
    }

    private void setTable() {
        try {

            colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemmcode"));
            colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            colPackSize.setCellValueFactory(new PropertyValueFactory<>("packsize"));
            colUnitePrice.setCellValueFactory(new PropertyValueFactory<>("uniteprice"));
            colQTYOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyonhand"));



            setItemToTable(itemBO.getAllItem());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void btnModifyItemDetailsOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String itemID = txtItemCode.getText();

        Items c1= itemBO.searchItem(itemID);
        if (c1==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(c1);
        }
        
    }

    public void btnRemoveItems(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtItemCode.getText();
        if (itemBO.deleteItem(id)){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
        setTable();
        clear();
    }

    public void btnAddItem(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Item i1=new Item(
                txtItemCode.getText(),txtDescription.getText(),txtPackSize.getText(),txtUnitePrice.getText(),Integer.parseInt(txtQTYOnHand.getText())
        );

        if(itemBO.addItem(i1))
            new Alert(Alert.AlertType.CONFIRMATION,"Save........").show();
        else
            new Alert(Alert.AlertType.WARNING,"Try again...");

        setTable();
        clear();
    }
    private void setItemToTable (ArrayList<Item> customers){

        ObservableList<ItemTM> oblist= FXCollections.observableArrayList();
        customers.forEach(e->{  oblist.add(new ItemTM(
                e.getItemmcode(),e.getDescription(),e.getPacksize(),e.getQtyonhand(),e.getUniteprice()));


        });
        tblItemDetails.setItems(oblist);

    }

    public void btnHome(ActionEvent actionEvent) throws IOException {
        Parent parent= FXMLLoader.load(this.getClass().getResource("../view/dashBoardForm.fxml"));
        Scene scene=new Scene(parent);

        Stage primaryStage=(Stage)this.root.getScene().getWindow();

        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }


    void setData(Items c){
        txtItemCode.setText(c.getItemmcode());
        txtDescription.setText(c.getDescription());
        txtPackSize.setText(c.getPacksize());
        txtUnitePrice.setText(c.getUniteprice());
        txtQTYOnHand.setText(String.valueOf(c.getQtyonhand()));


    }
    void clear(){
        txtItemCode.clear();
        txtDescription.clear();
        txtPackSize.clear();
        txtUnitePrice.clear();
        txtQTYOnHand.clear();
    }
}
