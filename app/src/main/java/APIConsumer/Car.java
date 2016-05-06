package APIConsumer;

/**
 * Created by Idorf on 05-05-2016.
 */
public class Car {


    Integer vin;
    String color;
    Integer miles;

    public Car(Integer vin, String color, Integer miles) {
        this.vin = vin;
        this.color = color;
        this.miles = miles;
    }

    public Integer getMiles() {
        return miles;
    }

    public void setMiles(Integer miles) {
        this.miles = miles;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getVin() {
        return vin;
    }

    public void setVin(Integer vin) {
        this.vin = vin;
    }
}
