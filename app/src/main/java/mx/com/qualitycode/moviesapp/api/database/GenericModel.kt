package mx.com.qualitycode.moviesapp.api.database


import org.json.JSONArray
import org.json.JSONObject

interface GenericModel {
    fun processData(lineArray: List<String>):Boolean

    fun isRightObject(lineArray: List<String>):Boolean = true

    fun processData(jsonObject: JSONObject) = false

    fun isRightJsonArray(jsonObject: JSONObject): Boolean = false

    fun getJsonArray(jsonObject: JSONObject): JSONArray = JSONArray()
}