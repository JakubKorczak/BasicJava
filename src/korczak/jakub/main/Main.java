package korczak.jakub.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;
import javax.xml.ws.Holder;

public class Main

// ***********|||||| ZADANIA Z KURSU JAVA PRZYGOTOWANE PRZEZ KM PROGRAMS
// http://km-programs.pl/kursy-programowanie/#java

// Loops, conditional statements, arrays, collections, JAVA 8, lambda
// expressions,
// streams, regular expressions, file reader.

{
	/*
	 * Pobierz od uzytkownika napis i sprawdz, ile wystepuje w nim wyrazow.
	 * Zbadaj, ile wyrazow zaczyna sie z duzej litery oraz ile wyrazow zaczyna
	 * sie z malej litery. Wypisz na ekranie otrzymane wyniki.
	 */

	public static void countingWords()
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Enter string");
		String words = input.nextLine();

		String[] bunchOfWords = words.split(" ");

		int upperLettersCounter = 0;
		int lowerLettersCounter = 0;

		if (words.isEmpty())
		{
			System.out.println("String is empty.");
		} else
		{
			char z;
			for (int i = 0; i < bunchOfWords.length; i++)
			{
				z = bunchOfWords[i].charAt(0);
				if (String.valueOf(z).matches("[A-Z]"))
				{
					upperLettersCounter++;
				} else if (Character.isLowerCase(z))
				{
					lowerLettersCounter++;
				}
			}

			System.out.println("There are " + upperLettersCounter + " words starting with upper case letter and "
					+ lowerLettersCounter + " words starting with lower case letter.");
		}

	}

	/*
	 * Pobieraj od uzytkownika napis, dopoki nie bedzie zawieral‚ poprawnie
	 * zapisanej liczby. Zakladamy, ze poprawnie zapisana liczba to taka, do
	 * ktorej zapisu uzyto "zwyklego" zapisu lub notacji naukowej. Przyklady
	 * poprawnie zapisanych liczb: 2.4, -12.45, 10E12, -5.45E9, 8E-3, 23.34e10,
	 * 24.3e-5.
	 */

	public static boolean checkingIfInScientificNotation(String inputWord)
	{
		final String REGEX = "-?[0-9]+[E]?-?[//.]?[0-9]*[Ee]?-?[0-9]*";
		return inputWord.matches(REGEX);
	}

	/*
	 * Pobierz od uzytkownika liste dowolnych marek samochodow(5). Nastepnie
	 * stosujac strumienie zwroc kolekcje Set marek, kore maja w nazwie
	 * skladajacej sie z samych duzych liter, co najmniej 3 samogloski, ktorych
	 * suma kodow ASCII jest liczba parzysta o nieparzystej cyfrze dziesiatek.
	 */
	public static void brandsOfCars()
	{
		final int SIZE = 5;
		System.out.println("Enter five brands of cars\n" + "=========================");

		String brand;
		Scanner input = new Scanner(System.in);
		List<String> groupOfBrands = new ArrayList<>();

		for (int i = 0; i < SIZE; i++)
		{
			do
			{
				System.out.println("Enter a brand (in upper case letter):");
				brand = input.nextLine();
			} while (!brand.matches("[A-Z]+"));
			System.out.println("Brand number " + i + " was added.");
			groupOfBrands.add(brand);
		}

		Predicate<String> p1 = nap ->
		{
			int counterOfVowels = 0;
			for (int i = 0; i < nap.length(); i++)
			{
				String oneLetter = String.valueOf(nap.charAt(i));
				if (oneLetter.matches("[aeyuioAEYUIO]"))
				{
					counterOfVowels++;
				}
			}

			return counterOfVowels >= 3;
		};

		Predicate<String> p2 = nap ->
		{
			int sumASCII = 0;
			for (int i = 0; i < nap.length(); i++)
			{
				sumASCII += nap.charAt(i);
			}

			return sumASCII % 2 == 0 && ((sumASCII % 100) / 10) % 2 != 0;

		};

		Set<String> filteredBrands = groupOfBrands.stream().filter(p1.and(p2)).collect(Collectors.toSet());

		filteredBrands.forEach(System.out::println);
	}

	/*
	 * Wygeneruj 100 liczb losowych z przedziału <20, 45> i zapisz je do pliku
	 * tekstowego �?liczby.txt’ w postaci: liczba1;liczba2;liczba3;… . Następnie
	 * pobierz zawartość pliku �?liczby.txt’ i oblicz odchylenie standardowe
	 * pobranych liczb.
	 */

	public static boolean standardDeviation() throws IOException
	{
		final int HOW_MANY_NUMBERS = 100;
		final int RANGE_DOWN = 20;
		final int RANGE_UP = 45;

		String fileName = "liczby.txt";
		Random randomNumbers = new Random();
		Writer writer = new FileWriter(fileName);

		for (int i = 0; i < HOW_MANY_NUMBERS; i++)
		{
			int r = RANGE_DOWN + randomNumbers.nextInt(RANGE_UP - RANGE_DOWN + 1);
			writer.write(new Integer(r).toString() + ";");
		}
		writer.close();

		File textFile = new File(fileName);
		Scanner input = new Scanner(textFile);

		double variance = 0;
		while (input.hasNextLine())
		{
			String[] groupOfStrings = input.nextLine().split(";");

			List<Double> numbers = Arrays.stream(groupOfStrings).map(e -> Double.parseDouble(e))
					.collect(Collectors.toList());
			double avarageValue = numbers.stream().collect(Collectors.summarizingDouble(e -> e)).getAverage();
			double sum = numbers.stream().reduce((suma, el) -> (suma + Math.pow((el - avarageValue), 2))).get();
			variance = Math.sqrt(sum / numbers.size());

			System.out.printf("Standard deviation = %.2f\n", variance);
		}

		if (variance > 6 && variance < 9)
		{
			return true;
		} else
		{
			return false;
		}
	}

	public static void showMenu()
	{
		String printMenu = "MENU - FUNCTIONS\n================\n";
		printMenu += "Choose an option:\n";
		printMenu += "\t1.Counting Words\n";
		printMenu += "\t2.Checking if number is in scientific notation\n";
		printMenu += "\t3.Brands of cars\n";
		printMenu += "\t4.Standard deviation\n";
		printMenu += "\t5.EXIT PROGRAM";

		System.out.println(printMenu);
		System.out.print("Your option is: ");
	}

	public static int optionChoice()
	{
		Scanner inputOption = new Scanner(System.in);
		int choice = Integer.parseInt(inputOption.nextLine());
		return choice;
	}

	public static void functionSwitcher()
	{
		boolean isExit = false;

		while (isExit == false)
		{
			showMenu();
			switch (optionChoice())
			{
			case 1:
				countingWords();
				break;
			case 2:
				boolean end = false;
				do
				{
					Scanner inputNumber = new Scanner(System.in);
					System.out.println("Enter number in scientific notation or write END");
					String number = inputNumber.nextLine();
					if (checkingIfInScientificNotation(number) == true || number.equals("END"))
					{
						System.out.println("Number: " + number + " is in scientific notation");
						end = true;
					}
				} while (end == false);
				break;
			case 3:
				brandsOfCars();
				break;
			case 4:
				try
				{
					standardDeviation();
				} catch (IOException e)
				{
					System.out.println("File not found");
					e.printStackTrace();
				}
				break;
			case 5:
				System.out.println("Program closed");
				isExit = true;
				break;
			default:
				JOptionPane.showMessageDialog(null, "Wrong choice! Try again.");
				break;
			}
		}
	}

	public static void main(String[] args)
	{
		functionSwitcher();
	}

}