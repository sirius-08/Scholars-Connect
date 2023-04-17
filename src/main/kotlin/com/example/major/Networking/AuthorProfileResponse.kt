package com.example.major.Networking

data class AuthorProfileResponse(
    var author: Author = Author(),
    var articles: List<Article> = listOf(Article()),
    var cited_by: CitedByAuthorResponse = CitedByAuthorResponse()
)

data class CitedByAuthorResponse(
    var graph: List<YearWiseCitations> = listOf(YearWiseCitations())
)

data class YearWiseCitations(
    var year: Int = 0,
    var citations: String = ""
)

// data class SearchMetadata(
//    var id: String,
//    var status: String,
//    var json_endpoint: String,
//    var created_at: String,
//    var processed_at: String,
//    var google_scholar_author_url: String,
//    var raw_html_file: String,
//    var total_time_taken: Float
// )

data class Author(
    var name: String = "",
    var affiliations: String = "",
    var email: String = "",
    // var interests: List<Interests>,
    // var thumbnail: String
)

data class Article(
    var title: String = "",
    // var link: String,
    // var citation_id: String,
    var authors: String = "",
    var publication: String = "",
    var cited_by: CitedBy = CitedBy(),
    var year: String = ""
)

data class CitedBy(
    var value: Int = 0
)
