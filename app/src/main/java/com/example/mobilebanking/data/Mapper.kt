package com.example.mobilebanking.data

import com.example.mobilebanking.data.local.entity.CardEntity
import com.example.mobilebanking.data.model.CardData
import com.example.mobilebanking.data.remote.response.CardResponse

object Mapper {
    fun CardResponse.toCardData(): CardData {
        return CardData(
            id = id,
            name = name,
            amount = amount,
            owner = owner,
            pan = pan,
            expiredYear = expiredYear,
            expiredMonth = expiredMonth.toInt(),
            isVisible = isVisible.toBoolean(),
            themeType = themeType.toInt()
        )
    }

    fun CardEntity.toUIData() : CardData =
        CardData(id, name, amount, owner, pan, expiredYear.toString(), expiredMonth, themeType, isVisible)}