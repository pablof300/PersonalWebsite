package me.pabloestrada.exercise.core.runs;

import com.google.gson.annotations.SerializedName;
import org.bson.types.ObjectId;

import java.util.Date;

// TODO:
// Research more about Jackson serialization

public class Run {

    @SerializedName("start_date")
    private Date startDate;

    @SerializedName("mongo_id")
    private ObjectId id;

    private float distance;
    private RunType typeOfRun;
    private boolean successfulStrengtheningExercise;

    public Run(final RunType typeOfRun) {
        this.typeOfRun = typeOfRun;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public RunType getTypeOfRun() {
        return typeOfRun;
    }

    public void setTypeOfRun(RunType typeOfRun) {
        this.typeOfRun = typeOfRun;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public boolean isSuccessfulStrengtheningExercise() {
        return successfulStrengtheningExercise;
    }

    public void setSuccessfulStrengtheningExercise(boolean successfulStrengtheningExercise) {
        this.successfulStrengtheningExercise = successfulStrengtheningExercise;
    }
}
