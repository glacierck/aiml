package pt.mleiria.ml.vo;

public class HouseBusinessVO implements CsvTable {

    private double size; 

    public double getSize(){
        return size;
    }

    public void setSize(double size){
        this.size = size;
    }

    private int numBedRooms; 

    public int getNumBedRooms(){
        return numBedRooms;
    }

    public void setNumBedRooms(int numBedRooms){
        this.numBedRooms = numBedRooms;
    }

    private double price; 

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

}