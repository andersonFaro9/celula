package com.faro.celula

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.AlarmClock
import android.provider.CalendarContract
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText
import com.itextpdf.text.*
import com.itextpdf.text.Font
import com.itextpdf.text.pdf.PdfPCell
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfWriter
import kotlinx.android.synthetic.main.relatorio_activity.*
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import com.itextpdf.text.FontFactory
import com.itextpdf.text.Phrase


class RelatorioActivity : AppCompatActivity() {

    val STORAGE_CODE: Int = 100

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
                        salvaDados()

                    }
                else -> salvaDados()
            }
        }

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_form, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when {

            item.itemId == R.id.fechar -> {
                finish()

                return true
            }

        }

        return super.onOptionsItemSelected(item)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == STORAGE_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                salvaDados()
            } else {
                makeText(this, "erro", LENGTH_SHORT).show()
            }

        } else {
            salvaDados()
        }

    }

    private fun salvaPdf() {

        val mDoc = Document(PageSize.A4, 0f, 0f, 0f, 0f)

        val mFileName =
            SimpleDateFormat("EE_MMM_dd_HH:mm:ss_yyyy", Locale.getDefault()).format(System.currentTimeMillis())

        val mFilePath = Environment.getExternalStorageDirectory().toString() + "/" + "celula_" + mFileName + ".pdf"

        try {
            PdfWriter.getInstance(mDoc, FileOutputStream(mFilePath))
            mDoc.open()

            val table = PdfPTable(1)

            val nomeCelula = edit_nome_celula.text.toString()
            val enderecoCelula = edit_endereco_celula.text.toString()
            val liderCelula = edit_lider_celula.text.toString()
            val anfitriaoCelula = edit_anfitriao_celula.text.toString()
            val dataCelula = edit_data_celula.text.toString()
            val dia = edit_dia_celula.text.toString()
            val horaCelula = edit_hora_celula.text.toString()
            val temaCelula = edit_tema_celula.text.toString()
            val textoCelula = edit_texto_celula.text.toString()
            val nomesMembrosCelula = edit_nomes_membros_celula.text.toString()
            val nomesVisitantesCelula = edit_nomes_visitantes_celula.text.toString()
            val informe = edit_informe.text.toString()


            val titulo = FontFactory.getFont(FontFactory.TIMES, 16f, Font.BOLD)
            var c1 = PdfPCell(Phrase("Relatório da Célula", titulo))

            c1.horizontalAlignment = Element.ALIGN_CENTER

            table.addCell(c1)

            val topico = FontFactory.getFont(FontFactory.TIMES, 14f, Font.BOLD)

            c1 = PdfPCell(Phrase("Célula:", topico))
            c1.horizontalAlignment = Element.ALIGN_LEFT
            table.addCell(c1)

            table.addCell(nomeCelula)


            c1 = PdfPCell(Phrase("Endereço:", topico))
            c1.horizontalAlignment = Element.ALIGN_LEFT
            table.addCell(c1)
            table.addCell(enderecoCelula)

            c1 = PdfPCell(Phrase("Líder:"))

            c1.horizontalAlignment = Element.ALIGN_LEFT
            table.addCell(c1)
            table.addCell(liderCelula)

            c1 = PdfPCell(Phrase("Anfitrião:", topico))
            c1.horizontalAlignment = Element.ALIGN_LEFT
            table.addCell(c1)
            table.addCell(anfitriaoCelula)

            c1 = PdfPCell(Phrase("Data:", topico))
            c1.horizontalAlignment = Element.ALIGN_LEFT
            table.addCell(c1)
            table.addCell(dataCelula)

            c1 = PdfPCell(Phrase("Dia:", topico))
            c1.horizontalAlignment = Element.ALIGN_LEFT
            table.addCell(c1)
            table.addCell(dia)

            c1 = PdfPCell(Phrase("Hora:", topico))
            c1.horizontalAlignment = Element.ALIGN_LEFT
            table.addCell(c1)
            table.addCell(horaCelula)

            c1 = PdfPCell(Phrase("Tema da mensagem:", topico))
            c1.horizontalAlignment = Element.ALIGN_LEFT
            table.addCell(c1)
            table.addCell(temaCelula)

            c1 = PdfPCell(Phrase("Texto do tema:", topico))
            c1.horizontalAlignment = Element.ALIGN_LEFT
            table.addCell(c1)

            table.addCell(textoCelula)


            c1 = PdfPCell(Phrase("Nomes(membros) da célula:", topico))
            c1.horizontalAlignment = Element.ALIGN_LEFT
            table.addCell(c1)
            table.addCell(nomesMembrosCelula)

            c1 = PdfPCell(Phrase("Nomes(visitantes) da célula:", topico))
            c1.horizontalAlignment = Element.ALIGN_LEFT
            table.addCell(c1)
            table.addCell(nomesVisitantesCelula)


            c1 = PdfPCell(
                Phrase(
                    "Mudança de endereço, horário, motivo por que não teve célula, ausência de alguém da liderança da célula?",
                    topico
                )
            )
            c1.horizontalAlignment = Element.ALIGN_LEFT
            table.addCell(c1)
            table.addCell(informe)


            val topico2 = FontFactory.getFont(FontFactory.TIMES_ITALIC, 13f, Font.BOLD)

            c1 = PdfPCell(
                Phrase(
                    "E todos os dias, no templo e nas casas, não cessavam de ensinar, e de anunciar a Jesus Cristo (Atos 5:42)",
                    topico2
                )
            )
            c1.horizontalAlignment = Element.ALIGN_BOTTOM
            table.addCell(c1)


            mDoc.add(table)

            mDoc.close()
            //Salvo em
            //$mFilePath
            Toast.makeText(this, "Salvo na memória do celular", Toast.LENGTH_LONG).show()


        } catch (e: Exception) {
            Toast.makeText(this, "Sem permissão para armazenar na memória", Toast.LENGTH_LONG).show()
        }


    }

    private fun salvaDados() {

        if (edit_nome_celula.text.isEmpty()

            || edit_endereco_celula.text.isEmpty()

            || edit_lider_celula.text.isEmpty()
            || edit_anfitriao_celula.text.isEmpty()
            || edit_data_celula.text!!.isEmpty()
            || edit_dia_celula.text.isEmpty()
            || edit_hora_celula.text!!.isEmpty()
            || edit_tema_celula.text.isEmpty()
            || edit_texto_celula.text.isEmpty()
            || edit_nomes_membros_celula.text.isEmpty()
            || edit_informe.text.isEmpty()
            || edit_nomes_visitantes_celula.text.isEmpty()
        ) {

            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
        } else {
            finish()
            salvaPdf()
        }


    }


}
