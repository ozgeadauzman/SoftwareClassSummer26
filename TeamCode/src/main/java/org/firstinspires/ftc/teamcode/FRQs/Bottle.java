package org.firstinspires.ftc.teamcode.FRQs;

public class Bottle {

    public double maxCapacity;

    public double currentAmount;

    public Bottle(double maxCapacity) {
        this.maxCapacity = maxCapacity;
        currentAmount = maxCapacity;
    }

    //updates amount of liquid
    public double updateAmount(double usedAmount) {

        //sets new amount
        currentAmount = currentAmount-usedAmount;

        if (currentAmount<(maxCapacity*0.25)) {
            //fills bottle
            currentAmount = maxCapacity;
        }

        return currentAmount;
    }

}