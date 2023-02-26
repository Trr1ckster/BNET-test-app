package com.trr1ckster.bnet_test_app.data.model

import java.io.Serializable

data class ApiModelItem(
    val categories: Categories?,
    val description: String?,
    val documentation: String?,
    val fields: List<Field>?,
    val id: Int?,
    val image: String?,
    val name: String?
) : Serializable