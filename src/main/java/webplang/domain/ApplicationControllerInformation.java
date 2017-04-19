package webplang.domain;

/**
 * Created by Michał on 2017-04-07.
 */

/**
 * ApplicationControllerInformation is the two numbers describing current exercise
 */
public class ApplicationControllerInformation {

    private int index;
    private int points;

    public ApplicationControllerInformation() {
        this.index = 0;
        this.points = 0;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "ApplicationControllerInformation{" +
                "index=" + index +
                ", points=" + points +
                '}';
    }
}
