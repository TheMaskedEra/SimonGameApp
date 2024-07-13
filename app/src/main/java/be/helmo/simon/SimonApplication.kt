package be.helmo.simon

import android.app.Application
import be.helmo.simon.data.ScoreRepository
import be.helmo.simon.data.SimonDatabase

class SimonApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        SimonDatabase.create(this)
        ScoreRepository.create(SimonDatabase.get()) //TODO devoir injecter les dépendances ici
    }
}