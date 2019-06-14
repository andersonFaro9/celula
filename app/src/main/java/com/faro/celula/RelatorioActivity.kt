package com.faro.celula

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import android.widget.Toast.*
import kotlinx.android.synthetic.main.relatorio_activity.*
import com.itextpdf.text.Document
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfWriter
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*


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

        val mDoc = Document()

        val mFileName = SimpleDateFormat("yyyy_MMdd_HH_mmsss", Locale.getDefault()).format(System.currentTimeMillis())

        val mFilePath = Environment.getExternalStorageDirectory().toString() + "/" + mFileName + ""


        try {
            PdfWriter.getInstance(mDoc, FileOutputStream(mFilePath))
            mDoc.open()

            val mText =  edit_text_content.text.toString()

            mDoc.addAuthor("Anderson")

            mDoc.add(Paragraph(mText))
            mDoc.close()

            Toast.makeText(this, "$mFileName.pdf  \n está salvo em \n$mFilePath", Toast.LENGTH_LONG).show()



        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }


    }


}
