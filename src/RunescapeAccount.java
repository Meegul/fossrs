import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

public class RunescapeAccount {
	String baseURL = "http://services.runescape.com/m=hiscore_oldschool/index_lite.ws?player=";
	String username;
	HashMap<String, Integer> skills = new HashMap();
	private final String[] skillNames = {"overall", "attack", "defense", "strength", "hitpoints",
        "ranged", "prayer", "magic", "cooking", "woodcutting", "fletching",
        "fishing", "firemaking", "crafting", "smithing", "mining", "herblore",
        "agility", "thieving", "slayer", "farming", "runecrafting", "hunter", "construction"};
	
	public RunescapeAccount(String username) {
		this.username = username;
	}

	public void getInformation() throws IOException {
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
                    int level = Integer.parseInt(tokens[1]);
                    if (i <= skillNames.length) {
                        skills.put(skillNames[i], level);
                    }
                    i++;
                }
            }
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
                in.close();
            }
		}
	}

	public static RunescapeAccount lookupAccount(String username) {
		RunescapeAccount acc = new RunescapeAccount(username);
		try {
			acc.getInformation();
			return acc;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}