package com.example.major.CitationsAndPapers

import com.example.major.Networking.AuthorProfileResponse
import com.example.major.Networking.YearWiseCitations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
@RequestMapping(path = ["citations"])
class CitationsController @Autowired constructor(var citationsService: CitationsService) {

    @GetMapping(path = ["excel_data/{organization}/{department}"])
    fun getExcelData(@PathVariable("organization") organization: String, @PathVariable("department") department: String): List<ExcelData> {
        //return citationsService.getExcelData(organization, department)
        return citationsService.getExcelData()
    }
    @GetMapping(path = ["userExcelData/{id}"])
    fun getUserExcelData(@PathVariable("id") id: String): ExcelData {
        return citationsService.getExcelDataForUser(id)
    }
}
