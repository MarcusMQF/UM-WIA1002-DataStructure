public class T1Q4 {
    public static void main(String[] args) {
        // Test code can be added here if needed
    }
}

abstract class Vehicle {
    private double maxSpeed;
    protected double currentSpeed;
    
    public Vehicle(double maxSpeed) {
        this.maxSpeed = maxSpeed;
        this.currentSpeed = 0.0; // Initialize currentSpeed to 0
    }
    
    public abstract void accelerate();
    
    public double getCurrentSpeed() {
        return currentSpeed;
    }
    
    public double getMaxSpeed() {
        return maxSpeed;
    }
    
    public void pedalToTheMetal() {
        while (currentSpeed < maxSpeed) {
            accelerate();
        }
    }
}
