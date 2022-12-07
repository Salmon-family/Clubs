package com.thechance.identity.repositories.mappers

import com.thechance.identity.entities.UserData
import com.thechance.identity.repositories.models.UserDataDTO
import javax.inject.Inject

class MapperUserDataDTOToUserData @Inject constructor() : Mapper<UserData, UserDataDTO> {
    override fun map(input: UserData): UserDataDTO {
        return UserDataDTO(
            birthdate = input.birthdate,
            email = input.email,
            reEmail = input.reEmail,
            username = input.username,
            firstname = input.firstname,
            lastname = input.lastname,
            gender = input.gender,
            password = input.password,
        )
    }
}