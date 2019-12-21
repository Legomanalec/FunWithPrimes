package turtleTesting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PrimeVisual extends JFrame {

	// Used for scanner input variable
	// Upper bound of the for loop
	private int numberAmmount = 0;
	
	//used for drawing horizontal line between largest gap
	

	/**
	 * Constructor, creates JFrame, initializes numberAmount
	 * 
	 * @param number
	 */
	public PrimeVisual(int number) {
		super("Random Turtle");
		setBounds(100, 100, 1300, 800);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		numberAmmount = number;
		
	}

	/**
	 * Draws a vertical line
	 * 
	 * @param g
	 * @param x
	 * @param y
	 * @param length
	 */
	private void DrawVertLine(Graphics g, int x, int y, int length) {
		g.drawLine(x, y, x, y + length);
	}
	
	/**
	 * Draws a horizontal line between the largest gap of non-primes
	 * @param g
	 * @param n
	 */
	private void DrawGapLine(Graphics g, int x, int y, int length) {
		g.drawLine(x, y, x + length, y);
	}

	/**
	 * Method to efficiently check if an integer is prime
	 * 
	 * @param n
	 * @return
	 */
	public static boolean isPrime(int n) {
		if (n == 0)
			return false;
		for (int i = 2; i <= (int) Math.ceil(Math.sqrt(n)); i++)
			if (n % i == 0 && n != 2)
				return false;
		if (n == 1)
			return false;
		return true;
	}
	
	/**
	 * less efficient way to find primes
	 * @param n
	 * @return
	 */
	public static boolean isPrimeWorse(int n) {
		if (n == 0)
			return false;
		for (int i = (int) Math.ceil(Math.sqrt(n)); i > 1; i--)
			if (n % i == 0 && n != 2)
				return false;
		if (n == 1)
			return false;
		return true;
	}

	// Not working
	//Need to figure out how longs work 
	public static boolean isPrimeLong(long n) {
		for (long i = (long) Math.ceil(Math.sqrt(n)); i > 1; i--)
			if (n % i == 0 && n != 2)
				return false;
		if (n == 1)
			return false;
		return true;
	}

	/**
	 * A method to determine the percent of prime numbers in an a sequence of all
	 * real integers
	 * 
	 * @param num
	 * @return
	 */
	public static double PercentPrimeAllInt(int num) {
		double k = 0;
		double j = 0;
		for (int i = 0; i < num; i++) {
			if (isPrime(i))
				k++;
			j++;
		}
		return (k / j) * 100;
	}

	/**
	 * A method to return the percent of prime numbers to a sequence of odd numbers
	 * "num" long
	 * 
	 * @param num
	 * @return
	 */
	public static double PercentPrimeAllOddInt(int num) {
		double k = 0;
		double j = 0;
		for (int i = 1; i < num; i += 2) {
			if (isPrime(i))
				k++;
			j++;
		}
		return (k / j) * 100;
	}

	/**
	 * A method that returns the percent of prime numbers in the first "num" numbers
	 * that are odd and don't end in 5
	 * 
	 * @param num
	 * @return
	 */
	public static double PercentPrimeAllOddIntNoFive(int num) {
		int k = 0;

		// prime count
		double j = 0;
		double i = 0;
		while (i < num) {
			if (k % 2 == 1 && k % 5 != 0) {
				i++;
				if (isPrime(k))
					j++;
			}
			k++;
		}
		return (j / i) * 100;
	}

	/**
	 * Uses DrawVertLine to place the lines this method is pretty much all of the
	 * logic for this program
	 */
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		int y = 55;
		int x = -40;

		for (int i = 1; i < numberAmmount; i++) {
			if (isPrime(i)) {
				DrawVertLine(g, i - x, y, -25);

			}

			// adds a new line to the visual when each line is greater than 1200
			if (i % 1200 == 0) {
				y += 27;
				x += 1200;
			}
		}
	}
	

	public static String primeStats(int num) {
		int countPrimeStats = 0;
		int largestPrimeStats = 0;
		int tempPrimeStats = 0;
		for (int i = 1; i < num; i++) {
			if (isPrime(i)) {
				countPrimeStats = 0;
			}

			else {
				countPrimeStats++;
				if (countPrimeStats > largestPrimeStats) {
					largestPrimeStats++;
					tempPrimeStats = i;
				}
			}
		}
		
		
		return "The longest run of non-prime numbers is: " + largestPrimeStats + "." + " The numbers "
				+ (tempPrimeStats - largestPrimeStats + 1) + "-" + tempPrimeStats + " are all non-prime.";
	}

	/**
	 * list of primes <= n
	 * 
	 * @param n
	 * @return
	 */
	public static ArrayList<Integer> primeList(int n) {
		ArrayList<Integer> list = new ArrayList<Integer>();

		for (int i = 0; i <= n; i++) {
			if (isPrime(i))
				list.add(i);
		}
		return list;
	}

	/**
	 * Uses fundamental theory of arithmatic to find the unique prime factorization
	 * of given number
	 * 
	 * @param n
	 * @return
	 */
	public static String primeFactorization(int n) {
		ArrayList<Integer> list = primeList(n);
		ArrayList<Integer> finalList = new ArrayList<Integer>();
		String factorization = "";

		for (int i = 0; i < list.size(); i++) {
			if (n % list.get(i) == 0) {
				n = n / list.get(i);
				finalList.add(list.get(i));
				i--;
			}
		}

		for (int num : finalList.subList(1, finalList.size())) {

			factorization += " x " + num;
		}

		return finalList.get(0) + factorization;
	}

	public static void main(String[] args) {

		// JFrame for excicutable
		JFrame window = new JFrame();
		window.setTitle("Fun with Primes");
		window.setSize(600, 400);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Prime visual
		JPanel mainPanel = new JPanel();
		JTextField num = new JTextField();
		JButton testNum = new JButton("Test");
		JLabel primeLabel = new JLabel();

		mainPanel.add(num);
		num.setColumns(10);
		mainPanel.add(testNum);
		mainPanel.add(primeLabel);

		// Prime factorization
		JPanel factorizationPanel = new JPanel();
		JTextField factorizationField = new JTextField();
		JButton factorizationRun = new JButton("Factorization");
		JLabel factorizationLabel = new JLabel();

		factorizationPanel.add(factorizationLabel);
		factorizationPanel.add(factorizationField);
		factorizationField.setColumns(10);
		factorizationPanel.add(factorizationRun);

		// Prime percentages
		JPanel percentPanel = new JPanel();
		JComboBox<String> percentMethod = new JComboBox<String>();
		JTextField percentAmount = new JTextField();
		JButton percentRun = new JButton("Test");
		JLabel percentLabel = new JLabel();

		percentMethod.addItem("All Positive Integers");
		percentMethod.addItem("No Even");
		percentMethod.addItem("No Even, No 5");
		percentMethod.addItem("Mersenne Primes (not working)");

		percentPanel.add(percentMethod);
		percentPanel.add(percentLabel);
		percentPanel.add(percentAmount);
		percentAmount.setColumns(10);
		percentPanel.add(percentRun);
		
		JPanel timeTook = new JPanel();
		JLabel time = new JLabel();
		timeTook.add(time);

		// JFrame main
		window.add(mainPanel);
		window.add(factorizationPanel);
		window.add(percentPanel);
		window.add(timeTook);

		window.getContentPane().setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));

		// prime visual button
		testNum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long start = System.nanoTime();
				new PrimeVisual(Integer.parseInt(num.getText())).setVisible(true);
				long end = System.nanoTime();
				time.setText("This calculation took " + (Long.toString((end - start) / 1000000)) + "ms");
				primeLabel.setText(primeStats(Integer.parseInt(num.getText())));

			}
		});

		// factorization button
		factorizationRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long start = System.nanoTime();
				factorizationLabel.setText(primeFactorization(Integer.parseInt(factorizationField.getText())));
				long end = System.nanoTime();
				time.setText("This calculation took " + (Long.toString((end - start) / 1000000)) + "ms");
			}

		});

		// percent test button
		percentRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (percentMethod.getSelectedItem().equals("All Positive Integers")) {
					long start = System.nanoTime();
					percentLabel
							.setText(Double.toString(PercentPrimeAllInt(Integer.parseInt(percentAmount.getText()))));
					long end = System.nanoTime();
					time.setText("This calculation took " + (Long.toString((end - start) / 1000000)) + "ms");
				}

				if (percentMethod.getSelectedItem().equals("No Even")) {
					long start = System.nanoTime();
					percentLabel
							.setText(Double.toString(PercentPrimeAllOddInt(Integer.parseInt(percentAmount.getText()))));
					long end = System.nanoTime();
					time.setText("This calculation took " + (Long.toString((end - start) / 1000000)) + "ms");
				}

				if (percentMethod.getSelectedItem().equals("No Even, No 5")) {
					long start = System.nanoTime();
					percentLabel.setText(
							Double.toString(PercentPrimeAllOddIntNoFive(Integer.parseInt(percentAmount.getText()))));
					long end = System.nanoTime();
					time.setText("This calculation took " + (Long.toString((end - start) / 1000000)) + "ms");
				}

				if (percentMethod.getSelectedItem().equals("Mersenne Prime")) {
					//Need to build Mersenne Prime method somehow...
					
				}

			}
		});

		window.setVisible(true);

	}

}
