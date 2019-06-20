import java.io.IOException;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class FinalSchmaker {

	public static HashMap<String, List<String>> Tri1;
	public static HashMap<String, List<String>> Tri2;
	public static HashMap<String, List<String>> Tri3;

	public static List<JComboBox> column1;
	public static List<JComboBox> column2;
	public static List<JComboBox> column3;

	public static String[][][] schedule;
	
	public static void addBox1(JPanel j, GridBagConstraints c, int number, String[] classes) {
		JComboBox temp = new JComboBox(classes);
		temp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (temp.getSelectedItem() != "") {
					if (column1.size() <= number) {
						addBox1(j, c, number + 1, classes);
					}
				}
			}
		});
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = number;
		j.add(temp, c);
		column1.add(temp);
		j.revalidate();
	}

	public static void addBox2(JPanel j, GridBagConstraints c, int number, String[] classes) {
		JComboBox temp = new JComboBox(classes);
		temp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (temp.getSelectedItem() != "") {
					if (column2.size() <= number) {
						addBox2(j, c, number + 1, classes);
					}
				}
			}
		});
		c.weighty = 1;
		c.gridx = 1;
		c.gridy = number;
		j.add(temp, c);
		column2.add(temp);
		j.revalidate();
	}

	public static void addBox3(JPanel j, GridBagConstraints c, int number, String[] classes) {
		JComboBox temp = new JComboBox(classes);
		temp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (temp.getSelectedItem() != "") {
					if (column3.size() <= number) {
						addBox3(j, c, number + 1, classes);
					}
				}
			}
		});
		c.weighty = 1;
		c.gridx = 2;
		c.gridy = number;
		j.add(temp, c);
		column3.add(temp);
		j.revalidate();
	}

	public static void createAndShowGUI(String[] classes1, String[] classes2, String[] classes3) {
		JFrame sch = new JFrame("Schmaker");
		column1 = new ArrayList<>();
		column2 = new ArrayList<>();
		column3 = new ArrayList<>();

		sch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel screen = new JPanel();

		JTabbedPane pane = new JTabbedPane();

		JPanel choosing = new JPanel();
		FJPanel first = new FJPanel();
		SJPanel second = new SJPanel();
		TJPanel third = new TJPanel();

		choosing.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTH;
		c.weighty = 0;

		JLabel one = new JLabel("Trimester 1");
		JLabel two = new JLabel("Trimester 2");
		JLabel three = new JLabel("Trimester 3");
		c.insets = new Insets(7, 0, 4, 0);
		c.gridx = 0;
		choosing.add(one, c);
		c.gridx = 1;
		choosing.add(two, c);
		c.gridx = 2;
		choosing.add(three, c);

		c.insets = new Insets(0, 0, 0, 0);
		c.gridy = 1;
		c.weighty = 1;
		JComboBox box1 = new JComboBox(classes1);
		column1.add(box1);
		box1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (box1.getSelectedItem() != "") {
					if (column1.size() <= 1) {
						addBox1(choosing, c, 2, classes1);
					}
				}
			}
		});
		c.gridx = 0;

		choosing.add(box1, c);

		JComboBox box2 = new JComboBox(classes2);
		column2.add(box2);
		box2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (box2.getSelectedItem() != "") {
					if (column2.size() <= 1) {
						addBox2(choosing, c, 2, classes2);
					}
				}
			}
		});
		c.gridx = 1;
		choosing.add(box2, c);

		JComboBox box3 = new JComboBox(classes3);
		column3.add(box3);
		box3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (box3.getSelectedItem() != "") {
					if (column3.size() <= 1) {
						addBox3(choosing, c, 2, classes3);
					}
				}
			}
		});
		c.gridx = 2;
		choosing.add(box3, c);

		box1.setVisible(true);
		box2.setVisible(true);
		box3.setVisible(true);

		JButton ready = new JButton("Click for Schedule");
		c.gridx = 0;
		c.gridy = 40;
		c.weighty = 0;
		c.insets = new Insets(80, 0, 8, 0);
		ready.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> ff = new ArrayList<>();
				for (JComboBox i : column1) {
					String hi = i.getSelectedItem().toString();
					if (hi != "") {
						ff.add(hi.split(" ")[0]);
					}
				}
				List<String> ss = new ArrayList<>();
				for (JComboBox i : column2) {
					String hi = i.getSelectedItem().toString();
					if (hi != "") {
						ss.add(hi.split(" ")[0]);
					}
				}
				List<String> tt = new ArrayList<>();
				for (JComboBox i : column3) {
					String hi = i.getSelectedItem().toString();
					if (hi != "") {
						tt.add(hi.split(" ")[0]);
					}
				}
				try {
					schmaker s = new schmaker();
					schedule = s.create(ff, ss, tt);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (schedule[0][0][0] == null) {
					first.ppaint(schedule[0]);
					for (int i = 0; i < schedule[0].length; i++) {
						for (int j = 0; j < schedule[0][0].length; j++) {
							System.out.print(schedule[0][i][j]);
						}
						System.out.println();
					}
					second.ppaint(schedule[1]);
					third.ppaint(schedule[2]);
				} else {
					if (schedule[0][0][0].equals("NO")) {

					} else {
						first.ppaint(schedule[0]);
						second.ppaint(schedule[1]);
						third.ppaint(schedule[2]);
					}
				}
			}
		});
		choosing.add(ready, c);
		c.insets = new Insets(0, 0, 0, 0);

		choosing.setPreferredSize(new Dimension(900, 100));

		pane.addTab("Courses", choosing);
		pane.addTab("First Tri", first);
		pane.addTab("Second Tri", second);
		pane.addTab("Third Tri", third);
		screen.add(pane);
		screen.setSize(screen.getWidth(), 500);

		sch.add(screen);

		pane.setVisible(true);
		sch.pack();
		sch.setSize(sch.getWidth(), 550);
		sch.setLocationRelativeTo(null);
		sch.setVisible(true);
	}

	public static void main(String[] args) throws IOException {
		schmaker s = new schmaker();
		
		Tri1 = s.readFile("Tri1.txt");
		Tri2 = s.readFile("Tri2.txt");
		Tri3 = s.readFile("Tri3.txt");

		schedule = new String[3][9][6];

		String[] classes1 = new String[Tri1.size() + 1];
		List<String> keys = new ArrayList<>(Tri1.keySet());
		for (int i = 0; i < classes1.length - 1; i++) {
			classes1[i] = keys.get(i) + " " + Tri1.get(keys.get(i)).get(0);
		}
		classes1[classes1.length - 1] = "";
		Arrays.sort(classes1);

		String[] classes2 = new String[Tri2.size() + 1];
		keys = new ArrayList<>(Tri2.keySet());
		for (int i = 0; i < classes2.length - 1; i++) {
			classes2[i] = keys.get(i) + " " + Tri2.get(keys.get(i)).get(0);
		}
		classes2[classes2.length - 1] = "";
		Arrays.sort(classes2);

		String[] classes3 = new String[Tri3.size() + 1];
		keys = new ArrayList<>(Tri3.keySet());
		for (int i = 0; i < classes3.length - 1; i++) {
			classes3[i] = keys.get(i) + " " + Tri3.get(keys.get(i)).get(0);
		}
		classes3[classes3.length - 1] = "";
		Arrays.sort(classes3);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI(classes1, classes2, classes3);
			}
		});
	}

}

