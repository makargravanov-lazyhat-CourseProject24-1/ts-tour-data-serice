package ru.jetlabs.ts.tourdataservice.models

sealed interface GetRoomByIdResult {
    data class Success(val data: HotelRoom) : GetRoomByIdResult
    data object NotFound : GetRoomByIdResult
}

sealed interface GetHotelByIdResult {
    data class Success(val data: Hotel) : GetHotelByIdResult
    data object NotFound : GetHotelByIdResult
}

sealed interface GetTransportByIdResult {
    data class Success(val data: Transport) : GetTransportByIdResult
    data object NotFound : GetTransportByIdResult
}

sealed interface GetPlaceByIdResult {
    data class Success(val data: Place) : GetPlaceByIdResult
    data object NotFound : GetPlaceByIdResult
}

sealed interface GetRouteByIdResult {
    data class Success(val data: TransportRoute) : GetRouteByIdResult
    data object NotFound : GetRouteByIdResult
}

sealed interface GetNutritionByIdResult {
    data class Success(val data: HotelNutrition) : GetNutritionByIdResult
    data object NotFound : GetNutritionByIdResult
}