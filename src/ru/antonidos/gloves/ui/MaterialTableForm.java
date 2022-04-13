package ru.antonidos.gloves.ui;

import ru.antonidos.gloves.entity.MaterialEntity;
import ru.antonidos.gloves.managers.MaterialEntityManager;
import ru.antonidos.gloves.util.BaseForm;
import ru.antonidos.gloves.util.CustomTableModel;
import ru.antonidos.gloves.util.DialogUtil;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class MaterialTableForm extends BaseForm {
    private JPanel mainPanel;
    private JTable table;
    private JButton addButton;

    CustomTableModel<MaterialEntity> model;

    public MaterialTableForm() {
        super(1200, 800);
        setContentPane(mainPanel);
        initButton();
        initTable();
        setVisible(true);
    }

    public void initButton(){
        addButton.addActionListener(e -> {
            dispose();
            new MaterialCreateForm();
        });
    }

    public void initTable(){
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(50);
        //ID, Title, CountInPack, Unit, CountInStock, MinCount, Description, Cost, Image, MaterialType
        try {
            model = new CustomTableModel<>(
                    MaterialEntity.class,
                    new String[]{"ID", "Title", "CountInPack", "Unit", "CountInStock", "MinCount", "Description", "Cost", "ImagePath", "MaterialType", "Image"},
                    MaterialEntityManager.selectAll()
            );


        } catch (SQLException e) {
            DialogUtil.showError(this, "Ошибка загрузки данных");
            e.printStackTrace();
        }
        table.setModel(model);

        TableColumn column = table.getColumn("ImagePath");
        column.setMaxWidth(0);
        column.setMinWidth(0);
        column.setPreferredWidth(0);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2){
                    int row = table.rowAtPoint(e.getPoint());
                    if(row!=-1){
                        dispose();
                        new MaterialEditForm(model.getRows().get(row));
                    }
                }
            }
        });
    }
}
