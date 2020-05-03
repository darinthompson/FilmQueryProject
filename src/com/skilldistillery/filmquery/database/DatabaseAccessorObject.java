package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private static final String user = "student";
	private static final String password = "student";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Actor findActorById(int actorId) throws SQLException {
		Actor actor = null;
		String selectStatement = "SELECT * FROM actor WHERE actor.id = ?";
		Connection conn = DriverManager.getConnection(URL, user, password);
		PreparedStatement pst = conn.prepareStatement(selectStatement);
		pst.setInt(1, actorId);

		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			actor = new Actor(rs.getString("actor.first_name"), rs.getString("actor.last_name"));
		}
		rs.close();
		pst.close();
		conn.close();

		return actor;
	}

	@Override
	public Film findFilmById(int filmId) throws SQLException {
		Film film = null;
		String selectStatement = "SELECT film.*, language.name FROM film JOIN language ON film.language_id = language.id WHERE film.id = ?";

		Connection conn = DriverManager.getConnection(URL, user, password);
		PreparedStatement pst = conn.prepareStatement(selectStatement);
		pst.setInt(1, filmId);

		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			film = new Film(rs.getNString("film.title"), rs.getString("film.description"), rs.getString("film.rating"),
					rs.getInt("film.release_year"), rs.getString("language.name"));
			addActorToFilm(film, user, password, filmId);
		}
		rs.close();
		pst.close();
		conn.close();

		return film;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) throws SQLException {
		return findFilmById(filmId).getActors();
	}

	private void addActorToFilm(Film _film, String _user, String _password, int _filmId) throws SQLException {
		String selectStatement = "SELECT actor.first_name, actor.last_name, film.title FROM film_actor JOIN film ON film.id = film_actor.film_id JOIN actor ON film_actor.actor_id = actor.id WHERE film.id = ?";
		Connection conn = DriverManager.getConnection(URL, _user, _password);
		PreparedStatement pst = conn.prepareStatement(selectStatement);
		pst.setInt(1, _filmId);

		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			Actor actor = new Actor(rs.getString("actor.first_name"), rs.getString("actor.last_name"));
			_film.addActorToCast(actor);
		}
		rs.close();
		pst.close();
		conn.close();
	}

	public List<Film> findFilmByStringSearch(String search) throws SQLException {
		Film film = null;
		List<Film> films = new ArrayList<>();
		String selectStatement = "SELECT film.*, language.name FROM film JOIN language ON film.language_id = language.id WHERE film.title LIKE ? OR film.description LIKE ?";
		Connection conn = DriverManager.getConnection(URL, user, password);
		PreparedStatement pst = conn.prepareStatement(selectStatement);
		pst.setString(1, "%" + search + "%");
		pst.setString(2, "%" + search + "%");
		ResultSet rs = pst.executeQuery();

		while (rs.next()) {
			film = new Film(rs.getString("film.title"), rs.getString("film.description"), rs.getString("film.rating"),
					rs.getInt("film.release_year"), rs.getString("language.name"));
			if (film != null) {
				addActorToFilm(film, user, password, rs.getInt("film.id"));
				films.add(film);
			}
		}
		rs.close();
		pst.close();
		conn.close();
		return films;
	}
}
