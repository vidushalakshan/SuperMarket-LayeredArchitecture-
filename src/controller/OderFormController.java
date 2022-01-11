package controller;

import bo.BOFactory;
import bo.custom.PurchaseOrderBo;
import com.jfoenix.controls.JFXTextField;
import entity.Customers;
import entity.Items;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import dto.Customer;
import dto.Item;
import dto.Order;
import dto.OrderDetails;
import view.tm.CartTM;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OderFormController {
    public JFXTextField txtTitle;
    public JFXTextField txtName;
    public JFXTextField TxtAddress;
    public JFXTextField txtCity;
    public JFXTextField txtProvince;
    public JFXTextField txtPostalCode;
    public ComboBox<String> cmbCustomerID;
    public ComboBox<String> cmbItemID;
    public JFXTextField txtDescription;
    public JFXTextField txtPackSize;
    public JFXTextField txtUnitePrice;
    public JFXTextField txtQTYOnHand;
    public JFXTextField txtQTY;
    public TableView<CartTM> tblCart;
    public TableColumn colId;
    public TableColumn colDescription;
    public TableColumn colQTY;
    public TableColumn colUnitePrice;
    public TableColumn colTotal;
    public Label lblTotal;
    public Label lblTime;
    public Label lblDate;
    public Label lblOderId;
    public AnchorPane root;

    int cartSelectedRowForRemove = -1;

    PurchaseOrderBo purchaseOrderBO = (PurchaseOrderBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PURCHASEORDER);

    public void initialize(){


        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitePrice.setCellValueFactory(new PropertyValueFactory<>("unitePrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        loadDateAndTime();
        setOrderId();

        try {
            loadCustomerIds();
            loadItemIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        /*===================*/

        cmbCustomerID.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    try {
                        setCustomerData(newValue);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });

        cmbItemID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setItemData(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        tblCart.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRowForRemove = (int) newValue;
        });
        /*===================*/
    }

    private void setOrderId() {
        try {
            lblOderId.setText(purchaseOrderBO.getOrderId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setItemData(String itemCode) throws SQLException, ClassNotFoundException {
        Items i1 =purchaseOrderBO.searchItems(itemCode);
        /*Item i1 = new ItemController().getItem(itemCode);*/
        if (i1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            txtDescription.setText(i1.getDescription());
            txtPackSize.setText(i1.getPacksize());
            txtUnitePrice.setText(i1.getUniteprice());
            txtQTYOnHand.setText(String.valueOf(i1.getQtyonhand()));


        }
    }

    private void setCustomerData(String customerId) throws SQLException, ClassNotFoundException {
        Customers c1 = purchaseOrderBO.searchCustomer(customerId);
        if (c1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtTitle.setText(c1.getTitle());
            txtName.setText(c1.getName());
            TxtAddress.setText(c1.getAddress());
            txtCity.setText(c1.getCity());
            txtProvince.setText(c1.getProvince());
            txtPostalCode.setText(c1.getPostalcode());
            txtName.setText(c1.getName());

        }
    }

    private void loadItemIds() throws SQLException, ClassNotFoundException {
        List<String> itemIds = purchaseOrderBO.loadItemIDS();
        cmbItemID.getItems().addAll(itemIds);
    }

    private void loadCustomerIds() throws SQLException, ClassNotFoundException {
        List<String> customerIds = purchaseOrderBO.loadCustomerIds();
        cmbCustomerID.getItems().addAll(customerIds);
    }

    private void loadDateAndTime() {
        // load Date
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        // load Time
        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() +
                            " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }




    private int isExists(CartTM tm){
        for (int i = 0; i < obList.size(); i++) {
            if (tm.getId().equals(obList.get(i).getId())){
                return i;
            }
        }
        return -1;
    }

    void calculateCost(){
        double ttl=0;
        for (CartTM tm:obList
        ) {
            ttl+=tm.getTotal();
        }
        lblTotal.setText(ttl+" /=");
    }

    public void clearOnAction(ActionEvent actionEvent) {
        if (cartSelectedRowForRemove==-1){
            new Alert(Alert.AlertType.WARNING, "Please Select a row").show();
        }else{
            obList.remove(cartSelectedRowForRemove);
            calculateCost();
            tblCart.refresh();
        }
    }


    ObservableList<CartTM> obList= FXCollections.observableArrayList();


    public void AddTocartOnAction(ActionEvent actionEvent) {
        String description = txtDescription.getText();
        int qtyOnHand = Integer.parseInt(txtQTYOnHand.getText());
        double unitPrice = Double.parseDouble(txtUnitePrice.getText());
        int qtyForCustomer = Integer.parseInt(txtQTY.getText());
        double total = qtyForCustomer * unitPrice;

        if (qtyOnHand<qtyForCustomer){
            new Alert(Alert.AlertType.WARNING,"Invalid QTY").show();
            return;
        }

        CartTM tm = new CartTM(
                cmbItemID.getValue(),
                description,
                qtyForCustomer,
                unitPrice,
                total
        );

        int rowNumber=isExists(tm);

        if (rowNumber==-1){// new Add
            obList.add(tm);
        }else{
            // update
            CartTM temp = obList.get(rowNumber);
            CartTM newTm = new CartTM(
                    temp.getId(),
                    temp.getDescription(),
                    temp.getQty()+qtyForCustomer,
                    unitPrice,
                    total+temp.getTotal()
            );

            obList.remove(rowNumber);
            obList.add(newTm);
        }
        tblCart.setItems(obList);
        calculateCost();

    }

    public void placeOderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetails> items= new ArrayList<>();
        double total=0;
        for (CartTM tempTm:obList
        ) {
            total+=tempTm.getTotal();
            items.add(new OrderDetails(tempTm.getId(),tempTm.getUnitePrice(),
                    tempTm.getQty()));
        }

        Order order= new Order(lblOderId.getText(), cmbCustomerID.getValue(),
                lblDate.getText(),
                total,
                items
        );


        if (purchaseOrderBO.placeOrder(order)){
            new Alert(Alert.AlertType.CONFIRMATION, "Success").show();
            setOrderId();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }

    }

    public void BackOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent= FXMLLoader.load(this.getClass().getResource("../view/dashBoardForm.fxml"));
        Scene scene=new Scene(parent);

        Stage primaryStage=(Stage)this.root.getScene().getWindow();

        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }

    public void btnOrderManage(ActionEvent actionEvent) throws IOException {
        Parent parent= FXMLLoader.load(this.getClass().getResource("../view/OrderManageForm.fxml"));
        Scene scene=new Scene(parent);

        Stage primaryStage=(Stage)this.root.getScene().getWindow();

        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }
}

