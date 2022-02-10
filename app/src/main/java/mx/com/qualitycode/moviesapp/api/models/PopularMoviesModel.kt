package mx.com.qualitycode.moviesapp.api.models


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie")
data class PopularMoviesModel(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("page")
    @ColumnInfo(name = "page") var page: Int,
    @SerializedName("results")
    @ColumnInfo(name = "results")var results: List<Result>,
    @SerializedName("total_pages")
    @ColumnInfo(name = "total_pages")var totalPages: Int,
    @SerializedName("total_results")
    @ColumnInfo(name = "total_results")var totalResults: Int
) {
    data class Result(
        @SerializedName("adult")
        @ColumnInfo(name = "adult")var adult: Boolean,
        @SerializedName("backdrop_path")
        @ColumnInfo(name = "backdrop_path")var backdropPath: String,
        @SerializedName("genre_ids")
        @ColumnInfo(name = "gere_ids")var genreIds: List<Int>,
        @SerializedName("id")
        @ColumnInfo(name = "id")var id: Int,
        @SerializedName("original_language")
        @ColumnInfo(name = "original_language")var originalLanguage: String,
        @SerializedName("original_title")
        @ColumnInfo(name = "original_title")var originalTitle: String,
        @SerializedName("overview")
        @ColumnInfo(name = "overview")var overview: String,
        @SerializedName("popularity")
        @ColumnInfo(name = "popularity")var popularity: Double,
        @SerializedName("poster_path")
        @ColumnInfo(name = "poster_path")var posterPath: String,
        @SerializedName("release_date")
        @ColumnInfo(name = "release_date")var releaseDate: String,
        @SerializedName("title")
        @ColumnInfo(name = "title")var title: String,
        @SerializedName("video")
        @ColumnInfo(name = "video")var video: Boolean,
        @SerializedName("vote_average")
        @ColumnInfo(name = "vote_average")var voteAverage: Double,
        @SerializedName("vote_count")
        @ColumnInfo(name = "vote_count")var voteCount: Int
    )
}