class FJPanel extends JPanel {

	public String[][] sched;

	public FJPanel() {
		this.setPreferredSize(new Dimension(400, 475));
		sched = new String[9][6];
	}

	public void ppaint(String[][] sched) {
		this.sched = sched;
		this.repaint();
	}

	public void paint(Graphics g) {
		Color c = new Color(252, 248, 221);
		g.setColor(c);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.BLUE);
		createGrid(g);
		displayClasses(g);
	}

	public void displayClasses(Graphics g) {
		int startx = 175;
		int starty = 80;
		int disx = this.getWidth() / 7;
		int disy = this.getHeight() / 10;
		String[] arr = { "1", "2", "3", "4", "5", "Lab" };
		for (int i = 0; i < arr.length; i++) {
			g.drawString(arr[i], startx + 10 + disx * i, starty - disy);
		}
		for (int i = 0; i < 10; i++) {
			int hi = 'A' + i;
			char y = (char) hi;
			g.drawString(Character.toString(y), startx - disx + 10, starty + disy * i);
		}
		for (int i = 0; i < sched.length; i++) {
			for (int j = 0; j < sched[0].length; j++) {
				if (sched[i][j] == null) {
					g.drawString("", startx + disx * j, starty + disy * i);
				} else {
					g.drawString(sched[i][j], startx + disx * j, starty + disy * i);
				}
			}
		}
	}

	public void createGrid(Graphics g) {
		g.setColor(Color.black);

		int num = 10;

		for (int i = 1; i < num; i++) {
			int y = (i * this.getHeight()) / num;
			g.fillRect(0, y, this.getWidth(), 2);
		}
		num = 7;

		for (int i = 1; i < num; i++) {
			int y = (i * this.getWidth()) / num;
			g.fillRect(y, 0, 2, this.getHeight());
		}
	}
}

