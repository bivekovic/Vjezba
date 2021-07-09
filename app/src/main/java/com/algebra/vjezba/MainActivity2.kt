package com.algebra.vjezba

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import java.util.*

class MainActivity2 : AppCompatActivity( ) {

    private lateinit var bOtvoriDatum   : Button
    private lateinit var bOtvoriVrijeme : Button

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_main2 )

        initWidgets( )
        setupListeners( )
    }

    fun initWidgets( ) {
        bOtvoriDatum   = findViewById( R.id.bOtvoriDatum )
        bOtvoriVrijeme = findViewById( R.id.bOtvoriVrijeme )

        val danas = Calendar.getInstance( )
        postaviDatum( danas.get( Calendar.YEAR ), danas.get( Calendar.MONTH )+1, danas.get( Calendar.DAY_OF_MONTH ) )
        postaviVrijeme( danas.get( Calendar.HOUR_OF_DAY ), danas.get( Calendar.MINUTE ) )
    }

    fun setupListeners( ) {
        val danas = Calendar.getInstance( )
        bOtvoriDatum.setOnClickListener {
            DatePickerDialog(
                this@MainActivity2,
                { dp, g, m, d -> postaviDatum( g, m+1, d ) },
                danas.get( Calendar.YEAR ),
                danas.get( Calendar.MONTH ),
                danas.get( Calendar.DAY_OF_MONTH )
            ).show( )
        }
        bOtvoriVrijeme.setOnClickListener {
            TimePickerDialog(
                this@MainActivity2,
                { dp, s, m -> postaviVrijeme( s, m ) },
                danas.get( Calendar.HOUR_OF_DAY ),
                danas.get( Calendar.MINUTE ),
                false
            ).show( )
        }
    }

    // Postavlja: "Datum: dd.mm.gggg." Datum: 09.07.2021. u tvDatum
    fun postaviDatum( godina : Int, mjesec : Int, dan : Int ) {
        val d = if( dan>9 ) ""+dan else "0$dan"
        val m = if( mjesec>9 ) ""+mjesec else "0$mjesec"
        bOtvoriDatum.text = "Datum: $d.$m.$godina."
    }

    fun postaviVrijeme( sati : Int, minute : Int ) {
        val s = if( sati>9 ) ""+sati else "0$sati"
        val m = if( minute>9 ) ""+minute else "0$minute"
        bOtvoriVrijeme.text = "Vrijeme: $s:$m"
    }

    override fun onCreateOptionsMenu( menu: Menu? ): Boolean {
        menuInflater.inflate( R.menu.main_menu2, menu )
        return super.onCreateOptionsMenu( menu )
    }

    override fun onOptionsItemSelected( item: MenuItem): Boolean {
        if( item.itemId==R.id.dialoziHome ) {
            finish( )
        }
        return super.onOptionsItemSelected( item )
    }
}