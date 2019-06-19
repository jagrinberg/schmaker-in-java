import java.io.*;
import java.util.*;

public class filecreater {
	public static void main(String[] args) throws IOException {
		for (int y = 1; y < 4; y++) {
			BufferedReader reader = new BufferedReader(new FileReader("Tri" + y + ".txt"));
			//PrintWriter write = new PrintWriter(new BufferedWriter(new FileWriter("Test.txt")));

			HashMap<String, List<String>> Tri = new HashMap<>();

			String line = "";

			line = reader.readLine();

			while (line != null) {
				String[] info = line.split("\t");
				String iden = "";
				String name = "";
				String blocks = "";
				for (int i = 0; i < info[0].length(); i++) {
					if (info[0].charAt(i) == ' ') {
						iden = info[0].substring(0, i);
						name = info[0].substring(i + 1);
						break;
					}
				}
				blocks = info[1];
				
				File classs = new File("C:\\Users\\jacob\\Documents\\Schedule\\Trimester" + y + "\\" + iden+ ".txt");
				boolean hi = classs.createNewFile();
				PrintWriter sched = new PrintWriter(new BufferedWriter(new FileWriter(classs, true)));
				
				if(hi) {
					sched.print(name + "\n");
				}
				
				if (Tri.containsKey(iden)) {
					List<String> temp = Tri.get(iden);
					temp.add(blocks);
					Tri.put(iden, temp);
				} else {
					List<String> temp = new ArrayList<>();
					temp.add(name);
					temp.add(blocks);
					Tri.put(iden, temp);
				}
				
				boolean exists = false;
				
				for (String g: Tri.get(iden).subList(1, Tri.get(iden).size()-1)) {
					if(g.equals(blocks)) {
						exists = true;
						System.out.println(g + " " + blocks);
						break;
						
					}
				}
				
				if(!exists) {
					sched.print(blocks + "\n");
				}
				line = reader.readLine();
				sched.close();
			}

			/*for (String i : Tri1.keySet()) {
				write.print(Tri1.get(i).get(0) + ": ");
				for (String j : Tri1.get(i).subList(1, Tri1.get(i).size())) {
					write.print(j + " ");
				}
				write.print("\n");
			}
			write.close();*/
			reader.close();
		}
	}
}
