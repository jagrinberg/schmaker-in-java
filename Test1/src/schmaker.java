import java.io.*;
import java.util.*;

public class schmaker {
	public static HashMap<String, List<String>> Tri1;
	public static HashMap<String, List<String>> Tri2;
	public static HashMap<String, List<String>> Tri3;

	public static List<String> Tri1classes;
	public static List<String> Tri2classes;
	public static List<String> Tri3classes;

	public static void main(String[] args) throws IOException {
		/*Tri1 = readFile("Tri1.txt");
		Tri2 = readFile("Tri2.txt");
		Tri3 = readFile("Tri3.txt");

		Tri1classes = new ArrayList<>();
		Tri2classes = new ArrayList<>();
		Tri3classes = new ArrayList<>();

		readClasses();

		List<String> empty = new ArrayList<>();

		classList first = new classList("f", "f", empty, 1, false);
		classList temp = first;
		for (String i : Tri1classes) {	
			classList current = new classList(i, Tri1.get(i).get(0), Tri1.get(i).subList(1, Tri1.get(i).size()), 1, false);
			temp.addClass(current);
			temp = current;
			if(i.endsWith("a")) {
				if(Tri2classes.contains(i.substring(0, i.length()-1) + "b")) {
					int ind = Tri2classes.indexOf(i.substring(0, i.length()-1) + "b");
					String value = Tri2classes.get(ind);
					current = new classList(value, Tri2.get(value).get(0), Tri2.get(value).subList(1, Tri2.get(value).size()), 2, true);
					temp.addClass(current);
					temp = current;
					Tri2classes.remove(ind);
					if(Tri3classes.contains(i.substring(0, i.length()-1) + "c")) {
						ind = Tri3classes.indexOf(i.substring(0, i.length()-1) + "c");
						value = Tri3classes.get(ind);
						current = new classList(value, Tri3.get(value).get(0), Tri3.get(value).subList(1, Tri3.get(value).size()), 3, true);
						temp.addClass(current);
						temp = current;
						Tri3classes.remove(ind);
					}
				}
			}
		}
		for (String i : Tri2classes) {
			classList current = new classList(i, Tri2.get(i).get(0), Tri2.get(i).subList(1, Tri2.get(i).size()), 2, false);
			temp.addClass(current);
			temp = current;
			if(i.endsWith("a")) {
				if(Tri3classes.contains(i.substring(0, i.length()-1) + "b")) {
					int ind = Tri3classes.indexOf(i.substring(0, i.length()-1) + "b");
					String value = Tri3classes.get(ind);
					current = new classList(value, Tri3.get(value).get(0), Tri3.get(value).subList(1, Tri3.get(value).size()), 3, true);
					temp.addClass(current);
					temp = current;
					Tri3classes.remove(ind);
				}
			}
		}
		for (String i : Tri3classes) {
			classList current = new classList(i, Tri3.get(i).get(0), Tri3.get(i).subList(1, Tri3.get(i).size()), 3, false);
			temp.addClass(current);
			temp = current;
		}

		// first.display();
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("Test2.txt", true)));
		boolean conflict = first.createSched(pw);
		System.out.println(conflict);
		pw.close();*/
	}
	
