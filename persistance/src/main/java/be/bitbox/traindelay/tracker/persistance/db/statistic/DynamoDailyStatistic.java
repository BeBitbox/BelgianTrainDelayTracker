package be.bitbox.traindelay.tracker.persistance.db.statistic;

import be.bitbox.traindelay.tracker.core.statistic.DailyStatistic;
import be.bitbox.traindelay.tracker.persistance.db.InstantConverter;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;

import java.time.Instant;
import java.time.LocalDate;

@DynamoDBTable(tableName = "DailyStatistic")
public class DynamoDailyStatistic {

    @DynamoDBHashKey(attributeName = "local_date")
    private String date;

    @DynamoDBTypeConverted(converter = InstantConverter.class)
    @DynamoDBAttribute(attributeName = "creationTime")
    private Instant creationTime;

    @DynamoDBAttribute(attributeName = "departures")
    private int departures;

    @DynamoDBAttribute(attributeName = "delays")
    private int delays;

    @DynamoDBAttribute(attributeName = "averageDelay")
    private int averageDelay;

    @DynamoDBAttribute(attributeName = "cancellations")
    private int cancellations;

    @DynamoDBAttribute(attributeName = "platformChanges")
    private int platformChanges;

    public DynamoDailyStatistic() {
    }

    DynamoDailyStatistic(DailyStatistic dailyStatistic) {
        this.date = dailyStatistic.getDay().toString();
        this.creationTime = Instant.now();
        this.departures = dailyStatistic.getDepartures();
        this.delays = dailyStatistic.getDelays();
        this.averageDelay = dailyStatistic.getAverageDelay();
        this.cancellations = dailyStatistic.getCancellations();
        this.platformChanges = dailyStatistic.getPlatformChanges();
    }

    DailyStatistic toDailyStatistic() {
        return DailyStatistic.DayStatisticBuilder.aDayStatistic()
                .withDay(LocalDate.parse(date))
                .withDepartures(departures)
                .withDelays(delays)
                .withAverageDelay(averageDelay)
                .withCancellations(cancellations)
                .withPlatformChanges(platformChanges)
                .build();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Instant getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Instant creationTime) {
        this.creationTime = creationTime;
    }

    public int getDepartures() {
        return departures;
    }

    public void setDepartures(int departures) {
        this.departures = departures;
    }

    public int getDelays() {
        return delays;
    }

    public void setDelays(int delays) {
        this.delays = delays;
    }

    public int getAverageDelay() {
        return averageDelay;
    }

    public void setAverageDelay(int averageDelay) {
        this.averageDelay = averageDelay;
    }

    public int getCancellations() {
        return cancellations;
    }

    public void setCancellations(int cancellations) {
        this.cancellations = cancellations;
    }

    public int getPlatformChanges() {
        return platformChanges;
    }

    public void setPlatformChanges(int platformChanges) {
        this.platformChanges = platformChanges;
    }
}
