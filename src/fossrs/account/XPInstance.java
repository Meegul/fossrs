package fossrs.account;

import java.time.LocalTime;
import java.io.Serializable;
import java.util.Comparator;

public class XPInstance implements Serializable {
    static final long serialVersionUID = 102L;

    private int xp;
    private LocalTime time;

    public XPInstance(int xp, LocalTime time) {
        this.xp = xp;
        this.time = time;
    }

    public static class XPInstanceComparator implements Comparator<XPInstance> {
        public int compare(final XPInstance inst1, final XPInstance inst2) {
            return (inst1.getTime().isBefore(inst2.getTime())) ? -1 : 1;
        }
    }

    public boolean equals(XPInstance other) {
        return this.xp == other.getXP() && this.time.equals(other.getTime());
    }

    public int getXP() { return xp; }
    public LocalTime getTime() { return time; }
}