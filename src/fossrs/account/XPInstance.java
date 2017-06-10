package fossrs.account;

import java.time.LocalTime;
import java.io.Serializable;

public class XPInstance implements Serializable {
    private int xp;
    private LocalTime time;

    public XPInstance(int xp, LocalTime time) {
        this.xp = xp;
        this.time = time;
    }

    public int getXP() { return xp; }
    public LocalTime getTime() { return time; }
}