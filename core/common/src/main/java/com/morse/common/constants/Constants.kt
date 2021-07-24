package com.morse.common.constants

class Constants {

    companion object {

        const val CURRENT_LANGUAGE = "CUURENT-LANGUAGE"

        const val AR = "ar"

        const val EN = "en"

        /* For Regexes for Validating */

        const val email_regex =
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@" + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\." + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|" + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"

        /* For Conversation*/

        const val chatName = "chatName"
        const val chatImage = "chatImage"

        /* For Permissions and Intents */
        const val requestIdForOpenCameraPermission = 0
        const val requestIdForReadAndWritePermissions = 2
        const val requestIdForApplicationSetting = 4
        const val requestIdForOpenFile = 6
        const val requestImageCapture = 8
        const val requestSelectFromGallery = 10
        const val requestSelectAudio = 12
        const val requestSelectDoument = 14
        const val requestVideoCapture = 16

        /* For Files Locations */
        const val appFolderName = "Connect"
        const val imagesFolderName = "Images"
        const val audioFolderName = "Audios"
        const val videoFolderName = "Videos"
        const val documentFolderName = "Documents"

        /* For Notifications  */
        const val loadingNotificationId = 11
        const val loadNotificationChannelID = "LOADING-CHANNEL-ID"
        const val loadNotificationChannelName = "LOADING-CHANNEL-NAME"

        const val accessTokenKey = "accessToken"
        const val tokenExpiryKey = "accessTokenExpiry"

        const val fullDateWithOutTasSeperatorKey = "yyyy-MM-dd"
        const val fullDateWithTasSeperatorKey = "yyyy-MM-dd'T'HH:mm:ss"
        const val fullDateKey = "MMM. dd, yyyy hh:mm a"
        const val dateKey = "MMM dd, yyyy"
        const val timeKey = "hh:mm a"

        const val refreshTokenKey = "refreshToken"
        const val profileKey = "profileKey"
        const val isGroup = "isGroup"
        const val chatId = "chatId"
        const val userName = "USER-NAME"
        const val groupObject = "GROUP-OBJECT"
        const val entityObject = "entity-OBJECT"

        const val selectedCountryId = "SELECT-COUNTRY-ID"
        const val selectedSendAsId = "SELECT-SEND-AS"

        const val assigneUsersKey = "ASSIGNE-USERS-DATA"

        const val sendAsBundleKey = "SEND-AS-BUNDLE"
        const val sendAsValueKey = "SEND-AS-PARACABLE"

        const val messageIdKey = "MESSAGE-ID-KEY"
        const val groupIdKey = "GROUP-ID-KEY"
        const val entityIdKey = "ENTITY-ID-KEY"
        const val userIdKey = "USER-ID"
        const val userNameKey = "USER-NAME"
        const val userArabicNameKey = "USER-ARABIC-NAME"
        const val userLogoKey = "USER-LOGO"

        const val subEntityKey = "OPEN-SUB-ENTITY"
        const val isFromMyProfileKey = "FROM-MY-PROFILE"
        const val idKey = "ENTITY-OR-SUBENTITY-ID"
        const val nameKey = "ENTITY-OR-SUBENTITY-NAME"
        const val arabicNameKey = "ENTITY-OR-SUBENTITY-ARABIC-NAME"
        const val logoKey = "ENTITY-OR-SUBENTITY-LOGO"

        const val userBottomSheet = "USER-BOTTOM-SHEET"
        const val user_name_bottom_sheet_args = "USER-NAME"
        const val user_email_bottom_sheet_args = "USER-EMAIL"
        const val user_joined_date_bottom_sheet_args = "USER-JOINED-DATE"
        const val user_last_activity_date_bottom_sheet_args = "USER-LAST-ACTIVITY-DATE"
        const val user_last_activity_time_bottom_sheet_args = "USER-LAST-ACTIVITY-TIME"

        const val myPrivillages_args = "MY-PRIVILLAGES-LIST"

        const val callNumberRequestIdIntent = 1

        const val isValidToComposeKey = "IS-VALID-TO-COMPOSE"
        const val isFocusableKey = "IS-FOCUSABLE"
    }

}

