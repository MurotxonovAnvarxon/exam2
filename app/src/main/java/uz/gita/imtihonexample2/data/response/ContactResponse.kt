package uz.gita.imtihonexample2.data.response


data class ContactResponse(
    val info: Info,
    val results: List<uz.gita.imtihonexample2.data.response.Result>,
)