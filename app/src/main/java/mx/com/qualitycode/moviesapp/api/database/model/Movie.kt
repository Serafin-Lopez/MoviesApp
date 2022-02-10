package mx.com.qualitycode.moviesapp.api.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country")
data class Movie(
        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = "country_id") var countryId: Int = -1,
        @ColumnInfo(name = "country_dsc") var countryDsc: String = "",
        @ColumnInfo(name = "country_code") var countryCode: String = ""
)