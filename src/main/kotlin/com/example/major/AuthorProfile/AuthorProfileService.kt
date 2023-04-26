package com.example.major.AuthorProfile

import it.skrape.core.htmlDocument
import it.skrape.fetcher.HttpFetcher
import it.skrape.fetcher.extract
import it.skrape.fetcher.skrape
import it.skrape.selects.and
import it.skrape.selects.html5.a
import it.skrape.selects.html5.div
import it.skrape.selects.html5.tr
import org.springframework.stereotype.Service

@Service
class AuthorProfileService {
    val baseUrl = "https://scholar.google.com/citations?hl=en&user="
    fun getBasicProfileInfo(id: String): BasicProfileInfo {
        var basicProfileInfo:BasicProfileInfo = BasicProfileInfo()
        val url = baseUrl + id

        try {
            skrape(HttpFetcher) {
                request {
                    this.url = url
                }
                extract {
                    htmlDocument {
                        div{
                            withId = "gsc_prf_in"
                            findFirst {
                                //println(this.text)
                                basicProfileInfo.name = this.text
                            }
                        }
                        div{
                            withClass = "gsc_prf_il"
                            findFirst {
                                //println(this.text)
                                basicProfileInfo.email = this.text
                            }
                        }
                        div{
                            withId = "gsc_prf_ivh"
                            findFirst {
                                //println(this.text.split("-")[0])
                                basicProfileInfo.institute = this.text.split("-")[0]
                            }
                        }
                        a {
                            withClass = "gsc_prf_inta" and "gs_ibl"
                            findAll {
                                this.forEach {
                                    //println(it.text)
                                    basicProfileInfo.interest.add(it.text)
                                }
                            }
                        }
    //                this.select(".gsc_prf_inta.gs_ibl")
    //                this.se
                    }
                }
            }
        } catch (e: it.skrape.selects.ElementNotFoundException) {
            println("Some element Not Found")
        } finally {
            return basicProfileInfo
        }
    }

    fun getPublications(id: String): List<Publication> {
        val publications: MutableList<Publication> = mutableListOf()
        val url = baseUrl + id
        skrape(HttpFetcher) {
            request {
                this.url = url
            }
            extract {
                htmlDocument {
                    tr {
                        withClass = "gsc_a_tr"
                        findAll {
                            this.forEach {
                                var publication:Publication = Publication()
//                                it.children[0].children.forEach {
//                                    println(it.text)
//                                }
//                                println(it.children[1].text)
//                                println(it.children[2].text)
                                publication.title = it.children[0].children[0].text
                                publication.coAuthors = it.children[0].children[1].text
                                publication.journal = it.children[0].children[2].text
                                publication.citedBy = it.children[1].text
                                publication.year = it.children[2].text
                                publications.add(publication)
                            }
                        }
                    }
                }
            }
        }
        return publications
    }
}