class SJPanel extends JPanel {
	public String[][] sched;

	public SJPanel() {
		this.setPreferredSize(new Dimension(400, 475));
		sched = new String[9][6];
	}

	public void ppaint(String[][] sched) {
		this.sched = sched;
		this.repaint();
	}

	public void paint(Graphics g) {
		Color c = new Color(252, 248, 221);
		g.setColor(c);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.BLUE);
		createGrid(g);
		displayClasses(g);
	}

	public void displayClasses(Graphics g) {
		int startx = 175;
		int starty = 80;
		int disx = this.getWidth() / 7;
		int disy = this.getHeight() / 10;
		String[] arr = { "1", "2", "3", "4", "5", "Lab" };
		for (int i = 0; i < arr.length; i++) {
			g.drawString(arr[i], startx + 10 + disx * i, starty - disy);
		}
		for (int i = 0; i < 10; i++) {
			int hi = 'A' + i;
			char y = (char) hi;
			g.drawString(Character.toString(y), startx - disx + 10, starty + disy * i);
		}
		for (int i = 0; i < sched.length; i++) {
			for (int j = 0; j < sched[0].length; j++) {
				if (sched[i][j] == null) {
					g.drawString("", startx + disx * j, starty + disy * i);
				} else {
					g.drawString(sched[i][j], startx + disx * j, starty + disy * i);
				}
			}
		}
	}

	public void createGrid(Graphics g) {
		g.setColor(Color.black);

		int num = 10;

		for (int i = 1; i < num; i++) {
			int y = (i * this.getHeight()) / num;
			g.fillRect(0, y, this.getWidth(), 2);
		}
		num = 7;

		for (int i = 1; i < num; i++) {
			int y = (i * this.getWidth()) / num;
			g.fillRect(y, 0, 2, this.getHeight());
		}
	}
}

class TJPanel extends JPanel {
	public String[][] sched;

	public TJPanel() {
		this.setPreferredSize(new Dimension(400, 475));
		sched = new String[9][6];
	}

	public void ppaint(String[][] sched) {
		this.sched = sched;
		this.repaint();
	}

	public void paint(Graphics g) {
		Color c = new Color(252, 248, 221);
		g.setColor(c);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.BLUE);
		createGrid(g);
		displayClasses(g);
	}

	public void displayClasses(Graphics g) {
		int startx = 175;
		int starty = 80;
		int disx = this.getWidth() / 7;
		int disy = this.getHeight() / 10;
		String[] arr = { "1", "2", "3", "4", "5", "Lab" };
		for (int i = 0; i < arr.length; i++) {
			g.drawString(arr[i], startx + 10 + disx * i, starty - disy);
		}
		for (int i = 0; i < 10; i++) {
			int hi = 'A' + i;
			char y = (char) hi;
			g.drawString(Character.toString(y), startx - disx + 10, starty + disy * i);
		}
		for (int i = 0; i < sched.length; i++) {
			for (int j = 0; j < sched[0].length; j++) {
				if (sched[i][j] == null) {
					g.drawString("", startx + disx * j, starty + disy * i);
				} else {
					g.drawString(sched[i][j], startx + disx * j, starty + disy * i);
				}
			}
		}
	}

	public void createGrid(Graphics g) {
		g.setColor(Color.black);

		int num = 10;

		for (int i = 1; i < num; i++) {
			int y = (i * this.getHeight()) / num;
			g.fillRect(0, y, this.getWidth(), 2);
		}
		num = 7;

		for (int i = 1; i < num; i++) {
			int y = (i * this.getWidth()) / num;
			g.fillRect(y, 0, 2, this.getHeight());
		}
	}
}
