package co.edu.unbosque.netflixbackend.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import co.edu.unbosque.netflixbackend.repository.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import co.edu.unbosque.netflixbackend.model.Documental;
import co.edu.unbosque.netflixbackend.model.Episodio;
import co.edu.unbosque.netflixbackend.model.Genero;
import co.edu.unbosque.netflixbackend.model.Pelicula;
import co.edu.unbosque.netflixbackend.model.Serie;
import co.edu.unbosque.netflixbackend.model.Temporada;

public class ExternalHTTPRequestHandler {

	private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
	private static final String API_KEY = "4584014a9db6264fabab2596a1da86b2";

	private static final String BASE_URL_PELICULAS = "https://api.themoviedb.org/3/discover/movie?api_key=" + API_KEY
			+ "&with_origin_country=US&language=es-ES";
	private static final String BASE_URL_DOCUMENTALES = "https://api.themoviedb.org/3/discover/movie?api_key=" + API_KEY
			+ "&with_genres=99&language=es-ES";

	public static List<Genero> obtenerGeneros() {
		List<Genero> listaGeneros = new ArrayList<>();

		String url = "https://api.themoviedb.org/3/genre/movie/list?api_key=" + API_KEY + "&language=es-ES";
		String jsonResponse = doGet(url);

		if (jsonResponse == null) {
			System.out.println("⚠️ No se pudo obtener la lista de géneros.");
			return listaGeneros;
		}

		JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
		JsonArray generosArray = jsonObject.getAsJsonArray("genres");

		for (JsonElement element : generosArray) {
			JsonObject generoObj = element.getAsJsonObject();
			Genero genero = new Genero();

			if (generoObj.has("id"))
				genero.setIdGenero(generoObj.get("id").getAsInt());
			if (generoObj.has("name"))
				genero.setNombre(generoObj.get("name").getAsString());

			listaGeneros.add(genero);
		}

		return listaGeneros;
	}

