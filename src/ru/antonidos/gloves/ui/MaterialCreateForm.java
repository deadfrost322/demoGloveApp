package ru.antonidos.gloves.ui;

import ru.antonidos.gloves.entity.MaterialEntity;
import ru.antonidos.gloves.managers.MaterialEntityManager;
import ru.antonidos.gloves.ui.MaterialTableForm;
import ru.antonidos.gloves.util.BaseForm;
import ru.antonidos.gloves.util.DialogUtil;

import javax.swing.*;
import java.sql.SQLException;

public class MaterialCreateForm extends BaseForm {
    private JPanel mainPanel;
    private JTextField titleField;
    private JTextField unitField;
    private JTextField descriptionField;
    private JTextField costField;
    private JTextField imagePathField;
    private JSpinner countInPackSpinner;
    private JSpinner countInStockSpinner;
    private JSpinner minCountSpinner;
    private JButton backButton;
    private JButton createButton;
    private JComboBox materialTypeCombo;

    public MaterialCreateForm() {
        super(800, 600);
        setContentPane(mainPanel);
        initButton();
        setVisible(true);
    }

    public void initButton(){
        backButton.addActionListener(e -> {
            dispose();
            new MaterialTableForm();
        });

        createButton.addActionListener(e -> {
            String title = titleField.getText();
            if(title.isEmpty()||title.length()>100){
                DialogUtil.showError(this, "Название введено не верно");
                return;
            }

            int countInPack = (int) countInPackSpinner.getValue();
            if (countInPack<0){
                DialogUtil.showError(this, "Количество в упаковке введено не верно");
                return;
            }

            String unit = unitField.getText();
            if(unit.isEmpty()||unit.length()>10){
                DialogUtil.showError(this, "Единица измерения введена не верно");
                return;
            }

            int countInStock = (int) countInStockSpinner.getValue();
            if (countInStock<0){
                DialogUtil.showError(this, "Количество на складе введено не верно");
                return;
            }
            int minCount = (int) minCountSpinner.getValue();
            if (minCount<0){
                DialogUtil.showError(this, "Минимальное количество введено не верно");
                return;
            }

            String description = descriptionField.getText();

            double cost =-1;
            try{
                cost = Double.parseDouble(costField.getText());
            } catch (Exception ex){
                DialogUtil.showError(this, "Стоимость введена не верно");
                return;
            }
            if (cost<=0){
                DialogUtil.showError(this, "Стоимость введена не верно");
                return;
            }

            String imagePath = imagePathField.getText();
            if(imagePath.length()>100){
                DialogUtil.showError(this, "Путь до картинки введен не верно");
                return;
            }

            String materialType = (String) materialTypeCombo.getSelectedItem();
            System.out.println(materialType);

            MaterialEntity material = new MaterialEntity(
                    title, countInPack,unit,countInStock,minCount,description,cost,imagePath,materialType
            );

            try {
                MaterialEntityManager.insert(material);
                DialogUtil.showInfo(this, "Запись успешно добавлена");
                dispose();
                new MaterialTableForm();
            } catch (SQLException ex) {
                DialogUtil.showError(this, "Ошибка добавления записи");
                ex.printStackTrace();
            }
        });
    }
    //ID, Title, CountInPack, Unit, CountInStock, MinCount, Description, Cost, Image, MaterialType
}
