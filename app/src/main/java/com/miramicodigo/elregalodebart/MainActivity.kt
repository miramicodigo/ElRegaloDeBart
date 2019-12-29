package com.miramicodigo.elregalodebart

import android.annotation.TargetApi
import android.media.AudioAttributes
import android.media.SoundPool
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.os.Build
import android.media.AudioManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

lateinit var spIdiota: SoundPool
lateinit var spCallate: SoundPool
lateinit var spVeteAlDiablo: SoundPool
var resIdiota = 0
var resCallate = 0
var resVeteAlDiablo = 0

open class MainActivity : AppCompatActivity(), View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        tvTitle.text = resources.getString(R.string.main_title)
        btnCallate.setOnClickListener(this)
        btnVeteAlDiablo.setOnClickListener(this)
        btnIdiota.setOnClickListener(this)

        createSoundPool()
    }

    private fun createSoundPool() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            createNewSoundPool()
        } else {
            createOldSoundPool()
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun createNewSoundPool() {
        val attributes = AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build()
        spIdiota = SoundPool.Builder().setAudioAttributes(attributes).build()
        spCallate = SoundPool.Builder().setAudioAttributes(attributes).build()
        spVeteAlDiablo = SoundPool.Builder().setAudioAttributes(attributes).build()
        loadSoundPool()
    }


    @Suppress("DEPRECATION")
    fun createOldSoundPool() {
        spIdiota = SoundPool(15, AudioManager.STREAM_MUSIC, 0)
        spCallate = SoundPool(10, AudioManager.STREAM_MUSIC, 0)
        spVeteAlDiablo = SoundPool(12, AudioManager.STREAM_MUSIC, 0)
        loadSoundPool()
    }

    fun loadSoundPool() {
        resIdiota = spIdiota.load(applicationContext, R.raw.idiota, 1)
        resCallate = spCallate.load(applicationContext, R.raw.callate, 1)
        resVeteAlDiablo = spVeteAlDiablo.load(applicationContext, R.raw.vete_al_diablo, 1)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btnCallate -> callate()
            R.id.btnVeteAlDiablo -> veteAlDiablo()
            R.id.btnIdiota -> idiota()
        }
    }


    fun idiota() {
        if(resIdiota != 0) {
            spIdiota.play(resIdiota, 1.0F, 1.0F,
                0, 0, 1F)
        }
    }
    fun callate() {
        if(resCallate != 0) {
            spCallate.play(resCallate, 1.0F, 1.0F,
                0, 0, 1F)
        }
    }

    fun veteAlDiablo() {
        if(resVeteAlDiablo != 0) {
            spVeteAlDiablo.play(resVeteAlDiablo, 1.0F, 1.0F,
                0, 0, 1F)
        }
    }
}