	// Método genérico para hacer peticiones HTTP GET
	public static String doGet(String url) {
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url))
				.header("Content-Type", "application/json").build();

		try {
			HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() != 200) {
				System.out.println("⚠️ Error HTTP " + response.statusCode());
				return null;
			}

			return response.body();

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}

	// 🔹 Obtener todas las películas
	public static String getAllMovies(int totalPages) {
		return fetchFromTMDB(BASE_URL_PELICULAS, totalPages);
	}

	// 🔹 Obtener todos los documentales
	public static String getAllDocumentaries(int totalPages) {
		return fetchFromTMDB(BASE_URL_DOCUMENTALES, totalPages);
	}

	// 🔹 Método genérico que obtiene los resultados paginados
	private static String fetchFromTMDB(String baseUrl, int totalPages) {
		JsonArray allItems = new JsonArray();

		for (int page = 1; page <= totalPages; page++) {
			String url = baseUrl + "&page=" + page;
			System.out.println("🔹 Consultando página " + page + ": " + url);

			String jsonResponse = doGet(url);
			if (jsonResponse == null)
				continue;

			JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

			if (jsonObject.has("results")) {
				JsonArray results = jsonObject.getAsJsonArray("results");
				for (JsonElement item : results) {
					allItems.add(item);
				}
			}

			int totalAvailablePages = jsonObject.has("total_pages") ? jsonObject.get("total_pages").getAsInt()
					: totalPages;

			if (page >= totalAvailablePages)
				break;
		}

		JsonObject finalJson = new JsonObject();
		finalJson.add("results", allItems);

		return prettyPrintUsingGson(finalJson.toString());
	}

	private static String prettyPrintUsingGson(String uglyJson) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonElement jsonElement = JsonParser.parseString(uglyJson);
		return gson.toJson(jsonElement);
	}

	// 🔸 Convierte JSON en objetos Pelicula
	public static List<Pelicula> obtenerPeliculas(int totalPages) {
		List<Pelicula> listaPeliculas = new ArrayList<>();

		String jsonResponse = getAllMovies(totalPages);
		JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
		JsonArray moviesArray = jsonObject.getAsJsonArray("results");

		for (JsonElement element : moviesArray) {
			JsonObject movieObj = element.getAsJsonObject();
			Pelicula pelicula = new Pelicula();

			if (movieObj.has("id"))
				pelicula.setIdPelicula(movieObj.get("id").getAsInt());
			if (movieObj.has("title"))
				pelicula.setNombre(movieObj.get("title").getAsString());
			if (movieObj.has("overview"))
				pelicula.setDescripcion(movieObj.get("overview").getAsString());
			if (movieObj.has("release_date"))
				pelicula.setFechaEstreno(movieObj.get("release_date").getAsString());
			if (movieObj.has("poster_path"))
				pelicula.setPoster("https://image.tmdb.org/t/p/w500" + movieObj.get("poster_path").getAsString());

			listaPeliculas.add(pelicula);
		}

		return listaPeliculas;
	}

	public static String obtenerUrlYoutubePelicula(String titulo) {
		final String API_KEY = "4584014a9db6264fabab2596a1da86b2"; // 🔑 tu API de TMDb
		try {
			// 1️⃣ Codificar el título para usarlo en la URL
			String encodedTitle = java.net.URLEncoder.encode(titulo, java.nio.charset.StandardCharsets.UTF_8);

			// 2️⃣ Buscar la película por nombre
			String urlBuscar = "https://api.themoviedb.org/3/search/movie?api_key=" + API_KEY + "&language=es-ES&query="
					+ encodedTitle;
			String responseBuscar = doGet(urlBuscar);
			if (responseBuscar == null)
				return null;

			JsonObject jsonBuscar = JsonParser.parseString(responseBuscar).getAsJsonObject();
			JsonArray resultados = jsonBuscar.getAsJsonArray("results");
			if (resultados.size() == 0)
				return null;

			// 3️⃣ Tomar el primer resultado
			int idPelicula = resultados.get(0).getAsJsonObject().get("id").getAsInt();

			// 4️⃣ Buscar los videos asociados
			String urlVideos = "https://api.themoviedb.org/3/movie/" + idPelicula + "/videos?api_key=" + API_KEY
					+ "&language=es-ES";
			String responseVideos = doGet(urlVideos);
			if (responseVideos == null)
				return null;

			JsonObject jsonVideos = JsonParser.parseString(responseVideos).getAsJsonObject();
			JsonArray videos = jsonVideos.getAsJsonArray("results");
			if (videos.size() == 0)
				return null;

			// 5️⃣ Buscar el primer video de YouTube (tráiler o teaser)
			for (JsonElement e : videos) {
				JsonObject video = e.getAsJsonObject();
				String site = video.has("site") ? video.get("site").getAsString() : "";
				String type = video.has("type") ? video.get("type").getAsString() : "";
				boolean oficial = video.has("official") && video.get("official").getAsBoolean();

				if ("YouTube".equalsIgnoreCase(site) && oficial
						&& (type.equalsIgnoreCase("Trailer") || type.equalsIgnoreCase("Teaser"))) {
					String key = video.get("key").getAsString();
					return "https://www.youtube.com/watch?v=" + key;
				}
			}

			// 6️⃣ Si no hay tráiler oficial, devolver el primer video disponible
			JsonObject videoPrimero = videos.get(0).getAsJsonObject();
			String key = videoPrimero.get("key").getAsString();
			return "https://www.youtube.com/watch?v=" + key;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 🔸 Convierte JSON en objetos Documental
	public static List<Documental> obtenerDocumentales(int totalPages) {
		List<Documental> listaDocumentales = new ArrayList<>();

		String jsonResponse = getAllDocumentaries(totalPages);
		JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
		JsonArray documentariesArray = jsonObject.getAsJsonArray("results");

		for (JsonElement element : documentariesArray) {
			JsonObject docObj = element.getAsJsonObject();
			Documental documental = new Documental();

			if (docObj.has("id"))
				documental.setIdDocumental(docObj.get("id").getAsInt());
			if (docObj.has("title"))
				documental.setNombre(docObj.get("title").getAsString());
			if (docObj.has("overview"))
				documental.setDescripcion(docObj.get("overview").getAsString());
			if (docObj.has("release_date"))
				documental.setFechaEstreno(docObj.get("release_date").getAsString());
			if (docObj.has("poster_path"))
				documental.setPoster("https://image.tmdb.org/t/p/w500" + docObj.get("poster_path").getAsString());

			listaDocumentales.add(documental);
		}

		return listaDocumentales;
	}

	// 🔹 Obtener series desde TMDb
	public static List<Serie> obtenerSerie(int totalPages) {
		List<Serie> listaSeries = new ArrayList<>();

		String baseUrl = "https://api.themoviedb.org/3/discover/tv?api_key=" + API_KEY
				+ "&with_origin_country=US&language=es-ES";
		String jsonResponse = fetchFromTMDB(baseUrl, totalPages);

		JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
		JsonArray seriesArray = jsonObject.getAsJsonArray("results");

		for (JsonElement element : seriesArray) {
			JsonObject serieObj = element.getAsJsonObject();
			Serie serie = new Serie();

			if (serieObj.has("id"))
				serie.setIdSerie(serieObj.get("id").getAsInt());
			if (serieObj.has("name"))
				serie.setTitulo(serieObj.get("name").getAsString());
			if (serieObj.has("overview"))
				serie.setDescripcion(serieObj.get("overview").getAsString());
			if (serieObj.has("first_air_date"))
				serie.setFechaEstreno(serieObj.get("first_air_date").getAsString());
			if (serieObj.has("poster_path"))
				serie.setPoster("https://image.tmdb.org/t/p/w500" + serieObj.get("poster_path").getAsString());

			// Calificación y popularidad
			Random random = new Random();
			double calificacion = Math.min(10.0, Math.round((1.0 + random.nextDouble() * 9.0) * 10.0) / 10.0);
			int popularidad = random.nextInt(101);
			serie.setCalificacion(calificacion);
			serie.setPopularidad(popularidad);

			listaSeries.add(serie);
		}

		return listaSeries;
	}

	private static final String BASE_URL = "https://api.themoviedb.org/3";

	// =====================================================
	// 🔹 OBTENER SERIES DESDE TMDb
	// =====================================================
	public static List<Serie> obtenerSeries(int totalPages) {
		List<Serie> listaSeries = new ArrayList<>();

		String baseUrl = BASE_URL + "/discover/tv?api_key=" + API_KEY + "&with_origin_country=US&language=es-ES";
		String jsonResponse = fetchFromTMDB(baseUrl, totalPages);

		JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
		JsonArray seriesArray = jsonObject.getAsJsonArray("results");

		for (JsonElement element : seriesArray) {
			JsonObject serieObj = element.getAsJsonObject();
			Serie serie = new Serie();

			if (serieObj.has("id"))
				serie.setIdSerie(serieObj.get("id").getAsInt());
			if (serieObj.has("name"))
				serie.setTitulo(serieObj.get("name").getAsString());
			if (serieObj.has("overview"))
				serie.setDescripcion(serieObj.get("overview").getAsString());
			if (serieObj.has("first_air_date"))
				serie.setFechaEstreno(serieObj.get("first_air_date").getAsString());
			if (serieObj.has("poster_path") && !serieObj.get("poster_path").isJsonNull())
				serie.setPoster("https://image.tmdb.org/t/p/w500" + serieObj.get("poster_path").getAsString());

			// Calificación y popularidad aleatorias
			Random random = new Random();
			double calificacion = Math.min(10.0, Math.round((1.0 + random.nextDouble() * 9.0) * 10.0) / 10.0);
			int popularidad = random.nextInt(101);

			serie.setCalificacion(calificacion);
			serie.setPopularidad(popularidad);

			listaSeries.add(serie);
		}

		return listaSeries;
	}

	// =====================================================
	// 🔹 OBTENER TEMPORADAS Y EPISODIOS DE UNA SERIE
	// =====================================================
	public static List<Temporada> obtenerTemporadasYepisodios(Serie serie, TemporadaRepository temporadaRepo,
			EpisodioRepository episodioRepo) throws SQLException {

		List<Temporada> temporadas = new ArrayList<>();
		String url = BASE_URL + "/tv/" + serie.getIdSerie() + "?api_key=" + API_KEY + "&language=es-ES";

		String jsonResponse = doGet(url);
		if (jsonResponse == null)
			return temporadas;

		JsonObject serieObj = JsonParser.parseString(jsonResponse).getAsJsonObject();
		JsonArray temporadasArray = serieObj.getAsJsonArray("seasons");

		for (JsonElement element : temporadasArray) {
			JsonObject tempObj = element.getAsJsonObject();
			Temporada temporada = new Temporada();

			temporada.setNumeroTemporada(tempObj.get("season_number").getAsInt());
			temporada.setAnioEstreno(tempObj.has("air_date") && !tempObj.get("air_date").isJsonNull()
					? Integer.parseInt(tempObj.get("air_date").getAsString().substring(0, 4))
					: 0);
			temporada.setIdSerie(serie.getIdSerie());

			// 🔹 Insertar temporada y recuperar ID generado
			int idGenerado = temporadaRepo.insertarTemporada(temporada);
			temporada.setIdTemporada(idGenerado);
			temporadas.add(temporada);

			// =====================================================
			// 🔹 Obtener episodios de esta temporada
			// =====================================================
			String urlEpisodios = BASE_URL + "/tv/" + serie.getIdSerie() + "/season/" + temporada.getNumeroTemporada()
					+ "?api_key=" + API_KEY + "&language=es-ES";

			String jsonEpisodios = doGet(urlEpisodios);
			if (jsonEpisodios == null)
				continue;

			JsonObject tempJson = JsonParser.parseString(jsonEpisodios).getAsJsonObject();
			JsonArray episodiosArray = tempJson.getAsJsonArray("episodes");

			for (JsonElement epElement : episodiosArray) {
				JsonObject epObj = epElement.getAsJsonObject();
				Episodio episodio = new Episodio();

				episodio.setTitulo(epObj.has("name") ? epObj.get("name").getAsString() : "Sin título");
				episodio.setNumeroEpisodio(epObj.has("episode_number") ? epObj.get("episode_number").getAsInt() : 0);
				episodio.setDescripcion(
						epObj.has("overview") ? epObj.get("overview").getAsString() : "Sin descripción");
				episodio.setPoster(epObj.has("still_path") && !epObj.get("still_path").isJsonNull()
						? "https://image.tmdb.org/t/p/w500" + epObj.get("still_path").getAsString()
						: null);

				// 🔗 URL del episodio en YouTube (según tu método existente)
				episodio.setUrlEpisodio(obtenerUrlYoutubePelicula(episodio.getTitulo()));

				// 🔹 ID real de la temporada insertada
				episodio.setIdTemporada(temporada.getIdTemporada());

				episodioRepo.insertarEpisodio(episodio);
			}
		}

		return temporadas;
	}

    public static void main(String[] args) throws SQLException {

          Random random = new Random(); PeliculaRepository repoPeliculas = new
          PeliculaRepository(); List<Pelicula> peliculas = obtenerPeliculas(10);

          for (Pelicula p : peliculas) {
              double calificacion = Math.min(10.0, Math.round((1.0 +
          random.nextDouble() * 9.0) * 10.0) / 10.0);

          int popularidad = random.nextInt(101);

          p.setCalificacion(calificacion); p.setPopularidad(popularidad);

           String url = obtenerUrlYoutubePelicula(p.getNombre()); p.setUrlPelicula(url);

          repoPeliculas.agregarPelicula(p);

          System.out.println("🎬 Agregada película: " + p.getNombre() + " | ⭐ " +
          String.format("%.1f", p.getCalificacion()) + " | 📈 " + p.getPopularidad());
         }




          DocumentalRepository documentalRepository = new DocumentalRepository();
          List<Documental> documentales = obtenerDocumentales(10);

          for (Documental d : documentales) { double calificacion = Math.min(10.0,
          Math.round((1.0 + random.nextDouble() * 9.0) * 10.0) / 10.0); int popularidad
          = random.nextInt(101);

          d.setCalificacion(calificacion); d.setPopularidad(popularidad);

          String url = obtenerUrlYoutubePelicula(d.getNombre());
          d.setUrlDocumental(url);

          documentalRepository.agregarDocumental(d);

         System.out.println("🎥 Agregado documental: " + d.getNombre() + " | ⭐ " +
          String.format("%.1f", d.getCalificacion()) + " | 📈 " + d.getPopularidad());
          }

          GeneroRepository repoGeneros = new GeneroRepository();
          List<Genero> generos = obtenerGeneros();

           for (Genero g : generos) {
          boolean insertado = repoGeneros.crearGeneros(g);

          if (insertado) { System.out.println("🎭 Género agregado: " + g.getNombre());
          } else { System.out.println("⚠️ No se pudo agregar: " + g.getNombre()); } }


        SerieRepository serieRepo = new SerieRepository();
        TemporadaRepository temporadaRepo = new TemporadaRepository();
        EpisodioRepository episodioRepo = new EpisodioRepository();

        List<Serie> series = obtenerSeries(3);

        for (Serie s : series) {
            serieRepo.insertarSerie(s);
            System.out.println("📺 Serie agregada: " + s.getTitulo());
            obtenerTemporadasYepisodios(s, temporadaRepo, episodioRepo);
        }
    }

}
