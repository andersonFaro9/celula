package com.faro.celula

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.ShareActionProvider
import android.view.Menu
import android.widget.Toast
import android.widget.Toast.*
import kotlinx.android.synthetic.main.relatorio_activity.*
import com.itextpdf.text.pdf.PdfWriter
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

import android.support.v7.widget.SearchView
import android.view.MenuItem
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
                Build.VERSION.SDK_INT > Build.VERSION_CODES.M -> if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
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

            var c1 = PdfPCell(Phrase("Relatório de célula"))
            c1.horizontalAlignment = Element.ALIGN_LEFT


            table.addCell(c1)

            c1 = PdfPCell(Phrase("Header 2"))
            c1.horizontalAlignment = Element.ALIGN_LEFT
            table.addCell(c1)


            c1 = PdfPCell(Phrase("Header 3"))
            c1.horizontalAlignment = Element.ALIGN_LEFT
            table.addCell(c1)

            table.addCell("1.0")
            table.addCell("1.1")
            table.addCell("1.2")
            table.addCell("2.1")
            table.addCell("2.2")
            table.addCell("2.3")
            table.addCell("2.4")
            table.addCell("2.5")


            table.addCell(c1)

            mDoc.add(table)
            mDoc.close()

            Toast.makeText(this, "Salvo em \n$mFilePath", Toast.LENGTH_LONG).show()


        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }


    }


}
