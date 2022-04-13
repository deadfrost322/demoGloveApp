package ru.antonidos.gloves.entity;

import lombok.Data;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

@Data
public class MaterialEntity {

//ID, Title, CountInPack, Unit, CountInStock, MinCount, Description, Cost, Image, MaterialType

    private int id;
    private String title;
    private int countInPack;
    private String unit;
    private int CountInStock;
    private int minCount;
    private String description;
    private double cost;
    private String imagePath;
    private String materialType;

    private ImageIcon image;

    public MaterialEntity(int id, String title, int countInPack, String unit, int countInStock, int minCount, String description, double cost, String imagePath, String materialType) {
        this.id = id;
        this.title = title;
        this.countInPack = countInPack;
        this.unit = unit;
        CountInStock = countInStock;
        this.minCount = minCount;
        this.description = description;
        this.cost = cost;
        this.imagePath = imagePath;
        this.materialType = materialType;

        try {
            this.image = new ImageIcon(ImageIO.read(MaterialEntity.class.getClassLoader().getResource(imagePath)).getScaledInstance(50,50, Image.SCALE_DEFAULT));
        } catch (Exception e) {
            try {
                this.image = new ImageIcon(ImageIO.read(MaterialEntity.class.getClassLoader().getResource("picture.png")).getScaledInstance(50,50, Image.SCALE_DEFAULT));
            } catch (Exception ex) {
            }
        }
    }

    public MaterialEntity( String title, int countInPack, String unit, int countInStock, int minCount, String description, double cost, String imagePath, String materialType)
    {
        this(-1, title, countInPack, unit, countInStock,minCount,description,cost,imagePath,materialType);
    }
}
