public enum API_URL {
    IMDB_MOVIES("https://alura-imdb-api.herokuapp.com/movies"),
    IMDB_TOP_MOVIES("https://api.mocki.io/v2/549a5d8b/MostPopularMovies"),
    IMDB_TOP_SERIES("https://api.mocki.io/v2/549a5d8b/Top250TVs"),
    IMDB_SERIES("https://api.mocki.io/v2/549a5d8b/MostPopularTVs");

    private final String url;

    API_URL(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
