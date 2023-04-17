package com.example.major.Interest

import org.springframework.data.jpa.repository.JpaRepository

interface InterestsRepository : JpaRepository<Interest, Long> {
    fun findAllByUserId(userId: String): List<Interest>
}
