package com.example.major.Networking

data class SearchProfileResponse(
    var profiles: List<Profile> = listOf<Profile>(Profile()),
//    var search_parameters: SearchParameters,
//    var pagination: Pagination
)

// data class searchMetadata(
//    var id: String,
//    var status: String,
//    var json_endpoint: String,
//    var created_at: String,
//    var processed_at: String,
//    var google_scholar_profiles_url: String,
//    var raw_html_file: String,
//    var total_time_taken: Float
// )
//
// data class SearchParameters(
//    var engine: String,
//    var mauthors: String,
//    var hl: String
// )

data class Profile(
    var name: String = "",
    var email: String = "",
    var cited_by: Int = 0,
    // var interests: List<Interests>,
    var link: String = "",
    var serpapi_link: String = "",
    var author_id: String = "",
    var affiliations: String = "",
    // var thumbnail: String
)

// data class Pagination(
//    var next: String,
//    var next_page_token: String
// )
//
// data class Interests(
//    var title: String,
//    var serpapi_link: String,
//    var link: String
// )
