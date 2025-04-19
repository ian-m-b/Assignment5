package Problem5;

import java.time.LocalDateTime;


class RideDetails {
    private double distance;
    private LocalDateTime startTime;
    private double demandFactor;

    public RideDetails(double distance, LocalDateTime startTime, double demandFactor) {
        this.distance = distance;
        this.startTime = startTime;
        this.demandFactor = demandFactor;
    }

    public double getDistance() { return distance; }
    public LocalDateTime getStartTime() { return startTime; }
    public double getDemandFactor() { return demandFactor; }
}

