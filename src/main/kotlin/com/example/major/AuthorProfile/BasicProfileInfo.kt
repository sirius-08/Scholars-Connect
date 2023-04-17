package com.example.major.AuthorProfile

data class BasicProfileInfo(var name: String = "", var institute: String = "", var email: String = "", var interest: MutableList<String> = mutableListOf())
