package com.example.penjualan_muhammadaasad.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class tbBarang (
        @PrimaryKey
        val id_brg: Int,
        val nm_brg: String,
        val hrg_brg: Int,
        val stok: Int

        )