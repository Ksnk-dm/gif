package com.ksnk.gif.ui.main.adapter


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestOptions
import com.ksnk.gif.GifViewActivity
import com.ksnk.gif.data.empty.Gif
import com.ksnk.gif.R
import com.ksnk.gif.ui.main.MainActivityViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.io.File
import java.io.FileOutputStream
import java.nio.ByteBuffer
import java.util.*


class MainRecyclerViewAdapter(context: Context) :
    RecyclerView.Adapter<MainViewHolder>() {

    private var listGifs: ArrayList<Gif>? = null
    private var context: Context? = null
    private var listData: List<Gif>? = null
    private var viewModel: MainActivityViewModel? = null

    fun setUpdatedGifs(listGifs: ArrayList<Gif>) {
        this.listGifs = listGifs
    }

    fun setView(viewModel: MainActivityViewModel) {
        this.viewModel = viewModel
    }

    init {
        listGifs = ArrayList<Gif>()
        listData = ArrayList<Gif>()
        this.context = context
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val layoutInflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return MainViewHolder(
            layoutInflater.inflate(
                R.layout.list_items,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val gif = viewModel?.getId(listGifs?.get(position)?.id.toString())
        if (gif?.delStatus == false) {
            Glide.with(holder.imageView)
                .asGif()
                .load(listGifs?.get(position)?.images?.original?.url)
                .apply(RequestOptions.centerCropTransform())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView)
            holder.textViewId.text = listGifs?.get(position)?.id.toString()
            CoroutineScope(Dispatchers.IO).async {
                saveImage(
                    Glide.with(holder.imageView)
                        .asGif()
                        .load(listGifs?.get(position)?.images?.original?.url)
                        .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                        .error(android.R.drawable.stat_notify_error)
                        .submit()
                        .get(), listGifs?.get(position)?.id.toString()
                )

            }
        } else {
            removeAt(position)
        }
        holder.imageButtonDel.setOnClickListener {
            delFile(listGifs?.get(position)?.id.toString(), position)
        }

        holder.itemView.setOnClickListener {
            val int: Intent = Intent(context, GifViewActivity::class.java)
            int.putExtra("list", listGifs)
            int.putExtra("position", position)
            context?.startActivity(int)
        }


    }


    private fun removeAt(position: Int) {
        listGifs?.removeAt(position)
    }

    private fun delFile(fileName: String, position: Int) {
        val storageDir = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                .toString() + "/cacheGifs"
        )
        val file = File("$storageDir/$fileName.gif")
        Log.d("dddd", file.absolutePath)
        file.delete()


        val gif = listGifs!![position]
        gif.delStatus = true
        viewModel?.update(gif)


        listGifs?.remove(listGifs!![position])

        notifyDataSetChanged()
    }

    private fun saveImage(image: GifDrawable, name: String): String? {
        var savedImagePath: String? = null
        val imageFileName = "$name.gif"
        val storageDir = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                .toString() + "/cacheGifs"
        )
        var success = true
        if (!storageDir.exists()) {
            success = storageDir.mkdirs()
        }
        if (success) {
            val imageFile = File(storageDir, imageFileName)
            savedImagePath = imageFile.absolutePath
            try {
                val byteBuffer = image.buffer
                val output = FileOutputStream(imageFile)
                val bytes = ByteArray(byteBuffer.capacity())
                (byteBuffer.duplicate().clear() as ByteBuffer).get(bytes)
                output.write(bytes, 0, bytes.size)
                output.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            // Add the image to the system gallery
            galleryAddPic(savedImagePath)
            //Toast.makeText(this, "IMAGE SAVED", Toast.LENGTH_LONG).show() // to make this working, need to manage coroutine, as this execution is something off the main thread
        }
        return savedImagePath
    }

    private fun galleryAddPic(imagePath: String?) {
        imagePath?.let { path ->
            val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
            val f = File(path)
            val contentUri: Uri = Uri.fromFile(f)
            mediaScanIntent.data = contentUri
            context?.sendBroadcast(mediaScanIntent)
        }
    }

    override fun getItemCount(): Int {
        return listGifs?.size ?: 0
    }
}