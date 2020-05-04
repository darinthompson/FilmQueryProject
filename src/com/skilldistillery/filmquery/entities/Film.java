package com.skilldistillery.filmquery.entities;

import java.util.ArrayList;
import java.util.List;

public class Film {
	private String title;
	private String description;
	private String rating;
	private int releaseYear;
	private String language;
	private List<Actor> actors;
	
	public Film(String _title, String _description, String _rating, int _releaseYear, String _language) {
		title = _title;
		description = _description;
		releaseYear = _releaseYear;
		language = _language;
		rating = _rating;
	}

	public void setTitle(String _title) {
		title = _title;
	}

	public String getTitle() {
		return title;
	}

	public void setDescription(String _description) {
		description = _description;
	}

	public String getDescription() {
		return description;
	}

	public void setReleaseYear(int _releaseYear) {
		_releaseYear = releaseYear;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public String toString() {
		return ("TITLE: " + title + "\nDESCRIPTION: " + description +"\nRATING: " + rating + "\nCAST: " + showActors() + "\nRELEASE YEAR: "
				+ releaseYear + "\nLANGUAGE: " + language);
	}

	public void addActorToCast(Actor _actor) {
		if (actors == null) {
			actors = new ArrayList<>();
		}
		if (_actor != null) {
			actors.add(_actor);
		} else {
			return;
		}
	}

	private String showActors() {
		String cast = "";
		if(actors != null) {
			for (Actor actor : actors) {
				cast += actor.getFullName() + ", ";
			}
		}
		return cast;
	}
	
	public List<Actor> getActors() {
		return actors;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + releaseYear;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (releaseYear != other.releaseYear)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}
