package com.expertapps.connect.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.OpenableColumns
import androidx.core.content.FileProvider
import com.morse.common.constants.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import com.morse.common.R
import io.paperdb.BuildConfig
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*

/*
*
*
*                  Change The ConfigBuild from import io.paperdb.BuildConfig to your Application
*
*
* */
data class FileInfo(
    var fileName: String = "",
    var filSize: String = "",
    var fileType: AttachmentType = AttachmentType.PDF,
    var fileTypeLogo: Int = R.drawable.ic_document
)

enum class AttachmentType {
    PDF,
    DOC,
    TXT,
    PPT,
    XLS,
    PNG,
    JPG,
    ZIP,
    AUDIO,
    VIDEO
}

enum class DirectoryType {
    ROOT,
    DOCUMENTS,
    IMAGES,
    VIDEOS,
    AUDIOS;

    companion object {

        val ROOT_PATH = Environment.getExternalStorageDirectory().absolutePath

        fun ROOT_ARGS(context: Context) = Pair(
            context.getExternalFilesDir(null)?.absolutePath ?: "/storage/emulated/0/",
            Constants.appFolderName
        )

        fun DOCUMENTS_ARGS(context: Context) = Pair(
            context.getExternalFilesDir(null)?.absolutePath.plus("/${Constants.appFolderName}"),
            Constants.documentFolderName
        )

        fun IMAGES_ARGS(context: Context) = Pair(
            context.getExternalFilesDir(null)?.absolutePath.plus("/${Constants.appFolderName}"),
            Constants.imagesFolderName
        )

        fun VIDEOS_ARGS(context: Context) = Pair(
            context.getExternalFilesDir(null)?.absolutePath.plus("/${Constants.appFolderName}"),
            Constants.videoFolderName
        )

        fun AUDIOS_ARGS(context: Context) = Pair(
            context.getExternalFilesDir(null)?.absolutePath.plus("/${Constants.appFolderName}"),
            Constants.audioFolderName
        )
    }

}

class FileUtils {

    companion object {

        private lateinit var appDirectory: File

        fun setUpDirectories(context: Context, type: DirectoryType): Boolean {
            when (type) {
                DirectoryType.ROOT -> {
                    return createNewDirectoryInExternalStorage(
                        context,
                        DirectoryType.ROOT_ARGS(context).first,
                        DirectoryType.ROOT_ARGS(context).second
                    )
                }
                DirectoryType.DOCUMENTS -> {
                    return createNewDirectoryInExternalStorage(
                        context,
                        DirectoryType.DOCUMENTS_ARGS(context).first,
                        DirectoryType.DOCUMENTS_ARGS(context).second
                    )
                }
                DirectoryType.IMAGES -> {
                    return createNewDirectoryInExternalStorage(
                        context,
                        DirectoryType.IMAGES_ARGS(context).first,
                        DirectoryType.IMAGES_ARGS(context).second
                    )
                }
                DirectoryType.VIDEOS -> {
                    return createNewDirectoryInExternalStorage(
                        context,
                        DirectoryType.VIDEOS_ARGS(context).first,
                        DirectoryType.VIDEOS_ARGS(context).second
                    )
                }
                DirectoryType.AUDIOS -> {
                    return createNewDirectoryInExternalStorage(
                        context,
                        DirectoryType.AUDIOS_ARGS(context).first,
                        DirectoryType.AUDIOS_ARGS(context).second
                    )
                }
            }
        }

         fun getDirectoryTypeByFileName (fileName : String) : DirectoryType {
            val type = getAttachmentType(fileName)
            if (type == AttachmentType.PNG || type == AttachmentType.JPG){
                return DirectoryType.IMAGES
            }else if (type == AttachmentType.PDF || type == AttachmentType.DOC || type == AttachmentType.PPT || type == AttachmentType.TXT || type == AttachmentType.XLS || type == AttachmentType.ZIP){
                return DirectoryType.DOCUMENTS
            }else if (type == AttachmentType.VIDEO ){
                return DirectoryType.VIDEOS
            }else if (type == AttachmentType.AUDIO ){
                return DirectoryType.AUDIOS
            }
            return DirectoryType.ROOT
        }

        private fun createNewDirectoryInExternalStorage(
            context: Context,
            location: String,
            name: String
        ): Boolean {
            appDirectory = File(location, name)
            if (!appDirectory.exists()) {
                return appDirectory.mkdirs()
            } else {
                return true
            }
        }

        fun saveFile(
            context: Context,
            fileName: String,
            fileData: ByteArray,
        ): Flow<Pair<Boolean, String>> {
            return flow {
                try {
                    val newFile = File(appDirectory.absolutePath, fileName)
                    val fileOutputStream = FileOutputStream(newFile)
                    fileOutputStream.write(fileData)
                    fileOutputStream.close()
                    emit(Pair(true, getActualPathFile(context, newFile)))
                } catch (e: Exception) {
                    Pair(false, null)
                }
            }

        }

        fun getActualPathFile(context: Context, downloadedFile: File): String {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                return FileProvider.getUriForFile(
                    context, BuildConfig.APPLICATION_ID + ".provider",
                    downloadedFile
                ).toString()
            } else {
                return Uri.fromFile(downloadedFile).toString()
            }
        }

