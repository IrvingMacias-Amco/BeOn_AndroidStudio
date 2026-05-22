package com.example.beon.data.remote.sanity

object SanityQueries {
    val moviesHome: String = """
        *[_type == "movie"] | order(releaseYear desc) {
          "_id": _id,
          title,
          "slug": slug.current,
          releaseYear,
          duration,
          shortSynopsis,
          longSynopsis,
          muxPlaybackId,
          muxAssetId,
          "posterUrl": poster.asset->url,
          "heroUrl": coalesce(keyArt.asset->url, poster.asset->url),
          "genres": genres[]->title,
          "cast": cast[].role,
          "ratingLabel": rating->label
        }
    """.trimIndent()

    fun movieById(movieId: String): String = """
        *[_type == "movie" && _id == "$movieId"][0] {
          "_id": _id,
          title,
          "slug": slug.current,
          releaseYear,
          duration,
          shortSynopsis,
          longSynopsis,
          muxPlaybackId,
          muxAssetId,
          "posterUrl": poster.asset->url,
          "heroUrl": coalesce(keyArt.asset->url, poster.asset->url),
          "genres": genres[]->title,
          "cast": cast[].role,
          "ratingLabel": rating->label
        }
    """.trimIndent()
}
