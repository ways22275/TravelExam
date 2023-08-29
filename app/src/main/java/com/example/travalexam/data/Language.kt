package com.example.travalexam.data

enum class Language (val langCode: String) {
  ZH_TW("zh-tw"),
  ZH_CN("zh-cn"),
  EN("en"),
  JA("ja"),
  KO("ko"),
  ES("es"),
  ID("id"),
  TH("th"),
  VI("vi")
}

fun Language?.getLangCode(): String {
  return this?.langCode ?: "zh-tw"
}

fun Language?.getDisplayName(): String {
  return this?.name ?: "ZH_TW"
}