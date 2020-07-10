package com.takusemba.jethub.repo

import android.net.Uri
import android.util.Log
import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.graphics.ImageAsset
import androidx.ui.graphics.painter.ImagePainter
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.layout.padding
import androidx.ui.layout.preferredSize
import androidx.ui.livedata.observeAsState
import androidx.ui.material.Button
import androidx.ui.material.IconButton
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import androidx.ui.res.vectorResource
import androidx.ui.text.style.TextAlign
import androidx.ui.unit.dp
import com.takusemba.jethub.base.viewmodel.NavigationViewModel
import com.takusemba.jethub.model.Owner
import com.takusemba.jethub.model.Repository
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun RepoScreen(
  repoViewModel: RepoViewModel,
  navigationViewModel: NavigationViewModel
) {
  val repo = repoViewModel.repository.observeAsState(Repository.EMPTY)
  Scaffold(
    topBar = { RepoTopBar() },
    bodyContent = { innerPadding ->
      Column {
        Header(repo.value.owner)
        Body(repo = repo.value, navigationViewModel = navigationViewModel)
      }
    }
  )
}

@Composable
fun RepoTopBar() {
  TopAppBar(
    title = {},
    navigationIcon = {
      IconButton(onClick = { }) {
        Icon(vectorResource(R.drawable.ic_back))
      }
    },
    backgroundColor = MaterialTheme.colors.surface
  )
}

@Composable
fun Header(owner: Owner) {
  Column {
    Row {
      // FIXME this does not work...
      CoilImage(
        data = Uri.parse(owner.avatarUrl),
        modifier = Modifier.preferredSize(128.dp),
        getFailurePainter = { error ->
          Log.d("TEST", "error: ${error.throwable.message}")
          ImagePainter(ImageAsset(10, 10))
        }
      )
      Text(text = owner.login)
    }
  }
}

@Composable
fun Body(
  repo: Repository,
  navigationViewModel: NavigationViewModel
) {
  Text(
    text = "This screen is under development...",
    textAlign = TextAlign.Center,
    style = MaterialTheme.typography.subtitle1,
    color = MaterialTheme.colors.onSurface
  )
  Box(
    gravity = ContentGravity.Center
  ) {
    Text(
      text = "Repo Fragment",
      textAlign = TextAlign.Center,
      style = MaterialTheme.typography.h4,
      color = MaterialTheme.colors.onSurface
    )
    Text(
      text = repo.toString(),
      textAlign = TextAlign.Center,
      style = MaterialTheme.typography.caption,
      color = MaterialTheme.colors.onSurface
    )
    Button(onClick = { navigationViewModel.openDeveloper(repo.owner.login) }) {
      Text(
        text = "Go to Developer Fragment",
        modifier = Modifier.padding(8.dp)
      )
    }
  }
}
