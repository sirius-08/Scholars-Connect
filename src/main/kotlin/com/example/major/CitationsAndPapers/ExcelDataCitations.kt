package com.example.major.CitationsAndPapers

data class ExcelData(
        var name: String = "",
        var excelDataCitations:List<ExcelDataCitations> = listOf(),
        var total: String = ""
)

data class ExcelDataCitations(
    var year: String = "",
    var yearWise: Int = 0,
    var cumulative: Int = 0
)
