package com.gaeunpark.myfristkmmapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform