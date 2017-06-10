package fossrs.account;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.io.Serializable;

public class OSRSAccount implements Serializable {

	private static final String baseURL = "http://services.runescape.com/m=hiscore_oldschool/index_lite.ws?player=";
	private String username;
	private HashMap<String, Skill> skills;
	private static final String[] skillNames = {
		"overall", "attack", "defense", "strength", "hitpoints",
        "ranged", "prayer", "magic", "cooking", "woodcutting", "fletching",
        "fishing", "firemaking", "crafting", "smithing", "mining", "herblore",
        "agility", "thieving", "slayer", "farming", "runecrafting", "hunter", "construction"
	};
	
	public OSRSAccount(String username) {
		this.username = username;
		this.skills = new HashMap<String, Skill>();
	}

	public void setSkill(String skillName, int level, int xp, int rank) {
		skills.put(skillName, new Skill(skillName, level, xp, rank));
	}

	public void addXP(String skillName, int xp) {
		skills.get(skillName).addXP(xp);
	}

	public void printStats() {
		for (String skillName : skillNames) {
			Skill skill = skills.get(skillName);
			System.out.printf("%12s|lvl:%4d|xp:%9d|rank:%7d\n", skillName, skill.getLevel(), skill.getXP(), skill.getRank());
		}
	}

	public void downloadSkills() throws IOException {
		BufferedReader in = null;
		try {
			URL hiscoreURL = new URL(baseURL + username);
			URLConnection conn = hiscoreURL.openConnection();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			int i = 0;
			while ((inputLine = in.readLine()) != null) {
                String[] tokens = inputLine.split(",");
                if (tokens.length == 3) {
					int rank = Integer.parseInt(tokens[0]);
                    int level = Integer.parseInt(tokens[1]);
					int xp = Integer.parseInt(tokens[2]);
					String skillname = skillNames[i];
                    if (i <= skillNames.length) {
                        setSkill(skillname, level, xp, rank);
                    }
                    i++;
                }
            }
		} catch (IOException e) {
			System.err.printf("There was an error looking up information for %s\n", username);
		} finally {
			if (in != null) {
                in.close();
            }
		}
	}
}