package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
		app.test();
		app.launch();

	}

	private void test() throws SQLException {
		db.findFilmById(-42);
	}

	private void launch() throws SQLException {
		Scanner input = new Scanner(System.in);
		int choice = 0;
		do {
			startUserInterface();
			choice = ValidateInt("CHOICE >>");
			switch (choice) {
			case 1:
				int searchId = ValidateInt("Movie ID >>");
				SearchById(searchId);
				break;
			case 2:
				System.out.print("SEARCH >> ");
				String query = input.nextLine();
				SearchByTitleOrDescription(query);
				break;
			case 3:
				System.out.println("GOOD_BYE!");
				break;
			default:
				System.out.println("Not a valid choice. Try again!");
				break;
			}
			input.nextLine();
		} while (choice != 3);
		input.close();
	}

	private void startUserInterface() {
		System.out.println("1. Search For Movie By ID");
		System.out.println("2. Search For Movie By Title or Description");
		System.out.println("3. Exit");
	}

	private void SearchByTitleOrDescription(String searchQuery) throws SQLException {
		List<Film> films = db.findFilmByStringSearch(searchQuery);
		if (films == null || films.size() == 0) {
			System.out.println("SORRY, NOTHING MATCHES YOUR SEARCH.");
		} else {
			for (Film f : films) {
				System.out.println("------------------------------");
				System.out.println(f.toString());
			}
		}
	}

	private void SearchById(int id) throws SQLException {
		Film film = db.findFilmById(id);
		if (film != null) {
			System.out.println(film.toString());
		} else {
			System.out.println("No Matches.");
		}
	}

	private int ValidateInt(String prompt) {
		Scanner scan = new Scanner(System.in);
		int userNum;
		while (true) {
			System.out.print(prompt);
			try {
				userNum = scan.nextInt();
				break;
			} catch (Exception e) {
				System.out.println("Invalid amount(Must be a number).");
				System.out.println();
				scan.nextLine();
			}
		}
		scan.nextLine();
		return userNum;
	}

}