	public String[][][] create(List<String> onec, List<String> twoc, List<String> threec) throws IOException {
		Tri1 = readFile("Tri1.txt");
		Tri2 = readFile("Tri2.txt");
		Tri3 = readFile("Tri3.txt");

		Tri1classes = new ArrayList<>();
		Tri2classes = new ArrayList<>();
		Tri3classes = new ArrayList<>();

		Tri1classes = onec;
		Tri2classes = twoc;
		Tri3classes = threec;
		
		for(int i = 0; i < Tri1classes.size(); i++) {
			if(Tri1classes.get(i).startsWith("VS")) {
				Tri1classes.remove(i);
				i--;
			}
		}
		for(int i = 0; i < Tri2classes.size(); i++) {
			if(Tri2classes.get(i).startsWith("VS")) {
				Tri2classes.remove(i);
				i--;
			}
		}
		for(int i = 0; i < Tri3classes.size(); i++) {
			if(Tri3classes.get(i).startsWith("VS")) {
				Tri3classes.remove(i);
				i--;
			}
		}

		List<String> empty = new ArrayList<>();

		classList first = new classList("f", "f", empty, 1, false);
		classList temp = first;
		for (String i : Tri1classes) {	
			classList current = new classList(i, Tri1.get(i).get(0), Tri1.get(i).subList(1, Tri1.get(i).size()), 1, false);
			temp.addClass(current);
			temp = current;
			if(i.endsWith("a")) {
				if(Tri2classes.contains(i.substring(0, i.length()-1) + "b")) {
					int ind = Tri2classes.indexOf(i.substring(0, i.length()-1) + "b");
					String value = Tri2classes.get(ind);
					current = new classList(value, Tri2.get(value).get(0), Tri2.get(value).subList(1, Tri2.get(value).size()), 2, true);
					temp.addClass(current);
					temp = current;
					Tri2classes.remove(ind);
					if(Tri3classes.contains(i.substring(0, i.length()-1) + "c")) {
						ind = Tri3classes.indexOf(i.substring(0, i.length()-1) + "c");
						value = Tri3classes.get(ind);
						current = new classList(value, Tri3.get(value).get(0), Tri3.get(value).subList(1, Tri3.get(value).size()), 3, true);
						temp.addClass(current);
						temp = current;
						Tri3classes.remove(ind);
					}
				}
			}
		}
		for (String i : Tri2classes) {
			classList current = new classList(i, Tri2.get(i).get(0), Tri2.get(i).subList(1, Tri2.get(i).size()), 2, false);
			temp.addClass(current);
			temp = current;
			if(i.endsWith("a")) {
				if(Tri3classes.contains(i.substring(0, i.length()-1) + "b")) {
					int ind = Tri3classes.indexOf(i.substring(0, i.length()-1) + "b");
					String value = Tri3classes.get(ind);
					current = new classList(value, Tri3.get(value).get(0), Tri3.get(value).subList(1, Tri3.get(value).size()), 3, true);
					temp.addClass(current);
					temp = current;
					Tri3classes.remove(ind);
				}
			}
		}
		for (String i : Tri3classes) {
			classList current = new classList(i, Tri3.get(i).get(0), Tri3.get(i).subList(1, Tri3.get(i).size()), 3, false);
			temp.addClass(current);
			temp = current;
		}

		// first.display();
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("Test2.txt", true)));
		boolean conflict = first.createSched(pw);
		System.out.println(conflict);
		pw.close();
		temp = first;
		while(temp.next!=null) {
			temp = temp.next;
		}
		String[][][] out= temp.state;
		if(conflict) {
			return out;
		}else {
			out[0][0][0] = "NO";
			return out;
		}
	}
	
	// reads users classes from a .txt file
	/*public static void readClasses() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("Classes.txt"));
		String line = reader.readLine();
		while (!line.equals("")) {
			Tri1classes.add(line);
			line = reader.readLine();
		}
		line = reader.readLine();
		while (!line.equals("")) {
			Tri2classes.add(line);
			line = reader.readLine();
		}
		line = reader.readLine();
		while (!line.equals("")) {
			Tri3classes.add(line);
			line = reader.readLine();
		}
	}*/

	// reads classes and puts them in a HashMap
	public HashMap<String, List<String>> readFile(String filename) throws IOException {
		InputStream is = getClass().getResourceAsStream(filename);
	    InputStreamReader isr = new InputStreamReader(is);
		
		BufferedReader reader = new BufferedReader(isr);
		//PrintWriter write = new PrintWriter(new BufferedWriter(new FileWriter("Test.txt", true)));

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

			if (Tri.containsKey(iden)) {
				List<String> temp = Tri.get(iden);
				if (!Tri.get(iden).contains(blocks)) {
					temp.add(blocks);
					Tri.put(iden, temp);
				}
			} else {
				List<String> temp = new ArrayList<>();
				temp.add(name);
				temp.add(blocks);
				Tri.put(iden, temp);
			}
			line = reader.readLine();
		}
		reader.close();
		return Tri;
	}
}

