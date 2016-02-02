package pt.mleiria.ml.vo;

public class TruckBusinessVO implements CsvTable {

    private double cityPopulation; 

    public double getCityPopulation(){
        return cityPopulation;
    }

    public void setCityPopulation(double cityPopulation){
        this.cityPopulation = cityPopulation;
    }

    private double profit; 

    public double getProfit(){
        return profit;
    }

    public void setProfit(double profit){
        this.profit = profit;
    }

}