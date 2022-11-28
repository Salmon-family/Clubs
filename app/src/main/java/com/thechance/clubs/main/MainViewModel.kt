package com.thechance.clubs.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfalah.remote.ClubService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val service: ClubService) : ViewModel() {


    fun test() {
        viewModelScope.launch {
//            val test = service.login("nadaFeteiha", "hussien1").body()
//            val test = service.getUserDetails(6).body()
//            val test = service.getUserFriends(6).body()?.payload
//            val test = service.getUserFriendRequests(11).body()
//            val test = service.isFriendWith(6, 31).body()
//            val test = service.removeFriend(6, 31).body()
//            val test = service.editUser(31, "lesid32214@lance7.com", "male", "Test", "Test", "123456").body()
//            val test = service.addLike(6, 253, LikeType.post).body()
//            val test = service.removeLike(6, 253, LikeType.post).body()
//            val test = service.addComment(6, 253, "Test 2").body()
//            val  test = service.deleteComment(6,251).body()
//            val  test = service.editComment(252,"Test 444").body()
//            val test = service.getComments(31, LikeType.post, 232).body()
//            val test = service.getGroupMembers(188).body()
//            val test = service.editGroups(235, 6, "TestTest").body()
//            val test = service.getNotifications(6).body()
//            val test = service.getCountsOfNotificationsORMessagesORFriendRequest(6).body()
//            val test = service.addGroups(6, "TestNadaaa", GroupPrivacy.PUBLIC.value).body()
//            val test = service.getAllPhotosInAlbum(236).body()
//            val test = service.getUserProfileAlbum(6, UserPictureType.profile).body()
//            val test = service.getProfileOrCoverPhotoDetails(31, 1680).body()
//            val test = service.getPhotoDetails(31, 1680).body()
//            val test = service.deleteSpecificProfilePhoto(3, 1751).body()
//            val test = service.deletePhoto(6, 2017).body()
//            val test = service.deleteSpecificCoverPhoto(6, 2024).body()
//            val test = service.getAllUserGroups(6).body()
//            val test = service.getGroupDetails(6,9).body()
//            val test = service.getAlbums(31, 31).body()
//            val test = service.getConversationWithFriend(31, 11).body()
//            val test = service.getUnreadMessages(3, 6).body()
//            val test = service.sendMessage(3,6,"test 123456 ").body()
//            val test = service.createAlbum(6, "Test1234", 3).body()
//            val test = service.deletePost(31,269).body()
//            val test= service.markNotificationsAsViewed(6).body()
//            Log.e("TESTLogin", test.toString())
        }
    }

}