package uz.gita.imtihonexample2

import uz.gita.imtihonexample2.data.response.Result
import uz.gita.imtihonexample2.data.room.ContactEntity


fun Result.toEntity():ContactEntity= ContactEntity(id = 0, title = this.name.title, firstName = this.name.first, lastName = this.name.last, age = this.dob.age, gender = this.gender, phone = this.phone, email = this.email, image = this.picture.medium, url = this.picture.large )


