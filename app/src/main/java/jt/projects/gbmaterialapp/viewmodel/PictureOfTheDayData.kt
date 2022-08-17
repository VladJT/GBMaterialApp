package jt.projects.gbmaterialapp.viewmodel

import jt.projects.gbmaterialapp.model.dto.PODServerResponseData


sealed class PictureOfTheDayData {
    data class Success(val serverResponseData: PODServerResponseData) :
        PictureOfTheDayData()

    data class Error(val error: Throwable) : PictureOfTheDayData()
    data class Loading(val progress: Int?) : PictureOfTheDayData()
}
