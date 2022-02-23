package com.forever.bee.usecase.high_rise_building

import kotlinx.coroutines.*
import java.lang.Exception

class HighRiseBuilding {
    private val scope = CoroutineScope((Dispatchers.Default))

    suspend fun startProject(name: String, floors: Int): Deferred<Builder> {
        return scope.async {
            val building = Builder(name, scope = this)

            val cores = Runtime.getRuntime().availableProcessors()

            building.speakThroughBullhorn("The building of $name is started with $cores building machines engaged")
            // Any orher phases can not be started until foundation is ready
            building.makeFoundation().join()

            (1..floors).forEach {
                var floorsNum = 0
                try {
                    // A floor should be raised before we can decorate it
                    floorsNum = building.buildingFloor(it)
                } catch (e: Exception) {
                    building.speakThroughBullhorn(e.message ?: "")

                    while (floorsNum < it) {
                        try {
                            floorsNum = building.buildingFloor(it)
                        } catch (e: Exception) {
                            // NOP
                        }
                    }
                }

                // These decorations could be made at the same time.\
                building.putTheWindows(it)
                building.installDoors(it)
                building.provideElectricity(it)
                building.fitOut(it)
            }
            building.buildRoof().join()
            building.speakThroughBullhorn("${building.name} is ready!")
            building
        }
    }
}