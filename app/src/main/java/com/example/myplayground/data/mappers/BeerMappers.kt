package com.example.myplayground.data.mappers

import com.example.myplayground.data.local.BeerEntity
import com.example.myplayground.data.remote.BeerDto
import com.example.myplayground.domain.Beer

fun BeerDto.toBeerEntity(): BeerEntity {
    return BeerEntity(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = first_brewed,
        imageUrl = image_url
    )
}

fun BeerEntity.toBeer(): Beer {
    return Beer(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = firstBrewed,
        imageUrl = imageUrl
    )
}