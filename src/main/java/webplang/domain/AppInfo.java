package webplang.domain;

/**
 * Created by Michał on 2017-04-07.
 */

/**
 * AppInfo is the two numbers describing current exercise
 */
public class AppInfo {

    private int index;
    private int points;

    public AppInfo() {
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
        return "AppInfo{" +
                "index=" + index +
                ", points=" + points +
                '}';
    }
}
