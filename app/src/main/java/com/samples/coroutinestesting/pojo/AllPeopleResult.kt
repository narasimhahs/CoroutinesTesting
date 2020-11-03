package com.samples.coroutinestesting.pojo

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Sample POJO for JSON mapping.
 */
data class AllPeopleResult (
    @JsonProperty("name")
    val name : String,

    @JsonProperty("height")
    val height : String,

    @JsonProperty("mass")
    val mass : String,

    @JsonProperty("hair_color")
    val hair_color : String,

    @JsonProperty("skin_color")
    val skin_color : String,

    @JsonProperty("eye_color")
    val eye_color : String,

    @JsonProperty("birth_year")
    val birth_year : String,

    @JsonProperty("gender")
    val gender : String,

    @JsonProperty("homeworld")
    val homeworld : String,

    @JsonProperty("films")
    val films : List<String>,

    @JsonProperty("species")
    val species : List<String>,

    @JsonProperty("vehicles")
    val vehicles : List<String>,

    @JsonProperty("starships")
    val starships : List<String>,

    @JsonProperty("created")
    val created : String,

    @JsonProperty("edited")
    val edited : String,

    @JsonProperty("url")
    val url : String
    )