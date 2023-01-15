package com.example.testcft.util

 fun valideUrl(url: String): String =
    if (!url.startsWith("https://") || url.startsWith("http://")){
        "http://$url"
    }else{
        url
    }
