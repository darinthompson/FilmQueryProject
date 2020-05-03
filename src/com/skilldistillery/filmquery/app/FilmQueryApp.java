package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
		app.test();
//    app.launch();
	}

	private void test() throws SQLException {
		Film film = db.findFilmById(7);
		Actor actor = db.findActorById(-2);
		List<Actor> cast = db.findActorsByFilmId(13);
		Scanner sc = new Scanner(System.in);
		System.out.print("SEARCH >> ");
		String search = sc.nextLine();
		List<Film> films = db.findFilmByStringSearch(search);
//		if (films != null) {
//			for (Film f : films) {
//				System.out.println(f.toString());
//			}
//		} else {
//			System.out.println("SORRY! No matches found.");
//		}
		if(films == null || films.size() == 0) {
			System.out.println("SORRY, NOTHING MATCHES YOUR SEARCH.");
		}
		for (Film f : films) {
			System.out.println(f.toString());
		}
	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		System.out.println("1. Search For Movie By ID");
		System.out.println("2. Find an Actor By ID");
		System.out.println("3. Get Cast List based on movie ID");
		System.out.print("CHOICE >>");
	}

}
