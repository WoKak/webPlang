package webplang.domain;

/**
 * Created by Michał on 2017-04-07.
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
}
