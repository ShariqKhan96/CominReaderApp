package android.webinnovatives.com.cominreaderapp.model

class ComicObject {
    var message: String? = null
    var error: Boolean? = null
    var comic: List<Comic>? = null


    constructor()
    constructor(message: String, error: Boolean, comic: List<Comic>) {
        this.message = message
        this.error = error
        this.comic = comic
    }
}