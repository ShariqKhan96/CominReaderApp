package android.webinnovatives.com.cominreaderapp.model

class ComicObject {
    var message: String? = null
    var error: Boolean? = null
    var comics: List<Comic>? = null


    constructor()
    constructor(message: String, error: Boolean, comics: List<Comic>) {
        this.message = message
        this.error = error
        this.comics = comics
    }
}