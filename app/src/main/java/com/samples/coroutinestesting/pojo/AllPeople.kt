package com.samples.coroutinestesting.pojo

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Sample POJO for JSON mapping.
 */
data class AllPeople(

    @JsonProperty("count")
    val count : Int,

    @JsonProperty("next")
    val next : String,

    @JsonProperty("previous")
    val previous : String,

    @JsonProperty("results")
    val results : List<AllPeopleResult>

)