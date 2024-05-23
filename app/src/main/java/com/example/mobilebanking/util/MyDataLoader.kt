package com.example.mobilebanking.util

import com.example.mobilebanking.data.model.CardData
import com.example.mobilebanking.domain.uscase.GetAllCardUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

object MyDataLoader {
    private lateinit var getAllCardsUseCase: GetAllCardUseCase
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    val loadStateFlow = MutableStateFlow(false)
    val listStateFlow = MutableStateFlow(value = listOf<CardData>())
    val totalAmountStateFlow = MutableStateFlow(0L)
    val errorStateFlow = MutableStateFlow("")

    fun init(useCase: GetAllCardUseCase) {
        getAllCardsUseCase = useCase
    }

    fun loadCardsData() {
        scope.launch {
            loadStateFlow.emit(true)
            getAllCardsUseCase().onEach { result ->
                result.onSuccess { list ->
                    var amount = 0L
                    list.forEach { amount += it.amount }
                    scope.launch {
                        loadStateFlow.emit(false)
                        totalAmountStateFlow.emit(amount)
                        listStateFlow.emit(list.map {it})
                    }
                }
                result.onFailure {
                    scope.launch {
                        loadStateFlow.emit(false)
                        errorStateFlow.emit(it.message.toString())
                    }
                }
            }.launchIn(scope)
        }
    }
}