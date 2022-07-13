package com.medium.viewpager

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun VerticalPagerScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        val items = createItems()
        val pagerState = rememberPagerState()
        val coroutineScope = rememberCoroutineScope()

        Row(
            modifier = Modifier.weight(1f)
        ) {
            VerticalPager(
                count = items.size,
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { currentPage ->
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = items[currentPage].title,
                        style = MaterialTheme.typography.h2
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = items[currentPage].subtitle,
                        style = MaterialTheme.typography.h4
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = items[currentPage].description,
                        style = MaterialTheme.typography.body1
                    )
                }
            }

            VerticalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(16.dp),
            )
        }

        Button(
            onClick = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(page = 2)
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Scroll to the third page")
        }
    }
}

private fun createItems() = listOf(
    VerticalPagerContent(title = "Title1", subtitle = "Subtitle1", description = "Description1"),
    VerticalPagerContent(title = "Title2", subtitle = "Subtitle2", description = "Description2"),
    VerticalPagerContent(title = "Title3", subtitle = "Subtitle3", description = "Description3"),
    VerticalPagerContent(title = "Title4", subtitle = "Subtitle4", description = "Description4"),
    VerticalPagerContent(title = "Title5", subtitle = "Subtitle5", description = "Description5")
)

data class VerticalPagerContent(
    val title: String,
    val subtitle: String,
    val description: String
)
