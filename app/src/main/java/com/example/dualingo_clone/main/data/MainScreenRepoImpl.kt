package com.example.dualingo_clone.main.data

import androidx.compose.ui.graphics.Color
import com.example.dualingo_clone.cache.data.CacheProvider
import com.example.dualingo_clone.cache.domain.Cache
import com.example.dualingo_clone.database.data.DatabaseImpl
import com.example.dualingo_clone.dataclasses.Excersise
import com.example.dualingo_clone.dataclasses.TopUsers
import com.example.dualingo_clone.dataclasses.User
import com.example.dualingo_clone.dataclasses.UserInfo
import com.example.dualingo_clone.main.domain.MainScreenRepo
import java.util.UUID

class MainScreenRepoImpl(private val db: DatabaseImpl) : MainScreenRepo {
    private val cache: Cache = CacheProvider.getCache()

    override suspend fun getUserData(): Pair<User, UserInfo?> {
        val (email, pwd)=cache.getUsersData()
        if (email!!.isEmpty() || pwd!!.isEmpty()){
            return Pair(User(), null)
        }
        val (usr, err) = db.signIn(email, pwd)
        if (err != null){
            return Pair(User(), null)
        }
        if (usr.password==pwd && usr.email==email){
            val (usr, usrInfo) = db.getUserData(usr)
            return Pair(usr, usrInfo)
        }
        return Pair(User(), null)
    }
    override suspend fun getAvailableExcersises(): List<Excersise> {
        val smiles = listOf(
            "🐼", "✏️",
            "🔊", "🎮",
        )
        val names = listOf(
            "Guess the animal",
            "Word practice",
            "Audition",
            "Game",
        )
        val excersises = mutableListOf<Excersise>()
        for (i in smiles.indices){
            val backColor:Color = when (i%4){
                0->Color(0xFF00b5ae)
                1->Color(0xFFd6185d)
                2->Color(0xFFf76400)
                3->Color(0xFF5ba890)
                else -> throw IllegalArgumentException("Invalid color index")
            }
            val excersise = Excersise(
                smile = smiles[i],
                text = names[i],
                backColor=backColor,
            )
            excersises.add(excersise)
        }
        return excersises
    }
    override suspend fun getTopUsers(): List<TopUsers>{
        return db.getTopUsers()
    }

    override suspend fun getUserById(id:UUID): User{
        return db.getUserById(id)
    }
}