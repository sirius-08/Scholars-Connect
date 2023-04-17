package com.example.major.CitationsAndPapers

import com.example.major.Networking.AuthorProfileResponse
import com.example.major.Networking.NetworkClient
import com.example.major.Networking.SearchProfileResponse
import com.example.major.Networking.YearWiseCitations
import com.example.major.RegisteredUsers.RegistrationRepository
import com.example.major.RegisteredUsers.User
import it.skrape.core.htmlDocument
import it.skrape.fetcher.HttpFetcher
import it.skrape.fetcher.extract
import it.skrape.fetcher.skrape
import it.skrape.selects.html5.div
import it.skrape.selects.html5.span
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import retrofit2.Call
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

@Service
class CitationsService @Autowired constructor(val registrationRepository: RegistrationRepository) {
    fun getExcelData(): List<ExcelData> {
        var users = registrationRepository.findAll().subList(0,5)
        var excelData: MutableList<ExcelData> = mutableListOf()
        users.forEach {
            excelData.add(getExcelDataForUser(it.id))
        }
        return excelData
    }

    fun getExcelDataForUser(id: String): ExcelData {
        val baseUrl = "https://scholar.google.com/citations?hl=en&user="

        var name: String = ""
        val byYear:MutableList<Int> = mutableListOf()
        val cumulative:MutableList<Int> = mutableListOf()
        val year:MutableList<String> = mutableListOf()

        skrape(HttpFetcher) {
            request {
                url = baseUrl + id
            }
            extract {
                htmlDocument{
                    span{
                        withClass = "gsc_g_al"
                        findAll {
                            this.forEach { it ->
                                //println(it.text)
                                byYear.add(it.text.toInt())
                                if(cumulative.isEmpty())
                                    cumulative.add(it.text.toInt())
                                else
                                    cumulative.add(cumulative.last() + it.text.toInt())
                            }
                        }
                    }

                    span{
                        withClass = "gsc_g_t"
                        findAll {
                            this.forEach { it ->
                                //println(it.text)
                                year.add(it.text)
                            }
                        }
                    }

                    //gsc_prf_in
                    div {
                        withId = "gsc_prf_in"
                        findFirst {
                            name = this.text
                        }
                    }
                }
            }
        }

        var excelDataCitations:MutableList<ExcelDataCitations> = mutableListOf()
        year.forEachIndexed { index, s ->
            excelDataCitations.add(ExcelDataCitations(s, byYear[index], cumulative[index]))
        }
        return ExcelData(name, excelDataCitations, excelDataCitations[excelDataCitations.size - 1].cumulative.toString())
    //return ExcelData(name, ExcelDataCitations(year, byYear, cumulative))
    }
}
