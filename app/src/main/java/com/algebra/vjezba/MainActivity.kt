package com.algebra.vjezba

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.DatePicker
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.util.*

class MainActivity : AppCompatActivity( ) {

    private lateinit var tvDatum : TextView
    private lateinit var dp      : DatePicker

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_main )

        initWidgets( )
        setupListeners( )
    }

    fun setupListeners( ) {

        val danas = Calendar.getInstance( )

        dp.init(
            danas.get( Calendar.YEAR ),
            danas.get( Calendar.MONTH ),
            danas.get( Calendar.DAY_OF_MONTH ),
            { dp, g, m, d -> postaviDatum(g, m+1, d) }
        )
    }

    fun initWidgets( ) {
        tvDatum = findViewById( R.id.tvDatum )
        dp      = findViewById( R.id.odabirDatuma )

        val danas = Calendar.getInstance( )
        postaviDatum( danas.get( Calendar.YEAR ), danas.get( Calendar.MONTH )+1, danas.get( Calendar.DAY_OF_MONTH ) )

        // SAMO API >= 21
        // dp.firstDayOfWeek = Calendar.MONDAY
    }

    // Postavlja: "Datum: dd.mm.gggg." Datum: 09.07.2021. u tvDatum
    fun postaviDatum( godina : Int, mjesec : Int, dan : Int ) {
        val d = if( dan>9 ) ""+dan else "0$dan"
        val m = if( mjesec>9 ) ""+mjesec else "0$mjesec"
        tvDatum.text = "Datum: $d.$m.$godina."
    }

    override fun onCreateOptionsMenu( menu: Menu? ): Boolean {
        menuInflater.inflate( R.menu.main_menu, menu )
        return super.onCreateOptionsMenu( menu )
    }

    override fun onOptionsItemSelected( item: MenuItem ): Boolean {
        if( item.itemId==R.id.dialoziMenu ) {
            val intent = Intent( this, MainActivity2::class.java )
            startActivity( intent )
        }
        return super.onOptionsItemSelected( item )
    }
}