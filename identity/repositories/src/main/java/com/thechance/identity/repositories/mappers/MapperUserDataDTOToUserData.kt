package com.thechance.identity.repositories.mappers

import com.thechance.identity.entities.UserData
import com.thechance.identity.repositories.models.UserDataDTO
import javax.inject.Inject

class MapperUserDataDTOToUserData @Inject constructor() : Mapper<UserData, UserDataDTO> {
    override fun map(input: UserData): UserDataDTO {
        return UserDataDTO(
            birthdate = input.birthdate,
            email = input.email,
            reemail = input.reEmail,
            username = input.username,
            fullName = input.fullName,
            jobTitle = input.jobTitle,
            fcmToken = input.fcmToken,
            gender = input.gender,
            password = input.password,
            userId = input.userId,
            newEmail = input.newEmail,
            newGender = input.newGender,
            currentPassword = input.currentPassword,
            newFullName = input.newFullName,
            newFcmToken = input.newFcmToken,
            newJobTitle = input.newJobTitle
        )
    }
}