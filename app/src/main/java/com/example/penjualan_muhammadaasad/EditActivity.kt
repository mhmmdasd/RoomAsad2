package com.example.penjualan_muhammadaasad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.penjualan_muhammadaasad.room.Constant
import com.example.penjualan_muhammadaasad.room.dbPenjualan
import com.example.penjualan_muhammadaasad.room.tbBarang
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {

    private val db by lazy { dbPenjualan(this) }
    private var tbBarangid : Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        setupView()
        tombolPerintah()
        tbBarangid = intent.getIntExtra("intent_id",tbBarangid)
        Toast.makeText(this, tbBarangid.toString(),Toast.LENGTH_SHORT).show()
    }

    fun setupView(){
        val intentType = intent.getIntExtra("intent_type",0)
        when (intentType){
            Constant.TYPE_CREATE ->{

            }
            Constant.TYPE_READ ->{
                btnSimpan.visibility = View.GONE
                btnUpdate.visibility = View.GONE
                tampildataid()
            }
            Constant.TYPE_UPDATE ->{
                btnSimpan.visibility = View.GONE
                tampildataid()
            }
        }
    }

    private fun tombolPerintah(){
        btnSimpan.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.tbPenjualanDAO().addtbBarang(
                    tbBarang(
                        EtIdBrg.text.toString().toInt(),
                        EtNmBrg.text.toString(),
                        EtHrgBrg.text.toString().toInt(),
                        EtStok.text.toString().toInt()
                    )
                )
                finish()
            }
        }
        btnUpdate.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.tbPenjualanDAO().updateBarang(
                    tbBarang(
                        EtIdBrg.text.toString().toInt(),
                        EtNmBrg.text.toString(),
                        EtHrgBrg.text.toString().toInt(),
                        EtStok.text.toString().toInt()
                    )
                )
                finish()
            }
        }
    }
    fun tampildataid(){
        tbBarangid = intent.getIntExtra("intent_id",0)
        CoroutineScope(Dispatchers.IO).launch {
            val data2 = db.tbPenjualanDAO().tampilid(tbBarangid).get(0)
            val id : String=data2.id_brg.toString()
            val harga : String=data2.hrg_brg.toString()
            val stok : String= data2.stok.toString()

            EtIdBrg.setText(id)
            EtNmBrg.setText(data2.nm_brg)
            EtHrgBrg.setText(harga)
            EtStok.setText(stok)
        }
    }


}