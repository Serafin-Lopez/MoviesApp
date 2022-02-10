package mx.com.qualitycode.moviesapp.api.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = MoviePopularEntity.TABLE_NAME)
data class MoviePopularEntity(

    @ColumnInfo(name = "uuid") @NotNull val uuid: String,
    @ColumnInfo(name = "username") @NotNull val username: String,
    @ColumnInfo(name = "names") @NotNull val names: String?,
    @ColumnInfo(name = "lastName") @NotNull val lastName: String? = null,
    @ColumnInfo(name = "secondLastName") @NotNull val secondLastName: String? = null,
    @ColumnInfo(name = "cellphone") @NotNull val cellphone: String? = null,
    @ColumnInfo(name = "email") @NotNull val email: String? = null,
    @ColumnInfo(name = "birthDate") @NotNull val birthDate: String? = null,
    @ColumnInfo(name = "isEnabled") @NotNull val isEnabled: Boolean? = null
) {
    companion object {
        const val TABLE_NAME = "user"
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    var contactId: Int = 0

}