import app.cash.turbine.test
import com.farhad.jollyjournal.com.farhad.jollyjournal.item.NewsItem
import com.farhad.jollyjournal.com.farhad.jollyjournal.item.NewsItemType
import com.farhad.jollyjournal.com.farhad.jollyjournal.mapper.NewsItemMapper
import com.farhad.jollyjournal.com.farhad.jollyjournal.view.home.HomeViewModel
import com.farhad.jollyjournal.domain.model.News
import com.farhad.jollyjournal.domain.model.NewsType
import com.farhad.jollyjournal.domain.usecase.GetNews
import com.farhad.jollyjournal.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit


@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val rule = MockitoJUnit.rule()

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var getNews: GetNews

    @Mock
    private lateinit var newsItemMapper: NewsItemMapper

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = HomeViewModel(getNews, newsItemMapper)
    }

    @Test
    fun testRetrySuccessState() = runTest {//(UnconfinedTestDispatcher()) {
        val mockNewsList = listOf(dummyArticle, dummyVideo)
        val mockNewsItems = listOf(dummyArticleItem, dummyVideoItem)

        `when`(getNews()).thenReturn(Result.success(mockNewsList))
        `when`(newsItemMapper.toPresentation(mockNewsList)).thenReturn(mockNewsItems)

        val job = launch {
            viewModel.retry()
        }
        viewModel.state.test {
            assertEquals(HomeViewModel.UiState.Loading, awaitItem())
            assertEquals(HomeViewModel.UiState.Loaded(mockNewsItems), awaitItem())
        }
        job.cancel()
    }

    @Test
    fun testRetryErrorState() = runTest {
        `when`(getNews()).thenReturn(Result.failure(RuntimeException()))

        viewModel.retry()

        assertEquals(HomeViewModel.UiState.Error, viewModel.state.value)
    }

    @Test
    fun testRetryErrorStateGetNewsException() = runTest {
        `when`(getNews()).thenThrow(RuntimeException())

        viewModel.retry()

        assertEquals(HomeViewModel.UiState.Error, viewModel.state.value)
    }

    private val dummyArticleItem = NewsItem.Article(
        type = NewsItemType.ARTICLE,
        imageURL = "https://example.com/article.jpg",
        headline = "Sample Article Headline",
        description = "Sample Article Description",
        articleURL = "https://example.com/article",
        hashtags = listOf("#sample", "#news"),
        isPaid = false
    )

    private val dummyVideoItem = NewsItem.Video(
        type = NewsItemType.VIDEO,
        imageURL = "https://example.com/video.jpg",
        headline = "Sample Video Headline",
        videoType = "Sample Video Type",
        duration = "2:30",
        videoURL = "https://example.com/video",
        isPaid = true
    )

    private val dummyArticle = News.Article(
        type = NewsType.ARTICLE,
        imageURL = "https://example.com/article.jpg",
        headline = "Sample Article Headline",
        description = "Sample Article Description",
        articleURL = "https://example.com/article",
        hashtags = listOf("#sample", "#news"),
        isPaid = false
    )

    private val dummyVideo = News.Video(
        type = NewsType.VIDEO,
        imageURL = "https://example.com/video.jpg",
        headline = "Sample Video Headline",
        videoType = "Sample Video Type",
        duration = "2:30",
        videoURL = "https://example.com/video",
        isPaid = true
    )
}