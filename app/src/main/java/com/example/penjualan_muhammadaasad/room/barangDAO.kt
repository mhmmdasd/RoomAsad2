package com.example.penjualan_muhammadaasad.room

import androidx.room.*


@Dao
interface barangDAO {

    @Insert
    fun addtbBarang(tbbar: tbBarang)

    @Update
    fun updateBarang(tbbar: tbBarang)

    @Delete
    fun deleteBarang(tbbar: tbBarang)



    @Query("SELECT * FROM tbBarang WHERE id_brg= :barang_id")
            fun tampilid(barang_id:Int) : List<tbBarang>

    @Query("SELECT * FROM tbBarang")
    fun tampilsemua() : List<tbBarang>
}