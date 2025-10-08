package co.edu.unbosque.netflixbackend.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import co.edu.unbosque.netflixbackend.model.Documental;
import co.edu.unbosque.netflixbackend.model.Pelicula;
import co.edu.unbosque.netflixbackend.repository.DocumentalRepository;


public class ExternalHTTPRequestHandler {

    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    private static final String API_KEY = "4584014a9db6264fabab2596a1da86b2";

    private static final String BASE_URL_PELICULAS =
            "https://api.themoviedb.org/3/discover/movie?api_key=" + API_KEY + "&with_origin_country=US";
    private static final String BASE_URL_DOCUMENTALES =
            "https://api.themoviedb.org/3/discover/movie?api_key=" + API_KEY + "&with_genres=99";


    // Método genérico para hacer peticiones HTTP GET
    public static String doGet(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .build();

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
            if (jsonResponse == null) continue;

            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

            if (jsonObject.has("results")) {
                JsonArray results = jsonObject.getAsJsonArray("results");
                for (JsonElement item : results) {
                    allItems.add(item);
                }
            }

            int totalAvailablePages = jsonObject.has("total_pages")
                    ? jsonObject.get("total_pages").getAsInt()
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

            if (movieObj.has("id")) pelicula.setIdPelicula(movieObj.get("id").getAsInt());
            if (movieObj.has("title")) pelicula.setNombre(movieObj.get("title").getAsString());
            if (movieObj.has("overview")) pelicula.setDescripcion(movieObj.get("overview").getAsString());
            if (movieObj.has("release_date")) pelicula.setFechaEstreno(movieObj.get("release_date").getAsString());
            if (movieObj.has("poster_path"))
                pelicula.setPoster("https://image.tmdb.org/t/p/w500" + movieObj.get("poster_path").getAsString());

            listaPeliculas.add(pelicula);
        }

        return listaPeliculas;
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

            if (docObj.has("id")) documental.setIdDocumental(docObj.get("id").getAsInt());
            if (docObj.has("title")) documental.setNombre(docObj.get("title").getAsString());
            if (docObj.has("overview")) documental.setDescripcion(docObj.get("overview").getAsString());
            if (docObj.has("release_date")) documental.setFechaEstreno(docObj.get("release_date").getAsString());
            if (docObj.has("poster_path"))
                documental.setPoster("https://image.tmdb.org/t/p/w500" + docObj.get("poster_path").getAsString());

            listaDocumentales.add(documental);
        }

        return listaDocumentales;
    }

    
    // 🔹 Método principal de prueba
    public static void main(String[] args) {
        // Guardar películas
        /*
        PeliculaRepository repoPeliculas = new PeliculaRepository();
        List<Pelicula> peliculas = obtenerPeliculas(2);
        for (Pelicula p : peliculas) {
            repoPeliculas.agregarPelicula(p);
            System.out.println("🎬 Agregada película: " + p.getNombre());
        }
        */

        // Guardar documentales
        DocumentalRepository repoDocumentales = new DocumentalRepository();
        List<Documental> documentales = obtenerDocumentales(5);
        for (Documental d : documentales) {
            repoDocumentales.agregarDocumental(d);
            System.out.println("🎥 Agregado documental: " + d.getNombre());
        }
    }
}
