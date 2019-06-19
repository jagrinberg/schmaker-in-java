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
		Tri1 = readFile("Tri1.txt");
		Tri2 = readFile("Tri2.txt");
		Tri3 = readFile("Tri3.txt");

		Tri1classes = new ArrayList<>();
		Tri2classes = new ArrayList<>();
		Tri3classes = new ArrayList<>();

		readClasses();

		List<String> empty = new ArrayList<>();

		classList first = new classList("f", "f", empty);
		classList temp = first;
		for (String i : Tri1classes) {
			classList current = new classList(i, Tri1.get(i).get(0), Tri1.get(i).subList(1, Tri1.get(i).size()));
			temp.addClass(current);
			temp = current;
		}

		//first.display();
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("Test2.txt", true)));
		boolean conflict = first.createSched(pw);
		System.out.println(conflict);
		pw.close();
	}

	// reads users classes from a .txt file
	public static void readClasses() throws IOException {
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
	}

	// reads classes and puts them in a HashMap
	public static HashMap<String, List<String>> readFile(String filename) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		PrintWriter write = new PrintWriter(new BufferedWriter(new FileWriter("Test.txt", true)));

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

		for (String i : Tri.keySet()) {
			write.print(Tri.get(i).get(0) + ": ");
			for (String j : Tri.get(i).subList(1, Tri.get(i).size())) {
				write.print(j + " ");
			}
			write.print("\n");
		}
		write.close();
		reader.close();
		return Tri;
	}
}

class classList {
	public int currentIndex;
	public String identifier;
	public String className;
	public List<String> periods;
	// public List<Integer> tris;
	public String[][][] state;
	public classList prev;
	public classList next;
	public boolean first;

	public classList(String identifier, String className, List<String> periods/* , List<Integer> tris */) throws IOException {
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
			return this.next.createSched(pw);
		} else {
			do {
				boolean complete = false;
				while (!complete) {
					pw.println(this.className);
					if (this.currentIndex >= this.periods.size()) {
						this.currentIndex = 0;
						return false;
					}
					for(int z = 0; z < this.prev.state.length; z++) {
						for(int x = 0; x < this.prev.state[0].length; x++) {
							for(int c = 0; c < this.prev.state[0][0].length; c++) {
								this.state[z][x][c] = this.prev.state[z][x][c];
							}	
						}
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
						if (this.state[0][i[0]][i[1]] != null) {
							complete = false;
							break;
						} else {
							this.state[0][i[0]][i[1]] = this.identifier;
						}
					}
					for (int q = 0; q < this.state[0].length; q++) {
						for (int w = 0; w < this.state[0][0].length; w++) {
							if (this.state[0][q][w] == null) {
								pw.print("00000 ");
							} else {
								pw.print(this.state[0][q][w] + " ");
							}
						}
						pw.println();
					}
					this.currentIndex++;
				}
				if (this.next == null) {
					for (int q = 0; q < this.state[0].length; q++) {
						for (int w = 0; w < this.state[0][0].length; w++) {
							if (this.state[0][q][w] == null) {
								pw.print("00000 ");
							} else {
								pw.print(this.state[0][q][w] + " ");
							}
						}
					}
					break;
				}
			} while (!this.next.createSched(pw));
			pw.close();
			return true;
		}
	}
}
