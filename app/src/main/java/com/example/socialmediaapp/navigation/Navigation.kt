package com.example.socialmediaapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.socialmediaapp.views.auth.LoginScreen
import com.example.socialmediaapp.views.auth.ProfileScreen
import com.example.socialmediaapp.views.auth.SignUpScreen
import com.example.socialmediaapp.data.models.PostData
import com.example.socialmediaapp.views.CommentsScreen
import com.example.socialmediaapp.views.FeedScreen
import com.example.socialmediaapp.views.MyPostsScreen
import com.example.socialmediaapp.views.NewPostScreen
import com.example.socialmediaapp.utils.NotificationMessage
import com.example.socialmediaapp.views.SearchScreen
import com.example.socialmediaapp.views.SinglePostScreen
import com.example.socialmediaapp.viewmodels.AppViewModel
import com.example.socialmediaapp.views.SplashScreen

@Composable
fun SocialMediaApp() {
    val vm = hiltViewModel<AppViewModel>()
    val navController = rememberNavController()

    NotificationMessage(vm = vm)

    NavHost(navController = navController, startDestination = DestinationScreen.Splash.route) {
        composable(DestinationScreen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(DestinationScreen.SignUp.route) {
            SignUpScreen(navController = navController, vm = vm)
        }
        composable(DestinationScreen.Login.route) {
            LoginScreen(navController = navController, vm = vm)
        }
        composable(DestinationScreen.Feed.route) {
            FeedScreen(navController = navController, vm = vm)
        }
        composable(DestinationScreen.Search.route) {
            SearchScreen(navController = navController, vm = vm)
        }
        composable(DestinationScreen.MyPosts.route) {
            MyPostsScreen(navController = navController, vm = vm)
        }
        composable(DestinationScreen.Profile.route) {
            ProfileScreen(navController = navController, vm = vm)
        }
        composable(DestinationScreen.NewPost.route) { navBackStachEntry ->
            val imageUri = navBackStachEntry.arguments?.getString("imageUri")
            imageUri?.let {
                NewPostScreen(navController = navController, vm = vm, encodedUri = it)
            }
        }
        composable(DestinationScreen.SinglePost.route) {
            val postData = navController
                .previousBackStackEntry
                ?.arguments
                ?.getParcelable<PostData>("post")
            postData?.let {
                SinglePostScreen(
                    navController = navController,
                    vm = vm,
                    post = postData
                )
            }
        }
        composable(DestinationScreen.CommentsScreen.route) { navBackStackEntry ->
            val postId = navBackStackEntry.arguments?.getString("postId")
            postId?.let { CommentsScreen(navController = navController, vm = vm, postId = it) }
        }
    }
}

sealed class DestinationScreen(val route: String) {
    object Splash : DestinationScreen("splash")
    object SignUp : DestinationScreen("signup")
    object Login : DestinationScreen("login")
    object Feed : DestinationScreen("feed")
    object Search : DestinationScreen("search")
    object MyPosts : DestinationScreen("myposts")
    object Profile : DestinationScreen("profile")
    object NewPost : DestinationScreen("newpost/{imageUri}") {
        fun createRoute(uri: String) = "newpost/$uri"
    }

    object SinglePost : DestinationScreen("singlepost")
    object CommentsScreen : DestinationScreen("comments/{postId}") {
        fun createRoute(postId: String) = "comments/$postId"
    }
}
