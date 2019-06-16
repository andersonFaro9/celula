package com.faro.celula

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import android.widget.Toast.*
import kotlinx.android.synthetic.main.relatorio_activity.*
import com.itextpdf.text.pdf.PdfWriter
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.itextpdf.text.*
import com.itextpdf.text.Phrase
import com.itextpdf.text.pdf.PdfPCell
import com.itextpdf.text.pdf.PdfPTable

class RelatorioActivity : AppCompatActivity() {

    val STORAGE_CODE:Int = 100

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        supportActionBar?.elevation = 0F

        setContentView(R.layout.relatorio_activity)

        salva_pdf?.setOnClickListener {
            when {
                Build.VERSION.SDK_INT > Build.VERSION_CODES.M ->
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    var permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)

                    requestPermissions(permissions, STORAGE_CODE)

                } else {
                    salvaPdf()
                }
                else -> salvaPdf()
            }
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when {
            requestCode == STORAGE_CODE -> {
                when {
                    grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED -> salvaPdf()
                    else -> makeText(this, "erro", LENGTH_SHORT).show()
                }

            }
            else -> {
                salvaPdf()
            }
        }

    }

    private fun salvaPdf() {

        val mDoc = Document(PageSize.A4, 0f, 0f, 0f, 0f)


        val mFileName = SimpleDateFormat( "yyyy-MM-dd hh:mm", Locale.getDefault()).format(System.currentTimeMillis())

        val mFilePath = Environment.getExternalStorageDirectory().toString() + "/" + mFileName + ".pdf"

        try {
            PdfWriter.getInstance(mDoc, FileOutputStream(mFilePath))
            mDoc.open()

            val table = PdfPTable(1)


            val mText =  edit_nome_celula.text.toString()

            var c1 = PdfPCell(Phrase("_____________RELATÓRIO DE CÉLULA_____________"))
            c1.horizontalAlignment = Element.ALIGN_CENTER
            table.addCell(c1)


            c1= PdfPCell(Phrase("CÉLULA:"))
            c1.horizontalAlignment = Element.ALIGN_LEFT
            table.addCell(c1)

            table.addCell(mText)


            table.addCell("")
            table.addCell("")
            table.addCell("")
            table.addCell("")



            mDoc.add(table)
            mDoc.close()

            Toast.makeText(this, "Salvo em \n$mFilePath", Toast.LENGTH_LONG).show()


        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }


    }


//    fun validaDados() {
//
//        edit_nome_celula?.addTextChangedListener(object : TextWatcher {
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                salva_pdf.visibility = View.INVISIBLE
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//
//                salva_pdf.visibility = View.VISIBLE
//                salva_pdf.setTextColor(Color.parseColor("#297AE0"))
//
//                when {
//                    edit_nome_celula.text.isEmpty() -> {
//                        salva_pdf.visibility = View.INVISIBLE
//                    }
//
//                    else -> salvaDados()
//                }
//
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//
//            }
//
//        })
//
//    }

//    private fun salvaDados() {
//
//        salva_pdf.setOnClickListener {
//
//            if (!edit_nome_celula.text.isEmpty() && !edit_endereco_celula.text.isEmpty() && !edit_lider_celula.text.isEmpty()) {
//
//
//                    startActivity(Intent(this, MainActivity::class.java))
//
//
//
//            }
//        }
//
//    }


}