class classList {
	public int currentIndex;
	public String identifier;
	public String className;
	public List<String> periods;
	public String[][][] state;
	public classList prev;
	public classList next;
	public int tri;
	public boolean first;
	public boolean depend;

	public classList(String identifier, String className, List<String> periods, int tri, boolean depend) throws IOException {
		this.tri = tri-1;
		this.depend=depend;
		this.identifier = identifier;
		this.className = className;
		this.periods = periods;
		if (identifier.equals("f")) {
			this.first = true;
		} else {
			this.first = false;
		}
		this.state = new String[3][9][6];
		// this.periods = periods;
		// this.tris = tris;
		this.currentIndex = 0;
		this.next = null;
	}

	public void addClass(classList temp) {
		this.next = temp;
		temp.prev = this;
	}

	public void display() {
		classList temp = this.next;
		while (temp != null) {
			System.out.println(temp.className);
			temp = temp.next;
		}
	}

	public boolean createSched(PrintWriter pw) {
		if (this.identifier.equals("f")) {
			if(this.next!=null) {
				return this.next.createSched(pw);
			}else {
				return true;
			}

		} else {
			do {
				boolean complete = false;
				while (!complete) {
					pw.println(this.className);
					if (this.currentIndex >= this.periods.size()) {
						this.currentIndex = 0;
						return false;
					}
					for (int z = 0; z < this.prev.state.length; z++) {
						for (int x = 0; x < this.prev.state[0].length; x++) {
							for (int c = 0; c < this.prev.state[0][0].length; c++) {
								this.state[z][x][c] = this.prev.state[z][x][c];
							}
						}
					}
					if(this.depend) {
						this.periods.clear();
						this.periods.add(prev.periods.get(prev.currentIndex-1));
					}
					String p = this.periods.get(this.currentIndex);
					pw.println(p);
					List<int[]> times = new ArrayList<>();
					for (int i = 0; i < p.length(); i++) {
						if (((int) p.charAt(i) - 'A') >= 0 && ((int) p.charAt(i) - 'A') <= 8) {
							int block = p.charAt(i) - 'A';
							i++;
							while (!(((int) p.charAt(i) - 'A') >= 0 && ((int) p.charAt(i) - 'A') <= 8)) {
								int col;
								if (p.charAt(i) == 'L') {
									col = 5;
								} else {
									col = p.charAt(i) - '1';
								}
								int[] hi = new int[2];
								hi[0] = block;
								hi[1] = col;
								times.add(hi);
								i++;
								if (i >= p.length()) {
									break;
								}
							}
							i--;
						}
					}
					complete = true;

					for (int[] i : times) {
						if (this.state[tri][i[0]][i[1]] != null) {
							complete = false;
							break;
						} else {
							this.state[tri][i[0]][i[1]] = this.identifier;
						}
					}
					for (int q = 0; q < this.state[0].length; q++) {
						for (int w = 0; w < this.state[0][0].length; w++) {
							if (this.state[tri][q][w] == null) {
								pw.print("00000 ");
							} else {
								pw.print(this.state[tri][q][w] + " ");
							}
						}
						pw.println();
					}
					this.currentIndex++;
				}
				if (this.next == null) {
					pw.println();
					for (int r = 0; r < 3; r++) {
						for (int q = 0; q < this.state[0].length; q++) {
							for (int w = 0; w < this.state[0][0].length; w++) {
								if (this.state[r][q][w] == null) {
									pw.print("00000 ");
								} else {
									pw.print(this.state[r][q][w] + " ");
								}
							}
							pw.println();
						}
						pw.println("\n");
					}
					break;
				}
			} while (!this.next.createSched(pw));
			pw.close();
			return true;
		}
	}
}