        fun getMimeType(filePath: String): String {
            if (filePath.contains(".doc") || filePath.contains(".docx")) {
                return "application/msword"
            } else if (filePath.contains(".pdf")) {
                // PDF file
                return "application/pdf"
            } else if (filePath.contains(".ppt") || filePath.contains(".pptx")) {
                // Powerpoint file
                return "application/vnd.ms-powerpoint"
            } else if (filePath.contains(".xls") || filePath.contains(".xlsx")) {
                // Excel file
                return "application/vnd.ms-excel"
            } else if (filePath.contains(".zip") || filePath.contains(".rar")) {
                // WAV audio file
                return "application/x-wav"
            } else if (filePath.contains(".rtf")) {
                // RTF file
                return "application/rtf"
            } else if (filePath.contains(".wav") || filePath.contains(".mp3")) {
                // WAV audio file
                return "audio/x-wav"
            } else if (filePath.contains(".gif")) {
                // GIF file
                return "image/gif"
            } else if (filePath.contains(".jpg") || filePath
                    .contains(".jpeg") || filePath.contains(".png")
            ) {
                // JPG file
                return "image/jpeg"
            } else if (filePath.contains(".txt")) {
                // Text file
                return "text/plain"
            } else if (filePath.contains(".3gp") || filePath
                    .contains(".mpg") || filePath.contains(".mpeg") || filePath
                    .contains(".mpe") || filePath.contains(".mp4") || filePath
                    .contains(".avi")
            ) {
                // Video files
                return "video/*"
            } else {
                return "*/*"
            }
        }

        private fun getAttachmentType(fileName: String): AttachmentType {
            val listOfData = fileName.toLowerCase().split(".")
            val data = listOfData.get(listOfData.size - 1)
            if (data.equals("pdf")) {
                return AttachmentType.PDF
            } else if (data.equals("doc") || data.equals("docx")) {
                return AttachmentType.DOC
            } else if (data.equals("txt")) {
                return AttachmentType.TXT
            } else if (data.equals("ppt")) {
                return AttachmentType.PPT
            } else if (data.equals("xls") || data.equals("xlsx")) {
                return AttachmentType.XLS
            } else if (data.equals("png")) {
                return AttachmentType.PNG
            } else if (data.equals("jpg") || data.equals("jpeg")) {
                return AttachmentType.JPG
            } else if (data.equals("zip") || data.equals("rar")) {
                return AttachmentType.ZIP
            } else if (data.equals("audio") || data.equals("mp3")) {
                return AttachmentType.AUDIO
            } else if (data.equals("video") || data.equals("mp4")) {
                return AttachmentType.VIDEO
            }
            return AttachmentType.PDF
        }

