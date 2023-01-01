package ru.duremika.kotlinrestfulapi.error

class UnauthorizedException(val xApiKey: String? = null) : Exception()