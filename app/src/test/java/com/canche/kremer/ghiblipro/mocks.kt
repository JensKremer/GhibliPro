package com.canche.kremer.ghiblipro

import com.canche.kremer.ghiblipro.data.network.NetworkResult
import com.canche.kremer.ghiblipro.domain.models.Film
import java.lang.Exception

val mockedFilm = Film(
    "Castle in the Sky",
    "天空の城ラピュタ",
    "Tenkū no shiro Rapyuta",
    "https://image.tmdb.org/t/p/w600_and_h900_bestv2/npOnzAbLh6VOIu3naU5QaEcTepo.jpg",
    "https://image.tmdb.org/t/p/w533_and_h300_bestv2/3cyjYtLWCBE1uvWINHFsFnE8LUK.jpg",
    "The orphan Sheeta inherited a mysterious crystal that links her to the mythical sky-kingdom of Laputa. With the help of resourceful Pazu and a rollicking band of sky pirates, she makes her way to the ruins of the once-great civilization. Sheeta and Pazu must outwit the evil Muska, who plans to use Laputa's science to make himself ruler of the world.",
    "Hayao Miyazaki",
    "Isao Takahata",
    1986,
    124,
    95
)

val mockedFilmList = listOf(mockedFilm)
val mockedNetworkResultSuccess = NetworkResult.Success(mockedFilmList)
val mockedNetworkResultError = NetworkResult.Error<List<Film>>(500, "Error al conectar con el servidor")
val mockedNetworkResultException = NetworkResult.Exception<List<Film>>(Throwable("Throw error"))