        fun getAttachmentImage(fileName: String): Int {
            val attachmentType = getAttachmentType(fileName)
            if (attachmentType == AttachmentType.PDF) {
                return R.drawable.ic_pdf
            } else if (attachmentType == AttachmentType.DOC) {
                return R.drawable.ic_document
            } else if (attachmentType == AttachmentType.TXT) {
                return R.drawable.ic_document
            } else if (attachmentType == AttachmentType.PPT) {
                return R.drawable.ic_document
            } else if (attachmentType == AttachmentType.XLS) {
                return R.drawable.ic_document
            } else if (attachmentType == AttachmentType.PNG) {
                return R.drawable.ic_photo_document
            } else if (attachmentType == AttachmentType.JPG) {
                return R.drawable.ic_photo_document
            } else if (attachmentType == AttachmentType.ZIP) {
                return R.drawable.ic_document
            } else if (attachmentType == AttachmentType.AUDIO) {
                return R.drawable.ic_headset
            } else if (attachmentType == AttachmentType.VIDEO) {
                return R.drawable.ic_video
            }
            return R.drawable.ic_pdf
        }

        fun loadFileInfo(resultIntent: Intent, activity: Activity): FileInfo {
            val fileInfo = FileInfo()
            resultIntent.data?.let { returnUri ->
                activity.contentResolver?.query(returnUri, null, null, null, null)
            }?.use { cursor ->

                val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
                cursor.moveToFirst()
                fileInfo.fileName = cursor.getString(nameIndex)
                val size = cursor.getLong(sizeIndex)
                var sizeResult = ""
                if (size >= 1024 * 1024) {
                    sizeResult = "${size / (1024 * 1024)} MB"
                } else if (size >= 1024 && size < 1024 * 1024) {
                    sizeResult = "${size / 1000f} KB"
                } else {
                    sizeResult = "$size B"
                }
                fileInfo.filSize = sizeResult
                fileInfo.fileType = getAttachmentType(fileName = fileInfo.fileName )
                fileInfo.fileTypeLogo = getAttachmentImage(fileName = fileInfo.fileName )
            }
            return fileInfo
        }

        fun getFileSize(totalSize: Double): String {
            val sizeResult: String
            if (totalSize >= 1024 * 1024) {
                sizeResult = "${totalSize / (1024 * 1024)} MB"
            } else if (totalSize >= 1024 && totalSize < 1024 * 1024) {
                sizeResult = "${totalSize / 1000f} KB"
            } else {
                sizeResult = "$totalSize BYTE"
            }
            return sizeResult
        }

        fun createMultiPart(
            activity: Activity,
            data: Intent,
            fileName: String,
            partName: String
        ): MultipartBody.Part {
            val inputStream = activity.contentResolver.openInputStream(data.data!!)
            val requestFile = getBytes(inputStream!!)
                .toRequestBody(
                    "multipart/form-data".toMediaTypeOrNull(),
                    0
                )
            return MultipartBody.Part.createFormData(partName, fileName, requestFile)
        }

        fun createMultiPartFromBitMap(
            activity: Activity,
            image : Bitmap ,
            fileName: String,
            partName: String
        ): MultipartBody.Part {
            val stream = ByteArrayOutputStream()
            image.compress(Bitmap.CompressFormat.PNG, 90, stream)
            val requestFile = stream.toByteArray()
                .toRequestBody(
                    "multipart/form-data".toMediaTypeOrNull(),
                    0
                )
            return MultipartBody.Part.createFormData(partName, fileName, requestFile)
        }

        private fun getBytes(inputStream: InputStream): ByteArray {
            val byteBuff = ByteArrayOutputStream()
            val buffSize = 1024
            val buff = ByteArray(buffSize)
            var len = 0
            len = inputStream.read(buff)
            while (len != -1) {
                byteBuff.write(buff, 0, len)
                len = inputStream.read(buff)
            }

            return byteBuff.toByteArray()
        }

        fun getImageNameWithTimeForCaptureingImages () : String {
            return  "PNG_${getCurrentTime(Constants.fullDateWithOutTasSeperatorKey).plus(".png")}"
        }

        fun getCurrentTime (format : String) = SimpleDateFormat(format).format(Date())

    }

}