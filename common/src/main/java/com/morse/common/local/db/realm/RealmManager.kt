package com.morse.common.local.db.realm

import android.content.Context
import io.realm.Realm
import io.realm.RealmObject
import kotlinx.coroutines.flow.flow

class RealmManager private constructor(public val realM: Realm) {

    companion object {
        @Volatile
        private var instance: RealmManager? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = with(Realm.init(context)){RealmManager(Realm.getDefaultInstance())}
    }

    inline fun <reified I : RealmObject> isExist(id: Int) = flow {
        val value = realM.where(I::class.java).equalTo("id", id).findFirst()
        emit(value != null)
    }

    fun <I : RealmObject> insertNewData(newData: I) {
        realM.beginTransaction()
        realM.insert(newData)
        realM.commitTransaction()
    }

    inline fun <reified I : RealmObject> selectAllData() = flow {
        val result = realM.where(I::class.java).findAll()
        emit(result)
    }

    inline fun <reified I : RealmObject> remove(data: I) {
        realM.beginTransaction()
        val value = realM.where(I::class.java).findFirst()
        value?.deleteFromRealm()
        realM.commitTransaction()
    }

    inline fun <reified I : RealmObject> clearAll() {
        realM.beginTransaction()
        realM.delete(I::class.java)
        realM.commitTransaction()
    }
}