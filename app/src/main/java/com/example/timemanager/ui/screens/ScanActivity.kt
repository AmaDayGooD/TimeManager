package com.example.timemanager.ui.screens

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.timemanager.databinding.ActivityScanBinding
import com.journeyapps.barcodescanner.DecoratedBarcodeView

class ScanActivity : AppCompatActivity() {

    private lateinit var barcodeScannerView: DecoratedBarcodeView
    private lateinit var binding: ActivityScanBinding

    companion object {
        fun createIntentScanActivity(context: Context): Intent {
            return Intent(context, ScanActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
        }

        const val CAMERA_REQUEST_CODE = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkCameraPermission()
    }

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST_CODE
            )
        } else {
            showScanner()
        }
    }

    private fun showScanner() {
        barcodeScannerView = binding.zxingBarcodeScanner

        barcodeScannerView.decodeContinuous { result ->
            val intent = Intent()
            intent.putExtra("SCAN_RESULT", result.text)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        if (::barcodeScannerView.isInitialized) {
            barcodeScannerView.resume()
        }
    }

    override fun onPause() {
        super.onPause()
        if (::barcodeScannerView.isInitialized) {
            barcodeScannerView.pause()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showScanner()
            } else {
                // Permission denied
                finish() // Закрываем активность, если разрешение не предоставлено
            }
        }
    }
}