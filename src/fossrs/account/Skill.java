package fossrs.account;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;

public class Skill implements Serializable {
    private String name;
    private int level;
    private int xp;
    private int rank;
    private ArrayList<XPInstance> history;

    public Skill(String name, int level, int xp, int rank) {
        this.name = name;
        this.level = level;
        this.rank = rank;
        this.xp = xp;
        history = new ArrayList<XPInstance>();
        history.add(new XPInstance(this.xp, LocalTime.now()));
    }

    public void addXP(int xp) {
        this.xp += xp;
        history.add(new XPInstance(this.xp, LocalTime.now()));
        this.level = getLevelFromXP(this.xp);
    }

    public String getName() { return name; }
    public int getLevel() { return level; }
    public int getXP() { return xp; }
    public int getRank() { return rank; }
    public ArrayList<XPInstance> getHistory() { return history; }

    public static int[] xpCutoffs = {
        -1, -1, 83, 174, 276, 388, 512, 650, 801, 969,
        1154, 1358, 1584, 1833, 2107, 2411, 2746, 3115, 3523, 3973,
        4470, 5018, 5624, 6291, 7028, 7842, 8740, 9730, 10824, 12031,
        13363, 14833, 16456, 18247, 20224, 22406, 24815, 27473, 30408, 33648,
        37224, 41171, 45529, 50339, 55649, 61512, 67983, 75127, 83014, 91721,
        101333, 111945, 123660, 136594, 150872, 166636, 184040, 203254, 224466,
        247886, 273742, 302288, 333804, 368599, 407015, 449428, 496254, 547953, 605032,
        668051, 737627, 814445, 899257, 992895, 1096278, 1210421, 1336443, 1475581, 1629200,
        1798808, 1986068, 2192818, 2421087, 2673114, 2951373, 3258594, 3597792, 3972294, 4385776,
        4842295, 5346332, 5902831, 6517253, 7195629, 7944614, 8771558, 9684577, 10692629, 11805606,
        13034431
    };

    public static int getLevelFromXP(int xp) {
        if (xp < 83)
            return 1;
        int level = 0;
        while (++level < 100) {
            int cutoff = xpCutoffs[level];
            if (xp < cutoff || level == 99)
                break;
        }
        return level;
    }
}