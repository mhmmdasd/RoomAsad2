package com.example.penjualan_muhammadaasad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.penjualan_muhammadaasad.room.Constant
import com.example.penjualan_muhammadaasad.room.dbPenjualan
import com.example.penjualan_muhammadaasad.room.tbBarang
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    lateinit var penjualanAdapter: PenjualanAdapter
    val db by lazy { dbPenjualan (this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        halEdit()
        setupRecyclerView()

    }

    override fun onStart() {
        super.onStart()
        loaddata()

        }
    fun loaddata(){
        CoroutineScope(Dispatchers.IO).launch {
            val barang = db.tbPenjualanDAO().tampilsemua ()
            Log.d("MainActivity", "dbResponse : $barang")
            withContext(Dispatchers.Main){
                penjualanAdapter.setData(barang)
            }
        }
    }

    fun setupRecyclerView (){
        penjualanAdapter = PenjualanAdapter(arrayListOf(),object :
        PenjualanAdapter.OnAdapterListener{
            override fun onClick(tbBarang: tbBarang) {
                intentEdit(tbBarang.id_brg,Constant.TYPE_READ)
            }
            override fun onUpdate(tbBarang: tbBarang) {
                intentEdit(tbBarang.id_brg, Constant.TYPE_UPDATE)
            }

            override fun onDelete(tbBarang: tbBarang) {
                deleteAlert(tbBarang)
            }
        })
        //idrecyclerview
        Listdatabarang.apply{
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = penjualanAdapter
        }
    }
    private fun deleteAlert(tbbar: tbBarang){
        val dialog = AlertDialog.Builder(this)
        dialog.apply {
            setTitle("Konfirmasi Hapus")
            setMessage("Yakin Hapus ${tbbar.nm_brg}?")
            setNegativeButton("Batal"){dialogInterface,i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Hapus"){dialogInterface,i->
                CoroutineScope(Dispatchers.IO).launch {
                    db.tbPenjualanDAO().deleteBarang(tbbar)
                    dialogInterface.dismiss()
                    loaddata()
                }
            }
        }
        dialog.show()
    }
    private fun halEdit(){
        btnInput.setOnClickListener{
           intentEdit(0, Constant.TYPE_CREATE)
        }
    }

    fun intentEdit(tbBarangid : Int, intenttype:Int){
        startActivity(
            Intent(applicationContext, EditActivity::class.java)
                .putExtra("intent_id",tbBarangid)
                .putExtra("intent_type", intenttype)
        )
    }
}