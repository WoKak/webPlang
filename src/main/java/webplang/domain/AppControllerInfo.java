package webplang.domain;

/**
 * Created by Michał on 2017-04-07.
 */
public class AppControllerInfo {

    private int idx;
    private int pts;

    public AppControllerInfo() {
        this.idx = 0;
        this.pts = 0;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }
}
