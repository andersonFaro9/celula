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
    import com.itextpdf.text.pdf.PdfWriter
    import java.io.FileOutputStream
    import java.text.SimpleDateFormat
    import java.util.*
    import com.itextpdf.text.*
    import com.itextpdf.text.Phrase
    import com.itextpdf.text.pdf.PdfPCell
    import com.itextpdf.text.pdf.PdfPTable
    import com.itextpdf.text.BaseColor
    import com.itextpdf.text.Font.*


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
                            salvaDados()
                    }
                    else -> salvaDados()
                }
            }

        }

        override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)

            if (requestCode == STORAGE_CODE) {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    salvaDados()
                }
                else {
                    makeText(this, "erro", LENGTH_SHORT).show()
                }

            }
            else {
                salvaDados()
            }

        }

        private fun salvaPdf() {

            val mDoc = Document(PageSize.A4, 0f, 0f, 0f, 0f)


            val mFileName = SimpleDateFormat( "yyyy-MM-dd hh:mm", Locale.getDefault()).format(System.currentTimeMillis())

            val mFilePath = Environment.getExternalStorageDirectory().toString()  + "/" + mFileName + ".pdf"

            try {
                PdfWriter.getInstance(mDoc, FileOutputStream(mFilePath))
                mDoc.open()

                val table = PdfPTable(1)

                val nomeCelula =  edit_nome_celula.text.toString()
                val enderecoCelula =  edit_endereco_celula.text.toString()
                val liderCelula =  edit_lider_celula.text.toString()
                val anfitriaoCelula =  edit_anfitriao_celula.text.toString()
                val dataCelula =  edit_data_celula.text.toString()
                val dia =  edit_dia_celula.text.toString()
                val horaCelula =  edit_hora_celula.text.toString()
                val temaCelula =  edit_tema_celula.text.toString()
                val textoCelula =  edit_texto_celula.text.toString()
                val nomesMembrosCelula=  edit_nomes_membros_celula.text.toString()
                val nomesVisitantesCelula=  edit_nomes_visitantes_celula.text.toString()
                val informe=  edit_informe.text.toString()



                var c1 = PdfPCell(Phrase("_____________RELATÓRIO DE CÉLULA_____________"))
                c1.horizontalAlignment = Element.ALIGN_CENTER

                table.addCell(c1)



                c1= PdfPCell(Phrase("CÉLULA:"))
                c1.horizontalAlignment = Element.ALIGN_LEFT
                table.addCell(c1)

                table.addCell(nomeCelula)


                c1= PdfPCell(Phrase("ENDEREÇO:"))
                c1.horizontalAlignment = Element.ALIGN_LEFT
                table.addCell(c1)
                table.addCell(enderecoCelula)

                c1= PdfPCell(Phrase("LÍDER:"))

                c1.horizontalAlignment = Element.ALIGN_LEFT
                table.addCell(c1)
                table.addCell(liderCelula)

                c1= PdfPCell(Phrase("ANFITRIÃO:"))
                c1.horizontalAlignment = Element.ALIGN_LEFT
                table.addCell(c1)
                table.addCell(anfitriaoCelula)

                c1= PdfPCell(Phrase("DATA:"))
                c1.horizontalAlignment = Element.ALIGN_LEFT
                table.addCell(c1)
                table.addCell(dataCelula)

                c1= PdfPCell(Phrase("DIA:"))
                c1.horizontalAlignment = Element.ALIGN_LEFT
                table.addCell(c1)
                table.addCell(dia)

                c1= PdfPCell(Phrase("HORA:"))
                c1.horizontalAlignment = Element.ALIGN_LEFT
                table.addCell(c1)
                table.addCell(horaCelula)

                c1= PdfPCell(Phrase("TEMA DA MENSAGEM:"))
                c1.horizontalAlignment = Element.ALIGN_LEFT
                table.addCell(c1)
                table.addCell(temaCelula)

                c1= PdfPCell(Phrase("TEXTO:"))
                c1.horizontalAlignment = Element.ALIGN_LEFT
                table.addCell(c1)

                table.addCell(textoCelula)


                c1= PdfPCell(Phrase("NOMES MEMBROS DA CÉLULA:"))
                c1.horizontalAlignment = Element.ALIGN_LEFT
                table.addCell(c1)
                table.addCell(nomesMembrosCelula)

                c1= PdfPCell(Phrase("NOMES DOS VISITANTES:"))
                c1.horizontalAlignment = Element.ALIGN_LEFT
                table.addCell(c1)
                table.addCell(nomesVisitantesCelula)


                c1= PdfPCell(Phrase("(OPCIONAL) INFORME: Mudança de endereço, horário, por que não teve célula, ausência de alguém da liderança da célula:"))
                c1.horizontalAlignment = Element.ALIGN_LEFT
                table.addCell(c1)
                table.addCell(informe)



                c1 = PdfPCell(Phrase("E todos os dias, no templo e nas casas, não cessavam de ensinar, e de anunciar a Jesus Cristo(Atos 5:42)"))
                c1.horizontalAlignment = Element.ALIGN_BOTTOM
                table.addCell(c1)


                mDoc.add(table)

                mDoc.close()

                Toast.makeText(this, "Salvo em \n$mFilePath", Toast.LENGTH_LONG).show()


            } catch (e: Exception) {
                Toast.makeText(this, "Sem permissão para armazenar na memória", Toast.LENGTH_LONG).show()
            }


        }

        private fun salvaDados() {

            if (edit_nome_celula.text.isEmpty()
                || edit_endereco_celula.text.isEmpty()
                || edit_lider_celula.text.isEmpty()
                || edit_anfitriao_celula.text.isEmpty()
                || edit_data_celula.text.isEmpty()
                || edit_dia_celula.text.isEmpty()
                || edit_hora_celula.text.isEmpty()
                || edit_tema_celula.text.isEmpty()
                || edit_texto_celula.text.isEmpty()
                || edit_nomes_membros_celula.text.isEmpty()
                || edit_nomes_visitantes_celula.text.isEmpty() ) {
                Toast.makeText(this, "Preencha os campos obrigatórios", Toast.LENGTH_SHORT).show()
            } else {
                finish()
                salvaPdf()
            }


        }


    }
